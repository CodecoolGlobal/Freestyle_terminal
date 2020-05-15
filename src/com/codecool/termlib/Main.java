package com.codecool.termlib;
import java.awt.event.*;
import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;


public class Main {



    public static void main(String [] args) throws Exception{



        Terminal mainTerminal = new Terminal();

        while(true) {
            System.out.println();
            System.out.println("Welcome to our Java Freestyle Project");
            System.out.println();
            System.out.println();
            System.out.println("MENU");
            System.out.println();
            System.out.println("1. Clear Screen");
            System.out.println("2. Set Font Color");
            System.out.println("3. Set Background Color");
            System.out.println("4. Set Underline");
            System.out.println("5. Set Dim");
            System.out.println("6. Move Cursor");
            System.out.println("7. Simon Game");
            System.out.println("8. Reset style");
            System.out.println("0. Exit");
            System.out.println();

            System.out.print("Enter your choice: ");

            Scanner userInput = new Scanner(System.in);
            int userSelection = -19;

            try {
                userSelection=userInput.nextInt();
            } catch (InputMismatchException e) {
            }

            if (userSelection >= 0 && userSelection <= 10) {

                switch (userSelection) {

                    case 0:
                        System.exit(0);
                    case 1:
                        mainTerminal.clearScreen();
                        break;
                    case 2:
                        String[] acceptedColors={"GREEN", "YELLOW", "BLUE", "CYAN", "RED", "MAGENTA"};
                        List<String> listOfColors=Arrays.asList(acceptedColors);
                        System.out.print("Please enter a color for the font (green, yellow, blue, cyan, red, magenta): ");
                        while (true) {
                            String chosenColor=userInput.next().toUpperCase();
                            if (listOfColors.contains(chosenColor)) {
                                mainTerminal.setColor(Color.valueOf(chosenColor));
                                break;
                            } else {
                                System.out.print("Please enter a valid color: ".toUpperCase());
                            }
                        }
                        break;

                    case 3:
                        String[] acceptedBgColors={"GREEN", "YELLOW", "BLUE", "CYAN", "RED", "MAGENTA"};
                        List<String> listOfBgColors=Arrays.asList(acceptedBgColors);
                        System.out.println("Please enter a color for the background (green, yellow, blue, cyan, red, magenta): ");
                        while (true) {
                            String chosenColor2=userInput.next().toUpperCase();
                            if (listOfBgColors.contains(chosenColor2)) {
                                mainTerminal.setBgColor(Color.valueOf(chosenColor2));
                                break;
                            } else {
                                System.out.print("Please enter a valid color: ".toUpperCase());
                            }
                        }
                        break;

                    case 4:
                        mainTerminal.setUnderline();
                        System.out.println("Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an " +
                                "unknown printer took a galley of type and scrambled it to make a type specimen book. It has" +
                                " survived not only five centuries, but also the leap into electronic");
                        System.out.println();
                        mainTerminal.resetStyle();
                        break;

                    case 5:
                        mainTerminal.setDim();
                        System.out.println("Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an " +
                                "unknown printer took a galley of type and scrambled it to make a type specimen book. It has" +
                                " survived not only five centuries, but also the leap into electronic");
                        System.out.println();
                        break;

                    case 6:
                        System.out.println("Please enter the direction(up, down, forward, backward): ");
                        String direction=userInput.next().toUpperCase();
                        System.out.println("Please enter the amount: ");
                        int amount=userInput.nextInt();
                        mainTerminal.moveCursor(Direction.valueOf(direction), amount);
                        System.out.println("This is where you moved the cursor.");
                        break;

                    case 7:
                        game(mainTerminal);
                        break;
                    case 8:
                        mainTerminal.resetStyle();
                        break;
                }
            } else {
                System.out.println("Invalid input. Try Again".toUpperCase());
            }
        }

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

            System.out.println(" to our Simon Says Game!");
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
            System.out.println("The letters will disappear after 2 seconds.");

            mainTerminal.resetStyle();

            while(true) {

                mainTerminal.setUnderline();
                mainTerminal.setDim();
                mainTerminal.setBgColor(Color.WHITE);
                mainTerminal.setColor(Color.RED);
                System.out.print("\033[1mHow many colors you want to guess: ");
                mainTerminal.resetStyle();
                System.out.println();

                Scanner userLength=new Scanner(System.in);

                int stringLength=0;
                try {
                    stringLength=userLength.nextInt();
                } catch (InputMismatchException e) {
                }

                if (stringLength > 0 && stringLength <= 10) {

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

                Thread.sleep(2700);
                mainTerminal.moveCursor(Direction.BACKWARD, corString.length());

                System.out.println("Enter the string:");
                String userString=userInput.next();
                mainTerminal.setUnderline();
                if (userString.equalsIgnoreCase(corString)) {
                    System.out.println("Congratulations! Your word is correct: " + corString);
                } else {
                    System.out.println("Sorry, your answer is wrong. The correct string was: " + corString + ". Please try again.");
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
                    System.out.println("Invalid input. Try Again".toUpperCase());
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