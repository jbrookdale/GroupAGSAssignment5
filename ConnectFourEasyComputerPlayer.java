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
        // Tests go here
    	
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
       return movePosition;
    }
    
	/**< constant for the size of the board in x direction */ 
	private static final int WIDTH = 10; 
	
	/**< constant for the size of the board in y direction */ 
	private static final int HEIGHT = 7; 
	/**Variable to store Generated column no */
	private static int m_columnNum;
	
}
