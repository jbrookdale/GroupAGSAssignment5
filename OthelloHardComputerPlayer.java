import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;

class OthelloHardComputerPlayer <C> extends HardComputerPlayer {
    
	public OthelloHardComputerPlayer(String newName, Object newPlayerColour) {
		super(newName, newPlayerColour);
	}

    public Point makeAIMove(OthelloBoard board) {
        int maximumFlips = 0;
        ArrayList<Point> bestFlips = new ArrayList<Point>();
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getFlipsForPosition(i,j, board) > maximumFlips) {
                     maximumFlips = getFlipsForPosition(i,j,board);
                }
            }
        }
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getFlipsForPosition(i,j, board) == maximumFlips) {
                    bestFlips.add(new Point(i,j));
                }
            }
        }
        
        Random r = new Random();
        int arrayPosition = r.nextInt(bestFlips.size());
        return bestFlips.get(arrayPosition);
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
        int flippedPieces = 0;
        Piece pieces[][] = board.getPieces();
        // If there is not a piece at position (x,y) then work out how many flips it causes
        // Otherwise it doesn't flip anything.
        if (pieces[x][y].getPieceColour() == OthelloPiece.OthelloPieceColour.NONE) {
            for (int i = -1; i <= 1; i++) { // These signify directions on a graph in the x-axis
                for (int j = -1; j <= 1; j++) { // These signify directions on a graph in the y-axis
                    int counter = 1;
                    
                    if ((x+(i*counter)) >= 0 && (x+(i*counter)) < 8 && (y+(j*counter)) >= 0 && (y+(j*counter)) < 8) {
                        // Loop while pieces are the opponents colour. Add 1 to counter for each iteration.
                        while (pieces[x+(i*counter)][y+(j*counter)].getPieceColour() != OthelloPiece.OthelloPieceColour.NONE // != NONE
                            && pieces[x+(i*counter)][y+(j*counter)].getPieceColour() != getColour()) { // != WHITE
                            counter++;
                        }
                    }
                    

                    // If the piece which stops the loop above is the players own colour, add the counter
                    // To the total number of flippied pieces, else don't add anything.
                    if ((x+(i*counter)) >= 0 && (x+(i*counter)) < 8 && (y+(j*counter)) >= 0 && (y+(j*counter)) < 8) {
                        if (!((x+(i*counter)) == x && (y+(j*counter)) == y)) {
                            if (pieces[x+(i*counter)][y+(j*counter)].getPieceColour() == getColour()) {
                                flippedPieces += (counter - 1);
                            }
                        }
                    }
                }
            }
        }
        return flippedPieces;
    }

	public static void main(String[] args) {

		OthelloHardComputerPlayer compAI = new OthelloHardComputerPlayer("CompAI",Piece.OthelloPieceColour.WHITE);
        OthelloGame game = new OthelloGame(compAI, new HumanPlayer("Mabelle",Piece.OthelloPieceColour.BLACK));
        
        for(int j = 0; j < 8; j++){
        	for(int i = 0; i < 8; i++){
        		game.board.setLoadedPieces(i, j, new OthelloPiece(Piece.OthelloPieceColour.NONE));
        	}
        }
        
        game.board.setLoadedPieces(3, 4, new OthelloPiece(Piece.OthelloPieceColour.BLACK));
        game.board.setLoadedPieces(3, 3, new OthelloPiece(Piece.OthelloPieceColour.WHITE));
        game.board.setLoadedPieces(4, 4, new OthelloPiece(Piece.OthelloPieceColour.WHITE));
        game.board.setLoadedPieces(4, 3, new OthelloPiece(Piece.OthelloPieceColour.BLACK));
        
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	int y = compAI.getFlipsForPosition(i,j,game.board);
                System.out.println("(" + (i) + "," + (j) + "): " + "flips " + y + " pieces");
            }
        }
        
        Point x = compAI.makeAIMove(game.board);
        System.out.println("ComAI move: " + x);
    }
}
