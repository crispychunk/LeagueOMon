package ui;

import java.util.Scanner;

public class PrintText {

    String text;

    //EFFECT: create a text object that can print and get input
    public PrintText() {
        text = "nothing";
    }

    //EFFECT: print text such that you must give an input to continue

    public void print(String text) {
        this.text = text;
        System.out.println(this.text);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    //EFFECT: print text and return input
    public String ask(String text) {
        this.text = text;
        System.out.println(this.text);
        Scanner scanner = new Scanner(System.in);
        String ans = scanner.nextLine();
        return ans;
    }

    //EFFECT: print text without needing input
    public void printFast(String text) {
        this.text = text;
        System.out.println(this.text);
    }
}
