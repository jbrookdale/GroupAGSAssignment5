import java.awt.Point;

public class Tester{

public static OthelloPiece WHITE_PIECE = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
       // creates an object of Piece which contains a black piece
public static OthelloPiece BLACK_PIECE = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
       // creates an object of Piece which contains
public static OthelloPiece NONE_PIECE = new OthelloPiece(Piece.OthelloPieceColour.NONE);

/**< create an object of Piece with colour RED so to give the colour to the
RED_PIECE constant*/
private ConnectFourPiece m_RedPiece = new ConnectFourPiece(Piece.ConnectFourPieceColour.RED);

/**< constant for storing the red piece colour for comparing with other
pieces, for setting a piece and checking for win scenario*/
private final Piece.ConnectFourPieceColour RED_PIECE = m_RedPiece.getPieceColour();

/**< create an object of Piece with colour YELLOW so to give the colour to
the YELLOW_PIECE constant*/
private ConnectFourPiece m_YellowPiece = new ConnectFourPiece(Piece.ConnectFourPieceColour.YELLOW);

/**< constant for storing the yellow piece colour for comparing with other
pieces, for setting a piece and checking for win scenario*/
private final Piece.ConnectFourPieceColour YELLOW_PIECE = m_YellowPiece.getPieceColour();

/**< constant for the size of the connect4 board in x direction */
public static final int C4_WIDTH = 10;

/**< constant for the size of the connect4 board in y direction */
public static final int C4_HEIGHT = 7;

/**< constant for the size of the othello board in x direction */
public static final int OTHELLO_WIDTH = 8;

/**< constant for the size of the othello board in y direction */
public static final int OTHELLO_HEIGHT = 8;

/**< array of piece variables storing all pieces on the board */
private ConnectFourPiece[][] m_Pieces = new ConnectFourPiece[C4_WIDTH][C4_HEIGHT];

/**< constant for column one on the board */
private final static int COLUMN_ONE = 0;

/**< constant for column two on the board */
private final static int COLUMN_TWO = 1;

/**< constant for column three on the board */
private final static int COLUMN_THREE = 2;

/**< constant for column four on the board */
private final static int COLUMN_FOUR = 3;

/**< constant for column five on the board */
private final static int COLUMN_FIVE = 4;

/**< constant for column six on the board */
private final static int COLUMN_SIX = 5;

/**< constant for column seven on the board */
private final static int COLUMN_SEVEN = 6;

/**< constant for row one on the board */
private final static int ROW_ONE = 0;

/**< constant for row two on the board */
private final static int ROW_TWO = 1;

/**< constant for row three on the board */
private final static int ROW_THREE = 2;

/**< constant for row four on the board */
private final static int ROW_FOUR = 3;

/**< constant for row five on the board */
private final static int ROW_FIVE = 4;

/**< constant for row six on the board */
private final static int ROW_SIX = 5;

/**< constant for row seven on the board */
private final static int ROW_SEVEN = 6;

/**< constant for row eight on the board */
private final static int ROW_EIGHT = 7;

/**< constant for row nine on the board */
private final static int ROW_NINE = 8;

/**< constant for row ten on the board */
private final static int ROW_TEN = 9;






    /**
* A method to test the OthelloGame class
*/
private static void testOthelloGame(){
final int GREATER_THAN_ZERO = 100;
        final int PLAYER_ONE = 0;
        final int PLAYER_TWO = 1;
        final int SCORE_OF_TWO = 2;
        final int SCORE_OF_TEN = 10;
        final int ROW_ONE = 0;
        final int ROW_SIX = 5;
        final int COLUMN_FOUR = 3;

        OthelloPiece testPiece = new OthelloPiece(OthelloPiece
                                                      .OthelloPieceColour
                                                            .WHITE);

        OthelloGame testGame = new OthelloGame(
                       new HumanPlayer("Tom",
                                       OthelloPiece.
                                       OthelloPieceColour.BLACK),
                       new HumanPlayer("Harry",
                                       OthelloPiece.
                                       OthelloPieceColour.BLACK));

        testGame.m_board.setBoard();
        System.out.println("Testing implemented methods:");
        System.out.println("Testing placing a piece in a valid "
                           + "square (5,3) WHITE");
        System.out.println("Expected output: true");
        System.out.println("Actual output: " + testGame.move(ROW_SIX,
                                            COLUMN_FOUR,testPiece));

        System.out.println("\nTesting placing a piece in an invalid "
                           + "square (0,3) WHITE");
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
        System.out.println("Actual output: "
                           + testGame.getPlayerName(PLAYER_ONE));

        System.out.println("\ntesting getPlayerName(100)");
        System.out.println("Expected output: Harry");
        System.out.println("Actual output: "
                           + testGame.getPlayerName(GREATER_THAN_ZERO));
    }
    
