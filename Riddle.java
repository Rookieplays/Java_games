import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Riddle 
{
	static ArrayList<ArrayList<String>> comments = new ArrayList<ArrayList<String>>();
	static ArrayList<ArrayList<String>> questionDetails = new ArrayList<ArrayList<String>>();
	static ArrayList<Integer> ranodmNumbersArray = new ArrayList<Integer>();
	static String[] questionAnswers;
	static String[] questions;
	static String[] explanations;
	static int score = 0, overallPOints=0, userIndex,health=4,attempt=0;
	static boolean questionsExists = false;
    static boolean loggedin = false;
	
	//An assumption which was made was that when the cancel or x button was hit the user wanted to exit
	//There progress up till then is saved
	public Riddle() 
	{
		int play=0;
	}
	public int playRiddle()throws IOException{
		populateLists();
		System.out.println(comments.get(1));
		System.out.println(questionDetails.get(1));
		//boolean loggedIn;
		String selection="";
		//acceptLogin();
		//if(loggedin)
       // {	 
       	int overallScore=0;
            
                selection = "Riddles";
             int   finalValue = questionsAndScore(selection);
                if (finalValue<0)
                	{overallScore=0;
                	attempt=0;}
                else
                	overallScore=finalValue;
                overallPOints=(overallScore*health)-attempt;
           //}
		JOptionPane.showMessageDialog(null,"Your overall-Score was: "+overallScore+"\n Your health Left was: "+health+" \n Your Overall attempt: "+attempt+"\n("+overallScore+"*"+health+")-"+attempt,"Point Break-Down",1);
         
            JOptionPane.showMessageDialog(null, "Your overall Points was: "+overallPOints, "Overall Score",1);
             return overallPOints;
             //  updateScore();
           // leaderboardGeneration();
		//}	
	}
    
  
	
	public static String[] convertToArray(ArrayList<String> listToConvert)
	{
		String[] tempArray = new String[listToConvert.size()];
		
		for(int i=0; i<tempArray.length; i++)
			tempArray[i] = listToConvert.get(i).trim();
		
		return tempArray;
	}
	
   
	
    public static int questionsAndScore(String selection)
    {
		//This method displays the questions for each and updates the users score for that round
		String answer="";
		int  i=0;
		
		//score = 0;
		String answerString, pattern = "([a-zA-Z]+\\s*)|([0-9])|([/?.,@';>:}<{]*)\\s*|((([a-zA-Z]+[,.;:'/?.,'}@{;<:'>]*\\s*)+)[a-zA-Z]+[/?.,'@;:.}x{<?>]*\\s*)";
		boolean validInput, completed=false, enoughQuestions=false;
		generateQuestions(selection);
		answer=answer.toLowerCase();
		answer=answer.trim();	
			if(selection!=null){ //Check to see if the cancel button was selected
					while(!completed&&!enoughQuestions&&health!=0)
                    {int Rc=(int)(Math.random()*10);int attempts=3;
						int points=0;
						String goodRc=comments.get(0).get(Rc);
						String badRc=comments.get(1).get(Rc);
				                    //While there is still quesions to display and the amount of questions displayed has not reached 10
						JTextArea  msg=new JTextArea(questions[i]);
                                	msg.setLineWrap(true);
                                	msg.setWrapStyleWord(true);
                                	JScrollPane scrollPane=new JScrollPane(msg);
                                	//scrollPane.getVerticalScrollBar();

						answerString = JOptionPane.showInputDialog(null, scrollPane, selection,1);
						answer=answerString;
						validInput = validateInput(answerString, pattern);
						if(validInput)
                        {
							if(answerString == null) //Check to see if user decides to exit during the current round
								completed= true;
							else{
									
									questionAnswers[i]=questionAnswers[i].toLowerCase();
									System.out.println(questionAnswers[i]);
									System.out.println(answer);
								if(answer.contains(questionAnswers[i])&&!(answer.equalsIgnoreCase("Answer")||answer.equalsIgnoreCase("Ans")))
                                { //Check to see if the number selected matches the correct number in the array with the correct answers
									
									score=score+10; i++;points=10;
									JOptionPane.showMessageDialog(null,goodRc+"\nPoints: +"+points);
									if(i>=10) enoughQuestions=true; //Ensures that there is only 10 questions displayed per round(if there is 10 questions)
								}
								else if (answer.equalsIgnoreCase("Answer")||answer.equalsIgnoreCase("Ans")&&health>1)
                          	    {
                          	    	int dialogButton=0;
                          	     dialogButton=JOptionPane.showConfirmDialog(null,"Are You sure you want to check the answer. this will cost you health.","Answer",dialogButton);
									if (dialogButton==JOptionPane.YES_OPTION){health=health-1;JOptionPane.showMessageDialog(null, "The Answer:\n"+explanations[i]+"\n Health: "+health, selection, 1); //If wrong answer is entered then an explanation is displayed
									i++;
									if(i>=10)enoughQuestions=true; }
									//Ensures that there is only 10 questions displayed per round(if there is 10 questions)
								}
							 else if (answer.equalsIgnoreCase("Skip")||answer.equalsIgnoreCase("sk"))i++;
								else if(attempts==0)i++;
								else	{score=score-5;points=-5;attempts--;attempt++;JOptionPane.showMessageDialog(null,badRc+"\nPoints: "+points+"\n attempts left: "+attempts,"Wrong",2);}
							}	
						}
						else
							{JOptionPane.showMessageDialog(null, "Invalid Input! Must be a number between a word/sentence/number", selection,0); attempt++;}//Id anything besides a number between 1-4 is entered
						if(i >= questions.length) //Check to see if all the questions for that topic have been displayed
							completed= true;
					}
					score=(score*100)/100;
					JOptionPane.showMessageDialog(null, "You Scored "+ score + "% in " + selection, selection,1);
			}
			return score;
	}
	
	public static void generateQuestions(String selection)
	{   //This method generates the questions, there answers and their explanations, and loads them into seperate arrays
		ranodmNumbersArray.clear(); // used this arrayList to hold the random numbers which have already been generated, to allow for unique numbers each time
		//int questionPosition = questionDetails.get(0).indexOf(selection); 
	int size = 10, aRandomNumber;
		int min = 1, max = (min+size)-1; //min: the topics position * by the amount of questions MAX: min + the amount of questions there are-1
		String output= "", temp;
		boolean enoughQuestions = false;
		questionAnswers = new String[size]; //decalring the array which will contain the answers to the questions 
		questions = new String[size]; //declaring the array which will hold the questions	
		explanations = new String[size]; //declaring the array which will hold the explanations
		for(int i=0; i<size&&!enoughQuestions;) //loop will keep running until all the questions are cycled through and the amount of questions does not equal 10
		{
			output="";
			aRandomNumber =  (int)(Math.random() * 31); //random number is generated beetween the min and max
			
				if(!ranodmNumbersArray.contains(aRandomNumber))//Checking if the random number was previously generated, this allows for unique questions to be choosen
				{	
					ranodmNumbersArray.add(aRandomNumber); //Fresh random number is added to random number array list
					output+= displayQuestionFormat (questionDetails.get(1).get(aRandomNumber))+"\n\n";  //Question is added to a string and the next 4 lines is concatenating the suggested answer onto the question
					questionAnswers[i] = questionDetails.get(3).get(aRandomNumber).trim(); //The answer for this question is added to the array containing the answers at the same index as its question
					explanations[i] = questionDetails.get(2).get(aRandomNumber); //The explanation for this question is added to the array containing the answers at the same index as its question
					questions[i] = output; //The question and suggested answer string is added to the questions array
					i++; //i is only incremented when a unique random number was generated
				}
				if(i>= 10) //Ensures only 10 questions, answers and explanations are loaded.
				enoughQuestions = true;	
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
	public static String displayQuestionFormat(String text)
	{
		int numberOfSpaces=text.indexOf("b");
		System.out.println(numberOfSpaces);
		if (numberOfSpaces!=0)
		{
			if (numberOfSpaces==18)
			{
				text=text.replace("\\s","p");
			}System.out.println(text);
		}return text;
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
	
	public static void writeToFile(File fileToWriteTo, String info) throws IOException
	{
		//This method is used to add new users to the LoginDetails file without overwitting the contents of the file
		FileWriter write = new FileWriter(fileToWriteTo, true);
		write.write(info);
		write.close();
	}
	
	public static void populateLists() throws IOException
	{
		File questions = new File("riddles.txt");
		File comment = new File("comments.txt");
        Scanner fileReader;
		String[] temp;
		comments.add(new ArrayList<String>());
		comments.add(new ArrayList<String>());

		for(int i=0; i<4; i++)
			questionDetails.add(new ArrayList<String>());
       
        if(questions.exists())
		{
			fileReader = new Scanner(questions);
            while(fileReader.hasNext())
			{
				temp = fileReader.nextLine().split(">");
				for(int i=0; i<questionDetails.size(); i++)
					questionDetails.get(i).add(temp[i]);
				System.out.println(questionDetails.get(1));
			}
			fileReader.close();
		}
		 if(comment.exists())
		{
			fileReader = new Scanner(comment);
            while(fileReader.hasNext())
			{
				temp = fileReader.nextLine().split(",");
				for(int i=0; i<comments.size(); i++)
					comments.get(i).add(temp[i]);
				System.out.println(comments.get(1));
			}
			fileReader.close();
		}
	}
}
