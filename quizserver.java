/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftaServer;
import java.net.*;
import java.io.*;
/**
 *
 * @author saurav anand
 */
public class quizserver {
    public static void main(String args[]){
        ServerSocket serso = null;
        try
        {
            serso = new ServerSocket(25002);
        }
        catch(IOException e)
        {
            System.out.print("Server socket nhi ban rha");
        }
        
        while(true)
        {
            Socket s;
            try
            {
                System.out.println("waiting to connect");
                s = serso.accept();
                System.out.println("New client arrived");
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                Thread th = new ClientHandeler(s,is,os);
                th.start();
            }
            catch(IOException e)
            {
                System.out.println("Error inside while" + e.getMessage());
            }
            
            
        }
            
        
        
        
    }
    
}
