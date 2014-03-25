import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

class ConnectFourHardComputerPlayer <C> extends HardComputerPlayer {

    
    private static int Height = 10;
    private static int Width = 7;
    static int n = 4;
    //Equation for checking the winning place on Connect4board
    //winPlace=(4 * w *h) - (3*w*n) - (3*h*n) + (3*w) + (3*h) - (4*n) + (2*n*n);
   // private static final int winPlaces =143;
    public ConnectFourHardComputerPlayer(String newName, C newPlayerColour) {
 		super(newName, newPlayerColour);
 	}



	public static void main(String[] args) {
        // Tests go here
    }
    
    
   
	//The move made should extend the longest chain(horizontal, vertical or diagonal) 
	//on the board by one
	// randomly pick one if such equal chain exist
    	  public Point makeAIMove(ConnectFourBoard board) {
    	        int maximum = 0;
    	        ArrayList<Point> max_choice = new ArrayList<Point>();
    	        
    	        for (int i = 0; i < Height; i++) {
    	                if (MaximumChain(i,board.getLowestEmptySlot(i), board) > maximum) {
    	                     maximum = MaximumChain(i,board.getLowestEmptySlot(i), board);
    	                
    	            }
    	        }
    	        
    	        for (int i = 0; i < Height; i++) {
    	            	// checking if more than one maximum chain exist
    	                if (MaximumChain(i,board.getLowestEmptySlot(i), board) == maximum) {
    	                    max_choice.add(new Point(i,7));
    	                }
    	        }
    	        
    	        Random r = new Random();
    	        int arrayPosition = r.nextInt(max_choice.size());
    	        return max_choice.get(arrayPosition);
    	    }
      // This method should return the longest chain
    // We need to return just the column to pace the piece after getting the maximum chain 
      private int MaximumChain(int x, int y, ConnectFourBoard board) {
      int chainLength = 0;
      Piece pieces[][] = board.getPieces();
        // If there is not a piece at position (x,y) then work out how many flips it causes
        // Otherwise it doesn't flip anything.
        if (pieces[x][y].getPieceColour() == ConnectFourPiece.ConnectFourPieceColour.NONE) {
            for (int i = -1; i <= 1; i++) { // These signify directions on a graph in the x-axis
                for (int j = -1; j <= 1; j++) { // These signify directions on a graph in the y-axis
                    int counter = 1;
                    
                    if ((x+(i*counter)) >= 0 && (x+(i*counter)) < 10 && (y+(j*counter)) >= 0 && (y+(j*counter)) < 7) {
                        // Loop while pieces are the opponents colour. Add 1 to counter for each iteration.
                        while (pieces[x+(i*counter)][y+(j*counter)].getPieceColour() == getColour()) {
                            counter++;
                        }
                    }
                    

                    // If the piece which stops the loop above is the players own colour, add the counter
                    // To the total number of flippied pieces, else don't add anything.
                    if ((x+(i*counter)) >= 0 && (x+(i*counter)) < 8 && (y+(j*counter)) >= 0 && (y+(j*counter)) < 8) {
                        if (!((x+(i*counter)) == x && (y+(j*counter)) == y)) {
                            
                                if ((counter - 1) > chainLength)
                                chainLength = (counter - 1);
                            
                        }
                    }
                }
            }
        }
        return chainLength;
		}


}
      