    /**
* A method to test the OthelloPiece class
*/

    private static void testOthelloPiece(){
        OthelloPiece piece =
            new OthelloPiece(Piece.OthelloPieceColour.BLACK);
        
        System.out.println("Valid inputs");
        System.out.println("OthelloPiece.getPieceColour() - Begin");
        System.out.println("Expected output: BLACK");
        System.out.println("");
        System.out.println("Actual output: " + piece.getPieceColour());
        System.out.println("OthelloPiece.getPieceColour() - End");
        System.out.println("");
        System.out.println("OthelloPiece.setPieceColour() - Begin");
        System.out.println("Expected output: true");
        System.out.println("");
        System.out.println("Actual output: " +
            piece.setPieceColour(Piece.OthelloPieceColour.WHITE));
        System.out.println("OthelloPiece.setPieceColour() - End");
        System.out.println("");
        System.out.println("OthelloPiece.getPieceColour() - Begin");
        System.out.println("Expected output: WHITE");
        System.out.println("");
        System.out.println("Actual output: " + piece.getPieceColour());
        System.out.println("OthelloPiece.getPieceColour() - End");
        System.out.println("");
        
        System.out.println("Invalid inputs");
        System.out.println("OthelloPiece.setPieceColour() - Begin");
        System.out.println("Expected output: false");
        System.out.println("");
        System.out.println("Actual output: " +
            piece.setPieceColour(Piece.OthelloPieceColour.NONE));
        System.out.println("OthelloPiece.setPieceColour() - End");
        System.out.println("");
        System.out.println("OthelloPiece.getPieceColour() - Begin");
        System.out.println("Expected output: WHITE");
        System.out.println("");
        System.out.println("Actual output: " + piece.getPieceColour());
        System.out.println("OthelloPiece.getPieceColour() - End");
}

    /**
     * A method to test the MenuGUI class
     */
    private static void testMenuGUI(){
        
        int VALID_WIDTH = 370;		
        int VALID_HEIGHT = 300;
    
        MenuGUI menu = new MenuGUI("Main Menu", VALID_WIDTH, VALID_HEIGHT);
    }

    /**
     * A method to test the ConnectFourPiece class
     */
    private static void testConnectFourPiece(){
        ConnectFourPiece piece =
        new ConnectFourPiece(Piece.ConnectFourPieceColour.RED);

        System.out.println("Valid inputs");
        System.out.println("OthelloPiece.getPieceColour() - Begin");
        System.out.println("Expected output: RED");
        System.out.println("");
        System.out.println("Actual output: " + piece.getPieceColour());
        System.out.println("OthelloPiece.getPieceColour() - End");
        System.out.println("");
        System.out.println("OthelloPiece.setPieceColour() - Begin");
        System.out.println("Expected output: true");
        System.out.println("");
        System.out.println("Actual output: " +
            piece.setPieceColour(Piece.ConnectFourPieceColour.YELLOW));
        System.out.println("OthelloPiece.setPieceColour() - End");
        System.out.println("");
        System.out.println("OthelloPiece.getPieceColour() - Begin");
        System.out.println("Expected output: YELLOW");
        System.out.println("");
        System.out.println("Actual output: " + piece.getPieceColour());
        System.out.println("OthelloPiece.getPieceColour() - End");
        System.out.println("");

        System.out.println("Invalid inputs");
        System.out.println("OthelloPiece.setPieceColour() - Begin");
        System.out.println("Expected output: false");
        System.out.println("");
        System.out.println("Actual output: " +
            piece.setPieceColour(Piece.ConnectFourPieceColour.NONE));
        System.out.println("OthelloPiece.setPieceColour() - End");
        System.out.println("");
        System.out.println("OthelloPiece.getPieceColour() - Begin");
        System.out.println("Expected output: YELLOW");
        System.out.println("");
        System.out.println("Actual output: " + piece.getPieceColour());
        System.out.println("OthelloPiece.getPieceColour() - End");
    }

