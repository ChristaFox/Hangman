
//Kiley Holman
//A01Hangman
//CSIS 2450-003
//9/5/13
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Hangman
{

	public static void playHangman2()
	{

		Scanner kb = new Scanner(System.in);
		String word = "software"; 
		char wordLetters[] = word.toCharArray();//creates the row of lines and puts them in a array
		String lines = "";
		int w1 = word.length()+1;
		for(int i=1; i<w1; i++)
		{
			lines += "- ";

		}//end for
		char lineLetters[] = lines.toCharArray();

		int wrongCount = 0;
		String guessedLetters = "";
		char[] lineLetters2 = new char[wordLetters.length];

		while( wrongCount <= 5 )
		{
			for(int i=0; i<lineLetters2.length; i++)//creates an array with lines without the spaces. 
			{
				lineLetters2[i] = lineLetters[i*2];
			}//end for 

			String n1 = new String(lineLetters);
			String n2 = new String(wordLetters);//Reassembles array into String
			String n3 = new String(lineLetters2);

			if( n3.equals(n2))//check to see if word has been fully guessed. 
			{
				win();
				return;// fixes return to menu 
			}//end if
			
			System.out.println(gallows(wrongCount));
			System.out.printf("\nNumber of worng guesses: %s\n WORD TO GUESS:   %s\n Letters guessed: %s\n", wrongCount,n1,guessedLetters, gallows(wrongCount));//prints out console during play
			String guess;//creates guess
			System.out.print("Choose a letter to guess:");//prompt
			guess = kb.next();//input from user
			

			if(guess.equals("1"))//exit to menu 
			{
				break;
			}//end if
			else
			{       	
				if( guessedLetters.contains(guess) )//Checks for duplicate guess 
				{
					System.out.println("You have already guessed that letter");
					continue;
				}//end if 

				if( word.contains(guess) )//checks to see if letter is in word
				{
					int w2 = word.length();
					for(int i = 0; i < w2; i++)
					{
						char aChar = guess.charAt(0);
						char bChar = wordLetters[i];
						if(bChar == aChar)
							lineLetters[i*2] = aChar;
					}//end for
					System.out.println("Yes! This letter is in the word");
				}//end if 
				else
				{
					System.out.println("Wrong!");
					wrongCount++;
				}//end else

				guessedLetters += guess.charAt(0);//adds players guess into a pool. ensures only the first letter is posted in guessed letters 

				if( wrongCount == 6 )
					lose(wrongCount, word);
			}//end else
		}//end while 
	}//end playHangman

	private static String gallows(int wrongCount)//gallows graphic
	{
		String gallows = "";
		if( wrongCount == 0)
			gallows = String.format("____\n|             \n|            \n|             \n_______\n\n");
		if( wrongCount == 1 )
			gallows = String.format("____\n|  'o    \n|          \n|         \n______\n\n");
		if( wrongCount == 2 )
			gallows = String.format("____\n|  'o    \n|   |     \n|         \n|______\n\n");
		if( wrongCount == 3 )
			gallows = String.format("____\n|  'o    \n|  /|     \n|         \n|______\n\n");
		if( wrongCount == 4 )
			gallows = String.format("____\n|  'o    \n|  /|\\  \n|          \n|______\n\n");
		if( wrongCount == 5 )
			gallows = String.format("____\n|  'o    \n|  /|\\  \n|  /       \n|______\n\n");
		if( wrongCount == 6 )
			gallows = String.format("____\n|  'o    \n|  /|\\  \n|  / \\   \n|______\n\n");
		return gallows;
	}
	

	public static void lose(int wrongCount, String word)
	{
		Scanner kbL = new Scanner(System.in);
		System.out.printf("%s\nSorry, You Lose! Your Life points are at %s\nThe word to guess was: %s!!!!\nEnter 1 to play again\nEnter 2 for menu\n",gallows(wrongCount), wrongCount,word);
		//shows the loser panel and prompts for replay
		kbL.next();
		if( kbL.equals("1"))
			playHangman2();
		else
			return;

	}//end lose

	public static void win()
	{
		Scanner kbW = new Scanner(System.in);
		System.out.printf("You WON!!!\nEnter 1 to play again\nEnter 2 for menu/exit\n");
		if( kbW.equals("1"))
			playHangman2();
		else
			return;

	}//end win
}//end PlayHangman
