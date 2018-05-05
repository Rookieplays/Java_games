import javax.swing.*;
import java.util.*;
import java.io.*;
public class register
{
    static Scanner userInput = new Scanner(System.in);
	static ArrayList<ArrayList<String>> loginDetails = new ArrayList<ArrayList<String>>();
	static ArrayList<ArrayList<String>> topicsMenu = new ArrayList<ArrayList<String>>();
	static ArrayList<ArrayList<String>> quizzDetails = new ArrayList<ArrayList<String>>();
		static ArrayList<ArrayList<String>> userDetail = new ArrayList<ArrayList<String>>();
		static ArrayList<ArrayList<String>> userDetails = new ArrayList<ArrayList<String>>();
		static ArrayList<ArrayList<String>> gameMenu = new ArrayList<ArrayList<String>>();
static ArrayList<ArrayList<String>> LBdetails = new ArrayList<ArrayList<String>>();

	static ArrayList<ArrayList<String>> topicMenu = new ArrayList<ArrayList<String>>();
	static ArrayList<Integer> ranodmNumbersArray = new ArrayList<Integer>();
	static int[] questionAnswers;
	static String[] questions;
	static String[] explanations;
	static int XP = 0, overallXP=0, overAllPoints=0,coins=0,points=0,userIndex,uNIndex ,uNameIndex;
	static boolean questionsExists = false;
	static  JTextField username = new JTextField(); 
	static  JTextField email = new JTextField();
    static boolean loggedin = false;
	public static void main(String args[]) throws IOException
	{
		Music BGM=new Music();
	String song=BGM.getListOfSongs();
	if(song.contains(","))
	BGM.MakePlayList();//****Playlist needs Fixing not urgent---jh
else
	BGM.playMusic(true,song);

		populateLists();

		boolean loggedIn;
		String selection="";
		String input="";
		acceptLogin();
		if(loggedin)
       {	 //Inventory();
        	//System.out.println(selection);
            while(selection != null)
            {

                selection = displayTopics();
                System.out.println(selection);
               generateQuestions(selection);
             
            	
               // overallXP += RPSLS(selection);System.out.println(overallXP);
            } 
           
            JOptionPane.showMessageDialog(null, "Your overall Points was: "+overAllPoints, "Overall Score",1);
            updateScore(); 
            
            updatePoints();
           updateLeaderBoard();
           
          leaderboardGeneration();
		}	
	}
	public static void generateQuestions(String selection)throws IOException
	{   //This method generates the questions, there answers and their explanations, and loads them into seperate arrays
		 // used this arrayList to hold the random numbers which have already been generated, to allow for unique numbers each time

		int topicPosition = topicsMenu.get(1).indexOf(selection); 
		int in=0;
		int[] cost=new int[6];
		
			cost[0]=0;
			cost[1]=2000;
			cost[2]=2300;
			cost[3]=2500;
			cost[4]=2800;
			cost[5]=3000;



		
		System.out.println(uNIndex);
		String money=userDetails.get(1).get(uNIndex);
		System.out.println(money);
		int mula=Integer.parseInt(money)+coins;
		System.out.println(mula);
		String experience=loginDetails.get(3).get(userIndex);
		int xperience=Integer.parseInt(experience)+XP;
		System.out.println(mula+"\n"+xperience);
		Status itemStatus=new Status();
		int gamePos=1;

		if (topicPosition==0&&itemStatus.getStatus(1,uNIndex)==true)
			{overallXP+= RPSLS(selection);gamePos=1;}
		else if (topicPosition==1&&itemStatus.getStatus(2,uNIndex)==true)//&&mula>=2500&&xperience>=1000
			{overallXP+= StartScramble(selection);gamePos=2;}
		else if(topicPosition==2&&itemStatus.getStatus(3,uNIndex)==true)//mula>=3000&&xperience>=1350)
			{startPictureQuiz();}
		else if(topicPosition==3&&itemStatus.getStatus(4,uNIndex)==true)//mula>=3000&&xperience>=1900)
			{startRiddle();}
			else if(topicPosition==4&&itemStatus.getStatus(4,uNIndex)==true)//mula>=3000&&xperience>=1900)
			{startMusic();}
		else if (selection!=null){JOptionPane.showMessageDialog(null,"You do not have enough XP or Coins to play.\n To play Scramble you need a minimum of 1000XP and 2500 coins.\nTo Play Picture Quiz you need a minimum of 1350XP and 3000 coins.","Entry requirements not met.",2);
			buy Buy=new buy();int price=cost[topicPosition];
			if (Buy.shopDialog(in,mula,price)==true)
			{
				coins=Buy.Transaction(price,mula);System.out.println("succesfull!!!");JOptionPane.showMessageDialog(null,"succes.."); System.out.println(topicPosition+","+uNIndex);
				if(itemStatus.setStatus(topicPosition,uNIndex)==true&&topicPosition==0)
					overallXP+= RPSLS(selection);
				else if(itemStatus.setStatus(topicPosition,uNIndex)==true&&topicPosition==1)
					overallXP+= StartScramble(selection);
				else if(itemStatus.setStatus(topicPosition,uNIndex)==true&&topicPosition==2)
					startPictureQuiz();

				else if(itemStatus.setStatus(topicPosition,uNIndex)==true&&topicPosition==3)

					startRiddle();
				else selection=null;
			}else selection=null;

		}
		else selection=null;
	}
	public static int StartScramble(String selection)throws IOException
	{
		Scramble play=new Scramble();
		play.runGame(selection);
		return play.runGame(selection);
	}
	public static void startPictureQuiz()throws IOException
	{
		pictureQuiz runQuiz=new pictureQuiz();
		runQuiz.runQuiz();
	}
	public static void startRiddle()throws IOException
	{
		Riddle runRiddle=new Riddle();
		overAllPoints+= runRiddle.playRiddle();
		int riddlePoints=overAllPoints;
		overallXP=riddlePoints*2;
		int XP=overallXP;
		coins+=pointsToCoins(riddlePoints,2,XP);
	}
	public static void startMusic()throws IOException
	{		Music BGM=new Music();
	
		BGM.playMusicFromList(true,BGM.displayMusic());
	}
    public static String[] convertToArray(ArrayList<String> listToConvert)
	{
		String[] tempArray = new String[listToConvert.size()];
		
		for(int i=0; i<tempArray.length; i++)
			tempArray[i] = listToConvert.get(i).trim();
		
		return tempArray;
	}

