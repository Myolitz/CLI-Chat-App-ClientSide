package org.Myolitz;

import lombok.Setter;

import java.io.DataInputStream;
import java.io.IOException;

public class ClientThread extends Thread
{
    private DataInputStream serverToClient;
    @Setter
    private boolean isDone;

    public ClientThread(DataInputStream stream)
    {
        this.serverToClient = stream;
        isDone = false;
    }

    @Override
    public void run()
    {
        while (!isDone)
        {
            try
            {
                System.out.println(serverToClient.readUTF());
            } catch (IOException e)
            {
                System.out.println("Had trouble reading from the server!");
                isDone = true;
            }
        }
        System.out.println("Closing READ thread for user");
    }
}
