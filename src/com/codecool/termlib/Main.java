package com.codecool.termlib;

import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;


// Request from user to enter a username
// If it's female(ends with A) color it magenta && if it's male, color with blue
// Create a file with highest scores. As default set some  :low scores from 90 years old Alex and 90 years old Silviu: 1
//  and highest score from 70 years old Alex: 2
// Think of a transition between terminal demo and game demo
// Error handling
// ASCI art

public class Main {

    public static void main(String [] args) throws Exception{

        Terminal terminal = new Terminal();
        Scanner userInput = new Scanner(System.in);

        boolean gameRunning = true;


        while (gameRunning) {

            terminal.clearScreen();
            terminal.moveTo(1, 1);

            terminal.setColor(Color.GREEN);

            String welcomeStr="Welcome";

            System.out.println();

            for (char l : welcomeStr.toCharArray()) {
                if (l == 'W') {
                    terminal.setColor(Color.GREEN);
                    System.out.print(l);
                } else if (l == 'e') {
                    terminal.setColor(Color.YELLOW);
                    System.out.print(l);
                } else if (l == 'l') {
                    terminal.setColor(Color.BLUE);
                    System.out.print(l);
                } else if (l == 'c') {
                    terminal.setColor(Color.CYAN);
                    System.out.print(l);
                } else if (l == 'o') {
                    terminal.setColor(Color.YELLOW);
                    System.out.print(l);
                } else if (l == 'm') {
                    terminal.setColor(Color.RED);
                    System.out.print(l);
                }
            }

            terminal.resetStyle();

            System.out.println(" to our Simion Says Game!");
            System.out.println("Rules: Reproduce the colors");
            terminal.setUnderline();
            terminal.setDim();
            terminal.setBgColor(Color.WHITE);
            terminal.setColor(Color.BLUE);
            System.out.print("How many colors you want to guess: ");
            terminal.resetStyle();
            System.out.println();
            Scanner userLength=new Scanner(System.in);
            int stringLength=userLength.nextInt();
            String corString=generateString(stringLength);

            terminal.resetStyle();
            ActionListener taskPerformer=new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    //...Perform a task...

                    terminal.hideString();
                }
            };

            Timer timer=new Timer(2000, taskPerformer);
            timer.setRepeats(false);
            timer.start();

            Thread.sleep(5000);
            terminal.moveCursor(Direction.BACKWARD, corString.length());

            System.out.println("Enter the string:");
            String userString=userInput.next();
            if (userString.equalsIgnoreCase(corString)) {
                System.out.println("You're good");
            } else {
                System.out.println("You're not good");
            }

            System.out.println("Do you want to play another game? y/n ");
            Scanner newGame = new Scanner(System.in);
            String answer = newGame.nextLine();
            gameRunning = answer.equals("y") ? true: false;
        }

    }

    private static String generateString(int strLength) {

        Terminal strTerminal = new Terminal();

        char[] stringColors = {'y', 'g', 'b', 'r', 'm', 'c'};

        StringBuilder temp = new StringBuilder("");

        for (int i = 0; i < strLength; i++) {
        temp.append(stringColors[(int) (Math.random() * stringColors.length)]);
        }

        String userString = temp.toString();

        for(int i = 0; i < userString.length(); i++) {

            char l = userString.charAt(i);

            if (l == 'g') {
                strTerminal.setColor(Color.GREEN);
                System.out.print(l);
            }
            else if (l == 'y') {
                strTerminal.setColor(Color.YELLOW);
                System.out.print(l);
            }
            else if (l == 'b') {
                strTerminal.setColor(Color.BLUE);
                System.out.print(l);
            }
            else if (l == 'c') {
                strTerminal.setColor(Color.CYAN);
                System.out.print(l);
            }
            else if (l == 'm') {
                strTerminal.setColor(Color.MAGENTA);
                System.out.print(l);
            }
            else if (l == 'r') {
                strTerminal.setColor(Color.RED);
                System.out.print(l);
            }

        }

        return userString;
    }
}