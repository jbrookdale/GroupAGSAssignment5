
import java.util.Arrays;

import Piece.OthelloPieceColour;

/**
*
* @file OthelloBoard.java
* @author Adam Yaziji
* @date 30 Jan 2014
* 
*
* @brief This class creates the board for the game of Othello. 
* it is extended from the abstract class Board.
* 
* This class creates a constuctor for OthelloBoard.
* It sets the board, checks if the players chosen move is valid or not,
* checks if either one of the players had won.
*/


public class OthelloBoard extends Board <Piece.OthelloPieceColour,OthelloPiece> {

	 
	
	// creates an object of Piece which contains a white piece
	 private final OthelloPiece WHITE_PIECE = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
       // creates an object of Piece which contains a black piece 
	 private final OthelloPiece BLACK_PIECE = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
       // creates an object of Piece which contains 
	 private final OthelloPiece NONE_PIECE = new OthelloPiece(Piece.OthelloPieceColour.NONE);
    

/**
* This is the constructor to make the Othello Board
*
* @param OthelloPieces - an array which stores the pieces on the board
*/	

	private OthelloPiece[][] piecesToSwap = new OthelloPiece[HEIGHT][WIDTH];
	
	
/**	
* This is the constructor for the OthelloBoard class.
*
* @param height - this is the height of the board
* @param width - this is the width of the board
*/	
	public OthelloBoard (int height, int width){
            super(height, width);
            m_Pieces = new OthelloPiece[WIDTH][HEIGHT];
            m_PieceCount+=4;
            
       }


 /**
*
* This sets the position of the piece the player has chosen to move to 
* but only if its a valid move.
*
* @param x - the x co-ordinate of the players chosen move
* @param y - the y co-ordinate of the player chosen move
* @param setPiece - boolean variable which returns true if 
* 		      the poisition the players wants to move to is valid.
* @param colour -  a variable which stores which colour piece the player has, enum type.
*
* @return true -It returns true if the method returns successfully. 
**/
	
public boolean setPiece (int x, int y, OthelloPiece colour) {
        m_Pieces[x][y]=colour;
        
        return true;
        
    }
        

/** 
* This method is a boolean which returns true if the players chooses to place the piece in any 
* available positions out of the eight positions around the desired piece.
* 
* @param x - the x co-ordinate of the players chosen move
* @param y - the y co-ordinate of the player chosen move
* @param colour -  a variable which stores which colour piece the player has, enum type.
*
**/   
        
	public boolean move (int x,int y, OthelloPiece colour){
            boolean valid = false;
            if(move(x,y,1,0,colour)) valid=true;
            if(move(x,y,1,1,colour)) valid=true;
            if(move(x,y,1,-1,colour)) valid=true;
            if(move(x,y,0,1,colour)) valid=true;
            if(move(x,y,0,-1,colour)) valid=true;
            if(move(x,y,-1,0,colour)) valid=true;
            if(move(x,y,-1,1,colour)) valid=true;
            if(move(x,y,-1,-1,colour)) valid=true;
            setThePieces();
            if(valid){
                m_PieceCount++;
            }
            return (valid);
          }


/** 
* This method is a boolean which returns true if the players chooses to place the piece in any 
* available positions out of the eight positions around the desired piece.
*
* 
* @ param x - the x co-ordinate of the players chosen move
* @ param y - the y co-ordinate of the player chosen move
* @ param colour -  a variable which stores which colour piece the player has, enum type.
*
**/   

        private boolean anyMove (int x,int y, OthelloPiece colour){
            boolean valid = false;
            if(move(x,y,1,0,colour)) valid=true;
            if(move(x,y,1,1,colour)) valid=true;
            if(move(x,y,1,-1,colour)) valid=true;
            if(move(x,y,0,1,colour)) valid=true;
            if(move(x,y,0,-1,colour)) valid=true;
            if(move(x,y,-1,0,colour)) valid=true;
            if(move(x,y,-1,1,colour)) valid=true;
            if(move(x,y,-1,-1,colour)) valid=true;
            return (valid);
          }

/**
* This method clears the array with the pieces in.
*/

        public void clearPieces(){
            for(int i =0; i<WIDTH;i++){
                for(int j = 0 ; j<HEIGHT;j++){
                    piecesToSwap[i][j]=null;
                }
            }
        }
          
/**
* This method returns the pieces to swap from the array, which ultimately places the 
* pieces on the board.
* 
* @return piecesToSwap - this returns the pieces to swap
*/
         
    	public OthelloPiece[][] setPieces(){
        return piecesToSwap;
         
    }

/**
* This method sets the pieces on the board. 
*/

