/**
* @file OthelloEasyComputerPlayer.java
* @author 
* @date  21st Mar 2014
* @see
*
* @brief This is a OthelloEasyComputerPlayer inherited from the EasyComputerPlayer
*        which generate a easy move for the computer player in Othello.
*		
* This class provides a random position among all the empty point with a valid move.
*
*/

import java.awt.Point;
import java.util.Random;

class OthelloEasyComputerPlayer <C> extends EasyComputerPlayer{
    
    public OthelloEasyComputerPlayer(String newName, C newPlayerColour) {
		super(newName, newPlayerColour);
		
	}
    
    
    /**
	* 
	* @param board - Othello board which provided all the information in the OthelloBoard class
	* @param colour -  The board which contains all the pieces for the current game.
	* @return m_movePosition -  return a move position point.        
	* 
	*/
    
    public Point makeAIMove(OthelloBoard board){
        Random r = new Random( );

        m_columnNum = r.nextInt(WIDTH);
        m_rowNum = r.nextInt(HEIGHT);
        OthelloPiece piece;
        if (getColour() == Piece.OthelloPieceColour.BLACK) {
            piece = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
        } else {
            piece = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
        }
        
        while (!board.anyMove(m_columnNum, m_rowNum,piece)) {
        	m_columnNum = r.nextInt(WIDTH);
        	m_rowNum = r.nextInt(HEIGHT);
        }
         System.out.println(m_columnNum);
        Point m_movePosition = new Point(m_columnNum, m_rowNum);
        return m_movePosition;
    }
    
    public static void main(String[] args) {
        // Tests go here
    }
	
	/**< constant for the size of the board in x direction */ 
    private static final int WIDTH = 8; 
    
    /**< constant for the size of the board in y direction */ 
    private static final int HEIGHT = 8; 
    
    private static int m_columnNum;
    private static int m_rowNum;
}
