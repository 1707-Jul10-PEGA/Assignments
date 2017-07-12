package com.md.q2.driver;

public class Driver {

	public static void main(String[] args) {
		fib(5);
	}
	
	public static int fib(int n) {
		//base case
		if(n == 1) {
			return 1;
		}
		
		//recursive case
		else {
			int fibInt = ( fib(n-1) + fib(n-2) );
			System.out.println("Fib of " + fibInt);
			return fibInt;
		}
		
	}
	
	
}
