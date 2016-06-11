package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Server implements Runnable
{
    private Thread thread;
    private ServerSocket servSock;
    private JFrame frame;
    private JTextArea textArea;

    public Server()
    {
        frame = new JFrame();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea = new JTextArea("");
        textArea.setEditable(false);
        frame.getContentPane().add(textArea);
        frame.setVisible(true);

        try
        {
            // Detect server ip
            InetAddress IP = InetAddress.getLocalHost();
            textArea.append("IP of server is : "+IP.getHostAddress() + "\n");
            textArea.append("Waitting for connect......\n");

            // Create server socket
            servSock = new ServerSocket(2000);

            // Create socket thread
            thread = new Thread(this);
            thread.start();
        }
        catch (java.io.IOException e)
        {
            textArea.append("Socket啟動有問題 !\n");
            textArea.append("IOException :" + e.toString() + "\n");
        }
    }

    public void run()
    {
        Socket clntSock = null;
        InputStream in=null;
        byte[] b = new byte[1024];
        int length;
        try {
            clntSock = servSock.accept();
            in = clntSock.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textArea.append("Connected!!\n");
        while(true)
        {
            try
            {
                // Transfer data
                length = in.read(b);
                String s = new String(b);
                textArea.setText("The result from APP is " + s);

            }
            catch(Exception e)
            {
                //System.out.println("Error: "+e.getMessage());
            }
        }
    }
}