    public static void updateScore() throws IOException
	{	
	System.out.println("Updating users xp...");//Updates the users score in the file for the score board
		File randomFile = new File("loginDetails.txt");
		PrintWriter write = new PrintWriter(randomFile);
		int i=0, previousScore;
		System.out.println("Getting users previous xp..."+userIndex);
		previousScore = Integer.parseInt(loginDetails.get(3).get(userIndex));
		System.out.println("Xp retrived "+previousScore);
		overallXP = previousScore+overallXP; //user score for this cycle plus users score for all other previous cylces
		loginDetails.get(3).set(userIndex,overallXP+""); //setting their score in the arraylist to their updated score
		while(i < loginDetails.get(3).size())
		{
			write.println(loginDetails.get(0).get(i)+","+loginDetails.get(1).get(i)+","+loginDetails.get(2).get(i)+","+loginDetails.get(3).get(i)); //overriding the files previous information with updated information
			i++; 
		}
		write.close();
	}
	 public static void updatePoints() throws IOException
	{	
	System.out.println("Updating users points...");//Updates the users score in the file for the score board
		File randomFile = new File("UsersInventory.txt");
		PrintWriter write = new PrintWriter(randomFile);
		int i=0, previousScore;
		
		System.out.println("Getting users current points..."+uNIndex);
		previousScore = Integer.parseInt(userDetails.get(1).get(uNIndex));
		System.out.println("points retrived "+previousScore);
		coins = previousScore+coins; //user score for this cycle plus users score for all other previous cylces
		userDetails.get(1).set(uNIndex,coins+""); //setting their score in the arraylist to their updated score
		while(i < userDetails.get(1).size())
		{
			System.out.println(userDetails.get(0).get(uNIndex));
			System.out.println(uNIndex);
			write.println(userDetails.get(0).get(i)+","+userDetails.get(1).get(i)); //overriding the files previous information with updated information
			i++;
		}
		write.close();
	}
	public static void updateLeaderBoard() throws IOException
	{	
	System.out.println("Updating users Leaderboard point...");//Updates the users score in the file for the score board
		File randomFile = new File("loginDetails.txt");
		PrintWriter write = new PrintWriter(randomFile);
		int i=0, previousScore;
		System.out.println("Getting users previous point..."+userIndex);
		previousScore = Integer.parseInt(loginDetails.get(4).get(userIndex));
		System.out.println("point retrived "+previousScore);
		overAllPoints = previousScore+overAllPoints; //user score for this cycle plus users score for all other previous cylces
		loginDetails.get(4).set(userIndex,overAllPoints+""); //setting their score in the arraylist to their updated score
		while(i < loginDetails.get(3).size())
		{
			write.println(loginDetails.get(0).get(i)+","+loginDetails.get(1).get(i)+","+loginDetails.get(2).get(i)+","+loginDetails.get(3).get(i)+","+loginDetails.get(4).get(i)); //overriding the files previous information with updated information
			i++; 
		}
		write.close();
	}
	public static void leaderboardGeneration()
	{	//Score Board generation
		String[] names = convertToArray(loginDetails.get(0));//Array with all the usernames 
		String[] results = convertToArray(loginDetails.get(4)); //Array with all the scores in the same index as the names
		String tempScore="", tempName="";
		String board = "Overall Rankings\n";
		for(int i=0; i<results.length-1; i++) //Bubble sort is used to sort the the arrays in decending order
		{
			for(int j=i+1; j<results.length; j++)
			{
				if(Integer.parseInt(results[j]) > Integer.parseInt(results[i]))
				{
					tempScore = results[i];
					tempName = names[i];
					results[i] = results[j];
					names[i] = names[j];
					results[j] = tempScore;
					names[j] = tempName;
				}
			}
		}
		for(int i=0; i<results.length; i++)
			board += String.format("Username: %-10s  Points: %-5s \n", names[i],results[i]);
		
		JOptionPane.showMessageDialog(null, board, "Leader Board", 1);
	}
	
