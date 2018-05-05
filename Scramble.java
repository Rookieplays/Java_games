import javax.swing.*;
import java.util.*;
import java.io.*;

public class Scramble
{
	static char []letterBag=new char[200];
	static int Player1PointsEarned=0;
	static int Player2PointsEarned=0;
	static int life=5;
	static int life1=5;
	static int points=2,xp=0,coins=0;

	static ArrayList<String> dictionary;
	static int letterValue[]=new int[8];

public Scramble() throws IOException
{
	String input="";
	xp=runGame(input);
}

	public static int runGame(String input)throws IOException
	{
		 input="";
				
		scramble(input);
			if (Player2PointsEarned>Player1PointsEarned)
				JOptionPane.showMessageDialog(null,"Player one Earned "+ Player1PointsEarned+" Points \n Player two Earned "+ Player2PointsEarned+" Points\n Player 2 Wins!!!! ");
			else if (Player1PointsEarned>Player2PointsEarned)
				JOptionPane.showMessageDialog(null,"Player one Earned "+ Player1PointsEarned+" Points \n Player two Earned "+ Player2PointsEarned+" Points\n Player 1 Wins!!!! ");

			else JOptionPane.showMessageDialog(null,"Player one Earned "+ Player1PointsEarned+" Points \n Player two Earned "+ Player2PointsEarned+" Points\n Well This is rare its a draw? ");
		return xp;

	}

