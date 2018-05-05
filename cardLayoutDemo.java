import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class cardLayoutDemo implements ItemListener {
    JPanel cards; //a panel that uses CardLayout
    final static String QUESTION1PANEL = "Question 1";
    final static String QUESTION2PANEL = "Question 2";
    final static String QUESTION3PANEL = "Question 3";
    final static String QUESTION4PANEL = "Question 4";
    final static String QUESTION5PANEL = "Question 5";
    final static String QUESTION6PANEL = "Question 6";
    final static String QUESTION7PANEL = "Question 7";
    final static String QUESTION8PANEL = "Question 8";
    final static String QUESTION9PANEL = "Question 9";
    static ArrayList<ArrayList<String>> stringNames = new ArrayList<ArrayList<String>>();
     static ArrayList<ArrayList<String>> imageDetails = new ArrayList<ArrayList<String>>();
    static ArrayList<ArrayList<String>> questionDetails = new ArrayList<ArrayList<String>>();
    static ArrayList<Integer> ranodmNumbersArray = new ArrayList<Integer>();
     static ArrayList<String> storeAnwers = new ArrayList<String>();
     static ArrayList<Boolean> EvaluateAnswer = new ArrayList<Boolean>();
  
    static int[] questionAnswers;
     static String[] questions;
    static String[] explanations;
    static String Imgpath;
    static String answerEx="";
   // static String []=new String[10];
    static int l=0,m=0;
    static String answerForQuestion="";
    static int score = 0, overallScore=0, questionCounter;
    static boolean questionsExists = false;
    static boolean loggedin = false;
    private  JRadioButton[]btns=new JRadioButton[4];
    JLabel picture;
    JLabel qs=new JLabel("my Label");
   static ButtonGroup group;
    
    public static void loadrandomNumbersArray()
    {
         Random myRandom = new Random();
        int[] numbers = new int[9];
        boolean[] check = new boolean[9];
        int amountFilled = 0;
        int trial;
          while (amountFilled < 9) {  
            trial = myRandom.nextInt(9);
            if (!check[trial]) {
                check[trial] = true;
                numbers[amountFilled] = trial;
                amountFilled++;
            }
        }
        for (int r = 0; r < 9; r++) {
            System.out.println(numbers[r]);
            ranodmNumbersArray.add(numbers[r]);
        }
    }
    public void addComponentToPane(Container pane) {
        loadrandomNumbersArray();
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = { QUESTION1PANEL, QUESTION2PANEL,QUESTION3PANEL,QUESTION4PANEL,QUESTION5PANEL,QUESTION6PANEL,QUESTION7PANEL,QUESTION8PANEL,QUESTION9PANEL };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        JPanel[] qi=new JPanel[9];
       
for (l=0;l<qi.length;l++)
{
        int selectionint=(int)(Math.random()*8);
    String[]selection=new String[imageDetails.get(0).size()];
  for (int i=0; i<selection.length; i++) {
        selection[i]=imageDetails.get(1).get(selectionint);
    }
   String selectionStr=selection[selectionint];
     System.out.println(imageDetails.get(1).get(imageDetails.get(0).size()-1));
   // ranodmNumbersArray.clear(); // used this arrayList to hold the random numbers which have already been generated, to allow for unique numbers each time
        int questionPosition = imageDetails.get(1).indexOf(selectionStr); 
        int size = numberOfQuestions(questionPosition), aRandomNumber;
        int min = questionDetails.get(0).indexOf(questionPosition+1+""), max = (min+size)-1; //min: the topics position * by the amount of questions MAX: min + the amount of questions there are-1
        String temp;
        
        boolean enoughQuestions = false;
        questionAnswers = new int[size]; //decalring the array which will contain the answers to the questions 
        questions = new String[size]; //declaring the array which will hold the questions   
        explanations = new String[size]; //declaring the array which will hold the explanations
     
        for(int i=0; i<size&&!enoughQuestions;i++) //loop will keep running until all the questions are cycled through and the amount of questions does not equal 10
        {
            Imgpath="";

     
            aRandomNumber=ranodmNumbersArray.get(l);
            System.out.println(">>>"+aRandomNumber);
             //= min + (int)(Math.random() * ((max - min) + 1)); //random number is generated beetween the min and max
            
               //Fresh random number is added to random number array list
                   Imgpath=imageDetails.get(1).get(aRandomNumber);
                    qs.setText(questionDetails.get(1).get(aRandomNumber)); 
                   System.out.println(ranodmNumbersArray.get(i));
                   //Question is added to a string and the next 4 lines is concatenating the suggested answer onto the question
                    btns[0]= new JRadioButton (questionDetails.get(2).get(aRandomNumber));
                    btns[1]= new JRadioButton (questionDetails.get(3).get(aRandomNumber));
                    btns[2]= new JRadioButton (questionDetails.get(4).get(aRandomNumber));
                    btns[3]= new JRadioButton (questionDetails.get(5).get(aRandomNumber));
                    questionAnswers[i] = Integer.parseInt(questionDetails.get(6).get(aRandomNumber).trim()); //The answer for this question is added to the array containing the answers at the same index as its question
                    answerEx=questionDetails.get(7).get(aRandomNumber); //The explanation for this question is added to the array containing the answers at the same index as its question
                    //questions[i] = output; //The question and suggested answer string is added to the questions array
                     //i is only incremented when a unique random number was generated
                
               
                if(i>= 10) //Ensures only 10 questions, answers and explanations are loaded.
                enoughQuestions = true; 
        }   
        //Put the JComboBox in a JPanel to get a nicer look.
        storeAnwers.add(answerEx);
        System.out.println(">>>>>>> "+storeAnwers.get(l));
        group=new ButtonGroup();  
   for(int i=0;i<btns.length;i++)
   {
       // btns[i].setActionCommand(stringnames[i]);
        group.add(btns[i]);
        btns[i].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {//m++;
                try{
                System.out.println(e.getID() == ActionEvent.ACTION_PERFORMED
                    ? "ACTION_PERFORMED" : e.getID());
                 System.out.println(e.getActionCommand());
                 System.out.println(storeAnwers.get(m));
                 String storeAnwer=storeAnwers.get(m);
        answerForQuestion=e.getActionCommand();
        if (answerForQuestion.equals(storeAnwer))
        {
            JOptionPane.showMessageDialog(null,"CORRECT!!");
            EvaluateAnswer.add(true);
            score=score+10;m++;
            overallScore=(score*100)/90;
            JOptionPane.showMessageDialog(null,"You Scored "+overallScore+"% .");
           
        }
        else
           { 
                JOptionPane.showMessageDialog(null,"INCORRECT","Nope",2); 
                EvaluateAnswer.add(false);
                score=score-2;
            overallScore=(score*100)/90;
            JOptionPane.showMessageDialog(null,"You Scored "+overallScore+"% .");
           
               // System.out.println("INCORRECT!!!");
           }   stats(overallScore);
           //get Answer
            }catch (IndexOutOfBoundsException ex){JOptionPane.showMessageDialog(null,"Selections Already made close the window.","Quiz completed",2);}
        }
        });
       // btns[i].addActionListener(this);
   }
   picture=new JLabel(createImageIcon(Imgpath));
    picture.setPreferredSize(new Dimension(230,162));
    System.out.println(Imgpath);
    JPanel radioPanel=new JPanel(new GridLayout(0,1));
    for (int i=0;i<btns.length;i++)
    radioPanel.add(btns[i]); 
    

   qi[l]=new JPanel();
    qi[l].add(radioPanel, BorderLayout.LINE_END);
   qi[l].add(qs,BorderLayout.LINE_END);
     qi[l].add(picture, BorderLayout.CENTER);
        //setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        //Create the "cards".
       /* JPanel card1 = new JPanel();
        card1.add(new JButton("Button 1"));
        card1.add(new JButton("Button 2"));
        card1.add(new JButton("Button 3"));
        
        JPanel card2 = new JPanel();
        card2.add(new JTextField("TextField", 20));*/
     }   
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(qi[0], QUESTION1PANEL);
        cards.add(qi[1], QUESTION2PANEL);
        cards.add(qi[2], QUESTION3PANEL);
        cards.add(qi[3], QUESTION4PANEL);
        cards.add(qi[4], QUESTION5PANEL);
        cards.add(qi[5], QUESTION6PANEL);
        cards.add(qi[6], QUESTION7PANEL);
        cards.add(qi[7], QUESTION8PANEL);
        cards.add(qi[8], QUESTION9PANEL);

        
        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
       // result();
        
    }
    public static void stats(int overallscore)
    {
          
         
      int amountofT=0,amountofF=0;
      for (int i = 0; i < EvaluateAnswer.size(); i++) {
        if (EvaluateAnswer.get(i) == true) 
         amountofT++; 
     else if (EvaluateAnswer.get(i)==false) amountofF++;
      }
    

    
       System.out.println(amountofT);
           System.out.println(amountofF);
           for (int i=0; i<EvaluateAnswer.size(); i++) 
               
           
           System.out.println(EvaluateAnswer.get(i));

        JOptionPane.showMessageDialog(null, "Your overallscore was "+overallscore+"\n"+"You got "+amountofT+" right out of "+EvaluateAnswer.size()+" Attempts. \n"+amountofF+" of your Attempts were Wrong.","Stats",1);


    }
    
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("cardLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        cardLayoutDemo demo = new cardLayoutDemo();
        demo.addComponentToPane(frame.getContentPane());
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
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
    }
    public static void main(String[] args)throws IOException 
    {
        addToList();
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
             @Override
            public void run() {
                createAndShowGUI();
                         }
        });
    }
   /* public static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    public static void result()
    {
      //  String []selections=new String[questionDetails.get(0).size()];
        String selection;
        ActionListener e;
       actionListener listen=new actionListener();
       listen.useactionListener( e,answerEx,score);
       System.out.println(selection+"\n"+answerEx);
       if (selection!=null)
       { 
       if (selection.equals(answerEx))
       {
            score=score+1;
            System.out.println("Correct "+ score);
       }
       else
        System.out.println("Incorret "+ score);
    }*/

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
    
        