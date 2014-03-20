class OthelloEasyComputerPlayer extends EasyComputerPlayer{
    
    public OthelloEasyComputerPlayer(String newName, Object newPlayerColour) {
		super(newName, newPlayerColour);
		
	}

	public static void main(String[] args) {
        // Tests go here
    }
    
    public static Point move(ConnectFourBoard board){
        Random r = new Random( );
        int Position;
        i = r.nextInt(WIDTH);
        j = r.nextInt(HEIGHT);
        while (!board.getPieces[i][j] == Piece.ConnectFourPieceColour EMPTY_PIECE) {
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