	public static int scramble(String input)throws IOException
	{
		String alphabets="a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z";
		boolean validInput, completed=false, enoughHP=false;
		char[] player1Letters=new char[8];
		int randomL;
		String input2="";
		int points=0;
		int badword=0;
		 int Xp=0;
		String pattern="[a-zA-Z]+";
		int used=0;
		int []storeUsed;
		int posOfLetter;
		String currentLetter, currentLetterWord="",cl,cw,css;
		boolean uniqueletters=false;
		//fill bag up with random letters need to make vowels more common
		for (int i=0; i<letterBag.length;i++)
		 {Random fillBag=new Random();
		char l=(char)(fillBag.nextInt(26)+'A');
		letterBag[i]=l;
			
		}System.out.println(letterBag);
		System.out.print("Player ones's Letters are: ");

		for (int i=0; i<player1Letters.length; i++)
		 { randomL=(int)(Math.random()*player1Letters.length);
			player1Letters[i]=letterBag[randomL];
			pointDistribution(player1Letters[i]+"",i);
			
		}
	if(input!=null)
	{		
			input=JOptionPane.showInputDialog(null,"Letters: "+Arrays.toString(player1Letters)+"\nPoints: "+Arrays.toString(letterValue)+"\nEnter a Word","Scramble: player 1",1);
				 validInput = validateInput(input, pattern);
						 System.out.println(validInput);
       	if(validInput)
       	{	
       		if(input == null) //Check to see if user decides to exit during the current round
			{
				completed= true;input="";
			}

					input=input.toUpperCase();
					 if (input.equals("f")||input.equals("F"))JOptionPane.showMessageDialog(null,Arrays.toString(player1Letters)," Your Letters",1);
							
					System.out.println("Proccesing word....\n checking for illegal letters...");
					for(int j=0; j<player1Letters.length; j++)
					{
						currentLetterWord += player1Letters[j]+"";
					}	
						System.out.println("\n"+currentLetterWord);
					for(int i=0;i<input.length();i++)
					{
						currentLetter=input.charAt(i)+"";
						if(!(currentLetterWord.contains(currentLetter)))
						badword++;
					}
						System.out.println("Success.");
						System.out.println(input);
						if(badword>=1)
						{
							System.out.println("\nYour word "+input+" was made up of letters you dont have.");	
						}
						else
						{
							System.out.println("\nword is made up of given letters.\n");
						}
//check if word is real
						System.out.println("Analyzing word ....");
						System.out.println("checking if word is in Dictionary...");
						Dictionary lookupWord=new Dictionary();
					if (lookupWord.wordExists(input))
						{		
							System.out.println("Word is Good.");

	//////Replacing Tiles//
							for(int i=0; i<input.length(); i++)
								{
										cl = input.charAt(i)+"";
		
								for(int j=0; j<player1Letters.length; j++)
										{

											cw = player1Letters[j]+"";
											if(cw.contains(cl))
												{
													 Player1PointsEarned+=pointDistribution(cw,j)/2;
			
														posOfLetter=currentLetterWord.indexOf(cw)+1;
														System.out.println(posOfLetter);
														currentLetterWord= currentLetterWord.replace(cw,"*");
														System.out.println(currentLetterWord);
														randomL=(int)(Math.random()*100);
														//player1Letters[j]=
														replaceletter(player1Letters,player1Letters[j ],letterBag[randomL]);
														//player1Letters[j]=player1Letters[j].replace(cw,letterBag[randomL]);
												}
										}
								}
									for(int i=0;i<player1Letters.length;i++)
									System.out.print(" "+player1Letters[i]);
								points=Player1PointsEarned+100;
										xp=xp+100;
										Xp=xp;
										coins=(points*life)/xp;
									JOptionPane.showMessageDialog(null,"Your Earned "+Player1PointsEarned+" On your Word ","Points Earned ",1);
						}
						
	
						else
						{
							System.out.println("Invalid word.");
							life=life-1;
							JOptionPane.showMessageDialog(null,"You lost a life \n"+life+"  Left ","Warning",2);

						}
				}else if(input.equalsIgnoreCase("klose"))
							completed=true;
		else {JOptionPane.showMessageDialog(null,"Only english Aplhabets allowed.","error",2);}
		if (life==0)
       							completed=true;
	}
	if(life>0&&life1>0)scrambleII(input2);
	return Xp;
}


public static int scrambleII(String input)throws IOException
	{
		String alphabets="a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z";
		boolean validInput, completed=false, enoughHP=false;
		char[] Player2Letters=new char[8];
		int randomL;
		int points=0;
		String input2="";
		int badword=0;
		int Xp=0;
		String pattern="[a-zA-Z]+";
		int used=0;
		int []storeUsed;
		int posOfLetter;
		String currentLetter, currentLetterWord="",cl,cw,css;
		boolean uniqueletters=false;
		//fill bag up with random letters need to make vowels more common
		for (int i=0; i<letterBag.length;i++)
		 {
				 Random fillBag=new Random();
				char l=(char)(fillBag.nextInt(26)+'A');
				letterBag[i]=l;
			
		}
		System.out.println(letterBag);
		System.out.print("Player ones's Letters are: ");

		for (int i=0; i<Player2Letters.length; i++)
		 {
		 		 randomL=(int)(Math.random()*Player2Letters.length);
				Player2Letters[i]=letterBag[randomL];
				//System.out.print(" "+Player2Letters[i]);
				pointDistribution(Player2Letters[i]+"",i);
			
		}
	if(input!=null)
	{		
	
			input=JOptionPane.showInputDialog(null,"Letters: "+Arrays.toString(Player2Letters)+"\nPoints: "+Arrays.toString(letterValue)+"\nEnter a Word","Scramble: Player 2",1);
		
			 validInput = validateInput(input, pattern);
							 System.out.println(validInput);
       					if(validInput)
       						{	
       								if(input == null) //Check to see if user decides to exit during the current round
								{
									completed= true;input="";}input=input.toUpperCase();
									 if (input.equals("f")||input.equals("F"))JOptionPane.showMessageDialog(null,Arrays.toString(Player2Letters)," Your Letters",1);

										System.out.println("Proccesing word....\n checking for illegal letters...");
									for(int j=0; j<Player2Letters.length; j++)
									{
										currentLetterWord += Player2Letters[j]+"";
									}	System.out.println("\n"+currentLetterWord);
									for(int i=0;i<input.length();i++)
									{
										currentLetter=input.charAt(i)+"";
										if(!(currentLetterWord.contains(currentLetter)))
											badword++;
									}
									System.out.println("Success.");
									System.out.println(input);
									if(badword>=1)
									{
										System.out.println("\nYour word "+input+" was made up of letters you dont have.");	
									}
									else
									{
										System.out.println("\nword is made up of given letters.\n");
									}
//check if word is real
									System.out.println("Analyzing word ....");
									System.out.println("checking if word is in Dictionary...");
									Dictionary lookupWord=new Dictionary();
									if (lookupWord.wordExists(input))
									{		
										System.out.println("Word is Good.");

	//////Replacing Tiles//
											for(int i=0; i<input.length(); i++)
											{
												cl = input.charAt(i)+"";
		
												for(int j=0; j<Player2Letters.length; j++)
												{

													cw = Player2Letters[j]+"";
													//System.out.println("CL: "+currentLetterWord + " CV: "+ currentLetter);

													if(cw.contains(cl))
													{
													 Player2PointsEarned+=pointDistribution(cw,j)/2;
			
															posOfLetter=currentLetterWord.indexOf(cw)+1;
															System.out.println(posOfLetter);
															currentLetterWord= currentLetterWord.replace(cw,"*");
															System.out.println(currentLetterWord);
															randomL=(int)(Math.random()*100);
																//Player2Letters[j]=
																replaceletter(Player2Letters,Player2Letters[j ],letterBag[randomL]);
																//Player2Letters[j]=Player2Letters[j].replace(cw,letterBag[randomL]);
													}
												}			
											}
										for(int i=0;i<Player2Letters.length;i++)
										System.out.print(" "+Player2Letters[i]);
										
										JOptionPane.showMessageDialog(null,"Your Earned "+Player2PointsEarned+" On your Word ","Points Earned ",1);
									}

	
									else
									{
										System.out.println("Invalid word.");
										life1=life1-1;
										JOptionPane.showMessageDialog(null,"You lost a life \n"+life1+"  Left ","Warning",2);
									}
							}	else if(input.equalsIgnoreCase("klose"))
							completed=true;		
						else {
								JOptionPane.showMessageDialog(null,"Only english Aplhabets allowed.","error",2);
							}
				if (life1==0)
       					completed=true;
	}
	if(life>0&&life1>0)scramble(input2);
	return Xp;
}

