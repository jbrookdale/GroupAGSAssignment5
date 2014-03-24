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
    
    private int getFlipsForPosition(int x, int y, OthelloBoard board) {
        // Create a second board, with exactly the same pieces as the first one.
        // Perform the move at (i,j) on the second board.
        // The number of flips is the number of white pieces on board 2 minus the number of white pieces on board 1
        
        OthelloBoard temp = board;
        //int flips = temp.getNumberOfPiecesColoured(getColour()) - board.getNumberOfPiecesColoured(getColour()) - 1;
        //return flips;
        return 3;
    }

	public static void main(String[] args) {
        // Tests go here
    }
	

}