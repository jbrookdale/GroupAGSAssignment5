import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;

class OthelloHardComputerPlayer <C> extends HardComputerPlayer {
    


	public OthelloHardComputerPlayer(String newName, Object newPlayerColour) {
		super(newName, newPlayerColour);
	}

    public Point makeAIMove(OthelloBoard board) {
        int maximumFlips = 0;
        ArrayList<Point> bestFlips = new ArrayList<Point>();
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getFlipsForPosition(i,j, board) > maximumFlips) {
                     maximumFlips = getFlipsForPosition(i,j,board);
                }
            }
        }
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getFlipsForPosition(i,j, board) == maximumFlips) {
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
    
    private int getFlipsForPosition(int x, int y, OthelloBoard board) {
        int flippedPieces = 0;
                OthelloPiece[][] boardPieces = board.getPieces();
                //already has piece here
                if(boardPieces[x][y] != new OthelloPiece(OthelloPiece.OthelloPieceColour.NONE)) {
                    //System.out.println(boardPieces[x][y]);
                    return 0;
                }
                        
                    //check pieces
                    for(int i = 0; i < 4; i++)
                    {
                        int off = 1;
                        int tempX = x;
                        int tempY = y;
                        boolean validPosition = false;
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
                                if(!validPosition && boardPieces[tempX][tempY] == getColour())
                                {
                                    if(off > 1)
                                    {
                                        j --;
                                        validPosition = true;
                                        end = off;
                                    }
                                    break;
                                }
                                //no piece
                                if(boardPieces[tempX][tempY] == getColour()) 
                                                            break;
                               
                                //can eat some piece (change colour of them)
                                if(validPosition)
                                {
                                    // There is a white between 2 black pieces.
                                    if(off < end)	
                                    {
                                        flippedPieces++;
                                }
                                else
                                {
                                    validPosition = false;
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
        // Tests go here
    }
	

}