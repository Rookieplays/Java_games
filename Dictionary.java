/** ------------------------------------------------------------------------ 
 * Dictionary.java
 *      Implement a dictionary class that reads in dictionary words
 *      from a file and allows looking up dictionary words.
 *
 * Class: CS 102, Fall 2006
 * Lab: Englebert Humberdink, Mon. 5:00 AM
 * System: BlueJ 2.0.5, jsdk 1.5, Windows XP
 *
 * @author  Dale Reed
 * @version October 27, 2006
 * ----------------------------------------------------------------------------
 */

// import Java libraries that are needed
import java.util.Scanner;       // used for console input

// the following are needed to implement reading from the dictionary
import java.io.IOException;     // for IOException
import java.util.ArrayList;     // Used to create ArrayLists
import java.io.*;               // Used for IOException, File
 

// Declare the class
public class Dictionary
{
    // Declare a dynamically allocated ArrayList of Strings for the dictionary.
    // The dictionary can hold any number of words.
    ArrayList<String> dictionary;
    
    
    /** Dictionary() Constructor
     *      Default constructor
     */
    public Dictionary() throws IOException
    {
       // First take care of creating and initializing the dictionary
        // Define the instance of the dictionary ArrayList
        dictionary = new ArrayList<String>();
        // Now fill the dictionary array list with words from the dictionary file
        readInDictionaryWords();        
    }//end Dictionary constructor
    
    
    /** readInDictionaryWords()
     *      Read in the words to create the dictionary.  It throws
     *  an IOException, which is a way to gracefully handle errors
     *  should there be a problem reading from the input.
     */
    public void readInDictionaryWords() throws IOException
    {
        // Define a Scanner to read from an input file.  Note that the name of
        // the file given in the code below MUST match the actual filename of 
        // the dictionary file.  This file should be in the same directory
        // as the source code for WordCross.java
        File dictionaryFile = new File("dictionary.txt");    // declare the file
        // ensure file exists and is in the correct directory
        if( ! dictionaryFile.exists()) {
            System.out.println("*** Error *** \n" +
                               "Your dictionary file has the wrong name or is " +
                               "in the wrong directory.  \n" +
                               "Aborting program...\n\n");
            System.exit( -1);    // Terminate the program
        }
        Scanner inputFile = new Scanner( dictionaryFile);
        
        // while there are words in the input file, add them to the dictionary, 
        // converting them to upper case
        while( inputFile.hasNext()) {

            dictionary.add( inputFile.nextLine().toUpperCase() );

        }
    }//end readInDictionaryWords()
    
    
    /** wordExists()
     *      Allow looking up a word in dictionary, returning a value of 
     *      true or false
     */
    public boolean wordExists( String wordToLookup)
    {
        System.out.println("Openning Dictionary");
        if( dictionary.contains( wordToLookup)) {
            return true;    // words was found in dictionary
        }
        else {
            return false;   // word was not found in dictionary    
        }
    }//end wordExists
    
    
}//end Class Dictionary