    /**
     * A method to test the ConnectFourPiece class
     */
    private static void testOthelloGameGUI(){
        final int WIDTH = 600, HEIGHT = 600;
        final int TOTAL_PLAYERS = 2;
        final int PLAYER_ONE = 0;
        final int PLAYER_TWO = 1;
        String[] s = new String[TOTAL_PLAYERS];
        s[PLAYER_ONE]="Jon";
        s[PLAYER_TWO]="Tom";
        Player playerOne = new OthelloHardComputerPlayer(s[0], "Black");
        Player playerTwo = new HumanPlayer(s[1], "White");
        OthelloGameGUI displayExample = new OthelloGameGUI("Othello",WIDTH,HEIGHT, playerOne, playerTwo);
        displayExample.setPlayers(s);

        displayExample.creatingGui();
}

    /**
     * A method to test the ConnectFourBoard class
     */
    private static void testConnectFourBoard(){
        //could not test as everything is private
    }
    
    /**
     * A method to test the HumanPlayer class
     */
    private static void testHumanPlayer(){

        final int TEST_SCORE = 2;
        ConnectFourPiece player1Piece;
        player1Piece = new ConnectFourPiece(ConnectFourPiece.ConnectFourPieceColour.YELLOW);
        HumanPlayer player1 = new HumanPlayer("Tom",player1Piece.getPieceColour());
        System.out.println("Player1: " +player1.getName());
        System.out.println("Colour: " +player1.getColour());
        System.out.println("score: " +player1.getScore());
        player1.setScore(TEST_SCORE);
        System.out.println("Change score: " +player1.getScore());
        OthelloPiece player2Piece;
        player2Piece = new OthelloPiece(OthelloPiece.OthelloPieceColour.BLACK);
        HumanPlayer player2 = new HumanPlayer("Tom",player2Piece.getPieceColour());
        System.out.println("Player1: " +player2.getName());
        System.out.println("Colour: " +player2.getColour());
        System.out.println("score: " +player2.getScore());
        player1.setScore(TEST_SCORE);
        System.out.println("Change score: " +player2.getScore());
    }
    
    //symbolic constants for test cases for OthelloBoard.java
    private static final int BOARD_SIZE = 8; //sets size of board to 8 wide and 8 high
         
    private static final int TEST_MOVE_X1 = 2; //sets x and y coorindates of a possible move
    private static final int TEST_MOVE_Y1 = 3; 
         
    private static final int TEST_PIECE_X = 5; //sets x and y coordinates of a piece in othello
    private static final int TEST_PIECE_Y = 3;
    
    private static final int OUTPUT_SETPIECE_TEST_X = 3; //expected x and y coordinates from output in setPiece test
    private static final int OUTPUT_SETPIECE_TEST_Y = 5;
   
    private static final int OUTPUT_MOVE_TEST_X = 1; //expected x and y coordinates from output move test
    private static final int OUTPUT_MOVE_TEST_Y = 2;
    
    private static final int OUTPUT_ANYMOVE_TEST_X = 1; //expected x and y coordinates from output in anymove test
    private static final int OUTPUT_ANYMOVE_TEST_Y = 2;
   
    private static final int OUTPUT_DECPIECE_TEST_X = 0; //expected x and y coordinates from output in decPiece test
    private static final int OUTPUT_DECPIECE_TEST_Y = 3;
    
    /**
     * This is a test for incPiececount and checkWin
     */

