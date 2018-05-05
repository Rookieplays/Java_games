import java.util.*;
import java.io.*;
public class characterSelection
{

  /* Version 1.00 
    =========================================================================================
    -  updates go in here....                                                                                    -
    -                                                                                       - 
    -                                                                                       -
    -                                                                                       -
    -=========================================================================================
*/
    static String classchoice="";
  public static void main(String[] args)throws IOException {
       getAntributes();
  }

  public static void getAntributes()throws IOException
  {
      ClassSelection getClassSelection=new ClassSelection();
        classchoice= getClassSelection.SelectedClass();
        int[]stats =new int[8];
        double []classBonus=new double[4];//holds the extra skill values that diffrentiates the different klasses
        
        String nameOfStat[]=new String[8];//exactly what it says
        int Droll=0;
        int Dcount=0;
        nameOfStat[0]="Hp";
        nameOfStat[1]="Chrisma";
        nameOfStat[2]="Luck";
        nameOfStat[3]="Agility";
        nameOfStat[4]="Strength";
        nameOfStat[5]="Courage";
        nameOfStat[6]="Intelligence";
        nameOfStat[7]="Endurance";
        //Health=10
        stats[0]=10;
        //distribute normal stats: Determined by a die roll with a 16 edge dice.
        while(Dcount<stats.length)
        {
            for(int i=1;i<stats.length;i++)
            {
                 Droll=(int)(Math.random()*16+1);
                stats[i]=Droll;
                Dcount++;
                
                
            }
        }  for(int i=0;i<stats.length;i++)
         System.out.println("Roll for "+nameOfStat[i]+": "+stats[i]);
            classBonus=getTraits(stats);
        /////////HUNTER//////////////
        if (classchoice.equals("Hunter"))
      { 
       stats[4]+=classBonus[0];//Str
        stats[6]+=classBonus[1];//Int
        stats[3]+=classBonus[2];//Agl
        stats[5]+=classBonus[3];//crg
        System.out.println("Bonus: \n"+nameOfStat[4]+"---> "+classBonus[0]+"\n"+nameOfStat[6]+"---> "+classBonus[1]+"\n"+nameOfStat[3]+"---> "+classBonus[2]+"\n"+nameOfStat[5]+"---> "+classBonus[3]+"\n");
    }
        //////////Warlock/////////////
        else if (classchoice.equals("Warlock"))
        {
        stats[6]+=classBonus[0];//Int
        stats[7]+=classBonus[1];//Endurance
        stats[2]+=classBonus[2];//Luck
        stats[1]+=classBonus[3];//Chrisma
           System.out.println("Bonus: \n"+nameOfStat[6]+"---> "+classBonus[0]+"\n"+nameOfStat[7]+"---> "+classBonus[1]+"\n"+nameOfStat[2]+"---> "+classBonus[2]+"\n"+nameOfStat[1]+"---> "+classBonus[3]+"\n");
        }
        /////////////ELfen//////////////
        else if (classchoice.equals("Elf"))
        {
        stats[3]+=classBonus[0];//Agl
        stats[7]+=classBonus[1];//Endurance
        stats[2]+=classBonus[2];//Luck
        stats[1]+=classBonus[3];//chrisma
           System.out.println("Bonus: \n"+nameOfStat[3]+"---> "+classBonus[0]+"\n"+nameOfStat[7]+"---> "+classBonus[2]+"\n"+nameOfStat[2]+"---> "+classBonus[2]+"\n"+nameOfStat[1]+"---> "+classBonus[3]+"\n");
        }
          //////Rougue////////  
        else if (classchoice.equals("Rogue"))
        {
        stats[6]+=classBonus[0];//Int
        stats[5]+=classBonus[1];//crg
        stats[2]+=classBonus[2];//Int
        stats[7]+=classBonus[3];//Endurance
           System.out.println("Bonus: \n"+nameOfStat[6]+"---> "+classBonus[0]+"\n"+nameOfStat[5]+"---> "+classBonus[1]+"\n"+nameOfStat[2]+"---> "+classBonus[2]+"\n"+nameOfStat[7]+"---> "+classBonus[3]+"\n");
        }
        for(int i=0;i<stats.length;i++)
        System.out.println(nameOfStat[i]+"="+stats[i]);
    }
public static double[] getTraits(int[]stat)
{
double classBonus[]=new double[4];
    
    if (classchoice.equals("Hunter"))
  {classBonus[0]=(stat[7]+stat[3]+stat[5])/1.5;
  classBonus[1]=(stat[0])/0.8;
  classBonus[2]=(stat[4]+stat[7])/1.2;
  classBonus[3]=(stat[4]+stat[6]+stat[7])/2;
  return classBonus;}


    else if(classchoice.equals("Warlock"))
    {classBonus[0]=(stat[6])/0.8;
  classBonus[1]=(stat[4]+stat[2]+stat[3])/2;
  classBonus[2]=(stat[0])/0.8;
  classBonus[3]=(stat[4]+stat[6])/1.5;return classBonus;}
 
  else if(classchoice.equals("Elf"))
    {classBonus[0]=(stat[4]+stat[7])/1.5;
  classBonus[1]=(stat[4]+stat[2]+stat[3])/2;
  classBonus[2]=(stat[0])/0.8;
  classBonus[3]=(stat[4]+stat[6])/1.5;return classBonus;}
  
  else if(classchoice.equals("Rogue"))
    {classBonus[0]=(stat[0])/0.8;
  classBonus[1]=(stat[4]+stat[6]+stat[7])/2;
  classBonus[2]=(stat[0])/0.8;//luck
  classBonus[3]=(stat[4]+stat[2]+stat[3])/2;return classBonus;}
  
    else return classBonus;
    
        
}

}