	private void setThePieces(){
            for(int i = 0; i<HEIGHT;i++){
              for(int j = 0; j<WIDTH;j++){
                  if(piecesToSwap[i][j]==null){
                      
                  }else{
                      m_Pieces[i][j]=piecesToSwap[i][j];
                      
                  }
              }
            }
        }

	
/**
* This method checks if there is a valid move anywhere on the board.
*
* @param boolean- anyValid returns true if there is a valid move anywhere on the board
* and false if there is no valid move.
*
*/

        public boolean anyValid(OthelloPiece colour){
            boolean valid = false;
            for(int i=0; i<WIDTH;i++){
                for(int j=0;j<HEIGHT;j++){
                    if(anyMove(i,j,colour)){
                        valid = true;
                    }
                }    
            }
                clearPieces();
            return valid;
        }
	
/**
*
* This is the method to decide whether the move the player wishes to make is valid or not.
*
* @param colour
* @param x - the x co-ordinate of the players chosen move
* @param y - the y co-ordinate of the player chosen move
* @param xdirection - the direction along the x-axis the chainswap has to follow
* @param ydirection - the direction along the x-axis the chainswap has to follow
* @param colour -  a variable which stores which colour piece the player has, enum type
*/


	private boolean move (int x, int y, int xdirection, int ydirection, OthelloPiece colour){
            
            OthelloPiece otherColour;
            int xsearch = x + xdirection;
            int ysearch = y + ydirection;


            if (colour.getPieceColour() == Piece.OthelloPieceColour.WHITE){
                otherColour = BLACK_PIECE;
               
            }else{
                otherColour = WHITE_PIECE;
               
            }
            if(m_Pieces[x][y].getPieceColour()==Piece.OthelloPieceColour.NONE){
               
                if(onBoard(xsearch,ysearch)){
                    if(m_Pieces[xsearch][ysearch].getPieceColour()==otherColour.getPieceColour()){
                    while(onBoard(xsearch,ysearch)&& m_Pieces[xsearch][ysearch].getPieceColour()==otherColour.getPieceColour()){
                        xsearch+=xdirection;
                        ysearch+=ydirection;
                    }
                   
                    if(onBoard(xsearch,ysearch)&&m_Pieces[xsearch][ysearch].getPieceColour()== colour.getPieceColour()){
                        xsearch-=xdirection;
                        ysearch-=ydirection;
                        piecesToSwap[xsearch][ysearch]=colour;
                        while(m_Pieces[xsearch][ysearch].getPieceColour()==otherColour.getPieceColour()){
                            
                            xsearch-=xdirection; 
                            ysearch-=ydirection;
                            piecesToSwap[xsearch][ysearch]=colour;                          
                        }
                         return true;
                    }
                }
                }
            }else{
                return false;
            }
            return false;
	}
		
	
/**
*
* This class is a method which returns true if the chain of pieces is still on the board
* or returns false if the chain of pieces has hit the edge of the board.
*
* @param x - the x co-ordinate of the players chosen move
* @param y - the y co-ordinate of the player chosen move
*
*
*/	
	private boolean onBoard(int x, int y){
            if((x<WIDTH&&y<HEIGHT)){
                if(0<=x&&0<=y){
                    return true;
                }
                    return false;
            }else{
                    return false;
            }
	}
        
/**
* This is a boolean method which returns false 
*
* @return false - This returns false is the method is not run successfully
*/

	public boolean CheckWin(){
	            return false;
	        }

/**
* This is a boolean method which returns isFull() method.
*
* @param int - totalPieces - this is the number of total pieces on the board
* @return isFull - tells checkWin if the board is full or not
*/	        
  	 public boolean checkWin (int totalPieces){
		return isFull();
	}
		
	@Override
	boolean checkWin() {
		return false;
	}
			
/**
* This method is used to get the pieces on the board.
*
*@return m_Pieces - this is an array holding all the pieces on the board
*/

    	public OthelloPiece[][] getPieces() {
       	 return m_Pieces;
    	}


	

/**
* This method initiates the board with two white pieces and two black pieces in 
* the middle of the board. In the poistions (3,3),(4,4),(3,4),(4,3).
*/
	     public void setBoard(){
                
	        m_Pieces[3][3]= WHITE_PIECE;
            m_Pieces[4][4]= WHITE_PIECE;
            m_Pieces[3][4]= BLACK_PIECE;
            m_Pieces[4][3]= BLACK_PIECE;
            for(int x=0;x<WIDTH;x++){
                for(int y=0;y<HEIGHT;y++){
                    if(m_Pieces[x][y]==null){
                        m_Pieces[x][y]=NONE_PIECE;
                    }
                }
            }
        }
 

/**
* This method increments the piece count.
*/

