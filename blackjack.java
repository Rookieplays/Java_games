import java.util.*;
import javax.swing.*;
import java.io.*;
public class blackjack{
	static Scanner sc=new Scanner(System.in);
	static ArrayList<Integer>Deck=new ArrayList<Integer>();
	static ArrayList<String>wordDeck=new ArrayList<String>();
	static		int totalPoints=0;
	static int dealertotalPoints=0;
	static int bet=0;
public static void main(String []args)throws IOException
{
	//String name="";
	
		Music BGM=new Music();
	String song=BGM.getListOfSongs();
	BGM.playMusic(true,song);
	runGame();


}
	public static void runGame()throws IOException
{


	
	System.out.println("Running game...?");
	String input="y";
	int bank=200;
	boolean keepPicking=false;
while(repeat(input)==true&&bank>0)
{	System.out.println("You have "+bank +" euro. How Much do want to bet?");bank=Bank(bank,input);
	System.out.println(bet);	
	if(bet>bank||bet<10)
	{
		System.out.println("This bet is not possible..");
		runGame();
	}
	dealertotalPoints=0; totalPoints=0;Deck.clear();distributecards();
String	anotherCard="yes";
	while(repeat(anotherCard)==true&&totalPoints<=21)
{
		System.out.println("Do you want another card ?");
	 anotherCard=sc.nextLine();
	
	if (repeat(anotherCard)==true)
	{
		int randomSelection=(int)(Math.random()*52);
		int NewCard= Deck.get(randomSelection);
		String NewWordCard=wordDeck.get(randomSelection);

		totalPoints+=NewCard;
		System.out.println(NewWordCard + "\t\t Total Points: "+totalPoints);
	}else

	keepPicking=true;
	if (repeat(anotherCard)==false||totalPoints>=21)
	{	System.out.print("\tPlayer Total Points: "+ totalPoints);
		simiulateAI();
		whoWon(dealertotalPoints,totalPoints,bank,bet);

		System.out.println("You have "+bank +" euro. Do you want to play again y/n/yes/no");
		keepPicking=false;
		input=sc.nextLine();
		
		
	}
}
}

}
public static int  Bank(int bank,String Input)
{
	
	String userInput=sc.nextLine();
	 bet=Integer.parseInt(userInput);
	 
	 bank=bank-bet;
	 
	 return bank;


}
public static boolean repeat(String userInput)
{
	if (userInput.equalsIgnoreCase("y")||userInput.equalsIgnoreCase("yes"))
		return true;
	else if(userInput.equalsIgnoreCase("n")||userInput.equalsIgnoreCase("no"))
		return false;
	else return false;
}
public static void loadDeck()
	{
		
		
		int []heartDeck=new int [10];
		int []diamondsDeck=new int [10];
		int []clovesDeck=new int [10]; 
		int []shamrockDeck=new int [10];
		String[]Numbers=new String[37];
		int []king=new int[5];
		int []queen=new int[5];
		int []jack=new int [5];
		int []ace=new int[5];
		String suits[]=new String [52];
		
		for (int i=1;i<heartDeck.length;i++)
			{
				heartDeck[i]=i;Deck.add(heartDeck[i]);
				diamondsDeck[i]=i;Deck.add(diamondsDeck[i]);
				clovesDeck[i]=i;Deck.add(clovesDeck[i]);
				shamrockDeck[i]=i;Deck.add(shamrockDeck[i]);

			}
			System.out.println();
		for(int i=1;i<king.length;i++)
		{int AceSelection=(int)(Math.random()*2);
				king[i]=10;Deck.add(king[i]);
				queen[i]=10;Deck.add(queen[i]);
				jack[i]=10;Deck.add(jack[i]);
				if (AceSelection==1)
				ace[i]=1;
			else
				ace[i]=11;
			
			Deck.add(ace[i]);

		}

		for (int i=0; i<4; i++) {
			switch(Deck.get(i)){case 1: Numbers[i]="One"; break;}
	//System.out.println(Numbers[i]);
		}
		for (int i=4; i<8; i++) {
			switch(Deck.get(i)){case 2: Numbers[i]="Two"; break;}
				//System.out.println(Numbers[i]);
			}
	for (int i=8; i<12; i++) {
			switch(Deck.get(i)){case 3: Numbers[i]="Three"; break;}
	//System.out.println(Numbers[i]);
}
	for (int i=12; i<16; i++) {
			switch(Deck.get(i)){case 4: Numbers[i]="Four"; break;}
	//System.out.println(Numbers[i]);
		}
	for (int i=16; i<20; i++) {
			switch(Deck.get(i)){case 5: Numbers[i]="Five"; break;}
	//System.out.println(Numbers[i]);
}
	for (int i=20; i<24; i++) {
			switch(Deck.get(i)){case 6: Numbers[i]="Six"; break;}
	//System.out.println(Numbers[i]);
		}
	for (int i=24; i<28; i++) {
			switch(Deck.get(i)){case 7: Numbers[i]="Seven"; break;}
	//System.out.println(Numbers[i]);
		}
	for (int i=28; i<32; i++) {
			switch(Deck.get(i)){case 8: Numbers[i]="eight"; break;}
	//System.out.println(Numbers[i]);
		}
	for (int i=32; i<36; i++) {
			switch(Deck.get(i)){case 9: Numbers[i]="Nine"; break;}
	//System.out.println(Numbers[i]);
		}
	//System.out.println(Deck.get(36));
	
switch(Deck.get(36)){case 10: suits[36]="King"; break;}
	  switch(Deck.get(40)){case 10: suits[37]="King"; break;}
	   switch(Deck.get(44)){case 10: suits[38]="King"; break;}
	    switch(Deck.get(48)){case 10: suits[39]="King"; break;}
		
	 switch(Deck.get(37)){case 10: suits[40]="queen"; break;}
	  switch(Deck.get(41)){case 10: suits[41]="queen"; break;}
	   switch(Deck.get(45)){case 10: suits[42]="queen"; break;}
	    switch(Deck.get(49)){case 10: suits[43]="queen"; break;}
		
	 switch(Deck.get(38)){case 10: suits[44]="jack"; break;}
	  switch(Deck.get(42)){case 10: suits[45]="jack"; break;}
	   switch(Deck.get(46)){case 10: suits[46]="jack"; break;}
	    switch(Deck.get(50)){case 10: suits[47]="jack"; break;}
		
	switch(Deck.get(39)){case 1: suits[48]="Ace";break; case 11:suits[48]="Ace";break;}
	switch(Deck.get(43)){case 1: suits[49]="Ace";break; case 11:suits[49]="Ace";break;}
	switch(Deck.get(47)){case 1: suits[50]="Ace";break; case 11:suits[50]="Ace";break;}
	switch(Deck.get(51)){case 1: suits[51]="Ace";break; case 11:suits[51]="Ace";break;}
		

	
	for (int i=0; i<Numbers.length-1; i++) {
		wordDeck.add(Numbers[i]);
	}
	/*for (int i=0; i<Numbers.length; i++) {
		suits[i]=Numbers[i];
	}*/
//System.out.println(wordDeck.size());
	for(int i=36;i<suits.length;i++)
	{
		wordDeck.add(suits[i]);
	}
	
}

