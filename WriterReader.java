
package bankmanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriterReader {
    
    public static  boolean WriteToTabDelimitedFile(int customerID, String[]  data, String FileName)  {
        try  {
            FileWriter fileWritter  =  new  FileWriter(FileName); 
            BufferedWriter bufferWritter  =  new  BufferedWriter(fileWritter);
            
            bufferWritter.write(customerID + "\t");
            // loop through all your data and print  it to the file 
            for (int  i=0;i< data.length;i++)
                bufferWritter.write(data[i]+"\t"); 
            bufferWritter.write("\n"); 
            bufferWritter.close();
        }  catch (IOException e)  {
            System.out.println("Error Printing Tab Delimited File");
            return false;
        }
        return true;
    }
    
    public static  boolean  appendToTabDelimitedFile (String data, String FileName)  {
        try{
            File file = new File(FileName);
            FileWriter fileWritter  =  new  FileWriter(file.getName(),true);
            BufferedWriter bufferWritter  =  new  BufferedWriter(fileWritter);
            
            if(file.exists()){  //if file doesn’t exists, return false return false;
                bufferWritter.write(data);
                bufferWritter.newLine(); 
                bufferWritter.close();
            }
        }  catch  (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    public static  String [] readFromTabDelimitedFile (String FileName)  { 
        List<String> lines  =  new  ArrayList<String>();
        try  {
            FileReader fileReader  =  new  FileReader(FileName);
            BufferedReader bufferedReader  =  new  BufferedReader(fileReader); 
            String line =  null;
            while ((line  =  bufferedReader.readLine())  != null)
                lines.add(line);
            bufferedReader.close();
        }  catch (IOException e)  {
            e.printStackTrace();
        }
        return lines.toArray(new  String[lines.size()]);
    }
    
}
