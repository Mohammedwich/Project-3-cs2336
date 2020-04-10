import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Pattern;

//Mohammed Ahmed, msa190000

public class Main
{

	public static void main(String[] args) throws IOException
	{
	
		
		//Take file name from user and open the files
		String databaseFileName;
		String batchFileName;
		Scanner inputReader = new Scanner(System.in);
		
		System.out.println("Enter the database file name: ");
		databaseFileName = inputReader.nextLine();
		
		System.out.println("Enter the batch file name: ");
		batchFileName = inputReader.nextLine();
		
		File databaseFile = new File(databaseFileName);
		if(databaseFile.exists() == false)
		{
			System.out.println("Database File does not exist");
		}
		
		File batchFile = new File(batchFileName);
		if(batchFile.exists() == false)
		{
			System.out.println("Batch File does not exist");
		}
		
		//create output file that will hold the displayed tree
		File outputDatFile = new File("cidercadeDAT.TXT");
		outputDatFile.createNewFile();
				
		//create output file that will hold batch file commands' output
		File outputLogFile = new File("cidercadeLOG.TXT");
		outputLogFile.createNewFile();
		
		
		Scanner databaseReader = new Scanner(databaseFile);
		Scanner batchReader = new Scanner(batchFile);
		FileWriter treeWriter = new FileWriter(outputDatFile);
		FileWriter logWriter = new FileWriter(outputLogFile);
		
		///////////////////////////////////////////////////////////////////////////////////////////
		
		
		//This tree will hold all the records and have commands from the batch file executed upon it
		BinTree<Payload> databaseTree = new BinTree<Payload>();
		
		
		// Read database file and store items in the tree
		while(databaseReader.hasNextLine())
		{
			String currentLine = databaseReader.nextLine();
			//TODO: use regex to skip invalid lines here if needed
			
			Scanner lineReader = new Scanner(currentLine);
			lineReader.useDelimiter(", "); // terms separated by comma and space
			
			while(lineReader.hasNext())
			{
				String name = lineReader.next();
				
				String highScoreString = lineReader.next();
				int highScore = Integer.parseInt(highScoreString);
				
				String initials = lineReader.next();
				
				String playsString = lineReader.next();
				int plays = Integer.parseInt(playsString);
				
				String revenueString = lineReader.next();
				//TODO: parse revenue if needed here
				
				//create and add the record to the tree
				Payload thePayload = new Payload(name, highScore, initials, plays);
				Node<Payload> theNode = new Node<Payload>(thePayload);
				databaseTree.insert(theNode, databaseTree.getRoot());
			}
			lineReader.close();
		}
		
		// execute commands from the batch file
		while(batchReader.hasNextLine())
		{
			String currentLine = batchReader.nextLine();
			//TODO: confirm valid line if needed
			Scanner lineReader = new Scanner(currentLine);
			
			while(lineReader.hasNext())
			{
				String commandString = lineReader.next();
				int command = Integer.parseInt(commandString);
				
				// Add record command
				if(command == 1)
				{
					//name in quotations so we want to pick the name out
					Pattern pattern = Pattern.compile("[0-9a-zA-Z]");
					lineReader.useDelimiter(pattern);
					lineReader.next(); // ditch the space and opening quotation mark
					lineReader.useDelimiter("\" ");
					String name = lineReader.next();
					lineReader.useDelimiter(" ");
					
					lineReader.next(); //ditch the ending quotation mark
					String highScoreString = lineReader.next();					
					int highScore = Integer.parseInt(highScoreString);
					
					String initials = lineReader.next();
					
					String playsString = lineReader.next();
					int plays = Integer.parseInt(playsString);
					
					String revenueString = lineReader.next();
					//TODO: parse revenue if needed here
					
					// add record to tree
					Payload thePayload = new Payload(name, highScore, initials, plays);
					Node<Payload> theNode = new Node<Payload>(thePayload);
					databaseTree.insert(theNode, databaseTree.getRoot());
					
					//log command to log file
					logWriter.append("RECORD ADDED\n");
					logWriter.append(thePayload.toString() + "\n\n\n");
					
				} 
				//Search record command
				else if(command == 2)
				{
					//Collect the multiple words of a name without parentheses
					StringBuilder searchTermBuilder = new StringBuilder();
					searchTermBuilder.append(lineReader.next()); //put in the first word
					while(lineReader.hasNext()) //put in the following words with a space preceeding them
					{
						searchTermBuilder.append(" ");
						searchTermBuilder.append(lineReader.next());
					}
					
					String searchTerm = searchTermBuilder.toString();
					ArrayList<Node<Payload>> searchResultsList = new ArrayList<Node<Payload>>();
					
					//Fill up the list with all partial matches
					Payload thePayload = new Payload(searchTerm);
					Node<Payload> theNode = new Node<Payload>(thePayload);
					databaseTree.search(theNode, databaseTree.getRoot(), searchResultsList);
					
					// log command to log file
					if(searchResultsList.isEmpty())
					{
						logWriter.append(searchTerm + " NOT FOUND\n\n\n");
					}
					else
					{						
						for(Node<Payload> currentNode : searchResultsList)
						{
							logWriter.append(currentNode.getObject().getName() + " FOUND\n");
							logWriter.append(currentNode.getObject().toStringWithoutName() + "\n");
						}
						logWriter.append("\n");
					}
					
				}
				else if(command == 3) //Edit an entry
				{
					//name in quotations so we want to pick the name out
					Pattern pattern = Pattern.compile("[0-9a-zA-Z]");
					lineReader.useDelimiter(pattern);
					lineReader.next(); // ditch the space and opening quotation mark
					lineReader.useDelimiter("\" ");
					
					String name = lineReader.next();
					
					lineReader.useDelimiter(" ");
					lineReader.next(); //ditch the ending quotation mark
					
					String fieldNumberString = lineReader.next();
					int fieldNumber = Integer.parseInt(fieldNumberString);
					String fieldDescription = "";
					
					String newValueString = lineReader.next();
					
					Node<Payload> keywordHolderNode = new Node<Payload>(new Payload(name));
					Node<Payload> changingNode = databaseTree.findNode(keywordHolderNode, databaseTree.getRoot());
					
					if(fieldNumber == 1) //change high score
					{
						int newHighscore = Integer.parseInt(newValueString);
						(changingNode.getObject()).setHighScore(newHighscore);
						
						databaseTree.edit(keywordHolderNode, changingNode);
						
						fieldDescription = "high score";
					}
					else if(fieldNumber == 2) //change initials
					{
						String newInitials = newValueString;
						(changingNode.getObject()).setInitials(newInitials);
						
						databaseTree.edit(keywordHolderNode, changingNode);

						fieldDescription = "initials";
					}
					else if(fieldNumber == 3) //change plays
					{
						int newPlays = Integer.parseInt(newValueString);
						(changingNode.getObject()).setPlays(newPlays);
						
						databaseTree.edit(keywordHolderNode, changingNode);

						fieldDescription = "plays";
					}
					
					//log command to log file
					logWriter.append(name + " UPDATED\n");
					logWriter.append("UPDATE TO "+ fieldDescription + " - " + "VALUE " + newValueString + "\n");
					logWriter.append(changingNode.getObject().toStringWithoutName() + "\n\n");
					
				} //end of edit command
				// delete a record
				else if(command == 4)
				{
					//Collect the multiple words of a name without parentheses
					StringBuilder deleteTermBuilder = new StringBuilder();
					deleteTermBuilder.append(lineReader.next()); //put in the first word
					while(lineReader.hasNext()) //put in the following words with a space preceeding them
					{
						deleteTermBuilder.append(" ");
						deleteTermBuilder.append(lineReader.next());
					}
					
					String deleteTerm = deleteTermBuilder.toString();
										
					Node<Payload> keywordHolderNode = new Node<Payload>(new Payload(deleteTerm));
					Node<Payload> nodeToDelete = databaseTree.findNode(keywordHolderNode, databaseTree.getRoot());
					
					if(nodeToDelete != null)
					{
						logWriter.append("RECORD DELETED\n");
						logWriter.append(nodeToDelete.toString() + "\n\n\n");
						
						databaseTree.delete(nodeToDelete, databaseTree.getRoot());
					}
					else
					{
						logWriter.append(deleteTerm + " NOT FOUND\n\n\n");
					}
					
				}
				//sort record
				else if(command == 5)
				{
					String ascOrDec = lineReader.next();
					ArrayList<Node<Payload>> sortedList = new ArrayList<Node<Payload>>(); //will be fed to getSorted()					
					
					if(databaseTree.getRoot() != null)
					{
						if(ascOrDec.compareTo("asc") == 0)
						{
							databaseTree.getSortedAsList(databaseTree.getRoot(), true, sortedList);
							
							logWriter.append("RECORDS SORTED ASCENDING\n");
							
							//write each item as a single-line entry to the log file
							for(Node<Payload> currentNode : sortedList)
							{
								StringBuilder recordBuilder = new StringBuilder();
								
								recordBuilder.append(currentNode.getObject().getName() + ", ");
								recordBuilder.append(currentNode.getObject().getHighScore() + ", ");
								recordBuilder.append(currentNode.getObject().getInitials() + ", ");
								recordBuilder.append(currentNode.getObject().getPlays() + ", ");
								recordBuilder.append(currentNode.getObject().getRevenueAsTwoDecimalStringWithDollarSign());
								
								logWriter.append(recordBuilder.toString());
							}
								
							logWriter.append("\n\n");
							
						}
						else if(ascOrDec.compareTo("dec") == 0)
						{
							databaseTree.getSortedAsList(databaseTree.getRoot(), false, sortedList);
							
							logWriter.append("RECORDS SORTED DESCENDING\n");

							//write each item as a single-line entry to the log file
							for(Node<Payload> currentNode : sortedList)
							{
								StringBuilder recordBuilder = new StringBuilder();
								
								recordBuilder.append(currentNode.getObject().getName() + ", ");
								recordBuilder.append(currentNode.getObject().getHighScore() + ", ");
								recordBuilder.append(currentNode.getObject().getInitials() + ", ");
								recordBuilder.append(currentNode.getObject().getPlays() + ", ");
								recordBuilder.append(currentNode.getObject().getRevenueAsTwoDecimalStringWithDollarSign());
								
								logWriter.append(recordBuilder.toString());
							}
							
							logWriter.append("\n\n");
						}
						else
						{
							logWriter.append("Failed to write sorted\n\n");
						}
					}
					
				} //sort command end				
			}
			lineReader.close();
		} //batch file processing end
			
		
		ArrayList<Node<Payload>> breadthOrderList = databaseTree.getAsBreadthFirstList();		
		//write each item as a single-line entry to the log file
		for(Node<Payload> currentNode : breadthOrderList)
		{
			StringBuilder recordBuilder = new StringBuilder();
			
			recordBuilder.append(currentNode.getObject().getName() + ", ");
			recordBuilder.append(currentNode.getObject().getHighScore() + ", ");
			recordBuilder.append(currentNode.getObject().getInitials() + ", ");
			recordBuilder.append(currentNode.getObject().getPlays() + ", ");
			recordBuilder.append(currentNode.getObject().getRevenueAsTwoDecimalStringWithDollarSign());
			
			treeWriter.append(recordBuilder.toString());
		}
		
		treeWriter.append("\n\n");
		
		
		
		inputReader.close();
		databaseReader.close();
		batchReader.close();
		treeWriter.close();
		logWriter.close();		
		
	} // main end

	////////////////////////////////////////////////////////////////////////////////////////////
	
	
}
