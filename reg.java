import java.util.*;
import javax.swing.*;
import java.io.*;

public class reg
{
	//Global Declarations to make everything assessable everywhere
	static Scanner userInput = new Scanner(System.in);
	static ArrayList<ArrayList<String>> loginDetails = new ArrayList<ArrayList<String>>();
	static ArrayList<userData> userDetail1 = new ArrayList<userData>();
	static ArrayList<ArrayList<String>> userDetail = new ArrayList<ArrayList<String>>();
static ArrayList<ArrayList<String>> topicsMenu = new ArrayList<ArrayList<String>>();
	static ArrayList<ArrayList<String>> gameMenu = new ArrayList<ArrayList<String>>();
	static ArrayList<ArrayList<String>> topicMenu = new ArrayList<ArrayList<String>>();
static ArrayList<ArrayList<String>> usersXP = new ArrayList<ArrayList<String>>();

	static ArrayList<ArrayList<String>> questionDetails = new ArrayList<ArrayList<String>>();
	static ArrayList<Integer> ranodmNumbersArray = new ArrayList<Integer>();
	static int xp = 0, overallScore=0, userIndex;
	static boolean questionsExists = false;
    static boolean loggedin = false;
     static  JTextField username = new JTextField(); 
	static String uN="";
	static String xPerience="";
	
	//An assumption which was made was that when the cancel or x button was hit the user wanted to exit
	//There progress up till then is saved
	public static void main(String args[]) throws IOException
	{
		populateLists();
		loadArrayList();
		//boolean loggedIn;
		String selection="";
		acceptLogin();
		 displayTopics();
		 //userXpLog(username.getText(),xPerience);
		// String info="Username:,XP:,";
		// overwriteFile("UsersXP.txt",info);

		// loadXP();

		if(loggedin)
        {	 																																																																																			
           // while(selection != null)
            //{
             //   selection =
              //  overallScore += questionsAndScore(selection);
          //  }
		
            //JOptionPane.showMessageDialog(null, "Your overall score was: "+overallScore, "Overall Score",1);
           System.out.println("updating ....");
            updatexp();
           // leaderboardGeneration();
		}	
	}
    public static void userXpLog(String user,String xp)throws IOException
    {
    	//usersXP.add(new ArrayList<String>());
    	loadXP();
    	

    	String recentXP=(usersXP.get(1).get(userXPPos)).substring(0,(usersXP.get(1).get(userXPPos)).length());
   		if (usersXP.get(0).get(userXPPos).equalsIgnoreCase(user)) {
    			//usersXP.get(userXPPos).set(userXPPos,xp);
    			usersXP.get(1).get(userXPPos).replace(recentXP,xp);
    		System.out.println(usersXP.get(1));}
    	  
    	}	
    		/*if(userExists(user))
    		{
    			for (int i=0; i<userDetail1.size(); i++) 
    				if(userDetail1.get(i).getUser().equals(user))
    					userDetail1.get(i).setXp(xp);
    				System.out.println("xp updated.");
    			
    		}
    }
    public static void sortUsers(ArrayList<userData> a)
	{
		//This method takes an arrayList of type Airport as its arguments.
		//If then uses the bubble sort technique to sort the array list in ascendning order based on the airports name
		userData temp;
		
		for(int i=a.size()-1; i>0; i--)
		{
			for(int j=0; j<i; j++)
			{
				if(userXP.get(i).get().compareToIgnoreCase(userDetail1.get(j).getUser()) < 0)
				{
					temp = userXP.get(i);
					userXP.set(i, usersXP.get(j));
					usersXP.set(j, temp);
				}
			}
		}
	}
    public static boolean userExists(String user)
    {
    	for(int i=0; i<userDetail1.size(); i++)
			if(userDetail1.get(i).getUser().equalsIgnoreCase(user)) return true;
		return false;
    }
    public static boolean xpExists(String xp)
    {
    	for(int i=0; i<userDetail1.size(); i++)
			if(userDetail1.get(i).getXp().equalsIgnoreCase(xp)) return true;
		return false;
    }
   /* public static void leaderboardGeneration()
	/*{	//Score Board generation
		String[] names = convertToArray(loginDetails.get(0));//Array with all the usernames 
		String[] results = convertToArray(loginDetails.get(2)); //Array with all the scores in the same index as the names
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
			board += String.format("Username: %-10s %-5s\n", names[i],results[i]);
		
		JOptionPane.showMessageDialog(null, board, "Leader Board", 1);
	}*/
	
