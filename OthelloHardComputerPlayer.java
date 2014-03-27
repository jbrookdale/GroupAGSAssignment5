import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;

/**
* @file OthelloHardComputerPlayer.java
* @author Mabelle, Jon
* @date  24th Mar 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition,for Random class and Loops
*
* @brief This is a OthelloHardComputerPlayer inherited from the HardComputerPlayer
*        which generate a hard move for the computer player in Othello.
*		
* This class provides a hard computer move by choosing the position with the maximum 
* number of pieces that the move can flipped.
*
*/

class OthelloHardComputerPlayer <C> extends HardComputerPlayer {
    
	/**< constant for the size of the board in x direction */ 
	private final static int BOARD_WIDTH = 8;
	
	/**< constant for the size of the board in y direction */ 
	private final static int BOARD_HEIGHT = 8;
	
	/** < the negative directions on a graph in the x-axis  */
	private final static int NEGATIVE_X_AXIS = -1;
	
	/** < the positive directions on a graph in the x-axis  */
	private final static int POSITIVE_X_AXIS = 1;
	
	/** < the negative directions on a graph in the y-axis  */
	private final static int NEGATIVE_Y_AXIS = -1;
	
	/** < the positive directions on a graph in the y-axis  */
	private final static int POSITIVE_Y_AXIS = 1;
	
	
	/**
     * This is the Constructor for the OthelloHardComputerPlayer class.
     * @param newName -The name of player
     * @param newPlayerColour -The Piece colour of the player
     *
     */
	public OthelloHardComputerPlayer(String newName, Object newPlayerColour) {
		super(newName, newPlayerColour);
	}

    /**
	* 
	* @param board - Othello board which provided all the information in the OthelloBoard class.
	* @return m_BestFlips - the best move with the maximum flips.       
	* 
	*/
	