	public static String displayTopics()throws IOException
	{ //Method to display the topics, only topics which have at least one question is generated
		
		String selection;
		ArrayList<String> options = new ArrayList<String>(); //Arraylist to hold the topics which have questions attached
		String[] actualTopics; //JOptionPane requires an array, arraylist is later transfered into this array
		for(int i=0; i<topicsMenu.get(0).size(); i++)
        {
						options.add(topicsMenu.get(1).get(i));
		}
		System.out.println(topicsMenu.get(1));
		actualTopics = convertToArray(options);
		selection = (String) JOptionPane.showInputDialog(null, "Choose a task to do","Task Manager",1, null, actualTopics, actualTopics[0]);
		//validateInput(selection,"");
		//if (selection.equals(actualTopics[0]))displaygame();
		//if (selection.equals(actualTopics[1]))displayQuizTopics();
		return selection;
    
	
	}
     public static String displayQuizTopics()throws IOException
	{ //Method to display the topics, only topics which have at least one question is generated
		String selection;
		ArrayList<String> options = new ArrayList<String>(); //Arraylist to hold the topics which have questions attached
		String[] actualTopics; //JOptionPane requires an array, arraylist is later transfered into this array
		for(int i=0; i<topicMenu.get(0).size(); i++)
        {
			//if(questionsExists(topicMenu.get(1).get(i)))//if the topic has questions then add it to the list of topics
				options.add(topicMenu.get(1).get(i));
		}
		actualTopics = convertToArray(options);
		selection = (String) JOptionPane.showInputDialog(null, "Choose a Topic","Quiz",1, null, actualTopics, actualTopics[0]);
		return selection;
    }
    public static String displaygame()throws IOException
	{ 		String selection;
		ArrayList<String> options = new ArrayList<String>(); //Arraylist to hold the topics which have questions attached
		String[] gametypes; //JOptionPane requires an array, arraylist is later transfered into this array
		for(int i=0; i<gameMenu.size(); i++)
        {
			//if the topic has questions then add it to the list of topics
				options.add(gameMenu.get(1).get(i));
		}
		gametypes = convertToArray(options);
		selection = (String) JOptionPane.showInputDialog(null, "Choose a game to play","Game",1, null, gametypes, gametypes[0]);
			System.out.println("displaygame()..."+ selection);
			return selection;	// sortUsers(UsersInventory);
	}/* System.out.println(userDetail1.get(0));
		 for (int i=0; i<userDetail1.size(); i++) 
		 {
		 	writeToFile("UsersInventory.txt",userDetail1.get(i).toString()+"\n");	
		 }*/

		
	
    