	public static String[] convertToArray(ArrayList<String> listToConvert)
	{
		String[] tempArray = new String[listToConvert.size()];
		
		for(int i=0; i<tempArray.length; i++)
			tempArray[i] = listToConvert.get(i).trim();
		
		return tempArray;
	}
	
   /* public static void updatexp() throws IOException
	{	//Updates the users score in the file for the score board
		saveXP(username.getText(),xPerience);
		loadXP();
		System.out.println(usersXP.get(0));
		File randomFile = new File("UsersXP.txt");
		PrintWriter write = new PrintWriter(randomFile);
		int i=0, previousScore;
		userIndex=usersXP.get(0).indexOf(username.getText());
		System.out.println(userIndex);

		previousScore = Integer.parseInt(usersXP.get(1).get(userIndex));
		overallScore = previousScore+overallScore; //user score for this cycle plus users score for all other previous cylces
		usersXP.get(1).set(userIndex,overallScore+""); //setting their score in the arraylist to their updated score
		while(i < usersXP.get(1).size())
		{
			write.println(usersXP.get(0).get(i)+","+usersXP.get(1).get(i)+","); //overriding the files previous information with updated information
			i++;
		}
		write.close();
	}*/
	
   /* public static int questionsAndScore(String selection)
    {
		//This method displays the questions for each and updates the users score for that round
		int answer, i=0;
		score = 0;
		String answerString, pattern = "[1-4]{1}";
		boolean validInput, completed=false, enoughQuestions=false;
		generateQuestions(selection);
			
			if(selection!=null){ //Check to see if the cancel button was selected
					while(!completed&&!enoughQuestions)
                    {//While there is still quesions to display and the amount of questions displayed has not reached 10
						answerString = JOptionPane.showInputDialog(null, questions[i], selection,1);
						validInput = validateInput(answerString, pattern);
						if(validInput)
                        {
							if(answerString == null) //Check to see if user decides to exit during the current round
								completed= true;
							else{
								answer = Integer.parseInt(answerString);
								if(answer == questionAnswers[i])
                                { //Check to see if the number selected matches the correct number in the array with the correct answers
									score++; i++;
									if(i>=10) enoughQuestions=true; //Ensures that there is only 10 questions displayed per round(if there is 10 questions)
								}
								else
                                {
									JOptionPane.showMessageDialog(null, explanations[i], selection, 1); //If wrong answer is entered then an explanation is displayed
									i++;
									if(i>=10)enoughQuestions=true; //Ensures that there is only 10 questions displayed per round(if there is 10 questions)
								}
							}	
						}
						else
							JOptionPane.showMessageDialog(null, "Invalid Input! Must be a number between 1-4", selection,1); //Id anything besides a number between 1-4 is entered
						if(i >= questions.length) //Check to see if all the questions for that topic have been displayed
							completed= true;
					}
					JOptionPane.showMessageDialog(null, "You Scored "+ score + " in " + selection, selection,1);
			}
			return score;
	}*/
	
