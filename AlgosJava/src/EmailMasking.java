import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Scanner;

public class EmailMasking {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        // to fetch email address 
        Scanner sc = new Scanner(System.in); 
        Pattern  pattern = Pattern.compile("\\.\\.");
        Pattern  digits = Pattern.compile("[0-9]");        
        Pattern  case1= Pattern.compile("[\\s]?[()][\\s]?"); 
        Pattern  specialchars = Pattern.compile("[\\s-]"); 
        Pattern  case2= Pattern.compile("[(]");
        
        
        String fname ="";
        while(sc.hasNext())
        {
        String inputtext = sc.nextLine();
            if(inputtext.charAt(0)=='E')
                {                
                 String[] name = inputtext.split("@|E:"); 
                 name[1] = name[1].replaceAll("\\s+", "");
                 Matcher m = pattern.matcher(name[1]);
                 int c = 0; 
                 while ( m.find())
                 c++;
                   //System.out.println(c);
                if(name[1].matches("^[A-za-z0-9!#$%&'*+-/=?^_'{|}~]+([.][A-za-z0-9!#$%&'*+-/=?^_'{|}~])*$") && (c==0) && 
                  (name[1].charAt(0)!='.') &&(name[1].charAt(name[1].length()-1)!='.'))
                    {
                    fname = Character.toString(name[1].charAt(0));
                    fname = fname.concat("*****" + name[1].substring(name[1].length()-1));
                    fname = "E:" + fname;
                    fname = fname.concat("@" + name[2]);        
                    System.out.println(fname);
                    } 
               }
            else if (inputtext.charAt(0)=='P')
                {
        
                  String[] text= inputtext.split("P:");
                  String num ="";
                  text[1] = text[1].replaceAll("\\s+", "");
                  String orig = text[1].substring(text[1].length()-4);
                  text[1]= text[1].substring(0,text[1].length()-4);
                  if(text[1].matches("\\d{6}"))
                   {
                      Matcher m1 = digits.matcher(text[1]);       
                      num = m1.replaceAll("*");
                    }
                  else if(text[1].matches("([+]\\d+)?\\s?\\d{3}\\d{3}"))
                    {
                     Matcher m1 = digits.matcher(text[1]);       
                     String phn = m1.replaceAll("*");
                     Matcher m2 = specialchars.matcher(phn);
                     num = m2.replaceAll("-");
                     }
                 else if(text[1].matches("[+]\\d+\\s?\\(\\d{3}\\)\\s?\\d{3}[-\\s]"))
                    {
                     Matcher m1 = digits.matcher(text[1]);       
                     String phn = m1.replaceAll("*");
                     Matcher m2 = case1.matcher(phn);
                     phn = m2.replaceAll("-");
                     Matcher m3 = specialchars.matcher(phn);
                     num = m2.replaceAll("-");
                    }
                
                else if(text[1].matches("\\(\\d{3}\\)\\s?\\d{3}[-\\s]"))
                   {
                   
                    Matcher m1 = digits.matcher(text[1]);       
                    String phn = m1.replaceAll("*");                    
                    Matcher m3 = case2.matcher(phn);
                    num = m3.replaceAll("");                    
                    Matcher m2 = case1.matcher(num);
                    num = m2.replaceAll("-");
                    Matcher m4 = specialchars.matcher(num);
                    num = m4.replaceAll("-");
                   }
                
             System.out.println("P:" + num + orig);
             
           }
        }
    }
    
}