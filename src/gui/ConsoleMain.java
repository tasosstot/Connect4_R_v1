package gui;


// exception for the class "Scanner"
import java.util.InputMismatchException;
// import java.util.Random;
import java.util.Scanner;

import connectfour.Board;
import connectfour.MiniMaxAi;
import connectfour.Move;
import utilities.Constants;


public class ConsoleMain {
	
	static final int numOfColumns = Constants.NUM_OF_COLUMNS;
	static final int inARow = Constants.IN_A_ROW;
	
	public static void main(String[] args) {
		
		String validNumbers = "";
		for (int i=0; i<numOfColumns; i++) {
			if (i < numOfColumns-2) {
				validNumbers += i+1 + ", ";
			} else if (i == numOfColumns-2) {
				validNumbers += i+1 + " or ";
			} else if (i == numOfColumns-1) {
				validNumbers += i+1;
			}
		}
		
		Board connectfourBoard = connectfourBoard();
		// We create the AI computer player "O" and the Connect-N board.
        // The "maxDepth" for the MiniMax algorithm is set to 3.
		// Feel free to change the values.
		// The bigger the value of "maxDepth" is, the more difficult the game is. 
		int XColumnPosition;
		int maxDepth = 3;
		MiniMaxAi OPlayer = new MiniMaxAi(maxDepth, Constants.P2);
		

        // Uncomment this, for "O" to play first
		// board.setLastLetterPlayed(Board.X);

		System.out.println("Minimax Connect-" + inARow + "!\n");
		System.out.println("\n*****************************");
		Board.printBoard(connectfourBoard.getGameBoard());
		System.out.println();
		
    	Scanner in = new Scanner(System.in);
        // While the game has not finished
		while(!connectfourBoard.checkForGameOver()) {
			switch (connectfourBoard.getLastPlayer()) {
			
					
                // If "O" played last, then "X" plays now.
				// "X" is the user-player
				case Constants.P2:
                    System.out.print("Human 'X' moves.");
                    try {
        				do {
        					System.out.print("\nGive column (1-" + numOfColumns + "): ");
        					XColumnPosition = in.nextInt();
        				} while (connectfourBoard.checkFullColumn(XColumnPosition-1));
        			} catch (ArrayIndexOutOfBoundsException e){
        				System.err.println("\nValid numbers are: " + validNumbers + ".\n");
        				break;
        			} catch (InputMismatchException e){
        				System.err.println("\nInput an integer number.");
        				System.err.println("\nValid numbers are: " + validNumbers + ".\n");
        				break;
        			}
					connectfourBoard.makeMove(XColumnPosition-1, Constants.P1);
					System.out.println();
					break;
					
                // If "X" played last, then "O" plays now.
				// "O" is the AI computer
				case Constants.P1:
                    System.out.println("AI 'O' moves.");
                    
                    // Make MiniMax move.
					Move OMove = OPlayer.miniMax(connectfourBoard);
					
					// Make a random move.
					// Random r = new Random();
					// int randomNum = r.nextInt(numOfColumns);
					// connectNBoard.makeMove(randomNum, Constants.O);

					connectfourBoard.makeMove(OMove.getColumn(), Constants.P2);
					System.out.println();
					break;
					
				default:
					break;
			}
			System.out.println("Turn: " + connectfourBoard.getTurn());
			Board.printBoard(connectfourBoard.getGameBoard());
		}
		in.close();
		
		System.out.println();

		if (connectfourBoard.getWinner() == Constants.P1) {
			System.out.println("Human player 'X' wins!");
		} else if (connectfourBoard.getWinner() == Constants.P2) {
			System.out.println("AI computer 'O' wins!");
		} else {
			System.out.println("It's a draw!");
		}
		
		System.out.println("Game over.");
				
	}

	private static Board connectfourBoard() {
		int XColumnPosition;
		int maxDepth = 3;
		MiniMaxAi OPlayer = new MiniMaxAi(maxDepth, Constants.P2);
		Board connectfourBoard = new Board();
		Scanner in = new Scanner(System.in);
		while (!connectfourBoard.checkForGameOver()) {
			switch (connectfourBoard.getLastPlayer()) {
			case Constants.P2:
				do {
					XColumnPosition = in.nextInt();
				} while (connectfourBoard.checkFullColumn(XColumnPosition - 1));
				connectfourBoard.makeMove(XColumnPosition - 1, Constants.P1);
				break;
			case Constants.P1:
				Move OMove = OPlayer.miniMax(connectfourBoard);
				connectfourBoard.makeMove(OMove.getColumn(), Constants.P2);
				break;
				
			}
		}
		return connectfourBoard;
	}
	
}
