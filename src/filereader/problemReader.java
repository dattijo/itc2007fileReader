/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filereader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PhDLab
 */
public class problemReader 
{    
    int numberOfExams,numberOfPeriods,numberOfRooms,examDuration;
    List <Integer> exams = new ArrayList<>();
    Map <Integer,List> studentMap = new HashMap<>();
    
    problemReader(String file) throws IOException 
    {
        InputStream in = getClass().getResourceAsStream(file);
        if (in == null) 
        {
            in = new FileInputStream(file);
        }
    
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);

        StreamTokenizer token = new StreamTokenizer(br);
        token.eolIsSignificant(true);
        boolean found ;
        found = false ;

        token.nextToken();
        while(!found) 
        {
            if ((token.sval != null) && ((token.sval.compareTo("Exams") == 0)))
                found = true ;
            else
                token.nextToken() ;
        }

        token.nextToken() ;
        token.nextToken() ;

        numberOfExams =  (int)token.nval ;
        System.out.println("Number of Exams: "+numberOfExams);
        token.nextToken();token.nextToken();token.nextToken();
        examDuration = (int)token.nval;
         System.out.println("Exam 1 duration: "+examDuration);
        //Read Enrollments
        found=false;
        int t=0,i=1;
        while(!found) 
        {
            if ((token.sval != null) && ((token.sval.compareTo("Periods") == 0)))
            {
                token.nextToken();
                token.nextToken();
                numberOfPeriods=(int)token.nval;
                System.out.println("Finished Reading Enrollments.");
                System.out.println("Number of Periods = "+numberOfPeriods);
                found = true ;
            }                
            else
            { 
                t = token.nextToken();
                //System.out.println("Token  = "+token.toString());
                switch(t)
                {
                    case StreamTokenizer.TT_EOL:                        
                        token.nextToken();
                        examDuration = (int)token.nval;
                        System.out.println("Exam "+ ++i +" duration: "+examDuration);
                        break;
                    case StreamTokenizer.TT_NUMBER:  
                        Double tok = token.nval;
                        if(studentMap.containsKey(tok.intValue()))
                        {                            
                            exams.add(i);
                            //studentMap.put(tok.intValue(), exams);
                        }
                        else
                        {
                            studentMap.put(tok.intValue(), exams);
                        }
                        System.out.println("nextToken():"+token.nval);
                        break;
                }
            }
        }
            
        //Read Periods
        found=false;
        while(!found) 
        {
            if ((token.sval != null) && ((token.sval.compareTo("Rooms") == 0)))
            {
                token.nextToken();
                token.nextToken();
                numberOfRooms=(int)token.nval;
                System.out.println("Finished Reading Periods.");
                System.out.println("Number of Periods = "+numberOfRooms);
                found = true ;
            }                
            else
            {                                                   
                t = token.nextToken();
                switch(t)
                {
                    case StreamTokenizer.TT_EOL:
                        break;
                    case StreamTokenizer.TT_NUMBER:                    
                        System.out.println("nextToken():"+token.nval);
                        break;
                }
            }
        }
         
        //Read Rooms
        found=false;
        while(!found) 
        {
            if ((token.sval != null) && ((token.sval.compareTo("PeriodHardConstraints") == 0)))
            {
                token.nextToken();
                System.out.println("Finished Reading Rooms.");
                found = true ;
            }                
            else
            {                                                   
                t = token.nextToken();
                switch(t)
                {
                    case StreamTokenizer.TT_EOL:
                        break;
                    case StreamTokenizer.TT_NUMBER:                    
                        System.out.println("nextToken():"+token.nval);
                        break;
                }
            }
        }
        
        //Read PeriodHardConstraints
        found=false;
        while(!found) 
        {
            if ((token.sval != null) && ((token.sval.compareTo("RoomHardConstraints") == 0)))
            {
                token.nextToken();
                token.nextToken();
                numberOfRooms=(int)token.nval;
                System.out.println("Finished Reading PeriodHardConstraints.");
                found = true ;
            }                
            else
            {                                                   
                t = token.nextToken();
                switch(t)
                {
                    case StreamTokenizer.TT_EOL:
                        break;
                    case StreamTokenizer.TT_NUMBER:                    
                        System.out.println("nextToken():"+token.nval);
                        break;
                    case StreamTokenizer.TT_WORD:
                        System.out.println("nextToken():"+token.sval);
                        break;
                }
            }
        }
        
        //Read RoomHardConstraints
        found=false;
        while(!found) 
        {
            if ((token.sval != null) && ((token.sval.compareTo("InstitutionalWeightings") == 0)))
            {
                token.nextToken();
                token.nextToken();
                numberOfRooms=(int)token.nval;
                System.out.println("Finished Reading RoomHardConstraints.");
                found = true ;
            }                
            else
            {                                                   
                t = token.nextToken();
                switch(t)
                {
                    case StreamTokenizer.TT_EOL:
                        break;
                    case StreamTokenizer.TT_NUMBER:                    
                        System.out.println("nextToken():"+token.nval);
                        break;
                    case StreamTokenizer.TT_WORD:
                        System.out.println("nextToken():"+token.sval);
                        break;
                }
            }
        }
            
        //Read InstitutionalWeightings
        while(t != StreamTokenizer.TT_EOF)
            {                               
                switch(t)
                {
                    case StreamTokenizer.TT_EOL:
                        break;
                    case StreamTokenizer.TT_NUMBER:                    
                        System.out.println("nextToken():"+token.nval);
                        break;
                    case StreamTokenizer.TT_WORD:
                        System.out.println("nextToken():"+token.sval);
                        break;
                }
                t= token.nextToken();
            }
        
        System.out.println("Reading Successful.");
    } 
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try
        {
            problemReader objProblemReader = new problemReader("C:/Users/PhDLab/Documents/NetBeansProjects/jMetal/resources/itc2007/exam_comp_set3.exam");
        }
        catch(Exception e)
        {
            new IOException(e);
        }
        
    }    
}