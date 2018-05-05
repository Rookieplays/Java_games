 import java.util.*;
import java.io.*;
import javax.swing.*;


public class game
 {
 	static ArrayList<ArrayList<String>>StudyDetails=new ArrayList<ArrayList<String>>();
 	public static void main(String[]args)throws IOException
 	{
 		//String input1="";
 		game();
 	}
 	public static void game( )throws IOException
 	{	
 		boolean keepGoing=false;

 		String lastLetter;
 		String firstLetter;
 		String lastLetter1;
 		String firstLetter1;
 		String recentWord="";
 		int points=0;
 		char checkLetter;
 		int indexofLastLetter;
 		String input1, input2;
 		String pattern="([a-zA-z]+)";
 		try{
 		do
 		{
 			input1=JOptionPane.showInputDialog(null,"Player 1: Enter your word");
 			input1=input1.trim();

 			//indexofLastLetter=recentWord.indexOf(recentWord.length()-1);
 			recentWord=input1.toUpperCase();
 			if(validateInput(input1,pattern))
 			{	
 				firstLetter1=recentWord.substring(0,1);

 				lastLetter1=recentWord.substring(recentWord.length()-1);
 				System.out.println("first Letter: "+ firstLetter1+"\tLast Letter: "+lastLetter1);
 				input2=JOptionPane.showInputDialog(null,"Player 2: Enter your word");
 				if (validateInput(input2,pattern)) {
 					
 				
 				recentWord=input2.toUpperCase();
 				firstLetter=recentWord.substring(0,1);
 				lastLetter=recentWord.substring(recentWord.length()-1);
 				System.out.println("first Letter: "+ firstLetter+"\tLast Letter: "+lastLetter);
 				if(lastLetter1.equals(firstLetter)||lastLetter1.equals(firstLetter))
 			{
 				keepGoing=true;
 			points=points+1;
 			}
 			else
 			{
 				System.out.println("Ooops your first letter Failed to match the previous lastLetter");
 				JOptionPane.showMessageDialog(null,"You Did not match Player one's last Letter.","Game Over",0);
 				break;
 			}
 		  }
 		}

 		}
 		while(keepGoing);
 		}
 	catch(NullPointerException e)
			{System.out.println("closing application...");}
		
 	}
 	public static boolean validateInput(String input,String pattern)throws IOException
	{
		if(input!=null&&input.equals(""))
		{	JOptionPane.showMessageDialog(null,"Empty input","Error",0);
		game();
			return false;
		}
		
		else if(!(input.matches(pattern)))
			{
				JOptionPane.showMessageDialog(null,"Format of input is incorrect, A word must be supplied","Error",0);
				game();
			return false;
		}
		else if (input==null) {
			try{game();}
			catch(NullPointerException e)
			{System.out.println("closing application...");}
			return true;

			
		}

		else
			return true;
	}
 	public static int simulateDie(int times)
	{
		int []die1=new int[6];
		int[] die2=new int[6];
		int randomdie1;
		int randomdie2;
		int frequency=0;
		int sum=0;
		double end=0;
		for (int i=0;i<5;i++)
		{
			die1[i]=i+1;
			die2[i]=i+1;
			if (die1[i]==times&&
				die2[i]==times)
 				frequency=frequency+1;

		}
		for(int i=0;i<times;i++)
		{
		randomdie1=(int)(Math.random()*5);
		randomdie2=(int)(Math.random()*5);
		System.out.print(die1[randomdie1]+" "+die2[randomdie2]+"\t");
		}
		for(int i:die1)
 			sum+=i;
 		for(int i:die2)
 			sum+=i;
 		end=frequency/sum;
 		System.out.println(frequency);
		return times;
	}
 	public static double reportOnSuccess(int frequency,int times )
 	{

		int []die1=new int[6];
		int[] die2=new int[6];
 		times=0;
 		 frequency=0;
 		int sum=0;
 		double end=0;
 		simulateDie(times);
 		for (int i=0; i<5; i++) {
 			if (die1[i]==times||die2[i]==times)
 				frequency=frequency+1;

 		}
 		for(int i:die1)
 			sum+=i;
 		for(int i:die1)
 			sum+=i;
 		end=frequency/sum;
 		System.out.println(end);
 		return frequency;
 	}
 	
 }