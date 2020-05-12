package com.codecool.termlib;


public class Main {

    public static void main(String[] args) {

        Terminal gameTerminal = new Terminal();

//        gameTerminal.setColor(Color.CYAN);
//        System.out.println("Make me cyan");
//        gameTerminal.setBgColor(Color.GREEN);
//        System.out.println("I have background color");
//        gameTerminal.resetStyle();
          System.out.println("I am back to default");
//        gameTerminal.clearScreen();
//        System.out.print("First new message");
        gameTerminal.moveTo(100, 50);
        System.out.print("A lot of text which has to use move");
    }
}