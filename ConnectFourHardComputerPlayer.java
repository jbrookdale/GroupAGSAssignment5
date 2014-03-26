import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

class ConnectFourHardComputerPlayer <C> extends HardComputerPlayer {


    private static int HEIGHT = 7;
    private static int WIDTH = 10;
    private final static int NEGATIVE_X_AXIS = -1;
	private final static int POSITIVE_X_AXIS = 1;
	private final static int NEGATIVE_Y_AXIS = -1;
	private final static int POSITIVE_Y_AXIS = 1;
	
    
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
    	      ArrayList<Point> m_MaxChoice = new ArrayList<Point>();
    	      int arrayPosition;
    	        
    	      for (int i = 0; i < WIDTH; i++) {
    	           if (MaximumChain(i,board.getLowestEmptySlot(i), board) > maximum) {
                   maximum = MaximumChain(i,board.getLowestEmptySlot(i), board);
  	               }
    	      }
    
            
    	      for (int i = 0; i < WIDTH; i++) {
    	            	// checking if more than one maximum chain exist
                   if (MaximumChain(i,board.getLowestEmptySlot(i), board) == maximum) {
                   m_MaxChoice.add(new Point(i,HEIGHT));
    	           }
    	      }
    
    	      arrayPosition= r.nextInt(m_MaxChoice.size());
    	      return m_MaxChoice.get(arrayPosition);
    	  }
    	  
      // This method return the longest chain length.
      private int MaximumChain(int x, int y, ConnectFourBoard board) {
          int m_chainLength = 0;
          Piece m_Pieces[][] = board.getPieces();
        // If there is not a piece at position (x,y) then work out long a chain it connects to
        if (m_Pieces[x][y].getPieceColour() == ConnectFourPiece.ConnectFourPieceColour.NONE) {
        	 for (int i = NEGATIVE_X_AXIS; i <= POSITIVE_X_AXIS; i++) { // These signify directions on a graph in the x-axis
                 for (int j = NEGATIVE_Y_AXIS; j <= POSITIVE_Y_AXIS; j++) { // These signify directions on a graph in the y-axis
                    int counter = 1;
                    
                    if ((x+(i*counter)) >= 0 && (x+(i*counter)) < WIDTH && (y+(j*counter)) >= 0 && (y+(j*counter)) < HEIGHT) {
                        // Loop while pieces are the players colour. Add 1 to counter for each iteration.
                        while (((x+(i*counter)) >= 0) && ((x+(i*counter)) < WIDTH) && ((y+(j*counter)) >= 0) && ((y+(j*counter)) < HEIGHT)
                                && m_Pieces[x+(i*counter)][y+(j*counter)].getPieceColour() == getColour()) {
                            counter++;
                        }
                    }

                        if (!((x+(i*counter)) == x && (y+(j*counter)) == y)) {
                                if ((counter - 1) > m_chainLength) {
                                	m_chainLength = (counter - 1);
                                }
                        }
                }
            }
        }
        
        return m_chainLength;
        }
}
      
