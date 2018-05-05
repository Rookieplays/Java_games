import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class test implements   ItemListener

{
     static ArrayList<ArrayList<String>> stringNames = new ArrayList<ArrayList<String>>();
JPanel cards;
        static ArrayList<ArrayList<String>> imageDetails = new ArrayList<ArrayList<String>>();
    static ArrayList<ArrayList<String>> questionDetails = new ArrayList<ArrayList<String>>();
    static ArrayList<Integer> ranodmNumbersArray = new ArrayList<Integer>();
    static int[] questionAnswers;
    final static String BUTTONPANEL="QUIZ";
    final static String TESTPANEL="yup";
    static String[] questions;
    static String[] explanations;
    static String Imgpath;
    static String answerEx="";
    static String answerForQuestion="";
    static int score = 0, overallScore=0, questionCounter;
    static boolean questionsExists = false;
    static boolean loggedin = false;
 private  JRadioButton[]btns=new JRadioButton[4];

JLabel picture;
JLabel qs=new JLabel("my Label");
//JLabel answerEx=new JLabel("my Label");
public void addComponentsToPane(Container pane)
{
    // super(new BorderLayout());
    int selectionint=(int)(Math.random()*8);
    String[]selection=new String[imageDetails.get(0).size()];
  for (int i=0; i<selection.length; i++) {
        selection[i]=imageDetails.get(1).get(selectionint);
    }
   String selectionStr=selection[selectionint];
     System.out.println(imageDetails.get(1).get(imageDetails.get(0).size()-1));
    ranodmNumbersArray.clear(); // used this arrayList to hold the random numbers which have already been generated, to allow for unique numbers each time
        int questionPosition = imageDetails.get(1).indexOf(selectionStr); 
        int size = numberOfQuestions(questionPosition), aRandomNumber;
        int min = questionDetails.get(0).indexOf(questionPosition+1+""), max = (min+size)-1; //min: the topics position * by the amount of questions MAX: min + the amount of questions there are-1
        String temp;
        boolean enoughQuestions = false;
        questionAnswers = new int[size]; //decalring the array which will contain the answers to the questions 
        questions = new String[size]; //declaring the array which will hold the questions   
        explanations = new String[size]; //declaring the array which will hold the explanations
        for(int i=0; i<size&&!enoughQuestions;) //loop will keep running until all the questions are cycled through and the amount of questions does not equal 10
        {
            Imgpath="";
            aRandomNumber = min + (int)(Math.random() * ((max - min) + 1)); //random number is generated beetween the min and max
            
                if(!ranodmNumbersArray.contains(aRandomNumber))//Checking if the random number was previously generated, this allows for unique questions to be choosen
                {   
                    ranodmNumbersArray.add(aRandomNumber); //Fresh random number is added to random number array list
                   Imgpath=imageDetails.get(1).get(aRandomNumber);
                    qs.setText(questionDetails.get(1).get(aRandomNumber)); 
                   
                   //Question is added to a string and the next 4 lines is concatenating the suggested answer onto the question
                    btns[0]= new JRadioButton (questionDetails.get(2).get(aRandomNumber));
                    btns[1]= new JRadioButton (questionDetails.get(3).get(aRandomNumber));
                    btns[2]= new JRadioButton (questionDetails.get(4).get(aRandomNumber));
                    btns[3]= new JRadioButton (questionDetails.get(5).get(aRandomNumber));
                    questionAnswers[i] = Integer.parseInt(questionDetails.get(6).get(aRandomNumber).trim()); //The answer for this question is added to the array containing the answers at the same index as its question
                    answerEx=questionDetails.get(7).get(aRandomNumber); //The explanation for this question is added to the array containing the answers at the same index as its question
                    //questions[i] = output; //The question and suggested answer string is added to the questions array
                    i++; //i is only incremented when a unique random number was generated
                }
                if(i>= 10) //Ensures only 10 questions, answers and explanations are loaded.
                enoughQuestions = true; 
        }   
    JPanel comboBoxPane=new JPanel();
    String comboBoxItems[]={BUTTONPANEL,TESTPANEL};
    JComboBox cb=new JComboBox(comboBoxItems);
    cb.setEditable(false);
    cb.addItemListener(this);
    comboBoxPane.add(cb);
    String stringnames[]; 
    stringnames=convertToArray(stringNames.get(0));
    ButtonGroup group=new ButtonGroup();
   for(int i=0;i<btns.length;i++)
   {
       // btns[i].setActionCommand(stringnames[i]);
        group.add(btns[i]);
       // btns[i].addActionListener(this);
   }
    picture=new JLabel(createImageIcon(Imgpath));
    picture.setPreferredSize(new Dimension(177,122));
    System.out.println(Imgpath);
    JPanel radioPanel=new JPanel(new GridLayout(0,1));
    for (int i=0;i<btns.length;i++)
    radioPanel.add(btns[i]); 
JPanel[] qi=new JPanel[9];
for (int i=0;i<qi.length;i++)
{
   qi[i]=new JPanel();
    qi[i].add(radioPanel, BorderLayout.LINE_END);
   qi[i].add(qs,BorderLayout.LINE_START);
     qi[i].add(picture, BorderLayout.CENTER);
        //setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
}
cards=new JPanel(new CardLayout());
for(int i=0;i<qi.length;i++)
cards.add(qi[i]);

pane.add(comboBoxPane,BorderLayout.PAGE_START);
pane.add(cards,BorderLayout.CENTER);


}
 /*public static int questionsAndScore(String selection)
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
    
public void itemsStateChanged(ItemEvent evt)
{
    CardLayout cl=(CardLayout)(cards.getLayout());
    cl.show(cards,(String)evt.getItem());
}

public void actionPerformed(ActionEvent e) {
        picture.setIcon(createImageIcon(Imgpath));
        System.out.println("iconDisplay "+Imgpath);
        System.out.println(e.getActionCommand());
        answerForQuestion=e.getActionCommand();
        if (answerForQuestion.equals(answerEx))
        {
            JOptionPane.showMessageDialog(null,"CORRECT!!");
            questionCounter++;score++;
          
        }
        else
           { 
                JOptionPane.showMessageDialog(null,"INCORRECT","Nope",2); 
                System.out.println("INCORRECT!!!");
           }     //get Answer
        //go to next question 
       // in next quesion if there is none left go to display result
    }

    public static String[] convertToArray(ArrayList<String> listToConvert)
    {
        String[] tempArray = new String[listToConvert.size()];
        
        for(int i=0; i<tempArray.length; i++)
            tempArray[i] = listToConvert.get(i).trim();
        
        return tempArray;
    }
 protected static ImageIcon createImageIcon(String png) {
       String imgPath =(Imgpath);
       System.out.println(imgPath);
        if (imgPath != null&&!(imgPath.equals(""))) {
            return new ImageIcon(imgPath);
        } else {
            System.err.println("Couldn't find file: " + png);
            return null;
        }
    }private static void createAndShowGUI() {
        //Create and set up the window.
       // JOptionPane.showMessageDialog(null,"What is this?");
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
test load=new test();
load.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }



public static void main (String[]args)throws IOException
{
    try{UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    }catch(UnsupportedLookAndFeelException ex)
    {
        ex.printStackTrace();
    }  catch(IllegalAccessException ex)
    {
        ex.printStackTrace();
    } catch(InstantiationException ex)
    {
        ex.printStackTrace();
    } catch(ClassNotFoundException ex)
    {
        ex.printStackTrace();
    }
    UIManager.put("swing.boldMetal",Boolean.FALSE);  
     addToList();
     javax.swing.SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {
        
                                  createAndShowGUI();
                                    //Ensures that there is only 10 questions displayed per round(if there is 10 questions)
                                }
                                
                              
               
            
        
        });

}
    public static void addToList()throws IOException
        {
            stringNames.add(new ArrayList<String>());
            String filename="test.txt";
            File file=new File(filename);
            String []temp;
            if (file.exists())
            {
                Scanner fileReader=new Scanner(file);
                while(fileReader.hasNext())
                {
                    temp=fileReader.nextLine().split(",");
                    for (int i=0;i<stringNames.size();i++)
                        stringNames.get(i).add(temp[i]);
                }fileReader.close();
                System.out.println(stringNames.get(0));

            }else System.out.println("File does not exist.");

        File images = new File("images.txt");
        File questions = new File("Questions.txt");
        Scanner fileReader;
        
        
        imageDetails.add(new ArrayList<String>());
        imageDetails.add(new ArrayList<String>());

        for(int i=0; i<8; i++)
            questionDetails.add(new ArrayList<String>());
       
        if(images.exists())
        {
            fileReader = new Scanner(images);

            while(fileReader.hasNext())
            {
                temp = fileReader.nextLine().split(",");
                for(int i=0; i<imageDetails.size(); i++)
                    imageDetails.get(i).add(temp[i]);
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
                    questionDetails.get(i).add(temp[i]); System.out.println(questionDetails.get(1).get(0));
            }
            fileReader.close();
        }
    
        }
}



