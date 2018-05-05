for (int i=1,j=36; i<king.length&&j<40; i++,j=i) switch(king[i]){case 10: suits[j]="King"; break;}
		
	for (int i=1,j=40; i<king.length&&j<44; i++,j=i) switch(queen[i]){case 10: suits[j]="queen"; break;}
		
	for (int i=1,j=44; i<king.length&&j<48; i++,j=i) switch(jack[i]){case 10: suits[j]="jack"; break;}
		
	for (int i=1,j=48; i<king.length&&j<52; i++,j=i) switch(ace[i]){case 1: suits[j]="Ace";break; case 11:suits[i]="Ace";break;}

		System.out.println(Deck.get(36));


switch(Deck.get(36)){case 10: suits[36]="King"; break;}
	  switch(Deck.get(40)){case 10: suits[37]="King"; break;}
	   switch(Deck.get(44)){case 10: suits[38]="King"; break;}
	    switch(Deck.get(48)){case 10: suits[39]="King"; break;}
		
	 switch(Deck.get(37)){case 10: suits[40]="queen"; break;}
	  switch(Deck.get(41)){case 10: suits[41]="queen"; break;}
	   switch(Deck.get(45)){case 10: suits[42]="queen"; break;}
	    switch(Deck.get(49)){case 10: suits[43]="queen"; break;}
		
	 switch(Deck.get(38)){case 10: suits[44]="jack"; break;}
	  switch(Deck.get(42)){case 10: suits[45]="jack"; break;}
	   switch(Deck.get(46)){case 10: suits[46]="jack"; break;}
	    switch(Deck.get(50)){case 10: suits[47]="jack"; break;}
		
	switch(Deck.get(39)){case 1: suits[48]="Ace";break; case 11:suits[48]="Ace";break;}
	switch(Deck.get(43)){case 1: suits[49]="Ace";break; case 11:suits[49]="Ace";break;}
	switch(Deck.get(47)){case 1: suits[50]="Ace";break; case 11:suits[50]="Ace";break;}
	switch(Deck.get(51)){case 1: suits[51]="Ace";break; case 11:suits[51]="Ace";break;}


	import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;


 public class Music extends JPanel
 					implements ListSelectionListener
 { 
 	private JList list;
    private DefaultListModel listModel;

    private static final String playString = "PLay";
    private static final String pauseString = "Pause";
    private JButton playButton;
    private JButton pauseButton;
    private JTextField songName;
 	static ArrayList<ArrayList<String>>musicInfo=new ArrayList<ArrayList<String>>();
 	static String name="";
 public Music() {
        super(new BorderLayout());

        listModel = new DefaultListModel();
        for (int i=17;i<musicInfo.get(0).size();i++)
        listModel.addElement(musicInfo.get(0).get(i));
    	 list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.addActionListener(new PLayListener());
        
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
    	JButton playButton = new JButton(playString);
    	JButton pauseButton = new JButton(pauseString);

    	playButton = new JButton(playString);
        playButton.setActionCommand(playString);
        playButton.addActionListener(new PLayListener());

        pauseButton = new JButton(pauseString);
        pauseButton.setActionCommand(pauseString);
        pauseButton.addActionListener(new PauseListener());

        songName = new JTextField(10);
        songName.addActionListener(new PLayListener());
        //songName.getDocument().addDocumentListener(hireListener);
        String name = listModel.getElementAt(
                              list.getSelectedIndex()).toString();
         JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));

        buttonPane.add(playButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(songName);
        buttonPane.add(pauseButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);System.out.println(name);
    }

        
    	 class PLayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
             String names=e.getActionCommand();
             System.out.println(names); 
          boolean play=true;
            music(play,names);

            
          
            }
        }
         class PauseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
          String names=e.getActionCommand();
          System.out.println(names);
           boolean	pause=false;
           music(pause,names);
            
          
            }
        }
        public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
            //No selection, disable fire button.
                playButton.setEnabled(false);
                 pauseButton.setEnabled(false);

            } else {
            //Selection, enable the fire button.
                playButton.setEnabled(true);
                 pauseButton.setEnabled(true);
            }
        }
    }
    

public static void main(String[]args)throws IOException
{
	addToList();
	 javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	
}
public static void createAndShowGUI()
{
	
	JFrame frame = new JFrame("Music Player 1.00");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new Music();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);



}
/*public static class AL implements ActionListener{
        public final void actionPerformed(ActionEvent e){
            music();
    }
}*/


 public static void music(boolean play,String nameS)
{

	int IndexofSelection=musicInfo.get(2).indexOf(nameS);
	String pathName=musicInfo.get(2).get(IndexofSelection);
	
        try{
        			
        			System.out.println(pathName);
        	
	 Clip clip=AudioSystem.getClip();

	 AudioInputStream ais=AudioSystem.getAudioInputStream(new File("music/"+pathName));
	 clip.open(ais);
	 clip.loop(0);
	 if (play==true)clip.start();
	 else clip.stop();
	}catch (Exception e){System.out.println("Error playing...");e.printStackTrace();}
	
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