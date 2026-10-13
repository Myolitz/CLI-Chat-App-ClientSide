/** Class: COSC 2327
 * @author Jesus Cardenas
 * @operating-system Endeavour OS
 */

package org.Myolitz;

//Libraries
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSide extends Thread
{
    protected ClientThread thread;
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    private Scanner scanner;
    private String userString;

    final int portNum = 12345;
    final String serverAddress = "localhost";

    public ClientSide()
    {
        scanner = new Scanner(System.in);
        userString = "";
    }

    @Override
    public void run()
    {
        try (Socket srvrCon = new Socket(serverAddress, portNum))
        {
            toServer = new DataOutputStream(new BufferedOutputStream(srvrCon.getOutputStream()));
            fromServer = new DataInputStream(new BufferedInputStream(srvrCon.getInputStream()));

            thread = new ClientThread(fromServer);
            thread.start();

            /*
                TODO:
                Login style system for memory? Let's get basic stuff going
             */
            System.out.println("Enter your nickname:");

            while (!userString.equalsIgnoreCase("QUIT"))
            {
                userString = scanner.nextLine();
                toServer.writeUTF(userString);
                toServer.flush();
            }
            thread.setDone(true);
        }
        catch (IOException e)
        {
            System.out.println("Failed to connect to server, try again!");
        }
    }
}