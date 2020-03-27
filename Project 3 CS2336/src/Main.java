import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
		
		//create output file
		File outputLogFile = new File("cidercade.log");
		outputLogFile.createNewFile();
		
		
		
		
		
		inputReader.close();
	} // main end

}
