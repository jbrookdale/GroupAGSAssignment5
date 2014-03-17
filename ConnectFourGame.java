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

    public int getLowestEmptySlot(int x) {
        return m_board.getLowestEmptySlot(x);
    }


	public ConnectFourPiece[][] getPieces() {
		return m_board.getPieces();
	}
	
    
	void saveGame(String fileName){
	
	}

	void loadGame(String fileName){
		
	}
    
	/**
	* Constructor for ConnectFourGame. This is used to instantiate 
	* integers if we want it to.
	* @param n1 -an integer
	* @return TRUE if successful
	*/
	public ConnectFourGame(String playerOneName, String playerOneType, 
        Piece.ConnectFourPieceColour m_player1Colour, String playerTwoName, 
        String playerTwoType, Piece.ConnectFourPieceColour m_player2Colour) { // This needs to be changed to only take 5 parameters or less - Jon
	
        super(playerOneName,playerOneType,m_player1Colour, playerTwoName, 
            playerTwoType, m_player2Colour);
	
        m_player[PLAYER_ONE] = new HumanPlayer(playerOneName, m_player1Colour);
        m_player[PLAYER_TWO] = new HumanPlayer(playerTwoName, m_player2Colour);
        //turn = PLAYER_ONE;

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

	public boolean gameWon() {
		return m_board.checkWin();
	}

	public boolean boardIsFull() {
		return m_board.isFull();
	}

	public static void main (String[] args){
		//Test here
	}
}