   public  static void acceptLogin() throws IOException
	{	//This method accepts and verifys the login details
		boolean validLogin = false, loginAccepted=false, emptyInput=false;;
		int dialogButton, attempts = 0,option;
		String info, title = "Registration";
		dialogButton =  JOptionPane.showConfirmDialog (null, "Are you an existing user?","Login", JOptionPane.YES_NO_OPTION);
     
        //A text field is a basic text control that enables the user to type a small amount of text.
        JTextField password = new JPasswordField();
        JTextField confirmEmail = new JTextField(); //A text field is a basic text control that enables the user to type a small amount of text.
        JTextField confirmPassword = new JPasswordField();
        
        if(dialogButton == JOptionPane.YES_OPTION)//Executes only if the user is already an existing user
		{
			System.out.println("User selected yes..");
            //Creates an object that accepts both username and password using JTextField
            
            Object[]message = {"Username:", username,"Password:", password};
            String attempted="***";
             System.out.println(attempted);
            //Loop will keep running until three attempts are made or a valid input is entered
			while(attempts<3 && !validLogin && !emptyInput)
			{

				option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
                System.out.println(username.getText()+"\n"+password.getText());

                //if user presses Cancel button the program loops through acceptLogin Method
                if(option == JOptionPane.CANCEL_OPTION)
                {
                    acceptLogin();
                    validLogin = true;
                }

                else 
				{
				    validLogin = validLoginEntered(username.getText(), password.getText());
				    if(!validLogin)
                    {
				        JOptionPane.showMessageDialog(null, "User Could Not Be Found!\nAttempt "+(attempts+1)+" of 3", title, 2);
				        attempts++;	
				        if (attempts==3) 
				        {
				        	System.out.println("Login Failed");
				        	dialogButton= JOptionPane.showConfirmDialog (null, "Do you want to Login Again?","Login Failed", JOptionPane.YES_NO_OPTION);
				        	 if(dialogButton==JOptionPane.YES_OPTION)
				        	 	{acceptLogin();System.out.println("Reseting Login...");
				        	 	validLogin=true;}
				        	 else  {
				        	 	System.out.println("Terminating Login...");
				        	 	JOptionPane.showMessageDialog(null,"Goodbye!!");
				        	 }
				        }
				        attempted=attempted.substring(0,attempted.length()-1);
				        System.out.println(attempted);
				    }
                    else
                    {
                        loggedin = true;
                        uNIndex=userDetails.get(0).indexOf(username.getText());
               System.out.println(uNameIndex);
                                uNameIndex=userDetails.get(1).indexOf(username.getText());
		
                        user(username.getText());
                        getUsername(username.getText());
                    }
				}
            }
        }
        //if user presses NO option then the user is asked to create a new user
        else if(dialogButton == JOptionPane.NO_OPTION)
		{     
            boolean userExists = false;
			File fileReader = new File("loginDetails.txt");
			File reader=new File("UsersInventory.txt");
			File read=new File("Leaderboard.txt");

			Object[] message= {"Email:",email,"Username:", username,"Password:", password,"Confirm Password:", confirmPassword};
              JOptionPane.showMessageDialog(null, message, "Create user",1);
            info = username.getText()+","+password.getText()+","+email.getText()+",0,"+"0"+"\n"; 
           String 	info2=username.getText()+","+"0"+"\n";
           String info3="null"+","+username.getText()+","+"0"+"\n";
            //this is used to check if the user already exists. If user already exists then the user is asked to try logging in 
           if (validpassword(password.getText())==true) {
           	
           
            if(validemail(email.getText())==true)
            {
            if(loginDetails.get(0).contains(username.getText()) || username.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Username Taken or Empty Input, Try Again","Registration Error!",0);
                userExists = true;
                acceptLogin();
            }
            //if user doesn't exist then a new user is created
            if(!userExists&&confirmPassword.getText().matches(password.getText()))
            {
            	System.out.println("New user Added to log...");
                writeToFile(fileReader, info);
                writeUserFile(username.getText()+".txt","");
                userDetails(username.getText()+".txt");
                writeToFile(reader,info2);
                 writeToFile(read,info3);


                loginDetails.get(0).add(username.getText());
                loginDetails.get(1).add(password.getText());
                loginDetails.get(2).add(email.getText());
                loginDetails.get(3).add("0");
                 loginDetails.get(4).add("0");
                userDetails.get(0).add(username.getText());
                userDetails.get(1).add("0");
                 LBdetails.get(0).add("null");
		     LBdetails.get(1).add(username.getText());
             LBdetails.get(2).add("0");
            // System.out.println(loginDetails.get(0)+loginDetails.get(1)+loginDetails.get(2)+loginDetails.get(3)+loginDetails.get(4)+);

                userIndex = loginDetails.get(0).indexOf(username.getText());
               // uNIndex=userDetails.get(0).indexOf(username.getText());
               // System.out.println(uNameIndex);

                validLogin=true;
                loggedin = true;
                JOptionPane.showMessageDialog(null,"welcome "+ username.getText());
	
            }else{JOptionPane.showMessageDialog(null,"Password does not match, Try again","Password Error",0);acceptLogin();}
        	}else 
        	{
        		
        		 acceptLogin();
        	}
        }else
        {

        		 acceptLogin();
        }
 
        }
        //if the login is not succesfull then the program exists 
	}
	
