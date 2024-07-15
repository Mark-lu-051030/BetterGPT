package interface_adapter;
import javax.swing.*;
import java.util.HashMap;
import java.util.Scanner;


public class ConsoleListener
{

    public static class Action
    {
        public static void act(String msg)
        {
            System.out.println("Receive message: " + msg);
        }
    }

    public Scanner scanner;
    public HashMap<String, Action> reply;
    public Action defaultaction;

    private boolean status = true;


    public ConsoleListener(Scanner inputscan)
    {
        this.scanner = inputscan;
    }

    public void addAction(String inputmsg, Action inputact)
    {
        this.reply.put(inputmsg, inputact);
    }

    public void removeAction(String inputmsg, Action inputact)
    {
        this.reply.remove(inputmsg, inputact);
    }

    public Action replaceAction(String inputmsg, Action inputact)
    {
        return this.reply.replace(inputmsg, inputact);
    }

    public boolean checkStatus()
    {
        return this.status;
    }

    public void listenInNewThread()
    {
        Thread t = new Thread()
        {
            public volatile boolean exit = false;
            public void run()
            {
                    listen();

                System.out.println("Thread is interruptted!");

            }
        };

        t.start();



    }

    public void listen()
    {
        while(true)
        {
            String line = scanner.nextLine();
            Action.act(line);

            if(line.equals("stop"))
            {
                System.out.println("Stop listen!");
                this.status = false;
                break;
            }

        }
    }




}

