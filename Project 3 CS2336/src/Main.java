import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Mohammed Ahmed, msa190000

public class Main
{

	public static void main(String[] args) throws IOException
	{
		BinTree<Payload> testTree = new BinTree<Payload>();
		
		Payload item1 = new Payload("Jack", 2000, "J.R.", 7);
		Node<Payload> item1Node = new Node<Payload>(item1);
		testTree.insert(item1Node, testTree.getRoot());
		
		Payload item2 = new Payload("Billy", 3000, "B.J.", 9);
		Node<Payload> item2Node = new Node<Payload>(item2);
		testTree.insert(item2Node, testTree.getRoot());
		
		Payload item3 = new Payload("Bill", 1000, "B.B.", 8);
		Node<Payload> item3Node = new Node<Payload>(item3);
		testTree.insert(item3Node, testTree.getRoot());
		
		String testString = testTree.toString();
		
		System.out.println(testString);
		
		
		Node<Payload> searchNode = new Node<Payload>(new Payload("Bill"));
		Node<Payload> searchResultNode = testTree.search(searchNode, testTree.getRoot());
		
		ArrayList<Node<Payload>> searchList = new ArrayList<Node<Payload>>();
		testTree.search2(searchNode, testTree.getRoot(), searchList);
		
		System.out.println(searchList);
		
		/*
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
		File outputDatFile = new File("cidercade.dat");
		outputDatFile.createNewFile();
				
		//create output file that will hold batch file commands' output
		File outputLogFile = new File("cidercade.log");
		outputLogFile.createNewFile();
		
		
		
		BinTree<Node<Payload>> databaseTree = new BinTree<Node<Payload>>();
		
		
		
		
		
		inputReader.close();
		*/
	} // main end

}
