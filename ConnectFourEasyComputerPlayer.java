import java.util.Arrays;
import java.util.Random;
import java.awt.Point;
/**
* @file ConnectFourHardComputerPlayer.java
* @author Godfrey Damabel 
* @date 26 March 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition,for Random class
*
* @brief This class implements the Connect Four easy Computer player moves
*
* 
* This class handles the easy Computer player. 
* It makes a random valid moves if such move exist.
*/

class ConnectFourEasyComputerPlayer <C> extends EasyComputerPlayer {

	 /**
     * This is the Constructor for the ConnectFourHardComputerPlayer class.
     * @param newName -The name of player
     * @param newPlayerColour -The Piece colour of the player
     *
     */
    public ConnectFourEasyComputerPlayer(String newName, C newPlayerColour) {
		super(newName, newPlayerColour);
	}

    /**
     * This is the main method of the ConnectFourHardComputerPlayer class
     * Runs various test on the class.
     */
	public static void main(String[] args) {
		/**
		* This is to test that Connect Four game works with Computer Easy implementation
		* This tests that the name, colour and score for each player can be retrieved 
		*/
		final int TEST_PLAYER_SCORE = 2;
		final int TEST_PLAYER2_SCORE = 4;
		        
		    ConnectFourPiece player1Piece;
		    player1Piece = new ConnectFourPiece(ConnectFourPiece.ConnectFourPieceColour.RED);
		    ConnectFourEasyComputerPlayer player1 =
		            new ConnectFourEasyComputerPlayer("Computer",player1Piece.getPieceColour());
		    System.out.println("Player Expected name " + "Computer");
		    System.out.println("Player1: " +player1.getName());
		    System.out.println("Player Expected colour " + "RED");
		    System.out.println("Colour: " +player1.getColour());
		    System.out.println("Player Expected Score " + "0");
		    System.out.println("score: " +player1.getScore());
		    player1.setScore(TEST_PLAYER_SCORE);
		    System.out.println("Player Expected Score " + "2");
		    System.out.println("Change score: " + player1.getScore());
		    
		    /**
			* This is to test that Connect Four game works with Computer Easy implementation
			* This tests that the name, colour and score for each player can be retrieved 
			*/
			    ConnectFourPiece player2Piece;
			    player1Piece = new ConnectFourPiece(ConnectFourPiece.ConnectFourPieceColour.YELLOW);
			    ConnectFourEasyComputerPlayer player2 =
			            new ConnectFourEasyComputerPlayer("Computer",player1Piece.getPieceColour());
			    System.out.println("Player Expected name " + "Computer");
			    System.out.println("Player2: " +player2.getName());
			    System.out.println("Player Expected colour " + "YELLOW");
			    System.out.println("Colour: " +player2.getColour());
			    System.out.println("Player Expected Score " + "0");
			    System.out.println("score: " +player2.getScore());
			    player2.setScore(TEST_PLAYER2_SCORE);
			    System.out.println("Player Expected Score " + "4");
			    System.out.println("Change score: " + player2.getScore());
		    
		    /** Test to ensure random move works */
		    ConnectFourBoard board = new ConnectFourBoard(WIDTH, HEIGHT);
		    ConnectFourEasyComputerPlayer<Object> compAI = new ConnectFourEasyComputerPlayer("CompAI",Piece.ConnectFourPieceColour.YELLOW);
	        /** < show the move of the hard computer player  */
	        Point x = compAI.makeAIMove(board);
	        System.out.println("Expected position corresponding to column " + x.y + " row " +x.x);
	        System.out.println("ConectFourHard Computer move: " + "y=" +x.y + "  " + "x=" +x.x);;
	        
		    
    	
			}
	/**
	 * This method makes the computer random valid moves.
	 * @param board -Connect Four Board
	 * @return movePosition- Position on the board to drop piece.
	 */
    public Point makeAIMove(ConnectFourBoard board){
        Random r = new Random();
        Point movePosition;
        /** Stores a generated value corresponding to the column to drop piece */
        m_columnNum = r.nextInt(WIDTH);
        while(!(board.getLowestEmptySlot(m_columnNum) < HEIGHT)) {
        	m_columnNum = r.nextInt(WIDTH);
            System.out.println(board.getLowestEmptySlot(m_columnNum));
        }
        System.out.println(m_columnNum);
        movePosition = new Point(m_columnNum,HEIGHT-1);
        System.out.println("The Random move made " + movePosition);
       return movePosition;
    }
    
	/**< constant for the size of the board in x direction */ 
	private static final int WIDTH = 10; 
	
	/**< constant for the size of the board in y direction */ 
	private static final int HEIGHT = 7; 
	/**Variable to store Generated column no */
	private static int m_columnNum;
	
}
