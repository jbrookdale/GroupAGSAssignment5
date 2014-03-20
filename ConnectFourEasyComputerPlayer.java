import java.util.Arrays;
import java.util.Random;
import java.awt.Point;

class ConnectFourEasyComputerPlayer extends EasyComputerPlayer {

	
    public ConnectFourEasyComputerPlayer(String newName, Object newPlayerColour) {
		super(newName, newPlayerColour);
	}


	public static void main(String[] args) {
        // Tests go here
    	randomMove();
    }
  
	// not sure how this is going to work
    // method is to generate random move, 
	//the move method will check if the generated position is a valid move and there are valid move
	//if true move else report invalid move and call the method again
    public static Point move(ConnectFourBoard board){
        Random r = new Random( );
        int Position;
        i = r.nextInt(WIDTH);
        while (!board.getPieces[i][7] == Piece.ConnectFourPieceColour EMPTY_PIECE) {
            i = r.nextInt(WIDTH);
        }
         System.out.println(i);
        Point movePosition = new Point(i,7);
       return movePosition;
    }
    
	/**< constant for the size of the board in x direction */ 
	private static final int WIDTH = 10; 
	
	/**< constant for the size of the board in y direction */ 
	private static final int HEIGHT = 7; 
	
	private static int i;
	
}