	public static boolean validemail(String mail )//String userName)
    {
       
        
        mail=mail.replaceAll(" ","");
        String pattern="[a-zA-Z0-9]+";
        String[]eName;
        String[]address;
        String mail2=mail;
        //userDetails.add(new ArrayList<String>());
        boolean emailMatched=true;
        eName=mail.split("@");
        address=mail.split("\\.");
        System.out.println("Analyzing "+mail+"....");
        System.out.println("checking email for @...");
        if (mail.contains("@"))

        {
            System.out.println("checking if email is valid ..");
            if(eName[0].matches(pattern)&&eName[0].length()>=4)
            {
                System.out.println("Analyzing Extention.");
                System.out.println(eName.length);
                if(address[1].equalsIgnoreCase("com")||address[1].equalsIgnoreCase("ie")||address[1].equalsIgnoreCase("net")||address[1].equalsIgnoreCase("Msn"))
                {
                    
                  System.out.println("This is a valid email. "+mail);
                            return true;
			    
                   
                   // return true;
                }else {JOptionPane.showMessageDialog(null,"Please use .com/.ie/.net/.msn","Incorrect Extention",0);return false;}
            }else {JOptionPane.showMessageDialog(null, eName[0]+" is not a valid email name.","Incorrect Email",0);return false;}
        } else {JOptionPane.showMessageDialog(null,"Email is Incorrect","Bad Email",0);return false;}
           
    }
    public static boolean validLoginEntered(String username, String enteredPassword)
	{
		//Method is used to check if the username and password entered have previously been registered
		int usernameIndex;
		String actualPassword;
		System.out.println("User Entered: "+username+"\n"+enteredPassword);
		usernameIndex = loginDetails.get(0).indexOf(username);
		userIndex = usernameIndex;
				
        if(username == null || enteredPassword == null)
            return true;    
        if(usernameIndex != -1)
        {
        	System.out.println("getting position of username....\ngetting password");
            actualPassword = loginDetails.get(1).get(usernameIndex);
            System.out.println("Analyzing password...");
            if(!actualPassword.equals(enteredPassword))
				return false;
            else 
				return true;
        }
        else
            return false;
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
	public static int RPSLS( String userinput)throws IOException
	{
		System.out.println("Running RPSLS...");
		int answer, i=0;
		XP = 0;
		boolean validInput, completed=false, enoughQuestions=false;
	

		String AiChoice="";
		 points=1;
		int attempts=2,life=5;
		
		
		String filename=loginDetails.get(0)+".txt";
        
       
        int randomselector;
        boolean playAgian=true;
        String message="Rock Paper Scissors Lizard Spock(RPSLS), is a game that was first introduced by Big bang theory's Sheldon Cooper, \n As an ellabouration of the classic RPS \n \n Rules: Scissors cuts paper, \n paper covers rock, \n rock smashes lizard, \n lizard poisons spock, \n spock smashes scissors, \n scissors decapacitae Lizard, \n lizard eats paper, \n paper disproves spock, \n spock vaporizes rock, \n ...and as it always have Rock crushes scissors. \nYou start with 5 HP(Health Points) if you lose you lose 1HP if You win you gain 1HP\n \t\tEnjoy";
       String extMsg="Either your are Attempting to quit or you entered the wrong thing.\n If you want to quit do that again, Otherwise.Enter the right thing or else..";
        Scanner sc=new Scanner(System.in);
        
        
       String  pattern ="rock|paper|scissors|lizard|spock|exit|x|help|h|lb";
       
  
     
    
      			
       			
      	if(userinput!=null){
       						
       							while(!completed&&!enoughQuestions)
                  			  {	  AiChoice=AI(AiChoice);	
                  			  JOptionPane.showMessageDialog(null,"Your current  XP: "+ XP+"\n Your current points: "+ points," RPSLS",1);
       			userinput=JOptionPane.showInputDialog(null," Type help or h to see the rules\nType exit or x to return to the previous screen.\n Type lb to view the Leaderboard \n\n Enter either Rock,Paper,Scissors,Lizard or spock.\n","RPSLS",3);
       				System.out.println("User Entered: "+ userinput);	 		
       								 validInput = validateInput(userinput, pattern);
       								 System.out.println(validInput);
       					if(validInput)
       						{	
       							if(userinput == null) //Check to see if user decides to exit during the current round
								{completed= true;userinput="";}
String result="You picked "+userinput+" AI choose "+AiChoice;	 userinput =userinput.toLowerCase(); 
       							if (userinput.equals("rock"))
       							{
       								if (AiChoice.equals(userinput)){JOptionPane.showMessageDialog(null,result+"\n Draw\n HP: "+ life,"Result",1);XP=XP+10;points=points+50;}
       								else if (AiChoice.equals("paper")) {life=life-1;JOptionPane.showMessageDialog(null,result+"\n Paper covers Rock \n >> You Lose!!! <<\n HP: "+life,"Result",1);XP=XP+5;points=points+0;}
       								else if (AiChoice.equals("scissors")){ life=life+1;JOptionPane.showMessageDialog(null,result+"\n Roc5crushes Scissors \n >> You Win!!! <<\nHP: "+life,"Result",1);XP=XP+50;points=points+100;}
       								else if (AiChoice.equals("spock")) {life=life-1;JOptionPane.showMessageDialog(null,result+"\n Spock vaporizes Rock \n >> You Lose!!! <<\nHP: "+life,"Result",1);XP=XP+5;points=points+0;}
       								else   {life=life+1;JOptionPane.showMessageDialog(null,result+"\n Rock crushes lizard \n >> You Win!!! \n<< HP: "+life,"Result",1);XP=XP+50;points=points+100;}
       			
				       				
				       			}
       							else if (userinput.equals("paper"))
       							{
       							if (AiChoice.equals(userinput)){JOptionPane.showMessageDialog(null,result+"\n Draw\n HP: "+life,"Result",1);XP=XP+10;points=points+50;}
       							else if (AiChoice.equals("rock")) {life=life+1;JOptionPane.showMessageDialog(null,result+"\n Paper covers Rock \n >> You Win!!! <<\n HP: "+life,"Result",1);XP=XP+50;points=points+100;}
       							else if (AiChoice.equals("scissors")){ life=life-1;JOptionPane.showMessageDialog(null,result+"\n Scissors  cuts paper \n >> You Lose  !!! <<\nHP: "+life,"Result",1);XP=XP+5;points=points+0;}
       							else if (AiChoice.equals("spock")) {life=life+1;JOptionPane.showMessageDialog(null,result+"\n Paper disproves spock \n >> You Win !!! <<\nHP: "+life,"Result",1);XP=XP+50;points=points+100;}
       							else   {life=life-1;JOptionPane.showMessageDialog(null,result+"\n lizard  eats paper  \n >> You Lose !!! <<\nHP: "+life,"Result",1);XP=XP+5;points=points+0;}
       			
       			
  		  			   			}

    				  	 		else if (userinput.equals("scissors"))
      				 			{
      			 				if (AiChoice.equals(userinput)){JOptionPane.showMessageDialog(null,result+"\n Draw\nHP: "+life,"Result",1);XP=XP+10;points=points+50;}
       							else if (AiChoice.equals("paper")) {life=life+1;JOptionPane.showMessageDialog(null,result+"\n Scissors  cuts  paper   \n >> You Win !!! <<\nHP: "+life,"Result",1);XP=XP+50;points=points+100;}
       							else if (AiChoice.equals("rock")) {life=life-1;JOptionPane.showMessageDialog(null,result+"\n Rock crushes Scissors \n >> You Lose!!! <<\nHP: "+life,"Result",1);XP=XP+5;points=points+0;}
       							else if (AiChoice.equals("spock")) {life=life-1;JOptionPane.showMessageDialog(null,result+"\n Spock smashes Scissors  \n >> You Lose !!! <<\nHP: "+life,"Result",1);XP=XP+5;points=points+0;}
       							else   {life=life+1;JOptionPane.showMessageDialog(null,result+"\n Scissors  decapacitae lizard  \n >> You Win !!! <<\nHP: "+life,"Result",1);XP=XP+50;;points=points+100;}
       			
       			
       							}		

       							else if (userinput.equals("spock"))
       							{
       							if (AiChoice.equals(userinput)){JOptionPane.showMessageDialog(null,result+"\n Draw\n HP: "+life,"Result",1);XP=XP+10;;points=points+50;}
       							else if (AiChoice.equals("paper")){ life=life-1;JOptionPane.showMessageDialog(null,result+"\n paper  disproves spock   \n >> You Lose !!! <<\n HP: "+life,"Result",1);XP=XP+5;points=points+0;}
       							else if (AiChoice.equals("scissors")) {life=life+1;JOptionPane.showMessageDialog(null,result+"\n spock Smashes  scissors   \n >> You Win !!! <<\n HP: "+life,"Result",1);XP=XP+50;points=points+100;}
       							else if (AiChoice.equals("rock")) {life=life+1;JOptionPane.showMessageDialog(null,result+"\n Spock vaporizes Rock \n >> You Win!!! <<\nHP: "+life,"Result",1);XP=XP+50;points=points+100;}
       							else   {life=life-1;JOptionPane.showMessageDialog(null,result+"\n lizard  poisons spock  \n >> You Lose !!! <<\n HP: "+life,"Result",1);XP=XP+5;points=points+0;}
       			
       			
       							}

       							else if (userinput.equals("lizard"))
       							{
       							if (AiChoice.equals(userinput)){JOptionPane.showMessageDialog(null,result+"\n Draw\n HP: "+life,"Result",1);XP=XP+10;points=points+50;}
       							else if (AiChoice.equals("paper")){life=life+1; JOptionPane.showMessageDialog(null,result+"\n Lizard eats paper  \n >> You Win !!! <<\n HP: "+life,"Result",1);XP=XP+50;points=points+100;}
       							else if (AiChoice.equals("scissors")){life=life-1; JOptionPane.showMessageDialog(null,result+"\n Scissors  decapacitae  lizard  \n >> You Lose !!! <<\n HP: "+life,"Result",1);XP=XP+5;points=points+0;}
       							else if (AiChoice.equals("spock")) {life=life+1;JOptionPane.showMessageDialog(null,result+"\n lizard  poisons spock  \n >> You Win !!! <<\n HP: "+life,"Result",1);XP=XP+50;points=points+100;}
       							else   {life=life-1;JOptionPane.showMessageDialog(null,result+"\n Rock crushes lizard \n >> You Lose!!! <<\n HP: "+life,"Result",1);XP=XP+5;points=points+100;}
       			
       				
       							}
       							else if(userinput.equals("help")||userinput.equals("h")){
       								 JOptionPane.showMessageDialog(null,message,"About The Game.",1);
    	
       							}
       							else if(userinput.equals("x")||userinput.equals("exit"))
       							{
       								displaygame();
       							}
       							else if(userinput.equals("lb"))
       								leaderboardGeneration();
       						}
       						else 
       							{JOptionPane.showMessageDialog(null,"The only acceptable inputs are either Rock,paper,scissors,spock,lizard \n"+extMsg,"Quit?",0);attempts=attempts-1;}
       						if (life==0||attempts==0)
       							completed=true;

       						  
       				}
       			JOptionPane.showMessageDialog(null,"Your Total XP: "+ XP+"\n Your Points Accumelated: "+ points," RPSLS",1);
       			overAllPoints+=points;
       			coins+=pointsToCoins(points,life,XP);
			 }
			 return XP;

   
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
    public static void populateLists() throws IOException
	{
		//Since Accessing files is slow, we decided to only access the file two times, at the start and at the end
		//This method is simply just transfering all the data from the file into Multidimensional ArrayLists

		File loginInfo = new File("loginDetails.txt");
		File topics = new File("task.txt");
		File games = new File("game.txt");
		File questions = new File("Quiz.txt");
		File topic = new File("Topics.txt");
		File userInv=new File("UsersInventory.txt");
		File leaderBoardinfo=new File("Leaderboard.txt");

		//File userinfo=new File
        Scanner fileReader;
		String[] temp;
		
		loginDetails.add(new ArrayList<String>());
		loginDetails.add(new ArrayList<String>());
		loginDetails.add(new ArrayList<String>());
		loginDetails.add(new ArrayList<String>());
		loginDetails.add(new ArrayList<String>());

		
		
		topicsMenu.add(new ArrayList<String>());
		topicsMenu.add(new ArrayList<String>());

		gameMenu.add(new ArrayList<String>());
		gameMenu.add(new ArrayList<String>());
		topicMenu.add(new ArrayList<String>());
		topicMenu.add(new ArrayList<String>());

		userDetails.add(new ArrayList<String>());
		userDetails.add(new ArrayList<String>());
	

		LBdetails.add(new ArrayList<String>());
		LBdetails.add(new ArrayList<String>());
		LBdetails.add(new ArrayList<String>());

		for(int i=0; i<9; i++)
			quizzDetails.add(new ArrayList<String>());
        if(loginInfo.exists())
		{
			fileReader = new Scanner(loginInfo);
			
			while(fileReader.hasNext())
			{
				temp = fileReader.nextLine().split(",");
				for(int i=0; i<loginDetails.size(); i++)
					loginDetails.get(i).add(temp[i]);
			}
			fileReader.close();
		}writeToFile(loginInfo,"");
		 
        if(topics.exists())
		{
			fileReader = new Scanner(topics);

			while(fileReader.hasNext())
			{
				temp = fileReader.nextLine().split(",");
				for(int i=0; i<topicsMenu.size(); i++)
					topicsMenu.get(i).add(temp[i]);
			}
			fileReader.close();
		}
		  if(topic.exists())
		{
			fileReader = new Scanner(topic);

			while(fileReader.hasNext())
			{
				temp = fileReader.nextLine().split(",");
				for(int i=0; i<topicMenu.size(); i++)
					topicMenu.get(i).add(temp[i]);
			}
			fileReader.close();
		}
		if(games.exists())
		{
			fileReader = new Scanner(games);

			while(fileReader.hasNext())
			{
				temp = fileReader.nextLine().split(",");
				for(int i=0; i<gameMenu.size(); i++)
					gameMenu.get(i).add(temp[i]);
			}
			fileReader.close();
		}
		if(leaderBoardinfo.exists())
		{
			fileReader = new Scanner(leaderBoardinfo);

			while(fileReader.hasNext())
			{
				temp = fileReader.nextLine().split(",");
				for(int i=0; i<LBdetails.size(); i++)
					LBdetails.get(i).add(temp[i]);
			}
			fileReader.close();
		}else writeFile("Leaderboard.txt","");
        if(questions.exists())
		{
			fileReader = new Scanner(questions);
            while(fileReader.hasNext())
			{
				temp = fileReader.nextLine().split(",");
				for(int i=0; i<quizzDetails.size(); i++)
					quizzDetails.get(i).add(temp[i]);
			}
			fileReader.close();
		}
		if(userInv.exists())
		{
			fileReader = new Scanner(userInv);
            while(fileReader.hasNext())
			{
				temp = fileReader.nextLine().split(",");
				for(int i=0; i<userDetails.size(); i++)
					userDetails.get(i).add(temp[i]);
			}
			fileReader.close();
		}

    }

    public static void user(String username)throws IOException
	{
		String filename=username+".txt";
		File file=new File(filename);
		String[]temp;
	userDetail.add(new ArrayList<String>());
		if (file.exists())
		{
			Scanner fileReader=new Scanner(file);
			while(fileReader.hasNext())
				{
					temp=fileReader.nextLine().split(",");
					for(int i=0; i<userDetail.size(); i++)
					userDetail.get(i).add(temp[i]);

				}fileReader.close();
				System.out.println(userDetail.get(0).get(0) + " Has Logged on..");		JOptionPane.showMessageDialog(null,"welcome "+ userDetail.get(0).get(0));
		}
	}
 
 	public static String getUsername(String user)
	{
		System.out.println(user);
		return user;
		
	}
	public static boolean validpassword(String password)
    {
       
        String pattern="([a-zA-Z0-9]+[,./?;'#~@u20AC$]*)";
       // String uppercase="([ABCDEFGHIJKLMNOPQRSTUVWXYZ]+)";
       // String lowerCase="([abcdefghijklmnopqrstuvwxyz]+)";
       // String BasetenDigit="[0123456789]";
       // String nonAlphanumeric="[,./?;'#~@u20AC]";
      

       
        password=password.trim();
        password=password.replaceAll(" ","");
       // userDetails.add(new ArrayList<String>());
        String password2="";
        String passwordx="";
        String blockedpassword;
        String passwordy;
        int T=0,F=0;
        for(int i=0;i<password.length();i++)
        {
            //blockedpassword=password.charAt(i)+"";
            //passwordx=passwordx.replace(blockedpassword,"*");
             System.out.print("*");


        } System.out.println("\n"+password);
        if (password.length()>=8) {
          
            if (password.matches(".*[A-Z]+.*")&&password.matches(".*[a-z]+.*")&&password.matches(".*[0-9].*")||password.matches(".*[,./?;'#~@u20AC].*"))
            { 
                            System.out.println("Good Password");
               

               return true;
        }else {System.out.println("Password Failed");JOptionPane.showMessageDialog(null,"The password must reach the following criteria:\n\nMust be 8 digits Long. \n Must have at Least 1 or more uppercase characters.(A-Z)\n 1 or more lowerCase characters.(a-z)\n Base 10 digits(0-9)\n and should have one non-alphanumeric(.,$)","Invalid password",0);return false;}

            
        }else{JOptionPane.showMessageDialog(null,"Weak Password you need more than just "+password.length()+" characters","weak password",0);return false;}

    }
	
	public static boolean questionsExists(String topic)
	{
		String topicIndex = (topicsMenu.get(1).indexOf(topic)+1)+""; //The index of the topic in the topics file +1
        if(quizzDetails.get(0).contains(topicIndex))//Checking if that index number is present in column one of the questions file
			return true;
		else 
			return false;

	}
	public static int numberOfQuestions(int selection)
	{
			//This Method was used to determine if questions for a cetain topic exited and if so how many
			selection+=1;
			int i=0, size=0;			
			questionsExists = false;

			while(i < quizzDetails.get(0).size())
			{
				//uses the topic number which was passed down and looks for matches
				if(quizzDetails.get(0).get(i).equals(selection+""))
                {
					size++; 
					questionsExists = true;
				}
				i++;
			}
			return size;
	}
	public static void userDetails(String filename)throws IOException
	{
		userDetail.add(new ArrayList<String>());
		
		JTextField fisrtname = new JTextField(); 
         JTextField lastname = new JTextField(); //A text field is a basic text control that enables the user to type a small amount of text.
        JTextField  birthday = new JTextField();
        JTextField address = new JTextField(); //A text field is a basic text control that enables the user to type a small amount of text.
        JTextField gender = new JTextField();
        Object[] message= {"First Name:",fisrtname,"Last Name:", lastname,"DOB(dd/mm/yyyy):", birthday," Address:", address};
	int	option = JOptionPane.showConfirmDialog(null, message, "User details", JOptionPane.OK_CANCEL_OPTION);
		//int XP=RPSLS();
		//String xp="XP: "+Integer.toString(XP);
		String fn=fisrtname.getText();
		String ln=lastname.getText();
		String bd=birthday.getText();
		String ad=address.getText();
		if(validDate(bd)==true)
	{
		String[]messages=new String[5];
		messages[0]=fn;
		messages[1]=ln;
		messages[2]=bd;
		messages[3]=ad;
		messages[4]="0";
	//	messages[4]=xp;
		//messages[0]=fn;
		for(int i=0;i<messages.length;i++)
		{
			//String input=message[i];
			String info=messages[i]+","+"\n";
		writeUserFile(filename,info);
		}
	}else {JOptionPane.showMessageDialog(null,"Invalid DOB, Format:dd/mm/yyyy (yyyy > 1890","DOB Error",0);userDetails("");}
}	
public static void Inventory()throws IOException
{
	int coins=overAllPoints/2;
	String user=username.getText();
	String info=user+","+coins+"\n";
	writeFile("UsersInventory.txt",info);
	System.out.println(info+" to UsersInventory.txt");
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
	public static int pointsToCoins(int ppoints,int Life,int xp)
	{
		coins=((overAllPoints*Life)/ppoints)+xp;
		return coins;
	}
	public static void writeToFile(File fileToWriteTo, String info) throws IOException
	{
		//This method is used to add new users to the LoginDetails file without overwitting the contents of the file
		FileWriter write = new FileWriter(fileToWriteTo, true);
		write.write(info);
		write.close();
	}
	public static void writeUserFile(String fileToWriteTo, String info) throws IOException
	{
		//This method is used to add new users to the LoginDetails file without overwitting the contents of the file
		FileWriter write = new FileWriter(fileToWriteTo, true);
		write.write(info);
		write.close();
	}
		public static void writeFile(String fileToWriteTo, String info) throws IOException
	{
		//This method is used to add new users to the LoginDetails file without overwitting the contents of the file
		FileWriter write = new FileWriter(fileToWriteTo, true);
		write.write(info);
		write.close();
	}

}

/**************Fix Leaderbord

		displaygame bug
		UsersInventory prominent  issue---Solved
		new game*************************/

