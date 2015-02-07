
/**
* Title: Hangman.java
* Abstract: This program plays a game of Hangman.
* Author: Anthony Symkowick
* ID: 3895
* Date: 2/6/15
*/


/**
* /Users/asymkowick/t1.txt
* /Users/asymkowick/t2.txt
*/

import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class Hangman {

	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);
		String filename, key;
		boolean won = false;
		
		System.out.print("Enter a file name: ");
		filename = keyboard.nextLine();  
		System.out.println();
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		key = reader.readLine(); // key is the ORIGINAL string value of EASY or DIFFICULT
		key = key.replace("",  " ").trim(); // key now stores E A S Y or D I F F I C U L T
		
		
		StringBuilder hiddenkey = new StringBuilder(key.length()); //StringBuilder creates a string of equal length to be filled in later
		
		
		
		char[] keyArray = key.toCharArray(); //use a char array to compare letters later on
		//System.out.println(keyArray); //delete this
		//System.out.println(key);
		
		
		
		
		while(hiddenkey.length() < key.length()) //allow for spaces between dashes by doubling the loop
		{
			hiddenkey.append("_ "); // build dashed "hidden" word WITH spaces
		}
		
		int lives = 4; //sample code starts with 4 lives
		int choice = 0;
		char guess;
		ArrayList alreadyGuessedCorrect = new ArrayList();
		ArrayList alreadyGuessedIncorrect = new ArrayList();
		
		
		//System.out.println(hiddenkey.length() + " and " + key.length());
		
		
		
		
		//start play
		
		while(lives > 0 && won == false)
		{
			System.out.println("So far, the word is: " + hiddenkey);
			System.out.println("You have " + lives + " incorrect guesses left.");
			System.out.print("Enter either 1 for guessing or 2 for hint: ");
			choice = keyboard.nextInt();
			
				if(choice == 1)
				{
					//guess
					System.out.print("Enter your guess: ");
					guess = keyboard.next().toCharArray()[0]; //takes string from user, converts to char array and places at index 0
					
					
						if(key.indexOf(guess) > -1) //guess is correct (indexOf returns the location of found item so if it is anywhere 0-length it is in the word)
						{
							if(!alreadyGuessedCorrect.contains(guess) && !alreadyGuessedIncorrect.contains(guess)) //it hasn't been guessed already
							{
								System.out.println("That's right! " + guess + " is in the word.");
								alreadyGuessedCorrect.add(guess);
								
									for(int i = 0; i < key.length(); i++)
									{
										
										if(guess == keyArray[i]) //if the guess is in spot i of the word
										{
											hiddenkey.setCharAt(i, guess); //set the corresponding spot in the dashed substitute
										}
									}
									
										
									for(int i = 0; i < hiddenkey.length(); i++)
									{
									
										if(hiddenkey.toString().indexOf("_") > -1)
										{
											won = false;
										}
										
										else 
										{
											won = true;
										}
									}
							}
							
							else // redundant correct letter
							{		
								System.out.println("Not valid input. You already guessed " + guess + ".");
								//if you enter a right letter again, you don't lose a life
							}
						}
						
						else //guess is wrong
						{
							
							
							if(alreadyGuessedIncorrect.contains(guess))
							{
								System.out.println("Not valid input. You already guessed " + guess + ".");
								lives--; //If you enter a wrong letter again, you still lose a life
							}
							
							else
							{
								System.out.println("Sorry, " + guess + " isn't in the word.");
								lives--;
							}
							
							alreadyGuessedIncorrect.add(guess); //add it to alreadyGuessedIncorrect list
						}
					
					
				}
			
				else if(choice == 2)
				{
					//hint
					//the instructions did not specify whether a user could use a hint on the last turn.
					//to avoid any unexpected errors, in this program
					//users can win on a hint.
					
					
					int firstdash = 0;
					firstdash = hiddenkey.toString().indexOf("_"); //finds the leftmost spot with a dash (index)
					char hint = keyArray[firstdash]; // the hint is the corresponding spot in the real array
					 
					hiddenkey.setCharAt(firstdash, hint);
					//System.out.println(firstdash);
					System.out.println("OK! The hint is " + hint);
					
					
					lives--;
					System.out.println("But since you used the hint, you can guess " + lives + " more times.");
					
					for(int i = 0; i < hiddenkey.length(); i++)
					{
					
						if(hiddenkey.toString().indexOf("_") > -1)
						{
							won = false;
						}
						
						else 
						{
							won = true;
						}
					}
					
					
					
					
					
					
				}
				
				else 
				{
					System.out.println("Invalid Input");
				}
			
			
			System.out.println();
		}
		
		//end game
		
		if(won == true)
		{
			System.out.println("Congratulations! The word was " + key);
		}
		
		else
		{
			System.out.println("You failed. The word was " + key);
		}
		
		
		
		
	}


}

