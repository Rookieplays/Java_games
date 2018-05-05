import java.util.*;
import javax.swing.*;
import java.io.*;

public class ClassSelection
{
	 static ArrayList<ArrayList<String>>Characters=new ArrayList<ArrayList<String>>();
	public ClassSelection()
	{

	}
	
	public String SelectedClass()throws IOException
	{
		loadClassFile();
		String selection;
		ArrayList<String>charactersArr=new ArrayList<String>();
		for(int i=0;i<Characters.get(0).size();i++)

		charactersArr.add(Characters.get(1).get(i));
		String[]classes;
		classes=convertToArray(charactersArr);
		selection=(String)JOptionPane.showInputDialog(null,"Select a Class to Join.","Avatar Creation",1,null,classes,classes[0]);
		System.out.println(selection);
		
		return selection;
	}
	public static String[] convertToArray(ArrayList<String>listToConvert)
	{
		String[]arr=new String[listToConvert.size()];
		for (int i=0; i<listToConvert.size(); i++) 
			arr[i]=listToConvert.get(i);
		return arr;
			
		
	}
	public static void loadClassFile()throws IOException
	{
		String []temp;
		File filename=new File("Characters.txt");
		Characters.add(new ArrayList<String>());
		Characters.add(new ArrayList<String>());
		if (filename.exists())
		{
			Scanner fileReader=new Scanner(filename);
			while(fileReader.hasNext())
			{
				temp=fileReader.nextLine().split(",");
				for(int i=0;i<Characters.size();i++)
					Characters.get(i).add(temp[i]);
			}
		}

	}
}