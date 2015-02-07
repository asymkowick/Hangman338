
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Hangman {
	Scanner keyboard = new Scanner(System.in);
	private int lives;
	private String status;
	private String keyword;
	private StringBuilder dashes;
	private char[] keyArray;
	private ArrayList<Character> trash = new ArrayList<Character>();
	

	
	public Hangman() {//constructor
		this.lives = 4; //start each new game with 4 lives
		this.keyword = "";
	}
	
	
	public void openFile() throws Exception {
		String filename;
		System.out.print("Enter a file name: ");
		filename = keyboard.nextLine();  
		BufferedReader reader = new BufferedReader(new FileReader(filename)); //open from file
		this.keyword = reader.readLine();
	}
	
	public void setupGame() {
		this.keyword = keyword.replace("",  " ").trim();
		this.dashes = new StringBuilder(keyword.length());
		while(dashes.length() < keyword.length())
		{
			dashes.append("_ ");
		}
		
		keyArray = keyword.toCharArray();
	}
	
	
	public void startGame() {
		this.status = "play";
		
		System.out.println("Welcome to Hangman!");
		while(!gameOver())
		{
			displayDashes();
			displayLivesRemaining();
			getUserChoice();
			System.out.println();
		}
		
		
	}
	
	public void displayDashes() {
		System.out.println("So far, the word is: " + dashes);
	}
	
	public void displayLivesRemaining() {
		System.out.println("You have " + this.lives + " lives left.");
	}
	
	
	public void getUserChoice() {
		System.out.print("Press 1 for Guess or 2 for Hint: ");
		int choice = keyboard.nextInt();
		if(choice == 1) getUserGuess();
		else displayHint();
	}
	
	
	public void getUserGuess() {
		System.out.print("Enter your guess: ");
		char guess = keyboard.next().toCharArray()[0];
		
		if(keyword.indexOf(guess) > -1) correctGuess(guess);
		else incorrectGuess(guess);
	}
	
	
	public void correctGuess(Character guess) {
		
		if(trash.contains(guess)) 
			{
				alreadyGuessed();
			}
		else
		{
			trash.add(guess);
			System.out.println("Correct! " + guess + " is in the word.");
			updateDashes(guess);
			
			for(int i = 0; i < dashes.length(); i++)
			{
				if(dashes.toString().indexOf("_") < 0)
				{
					status = "won";
				}
			}
		}
		
		
	}
	
	public void incorrectGuess(Character guess) {
		System.out.println("Sorry, " + guess + " isn't in the word.");
		trash.add(guess);
		lives--;
	}
	
	
	public void updateDashes(Character guess) {
		for(int i = 0; i < keyword.length(); i++)
		{
			if(guess == keyArray[i]) //if the guess is in spot i of the word
			{
				dashes.setCharAt(i, guess); //set the corresponding spot in the dashed substitute
			}
		}
	}
	
	public void displayHint() {
		int firstdash = 0;
		firstdash = dashes.toString().indexOf("_"); //finds the leftmost spot with a dash (index)
		char hint = keyArray[firstdash]; // the hint is the corresponding spot in the real array
		dashes.setCharAt(firstdash, hint);
		System.out.println("OK! The hint is " + hint);
		lives--;
		System.out.println("But since you used the hint, you can guess " + lives + " more times.");
		
		for(int i = 0; i < dashes.length(); i++)
		{
			if(dashes.toString().indexOf("_") < 0)
			{
				status = "won";
			}
		}
	}
	
	
	public void alreadyGuessed() {
		System.out.println("Oops, you already guessed that word. (No Lives Lost)");
		//might need stuff here
	}
	
	public int updateLives() {
		lives--;
		if(lives == 0) status = "lost";
		return lives;
		
	}
	
	public boolean gameOver() {
		if(status == "play" && lives > 0) return false;
		else if(status == "won")
			{
				youWin(); return true;
			}
		else 
			{
				youLose(); return true;
			}
	}
	
	public void youLose() {
		System.out.println("You failed! The word was  " + keyword);
	}
	
	public void youWin() {
		System.out.println("Congratulations! You won! " + keyword);
	}
	
}