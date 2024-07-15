package interface_adapter;

import interface_adapter.ConsoleListener;

import java.util.Scanner;

public class ListenerThread extends Thread
{
    public volatile boolean exit = false;
    private ConsoleListener consolelistener;

    public ListenerThread()
    {
        this.consolelistener = new ConsoleListener(new Scanner(System.in));
    }

    public void run()
    {
        while(!exit)
        {
            this.consolelistener.listen();
        }
        System.out.println("Thread is interruptted!");
    }

    public void setExit()
    {
        this.exit = true;
        System.out.println("exit has been set to true!");
    }


}


