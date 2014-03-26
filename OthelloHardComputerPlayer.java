import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;

class OthelloHardComputerPlayer <C> extends HardComputerPlayer {
    
	private final static int BOARD_WIDTH = 8;
	private final static int BOARD_HEIGHT = 8;
	private final static int NEGATIVE_X_AXIS = -1;
	private final static int POSITIVE_X_AXIS = 1;
	private final static int NEGATIVE_Y_AXIS = -1;
	private final static int POSITIVE_Y_AXIS = 1;
	
	public OthelloHardComputerPlayer(String newName, Object newPlayerColour) {
		super(newName, newPlayerColour);
	}

    public Point makeAIMove(OthelloBoard board) {
        int m_MaximumFlips = 0;
        ArrayList<Point> m_BestFlips = new ArrayList<Point>();
        
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (getFlipsForPosition(i,j, board) > m_MaximumFlips) {
                	m_MaximumFlips = getFlipsForPosition(i,j,board);
                }
            }
        }
        
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (getFlipsForPosition(i,j, board) == m_MaximumFlips) {
                	m_BestFlips.add(new Point(i,j));
                }
            }
        }
        
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
    *
    **/   
    
    private int getFlipsForPosition(int x, int y, OthelloBoard board) {
        int m_FlippedPieces = 0;
        Piece m_Pieces[][] = board.getPieces();
        // If there is not a piece at position (x,y) then work out how many flips it causes
        // Otherwise it doesn't flip anything.
        if (m_Pieces[x][y].getPieceColour() == OthelloPiece.OthelloPieceColour.NONE) {
            for (int i = NEGATIVE_X_AXIS; i <= POSITIVE_X_AXIS; i++) { // These signify directions on a graph in the x-axis
                for (int j = NEGATIVE_Y_AXIS; j <= POSITIVE_Y_AXIS; j++) { // These signify directions on a graph in the y-axis
                    int counter = 1;
                    
                    if ((x+(i*counter)) >= 0 && (x+(i*counter)) < BOARD_WIDTH && (y+(j*counter)) >= 0 && (y+(j*counter)) < BOARD_HEIGHT) {
                        // Loop while pieces are the opponents colour. Add 1 to counter for each iteration.
                        while (m_Pieces[x+(i*counter)][y+(j*counter)].getPieceColour() != OthelloPiece.OthelloPieceColour.NONE // != NONE
                            && m_Pieces[x+(i*counter)][y+(j*counter)].getPieceColour() != getColour()) { // != WHITE
                            counter++;
                        }
                    }
                    

                    // If the piece which stops the loop above is the players own colour, add the counter
                    // To the total number of flippied pieces, else don't add anything.
                    if ((x+(i*counter)) >= 0 && (x+(i*counter)) < BOARD_WIDTH && (y+(j*counter)) >= 0 && (y+(j*counter)) < BOARD_HEIGHT) {
                        if (!((x+(i*counter)) == x && (y+(j*counter)) == y)) {
                            if (m_Pieces[x+(i*counter)][y+(j*counter)].getPieceColour() == getColour()) {
                            	m_FlippedPieces += (counter - 1);
                            }
                        }
                    }
                }
            }
        }
        return m_FlippedPieces;
    }

	public static void main(String[] args) {

		OthelloHardComputerPlayer compAI = new OthelloHardComputerPlayer("CompAI",Piece.OthelloPieceColour.WHITE);
        OthelloGame game = new OthelloGame(compAI, new HumanPlayer("Mabelle",Piece.OthelloPieceColour.BLACK));
        
        for(int j = 0; j < BOARD_HEIGHT; j++){
        	for(int i = 0; i < BOARD_WIDTH; i++){
        		game.board.setLoadedPieces(i, j, new OthelloPiece(Piece.OthelloPieceColour.NONE));
        	}
        }
        
        game.board.setLoadedPieces(3, 4, new OthelloPiece(Piece.OthelloPieceColour.BLACK));
        game.board.setLoadedPieces(3, 3, new OthelloPiece(Piece.OthelloPieceColour.WHITE));
        game.board.setLoadedPieces(4, 4, new OthelloPiece(Piece.OthelloPieceColour.WHITE));
        game.board.setLoadedPieces(4, 3, new OthelloPiece(Piece.OthelloPieceColour.BLACK));
        
        
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
            	int y = compAI.getFlipsForPosition(i,j,game.board);
                System.out.println("(" + (i) + "," + (j) + "): " + "flips " + y + " pieces");
            }
        }
        
        Point x = compAI.makeAIMove(game.board);
        System.out.println("ComAI move: " + x);
    }
}
