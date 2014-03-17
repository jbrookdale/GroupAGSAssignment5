

/**
* @file OthelloGame.java
* @author Thomas Vass
* @date 05 Feb 2014
* @see 'Game.java for inherited methods.
*
* @brief This class controls the Othello game
*		
 * OthelloGame implements the abstract methods of Game.
 * It inherits all responsibilities of Game, as it is 
 * more specific version of Game. It interacts with the 
 * other classes in the same way as Game except it interacts
 * with the subclasses of those classes.
*
*/

public class OthelloGame extends Game <Piece.OthelloPieceColour>{
    
    //each player starts with 2 pieces
    private final int START_SCORE = 2;
    //for the game loop
    private boolean validMove = false;
    //size of the board
    private final int MAX_LENGTH=8, MAX_HEIGHT=8;
    //the board its self
    public OthelloBoard board = new OthelloBoard(MAX_LENGTH,MAX_HEIGHT);


    /** 
     *Constructor for OthelloGame. This is used to instantiate 
     * integers if we want it to.
     * 
     * @param playerOneName  -Name of player one
     * @param playerOneType  -Type of game player one is playing
     * @param playerOneColour -Colour of player one piece
     * @param playerTwoName  -Name of player two
     * @param playerTwoType  -Type of game player two is playing
     * @param playerTwoColour -Colour of player two piece
    */ 
    public OthelloGame(String playerOneName, String playerOneType, 
            Piece.OthelloPieceColour playerOneColour, String playerTwoName, 
            String playerTwoType, Piece.OthelloPieceColour playerTwoColour) {
        
        super(playerOneName, playerOneType, playerOneColour, 
                playerTwoName, playerTwoType, playerTwoColour);

        board.setBoard();
        m_player[0].setScore(START_SCORE);
        m_player[1].setScore(START_SCORE);
    }
        


    //not done in this assignement
    public void pause() {}


    /**
    *was intended to hold the logic of the game (i.e who's turn it is)
    *however it ended up being easier to implement this in the GUI
    *@see OthelloGameGUI
    */
    @Override
    boolean gameLoop() {
        boolean turn = true; //true = player1, false = player2
        while(turn){
            if(validMove){
                return true;
            }else{
                return false;
            }
        }
        
        return false;
    }

    //not done in this assignment
    public void save(String filename) {}
    //not done in this assignment
    public void load(String filename) {}

    /**
     * @param x - x cordinate on board
     * @param y - y cordinate on board
     * @param colour - Colour of the piece to move
     *@return boolean - returns true if a valid move has been made
    */
    public boolean move(int x, int y, OthelloPiece colour) {
        return(board.move(x,y,colour));//ask board if valid move       
    }

    //not done in this assignement
    public void timeStart(){}

    //not done in this assignement
    public long timeElapsed(){return 0;}
    

    /**
    * get player one score 
    * @param -Nothing 
    * @return int - player one score
    */
    int getPlayer1Score(){
        return m_player[0].getScore();
    }

    /**
    * get player two score 
    * @param -Nothing
    * @return int - player two score
    */
    int getPlayer2Score(){
        return m_player[1].getScore();
    }

    /**
    * set player one score
    * @param score -the score of player one
    * @return -Nothing
    */
    void setPlayer1Score(int score){
         m_player[0].setScore(score);
    }

    /**
    * set player two score
    * @param score -the score of player two
    * @return -Nothing
    */
    void setPlayer2Score(int score){
        m_player[1].setScore(score);
    }


    /**
    * @param int x - x coord
    * @param int y - y coord
    * @param Piece.OthelloPieceColour
    * @exception UnsupportedOperationException On move error
    * inherited move function, decided not to use this as board takes in a piece object
    * and this method only allows the colour itself as a parameter
    */
    @Override
    boolean move(int x, int y, Piece.OthelloPieceColour colour) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
    * main method for testing purposes only
    * @param args Unused.
    * @return Nothing.
    */
    public static void main (String[] args){
        OthelloPiece testPiece = new OthelloPiece(OthelloPiece.OthelloPieceColour.WHITE);

        OthelloGame testGame = new OthelloGame("Tom","Human",OthelloPiece.OthelloPieceColour.BLACK,
                                            "Harry","Human",OthelloPiece.OthelloPieceColour.WHITE);
        testGame.board.setBoard();
        System.out.println("testing implemented methods:");
        System.out.println("testing placing a piece in a valid square (5,3) WHITE");
        System.out.println("expected output: true");
        System.out.println(testGame.move(5,3,testPiece));
        System.out.println();
        System.out.println("testing placing a piece in an invalid square (0,3) WHITE");
        System.out.println("expected output: false");
        System.out.println(testGame.move(0,3,testPiece));
        System.out.println();
        System.out.println("testing updating player1 score by 2");
        System.out.println("expected output: player score 4");
        testGame.setPlayer1Score(2);
        System.out.println("player score: "+testGame.getPlayer1Score());
        System.out.println();
        System.out.println("testing updating player2 score by 10");
        System.out.println("expected output: player score 12");
        testGame.setPlayer2Score(10);
        System.out.println("player score: "+testGame.getPlayer2Score());
        System.out.println();
        System.out.println("testing inherited methods:");
        String[] s = testGame.getPlayerNames();
        System.out.println("testing getPlayerNames()");
        System.out.println("expected output: Tom, Harry");
        System.out.println(s[0]+" , "+s[1]);
        System.out.println();
        System.out.println("testing getPlayerName(0)");
        System.out.println("expected output: Tom");
        System.out.println(testGame.getPlayerName(0));
        System.out.println();
        System.out.println("testing getPlayerName(100)");
        System.out.println("expected output: Harry");
        System.out.println(testGame.getPlayerName(100));

        

    }
}