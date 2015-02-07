/**
* Title: HangmanDemo.java
* Abstract: This program plays a Hangman game using the Hangman.java class
* Author: Anthony Symkowick
* ID: 3895
* Date: 2/4/15
*/


import java.util.*;

/**
* Files were created on a Mac, these are the paths to my test files.
* Experience should not differ on Windows.
* /Users/asymkowick/t1.txt
* /Users/asymkowick/t2.txt
*/

public class HangmanDemo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Hangman Game = new Hangman();
		
		Game.openFile();
		Game.setupGame();
		Game.startGame();

	}

}
