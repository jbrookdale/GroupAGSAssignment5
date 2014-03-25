import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

class ConnectFourHardComputerPlayer <C> extends HardComputerPlayer {


    private static int HEIGHT = 7;
    private static int WIDTH = 10;
    public ConnectFourHardComputerPlayer(String newName, C newPlayerColour) {
 		super(newName, newPlayerColour);
 	}



	public static void main(String[] args) {
        // Tests go here
    }
    
    
   
	// Scan the board for position that has maximum chain length
	// randomly pick one if there are more than one chain length
    	  public Point makeAIMove(ConnectFourBoard board) {
    		  Random r = new Random();
    	        int maximum = 0;
    	        ArrayList<Point> max_choice = new ArrayList<Point>();
    	        int arrayPosition;
    	        
    	        for (int i = 0; i < WIDTH; i++) {
    	                if (MaximumChain(i,board.getLowestEmptySlot(i), board) > maximum) {
    	                     maximum = MaximumChain(i,board.getLowestEmptySlot(i), board);
    	                 }
    	        }
    
                
    
    	        for (int i = 0; i < WIDTH; i++) {
    	            	// checking if more than one maximum chain exist
    	                if (MaximumChain(i,board.getLowestEmptySlot(i), board) == maximum) {
    	                    max_choice.add(new Point(i,HEIGHT));
    	                }
    	        }
    
    	        arrayPosition= r.nextInt(max_choice.size());
    	        return max_choice.get(arrayPosition);
    	    }
      // This method return the longest chain length.
      private int MaximumChain(int x, int y, ConnectFourBoard board) {
          int chainLength = 0;
          Piece pieces[][] = board.getPieces();
        // If there is not a piece at position (x,y) then work out long a chain it connects to
        if (pieces[x][y].getPieceColour() == ConnectFourPiece.ConnectFourPieceColour.NONE) {
            for (int i = -1; i <= 1; i++) { // These signify directions on a graph in the x-axis
                for (int j = -1; j <= 1; j++) { // These signify directions on a graph in the y-axis
                    int counter = 1;
                    
                    if ((x+(i*counter)) >= 0 && (x+(i*counter)) < WIDTH && (y+(j*counter)) >= 0 && (y+(j*counter)) < HEIGHT) {
                        // Loop while pieces are the players colour. Add 1 to counter for each iteration.
                        while (((x+(i*counter)) >= 0) && ((x+(i*counter)) < WIDTH) && ((y+(j*counter)) >= 0) && ((y+(j*counter)) < HEIGHT)
                                && pieces[x+(i*counter)][y+(j*counter)].getPieceColour() == getColour()) {
                            counter++;
                        }
                    }

                        if (!((x+(i*counter)) == x && (y+(j*counter)) == y)) {
                                if ((counter - 1) > chainLength) {
                                    chainLength = (counter - 1);
                                }
                        }
                }
            }
        }
        
        return chainLength;
		}


}
      