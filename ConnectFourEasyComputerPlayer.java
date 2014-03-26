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
  
    public Point makeAIMove(ConnectFourBoard board){
        Random r = new Random();
        m_columnNum = r.nextInt(WIDTH);
        while(!(board.getLowestEmptySlot(m_columnNum) < HEIGHT)) {
        	m_columnNum = r.nextInt(WIDTH);
            System.out.println(board.getLowestEmptySlot(m_columnNum));
        }
        System.out.println(m_columnNum);
        Point movePosition = new Point(m_columnNum,6);
       return movePosition;
    }
    
	/**< constant for the size of the board in x direction */ 
	private static final int WIDTH = 10; 
	
	/**< constant for the size of the board in y direction */ 
	private static final int HEIGHT = 7; 
	
	private static int m_columnNum;
	
}
