/*Author: Miguel Zavala
 
  Date: September 17 2014
  
  Purpose: This program takes imports a .txt file and sets up a jeopardy game with it
  
  Algorithm: 
  1.The board is displayed.
  2.The user is asked what row they would like to choose. If the user 
  chooses something that is out of the index then they are asked to choose again
  3.The user is asked what column they would like to choose. If the user 
  chooses something that is out of the index then they are asked to choose again
  4.The question is displayed
  5.The user enters their answer
  6.The user is asked if they would like to keep playing if yes then the game continues
  	if no then the game is stopped and the point totals are shown
  7.The games continues as shown above until the user does not want to play any more 
  	or if all of the questions have been asked.
*/

//import needed classes
import java.util.*;
import java.io.*;


//start of the main method
public class JeopadyGame 
{
	
	
	public static void main (String [] args)
	{
		//instantiate scanner
		Scanner scan = new Scanner(System.in);
		Scanner inputStream = null;
		
		//instantiate multidimensional array that holds the objects
		Jeopardy board[][] = new Jeopardy[5][5];
		
		//keepPlaying is used to see if the user wants to keep playing the game
		String keepPlaying = "yes";
		
		//userRow and userColumn holds the specific row and column the user wants to answer
		//pointTotal is used to keep the users point total throughout the game
		int userRow,userColumn,pointTotal = 0;
		int languages = 0, techniques = 0, years = 0, people = 0, general = 0;
		
		//userAnswer keeps the users answer to whether or not they want to keep playing
		String userAnswer;
		
		
		
		
		//try catch to see if the files exists
		try
		{
		inputStream = new Scanner(new FileInputStream("Test.txt"));
		inputStream.useDelimiter("\t|\\n");
		
		}
		
		//if the file was not found then this runs and the program exits
		catch(FileNotFoundException e)
		{
			System.out.println("File does not exist brah!");
			System.exit(0);
		}
		
		//reads the .txt file and creates objects using the Jeopardy class and populate the multidimensional array
		for(int column = 0  ; column < 5; column++)
		{
			for(int row = 0; row < 5; row++)
			{
				Jeopardy gameInsideLoop = new Jeopardy(inputStream.next(),inputStream.next(), inputStream.next(),inputStream.nextInt(), false,false);
				board[row][column] = gameInsideLoop;
			}
		}
		inputStream.close();
		
		
				
		System.out.println("Jeopardy Game\n");
		
		
		//displays the board
		for(int row = 0 ; row < 5; row++)
		{
			for (int column = 0; column < 5; column++) 
			{
				System.out.print(board[row][column].category + " "+ board[row][column].toString() + "\t\t");
			}
			
			System.out.println("");
		}
		
		System.out.println("\nThe game board is displayed.");
		System.out.println("Asnwers must be given in a question format or they will be incorrect!");
		
		//asks the user to enter a row they want to answer
		System.out.print("Choose a row to answer:");
		userRow = scan.nextInt();
		userRow -= 1;
		
		//this loop checks the range of the row the user enters. if its out of the bounds it asks
		//the user to keep entering a valid row range until one is put in
		if(userRow > 4 || userRow < 0)
		{
			do 
			{

				System.out.println("This is not a valid range!");
				System.out.println("\nPlease choose another row: ");

				userRow = scan.nextInt();
				userRow -= 1;

			} while (userRow > 4 || userRow < 0);
		}
		
		//asks the user to input a column to answer
		System.out.print("Choose a column to answer:");
		userColumn = scan.nextInt();
		scan.nextLine();
		userColumn-= 1;
		
		//this loop checks the range of the column the user enters. if its out of the bounds it asks
		//the user to keep entering a valid column range until one is put in
		if(userColumn > 4 || userColumn < 0)
		{
			do 
			{
				System.out.println("This is not a valid range!");
				System.out.print("Choose another column:");
				userColumn = scan.nextInt();
				scan.nextLine();
				userColumn -= 1;

			} while (userColumn < 0 || userColumn > 4);
		}
		
		//prints out the question of the row and column the user has picked
		System.out.println(board[userRow][userColumn].question);
		scan.reset();
		userAnswer = scan.nextLine();
		
		//this runs if the answer is correct
		if(userAnswer.toLowerCase().equals(board[userRow][userColumn].answer.toLowerCase()))
		{
			
			System.out.println("Correct!");
			
			//adds the points to the point total
			pointTotal+= board[userRow][userColumn].points;
			
			//changes the question from unasked to asked
			board[userRow][userColumn].setAsked(true);
			
			//changes the answer to correct
			board[userRow][userColumn].setCorrect(true);
			
			//prints out the point total
			System.out.println("The total number of points you have is "+pointTotal);
			
			
			//adds the points of the correct answer to the correct column variable
			switch (userColumn)
			{
			case 0: 
				languages += board[userRow][userColumn].points;
				break;
			
			case 1:
				techniques+= board[userRow][userColumn].points;
				break;
				
			case 2:
				years += board[userRow][userColumn].points;
				break;
				
			case 3:
				people += board[userRow][userColumn].points;
				break;
				
			case 4:
				general += board[userRow][userColumn].points;
				break;
			}
			
			
		}
		
		//this runs if the answer is incorrect
		else
		{
			//prints out the users answer
			System.out.println("You said: "+userAnswer.toLowerCase());
			
			//prints out the correct answer
			System.out.println("The correct asnwer is "+board[userRow][userColumn].answer.toLowerCase());
			
			//changes the question from unasked to asked
			board[userRow][userColumn].setAsked(true);
			
			//prints out the point total
			System.out.println("The total number of points you have is "+pointTotal);
			
		}
		
		
		//asks the user if they would like to keep playing
		System.out.println("\nDid you want to keep playing?");
		keepPlaying = scan.nextLine().toLowerCase();
		
		//if the user answers yes to whether or not they want to keep playing then this will run
		if(keepPlaying.equals("yes"))
		{
			do 
			{
				//this is just a space for aesthetics 
				System.out.println(" ");
				
				
				//this displays the board
				for (int row = 0; row < 5; row++) 
				{

					for (int column = 0; column < 5; column++) 
					{
						//if the user has not already answered the question this displays the normal board
						if(board[row][column].asked == false)
						{
						System.out.print(board[row][column].category + " "+ board[row][column].toString() + "\t\t");
						}
						
						//if the user has already answered the question this is displayed
						//where the category and point total is
						else
						{
							if(board[row][column].getCorrect() == true)
							{
								System.out.print("---Correct---" + "\t\t");
							}
							else
							{
								System.out.print("--Incorrect--" + "\t\t");
							}
						}
					}
					System.out.println("");

				}
				
				//asks the user to enter a row
				System.out.print("Choose a row to answer:");
				userRow = scan.nextInt();
				userRow -= 1;
				
				//checks to see if the row is of valid range
				if (userRow > 4 || userRow < 0) {
					do {

						System.out.println("This is not a valid range!");
						System.out.println("\nPlease choose another row: ");

						userRow = scan.nextInt();
						userRow -= 1;

					} 
					while (userRow > 4 || userRow < 0);
				}

				//asks user to enter a column
				System.out.print("Choose a column to answer:");
				userColumn = scan.nextInt();
				scan.nextLine();
				userColumn -= 1;
				
				//checks to see if the column is of valid range
				if (userColumn > 4 || userColumn < 0) 
				{
					do {
						System.out.println("This is not a valid range!");
						System.out.print("Choose another column:");
						userColumn = scan.nextInt();
						scan.nextLine();
						userColumn -= 1;

					} 
					while (userColumn < 0 || userColumn > 4);
				}

				//checks to see if the row and column the user has entered has already been asked
				//if it has then it asks the user to pick a different row and column
				if (board[userRow][userColumn].getAsked() == true) 
				{
					do 
					{
						
						System.out.println("You have already chosen this question!");
						
						//asks the user for another row
						System.out.println("\nPlease choose another row and point total to answer: ");

						userRow = scan.nextInt();
						userRow -= 1;
					
						//checks if the row is within valid range
						if (userRow > 4 || userRow < 0) 
						{
							do 
							{

								System.out.println("This is not a valid range!");
								System.out.println("\nPlease choose another row: ");

								userRow = scan.nextInt();
								userRow -= 1;

							} 
							while (userRow > 4 || userRow < 0);
						}

						//asks the user for another column
						System.out.print("Choose a column to answer:");
						userColumn = scan.nextInt();
						scan.nextLine();
						userColumn -= 1;
						
						//checks the range of the column
						if (userColumn > 4 || userColumn < 0) 
						{
							do 
							{
								System.out
										.println("This is not a valid range!");
								System.out.print("Choose another column:");
								userColumn = scan.nextInt();
								scan.nextLine();
								userColumn -= 1;

							} 
							while (userColumn < 0 || userColumn > 4);
						}

					} 
					while (board[userRow][userColumn].getAsked() == true);
				}

				//prints out the question the user has entered
				System.out.println(board[userRow][userColumn].question);
				scan.reset();
				userAnswer = scan.nextLine();
				
				//this runs if the answer is correct
				if (userAnswer.toLowerCase().equals(board[userRow][userColumn].answer.toLowerCase())) 
				{

					System.out.println("Correct!");
					
					//adds the points to the point total
					pointTotal += board[userRow][userColumn].points;
					
					//changes the question from unasked to asked
					board[userRow][userColumn].setAsked(true);
					
					//sets the users answer to correct
					board[userRow][userColumn].setCorrect(true);
					
					//prints out the point total
					System.out.println("The total number of points you have is "+ pointTotal);
					
					
					//adds the points of the correct answer to the correct column variable
					switch (userColumn)
					{
					case 0: 
						languages += board[userRow][userColumn].points;
						break;
					
					case 1:
						techniques+= board[userRow][userColumn].points;
						break;
						
					case 2:
						years += board[userRow][userColumn].points;
						break;
						
					case 3:
						people += board[userRow][userColumn].points;
						break;
						
					case 4:
						general += board[userRow][userColumn].points;
						break;
					}
					
					

				} 
				
				//this runs if the answer is incorrect
				else 
				{
					//prints the users answer
					System.out.println("You said: " + userAnswer.toLowerCase());
					
					//prints the correct answer
					System.out.println("The correct asnwer is "+ board[userRow][userColumn].answer.toLowerCase());
					
					//changes the question from unasked to asked
					board[userRow][userColumn].setAsked(true);
					
					//updates the point total
					System.out.println("The total number of points you have is "+ pointTotal);

				}
				
				//asks the user if they want to keep playing
				System.out.println("\nDid you want to keep playing?");
				keepPlaying = scan.nextLine().toLowerCase();
			}
			while (keepPlaying.equals("yes"));
		}
		
		//prints out the point total for each category and overall when the user no longer wants to play
		System.out.println("Thank you for playing. Your point total is: "+pointTotal);
		System.out.println("Total points for languages is: "+languages);
		System.out.println("Total points for techniques is: "+techniques);
		System.out.println("Total points for years is: "+years);
		System.out.println("Total points for people is: "+people);
		System.out.println("Total points for general is: "+general);
		
		
		
		
	}
}

