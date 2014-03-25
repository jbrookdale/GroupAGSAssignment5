import java.awt.Point;
import java.util.Random;

class OthelloEasyComputerPlayer <C> extends EasyComputerPlayer{
    
    public OthelloEasyComputerPlayer(String newName, C newPlayerColour) {
		super(newName, newPlayerColour);
		
	}

	public static void main(String[] args) {
        // Tests go here
    }
    
    public Point makeAIMove(OthelloBoard board){
        Random r = new Random( );
        int Position;
        i = r.nextInt(WIDTH);
        j = r.nextInt(HEIGHT);
        OthelloPiece piece;
        if (getColour() == Piece.OthelloPieceColour.BLACK) {
            piece = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
        } else {
            piece = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
        }
        
        while (!board.anyMove(i,j,piece)) {
            i = r.nextInt(WIDTH);
            j = r.nextInt(HEIGHT);
        }
         System.out.println(i);
        Point movePosition = new Point(i,j);
       return movePosition;
    }
	
	    /**< constant for the size of the board in x direction */ 
    private static final int WIDTH = 10; 
    
    /**< constant for the size of the board in y direction */ 
    private static final int HEIGHT = 7; 
    
    private static int i;
    private static int j;
	
	//method to generate ramdom move
}