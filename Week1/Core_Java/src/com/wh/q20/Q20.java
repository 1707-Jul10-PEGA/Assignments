package com.wh.q20;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Q20. Create a notepad file called Data.txt and enter the following:
 * 
 * Mickey:Mouse:35:Arizona
 * Hulk:Hogan:50:Virginia
 * Roger:Rabbit:22:California
 * Wonder:Woman:18:Montana
 * 
 * Write a program taht would read from the file and print it out to the screen
 * in the following format:
 * 
 * Name: Micky Mouse
 * Age: 35 years
 * State: Arizona State
 * 
 * @author Wei Huang
 *
 */
public class Q20 {
	public static void main(String[] args) {
		String[] data;
		try (Scanner scan = new Scanner(new FileReader("Data.txt"))) {
			while (scan.hasNextLine()) {
				data = scan.nextLine().split(":");
				System.out.println("Name: " + data[0] + " " + data[1]);
				System.out.println("Age: " + data[2] + " years");
				System.out.println("State: " + data[3] + " State");
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
