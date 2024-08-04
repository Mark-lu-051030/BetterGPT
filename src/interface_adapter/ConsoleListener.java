package interface_adapter;

import java.util.HashMap;
import java.util.Scanner;

/**
 * A class that listens for console input and triggers corresponding actions.
 */
public class ConsoleListener {

    /**
     * A nested static class representing an action to be performed on receiving a message.
     */
    public static class Action {
        /**
         * Performs the action associated with the received message.
         *
         * @param msg The message received from the console.
         */
        public static void act(String msg) {
            System.out.println("Receive message: " + msg);
        }
    }

    private Scanner scanner;
    private HashMap<String, Action> reply = new HashMap<>();
    private Action defaultAction;
    private boolean status = true;

    /**
     * Constructs a ConsoleListener with the specified input scanner.
     *
     * @param inputScan The Scanner to read console input from.
     */
    public ConsoleListener(Scanner inputScan) {
        this.scanner = inputScan;
    }

    /**
     * Adds an action to be triggered for a specific input message.
     *
     * @param inputMsg The message to trigger the action.
     * @param inputAct The action to be performed.
     */
    public void addAction(String inputMsg, Action inputAct) {
        this.reply.put(inputMsg, inputAct);
    }

    /**
     * Removes an action associated with a specific input message.
     *
     * @param inputMsg The message whose action should be removed.
     * @param inputAct The action to be removed.
     */
    public void removeAction(String inputMsg, Action inputAct) {
        this.reply.remove(inputMsg, inputAct);
    }

    /**
     * Replaces an existing action with a new one for a specific input message.
     *
     * @param inputMsg The message whose action should be replaced.
     * @param inputAct The new action to be set.
     * @return The previous action associated with the message.
     */
    public Action replaceAction(String inputMsg, Action inputAct) {
        return this.reply.replace(inputMsg, inputAct);
    }

    /**
     * Checks the current status of the listener.
     *
     * @return true if the listener is active, false otherwise.
     */
    public boolean checkStatus() {
        return this.status;
    }

    /**
     * Listens for console input in a new thread.
     */
    public void listenInNewThread() {
        Thread t = new Thread() {
            public volatile boolean exit = false;

            @Override
            public void run() {
                listen();
                System.out.println("Thread is interrupted!");
            }
        };

        t.start();
    }

    /**
     * Listens for console input and triggers actions based on the input messages.
     */
    public void listen() {
        while (true) {
            String line = scanner.nextLine();
            Action.act(line);

            if (line.equals("stop")) {
                System.out.println("Stop listening!");
                this.status = false;
                break;
            }
        }
    }
}
