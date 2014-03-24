import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;

class OthelloHardComputerPlayer <C> extends HardComputerPlayer {
	
	public int whiteNum = 0;
	public int blackNum = 0;
    
	public OthelloHardComputerPlayer(String newName, Object newPlayerColour) {
		super(newName, newPlayerColour);
	}

    public Point makeAIMove(OthelloBoard board) {
        int maximumFlips = 0;
        ArrayList<Point> bestFlips = new ArrayList<Point>();
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getFlipsForPosition(i,j, board.getPieces()) > maximumFlips) {
                     maximumFlips = getFlipsForPosition(i,j,board.getPieces());
                }
            }
        }
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getFlipsForPosition(i,j, board.getPieces()) == maximumFlips) {
                    bestFlips.add(new Point(i,j));
                }
            }
        }
        
        Random r = new Random();
        int arrayPosition = r.nextInt(bestFlips.size());
        return bestFlips.get(arrayPosition);
    }
    
    
    /** 
    * This method returns how many pieces are flipped if the player places a piece at
    * position (x,y) on the board.
    * 
    * @param x - the x co-ordinate of the piece to count the flips for
    * @param y - the y co-ordinate of the piece to count the flips for
    * @param colour -  The board which contains all the pieces for the current game.
    *
    **/   
    
    private int getFlipsForPosition(int x, int y, OthelloPiece[][] boardPieces) {
        		
    		int flippedPieces = 0;
                OthelloPiece[][] newPieces = boardPieces;
                
                //already has piece here
                // OthelloPiece temp = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
                
                if(!newPieces[x][y].equals(Piece.OthelloPieceColour.NONE)) {
                    System.out.println(newPieces[x][y]);
                    return 1;
                }
                
                // it never goes through the code below just 52 to 63 always ..
                        
                    //check pieces
                    for(int i = 0; i < 4; i++)
                    {
                        int off = 1;
                        int tempX = x;
                        int tempY = y;
                        boolean anyValid = false;
                        int end = 1;
                            
                        //two sides of a direction
                        for(int j = 0; j < 2; j ++)
                        {
                            off = 1;
                            while(true)
                            {
                                switch(i)
                                {
                                    //vertical direction
                                        case 0:
                                            if(j == 0)
                                                tempX = x + off;
                                            else
                                                tempX = x - off;
                                            break;
                                        //horizontal direction
                                        case 1:
                                            if(j == 0)
                                                tempY = y + off;
                                            else
                                                tempY = y - off;
                                            break;
                                        //1 o'clock and 7 o'clock direction
                                        case 2:
                                            if(j == 0)
                                            {
                                                tempX = x + off;	
                                                tempY = y + off;
                                            }
                                            else
                                            {
                                                tempX = x - off;	
                                                tempY = y - off;
                                            }
                                            break;
                                        //11 o'clock and 5 o'clock direction
                                        case 3:
                                            if(j == 0)
                                            {
                                                tempX = x - off;	
                                                tempY = y + off;
                                            }
                                            else
                                            {
                                                tempX = x + off;	
                                                tempY = y - off;
                                            }
                                            break;
                                        default:
                                            //JOptionPane.showMessageDialog(of, "error!");
                                }
                                    
                                //search for the next direction
                                if(!anyValid && newPieces[tempX][tempY] == getColour())
                                {
                                    if(off > 1)
                                    {
                                        j --;
                                        anyValid = true;
                                        end = off;
                                    }
                                    break;
                                }
                                //no piece
                                if(newPieces[tempX][tempY] == getColour()) 
                                                            break;
                               
                                //can eat some piece (change colour of them)
                                if(anyValid)
            					{
            						if(off < end)	
            						{
            							flippedPieces ++;
            						}
            						else
            						{
            							anyValid = false;
            							break;
            						}
            					}
            					off ++;
                        }
                    }
                }
                System.out.println( " eated:  " + flippedPieces);
                return flippedPieces; // This isn't working at the moment...
    }

	public static void main(String[] args) {
		OthelloHardComputerPlayer compAI = new OthelloHardComputerPlayer("CompAI",Piece.OthelloPieceColour.WHITE);
        OthelloGame game = new OthelloGame(compAI, new HumanPlayer("Mabelle",Piece.OthelloPieceColour.BLACK));
        
        for(int j = 0; j < 8; j++){
        	for(int i = 0; i < 8; i++){
        		game.board.setLoadedPieces(i, j, new OthelloPiece(Piece.OthelloPieceColour.NONE));
        	}
        }
        
        game.board.setLoadedPieces(3, 4, new OthelloPiece(Piece.OthelloPieceColour.BLACK));
        game.board.setLoadedPieces(3, 3, new OthelloPiece(Piece.OthelloPieceColour.WHITE));
        game.board.setLoadedPieces(4, 4, new OthelloPiece(Piece.OthelloPieceColour.WHITE));
        game.board.setLoadedPieces(4, 3, new OthelloPiece(Piece.OthelloPieceColour.BLACK));
        
        
        OthelloPiece[][] test = game.board.getPieces();
        System.out.println(test[3][4].getPieceColour());
        
        
        int x = compAI.getFlipsForPosition(5,3, test);
        System.out.println(x);
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	int y = compAI.getFlipsForPosition(i,j, test);
                System.out.println(y);
            }
        }
    }


}