		static void replaceletter(char[]arr,char find,char replace )
		{
			for (int i=0;i<arr.length;i++)
				if(arr[i]==find)
				{
					arr[i]=replace;
					return;
				}
		}
		public static boolean validateInput(String input, String pattern)
	{
		if(input == null)
			return true;
		if(input != null && input.equals(""))
			return false;
		else if(!input.matches(pattern))
			return false;
		else 
			return true;
	}
		public static int pointDistribution(String input,int i)
		{
			int point=0, point1=0;;
			 String onePointer="A|E|I|O|U|L|N|S|T|R";
		String twoPointers="D|G";
		String threePointers="B|C|M|P";
		String fourPointers="F|H|J|W|Y";
		String fivePointers="K";
		String eightPoiners="J|V|X";
		String temPointers="Q|Z";
			if (input.matches(onePointer)){point=point +1;point1=point1 +1;System.out.println(input+"="+point);}
			else if (input.matches(twoPointers)){point=point+2;point1=point1 +2;System.out.println(input+"="+point);}
			else if (input.matches(threePointers)){point=point+3;point1=point1 +3;System.out.println(input+"="+point);}
			else if (input.matches(fourPointers)){point=point+4;point1=point1 +4;System.out.println(input+"="+point);}
			else if (input.matches(fivePointers)){point=point+5;point1=point1 +5;System.out.println(input+"="+point);}
			else if (input.matches(eightPoiners)){point=point+8;point1=point1 +8;System.out.println(input+"="+point);}
			else if (input.matches(temPointers)){point=point+10;point1=point1 +10;System.out.println(input+"="+point);}
			else {point=point+0;}//System.out.println(input+"="+point);}
			letterValue[i]=point1;
			point+=point;
			return point;

		}
}// trying to add sound
//add timer
