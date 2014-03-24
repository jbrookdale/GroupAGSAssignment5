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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class OthelloGameGUI extends GameGUI {
	private static final long serialVersionUID = 1L;

	static JFrame window;
    
    private static Piece.OthelloPieceColour p1colour;
    private static Piece.OthelloPieceColour p2colour;

    private static String m_player1Name;
	private static String m_player2Name;
	
	private static OthelloPiece m_player1Colour;
	private static OthelloPiece m_player2Colour;
	
	private static JButton defaultButton = null;
	private final static int TOTALWIDTH = 8;
	private final static int TOTALHEIGHT = 8;
	private final int BOARDWIDTH = 75;
	private final int BOARDHEIGHT = 75;
	
	OthelloBoard board = new OthelloBoard(TOTALHEIGHT,TOTALWIDTH);
	static OthelloGame gameCheck;
	
    static JLabel[][] gridButtons;
    private static ImageIcon backgroundTile;
	private static ImageIcon blackPiece;
	private static ImageIcon whitePiece;
    private static ImageIcon blackWinningPiece;
	private static ImageIcon whiteWinningPiece;
	private static ImageIcon whiteToBlackPiece;
	private static ImageIcon blackToWhitePiece;

    private JPanel panel;
    
	/** 
	 * This is the constructor for the OthelloGameGui class.
	 *
	 * @param title -the variable storing the title of the window.
	 * @param width -the variable storing width of the .
	 * @param height -the variable storing height of the .
	 */
	public OthelloGameGUI(String title, int width, int height, String p1Colour) {
		super(title,width,height);
		
		/* What is this? */
        URL location = OthelloGameGUI.class.getProtectionDomain()
                       .getCodeSource().getLocation();
		System.out.println(location.getFile());
		
        backgroundTile = new ImageIcon(getClass().getResource("OthelloBackground.png"));
		blackPiece = new ImageIcon(getClass().getResource("PieceBlack.png"));
		whitePiece = new ImageIcon(getClass().getResource("PieceWhite.png"));
        whiteWinningPiece = new ImageIcon(getClass().getResource("PieceWhiteFinal.png"));
        blackWinningPiece = new ImageIcon(getClass().getResource("PieceBlackFinal.png"));
		whiteToBlackPiece = new ImageIcon(getClass().getResource("PieceWhiteToBlack75.gif"));
		blackToWhitePiece = new ImageIcon(getClass().getResource("PieceBlackToWhite75.gif"));
		setTime(0);
		setTimerLabel();
		startTimer();
		
		if(p1Colour.equals("Black")){
			m_player1Colour = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
			m_player2Colour = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
			p1colour = Piece.OthelloPieceColour.BLACK;
			p2colour = Piece.OthelloPieceColour.WHITE;
		}else{
			m_player1Colour = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
			m_player2Colour = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
			p1colour = Piece.OthelloPieceColour.WHITE;
			p2colour = Piece.OthelloPieceColour.BLACK;
		}
		
		gameCheck = new OthelloGame(new HumanPlayer(m_player1Name, p1colour),
                					new HumanPlayer(m_player2Name, p2colour));
		
		panel = new JPanel();	
	}

	public static void setPieces(int i, int j, String colour){
		if(colour.equals("Black")){
			gridButtons[i][j].setIcon(blackPiece);
		}else if(colour.equals("White")){
			gridButtons[i][j].setIcon(whitePiece);
		}else{
			gridButtons[i][j].setIcon(backgroundTile);
		}
	}
	
    /**
    *@param String[] players - takes in a string of players
    * first two values become player 1 and player 2
    */
    public void setPlayers(String[] players){
        final int PLAYER_ONE = 0;
        final int PLAYER_TWO = 1;
        m_player1Name = players[PLAYER_ONE];
        m_player2Name = players[PLAYER_TWO];
    }
    
	public static String getPlayerName(int x){
		if(x == 0){
			return m_player1Name;
		}else{
			return m_player2Name;
		}
	}
	
	/**
	 * This gets the height of the board
	 * @param height -height of the board
	 * @return the height of the board
	 */
	public int getHeight(int height){ // Could this be more useless??
		return height;
	}

	/**
	 * This gets the Width of the board
	 * @param width -width of the board
	 * @return the width of the board
	 */
	public int getWidth(int width){ //Same here, why!?
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
		}
		return true;
	}

	 /**
	  * This method creates the GUI and board, adds current score, checks to see what button
	  * is pushed and sends that to the action listener.  This essentially sets the original
	  * board and then sends it to the action listener to do the rest.
	  */
	void creatingGui(){
		window = new JFrame("Othello                 "+m_player1Name+
                            " turn      "+m_player1Name+
                            " Score: " +  gameCheck.getPlayer1Score()+
                            "       "  +m_player2Name+
                            " Score: " + gameCheck.getPlayer2Score());
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.setLayout(new GridBagLayout());
        window.getContentPane().setBackground(Color.GREEN);
        GridBagConstraints c = new GridBagConstraints();
		window.setIconImage(new ImageIcon(this.getClass()
                .getResource("Othello.jpeg")).getImage());
		
		
		setPlayerLabel(m_player1Name, "Black", m_player2Name, "White");
		
		panel.add(getTimerLabel());
		panel.add(getPlayerLabel());
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 8;
		c.anchor = GridBagConstraints.NORTH;
		panel.setBackground(Color.GREEN);
		window.add(panel, c);
		//window.add(m_player1Score);
		//m_player1Score.setText(score.getPieceCount());
		GUIHandler handler = new GUIHandler();
		//player1 = new JLabel("Player1 Score: ");
		gridButtons = new JLabel[TOTALWIDTH][TOTALHEIGHT];
		for(int y = 0; y < TOTALWIDTH; y++){ 
			for(int x = 0; x < TOTALHEIGHT; x++){
				gridButtons[x][y]=new JLabel("");
				gridButtons[x][y].setIcon(backgroundTile);
				gridButtons[x][y].addMouseListener(handler);
				gridButtons[x][y].setPreferredSize(new Dimension(BOARDWIDTH,BOARDHEIGHT));
               c.gridx = x;
               c.gridy = y+1;
               c.gridwidth = 1;
			   	window.add(gridButtons[x][y],c); //adds button to grid
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
        int reply = JOptionPane.showConfirmDialog(null, message , message,
                                                  JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	window.dispose();
        	panel.removeAll();
            // Old constructor call
        	//gameCheck = new OthelloGame(m_player1Name,m_player1Type,m_player1Colour.getPieceColour(),
			//	m_player2Name,m_player2Type,m_player2Colour.getPieceColour());
            gameCheck = new OthelloGame(new HumanPlayer(m_player1Name, p1colour),
                                        new HumanPlayer(m_player2Name, p2colour));
        	creatingGui();
            panel.updateUI();
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
		 	JMenu menu = new JMenu("Menu");
	        add(menu);
	        JMenuItem newGameButton = new JMenuItem("New Game");
	        JMenuItem saveGameButton = new JMenuItem("Save Game");
	        JMenuItem loadGameButton = new JMenuItem("Load Game");
	        menu.add(newGameButton);
	        menu.add(saveGameButton);
	        menu.add(loadGameButton);
	        menu.add(new JMenuItem("Pause game"));
	        JMenuBar menuBar = new JMenuBar();
	        menuBar.add(menu);
	        setJMenuBar(menuBar);

	        newGameButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	                    System.out.println("new game clicked");
	                    displayPlayAgain("Start new game?");
	            }
	        });

	        saveGameButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	                    System.out.println("save game clicked");
	                    new OthelloGameSaver("C:\\Users\\Ieuan\\Desktop\\OthelloTest.xml");
	            }
	        });
	        
	        loadGameButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	                    System.out.println("load game clicked");
	                    new OthelloGameLoader("C:\\Users\\Ieuan\\Desktop\\OthelloTest.xml");
	            }
	        });
		return menuBar;
	}
    public static OthelloGame getGame(){
    	return gameCheck;
    }
	
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
		boolean player = true; //true is player1, false player2
		
        public void mouseReleased(MouseEvent event) {
        	for(int y = 0; y < TOTALWIDTH; y++){ 
				for(int x = 0; x < TOTALHEIGHT; x++){ 
					updateTitle(player);
                    //player1 moves
					if(event.getSource() == gridButtons[x][y] && player == true){
                        if(player1Move(x,y)){
                            //player1 made valid move, player2's turn
                            player = false;
                        }else{
                            //player1 invalid move
                            player = true;
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

		/* Inherited from MouseListener */
        public void mouseClicked(MouseEvent e) {}
		/* Inherited from MouseListener */ 
		public void mousePressed(MouseEvent e) {}
        /* Inherited from MouseListener */
        public void mouseExited(MouseEvent e) {}
        /* Inherited from MouseListener */
        public void mouseEntered(MouseEvent e) {}
    }



    /**
    * @param boolean player - used to define who's turn it is
   	* if true then displays the score and that it is player one's turn
   	* if false then displays the score and that it is player two's turn
    */
    private void updateTitle(boolean player){
    	if(player){
    		window.setTitle(("Othello                 "+m_player1Name+
                             "'s turn          "+ m_player1Name+
                             " Score: " +  gameCheck.getPlayer1Score()+
                             "	    "+m_player2Name+
                             " Score: " + gameCheck.getPlayer2Score()));
    	}else{
    		window.setTitle(("Othello                 "+m_player2Name+
                             "'s turn          "+ m_player1Name+
                             " Score: " +  gameCheck.getPlayer1Score()+
                             "	    "+m_player2Name+
                             " Score: " + gameCheck.getPlayer2Score()));
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
        static boolean player1Move(int x, int y){
            if(getGame().board.anyValid(m_player1Colour)){
                if(getGame().move(x,y,m_player1Colour)){
                	if(p1colour == Piece.OthelloPieceColour.WHITE){
                		gridButtons[x][y].setIcon(whitePiece);
                	}else{
                		gridButtons[x][y].setIcon(blackPiece);
                	}
                    OthelloPiece piecesToSwap[][]=gameCheck.board.setPieces();

                    player1DoSwapPieces(piecesToSwap);
                    
                    gameCheck.setPlayer1Score(1);
                    gameCheck.board.clearPieces();

                    System.out.print("player 1 score: "+
                                     gameCheck.getPlayer1Score());
                    System.out.println(" player 2 score: "+
                                       gameCheck.getPlayer2Score());
                    return true;//made a valid move
                }
                
                return false;//invalid move
            }
            return true; //no possible move
        }
        
        /**
        * @param OthelloPiece[][] piecesToSwap - array containing the 
        * pieces that need swapping
        * This method will flip all pieces that need to be flipped
        */
        private static void player1DoSwapPieces(OthelloPiece piecesToSwap[][]) {
            for(int i=0;i<TOTALWIDTH;i++){
                for(int j=0;j<TOTALHEIGHT;j++){
                    if(piecesToSwap[i][j]==null){
                        //do nothing
                    }else{
                        player1SwapPieces(i,j);
                    }
                }
            }
        }


    	/**
    	* @param int i - x coord of point on board
    	* @param int j - y coord of point on board
		*
		* used to swap player one's pieces
    	*/
        private static void player1SwapPieces(int i, int j){
        	
        	ImageIcon p1Icon;
        	ImageIcon p1FlipIcon;
        	ImageIcon p1FlipIcon2;
        	
        	if(p1colour != Piece.OthelloPieceColour.WHITE){
        		p1Icon= whitePiece;
        		p1FlipIcon = blackToWhitePiece;
        		p1FlipIcon2 = whiteToBlackPiece;
        	}else{
        		p1Icon= blackPiece;
        		p1FlipIcon2 = blackToWhitePiece;
        		p1FlipIcon = whiteToBlackPiece;
        	}
        	
        	if(gridButtons[i][j].getIcon()==p1Icon||
                    gridButtons[i][j].getIcon()==p1FlipIcon){
                
                gridButtons[i][j].setIcon(p1FlipIcon2);
                gameCheck.setPlayer1Score(1);
                gameCheck.setPlayer2Score(-1);
                blackToWhitePiece.getImage().flush();
                //gridButtons[i][j].setIcon(whitePiece);
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
        static boolean player2Move(int x, int y){
            if(gameCheck.board.anyValid(m_player2Colour)){
                if(gameCheck.move(x,y,m_player2Colour)){
                	if(p1colour == Piece.OthelloPieceColour.WHITE){
                		gridButtons[x][y].setIcon(blackPiece);
                	}else{
                		gridButtons[x][y].setIcon(whitePiece);
                	}
                	
                   OthelloPiece piecesToSwap[][] = gameCheck.board.setPieces();

                   player2DoSwapPieces(piecesToSwap);
                   
                   gameCheck.setPlayer2Score(1);
                   gameCheck.board.clearPieces();
                   
                   if(p1colour == Piece.OthelloPieceColour.WHITE){
               			gridButtons[x][y].setIcon(blackPiece);
               		}else{
               			gridButtons[x][y].setIcon(whitePiece);
               		}
                   
                   return true;//valid move made
                }
                return false;//invalid move
            }
            return true; //no possible valid move
        }
        
        /**
        * @param OthelloPiece[][] piecesToSwap - array containing the 
        * pieces that need swapping
        * This method will flip all pieces that need to be flipped
        */
        private static void player2DoSwapPieces(OthelloPiece piecesToSwap[][]) {
            for(int i=0;i<TOTALWIDTH;i++){
                for(int j=0;j<TOTALHEIGHT;j++){
                    if(piecesToSwap[i][j]==null){
                        //do nothing
                    }else{
                        player2SwapPieces(i,j);
                    }                                                            
                }
            }
        }

        /**
    	* @param int i - x coord of point on board
    	* @param int j - y coord of point on board
		*
		* used to swap player two's pieces
    	*/
        private static void player2SwapPieces(int i, int j){
            
        	ImageIcon p2Icon;
        	ImageIcon p2FlipIcon;
        	ImageIcon p2FlipIcon2;
        	
        	if(p1colour == Piece.OthelloPieceColour.WHITE){
        		p2Icon= whitePiece;
        		p2FlipIcon = blackToWhitePiece;
        		p2FlipIcon2 = whiteToBlackPiece;
        	}else{
        		p2Icon= blackPiece;
        		p2FlipIcon2 = blackToWhitePiece;
        		p2FlipIcon = whiteToBlackPiece;
        	}
        	
        	if(gridButtons[i][j].getIcon()==p2Icon||
               gridButtons[i][j].getIcon()==p2FlipIcon){
                gridButtons[i][j].setIcon(p2FlipIcon2);
                gameCheck.setPlayer2Score(1);
                gameCheck.setPlayer1Score(-1);

                whiteToBlackPiece.getImage().flush();
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
            final int NOBODY = 0;
            final int PLAYER_ONE = 1;
            final int PLAYER_TWO = 2;
            final int DRAW = 3;
            int winner = NOBODY;
            
            final boolean PLAYER_1 = true;
            final boolean PLAYER_2 = false;
            
            updateTitle(PLAYER_1);
            updateTitle(PLAYER_2);
            
            if(!gameCheck.board.anyValid(m_player1Colour)&&
                    !gameCheck.board.anyValid(m_player2Colour)){
                
                if(gameCheck.getPlayer1Score()>gameCheck.getPlayer2Score()){
				    winner = PLAYER_ONE;
                }else if (gameCheck.getPlayer1Score() < 
                          gameCheck.getPlayer2Score()) {
                    winner = PLAYER_TWO;
                } else {
                    winner = DRAW;
                }
            }
            if(gameCheck.getPlayer1Score()==0||
               gameCheck.getPlayer2Score()==0){
                if(gameCheck.getPlayer1Score()>
                   gameCheck.getPlayer2Score()){
				    winner = PLAYER_ONE;
                }else{
                    winner = PLAYER_TWO;
                }
            }
            if(gameCheck.board.isFull()){
                if(gameCheck.getPlayer1Score()>
                   gameCheck.getPlayer2Score()){
					winner = PLAYER_ONE;
                }else{
                	winner = PLAYER_TWO;
                }
                
            }
            if (winner == PLAYER_ONE) {
                displayWinnerPieces(m_player1Colour);
                displayWinner(m_player1Name);
                System.out.println("m_player1Name");
                displayPlayAgain("play again?");
            } else if (winner == PLAYER_TWO) {
                displayWinnerPieces(m_player2Colour);
                displayWinner(m_player2Name);
                System.out.println("m_player2Name");
                displayPlayAgain("play again?");
            } else if (winner == DRAW) {
                
                displayWinner(m_player1Name + " and " + m_player2Name);
                System.out.println("Draw");
                displayPlayAgain("play again?");
            }
        }

        /**
        *@param OthelloPiece colour - takes in the winning player's colour
        * replaces the player's pieces with the winning pieces.
        */
        private void displayWinnerPieces(final OthelloPiece colour){
            final int ANIMATION_TIME = 1700;
            new Thread(new Runnable() {
                public void run() {
                    try {
                       Thread.sleep(ANIMATION_TIME); 
                    } catch (Exception e) {
                
                    }
                    if(colour.getPieceColour()==Piece.OthelloPieceColour.WHITE){
                        for(int i=0;i<TOTALHEIGHT;i++){
                            for(int j=0;j<TOTALWIDTH;j++){
                                if(gridButtons[i][j].getIcon()==whitePiece||
                                   gridButtons[i][j].getIcon()==blackToWhitePiece){
                                    gridButtons[i][j].setIcon(whiteWinningPiece);
                                }
                            }
                        }
                    }else{
                        for(int i=0;i<TOTALHEIGHT;i++){
                            for(int j=0;j<TOTALWIDTH;j++){
                                if(gridButtons[i][j].getIcon()==blackPiece||
                                   gridButtons[i][j].getIcon()==
                                       whiteToBlackPiece){
                                    gridButtons[i][j].setIcon(blackWinningPiece);
                                }
                            }
                        }
                    }
                }
            }).start();
        }

	public static void main(String[] args){
		final int WIDTH = 600, HEIGHT = 600;
        final int TOTAL_PLAYERS = 2;
        final int PLAYER_ONE = 0;
        final int PLAYER_TWO = 1;
		String[] s = new String[TOTAL_PLAYERS];
		s[PLAYER_ONE]="Jon";
		s[PLAYER_TWO]="Tom";
		OthelloGameGUI displayExample = new OthelloGameGUI("Othello",WIDTH,HEIGHT, "Black");
		displayExample.setPlayers(s);

		displayExample.creatingGui();
	}	
}
