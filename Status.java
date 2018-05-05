import java.util.*;
import java.io.*;

public class Status
{
	static ArrayList<ArrayList<String>> Status = new ArrayList<ArrayList<String>>();
		
	public Status()
	{
		int gamePod;
		int statuspos;
	}
	public boolean getStatus(int gamePos,int statusPos)throws IOException
	{
		loadFile();System.out.println("getting status");System.out.println(Status.get(gamePos).get(statusPos));
		if ((Status.get(gamePos).get(statusPos)).equals("UNLOCKED"))
			{
			return true;}
		else 
			return false;

	}
	public boolean setStatus(int gamePos,int statusPos)throws IOException
	{
		//System.out.println(Status.get(gamePos).get(statusPos));
		String status="UNLOCKED";System.out.println("Setting status");
		File randomFile = new File("Status.txt");
		PrintWriter write = new PrintWriter(randomFile);
		Status.get(gamePos).set(statusPos,status);
		int i=0;
		while(i < Status.get(0).size())
		{
			write.println(Status.get(0).get(i)+","+Status.get(1).get(i)+","+Status.get(2).get(i)+","+Status.get(3).get(i)+","+Status.get(4).get(i)+","+Status.get(5).get(i)+","+Status.get(6).get(i)); //overriding the files previous information with updated information
			i++; 
		}
		write.close();System.out.println(Status.get(gamePos).get(statusPos));
		return true;
	}
	public static void loadFile()throws IOException
	{
		File status=new File("Status.txt");
		for(int i=0; i<7; i++)
		Status.add(new ArrayList<String>());

		String[]temp;

		 if(status.exists())
		{
		Scanner	fileReader = new Scanner(status);

			while(fileReader.hasNext())
			{
				temp = fileReader.nextLine().split(",");
				for(int i=0; i<Status.size(); i++)
					Status.get(i).add(temp[i]);
			}System.out.println(Status.size());System.out.println(Status.get(2));
			fileReader.close();
		}
	}
	
}