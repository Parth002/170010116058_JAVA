import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
  private static Socket socket;
  public static void main(String[] args)
  {
    try
    {
      int port = 6969;
      ServerSocket serverSocket = new ServerSocket(port);
      System.out.println("Server Started and listening to the port 6969");

     
        socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String input = br.readLine();
        System.out.println("Message received from client is "+input);
        String returnMessage;

        
        StringBuilder input1 = new StringBuilder();
        input1.append(input);
        input1 = input1.reverse();
        String out = input1.toString();
        StringBuffer cap =  new StringBuffer(out);
        int chln = cap.length();
        
        for (int i=0; i<chln; i++)
         {
            Character c = cap.charAt(i);
            if (Character.isLowerCase(c))
              cap.replace(i, i+1, Character.toUpperCase(c)+"");
            else
              cap.replace(i, i+1, Character.toLowerCase(c)+"");
         }
         returnMessage = cap.toString();
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(returnMessage);
        System.out.println("Message sent to the client is "+returnMessage);
        bw.flush();
    }
    catch (IOException e) {
      System.out.println("Error with sending/receiving");
    }

    finally
    {
      try
      {
        //SOcket Close
        socket.close();
      }
      catch(Exception e){}
    }
  }
}