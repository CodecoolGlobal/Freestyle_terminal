package com.codecool.termlib;

import com.sun.jdi.InvalidTypeException;

import java.awt.event.*;
import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;


// Request from user to enter a username
// If it's female(ends with A) color it magenta && if it's male, color with blue
// Create a file with highest scores. As default set some  :low scores from 90 years old Alex and 90 years old Silviu: 1
//  and highest score from 70 years old Alex: 2
// Think of a transition between mainTerminal demo and game demo
// Error handling
// ASCI art

public class Main {



    public static void main(String [] args) throws Exception{

        Terminal mainTerminal = new Terminal();

        game(mainTerminal);


    }

    private static void game(Terminal mainTerminal) throws InterruptedException {

        Scanner userInput = new Scanner(System.in);

        boolean gameRunning = true;



        while (gameRunning) {

            mainTerminal.clearScreen();
            mainTerminal.moveTo(1, 1);

            mainTerminal.setColor(Color.GREEN);

            String welcomeStr="Welcome";

            System.out.println();

            for (char l : welcomeStr.toCharArray()) {
                if (l == 'W') {
                    mainTerminal.setColor(Color.GREEN);
                    System.out.print(l);
                } else if (l == 'e') {
                    mainTerminal.setColor(Color.YELLOW);
                    System.out.print(l);
                } else if (l == 'l') {
                    mainTerminal.setColor(Color.BLUE);
                    System.out.print(l);
                } else if (l == 'c') {
                    mainTerminal.setColor(Color.CYAN);
                    System.out.print(l);
                } else if (l == 'o') {
                    mainTerminal.setColor(Color.YELLOW);
                    System.out.print(l);
                } else if (l == 'm') {
                    mainTerminal.setColor(Color.RED);
                    System.out.print(l);
                }

            }

            mainTerminal.resetStyle();

            System.out.println(" to our Simion Says Game!");
            mainTerminal.setUnderline();
            System.out.println("RULES");
            mainTerminal.resetStyle();
            System.out.println("In this game you will have to reproduce a word pattern formed by a chosen number of letters, maximum ten. ");
            System.out.println("Each letter represents a color:");
            System.out.println();
            colorString("y: yellow", mainTerminal, Color.YELLOW);
            colorString("g: green", mainTerminal, Color.GREEN);
            colorString("r: red", mainTerminal, Color.RED);
            colorString("m: magenta", mainTerminal, Color.MAGENTA);
            colorString("b: blue", mainTerminal, Color.BLUE);
            colorString("c: cyan", mainTerminal, Color.CYAN);
            mainTerminal.resetStyle();
            System.out.println();
            System.out.println("The letters will disappear after 5 seconds.");

            mainTerminal.resetStyle();

            while(true) {

                mainTerminal.setUnderline();
                mainTerminal.setDim();
                mainTerminal.setBgColor(Color.WHITE);
                mainTerminal.setColor(Color.BLUE);
                System.out.print("How many colors you want to guess: ");
                mainTerminal.resetStyle();
                System.out.println();

                Scanner userLength=new Scanner(System.in);

                int stringLength=0;
                try {
                    stringLength=userLength.nextInt();
                } catch (InputMismatchException e) {
                }

                if (stringLength > 0 && stringLength < 10) {

                String corString=generateString(stringLength);

                mainTerminal.resetStyle();
                ActionListener taskPerformer=new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        //...Perform a task...

                        mainTerminal.hideString();
                    }
                };

                Timer timer=new Timer(2000, taskPerformer);
                timer.setRepeats(false);
                timer.start();

                Thread.sleep(5000);
                mainTerminal.moveCursor(Direction.BACKWARD, corString.length());

                System.out.println("Enter the string:");
                String userString=userInput.next();
                mainTerminal.setUnderline();
                if (userString.equalsIgnoreCase(corString)) {
                    System.out.println("Congratulations! You're word is correct: " + corString);
                } else {
                    System.out.println("Sorry, you're answer is wrong. The correct string was: " + corString + ". Please try again.");
                }

                mainTerminal.resetStyle();
                System.out.println();

                System.out.println("Do you want to play another game? y/n ");
                Scanner newGame=new Scanner(System.in);
                String answer=newGame.nextLine();
                if (answer.equals("n")) {
                    System.out.println();
                    gameRunning = false;
                    break;
                }
            }
                else {
                    System.out.println("Invalid input. Try Again");
                }
            }
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

    public static void colorString(String str, Terminal terminal, Color color) {

        terminal.setColor(color);
        System.out.println(str);

    }
}