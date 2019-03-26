/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftaServer;

import softa1.*;
import java.net.*;
import java.io.*;
/**
 *
 * @author saurav anand
 */


public class ClientHandeler extends Thread {
     Socket s;
     InputStream is;
     OutputStream os;
     InputStreamReader isr;
     OutputStreamWriter osr;
     BufferedReader br;
     BufferedWriter bw;
    
     public ClientHandeler(Socket s, InputStream is, OutputStream os)
     {
         this.is = is;
         this.os = os;
         this.s = s;
         System.out.println("new thread started");
     }
     
     @Override
     public void run()
     {
        isr = new InputStreamReader(is);
        osr = new OutputStreamWriter(os);
        br = new BufferedReader(isr);
        bw = new BufferedWriter(osr);
        System.out.println("inside run");
         
         try{
            String userName = br.readLine();
            System.out.println(userName);
            String password = br.readLine();
            System.out.println(password);
            String name = br.readLine();
            System.out.println(name);
            int TeacOrStud = br.read();
            System.out.println(TeacOrStud);
            String Stream = br.readLine();
            System.out.println(Stream);
            String ip = br.readLine();
            System.out.println(ip);
            
            
            
             
             if(name.compareTo("null") == 0)
             {
                 Log_in log = new Log_in(userName,password,TeacOrStud,ip);
                 log.connect();
                 String str;
                 System.out.println("Prepare to Login");
                 if(log.login())
                 {
                     System.out.println("Logged in Successfully");
                     str = "Logged in Successfully"+"\n";
                     bw.write(str);
                     bw.flush();
                 }else{
                     System.out.println("Unable to Login");
                     str = "Unable to login"+"\n";
                     bw.write(str);
                     bw.flush();
                 }
                 
             }
             else{
                 Log_in log = new Log_in(userName,password,name,TeacOrStud,Stream,ip);
                 log.connect();
                 System.out.println("Preparing to register");
                 String str;
                 if(log.register())
                 {  
                      System.out.println("Successfully Registered");
                      str = "Successfully Registered"+"\n";
                     bw.write(str);
                     bw.flush();
                     
                 }else{
                       System.out.println("Unable to Register");
                       str = "Unable to Register"+"\n";
                     bw.write(str);
                     bw.flush();
                 }
             }
        }
         catch(Exception ex)
         {
             System.out.println("Connection Lost: ");
         }
         
         
         
         
         
     
     }
    
}
