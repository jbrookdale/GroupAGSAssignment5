/*--80 Characters Wide---------- Remove before Submitting --------------------*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * @file OthelloGameGui.java
 * @author Shannon Gahring
 * @date 30 Jan 2014
 * @see GameGui.java for inherited methods.
 *
 * @brief This class creates the GUI for the game of Othello and 
 * 		  is extended from the GameGui class.
 * 
 * This class animates the move of a piece being added,
 * displays a move feedback if a move is not allowed,
 * displays the winner of the game,
 * controls the pause game button and
 * updates the display every time a move is made.
 */
public class OthelloGameGUI extends GameGUI {
    JFrame window;
	/** 
	 * This is the constructor for the OthelloGameGui class.
	 *
	 * @param title -the variable storing the title of the window.
	 * @param width -the variable storing width of the .
	 * @param height -the variable storing height of the .
	 */
	public OthelloGameGUI(String title, int width, int height) {
		//Sets title, width, and height to the value of GameGui
		super(title,width,height);
        URL location = OthelloGameGUI.class.getProtectionDomain().getCodeSource().getLocation();
		System.out.println(location.getFile());
		blackPiece = new ImageIcon(getClass().getResource("PieceBlack.png"));
		whitePiece = new ImageIcon(getClass().getResource("PieceWhite.png"));
        whiteWinningPiece = new ImageIcon(getClass().getResource("PieceWhiteFinal.png"));
        blackWinningPiece = new ImageIcon(getClass().getResource("PieceBlackFinal.png"));
		whiteToBlackPiece = new ImageIcon(getClass().getResource("PieceWhiteToBlack75.gif"));
		blackToWhitePiece = new ImageIcon(getClass().getResource("PieceBlackToWhite75.gif"));
	}
	
	/**
	 * This is the blank constructor that initializes the pieces.  Both the
	 * pieces that move and stay the same
	 * 
	 */
	

	/**
	 * This gets the height of the board
	 * @param height -height of the board
	 * @return the height of the board
	 */
	public int getHeight(int height){
		return height;
	}

	/**
	 * This gets the Width of the board
	 * @param width -width of the board
	 * @return the width of the board
	 */
	public int getWidth(int width){
		return width;
	}

	/** 
	 * This is the method for animating the move of a piece 
	 * on the board of the Othello game.
	 *
	 * @return moveMade -if the move was successful moveMade returns true and false if not.
	 */
	public boolean animateMove() {

		boolean moveMade = false;
		//Check if this is actually what they want
		if (!moveMade) {
			moveMade = true;
		}
		return moveMade;
		
	}

	/** 
	 * This is the method for displaying the feedback message 
	 * if a player makes an incorrect move.
	 *
	 * @param message -the variable storing the text of the message.
	 */
	public boolean displayMoveFeedback(String message) {

		if(animateMove() == true){

		}
		else{
			JOptionPane.showMessageDialog(null, message);
			System.out.println("Invalid move.  Try another");
			//return message;
		}
		return true;
	}


	/** 
	 * This is the method for updating the GUI display of Othello game it will 
	 * keep track of the pieces, time, score and piece count.
	 * 
	 * Take amount of pieces from the player.  From get and sets
	 *
	 * Note
	 *
	 * @param pieces -the array variable storing all the pieces on the board.
	 * @param time -the variable storing amount of the time elapsed since the start of the game.
	 * @param score -the variable storing the score of each player.
	 * @param pieceCount -the variable storing the number of pieces on the board.
	 */
	 boolean updateDisplay(OthelloPiece[][] pieces, Date time, int[] score, int[] pieceCount) {
		
		return false;

	}


