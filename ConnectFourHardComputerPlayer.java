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
    	            for (int j = 0; j < Width; j++) {
    	                if (MaximumChain(i,j, board) > maximum) {
    	                     maximum = MaximumChain(i,j, board);
    	                }
    	            }
    	        }
    	        
    	        for (int i = 0; i < Height; i++) {
    	            for (int j = 0; j < Width; j++) {
    	            	// checking if more than one maximum chain exist
    	                if (MaximumChain(i,j, board) == maximum) {
    	                    max_choice.add(new Point(i,7));
    	                }
    	            }
    	        }
    	        
    	        Random r = new Random();
    	        int arrayPosition = r.nextInt(max_choice.size());
    	        return max_choice.get(arrayPosition);
    	    }
      // This method should return the longest chain
    // We need to return just the column to pace the piece after getting the maximum chain 
      private int MaximumChain(int i, int j, ConnectFourBoard board) {
			
			return 1; 
		}


}
      