//start of the jeopardy class 
class Jeopardy
{
	//instantiate the variables
	String question;
	String answer;
	String category;
	int points;
	boolean asked;
	boolean correct;
	
	
	//regular constructor
	Jeopardy()
	{	
		
	}
	
	
	//overloaded constructor
	Jeopardy(String question, String answer, String category, int points ,boolean asked,boolean correct)
	{
		this.question = question;
		this.answer = answer;
		this.category = category;
		this.points = points;
		this.correct = correct;
	}
	
	//sets the question
	public void setQuestion(String question)
	{
		this.question = question;
	}
	
	//gets the question
	public String getQuestion()
	{
		return question;
	}
	
	//sets the answer
	public void setAnswer(String answer)
	{
		this.answer = answer;
	}
	
	
	//gets the answer
	public String getAnswer()
	{
		return answer;
	}
	
	
	
	//sets the category
	public void setCategory(String category)
	{
		this.question = category;
	}
	
	//gets the category
	public String getCategory()
	{
		return category;
	}
	
	//sets the points
	public void setPoints(int points)
	{
		this.points = points;
	}
	
	//gets the points
	public int getPoints()
	{
		return points;
	}
	
	//sets asked
	public void setAsked(boolean asked)
	{
		this.asked = asked;
	}
	
	//gets asked
	public boolean getAsked()
	{
		return asked;
	}
	
	//sets correct
	public void setCorrect (boolean correct)
	{
		this.correct = correct;
	}
	
	//gets correct
	public boolean getCorrect()
	{
		return correct;
	}
	
	//to string 
	public String toString()
	{
		return +points+"";
	}
}







