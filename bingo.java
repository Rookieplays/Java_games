import java.util.*;
import java.io.*;
public class bingo
{

public static void main(String[]args)
	{

		draw();
	}
public static void draw()
{
	char []board=new char[6];
	String[]boardnum=new String[6];
	String []b=new String[6];
	String []k=new String[6];
	String []n=new String[6];
	String []g=new String[6];
	String []o=new String[6];

	board[0]='a';
	board[1]='b';
	board[2]='c';
	board[3]='d';
	board[4]='e';
	board[5]='f';

	for(int i=1;i<=6;i++)
	{
		System.out.print("*");
		for (int j=1; j<=6;j++) {
		System.out.print("*");	
		}System.out.println("");
	}
	for(int i=0;i<boardnum.length;i++)boardnum[i]=Integer.toString(i);
		for (int i=0;i<6 ;i++ ) b[i]=(board[0]+"")+boardnum[i];
			for (int i=0;i<6 ;i++ ) k[i]=(board[1]+"")+boardnum[i];
				for (int i=0;i<6 ;i++ ) n[i]=(board[2]+"")+boardnum[i];
					for (int i=0;i<6 ;i++ ) g[i]=(board[3]+"")+boardnum[i];
						for (int i=0;i<6 ;i++ ) o[i]=(board[4]+"")+boardnum[i];

	for(int i=0;i<6;i++)
		System.out.print(b[i]);
		System.out.println("");
	for(int i=0;i<6;i++)
		System.out.print(k[i]);
		System.out.println("");
		for(int i=0;i<6;i++)
		System.out.print(n[i]);
		System.out.println("");
		for(int i=0;i<6;i++)
		System.out.print(g[i]);
		System.out.println("");
		for(int i=0;i<6;i++)
		System.out.print(o[i]);
		System.out.println("");
							

		
	
}
}