/**
* @file    -ConnectFourGame.java
* @author  -Shannon Gahring, Kristoffer Page
* @date    -17 Feb '14
* @see     -Game.java for inherited methods
*
* @brief  -This class controls the order of the game, interactions between
* Player and Board.  It tracks time and keeps the flow of the 
* game going.  It switches off between players and contacts
* board to make sure the move is valid
*
*/

public class ConnectFourGame extends Game<Piece.ConnectFourPieceColour> {

	private ConnectFourBoard m_board;

    /**
     * Gets the lowest empty tile on the board when given a column.
     * @param column - an integer representing the column
     * @return rowNum - the lowest empty position on the board if successful
     */
    public int getLowestEmptySlot(int column) {
        return m_board.getLowestEmptySlot(column);
    }

    /**
     * Gets a 2D array of pieces representing the board.
     * @return A 2D array containing the position of pieces on the board.
     */
	public ConnectFourPiece[][] getPieces() {
		return m_board.getPieces();
	}

    /**
     * Gets the board object containing all the pieces.
     * @return The board object containing all the pieces.
     */
    public ConnectFourBoard getBoard() {
        return m_board;
    }
	
	/**
	* Constructor for ConnectFourGame. This is used to instantiate 
	* integers if we want it to.
	* @param n1 -an integer
	* @return TRUE if successful
	*/
	public ConnectFourGame(Player playerOne, Player playerTwo) {
	
        super(playerOne, playerTwo);

        m_board = new ConnectFourBoard(ConnectFourBoard.WIDTH, 
        	ConnectFourBoard.HEIGHT);

	}
	
	/**
	 * This method alternates from one player to the other.
	 * It also checks to see if someone has won the game
	 * and if the move is valid.  
	 */
	public boolean gameLoop() {
		return false;
	}
	
	/**
	 * This method gets data from the GUI on the coordinates of the
	 * move made and the colour.  It sends this data to the board
	 * to check if the board is full and if there has been a winner.
	 * If these two pass then it says that it is a valid move.
	 * 
	 * @param x -the x coordinate from GUI
	 * @param y -the y coordinate from GUI
	 * @param colour -the colour of the piece played
	 * 				  this shows whose turn it is
	 */
	public boolean move(int x, int y, Piece.ConnectFourPieceColour colour) {
		 return m_board.setPiece(x, y, colour);
	}

    /**
    * A method to check if the game has been won.
    * @return TRUE if the game has been won.
    */
	public boolean gameWon() {
		return m_board.checkWin();
	}

    /**
    * A method to check if the Board is filled completed with pieces.
    * @return TRUE if the board is full.
    */
	public boolean boardIsFull() {
		return m_board.isFull();
	}

	public static void main (String[] args){
        ConnectFourGame g = new ConnectFourGame(
                                new HumanPlayer("Jon",
                                    Piece.ConnectFourPieceColour.RED),
                                new HumanPlayer("Joey",
                                    Piece.ConnectFourPieceColour.YELLOW));
                                    
        final int WIDTH = 10;
        final int HEIGHT = 7;
        final int COLUMN_TWO = 1;
        
		System.out.println("\nConnectFourGame Tests:\n");
        System.out.println("ConnectFourGame.boardIsFull() - Begin");
        System.out.println("Expected output: false");
        System.out.println("");
        System.out.println("Actual output: "
                                   + g.boardIsFull());
        System.out.println("ConnectFourGameGUI.boardIsFull() - End");
        System.out.println("");
        
        System.out.println("ConnectFourGame.boardIsFull() - Begin");
        System.out.println("Expected output: false");
        System.out.println("");
        System.out.println("Actual output: "
                                   + g.boardIsFull());
        System.out.println("ConnectFourGameGUI.boardIsFull() - End");
        System.out.println("");
        
        System.out.println("ConnectFourGame.gameWon() - Begin");
        System.out.println("Expected output: false");
        System.out.println("");
        System.out.println("Actual output: "
                                   + g.gameWon());
        System.out.println("ConnectFourGameGUI.gameWon() - End");
        System.out.println("");
        
        System.out.println("ConnectFourGame.getLowestEmptySlot() - Begin");
        System.out.println("Expected output: 0");
        System.out.println("");
        System.out.println("Actual output: "
                                   + g.getLowestEmptySlot(COLUMN_TWO));
        System.out.println("ConnectFourGameGUI.gameWon() - End");
        System.out.println("");
        
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                g.move(i,j,Piece.ConnectFourPieceColour.RED);
            }
        }
        
        System.out.println("ConnectFourGame.boardIsFull() - Begin");
        System.out.println("Expected output: true");
        System.out.println("");
        System.out.println("Actual output: "
                                   + g.boardIsFull());
        System.out.println("ConnectFourGameGUI.boardIsFull() - End");
        System.out.println("");
        
        System.out.println("ConnectFourGame.gameWon() - Begin");
        System.out.println("Expected output: true");
        System.out.println("");
        System.out.println("Actual output: "
                                   + g.gameWon());
        System.out.println("ConnectFourGameGUI.gameWon()  - End");
        System.out.println("");
	}
}