	public  void incPieceCount () {
            m_PieceCount++;
	}
 

       
/**
* This method decrements the piece count.
*/	
	public void decPieceCount () {
		m_PieceCount --;
	}		
	
	
	
/**
* This method returns the piece count.
*
*@return m_PieceCount - This returns the piece count on the board
*/
        
	public int getPieceCount () {
            return m_PieceCount;
	}
	
/**
* This is a test for incPiececount and checkWin
*/

	public static void test2() {
       	OthelloBoard Board = new OthelloBoard(8,8);
       	Board.setBoard();
       	Board.incPieceCount();
       	System.out.println(Board.getPieceCount());
       	System.out.println(Board.move(0, 3, Board.WHITE_PIECE));
       	Board.m_Pieces[5][3]=Board.WHITE_PIECE;
       	System.out.println("");
       	System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.checkWin() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + Board.checkWin());
       	System.out.println("");
    	}
	
	
 /**
* This is a test for decPiececount and CheckWin
*/	
	public static void test3() {
	       OthelloBoard Board = new OthelloBoard(8,8);
	       Board.setBoard();
	       Board.decPieceCount();
	       System.out.println(Board.getPieceCount());
	       System.out.println(Board.move(2, 3, Board.WHITE_PIECE));
	       Board.m_Pieces[5][3]=Board.WHITE_PIECE;
	       System.out.println("");
	       System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.CheckWin() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + Board.CheckWin());
	       System.out.println("");
	    }
	

/**
* This is a test for setPiece
*/	

	public static void test4() {
		OthelloBoard Board = new OthelloBoard(8,8);
		Board.setBoard();
		Board.decPieceCount();
		System.out.println(Board.getPieceCount());
		System.out.println(Board.move(2, 3, Board.WHITE_PIECE));
		Board.m_Pieces[5][3]=Board.WHITE_PIECE;
		System.out.println("");
		System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.setPiece() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + Board.setPiece(3,5,Board.BLACK_PIECE));
		System.out.println("");
	}
	
	
/**	
* This is a test for move
*/
	
	public static void test5() {
		OthelloBoard Board = new OthelloBoard(8,8);
	       Board.setBoard();
	       Board.decPieceCount();
	       System.out.println(Board.getPieceCount());
	       System.out.println(Board.move(2, 3, Board.WHITE_PIECE));
	       Board.m_Pieces[5][3]=Board.WHITE_PIECE;
	       System.out.println("");
	       System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.move() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + Board.move(1,2,Board.BLACK_PIECE));
	       System.out.println("");
	}
	
	

/**	
* This is a test for anyMove
*/	
	public static void test6() {
		OthelloBoard Board = new OthelloBoard(8,8);
       	Board.setBoard();
		Board.decPieceCount();
       	System.out.println(Board.getPieceCount());	
		System.out.println(Board.move(2, 3, Board.WHITE_PIECE));
		Board.m_Pieces[5][3]=Board.WHITE_PIECE;
		System.out.println("");
		System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.anyMove() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + Board.anyMove(1,2,Board.BLACK_PIECE));
		System.out.println("");
	}
		
/**	
* This is a test for getPieces
*/		
	public static void test7() {
			OthelloBoard Board = new OthelloBoard(8,8);
		      	Board.setBoard();
			Board.decPieceCount();
		      	System.out.println(Board.getPieceCount());	
			System.out.println(Board.move(2, 3, Board.WHITE_PIECE));
			Board.m_Pieces[5][3]=Board.WHITE_PIECE;
			System.out.println("");
			System.out.println("");
			Board.checkWin();
			System.out.println("Valid inputs");
			System.out.println("OthelloBoard.clearPieces() - Begin");
			System.out.println("Expected output: 0");
			System.out.println("");
			Board.clearPieces();
			System.out.println("Actual output: " + Board.getPieces());
			System.out.println("");
	}
	

/**	
* This is a test for setPiece
*/
	
	public static void test8() {
				OthelloBoard Board = new OthelloBoard(8,8);
			      	Board.setBoard();
				Board.decPieceCount();
			      	System.out.println(Board.getPieceCount());	
				System.out.println(Board.move(2, 3, Board.WHITE_PIECE));
				Board.m_Pieces[5][3]=Board.WHITE_PIECE;
				System.out.println("");
				System.out.println("");
				Board.checkWin();
				System.out.println("Valid inputs");
				System.out.println("OthelloBoard.clearPieces() - Begin");
				System.out.println("Expected output: Throws exception");
				System.out.println("");
				try{
					
				}catch(UnsupportedOperationException e){

				}
				System.out.println("");
		}
				
/** 		
* This is the main method of the OthelloBoard class. This method is used to test the 
* methods within the class with both valid and invalid parameters.
*/		
	public static void main (String[] args){
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
		test8();
	}
}