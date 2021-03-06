/**
* @file OthelloGame.java
* @author Thomas Vass, Jon Bailey, Ieuan Skinner.
* @date 05 Feb 2014
*
* @brief This class controls the Othello game.
*		
* OthelloGame implements the abstract methods of Game. It inherits all 
* responsibilities of Game, as it is more specific version of Game. 
* It interacts with the other classes in the same way as Game except it 
* interacts with the subclasses of those classes.
*/

public class OthelloGame extends Game <Piece.OthelloPieceColour>{
    
    private final int START_SCORE = 2;
    private boolean m_validMove = false;
    private final int MAX_LENGTH=8, MAX_HEIGHT=8;
    public OthelloBoard m_board = new OthelloBoard(MAX_LENGTH,MAX_HEIGHT);

	/**
     * This access method will return player ones score.
     * 
     * @return int	Player ones current score.
     */
     public static int getPlayer1Score(){
         return m_player[0].getScore();
     }

     /**
     * This access method will return player twos score.
     * 
     * @return int	Player twos score.
     */
     static int getPlayer2Score(){
         return m_player[1].getScore();
     }

     /**
     * This access method will set player ones score.
     * @param score	Player ones score.
     */
     public static void setPlayer1Score(int score){
          m_player[0].setScore(score);
     }

     /**
      * This access method will set player twos score.
      * @param score	Player twos score.
      */
     public static void setPlayer2Score(int score){
         m_player[1].setScore(score);
     }
     
     /**
      * This access method will return the 2D array of OthelloPieces 
      * which make up the board.
      * @return board.getPieces	A 2D array of OthelloPieces.
      */
 	 public OthelloPiece[][] getPieces() {
 		 return m_board.getPieces();
     }
     
     /** 
     * This is the Constructor for OthelloGame. It is used to instantiate 
	 * the the board, the players scores and to set the players. 
     * 
     * @param playerOne	The player object containing player one
     * @param playerTwo	The player object containing player two
    */ 
    public OthelloGame(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);

        m_board.setBoard();
        m_player[PLAYER_ONE].setScore(START_SCORE);
        m_player[PLAYER_TWO].setScore(START_SCORE);
    }
       
    /**
    * This method was intended to hold the logic of the game (i.e who's turn 
    * it is) however it ended up being easier to implement this in the GUI.
    * @see OthelloGameGUI
    */
    public boolean gameLoop() {
        boolean turn = true; //true = player1, false = player2
        while(turn){
            if(m_validMove){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    /**
     * @param x			Column coordinate of the piece on the board.
     * @param y			Row coordinate of the piece on the board.
     * @param colour	The piece we want to move.
     * @return boolean 	Returns true if a valid move has been made.
     */
    public boolean move(int x, int y, OthelloPiece colour) {
        return(m_board.move(x,y,colour));//ask board if valid move       
    }
	
	/**
     * @param x			Column coordinate of the piece on the board.
     * @param y			Row coordinate of the piece on the board.
     * @param colour	The enum colour type of the piece we want to move.
     * 
     * @exception UnsupportedOperationException 
     * On move error inherited move function, decided not to use this as board 
     * takes in a piece object and this method only allows the colour itself as
     * a parameter.
     */
    public boolean move(int x, int y, Piece.OthelloPieceColour colour) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /** This is the main method containing the unit tests for this class. */
    
    public static void main (String[] args){
        final int GREATER_THAN_ZERO = 100;
        final int PLAYER_ONE = 0;
        final int PLAYER_TWO = 1;
        final int SCORE_OF_TWO = 2;
        final int SCORE_OF_TEN = 10;
        final int ROW_ONE = 0;
        final int ROW_SIX = 5;
        final int COLUMN_FOUR = 3;
        
        OthelloPiece testPiece = new OthelloPiece(OthelloPiece.OthelloPieceColour.WHITE);

        OthelloGame testGame = new OthelloGame(
                               new HumanPlayer("Tom", 
                                               OthelloPiece.
                                               OthelloPieceColour.BLACK),
                               new HumanPlayer("Harry",
                                               OthelloPiece.
                                               OthelloPieceColour.BLACK));
        
        testGame.m_board.setBoard();
        System.out.println("Testing implemented methods:");
        System.out.println("Testing placing a piece in a valid square (5,3) WHITE");
        System.out.println("Expected output: true");
        System.out.println("Actual output: " + testGame.move(ROW_SIX,
                                                    COLUMN_FOUR,testPiece));
        
        System.out.println("\nTesting placing a piece in an invalid square (0,3) WHITE");
        System.out.println("Expected output: false");
        System.out.println("Actual output: " + testGame.move(ROW_ONE,COLUMN_FOUR
                                                             ,testPiece));
        
        System.out.println("\nTesting updating player1 score by 2");
        System.out.println("Expected output: 4");
        testGame.setPlayer1Score(SCORE_OF_TWO);
        System.out.println("Actual output: " + testGame
                                .getPlayer1Score());
        
        System.out.println("\nTesting updating player2 score by 10");
        System.out.println("Expected output: 12");
        testGame.setPlayer2Score(SCORE_OF_TEN);
        System.out.println("Actual output: " + testGame
                                .getPlayer2Score());
        
        System.out.println("\nTesting inherited methods:");
        String[] s = testGame.getPlayerNames();
        System.out.println("Testing 'getPlayerNames()'");
        System.out.println("Expected output: Tom, Harry");
        System.out.println("Actual output: " + s[PLAYER_ONE] + ", "
                            + s[PLAYER_TWO]);
        
        System.out.println("\nTesting 'getPlayerName(0)'");
        System.out.println("Expected output: Tom");
        System.out.println("Actual output: " + testGame.getPlayerName(PLAYER_ONE));
        
        System.out.println("\ntesting getPlayerName(100)");
        System.out.println("Expected output: Harry");
        System.out.println("Actual output: " + testGame.getPlayerName(GREATER_THAN_ZERO));
    }
}