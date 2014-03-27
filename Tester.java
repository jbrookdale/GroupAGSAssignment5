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
	pieces,	for setting a piece and checking for win scenario*/  							
	private final Piece.ConnectFourPieceColour RED_PIECE = m_RedPiece.getPieceColour();

	/**< create an object of Piece with colour YELLOW so to give the colour to 
	the YELLOW_PIECE constant*/  
	private ConnectFourPiece m_YellowPiece = new ConnectFourPiece(Piece.ConnectFourPieceColour.YELLOW);

	/**< constant for storing the yellow piece colour for comparing with other 
	pieces,	for setting a piece and checking for win scenario*/ 					
	private final Piece.ConnectFourPieceColour YELLOW_PIECE = m_YellowPiece.getPieceColour();
	public static final int WIDTH = 10; 
	
	/**< constant for the size of the board in y direction */ 
	public static final int HEIGHT = 7; 
	
	/**< array of piece variables storing all pieces on the board */ 
	private ConnectFourPiece[][] m_Pieces = new ConnectFourPiece[WIDTH][HEIGHT]; 

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
		String[] s = new String[2];
		s[0]="Tom";
		s[1]="Harry";
		OthelloGameGUI displayExample = new OthelloGameGUI("Othello",WIDTH,HEIGHT,
                                            new HumanPlayer("Tom","Black"),
                                            new HumanPlayer("Harry","White"));
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
	public static void test2() {
	final int WIDTH = 8, HEIGHT = 8;
       	OthelloBoard Board = new OthelloBoard(WIDTH,HEIGHT);
       	Board.setBoard();
       	Board.incPieceCount();
       	System.out.println(Board.getPieceCount());
       	System.out.println(Board.move(COLUMN_ONE,ROW_FOUR, WHITE_PIECE));
       	Board.m_Pieces[COLUMN_SIX][ROW_FOUR]=WHITE_PIECE;
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
	public static void test3() {
	       final int WIDTH = 8, HEIGHT = 8;
	       OthelloBoard Board = new OthelloBoard(WIDTH,HEIGHT);
	       Board.setBoard();
	       Board.decPieceCount();
	       System.out.println(Board.getPieceCount());
	       System.out.println(Board.move(COLUMN_THREE,ROW_FOUR, WHITE_PIECE));
	       Board.m_Pieces[COLUMN_SIX][ROW_FOUR]=WHITE_PIECE;
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

	public static void test4() {
		final int WIDTH = 8, HEIGHT = 8;
	        OthelloBoard Board = new OthelloBoard(WIDTH,HEIGHT);
		Board.setBoard();
		Board.decPieceCount();
		System.out.println(Board.getPieceCount());
		System.out.println(Board.move(COLUMN_THREE,ROW_FOUR, WHITE_PIECE));
		Board.m_Pieces[COLUMN_SIX][ROW_FOUR]=WHITE_PIECE;
		System.out.println("");
		System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.setPiece() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + Board.setPiece(COLUMN_FOUR,ROW_SIX,BLACK_PIECE));
		System.out.println("");
	}
	
	
    /**	
     * This is a test for move
     */
	
	public static void test5() {
	       final int WIDTH = 8, HEIGHT = 8;
	       OthelloBoard Board = new OthelloBoard(WIDTH,HEIGHT);
	       Board.setBoard();
	       Board.decPieceCount();
	       System.out.println(Board.getPieceCount());
	       System.out.println(Board.move(COLUMN_THREE,ROW_FOUR, WHITE_PIECE));
	       Board.m_Pieces[COLUMN_SIX][ROW_FOUR]=WHITE_PIECE;
	       System.out.println("");
	       System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.move() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + Board.move(COLUMN_TWO,ROW_THREE,BLACK_PIECE));
	       System.out.println("");
	}
	
		
    /**	
     * This is a test for getPieces
     */		
	public static void test7() {
			final int WIDTH = 8, HEIGHT = 8;
	                OthelloBoard Board = new OthelloBoard(WIDTH,HEIGHT);
		      	Board.setBoard();
			Board.decPieceCount();
		      	System.out.println(Board.getPieceCount());	
			System.out.println(Board.move(COLUMN_THREE,ROW_FOUR, WHITE_PIECE));
			Board.m_Pieces[COLUMN_SIX][ROW_FOUR]=WHITE_PIECE;
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
	public static void test8() {
				final int WIDTH = 8, HEIGHT = 8;
	                	OthelloBoard Board = new OthelloBoard(WIDTH,HEIGHT);
			      	Board.setBoard();
				Board.decPieceCount();
			      	System.out.println(Board.getPieceCount());	
				System.out.println(Board.move(COLUMN_THREE,ROW_FOUR, WHITE_PIECE));
				Board.m_Pieces[COLUMN_SIX][ROW_FOUR]=WHITE_PIECE;
				System.out.println("");
				System.out.println("");
				Board.checkWin();
				System.out.println("Valid inputs");
				System.out.println("OthelloBoard.clearPieces() - Begin");
				System.out.println("Expected output: Throws exception");
				System.out.println("");
				try{
					
				}catch(UnsupportedOperationException e){

				}
				System.out.println("");
		}

    /**
     * A method to test the OthelloBoard class
     */
		private static void testOthelloBoard(){
			test2();
	     	test3();
	     	test4();
	     	test5();
		    test7();
		    test8();
		}

    /**
     * A method to test ConnectFour class
     */
		private static void testConnectFour(){
            String[] s1 = new String[3];
            String[] s2 = new String[3];
            s1[0] = "Tom";
            s1[1] = "Human";
            s1[2] = "Red";
            s2[0] = "Harry";
            s2[1] = "Human";
            s2[2] = "Yellow";
			ConnectFourGameGUI connectFour = 
					    new ConnectFourGameGUI(s1, s2, false, "");
		}

        private static void testEasyConnectFourComputerPlayer() {
            
        }
        
        private static void testHardConnectFourComputerPlayer() {
                    
        }
        
        private static void testEasyOthelloComputerPlayer() {
                            
        }

        private static void testHardOthelloComputerPlayer() {
                            
        }

        private static void testConnectFourGameLoader() {
                            
        }

        private static void testConnectFourGameSaver() {
                            
        }

        private static void testOthelloGameLoader() {
                            
        }

        private static void testOthelloGameSaver() {
                            
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
		System.out.println("testing connectFourGUI");
        testEasyConnectFourComputerPlayer();
        testHardConnectFourComputerPlayer();
        testEasyOthelloComputerPlayer();
        testHardOthelloComputerPlayer();
        testConnectFourGameLoader();
        testConnectFourGameSaver();
        testOthelloGameLoader();
        testOthelloGameSaver();
	}
	
}
