import java.awt.Point;
import java.util.Random;

/**
* @file OthelloEasyComputerPlayer.java
* @author Damabel Godfrey
* @date  21st Mar 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition,for Random class
*
* @brief This is a OthelloEasyComputerPlayer it extends EasyComputerPlayer class
*        which generate a easy move for the computer player in Othello.
*		
* This class makes a random move in any of the points on Othello board.
*
*/

public class OthelloEasyComputerPlayer <C> extends EasyComputerPlayer{
	
	/**< constant for the size of the board in x direction */ 
    private static final int WIDTH = 8; 
    
    /**< constant for the size of the board in y direction */ 
    private static final int HEIGHT = 8; 
    
	/**< the column number of the computer move point  */ 
    private static int m_columnNum;
    
    /**< the row number of the computer move point  */ 
    private static int m_rowNum;
	
	/**
     * Constructor calls the player's name and the player piece colour
     * @param newName -The name of the player
     * @param newPlayerColour -The colour of the player
     */
    public OthelloEasyComputerPlayer(String newName, C newPlayerColour) {
		super(newName, newPlayerColour);
		
	}
    
    /**
	* 
	* @param board - Othello board which provided all the information in the OthelloBoard class
	* @return m_movePosition -  return a move position point.
	*/
    
    public Point makeAIMove(OthelloBoard board){
       
    	Random r = new Random( );
    	
    	/**< define the player colour  */ 
    	Piece.OthelloPieceColour playerColour;
    	if(getColour().equals("White")){
    		playerColour = Piece.OthelloPieceColour.WHITE;
    	}else
    	{
    		playerColour = Piece.OthelloPieceColour.BLACK;
    	}
    	
    	/**< get a random computer move point */ 
        m_columnNum = r.nextInt(WIDTH);
        m_rowNum = r.nextInt(HEIGHT);
        
        /**< check the colour of the current piece or player  */ 
        OthelloPiece piece;
        if (playerColour == Piece.OthelloPieceColour.BLACK) {
            piece = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
        } else {
            piece = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
        }
        
        /**< if there is not a valid move at the current random computer move
         *   choose another random point for the easy computer player */ 
        while (!board.anyMove(m_columnNum, m_rowNum,piece)) {
        	m_columnNum = r.nextInt(WIDTH);
        	m_rowNum = r.nextInt(HEIGHT);
        }
        Point m_movePosition = new Point(m_columnNum, m_rowNum);
        return m_movePosition;
    }
    
    
    
    /**
     * Testing for OthelloEasyComputerPlayer.
     * @param args -nil
     */
    public static void main(String[] args) {
    	/** Test to ensure random move works */
		/** < set two player   */
	OthelloEasyComputerPlayer compAI = new OthelloEasyComputerPlayer("CompAI",Piece.OthelloPieceColour.WHITE);
        OthelloGame game = new OthelloGame(compAI, new HumanPlayer("Mabelle",Piece.OthelloPieceColour.BLACK));
              
        /** < show the move of the easy computer player  */
        System.out.println("Expected Random position corresponding to point x");
        Point x = compAI.makeAIMove(game.m_board);
        System.out.println("Othello easy ComAI move: " +"x="+ x.x + " y=" + x.y);
        
        
      /**
	* This is to test that Othello game works with Othello Computer Easy implementation
	* This tests that the name, colour and score for each player can be retrieved 
	*/
	final int TEST_PLAYER_SCORE = 2;
		        
	OthelloEasyComputerPlayer player1Piece;
	player1Piece = new OthelloEasyComputerPlayer("computer", Piece.OthelloPieceColour.BLACK);
	OthelloEasyComputerPlayer player1 =
		              new OthelloEasyComputerPlayer("Computer",player1Piece.getColour());
	System.out.println("Player Expected name " + "Computer");
	System.out.println("Player1: " +player1.getName());
	System.out.println("Player Expected colour " + "BLACK");
	System.out.println("Colour: " +player1.getColour());
	System.out.println("Player Expected Score " + "0");
	System.out.println("score: " +player1.getScore());
	player1.setScore(TEST_PLAYER_SCORE);
	System.out.println("Player Expected Score " + "2");
	System.out.println("Change score: " + player1.getScore());
    }
}
