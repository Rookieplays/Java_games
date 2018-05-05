import javax.swing.*;
import java.util.*;

 public class buy
{
	public buy()
	{

	}
	public boolean shopDialog(int input,int coins,int amount)
	{
		int dialogBtn=JOptionPane.showConfirmDialog(null,"Do you want to buy This Item?\n Cost: "+amount+"\nYour coins: "+coins,"Locked",JOptionPane.YES_NO_OPTION);
		if (input==JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}
	public int Transaction(int cost,int money)
	{
		if (money>=cost)
		{
			money=money-cost;
			JOptionPane.showMessageDialog(null,"Game Unlocked\nCurrent coins in Inventory: "+money,"Unlocked",1);
		return money;
		}
		else JOptionPane.showMessageDialog(null,"You do not have enough money in your Inventory\nYour coins: "+money,"Transaction Failed",0);
		return money;
	}
}