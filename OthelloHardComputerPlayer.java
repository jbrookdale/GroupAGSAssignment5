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
                    int counter = 0;
                    // Loop while pieces are the opponets colour. Add 1 to counter for each iteration.
                    while (pieces[x+(i*counter)][y+(j*counter)].getPieceColour() != OthelloPiece.OthelloPieceColour.NONE
                        && pieces[x+(i*counter)][y+(j*counter)].getPieceColour() != getColour()) {
                        counter++;
                    }
                    // If the piece which stops the loop above is the players own colour, add the counter
                    // To the total number of flippied pieces, else don't add anything.
                    if (pieces[x+(i*counter)][y+(j*counter)].getPieceColour() == getColour()) {
                        flippedPieces += counter;
                    }
                }
            }
        }
        return flippedPieces;
    }

	public static void main(String[] args) {

    }
	

}