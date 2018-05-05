import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;


 public class Music 
 {    
        static ArrayList<ArrayList<String>>musicInfo=new ArrayList<ArrayList<String>>();
        static Scanner sc=new Scanner(System.in);
        static String songChoice="";
public Music ()throws IOException
{
    String play="";
    
}
public String getListOfSongs()throws IOException
{
	addToList();
	String[]SongList=new String[musicInfo.get(0).size()];
	for (int i=0;i<musicInfo.get(0).size();i++)
	{
		SongList[i]=Integer.toString(i)+"."+" "+musicInfo.get(0).get(i)+"--"+musicInfo.get(1).get(i);
	}
	for(int i=0;i<SongList.length;i++)System.out.println(SongList[i]);
		System.out.println("Select a Song to play:\t");
	songChoice=sc.nextLine();
	
		return songChoice;

}
public void MakePlayList()throws IOException
{
	boolean play=true;

	String []playlist=songChoice.split(",");//split input into string arrays
	int []playlistSongs=new int[playlist.length];//turn to interger
	for (int i=0;i<playlist.length;i++)
		playlistSongs[i]=Integer.parseInt(playlist[i]);
	 String []pathName=new String[playlistSongs.length];//get the position of the song from list
	String[]songname=new String[playlistSongs.length]; 
	for (int i=0;i<playlist.length;i++)
	{ pathName[i]=musicInfo.get(2).get(playlistSongs[i]);
     songname[i]= musicInfo.get(0).get(playlistSongs[i]);}
       try{
                    
                    
     Clip[] clip=new Clip[playlist.length];
     for(int i=0;i<playlistSongs.length;i++)
{System.out.println(pathName[i]);
            
    	clip[i] =AudioSystem.getClip();

     AudioInputStream ais=AudioSystem.getAudioInputStream(new File("music/"+pathName[i]));
     clip[i].open(ais);
 }
    // clip[i].loop(0);
   // System.out.println("Now Playing "+songname[i]+"...");

     for(Clip c: clip)
    while(c.getFramePosition()<c.getFrameLength())
        Thread.yield(); 
     //clip[i].start();

    }catch (Exception e){System.out.println("Error playing...");e.printStackTrace();}
   
}


 public  void playMusic(boolean play,String nameS)throws IOException


{
	
    int IndexofSelection=Integer.parseInt(nameS);
    String pathName=musicInfo.get(2).get(IndexofSelection);
    String songname=musicInfo.get(0).get(IndexofSelection);
        try{
                    
                    System.out.println(pathName);
            
     Clip clip=AudioSystem.getClip();

     AudioInputStream ais=AudioSystem.getAudioInputStream(new File("music/"+pathName));
     clip.open(ais);
     clip.loop(0);
     if (play==true){clip.start();System.out.println("Now Playing "+songname+"...");}
     else clip.stop();
    }catch (Exception e){System.out.println("Error playing...");e.printStackTrace();}
    
 }
 public  void playMusicFromList(boolean play,String nameS)throws IOException


{
    
    int IndexofSelection=musicInfo.get(0).indexOf(nameS);
    String pathName=musicInfo.get(2).get(IndexofSelection);
    String songname=musicInfo.get(0).get(IndexofSelection);
        try{
                    
                    System.out.println(pathName);
            
     Clip clip=AudioSystem.getClip();

     AudioInputStream ais=AudioSystem.getAudioInputStream(new File("music/"+pathName));
     clip.open(ais);
     clip.loop(0);
     if (play==true){clip.start();System.out.println("Now Playing "+songname+"...");}
     else clip.stop();
    }catch (Exception e){System.out.println("Error playing...");e.printStackTrace();}
    
 }
    public static String[] convertToArray(ArrayList<String>listToConvert)
    {
        String[]arr=new String[listToConvert.size()];
        for (int i=0; i<listToConvert.size(); i++) 
            arr[i]=listToConvert.get(i);
        return arr;
            
        
    }
 public String displayMusic()
 {
    ArrayList<String>listOfSong=new ArrayList<String>();
    for (int i=0; i<musicInfo.get(0).size();i++ )
     {
        listOfSong.add(musicInfo.get(0).get(i));     
    }
    String[]songs;
    songs=convertToArray(listOfSong);
    String selection= (String)JOptionPane.showInputDialog(null,"Choose a song","Music",0,null,songs,songs[0]);
    return selection;
 }


    public static void addToList()throws IOException
    {
       
        String []temp;
       
        File music = new File("music.txt");
        Scanner fileReader;


        musicInfo.add(new ArrayList<String>());
        musicInfo.add(new ArrayList<String>());
        musicInfo.add(new ArrayList<String>());
        
     
        if(music.exists())
        {
            fileReader = new Scanner(music);
            while(fileReader.hasNext())
            {
                temp = fileReader.nextLine().split(",");
                for(int i=0; i<musicInfo.size(); i++)
                    musicInfo.get(i).add(temp[i]); //System.out.println(musicInfo.get(1).get(0));
            }
            fileReader.close();
        }
    }
}
 