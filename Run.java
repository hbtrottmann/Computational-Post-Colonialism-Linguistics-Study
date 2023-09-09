import java.util.*;
import java.io.*;

//output will have quantitative data in a .csv file and
//String data (lines with abnormally heavy weight) will be output into a .txt file
public class Run
{
    public static void main (String [] args) throws IOException
    {
    //Scanners
        Scanner text = new Scanner(new File("Text.txt"));
        Scanner positivephrases = new Scanner(new File("Positive Phrases.txt"));
        Scanner positivewords = new Scanner(new File("Positive Words.txt"));
        Scanner negativephrases = new Scanner(new File("Negative Phrases.txt"));
        Scanner negativewords = new Scanner (new File("Negative Words.txt"));
        PrintWriter results = new PrintWriter (new File("/Users/helentrottmann/Computational Post-Colonialism Linguistics Study/Results.csv"));
        PrintWriter heavylines = new PrintWriter (new File("Heavy Lines.txt"));
        

    //Variables
        int wc = 0; //word count
        int ww = 0; //total weighted words
        int pw = 0; //total positive words 
        int nw = 0; //total negative words 
        int lines = 0; //total number of sentences
        int ws = 0; //total number of weighted sentences
        int pp = 0; //total positive phrases
        int np = 0; //total negative phrases
        int total = 0; //total weight (pos and neg)
        ArrayList<String> heavy = new ArrayList<String>();



    //dictionary arrayLists
        ArrayList<String> pphrases = new ArrayList<String>();
        ArrayList<String>pwords = new ArrayList<String>();
        ArrayList<String> nphrases = new ArrayList<String>();
        ArrayList<String>nwords = new ArrayList<String>();

    //fill the dictionaries 
        //positive phrases
        while(positivephrases.hasNext())
        {
            pphrases.add(positivephrases.next());
        }
        //positive words
        while(positivewords.hasNext())
        {
            pwords.add(positivewords.next());
        }
        //negative phrases
        while(negativephrases.hasNext())
        {
            nphrases.add(negativephrases.next());
        }
        //negative words
        while(negativewords.hasNext())
        {
            nwords.add(negativewords.next());
        }

        wc = text.nextInt();
       text.nextLine();
       while(text.hasNextLine())
       {
            String line = Punctuation.clear(text.nextLine()); //make it w/o any of the punctation 
            lines++;
            int lineweight = 0;

        //word by word analysis
            String[] words = line.split(" ");
            //this will run through each of the positive words. 
            //it does not account for words like "no" and "not" preceding a given buzz-word. 
            //this will be addressed in later studies
            for(int i=0; i<pwords.size(); i++)
            {
                for(int j=0; j<words.length; j++)
                {
                    if(pwords.get(i).equals(words[j]))
                    {
                        pw++;
                        lineweight++;
                    }
                }
            }  
            
            //negative words 
            for(int i=0; i<nwords.size(); i++)
            {
                for(int j=0; j<words.length; j++)
                {
                    if(nwords.get(i).equals(words[j]))
                    {
                        nw++;
                        lineweight++;
                    }
                }
            }

        //now for the full line
            //positive phrases
            for(String l : pphrases)
            {
                if(line.contains(l))
                {
                    pp++;
                    lineweight++;
                }
            }
            //negative phrases
            for(String l : nphrases)
            {
                if(line.contains(l))
                {
                    np++;
                    lineweight++;
                }
            }
        
        //single line analysis
            total += ww+ws;

            if(lineweight>=3) //the number here should be adjusted based off of the average line weight of a given text 
            {
                heavy.add(line);
            }
            if(lineweight!=0)
            {
                ws++;
            }
            
       }
       ww = pw+nw;

       double ww2wc = (double)ww/wc;
       double pw2nw = (double)pw/nw;
       double perweighted = (double)ws/lines;
       double aveweight = (double)total/lines;
       int totalpos = pp+pw;
       int totalneg = np+nw;
       double totalp2n = (double)totalpos/totalneg;

       for(int i=0; i<heavy.size(); i++)
       {
            heavylines.println(heavy.get(i));
       }

       results.print(wc);
       results.print(", ");
       results.print(ww);
       results.print(", ");
       results.print(pw);
       results.print(", ");
       results.print(nw);
       results.print(", ");
       results.print(ww2wc);
       results.print(", ");
       results.print(pw2nw);
       results.print(", ");
       results.print(lines);
       results.print(", ");
       results.print(ws);
       results.print(", ");
       results.print(pp);
       results.print(", ");
       results.print(np);
       results.print(", ");
       results.print(perweighted);
       results.print(", ");
       results.print(aveweight);
       results.print(", ");
       results.print(totalpos);
       results.print(", ");
       results.print(totalneg);
       results.print(", ");
       results.print(totalp2n);

       results.close();
       heavylines.close();

    }
}