	public static void distributecards()
	{
		loadDeck();

		int playersHand[]=new int[3];
		String playersHandinWords[]=new String[3];
		String []king=new String[4];
		String[]queen=new String[4];
		String []jack=new String[4];
		String[]ace=new String[4];
		int currentDeck[]=new int[52];
		String currentwDeck[]=new String[52];


		
		for (int i=0;i<Deck.size();i++)
		{
			int randomSelection=(int)(Math.random()*52);
			currentDeck[i]=Deck.get(randomSelection);
			currentwDeck[i]=wordDeck.get(randomSelection);

			
		}
		
			System.out.println("Your Hand: ");
		for(int i=1;i<playersHand.length;i++)
		{
			int randomSelection=(int)(Math.random()*52);
			playersHand[0]=0;
			playersHand[i]=currentDeck[randomSelection];
			playersHandinWords[i]=currentwDeck[randomSelection];
			totalPoints+=playersHand[i];
			System.out.println(playersHandinWords[i]+"\t\t Total Points: "+totalPoints);
			//System.out.println(playersHand[i]+"\t\t"+(playersHand[i]+ playersHand[i-1]));
		}
		


		
	}
	public static void simiulateAI()
	{


			int comptersHand[]=new int[4];
			String comptersHandinWords[]=new String[4];
			int currentDeck[]=new int[52];
			String currentwDeck[]=new String[52];

		
		for (int i=0;i<Deck.size();i++)
		{
			int randomSelection=(int)(Math.random()*52);
			currentDeck[i]=Deck.get(randomSelection);
			currentwDeck[i]=wordDeck.get(randomSelection);}System.out.println("\nDealer's Hand: ");
			for(int i=1;i<comptersHand.length;i++)
		{
			int randomSelection=(int)(Math.random()*52);
			comptersHand[0]=0;
			comptersHand[i]=currentDeck[randomSelection];
			comptersHandinWords[i]=currentwDeck[randomSelection];
			dealertotalPoints+=comptersHand[i];
			System.out.println(comptersHandinWords[i]+"\t\t Total Points: "+dealertotalPoints);
		}System.out.println("\tComputer Total Points: "+dealertotalPoints);
	}
	public static void whoWon(int dp,int pp,int bank,int bet)
	{
		if(pp<=21&&dp<=21)
			System.out.println("Unfortunately Dealer Wins");
		else if(pp<=21&&dp>21)
		{	
			System.out.println("Congratulation You Won!!!");
			bank=pp+dp+bet;
		}
		else if(pp==21)
			{
				System.out.println("Congratulation You Won!!!");
				bank=pp+dp+bet;
			}

		else
			System.out.println("Unfortunately Dealer Wins");

	}
}
