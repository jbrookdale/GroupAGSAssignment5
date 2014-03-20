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
        int Position;// = new int[WIDTH][HEIGHT];
        i = r.nextInt(WIDTH);
        while (!board.getPieces[i][7] == Piece.ConnectFourPieceColour EMPTY_PIECE) {
            i = r.nextInt(WIDTH);
        }
          
          //j = r.nextInt(7);
         //movePosition[i][j] =Position[i][j];
         //System.out.println( j);
         System.out.println(i);
       return new Point(i,7);
    }
    
	/**< constant for the size of the board in x direction */ 
	private static final int WIDTH = 10; 
	
	/**< constant for the size of the board in y direction */ 
	private static final int HEIGHT = 7; 
	
	static int movePosition [][] = new int[WIDTH][HEIGHT];
	private static int i;
	private static int j;
	
}