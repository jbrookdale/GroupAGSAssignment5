import java.util.Arrays;
import java.util.Random;
import java.awt.Point;

class ConnectFourEasyComputerPlayer <C> extends EasyComputerPlayer {

	
    public ConnectFourEasyComputerPlayer(String newName, C newPlayerColour) {
		super(newName, newPlayerColour);
	}


	public static void main(String[] args) {
        // Tests go here
    	
    }
  
    public Point move(ConnectFourBoard board){
        Random r = new Random( );
        int Position;
        i = r.nextInt(WIDTH);
        while(!(board.getLowestEmptySlot(i) < HEIGHT)) {
            i = r.nextInt(WIDTH);
            System.out.println(board.getLowestEmptySlot(i));
        }
        System.out.println(i);
        Point movePosition = new Point(i,6);
       return movePosition;
    }
    
	/**< constant for the size of the board in x direction */ 
	private static final int WIDTH = 10; 
	
	/**< constant for the size of the board in y direction */ 
	private static final int HEIGHT = 7; 
	
	private static int i;
	
}