	 /**
	  * This method creates the GUI and board, adds current score, checks to see what button
	  * is pushed and sends that to the action listener.  This essentially sets the original
	  * board and then sends it to the action listener to do the rest.
	  */
	void creatingGui(){
		window = new JFrame("Othello                 "+m_player1Name+" turn      "+m_player1Name+" Score: " +  gameCheck.getPlayer1Score()
                        + "       "  +m_player2Name+" Score: " + gameCheck.getPlayer2Score());
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.setLayout(new GridLayout(TOTALWIDTH,TOTALHEIGHT));
		window.setIconImage(new ImageIcon(this.getClass()
                .getResource("Othello.jpeg")).getImage());
		
		//window.add(m_player1Score);
		//m_player1Score.setText(score.getPieceCount());
		GUIHandler handler = new GUIHandler(); //test.new GUIHandler();
		//player1 = new JLabel("Player1 Score: ");
		gridButtons = new JButton[TOTALWIDTH][TOTALHEIGHT];
		for(int y = 0; y < TOTALWIDTH; y++){ 
			for(int x = 0; x < TOTALHEIGHT; x++){
				gridButtons[x][y]=new JButton("");
				gridButtons[x][y].setBackground(Color.GREEN);
				gridButtons[x][y].addMouseListener(handler);
				//gridButtons[x][y].setBounds(4, 4, 6, 6);
				gridButtons[x][y].setPreferredSize(new Dimension(BOARDWIDTH,BOARDHEIGHT));
				window.add(gridButtons[x][y]); //adds button to grid
			}
		}
		
		gridButtons[3][3].setIcon(whitePiece);
		gridButtons[4][4].setIcon(whitePiece);
		gridButtons[3][4].setIcon(blackPiece);
		gridButtons[4][3].setIcon(blackPiece);
                
		window.setJMenuBar(creatingMenu());
        window.addWindowListener(new WindowAdapter() {
                    
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to exit the program?", 
                "Exit Program Message Box",
                JOptionPane.YES_NO_OPTION);
                
                //if yes is pressed close m_Frame else keep open
                if (confirmed == JOptionPane.YES_OPTION) {
                   	window.dispose();
                   
                } 
            }
        });

		window.getRootPane().setDefaultButton(defaultButton);
		window.pack();
		window.setVisible(true);

	}

	/**
	 * This method creates the menu and then adds it to the board.
	 * It adds the pause button, save game, and load previous game.  
	 * These have no been fully implemented yet.
	 * @return -It returns 
	 */

	public boolean displayPlayAgain(String message) {
        int reply = JOptionPane.showConfirmDialog(null, message , message, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	window.dispose();
        	gameCheck = new OthelloGame(m_player1Name,m_player1Type,m_player1Colour.getPieceColour(),
				m_player2Name,m_player2Type,m_player2Colour.getPieceColour());
        	String[] s = gameCheck.getPlayerNames();
            creatingGui();
            
        } else {
            dispose();
        }
        return reply == JOptionPane.YES_OPTION;
    }


    /**
    * setups up the menu bar which has options to load a game, save a game, pause the game
    * and start a new game.
    * load,save, and pause have not been implemented in this version
    */  
	private JMenuBar creatingMenu(){
		menuBar = new JMenuBar();
		mainMenu = new JMenu("Menu");

		mainMenu.setMnemonic(KeyEvent.VK_A);
		mainMenu.getAccessibleContext();
		menuBar.add(mainMenu);
        javax.swing.JMenuItem newGame=new javax.swing.JMenuItem();
        newGame.setText("New Game");
        newGame.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent event){
        		displayPlayAgain("play again?");
        	}
        });
		mainMenu.add(newGame);
		menuItem = new JMenuItem("Pause Game",
				KeyEvent.VK_T);

		menuItem.setMnemonic(KeyEvent.VK_T); 
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext();

		mainMenu.add(menuItem);

		mainMenu.setMnemonic(KeyEvent.VK_A);
		mainMenu.getAccessibleContext();
		menuBar.add(mainMenu);
		menuItem = new JMenuItem("Save Game",
				KeyEvent.VK_T);
		menuItem.setMnemonic(KeyEvent.VK_T); 
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext();
		mainMenu.add(menuItem);

		mainMenu.setMnemonic(KeyEvent.VK_A);
		mainMenu.getAccessibleContext();
		menuBar.add(mainMenu);
		menuItem = new JMenuItem("Load Game",
				KeyEvent.VK_T);
		menuItem.setMnemonic(KeyEvent.VK_T); 
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext();
		mainMenu.add(menuItem);
		return menuBar;
	}



	private JLabel[][] m_pieceLabels; /**< array variable storing all the pieces on the board to be displayed */ 
	//private JLabel m_player1Name; /**< variable storing player one's name to be displayed */ 
	private String m_player1Name;
	//private JLabel m_player2Name; /**< variable storing player two's name to be displayed */ 
	private String m_player2Name;
	private JLabel m_player1Score; /**< variable storing player one's score to be displayed */ 
	private JLabel m_player2Score; /**< variable storing player two's score to be displayed */ 
	private String m_player1Type= "Human";/**< variable storing player one's type*/ 
	private String m_player2Type= "Human";/**< variable storing player two's type*/ 
	private OthelloPiece m_player1Colour = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
	private OthelloPiece m_player2Colour = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
	private static JButton defaultButton = null;
	private final int TOTALWIDTH = 8;
	private final int TOTALHEIGHT = 8;
	private final int BOARDWIDTH = 90;
	private final int BOARDHEIGHT = 85;
	
	OthelloBoard board = new OthelloBoard(TOTALHEIGHT,TOTALWIDTH);
	OthelloGame gameCheck = new OthelloGame(m_player1Name,m_player1Type,m_player1Colour.getPieceColour(),
				m_player2Name,m_player2Type,m_player2Colour.getPieceColour());

	//Pieces and Board
	static JButton[][] gridButtons;
	private ImageIcon blackPiece;
	private ImageIcon whitePiece;
    private ImageIcon blackWinningPiece;
	private ImageIcon whiteWinningPiece;
	private ImageIcon whiteToBlackPiece;
	private ImageIcon blackToWhitePiece;

	//Menu bar
	private static JMenuBar menuBar;
	private static JMenu mainMenu;
	private static JMenuItem menuItem;
        

	
	/**
	 * This is a separate class inside of OthelloGameGui that receives
	 * any action done on the board.  If a button is pushed, it is sent
	 * here to perform some action.
	 * 
 	 * This method is where the action is performed.  It searches 
	 * through the board and finds our which piece is clicked
	 * and then adds a piece to it.  It also contacts the game
	 * to find out if it's a valid move and if it is which pieces
	 * to flip.
	 * @param -Event is which button is clicked
	 */

	private class GUIHandler implements MouseListener{
		OthelloBoard changeBoard = new OthelloBoard(TOTALHEIGHT,TOTALWIDTH);
		boolean player = true; //true is player1, false player2
		@Override
        public void mouseReleased(MouseEvent event) {
        	
        	for(int y = 0; y < TOTALWIDTH; y++){ 
				for(int x = 0; x < TOTALHEIGHT; x++){ 

                    updateTitle(player);                       
					if(event.getSource() == gridButtons[x][y] && player == true){//player1 moves
                        if(player1Move(x,y)){
                            player = false;//player1 made valid move, player2's turn
                        }else{
                            player = true;//player1 invalid move
                        }
                        endGame();
                    }else if(player == false && event.getSource() == gridButtons[x][y]) {
                        if(player2Move(x,y)){
                            player = true;
                        }else{
                            player = false;
                    	}
                    	endGame();
                    }
                }
            }
        }


        /**
        * Inherited from MouseListener
        */
		@Override
        public void mouseClicked(MouseEvent e) {

        }
        /**
        * Inherited from MouseListener
        */
        @Override
        public void mousePressed(MouseEvent e) {
            
        }
        /**
        * Inherited from MouseListener
        */
        @Override
        public void mouseExited(MouseEvent e) {
        }
        /**
        * Inherited from MouseListener
        */
        @Override
        public void mouseEntered(MouseEvent e) {
        }

    }



    /**
    * @param boolean player - used to define who's turn it is
   	* if true then displays the score and that it is player one's turn
   	* if false then displays the score and that it is player two's turn
    */
    private void updateTitle(boolean player){
    	if(player){
    		window.setTitle(("Othello                 "+m_player1Name+"'s turn          "+ m_player1Name+" Score: " +  gameCheck.getPlayer1Score()
    			+"	    "+m_player2Name+" Score: " + gameCheck.getPlayer2Score()));
    	}else{
    		window.setTitle(("Othello                 "+m_player2Name+"'s turn          "+ m_player1Name+" Score: " +  gameCheck.getPlayer1Score()
    			+"	    "+m_player2Name+" Score: " + gameCheck.getPlayer2Score()));
    	}
    }



    	/**
    	* @param int x - x coord of point on board
    	* @param int y - y coord of point on board
    	* this method validates player one's moves and
    	* then calls player1SwapPieces to swap the valid peices
    	* @return boolean - returns false if move could not be validated or
    	* 					that they have no valid move to make.
    	*/
        private boolean player1Move(int x, int y){
            if(gameCheck.board.anyValid(m_player1Colour)){
                if(gameCheck.move(x,y,m_player1Colour)){
                    gridButtons[x][y].setIcon(whitePiece);
                    OthelloPiece piecesToSwap[][] = gameCheck.board.setPieces();

                    for(int i=0;i<TOTALWIDTH;i++){
                        for(int j=0;j<TOTALHEIGHT;j++){
                            if(piecesToSwap[i][j]==null){
                                //do nothing
                            }else{
                                player1SwapPieces(i,j);
                            }
                        }
                    }
                    
                    gameCheck.setPlayer1Score(1);
                    gameCheck.board.clearPieces();
                    //gridButtons[x][y].setIcon(whitePiece);
                    System.out.print("player 1 score: "+gameCheck.getPlayer1Score());
                    System.out.println(" player 2 score: "+gameCheck.getPlayer2Score());
                    return true;//made a valid move
                }
                return false;//invalid move
            }
            return true; //no possible move
        }


    	/**
    	* @param int i - x coord of point on board
    	* @param int j - y coord of point on board
		*
		* used to swap player one's pieces
    	*/
        private void player1SwapPieces(int i, int j){
            if(gridButtons[i][j].getIcon()==blackPiece||
                    gridButtons[i][j].getIcon()==whiteToBlackPiece){
                
                gridButtons[i][j].setIcon(blackToWhitePiece);
                gameCheck.setPlayer1Score(1);
                gameCheck.setPlayer2Score(-1);
                blackToWhitePiece.getImage().flush();
                gridButtons[i][j].setIcon(whitePiece);
            }
        }

        /**
    	* @param int x - x coord of point on board
    	* @param int y - y coord of point on board
    	* this method validates player two's moves and
    	* then calls player2SwapPieces to swap the valid peices
    	* @return boolean - returns false if move could not be validated or
    	* 					that they have no valid move to make.
    	*/
        private boolean player2Move(int x, int y){
            if(gameCheck.board.anyValid(m_player2Colour)){
                if(gameCheck.move(x,y,m_player2Colour)){
                   gridButtons[x][y].setIcon(blackPiece);
                   OthelloPiece piecesToSwap[][] = gameCheck.board.setPieces();

                   for(int i=0;i<TOTALWIDTH;i++){
                       for(int j=0;j<TOTALHEIGHT;j++){
                           if(piecesToSwap[i][j]==null){
                               //do nothing
                           }else{
                           		
                                player2SwapPieces(i,j);
                               }                                                            
                           }

                       }
                            gameCheck.setPlayer2Score(1);
                            gameCheck.board.clearPieces();
                            gridButtons[x][y].setIcon(blackPiece);
                            return true;//valid move made
                }
                return false;//invalid move
            }
            return true; //no possible valid move
        }

        /**
    	* @param int i - x coord of point on board
    	* @param int j - y coord of point on board
		*
		* used to swap player two's pieces
    	*/
        private void player2SwapPieces(int i, int j){
            if(gridButtons[i][j].getIcon()==whitePiece||gridButtons[i][j].getIcon()==blackToWhitePiece){
                gridButtons[i][j].setIcon(whiteToBlackPiece);
                gameCheck.setPlayer2Score(1);
                gameCheck.setPlayer1Score(-1);

                whiteToBlackPiece.getImage().flush();
                gridButtons[i][j].setIcon(blackPiece);
            }
        }

        /**
        * endGame() used to figure out if the game has finished
        * checks if either player has any valid moves
        * if no valid moves for either player then the game will end
        * next it checks if either player has no peices left
        * if a player has no peices then the game will end
        * finally checks if the board is full
        * if the board is full then the game will end
        */
        private void endGame(){
            if(!gameCheck.board.anyValid(m_player1Colour)&&
                    !gameCheck.board.anyValid(m_player2Colour)){
                
                if(gameCheck.getPlayer1Score()>gameCheck.getPlayer2Score()){
					displayWinnerPieces(m_player1Colour);
                    displayWinner(m_player1Name);
                    System.out.println("m_player1Name");
                    displayPlayAgain("play again?");
                    
                }else{
                	displayWinnerPieces(m_player2Colour);
                    displayWinner(m_player2Name);
                    System.out.println("m_player2Name");
                    displayPlayAgain("play again?");
                    
                }
            }
            if(gameCheck.getPlayer1Score()==0||gameCheck.getPlayer2Score()==0){
                if(gameCheck.getPlayer1Score()>gameCheck.getPlayer2Score()){
					displayWinnerPieces(m_player1Colour);
                    displayWinner(m_player1Name);
                    System.out.println("m_player1Name");
                    displayPlayAgain("play again?");
                    
                }else{
                	displayWinnerPieces(m_player2Colour);
                    displayWinner(m_player2Name);
                    System.out.println("m_player2Name");
                    displayPlayAgain("play again?");
                }
            }
            if(gameCheck.board.isFull()){
                if(gameCheck.getPlayer1Score()>gameCheck.getPlayer2Score()){
					displayWinnerPieces(m_player1Colour);
                    displayWinner(m_player1Name);
                    System.out.println("m_player1Name");
                    displayPlayAgain("play again?");
                    
                }else{
                	displayWinnerPieces(m_player2Colour);
                    displayWinner(m_player2Name);
                    System.out.println("m_player2Name");
                    displayPlayAgain("play again?");
                    
                }
                
            }
        }

        /**
        *@param OthelloPiece colour - takes in the winning player's colour
        * replaces the player's pieces with the winning pieces.
        */
        private void displayWinnerPieces(OthelloPiece colour){
            if(colour.getPieceColour()==Piece.OthelloPieceColour.WHITE){
                for(int i=0;i<TOTALHEIGHT;i++){
                    for(int j=0;j<TOTALWIDTH;j++){
                        if(gridButtons[i][j].getIcon()==whitePiece||gridButtons[i][j].getIcon()==blackToWhitePiece){
                            gridButtons[i][j].setIcon(whiteWinningPiece);
                        }
                    }
                }
            }else{
                for(int i=0;i<TOTALHEIGHT;i++){
                    for(int j=0;j<TOTALWIDTH;j++){
                        if(gridButtons[i][j].getIcon()==blackPiece||gridButtons[i][j].getIcon()==whiteToBlackPiece){
                            gridButtons[i][j].setIcon(blackWinningPiece);
                        }
                    }
                }
            }
            
        }

        /**
        *@param String[] players - takes in a string of players
        * first two values become player 1 and player 2
        */
        public void setPlayers(String[] players){
            m_player1Name = players[0];
            m_player2Name = players[1];
        }
        
        

	public static void main(String[] args){
		final int WIDTH = 600, HEIGHT = 600;
		String[] s = new String[2];
		s[0]="Tom";
		s[1]="Harry";
		OthelloGameGUI displayExample = new OthelloGameGUI("Othello",WIDTH,HEIGHT);
		displayExample.setPlayers(s);

		displayExample.creatingGui();
	}


	//@Override
	boolean updateDisplay(Piece[][] pieces, Date time, int[] score,
			int[] pieceCount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