    public static void incPieceCountTest() {
        OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
        Board.setBoard();
        Board.incPieceCount();
        System.out.println(Board.getPieceCount());
        System.out.println(Board.move(
            OUTPUT_DECPIECE_TEST_X, OUTPUT_DECPIECE_TEST_Y, WHITE_PIECE));
        Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=WHITE_PIECE;
        System.out.println("");
        System.out.println("");
        Board.checkWin();
        System.out.println("Valid inputs");
        System.out.println("OthelloBoard.checkWin() - Begin");
        System.out.println("Expected output: false");
        System.out.println("");
        System.out.println("Actual output: " + Board.checkWin());
        System.out.println("");
    }
        
        
    /**
     * This is a test for decPiececount and CheckWin
     */	
    public static void decPieceCountTest() {
        OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
        Board.setBoard();
        Board.decPieceCount();
        System.out.println(Board.getPieceCount());
        System.out.println(Board.move(
            TEST_MOVE_X1, TEST_MOVE_Y1, WHITE_PIECE));
        Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=WHITE_PIECE;
        System.out.println("");
        System.out.println("");
        Board.checkWin();
        System.out.println("Valid inputs");
        System.out.println("OthelloBoard.CheckWin() - Begin");
        System.out.println("Expected output: false");
        System.out.println("");
        System.out.println("Actual output: " + Board.CheckWin());
        System.out.println("");
    }
        

    /**
    * This is a test for setPiece
    */	

    public static void setPieceTest() {
        OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
        Board.setBoard();
        Board.decPieceCount();
        System.out.println(Board.getPieceCount());
        System.out.println(Board.move(
            TEST_MOVE_X1, TEST_MOVE_Y1, WHITE_PIECE));
        Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=WHITE_PIECE;
        System.out.println("");
        System.out.println("");
        Board.checkWin();
        System.out.println("Valid inputs");
        System.out.println("OthelloBoard.setPiece() - Begin");
        System.out.println("Expected output: true");
        System.out.println("");
        System.out.println("Actual output: " + 
        Board.setPiece(
            OUTPUT_SETPIECE_TEST_X,OUTPUT_SETPIECE_TEST_Y,BLACK_PIECE));
        System.out.println("");
    }
        
        
    /**	
    * This is a test for move
    */
        
    public static void moveTest() {
        OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
        Board.setBoard();
        Board.decPieceCount();
        System.out.println(Board.getPieceCount());
        System.out.println(Board.move(
        TEST_MOVE_X1, TEST_MOVE_Y1, WHITE_PIECE));
        Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=WHITE_PIECE;
        System.out.println("");
        System.out.println("");
        Board.checkWin();
        System.out.println("Valid inputs");
        System.out.println("OthelloBoard.move() - Begin");
        System.out.println("Expected output: false");
        System.out.println("");
        System.out.println("Actual output: " + 
        Board.move(OUTPUT_MOVE_TEST_X,OUTPUT_MOVE_TEST_Y,BLACK_PIECE));
        System.out.println("");
    }
        
        

    /**	
     * This is a test for anyMove
     */	
    public static void anyMoveTest() {
        OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
        Board.setBoard();
        Board.decPieceCount();
        System.out.println(Board.getPieceCount());	
        System.out.println(Board.move(
        TEST_MOVE_X1, TEST_MOVE_Y1, WHITE_PIECE));
        Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=WHITE_PIECE;
        System.out.println("");
        System.out.println("");
        Board.checkWin();
        System.out.println("Valid inputs");
        System.out.println("OthelloBoard.anyMove() - Begin");
        System.out.println("Expected output: false");
        System.out.println("");
        System.out.println("Actual output: " 
            + Board.anyMove(
            OUTPUT_ANYMOVE_TEST_X,OUTPUT_ANYMOVE_TEST_Y,BLACK_PIECE));
        System.out.println("");
    }
            
    /**	
     * This is a test for getPieces
     */		
    public static void getPiecesTest() {
        OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
        Board.setBoard();
        Board.decPieceCount();
        System.out.println(Board.getPieceCount());	
        System.out.println(Board.move(
            TEST_MOVE_X1, TEST_MOVE_Y1, WHITE_PIECE));
        Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=WHITE_PIECE;
        System.out.println("");
        System.out.println("");
        Board.checkWin();
        System.out.println("Valid inputs");
        System.out.println("OthelloBoard.clearPieces() - Begin");
        System.out.println("Expected output: 0");
        System.out.println("");
        Board.clearPieces();
        System.out.println("Actual output: " + Board.getPieces());
        System.out.println("");
    }
        