	public static void generateQuestions(String selection)
	{   //This method generates the questions, there answers and their explanations, and loads them into seperate arrays
		ranodmNumbersArray.clear(); // used this arrayList to hold the random numbers which have already been generated, to allow for unique numbers each time
		int questionPosition = topicsMenu.get(1).indexOf(selection); 
		int size = numberOfQuestions(questionPosition), aRandomNumber;
		int min = questionDetails.get(0).indexOf(questionPosition+1+""), max = (min+size)-1; //min: the topics position * by the amount of questions MAX: min + the amount of questions there are-1
		String output= "", temp;
		boolean enoughQuestions = false;
	int[]	questionAnswers = new int[size]; //decalring the array which will contain the answers to the questions 
	String[]	questions = new String[size]; //declaring the array which will hold the questions	
	String[]	explanations = new String[size]; //declaring the array which will hold the explanations
		for(int i=0; i<size&&!enoughQuestions;) //loop will keep running until all the questions are cycled through and the amount of questions does not equal 10
		{
			output="";
			aRandomNumber = min + (int)(Math.random() * ((max - min) + 1)); //random number is generated beetween the min and max
			
				if(!ranodmNumbersArray.contains(aRandomNumber))//Checking if the random number was previously generated, this allows for unique questions to be choosen
				{	
					ranodmNumbersArray.add(aRandomNumber); //Fresh random number is added to random number array list
					output+= questionDetails.get(2).get(aRandomNumber)+"\n\n";  //Question is added to a string and the next 4 lines is concatenating the suggested answer onto the question
					output+= "1. "+ questionDetails.get(3).get(aRandomNumber)+"\n";
					output+= "2. "+ questionDetails.get(4).get(aRandomNumber)+"\n";
					output+= "3. "+ questionDetails.get(5).get(aRandomNumber)+"\n";
					output+= "4. "+ questionDetails.get(6).get(aRandomNumber)+"\n";
					questionAnswers[i] = Integer.parseInt(questionDetails.get(7).get(aRandomNumber).trim()); //The answer for this question is added to the array containing the answers at the same index as its question
					explanations[i] = questionDetails.get(8).get(aRandomNumber); //The explanation for this question is added to the array containing the answers at the same index as its question
					questions[i] = output; //The question and suggested answer string is added to the questions array
					i++; //i is only incremented when a unique random number was generated
				}
				if(i>= 10) //Ensures only 10 questions, answers and explanations are loaded.
				enoughQuestions = true;	
		}	
	}
	public static void displaygame()throws IOException
	{ try{
		String selection;
		ArrayList<String> options = new ArrayList<String>(); //Arraylist to hold the topics which have questions attached
		String[] gametypes; //JOptionPane requires an array, arraylist is later transfered into this array
		for(int i=0; i<gameMenu.size(); i++)
        {
			//if the topic has questions then add it to the list of topics
				options.add(gameMenu.get(1).get(i));
		}
		gametypes = convertToArray(options);
		selection = (String) JOptionPane.showInputDialog(null, "Choose a game to play","Game",1, null, gametypes, gametypes[0]);
		if (selection.equals(gametypes[0]))RPSLS();		// sortUsers(usersXP);
		/* System.out.println(userDetail1.get(0));
		 for (int i=0; i<userDetail1.size(); i++) 
		 {
		 	writeToFile("UsersXP.txt",userDetail1.get(i).toString()+"\n");	
		 }*/

		//return selection;
	} catch(NullPointerException e){displayTopics();}
    
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
	
    public static String displayTopics()throws IOException
	{ //Method to display the topics, only topics which have at least one question is generated
		
	try{	String selection;
		ArrayList<String> options = new ArrayList<String>(); //Arraylist to hold the topics which have questions attached
		String[] actualTopics; //JOptionPane requires an array, arraylist is later transfered into this array
		for(int i=0; i<topicsMenu.size(); i++)
        {
			if(questionsExists(topicsMenu.get(1).get(i)))//if the topic has questions then add it to the list of topics
				options.add(topicsMenu.get(1).get(i));
		}
		actualTopics = convertToArray(options);
		selection = (String) JOptionPane.showInputDialog(null, "Choose a task to do","Task Manager",1, null, actualTopics, actualTopics[0]);
		validateInput(selection,"");
		if (selection.equals(actualTopics[0]))displaygame();
		if (selection.equals(actualTopics[1]))displayQuizTopics();
		return selection;
    
	}   catch(NullPointerException e){acceptLogin();}return "";
    
	}
	
     static void acceptLogin() throws IOException
	{	//This method accepts and verifys the login details
		boolean validLogin = false, loginAccepted=false, emptyInput=false;;
		int dialogButton, attempts = 0,option;
		String info, title = "Registration";
		dialogButton =  JOptionPane.showConfirmDialog (null, "Are you an existing user?","Login", JOptionPane.YES_NO_OPTION);
     
         JTextField email = new JTextField(); //A text field is a basic text control that enables the user to type a small amount of text.
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
			Object[] message= {"Email:",email,"Username:", username,"Password:", password,"Confirm Password:", confirmPassword};
              JOptionPane.showMessageDialog(null, message, "Create user",1);
            info = username.getText()+","+password.getText()+","+email.getText()+"\n"; 
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
                loginDetails.get(0).add(username.getText());
                loginDetails.get(1).add(password.getText());
                loginDetails.get(2).add("0");
                userIndex = loginDetails.get(0).indexOf(username.getText());
                validLogin=true;
                loggedin = true;
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
		usernameIndex = loginDetails.get(0).indexOf(username);
		userIndex = usernameIndex;
				
        if(username == null || enteredPassword == null)
            return true;    
        if(usernameIndex != -1)
        {
            actualPassword = loginDetails.get(1).get(usernameIndex);
            if(!actualPassword.equals(enteredPassword))
				return false;
            else 
				return true;
        }
        else
            return false;
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

				}fileReader.close();JOptionPane.showMessageDialog(null,"welcome "+ userDetail.get(0).get(0));
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
        if(questionDetails.get(0).contains(topicIndex))//Checking if that index number is present in column one of the questions file
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

			while(i < questionDetails.get(0).size())
			{
				//uses the topic number which was passed down and looks for matches
				if(questionDetails.get(0).get(i).equals(selection+""))
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
        Object[] message= {"Fist Name:",fisrtname,"Last Name:", lastname,"DOB(dd/mm/yyyy):", birthday," Address:", address};
	int	option = JOptionPane.showConfirmDialog(null, message, "User details", JOptionPane.OK_CANCEL_OPTION);
		//int XP=RPSLS();
		//String xp="XP: "+Integer.toString(XP);
		String fn=fisrtname.getText();
		String ln=lastname.getText();
		String bd=birthday.getText();
		String ad=address.getText();
		if(validDate(bd)==true)
	{
		String[]messages=new String[4];
		messages[0]=fn;
		messages[1]=ln;
		messages[2]=bd;
		messages[3]=ad;
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
	
	
	public static void populateLists() throws IOException
	{
		//Since Accessing files is slow, we decided to only access the file two times, at the start and at the end
		//This method is simply just transfering all the data from the file into Multidimensional ArrayLists

		File loginInfo = new File("loginDetails.txt");
		File topics = new File("task.txt");
		File games = new File("game.txt");
		File questions = new File("Questions.txt");
		File topic = new File("Topics.txt");
		//File userinfo=new File
        Scanner fileReader;
		String[] temp;
		
		loginDetails.add(new ArrayList<String>());
		loginDetails.add(new ArrayList<String>());
		loginDetails.add(new ArrayList<String>());
		
		topicsMenu.add(new ArrayList<String>());
		topicsMenu.add(new ArrayList<String>());

		gameMenu.add(new ArrayList<String>());
		gameMenu.add(new ArrayList<String>());
		topicMenu.add(new ArrayList<String>());
		topicMenu.add(new ArrayList<String>());


		for(int i=0; i<9; i++)
			questionDetails.add(new ArrayList<String>());
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
        if(questions.exists())
		{
			fileReader = new Scanner(questions);
            while(fileReader.hasNext())
			{
				temp = fileReader.nextLine().split(",");
				for(int i=0; i<questionDetails.size(); i++)
					questionDetails.get(i).add(temp[i]);
			}
			fileReader.close();
		}
    }


    public static void loadArrayList() throws IOException
	{
		//This method just loads all the contents of the file into arraylists
		String usersXPFile = "UsersXP.txt";
		
		File file = new File(usersXPFile);
		
		Scanner fileReader;
		
		if(file.exists())
		{
			fileReader = new Scanner(file);
			while(fileReader.hasNext())
				userDetail1.add(userData.addNewuser(fileReader.nextLine().trim()));
			fileReader.close();
		}
		else overwriteFile(usersXPFile, "");
		
		
	}

public static void overwriteFile(String fileName, String lineToWrite) throws IOException
	{
		//This method takes a file name and a line to write to a file
		//it then overwrites all the items in the file with the text passed down
		FileWriter writer = new FileWriter(fileName);
		writer.write(lineToWrite);
		writer.close();
	}
	public static void writeToFile(String fileName, String lineToWrite) throws IOException
	{
		//This method takes a file name and a line to write to a file
		//it then writes all the items in the file with the text passed down
		FileWriter writer = new FileWriter(fileName, true);
		writer.write(lineToWrite);
		writer.close();
	}

    public static void	saveXP(String userName,String xp)throws IOException
    {
    	loadXP();
    	
    	String info=userName+","+xp+","+"\n";
    	writeFile("usersXP.txt",info);
    }
    public static void loadXP()throws IOException
    {
    	String filename="UsersXP.txt";
    	usersXP.add(new ArrayList<String>());
    	usersXP.add(new ArrayList<String>());
		File file=new File(filename);
		String[]temp;
		if (file.exists())
		{
			Scanner fileReader=new Scanner(file);
			while(fileReader.hasNext())
			{
				temp=fileReader.nextLine().split(",");
				for(int i=0;i<2;i++)
				{
					usersXP.get(i).add(temp[i]);
					

				}
			}fileReader.close();
			

		}
		else {
			writeFile(filename,"");
		}
    }
    /////////////////////////////// RPSLS Game //////////////////////
    public static void RPSLS()throws IOException
	{


		String AiChoice="";
		int points=0;
		int life=4;
		int XP=0;
		xPerience="";
		String filename=loginDetails.get(0)+".txt";
        
        String userinput="";
        int randomselector;
        boolean playAgian=true;
        String message="Rock Paper Scissors Lizard Spock(RPSLS), is a game that was first introduced by Big bang theory's Sheldon Cooper, \n As an ellabouration of the classic RPS \n \n Rules: Scissors cuts paper, \n paper covers rock, \n rock smashes lizard, \n lizard poisons spock, \n spock smashes scissors, \n scissors decapacitae Lizard, \n lizard eats paper, \n paper disproves spock, \n spock vaporizes rock, \n ...and as it always have Rock crushes scissors. \n \t\tEnjoy";
        Scanner sc=new Scanner(System.in);
        JOptionPane.showMessageDialog(null,message,"About The Game.",1);
    	 
        System.out.println("User Entered: "+ userinput);
       String  pattern ="rock|paper|scissors|lizard|spock";
       boolean validinput=true;
   try{   
      while(validinput==true&&playAgian==true)
      {AiChoice=AI(AiChoice);
      			userinput=JOptionPane.showInputDialog(null,"Enter either Rock,Paper,Scissors,Lizard or spock.","RPSLS",3);
       			 userinput =userinput.toLowerCase(); 
       			 String result="You picked "+userinput+" AI choose "+AiChoice;
      	 	if(userinput !=null&&userinput=="")
       		{
       			JOptionPane.showMessageDialog(null,"Empty input","Invalid input",0);
       			validinput=true;
       			playAgian=true;
       		}
       		else if (!(userinput.matches(pattern)))
       			{
       				JOptionPane.showMessageDialog(null,"The only acceptable inputs are either Rock,paper,scissors,spock,lizard","Invalid input",0);
       				validinput=true;
       				playAgian=true;
       			}
       		else if (userinput==null)
       		{displaygame();
       			validinput=true;
       			playAgian=false;

       		}
       		else
       		 {
       			if (userinput.equals("rock"))
       			{
       				if (AiChoice.equals(userinput)){JOptionPane.showMessageDialog(null,result+"\n Draw","Result",1);XP=XP+10;points=points+50;}
       				else if (AiChoice.equals("paper")) {JOptionPane.showMessageDialog(null,result+"\n Paper covers Rock \n >> You Lose!!! <<","Result",1);XP=XP+5;points=points+0;}
       				else if (AiChoice.equals("scissors")){ JOptionPane.showMessageDialog(null,result+"\n Roc5crushes Scissors \n >> You Win!!! <<","Result",1);XP=XP+50;points=points+100;}
       				else if (AiChoice.equals("spock")) {JOptionPane.showMessageDialog(null,result+"\n Spock vaporizes Rock \n >> You Lose!!! <<","Result",1);XP=XP+5;points=points+0;}
       				else   {JOptionPane.showMessageDialog(null,result+"\n Rock crushes lizard \n >> You Win!!! <<","Result",1);XP=XP+50;points=points+100;}
       			
       				
       			}
       			else if (userinput.equals("paper"))
       			{
       				if (AiChoice.equals(userinput)){JOptionPane.showMessageDialog(null,result+"\n Draw","Result",1);XP=XP+10;points=points+50;}
       				else if (AiChoice.equals("rock")) {JOptionPane.showMessageDialog(null,result+"\n Paper covers Rock \n >> You Win!!! <<","Result",1);XP=XP+50;points=points+100;}
       				else if (AiChoice.equals("scissors")){ JOptionPane.showMessageDialog(null,result+"\n Scissors  cuts paper \n >> You Lose  !!! <<","Result",1);XP=XP+5;points=points+0;}
       				else if (AiChoice.equals("spock")) {JOptionPane.showMessageDialog(null,result+"\n Paper disproves spock \n >> You Win !!! <<","Result",1);XP=XP+50;points=points+100;}
       				else   {JOptionPane.showMessageDialog(null,result+"\n lizard  eats paper  \n >> You Lose !!! <<","Result",1);XP=XP+5;points=points+0;}
       			
       			
       			}

      	 		else if (userinput.equals("scissors"))
       			{
       				if (AiChoice.equals(userinput)){JOptionPane.showMessageDialog(null,result+"\n Draw","Result",1);XP=XP+10;points=points+50;}
       				else if (AiChoice.equals("paper")) {JOptionPane.showMessageDialog(null,result+"\n Scissors  cuts  paper   \n >> You Win !!! <<","Result",1);XP=XP+50;points=points+100;}
       				else if (AiChoice.equals("rock")) {JOptionPane.showMessageDialog(null,result+"\n Rock crushes Scissors \n >> You Lose!!! <<","Result",1);XP=XP+5;points=points+0;}
       				else if (AiChoice.equals("spock")) {JOptionPane.showMessageDialog(null,result+"\n Spock smashes Scissors  \n >> You Lose !!! <<","Result",1);XP=XP+5;points=points+0;}
       				else   {JOptionPane.showMessageDialog(null,result+"\n Scissors  decapacitae lizard  \n >> You Win !!! <<","Result",1);XP=XP+50;;points=points+100;}
       			
       			
       			}		

       			else if (userinput.equals("spock"))
       			{
       				if (AiChoice.equals(userinput)){JOptionPane.showMessageDialog(null,result+"\n Draw","Result",1);XP=XP+10;;points=points+50;}
       				else if (AiChoice.equals("paper")){ JOptionPane.showMessageDialog(null,result+"\n paper  disproves spock   \n >> You Lose !!! <<","Result",1);XP=XP+5;points=points+0;}
       				else if (AiChoice.equals("scissors")) {JOptionPane.showMessageDialog(null,result+"\n spock Smashes  scissors   \n >> You Win !!! <<","Result",1);XP=XP+50;points=points+100;}
       				else if (AiChoice.equals("rock")) {JOptionPane.showMessageDialog(null,result+"\n Spock vaporizes Rock \n >> You Win!!! <<","Result",1);XP=XP+50;points=points+100;}
       				else   {JOptionPane.showMessageDialog(null,result+"\n lizard  poisons spock  \n >> You Lose !!! <<","Result",1);XP=XP+5;points=points+0;}
       			
       			
       			}

       			else if (userinput.equals("lizard"))
       			{
       				if (AiChoice.equals(userinput)){JOptionPane.showMessageDialog(null,result+"\n Draw","Result",1);XP=XP+10;points=points+50;}
       				else if (AiChoice.equals("paper")){ JOptionPane.showMessageDialog(null,result+"\n Lizard eats paper  \n >> You Win !!! <<","Result",1);XP=XP+50;points=points+100;}
       				else if (AiChoice.equals("scissors")){ JOptionPane.showMessageDialog(null,result+"\n Scissors  decapacitae  lizard  \n >> You Lose !!! <<","Result",1);XP=XP+5;points=points+0;}
       				else if (AiChoice.equals("spock")) {JOptionPane.showMessageDialog(null,result+"\n lizard  poisons spock  \n >> You Win !!! <<","Result",1);XP=XP+50;points=points+100;}
       				else   {JOptionPane.showMessageDialog(null,result+"\n Rock crushes lizard \n >> You Lose!!! <<","Result",1);XP=XP+5;points=points+100;}
       			
       				
       			}
       			JOptionPane.showMessageDialog(null,"Your XP: "+ XP+"\n Your Points Accumelated: "+ points," RPSLS",1);
       			//xp=Integer.toString(XP);
       			validinput=true;
       			playAgian=true;

    			xPerience=Integer.toString(XP);

    			//saveXP(username.getText(),xp);userXpLog(username.getText(),xp);

       		}
       }

   }       catch(NullPointerException e){displaygame();}
	}
	public static boolean vi(String input, String pattern)
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
}

