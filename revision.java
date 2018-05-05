import java.util.*;
import java.io.*;
import javax.swing.*;


public class revision
{
	static ArrayList<ArrayList<String>>userDetail=new ArrayList<ArrayList<String>>();
	public static void main(String[]args)throws IOException
	{
		//int[] ISBN=null;
		/*try{
		
	if(	checkIfISBNIsvalid(ISBN))
		System.out.println("^-^");
	else System.out.println("Invalid ISBN");	
		String input;
		}
		catch (InputMismatchException e)
		{
			System.out.println("Integers Only");
		}
		//Scanner sc=new Scanner(System.in);
*/
		//thirteenDigitISBN(ISBN);
		//readFromfile();

		//String input=JOptionPane.showInputDialog(null,"DOB:");
	RPSLS();
		//if(validDate(input)==true)JOptionPane.showMessageDialog(null,"Good DOB");

	}
	public static boolean checkIfISBNIsvalid(int[] ISBN)
	{
		int []input;
		int []numberToMultiply=new int[11];
		//int []ISBN;
		int numEXCheckDig=0;
		int [] weight=new int[11];
		int sumOfProducts=0;
		int Sumation=0;
		weight[0]=10;
		weight[1]=9;
		weight[2]=8;
		weight[3]=7;
		weight[4]=6;
		weight[5]=5;
		weight[6]=4;
		weight[7]=3;
		weight[8]=2;
		
		////for(int i=10;i>1;i--)
	//	{	weight[i]=i;
		//System.out.print(weight[i]);
	//}
		System.out.println("ENter your 10 digits ISBN Number:");
		ISBN=new int[10];
		Scanner sc=new Scanner(System.in);
		for(int i=0;i<ISBN.length;i++){
		ISBN[i]=sc.nextInt();
		if (ISBN[i]>9){
			System.out.println("Values must be within 0-9");
			checkIfISBNIsvalid(ISBN);
		}
	}
		
	//System.out.println(weight[5]);

	for(int i=0;i<9;i++)
	{
		System.out.println("ISBN:\t"+ISBN[i]+"\\s");
		System.out.println("weight:\t"+weight[i]+"\\s");
		
	 numEXCheckDig=ISBN[i];
	numberToMultiply[i]=ISBN[i]*weight[i];
	System.out.println("Digit x weight:"+numberToMultiply[i]+"\\s");
}
int checkDigit=ISBN[9];
System.out.println("\n"+checkDigit);
for (int i:numberToMultiply)
{
	sumOfProducts+=i;
}

Sumation+=sumOfProducts+checkDigit;
System.out.println(Sumation);

if (Sumation%11==0)
{
	for(int i=0;i<10;i++)
	System.out.print(ISBN[i]);
	System.out.print("  is a valid ISBN Number.");
	return true;
}
else
	return false;

	}
	public static void thirteenDigitISBN(int[] ISBN)
	{
		int []input=new int[13];
		int []numberToMultiply=new int[13];
		//int []ISBN;
		int numEXCheckDig=0;
		int [] weight=new int[11];
		int sumOfProducts=0;
		int Sumation=0;
		int sumation=0;
		weight[0]=10;
		weight[1]=9;
		weight[2]=8;
		weight[3]=7;
		weight[4]=6;
		weight[5]=5;
		weight[6]=4;
		weight[7]=3;
		weight[8]=2;
		
		////for(int i=10;i>1;i--)
	//	{	weight[i]=i;
		//System.out.print(weight[i]);
	//}
		System.out.println("ENter your 13 digits ISBN Number:");
		ISBN=new int[13];
		Scanner sc=new Scanner(System.in);
		for(int i=0;i<ISBN.length;i++){
		ISBN[i]=sc.nextInt();
		if (ISBN[i]>9){
			System.out.println("Values must be within 0-9");
			thirteenDigitISBN(ISBN);
		}
	}
		
	//System.out.println(weight[5]);

	for(int i=0;i<12;i++)
	{
		//System.out.println("ISBN:\t"+ISBN[i]+"\\s");
		//System.out.println("weight:\t"+weight[i]+"\\s");
		
	 numEXCheckDig=ISBN[i];
	//numberToMultiply[i]=ISBN[i]*weight[i];
	
}System.out.println("13th Digit: "+ISBN[12]+"\t");

int checkDigit=ISBN[12];
for(int i=1;i<12;i=i+2)
{
	
	numberToMultiply[i]=ISBN[i]*3;
	System.out.println(numberToMultiply[i]);
}
System.out.println("\n");
for(int i=0;i<12;i=i+2)
	input[i]=ISBN[i];
for(int i:input)
	sumOfProducts+=i;

for (int i:numberToMultiply)
{
	sumOfProducts+=i;
}

Sumation=sumOfProducts%10;
sumation=10-Sumation;

System.out.println("Check digit is: "+sumation);
System.out.print("The complete sequence is: ");
for (int i=0; i<12;i++ ) {
	System.out.print(ISBN[i]);

}System.out.println(sumation);


/*if (Sumation%11==0)
{
	for(int i=0;i<10;i++)
	System.out.print(ISBN[i]);
	System.out.print("  is a valid ISBN Number.");
	return true;
}
else
	return false;*/


	}
	public static boolean validDate(String input)
	{
		String pattern="([a-zA-Z]+)|(([a-zA-Z]*)/)+";
		if(input.contains("/")&&!(input.matches(pattern)))
		{
			String[] dmy=input.split("/");
			int dd=Integer.parseInt(dmy[0]);
			int mm=Integer.parseInt(dmy[1]);
			int yyyy=Integer.parseInt(dmy[2]);
			if(!((dd>30||dd<1)||(mm>12||mm<1)||(yyyy==1890)))
				return true;
			else
				return false;
		}else return false;
	}
	public static void readFromfile()throws IOException
	{
		String filename="Register.txt";
		String []temp;
		userDetail.add(new ArrayList<String>());
			userDetail.add(new ArrayList<String>());
				userDetail.add(new ArrayList<String>());
		File file=new File(filename);
		if (file.exists())
		{
			Scanner fileReader=new Scanner(file);
			while(fileReader.hasNext())
			{
				temp=fileReader.nextLine().split(",");
				for(int i=0;i<userDetail.size();i++)
					userDetail.get(i).add(temp[i]);
			}System.out.println(userDetail.get(2).get(0));
			fileReader.close();
		}
		else System.out.println("oooops");
	}
	public static void RPSLS()
	{
		String AiChoice="";
		int points=0;
		int life=4;
		int XP=0;
        AiChoice=AI(AiChoice);
        String userinput="";
        int randomselector;
        boolean playAgian=true;
        String message="Rock Paper Scissors Lizard Spock(RPSLS), is a game that was first introduced by Big bang theory's Sheldon Cooper, \n As an ellabouration of the classic RPS \n \n Rules: Scissors cuts paper, \n paper covers rock, \n rock smashes lizard, \n lizard poisons spock, \n spock smashes scissors, \n scissors decapacitae Lizard, \n lizard eats paper, \n paper disproves spock, \n spock vaporizes rock, \n ...and as it always have Rock crushes scissors. \n \t\tEnjoy";
        Scanner sc=new Scanner(System.in);
        JOptionPane.showMessageDialog(null,message,"About The Game.",1);
    	 
        System.out.println("User Entered: "+ userinput);
       String  pattern ="rock|paper|scissors|lizard|spock";
       boolean validinput=true;
       String result="You picked "+userinput+" AI choose "+AiChoice;
      while(validinput==true&&playAgian==true){
      			userinput=JOptionPane.showInputDialog(null,"Enter either Rock,Paper,Scissors,Lizard or spock.","RPSLS",3);
       			 userinput =userinput.toLowerCase();
      if (userinput==null)
       	{
       		
       		       	}
       	else if (!(userinput.matches(pattern)))
       			{
       		JOptionPane.showMessageDialog(null,"The only acceptable inputs are either Rock,paper,scissors,spock,lizard","Invalid input",0);
       		validinput=true;
       		playAgian=true;
       	}
       	 else 	if(userinput !=null&&userinput=="")
       	{
       		JOptionPane.showMessageDialog(null,"Empty input","Invalid input",0);
       		validinput=true;
       		playAgian=true;
       	}
       	else {
       		if (userinput.equals("rock"))
       		{
       			if (AiChoice.equals(userinput))JOptionPane.showMessageDialog(null,result+"\n Draw","Result",1);
       			else if (AiChoice.equals("paper")) JOptionPane.showMessageDialog(null,result+"\n Paper covers Rock \n >> You Lose!!! <<","Result",1);
       			else if (AiChoice.equals("scissors")) JOptionPane.showMessageDialog(null,result+"\n Rock crushes Scissors \n >> You Win!!! <<","Result",1);
       			else if (AiChoice.equals("spock")) JOptionPane.showMessageDialog(null,result+"\n Spock vaporizes Rock \n >> You Lose!!! <<","Result",1);
       			else   JOptionPane.showMessageDialog(null,result+"\n Rock crushes lizard \n >> You Win!!! <<","Result",1);
       		
       			
       		}
       		else if (userinput.equals("paper"))
       		{
       			if (AiChoice.equals(userinput))JOptionPane.showMessageDialog(null,result+"\n Draw","Result",1);
       			else if (AiChoice.equals("rock")) JOptionPane.showMessageDialog(null,result+"\n Paper covers Rock \n >> You Win!!! <<","Result",1);
       			else if (AiChoice.equals("scissors")) JOptionPane.showMessageDialog(null,result+"\n Scissors  cuts paper \n >> You Lose  !!! <<","Result",1);
       			else if (AiChoice.equals("spock")) JOptionPane.showMessageDialog(null,result+"\n Paper disproves spock \n >> You Win !!! <<","Result",1);
       			else   JOptionPane.showMessageDialog(null,result+"\n lizard  eats paper  \n >> You Lose !!! <<","Result",1);
       		
       			
       		}

       		else if (userinput.equals("scissors"))
       		{
       			if (AiChoice.equals(userinput))JOptionPane.showMessageDialog(null,result+"\n Draw","Result",1);
       			else if (AiChoice.equals("paper")) JOptionPane.showMessageDialog(null,result+"\n Scissors  cuts  paper   \n >> You Win !!! <<","Result",1);
       			else if (AiChoice.equals("rock")) JOptionPane.showMessageDialog(null,result+"\n Rock crushes Scissors \n >> You Lose!!! <<","Result",1);
       			else if (AiChoice.equals("spock")) JOptionPane.showMessageDialog(null,result+"\n Spock smashes Scissors  \n >> You Lose !!! <<","Result",1);
       			else   JOptionPane.showMessageDialog(null,result+"\n Scissors  decapacitae lizard  \n >> You Win !!! <<","Result",1);
       		
       			
       		}

       		else if (userinput.equals("spock"))
       		{
       			if (AiChoice.equals(userinput))JOptionPane.showMessageDialog(null,result+"\n Draw","Result",1);
       			else if (AiChoice.equals("paper")) JOptionPane.showMessageDialog(null,result+"\n paper  disproves spock   \n >> You Lose !!! <<","Result",1);
       			else if (AiChoice.equals("scissors")) JOptionPane.showMessageDialog(null,result+"\n spock Smashes  scissors   \n >> You Win !!! <<","Result",1);
       			else if (AiChoice.equals("rock")) JOptionPane.showMessageDialog(null,result+"\n Spock vaporizes Rock \n >> You Win!!! <<","Result",1);
       			else   JOptionPane.showMessageDialog(null,result+"\n lizard  poisons spock  \n >> You Lose !!! <<","Result",1);
       		
       			
       		}

       		else if (userinput.equals("lizard"))
       		{
       			if (AiChoice.equals(userinput))JOptionPane.showMessageDialog(null,result+"\n Draw","Result",1);
       			else if (AiChoice.equals("paper")) JOptionPane.showMessageDialog(null,result+"\n Lizard eats paper  \n >> You Win !!! <<","Result",1);
       			else if (AiChoice.equals("scissors")) JOptionPane.showMessageDialog(null,result+"\n Scissors  decapacitae  lizard  \n >> You Lose !!! <<","Result",1);
       			else if (AiChoice.equals("spock")) JOptionPane.showMessageDialog(null,result+"\n lizard  poisons spock  \n >> You Win !!! <<","Result",1);
       			else   JOptionPane.showMessageDialog(null,result+"\n Rock crushes lizard \n >> You Lose!!! <<","Result",1);
       		
       			
       		}
       		validinput=true;
       		playAgian=true;
       	}
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
	 public static String AI(String AiChoice )
    {
       double  randomselector; 
       int points;

 randomselector=Math.random()*1.66;
       if (randomselector<=0.33)
       AiChoice ="rock";
       else if(randomselector>0.33&&randomselector<0.66 )
       AiChoice ="scissors";
       
       else if(randomselector>0.66&&randomselector<1.00)
       AiChoice ="paper";
       
       else if(randomselector>1&&randomselector <1.33)
       AiChoice ="lizard";
       
       else 
       AiChoice ="spock";
       return AiChoice;
       
       
    }
    public static boolean validInput(String input,String pattern){
       boolean goodInput=false;
       while(!goodInput)
       {
        

       if (input !=null&&input==""){goodInput=false; return false;}
       else if (!(input.matches(pattern))){goodInput=false;return false;}
       else {goodInput=true;return true ;}
     }return false;
    } 
    public static void test()
    {
    	String userinput=JOptionPane.showInputDialog(null,"dialog");
    	if (userinput==null)
    	{
    		System.out.println(userinput);
    	}
    }
}

