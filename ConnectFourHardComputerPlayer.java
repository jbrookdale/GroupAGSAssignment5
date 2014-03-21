import java.awt.Point;
class ConnectFourHardComputerPlayer <C> extends HardComputerPlayer {

    private int numPieces;
    private static final int maxPieces = 70, Empty = 2;
    public static boolean[][][] map;
    static int w = 10;
    static int h =7;
    static int n =4;
    //Equation for checking the winning place on Connect4board
    //winPlace=(4 * w *h) - (3*w*n) - (3*h*n) + (3*w) + (3*h) - (4*n) + (2*n*n);
    private static final int winPlaces =143;
    public static void main(String[] args) {
        // Tests go here
    }
    
    
    // this method checks the state of the board 
      public void Connect4State() {
     
      }
      public void Move(int x, int y) {
    	  
    	}
      
      // point might not be suitable. may be 2d jlabel array
      public Point computerMove(int i, int j) {
		return null;
      }
}
      