    public Point makeAIMove(OthelloBoard board) {
    	/** < store the maximum number of the pieces that are flipped  */
    	int m_MaximumFlips = 0;
    	
    	/** < store all the computer hard move point with the maximum piece flip number  */
        ArrayList<Point> m_BestFlips = new ArrayList<Point>();
        
        /** < scan the whole board and find the maximum number of the flipped pieces (m_MaximumFlips)  */
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (getFlipsForPosition(i,j, board) > m_MaximumFlips) {
                	m_MaximumFlips = getFlipsForPosition(i,j,board);
                }
            }
        }
        
        /** < scan the whole board store the point with the maximum flipped piece number 
         *    to the m_BestFlips ArrayList */
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (getFlipsForPosition(i,j, board) == m_MaximumFlips) {
                	m_BestFlips.add(new Point(i,j));
                }
            }
        }
        
        /** < choose a point from the m_BestFlips ArrayList randomly */
        Random r = new Random();
        int m_ArrayPosition = r.nextInt(m_BestFlips.size());
        return m_BestFlips.get(m_ArrayPosition);
    }
    
    
    /** 
    * This method returns how many pieces are flipped if the player places a piece at
    * position (x,y) on the board.
    * 
    * @param x - the x co-ordinate of the piece to count the flips for
    * @param y - the y co-ordinate of the piece to count the flips for
    * @param colour -  The board which contains all the pieces for the current game.
    * @return m_FlippedPieces - the maximum number of pieces that can be filpped
    *
    **/   
    
    private int getFlipsForPosition(int x, int y, OthelloBoard board) {
    	Piece.OthelloPieceColour playerColour;
    	if(getColour().equals("White")){
    		playerColour = Piece.OthelloPieceColour.WHITE;
    	}else
    	{
    		playerColour = Piece.OthelloPieceColour.BLACK;
    	}
        int m_FlippedPieces = 0;
        Piece m_Pieces[][] = board.getPieces();
        /** < If there is not a piece at position (x,y) then work out how many flips it causes */
        /** < Otherwise it doesn't flip anything. */
        if (m_Pieces[x][y].getPieceColour() == OthelloPiece.OthelloPieceColour.NONE) {
        	/** <  These signify directions on a graph in the x-axis */
        	for (int i = NEGATIVE_X_AXIS; i <= POSITIVE_X_AXIS; i++) { 
        		/** <  These signify directions on a graph in the y-axis */
                for (int j = NEGATIVE_Y_AXIS; j <= POSITIVE_Y_AXIS; j++) {
                    int counter = 1;
                    
                    if ((x+(i*counter)) >= 0 && (x+(i*counter)) < BOARD_WIDTH && (y+(j*counter)) >= 0 && (y+(j*counter)) < BOARD_HEIGHT) {
                        /** <  Loop while pieces are the opponents colour. Add 1 to counter for each iteration. */
                    	while ((x+(i*counter)) >= 0 && (x+(i*counter)) < BOARD_WIDTH && (y+(j*counter)) >= 0 && (y+(j*counter)) < BOARD_HEIGHT
                                && m_Pieces[x+(i*counter)][y+(j*counter)].getPieceColour() != OthelloPiece.OthelloPieceColour.NONE
                                && m_Pieces[x+(i*counter)][y+(j*counter)].getPieceColour() != playerColour) {
                            counter++;
                        }
                    }
                    

                    /** <  If the piece which stops the loop above is the players own colour, add the counter 
                     *     To the total number of flippied pieces, else don't add anything.   */
                    if ((x+(i*counter)) >= 0 && (x+(i*counter)) < BOARD_WIDTH && (y+(j*counter)) >= 0 && (y+(j*counter)) < BOARD_HEIGHT) {
                        if (!((x+(i*counter)) == x && (y+(j*counter)) == y)) {
                            if (m_Pieces[x+(i*counter)][y+(j*counter)].getPieceColour() == playerColour) {
                            	m_FlippedPieces += (counter - 1);
                            }
                        }
                    }
                }
            }
        }
        return m_FlippedPieces;
    }

    
    /**
    * 
    * Testing for OthelloHardComputerPlayer.
    * @param args -nil
    * 
    **/   
	public static void main(String[] args) {

         /**
	   * This is to test that Othello game works with hard computer player implementation
	   * This tests that the name, colour and score for each player can be retrieved 
	   */
	   final int TEST_PLAYER_SCORE = 2;

	   OthelloHardComputerPlayer player1Piece;
	   player1Piece = new OthelloHardComputerPlayer("computer", Piece.OthelloPieceColour.BLACK);
	   OthelloHardComputerPlayer player1 =
	   new OthelloHardComputerPlayer("Computer",player1Piece.getColour());
	   System.out.println("");
	   System.out.println("Player Expected name " + "Computer");
	   System.out.println("Player1: " +player1.getName());
	   System.out.println("Player Expected colour " + "BLACK");
	   System.out.println("Colour: " +player1.getColour());
	   System.out.println("Player Expected Score " + "0");
	   System.out.println("score: " +player1.getScore());
	   player1.setScore(TEST_PLAYER_SCORE);
	   System.out.println("Player Expected Score " + "2");
	   System.out.println("Change score: " + player1.getScore());
		
		/** < set two player   */
	OthelloHardComputerPlayer compAI = new OthelloHardComputerPlayer("CompAI",Piece.OthelloPieceColour.WHITE);
        OthelloGame game = new OthelloGame(compAI, new HumanPlayer("Mabelle",Piece.OthelloPieceColour.BLACK));
              
        /** < show how many pieces every single point can flip */
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
            	int y = compAI.getFlipsForPosition(i,j,game.m_board);
            	System.out.println("");
                System.out.println("point:(" + (i) + "," + (j) + "): " + "flips " + y + " pieces");
            }
        }
        
        /** < show the move of the hard computer player  */
        System.out.println("");
        System.out.println("Expected maximum pieces flipped position:");
        System.out.println("Random piont in {(2,3),(3,2),(4,5),(5,4)}");
        Point x = compAI.makeAIMove(game.m_board);
        System.out.println("Othello Hard ComAI move: " + x);
	}
}
