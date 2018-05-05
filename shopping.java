import java.util.*;
import javax.swing.*;
import javax.swing.awt.*;
import java.io.*;

public class shopping
{
	public static void main(String[]args)
	{
		if (checkIfUserExists()==true)
			presentTask();
		else
			createNewUser();

	}
	public static boolean checkIfUserExists()
	{
		int selection;
		selection=JOptionPane.showConfirmDialog(null,"Have You created a List before?","Shopping list",3);
		if(selection==JOptionPane.YES_OPTION)
			return true;
		else 
			return false;
	}
	public static void presentTask()
	{

	}
	public static void createNewUser()
	{

	}
}