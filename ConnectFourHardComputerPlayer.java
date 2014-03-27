import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
* @file ConnectFourHardComputerPlayer.java
* @author Godfrey DAMABEL, JON BAILEY 
* @date 26 March 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition,for Random class and Loops
*
* @brief This class implements the Connect Four Hard Computer player moves
*
* 
* This class handles the Hard Computer player. It extends HardComputerPlayer class and implement the Connect Four Computer moves. The move made extends the 
* longest chain(horizontal, vertical or diagonal) on the board by one.
* It randomly picks one if many of such chain exist.
*/

class ConnectFourHardComputerPlayer <C> extends HardComputerPlayer {

	/** Height of the Connect Four Board */
    private static int HEIGHT = 7;
    /** Width of the Connect Four Board */
    private static int WIDTH = 10;
    /** One position to the left of Connect Four piece on board in X direction*/
    private final static int NEGATIVE_X_AXIS = -1;
    /** One position to the Right of Connect Four piece on board in X direction */
	private final static int POSITIVE_X_AXIS = 1;
	/** One position to the left of Connect Four piece on board in Y direction */
	private final static int NEGATIVE_Y_AXIS = -1;
	/** One position to the right of Connect Four piece on board in Y direction */
	private final static int POSITIVE_Y_AXIS = 1;
	
	  /**
     * This is the Constructor for the ConnectFourHardComputerPlayer class.
     * @param newName -The name of player
     * @param newPlayerColour -The Piece colour of the player
     *
     */
    public ConnectFourHardComputerPlayer(String newName, C newPlayerColour) {
 		super(newName, newPlayerColour);
 	}
    /**
     * This is the main method of the ConnectFourHardComputerPlayer class
     * Runs various test on the class.
     */
	public static void main(String[] args) {
		final int TEST_SCORE = 2;
		ConnectFourPiece player1Piece;
	        player1Piece = new ConnectFourPiece(ConnectFourPiece.ConnectFourPieceColour.RED);
	        ConnectFourHardComputerPlayer player1 = new ConnectFourHardComputerPlayer("Computer",player1Piece.getPieceColour());
	        System.out.println("Player1: " +player1.getName());
	        System.out.println("Colour: " +player1.getColour());
	        System.out.println("score: " +player1.getScore());
	        player1.setScore(TEST_SCORE);
	        System.out.println("Change score: " +player1.getScore());
	        
	        /** Test to ensure random move works */
		    ConnectFourBoard board = new ConnectFourBoard(WIDTH, HEIGHT);
		    ConnectFourHardComputerPlayer<Object> compAI = new ConnectFourHardComputerPlayer("CompAI",Piece.ConnectFourPieceColour.YELLOW);
	        /** < show the move of the hard computer player  */
	        Point x = compAI.makeAIMove(board);
	        System.out.println("Expected position corresponding to column " + x.y + " row " +x.x);
	        System.out.println("ConectFourHard Computer move: " + "y=" +x.y + "  " + "x=" +x.x);;
	        
	        /** Checks if Maximum chain exist on empty board */
	        int y =compAI.MaximumChain(WIDTH-1, HEIGHT-1, board);
	        System.out.println("Expected output 0");
	        System.out.println(y);
    }
    
    
	 /**
	   * This method makes the computer valid moves on the longest chain of pieces on the board.
	   * @param board -Connect Four Board
	   * @return - Position on the board to drop piece
	   */
    	  public Point makeAIMove(ConnectFourBoard board) {
    		  Random r = new Random();
    	      int maximum = 0;
    	      ArrayList<Point> m_MaxChoice = new ArrayList<Point>();
    	      int arrayPosition;
    	      /** Set maximum to be the maximum chain length of piece on board */ 
    	      for (int i = 0; i < WIDTH; i++) {
    	           if (MaximumChain(i,board.getLowestEmptySlot(i), board) > maximum) {
                   maximum = MaximumChain(i,board.getLowestEmptySlot(i), board);
  	               }
    	      }
    
    	      /** If more maximum chain length exist store all in an array */
    	      for (int i = 0; i < WIDTH; i++) {
    	            	// checking if more than one maximum chain exist
                   if (MaximumChain(i,board.getLowestEmptySlot(i), board) == maximum) {
                   m_MaxChoice.add(new Point(i,HEIGHT));
    	           }
    	      }
    
    	      arrayPosition= r.nextInt(m_MaxChoice.size());
    	      return m_MaxChoice.get(arrayPosition);
    	  }
    	  
    /**
     * This method scan through the board in all direction searching for the maximum
     * chain length
     * 
     * @param x - Horizontal Coordinate of the board
     * @param y - Vertical Coordinate of the board
     * @param board - ConnectFourBoard
     * @return chainLength - The maximum chain length of piece on the board
     */      
      public int MaximumChain(int x, int y, ConnectFourBoard board) {
          int m_chainLength = 0;
          Piece m_Pieces[][] = board.getPieces();
          /*If there is not a piece at position (x,y) then work out long a chain it connects to */
        if (m_Pieces[x][y].getPieceColour() == ConnectFourPiece.ConnectFourPieceColour.NONE) {
        	/** These is directions on a graph in the x-axis */
        	 for (int i = NEGATIVE_X_AXIS; i <= POSITIVE_X_AXIS; i++) {
        		 /** These is directions on a graph in the y-axis */
                 for (int j = NEGATIVE_Y_AXIS; j <= POSITIVE_Y_AXIS; j++) { // These signify directions on a graph in the y-axis
                    int counter = 1;
                    
                    if ((x+(i*counter)) >= 0 && (x+(i*counter)) < WIDTH && (y+(j*counter)) >= 0 && (y+(j*counter)) < HEIGHT) {
                        /*  Loop while pieces are the players colour. Add 1 to counter for each iteration. */
                        while (((x+(i*counter)) >= 0) && ((x+(i*counter)) < WIDTH) && ((y+(j*counter)) >= 0) && ((y+(j*counter)) < HEIGHT)
                                && m_Pieces[x+(i*counter)][y+(j*counter)].getPieceColour() == getColour()) {
                            counter++;
                        }
                    }

                        if (!((x+(i*counter)) == x && (y+(j*counter)) == y)) {
                                if ((counter - 1) > m_chainLength) {
                                	m_chainLength = (counter - 1);
                                }
                        }
                }
            }
        }
        
        return m_chainLength;
        }
}
      
