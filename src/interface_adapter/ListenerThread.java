package interface_adapter;

import java.util.Scanner;

/**
 * A thread that continuously listens for console input using a ConsoleListener.
 */
public class ListenerThread extends Thread {
    public volatile boolean exit = false;
    private ConsoleListener consoleListener;

    /**
     * Constructs a ListenerThread with a new ConsoleListener.
     */
    public ListenerThread() {
        this.consoleListener = new ConsoleListener(new Scanner(System.in));
    }

    /**
     * Runs the thread, continuously listening for console input until the exit flag is set.
     */
    @Override
    public void run() {
        while (!exit) {
            this.consoleListener.listen();
        }
        System.out.println("Thread is interrupted!");
    }

    /**
     * Sets the exit flag to true, causing the run loop to terminate.
     */
    public void setExit() {
        this.exit = true;
        System.out.println("Exit has been set to true!");
    }
}