    /**	
     * This is a test for setPiece
     */
    public static void setPieceTest2() {
        OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
        Board.setBoard();
        Board.decPieceCount();
        System.out.println(Board.getPieceCount());
        System.out.println(Board.move(
            TEST_MOVE_X1, TEST_MOVE_Y1, WHITE_PIECE));
            Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=WHITE_PIECE;
        System.out.println("");
        System.out.println("");
        Board.checkWin();
        System.out.println("Valid inputs");
        System.out.println("OthelloBoard.clearPieces() - Begin");
        System.out.println("Expected output: Throws exception");
        System.out.println("");

        System.out.println("");
    }



    /**
     * A method to test the OthelloBoard class
     */
    private static void testOthelloBoard(){
        incPieceCountTest();
        decPieceCountTest();
        setPieceTest();
        moveTest();
        anyMoveTest();
        getPiecesTest();
        setPieceTest2();
    }

    /**
     * A method to test ConnectFour class
     */
    private static void testConnectFour(){
        final int TOTAL_PLAYER_COMPONENTS = 3;
        final int PLAYER_COMPONENT_ONE = 0;
        final int PLAYER_COMPONENT_TWO = 1;
        final int PLAYER_COMPONENT_THREE = 2;
        String[] s1 = new String[TOTAL_PLAYER_COMPONENTS];
        String[] s2 = new String[TOTAL_PLAYER_COMPONENTS];
        s1[PLAYER_COMPONENT_ONE] = "Tom";
        s1[PLAYER_COMPONENT_TWO] = "Human";
        s1[PLAYER_COMPONENT_THREE] = "Red";
        s2[PLAYER_COMPONENT_ONE] = "Jon";
        s2[PLAYER_COMPONENT_TWO] = "Human";
        s2[PLAYER_COMPONENT_THREE] = "Yellow";
        ConnectFourGameGUI g = new ConnectFourGameGUI(s1,s2,false,"");
        
        final int ANIMATION_TIME = 750;
    
        final int COLUMN_THREE = 2;
        final int COLUMN_SEVEN = 6;
    
        System.out.println("ConnectFourGameGUI.performMove() "
                                       + "- Begin");
        System.out.println("Expected output: first piece placed in the third "
                                       + "column");
        System.out.println("");
        System.out.println("Actual output: on screen.");
        g.performMove(COLUMN_THREE);
        System.out.println("ConnectFourGameGUI.performMove() "
                                       + "- End");
        System.out.println("");
    
        System.out.println("ConnectFourGameGUI.performMove() - Begin");
        System.out.println("Expected output: Nothing as move "
                               + "shouldn't be performed until"
                               + "the last animation has finished");
        System.out.println("");
        System.out.println("Actual output: on screen.");
        g.performMove(COLUMN_SEVEN);
        System.out.println("ConnectFourGameGUI.performMove() - End");
        System.out.println("");
    
        System.out.println("Next test in .75 seconds...");
    
        System.out.println("");

        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (Exception e) {
        
        }
    
        System.out.println("ConnectFourGameGUI.performMove() - Begin");
        System.out.println("Expected output: opponent piece placed in the "
                                               + "third column, ontop of"
                                               + " the first piece");
        System.out.println("");
        System.out.println("Actual output: on screen.");
        g.performMove(COLUMN_THREE);
        System.out.println("ConnectFourGameGUI.performMove() - End");
        System.out.println("");
    }

    /**
     * A method to test EasyConnectFourComputerPlayer class
     */
    private static void testEasyConnectFourComputerPlayer() {
        /**
          * This is to test that Connect Four game works with Computer Easy implementation
          * This tests that the name, colour and score for each player can be retrieved 
          */
          final int TEST_PLAYER_SCORE = 2;
          final int TEST_PLAYER2_SCORE = 4;
          final int WIDTH = 10;
          final int HEIGHT = 7;
                        
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
          /** < show the move of the easy computer player  */
          Point x = compAI.makeAIMove(board);
          System.out.println("Expected position corresponding to column " + x.y + " row " +x.x);
          System.out.println("ConectFourHard Computer move: " + "y=" +x.y + "  " + "x=" +x.x);;
    }

