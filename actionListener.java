import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class actionListener  extends JPanel
                        implements ActionListener
 {
 	static String answerForQuestion="";
 	public actionListener()
 	{
 		String name="";
 	}
   public void useactionListener(ActionEvent e,String answerEx,int score) {
        
     
        System.out.println(e.getActionCommand());
        answerForQuestion=e.getActionCommand();
        if (answerForQuestion.equals(answerEx))
        {
            JOptionPane.showMessageDialog(null,"CORRECT!!");
            score++;
          
        }
        else
           { 
                JOptionPane.showMessageDialog(null,"INCORRECT","Nope",2); 
                System.out.println("INCORRECT!!!");
           }     //get Answer
        //go to next question 
       // in next quesion if there is none left go to display result
    }
   }