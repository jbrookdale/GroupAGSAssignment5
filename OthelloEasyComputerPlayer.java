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

        m_columnNum = r.nextInt(WIDTH);
        m_rowNum = r.nextInt(HEIGHT);
        OthelloPiece piece;
        if (getColour() == Piece.OthelloPieceColour.BLACK) {
            piece = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
        } else {
            piece = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
        }
        
        while (!board.anyMove(m_columnNum, m_rowNum,piece)) {
        	m_columnNum = r.nextInt(WIDTH);
        	m_rowNum = r.nextInt(HEIGHT);
        }
         System.out.println(m_columnNum);
        Point m_movePosition = new Point(m_columnNum, m_rowNum);
        return m_movePosition;
    }
	
	    /**< constant for the size of the board in x direction */ 
    private static final int WIDTH = 10; 
    
    /**< constant for the size of the board in y direction */ 
    private static final int HEIGHT = 7; 
    
    private static int m_columnNum;
    private static int m_rowNum;
	
	//method to generate random move
}