    /**
     * A method to test HardConnectFourComputerPlayer class
     */
    private static void testHardConnectFourComputerPlayer() {
        final int TEST_SCORE = 2;
        final int WIDTH = 10;
        final int HEIGHT = 7;
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
        
        private static void testEasyOthelloComputerPlayer() {
            /** Test to ensure random move works */
    		/** < set two player   */
        	OthelloEasyComputerPlayer compAI = new OthelloEasyComputerPlayer("CompAI",Piece.OthelloPieceColour.WHITE);
                OthelloGame game = new OthelloGame(compAI, new HumanPlayer("Mabelle",Piece.OthelloPieceColour.BLACK));
                      
                /** < show the move of the easy computer player  */
                System.out.println("");
                System.out.println("Expected easy computer move position:");
                System.out.println("Random piont in {(2,3),(3,2),(4,5),(5,4)}");
                Point x = compAI.makeAIMove(game.m_board);
                System.out.println("Othello Easy ComAI move: " + x);
                
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

        private static void testHardOthelloComputerPlayer() {
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
                    
              /** < show the move of the hard computer player  */
              System.out.println("");
              System.out.println("Expected maximum pieces flipped position:");
              System.out.println("Random piont in {(2,3),(3,2),(4,5),(5,4)}");
              Point x = compAI.makeAIMove(game.m_board);
              System.out.println("Othello Hard ComAI move: " +"x="+ x.x + " y=" + x.y);
        }

        private static void testGameSaver(){
        	final String TEST_PLAYER_1_NAME = "X";
    		final String TEST_PLAYER_2_NAME = "Y";
    		
    		final String TEST_PLAYER_1_COLOUR = "Red";
    		final String TEST_PLAYER_2_COLOUR = "Yellow";
    		
    		Player testPlayer1 = new HumanPlayer(TEST_PLAYER_1_NAME, TEST_PLAYER_1_COLOUR);
    		Player testPlayer2 = new HumanPlayer(TEST_PLAYER_2_NAME, TEST_PLAYER_2_COLOUR);
    		
    		Game testGame = new ConnectFourGame(testPlayer1, testPlayer2);
    		
    		GameSaver testSaving = new GameSaver("saves//testGameSaver.xml");
    		
    		if(testSaving.getFile().exists()){
    			System.out.println("Test passed.");
    		}else{
    			System.out.println("Test failed.");
    		}
        }
        
        private static void testGameLoader(){
        	GameLoader testOthelloLoading = new GameLoader("saves\\othellosaves\\testOthello.xml");
        	GameLoader testConnectFourLoading = new GameLoader("saves\\connect4saves\\testConnect4.xml");
        }
        
        private static void testConnectFourGameLoader() {
            final int TOTAL_PLAYER_COMPONENTS = 3;
            final int PLAYER_COMPONENT_ONE = 0;
            final int PLAYER_COMPONENT_TWO = 1;
            final int PLAYER_COMPONENT_THREE = 2;
            String[] s1 = new String[TOTAL_PLAYER_COMPONENTS];
            String[] s2 = new String[TOTAL_PLAYER_COMPONENTS];
            s1[PLAYER_COMPONENT_ONE] = "X";
            s1[PLAYER_COMPONENT_TWO] = "Human";
            s1[PLAYER_COMPONENT_THREE] = "Red";
            s2[PLAYER_COMPONENT_ONE] = "X";
            s2[PLAYER_COMPONENT_TWO] = "Computer: Hard";
            s2[PLAYER_COMPONENT_THREE] = "Yellow";
            ConnectFourGameGUI testConnect4 = new ConnectFourGameGUI(s1,s2,false,"");

    		ConnectFourGameLoader test = new ConnectFourGameLoader("saves\\connect4saves\\testConnect4.xml");
        }

        private static void testConnectFourGameSaver() {
    		final String TEST_PLAYER_1_NAME = "X";
    		final String TEST_PLAYER_2_NAME = "Y";
    		
    		final String TEST_PLAYER_1_COLOUR = "Red";
    		final String TEST_PLAYER_2_COLOUR = "Yellow";
    		
    		Player testPlayer1 = new HumanPlayer(TEST_PLAYER_1_NAME, TEST_PLAYER_1_COLOUR);
    		Player testPlayer2 = new HumanPlayer(TEST_PLAYER_2_NAME, TEST_PLAYER_2_COLOUR);
    	
    		ConnectFourGame testGame = new ConnectFourGame(testPlayer1, testPlayer2);
    	
    		ConnectFourGameSaver testSaving = new ConnectFourGameSaver("saves//connect4saves//testC4GameSaver.xml");
    	
    		if(testSaving.getFile().exists()){
    			System.out.println("Test passed.");
    		}else{
    			System.out.println("Test failed.");
    		}
        }

        private static void testOthelloGameLoader() {
    		final int BOARD_SIZE = 8;
    		
    		final String TEST_PLAYER_1_NAME = "X";
    		final String TEST_PLAYER_2_NAME = "Y";
    		
    		final String TEST_PLAYER_1_COLOUR = "Black";
    		final String TEST_PLAYER_2_COLOUR = "White";
    		
    		Player testPlayer1 = new HumanPlayer(TEST_PLAYER_1_NAME, TEST_PLAYER_1_COLOUR);
        	Player testPlayer2 = new HumanPlayer(TEST_PLAYER_2_NAME, TEST_PLAYER_2_COLOUR);
        	
        	OthelloGameGUI testOthello = new OthelloGameGUI("Othello", BOARD_SIZE, BOARD_SIZE, testPlayer1, testPlayer2);
    		testOthello.creatingGui();
    		OthelloGameLoader testOthelloLoading = new OthelloGameLoader("saves\\othellosaves\\testOthello.xml");
        }

        private static void testOthelloGameSaver() {		
    		final String TEST_PLAYER_1_NAME = "X";
    		final String TEST_PLAYER_2_NAME = "Y";
    		
    		final String TEST_PLAYER_1_COLOUR = "Black";
    		final String TEST_PLAYER_2_COLOUR = "White";
    		
    		Player testPlayer1 = new HumanPlayer(TEST_PLAYER_1_NAME, TEST_PLAYER_1_COLOUR);
    		Player testPlayer2 = new HumanPlayer(TEST_PLAYER_2_NAME, TEST_PLAYER_2_COLOUR);
    	
    		OthelloGame testGame = new OthelloGame(testPlayer1, testPlayer2);
    		OthelloGameGUI.setPlayerOne(testPlayer1);
    		OthelloGameGUI.setPlayerTwo(testPlayer2);
    		
    		OthelloGameSaver testSaving = new OthelloGameSaver("saves//othellosaves//testOthelloGameSaver.xml");
    	
    		if(testSaving.getFile().exists()){
    			System.out.println("Test passed.");
    		}else{
    			System.out.println("Test failed.");
    		}
        }


    /**
* This is the main method, which is used for testing
* @param args[] - This array of Strings contains all the arguments
that are passed in when the program is run. These are not used though,
so any arguments passed in will have no effect on the program or testing.
*/
	public static void main (String[] args){
        System.out.println("testing OthelloGame");
        testOthelloGame();
        System.out.println("testing OthelloPiece");
        testOthelloPiece();
        System.out.println("testing ConnectFourPiece");
        testConnectFourPiece();
        System.out.println("testing menuGUI");
        testMenuGUI();
        System.out.println("testing connectFourBoard");
        testConnectFourBoard();
        System.out.println("testing OthelloGameGUI");
        testOthelloGameGUI();
        System.out.println("testing HumanPlayer");
        testHumanPlayer();
        System.out.println("testing OthelloBoard");
        testOthelloBoard();
        System.out.println("testing ConnectFourEasyComputerPlayer");
        testEasyConnectFourComputerPlayer();
        System.out.println("testing ConnectFourHardComputerPlayer");
        testHardConnectFourComputerPlayer();
        System.out.println("testing OthelloEasyComputerPlayer");
        testEasyOthelloComputerPlayer();
        System.out.println("testing OthelloHardComputerPlayer");
        testHardOthelloComputerPlayer();
        System.out.println("testing GameLoader");
        testGameLoader();
        System.out.println("testing GameSaver");
        testGameSaver();
        System.out.println("testing ConnectFourGameLoader");
        testConnectFourGameLoader();
        System.out.println("testing ConnectFourGameSaver");
        testConnectFourGameSaver();
        System.out.println("testing OthelloGameLoader");
        testOthelloGameLoader();
        System.out.println("testing OthelloGameSaver");
        testOthelloGameSaver();
    }
}
