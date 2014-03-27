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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Point;

import javax.swing.JFileChooser;

import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;
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
	private static JFrame m_window;
    
    private static Piece.OthelloPieceColour m_p1colour;
    private static Piece.OthelloPieceColour m_p2colour;

    private static String m_player1Name;
	private static String m_player2Name;
	
	private static OthelloPiece m_player1Colour;
	private static OthelloPiece m_player2Colour;
	
	private static JButton m_defaultButton = null;
	private final static int TOTALWIDTH = 8;
	private final static int TOTALHEIGHT = 8;
	private final int BOARDWIDTH = 75;
	private final int BOARDHEIGHT = 75;
    private final int TOTAL_PLAYERS = 2;
	
	OthelloBoard m_board = new OthelloBoard(TOTALHEIGHT,TOTALWIDTH);
	static OthelloGame m_gameCheck;
	
    static JLabel[][] m_gridButtons;
    private static ImageIcon m_backgroundTile;
	private static ImageIcon m_blackPiece;
	private static ImageIcon m_whitePiece;
    private static ImageIcon m_blackWinningPiece;
	private static ImageIcon m_whiteWinningPiece;
	private static ImageIcon m_whiteToBlackPiece;
	private static ImageIcon m_blackToWhitePiece;

    private Thread m_animationThread = new Thread();

    private static Player m_playerOne;
    private static Player m_playerTwo;

    private JPanel m_panel;
    
	/** 
	 * This is the constructor for the OthelloGameGui class.
	 *
	 * @param title -the variable storing the title of the window.
	 * @param width -the variable storing width of the .
	 * @param height -the variable storing height of the .
	 */
	public OthelloGameGUI(String title, int width, int height, Player playerOne, Player playerTwo) {
		super(title,width,height);
		
		/* What is this? */
        URL location = OthelloGameGUI.class.getProtectionDomain()
                       .getCodeSource().getLocation();
		System.out.println(location.getFile());
		
        m_backgroundTile = new ImageIcon(getClass().getResource("OthelloBackground.png"));
		m_blackPiece = new ImageIcon(getClass().getResource("PieceBlack.png"));
		m_whitePiece = new ImageIcon(getClass().getResource("PieceWhite.png"));
        m_whiteWinningPiece = new ImageIcon(getClass().getResource("PieceWhiteFinal.png"));
        m_blackWinningPiece = new ImageIcon(getClass().getResource("PieceBlackFinal.png"));
		m_whiteToBlackPiece = new ImageIcon(getClass().getResource("PieceWhiteToBlack75.gif"));
		m_blackToWhitePiece = new ImageIcon(getClass().getResource("PieceBlackToWhite75.gif"));
		setTime(0);
		setTimerLabel();
		startTimer();

        m_playerOne = playerOne;
        m_playerTwo = playerTwo;
		
		if(playerOne.getColour().equals("Black")){
			m_player1Colour = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
			m_player2Colour = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
			m_p1colour = Piece.OthelloPieceColour.BLACK;
			m_p2colour = Piece.OthelloPieceColour.WHITE;
		}else{
			m_player1Colour = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
			m_player2Colour = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
			m_p1colour = Piece.OthelloPieceColour.WHITE;
			m_p2colour = Piece.OthelloPieceColour.BLACK;
		}
        
		OthelloGame game = new OthelloGame(playerOne, playerTwo);
		
		setGame(game);
		
		m_panel = new JPanel();	
	}

	/**
	 * This access method sets the member variable m_gameCheck.
	 * 
	 * @param game	The OthelloGame object to be set.
	 */
	public static void setGame(OthelloGame game){
		m_gameCheck = game;
	}
	
	/**
	 * This access method returns the member variable m_gameCheck.
	 *
	 * @return m_gameCheck	The OthelloGame object being used.
	 */
    public static OthelloGame getGame(){
    	return m_gameCheck;
    }
	
	
	/**
	 * This access methods sets player one member variable.
	 * 
	 * @param playerOne	The Player object to be set.
	 */
	public static void setPlayerOne(Player playerOne){
		 m_playerOne = playerOne;
	}
	
	/**
	 *This access methods sets player two member variable.
	 *
	 * @param playerTwo	The Player object to be set.
	 */
	public static void setPlayerTwo(Player playerTwo){
        m_playerTwo = playerTwo;
	}
	
	/**
	 * This access methods will set a piece on the board.
	 * 
	 * @param i			The column position of the piece to be set.
	 * @param j			The row position of the piece to be set.
	 * @param colour	The colour of the piece to be set.
	 */
	public static void setPieces(int i, int j, String colour){
		if(colour.equals("Black")){
			m_gridButtons[i][j].setIcon(m_blackPiece);
		}else if(colour.equals("White")){
			m_gridButtons[i][j].setIcon(m_whitePiece);
		}else{
			m_gridButtons[i][j].setIcon(m_backgroundTile);
		}
	}
	
    /**
     * This method will set initialise players one and two. 
     * 
     * @param String[] players - takes in a string of players
     * first two values become player 1 and player 2
     */
    public void setPlayers(String[] players){
        final int PLAYER_ONE = 0;
        final int PLAYER_TWO = 1;
        m_player1Name = players[PLAYER_ONE];
        m_player2Name = players[PLAYER_TWO];
    }
    
    /**
     * This access method will return player one or two.
     * 
     * @param x			If 0 return player one else player two.
     * @return	Player	The desired player object.
     */
	public static String getPlayerName(int x){
		if(x == 0){
			return m_playerOne.getName();
		}else{
			return m_playerTwo.getName();
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
		m_window = new JFrame("Othello                 " + m_playerOne.getName()+
                            " turn      "+m_playerOne.getName()+
                            " Score: " +  getGame().getPlayer1Score()+
                            "       "  +m_playerTwo.getName()+
                            " Score: " + getGame().getPlayer2Score());
		m_window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		m_window.setLayout(new GridBagLayout());
        m_window.getContentPane().setBackground(Color.GREEN);
        GridBagConstraints c = new GridBagConstraints();
		m_window.setIconImage(new ImageIcon(this.getClass()
                .getResource("Othello.jpeg")).getImage());
		
		
		if(m_playerOne.getColour().equals("Black")){
			setPlayerLabel(m_playerOne.getName(), "Black", m_playerTwo.getName(), "White");
		}else{
			setPlayerLabel(m_playerOne.getName(), "White", m_playerTwo.getName(), "Black");	
		}
		
		m_panel.add(getTimerLabel());
		m_panel.add(getPlayerLabel());
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 8;
		c.anchor = GridBagConstraints.NORTH;
		m_panel.setBackground(Color.GREEN);
		m_window.add(m_panel, c);
		
		GUIHandler handler = new GUIHandler();
		
		m_gridButtons = new JLabel[TOTALWIDTH][TOTALHEIGHT];
		for(int y = 0; y < TOTALWIDTH; y++){ 
			for(int x = 0; x < TOTALHEIGHT; x++){
				m_gridButtons[x][y]=new JLabel("");
				m_gridButtons[x][y].setIcon(m_backgroundTile);
				m_gridButtons[x][y].addMouseListener(handler);
				m_gridButtons[x][y].setPreferredSize(new Dimension(BOARDWIDTH,BOARDHEIGHT));
               c.gridx = x;
               c.gridy = y+1;
               c.gridwidth = 1;
			   	m_window.add(m_gridButtons[x][y],c); //adds button to grid
			}
		}
		
		m_gridButtons[3][3].setIcon(m_whitePiece);
		m_gridButtons[4][4].setIcon(m_whitePiece);
		m_gridButtons[3][4].setIcon(m_blackPiece);
		m_gridButtons[4][3].setIcon(m_blackPiece);
                
		m_window.setJMenuBar(creatingMenu());
        m_window.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to exit the program?", 
                "Exit Program Message Box",
                JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                   	m_window.dispose();
                } 
            }
        });

		m_window.getRootPane().setDefaultButton(m_defaultButton);
		m_window.pack();
		m_window.setVisible(true);

        if (!m_playerOne.getPlayerType().equals("Human")) {
            int x, y;
            if (m_playerOne.getPlayerType().equals("ComputerEasy")) {
                Point p = ((OthelloEasyComputerPlayer)m_playerOne).makeAIMove(m_board);
                x = (int)p.getX();
                y = (int)p.getY();
            } else {
                Point p = ((OthelloHardComputerPlayer)m_playerOne).makeAIMove(m_board);
                x = (int)p.getX();
                y = (int)p.getY();
            }
            performMove(x,y);
        }
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
        	m_window.dispose();
        	m_panel.removeAll();
            OthelloGame game = new OthelloGame(new HumanPlayer(m_playerOne.getName(), m_p1colour),
                                        		new HumanPlayer(m_playerTwo.getName(), m_p2colour));
            
            setGame(game);
            
        	creatingGui();
            m_panel.updateUI();
            // Reset for next game if they play again.
            getGame().m_board.resetBoard();
            
            if(getGame().getPlayerTurn() % 2 == 1){
            	getGame().incrementTurn();
            }
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
	                 String saveGame = JOptionPane.showInputDialog("Enter save name:");
	                 Date dNow = new Date( );
	                 SimpleDateFormat timeStamp = 
	                 new SimpleDateFormat ("H.mm dd.MM.yy");

	                 System.out.println("Current Date: " + timeStamp.format(dNow));
	                    
	                 new OthelloGameSaver("saves\\othellosaves\\" + saveGame + "  [" + timeStamp.format(dNow) + "]" + ".xml");
	                    
	            }
	        });
	        
	        loadGameButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	                    System.out.println("load game clicked");
	                    String saveFile = "";
	    				String fileExtension = "";
	                    JFileChooser chooser = new JFileChooser();
	                    chooser.setCurrentDirectory(new File(".\\saves\\othellosaves\\"));
	                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                        "Game saves only", "xml");
	                    chooser.setFileFilter(filter);
	                    int returnVal = chooser.showOpenDialog(OthelloGameGUI.this);
	                    if(returnVal == JFileChooser.APPROVE_OPTION) {
	                    	saveFile = chooser.getSelectedFile().getName();
	                        fileExtension = saveFile.substring(saveFile.lastIndexOf(".") + 1, saveFile.length());
	                    	
	                    }
	                    if(fileExtension.equals("xml")) { //checks if file selected is an xml file
	    	                if(saveFile != "") { //checks if the user selected cancel instead
	    	                	new OthelloGameLoader("saves\\othellosaves\\" + saveFile);
	    	                }
	                    } else {
	                    	JOptionPane.showMessageDialog(null, "You have not chosen a game save");
	                    }
	            }
	        });
		return menuBar;
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
		
        public void mouseReleased(MouseEvent event) {
            while (!m_animationThread.isAlive()) {
        	boolean playerHuman = false;
                    if (getGame().getPlayerTurn() % TOTAL_PLAYERS == 0) {
                        if (m_playerOne.getPlayerType().equals("Human")) {
                            playerHuman = true;
                        }
                    } else {
                        if (m_playerTwo.getPlayerType().equals("Human")) {
                            playerHuman = true;
                        }
                    }
                    
                    if (playerHuman) {
                        for(int y = 0; y < TOTALWIDTH; y++){ 
                            for(int x = 0; x < TOTALHEIGHT; x++){ 
                                updateTitle(getGame().getPlayerTurn() % TOTAL_PLAYERS == 0);
                                //player1 moves
                                if(event.getSource() == m_gridButtons[x][y]){
                                    performMove(x,y);
                                }
                            }
                        }
                    }
                    
                    
                    if (!playerHuman) {
                        int i, j;
                        if (getGame().getPlayerTurn() % TOTAL_PLAYERS == 0) {
                            if (m_playerOne.getPlayerType().equals("ComputerEasy")) {
                                Point p = ((OthelloEasyComputerPlayer)m_playerOne).makeAIMove(m_board);
                                i = (int)p.getX();
                                j = (int)p.getY();
                            } else {
                                Point p = ((OthelloHardComputerPlayer)m_playerOne).makeAIMove(m_board);
                                i = (int)p.getX();
                                j = (int)p.getY();
                            }
                            performMove(i,j);
                        } else if (getGame().getPlayerTurn() % TOTAL_PLAYERS == 1) {
                            if (m_playerTwo.getPlayerType().equals("ComputerEasy")) {
                                Point p = ((OthelloEasyComputerPlayer)m_playerTwo).makeAIMove(m_board);
                                i = (int)p.getX();
                                j = (int)p.getY();
                            } else {
                                Point p = ((OthelloHardComputerPlayer)m_playerTwo).makeAIMove(m_board);
                                i = (int)p.getX();
                                j = (int)p.getY();
                            }
                            performMove(i,j);
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
	 * This method is used by the players to perform a move according to mouse input.
	 * 
	 * @param x	The column position where a move is to be performed.
	 * @param y The row position where a move is to be performed.
	 */
    public void performMove(int x, int y) {
        if (getGame().getPlayerTurn() % TOTAL_PLAYERS == 0) {
            if(player1Move(x,y)){
                //player1 made valid move, player2's turn
                getGame().incrementTurn();
            } else {
                if (getGame().getPlayer(getGame().getPlayerTurn() % TOTAL_PLAYERS).getPlayerType() == "Human") {
                            javax.swing.JOptionPane.showMessageDialog(null, 
                                                            "Invalid move");
                        }
            }
        } else {
            if(player2Move(x,y)){
                getGame().incrementTurn();
            } else {
                if (getGame().getPlayer(getGame().getPlayerTurn() % TOTAL_PLAYERS).getPlayerType() == "Human") {
                            javax.swing.JOptionPane.showMessageDialog(null, 
                                                            "Invalid move");
                        }
            }
        }
        endGame();
        
        m_animationThread = new Thread(new Runnable() {
            final int ANIMATION_TIME = 2000;
            public void run() {
                try {
                    Thread.sleep(ANIMATION_TIME);
                } catch (Exception e) {
                            
                }
                boolean playerHuman = false;
                        if (getGame().getPlayerTurn() % TOTAL_PLAYERS == 0) {
                            if (m_playerOne.getPlayerType().equals("Human")) {
                                playerHuman = true;
                            }
                        } else {
                            if (m_playerTwo.getPlayerType().equals("Human")) {
                                playerHuman = true;
                            }
                        }
                        
                        
                        if (!playerHuman) {
                            int i, j;
                            if (getGame().getPlayerTurn() % TOTAL_PLAYERS == 0) {
                                if (m_playerOne.getPlayerType().equals("ComputerEasy")) {
                                    Point p = ((OthelloEasyComputerPlayer)m_playerOne).makeAIMove(m_board);
                                    i = (int)p.getX();
                                    j = (int)p.getY();
                                } else {
                                    Point p = ((OthelloHardComputerPlayer)m_playerOne).makeAIMove(m_board);
                                    i = (int)p.getX();
                                    j = (int)p.getY();
                                }
                                performMove(i,j);
                            } else if (getGame().getPlayerTurn() % TOTAL_PLAYERS == 1) {
                                if (m_playerTwo.getPlayerType().equals("ComputerEasy")) {
                                    Point p = ((OthelloEasyComputerPlayer)m_playerTwo).makeAIMove(m_board);
                                    i = (int)p.getX();
                                    j = (int)p.getY();
                                } else {
                                    Point p = ((OthelloHardComputerPlayer)m_playerTwo).makeAIMove(m_board);
                                    i = (int)p.getX();
                                    j = (int)p.getY();
                                }
                                performMove(i,j);
                            }
                        }
            }
        });
        m_animationThread.start();   
    }
    
    


    /**
     * This method updates the title bar.
     * 
     * @param boolean player - used to define who's turn it is
   	 * if true then displays the score and that it is player one's turn
   	 * if false then displays the score and that it is player two's turn
     */
    private void updateTitle(boolean player){
    	if(player){
    		m_window.setTitle(("Othello                 "+m_playerOne.getName()+
                             "'s turn          "+ m_playerOne.getName()+
                             " Score: " +  getGame().getPlayer1Score()+
                             "	    "+m_playerTwo.getName()+
                             " Score: " + getGame().getPlayer2Score()));
    	}else{
    		m_window.setTitle(("Othello                 "+m_playerTwo.getName()+
                             "'s turn          "+ m_playerOne.getName()+
                             " Score: " +  getGame().getPlayer1Score()+
                             "	    "+m_playerTwo.getName()+
                             " Score: " + getGame().getPlayer2Score()));
    	}
    }



    	/**
    	 * This method validates player one's moves and
    	 * then calls player1SwapPieces to swap the valid pieces
    	 * 
    	 * @param int x - x coordinate of point on board
    	 * @param int y - y coordinate of point on board
    	 * @return boolean - returns false if move could not be validated or
    	 * 					that they have no valid move to make.
    	 */
        static boolean player1Move(int x, int y){
            if(getGame().m_board.anyValid(m_player1Colour)){
                if(getGame().move(x,y,m_player1Colour)){
                	if(m_p1colour == Piece.OthelloPieceColour.WHITE){
                		m_gridButtons[x][y].setIcon(m_whitePiece);
                	}else{
                		m_gridButtons[x][y].setIcon(m_blackPiece);
                	}
                    OthelloPiece piecesToSwap[][]=getGame().m_board.setPieces();

                    player1DoSwapPieces(piecesToSwap);
                    
                    getGame().setPlayer1Score(1);
                    getGame().m_board.clearPieces();

                    System.out.print("player 1 score: "+
                                     getGame().getPlayer1Score());
                    System.out.println(" player 2 score: "+
                                       getGame().getPlayer2Score());
                    return true;//made a valid move
                }
                
                return false;//invalid move
            }
            return true; //no possible move
        }
        
        /**
        * This method will flip all pieces that need to be flipped.
        * 
        * @param OthelloPiece[][] piecesToSwap - array containing the 
        * pieces that need swapping.
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
    	 * Used to swap player one's pieces
    	 * 
    	 * @param int i - x coord of point on board
    	 * @param int j - y coord of point on board
    	 */
        private static void player1SwapPieces(int i, int j){
        	
        	ImageIcon p1Icon;
        	ImageIcon p1FlipIcon;
        	ImageIcon p1FlipIcon2;
        	
        	if(m_p1colour != Piece.OthelloPieceColour.WHITE){
        		p1Icon= m_whitePiece;
        		p1FlipIcon = m_blackToWhitePiece;
        		p1FlipIcon2 = m_whiteToBlackPiece;
        	}else{
        		p1Icon= m_blackPiece;
        		p1FlipIcon2 = m_blackToWhitePiece;
        		p1FlipIcon = m_whiteToBlackPiece;
        	}
        	
        	if(m_gridButtons[i][j].getIcon()==p1Icon||
                    m_gridButtons[i][j].getIcon()==p1FlipIcon){
                
                m_gridButtons[i][j].setIcon(p1FlipIcon2);
                getGame().setPlayer1Score(1);
                getGame().setPlayer2Score(-1);
                m_blackToWhitePiece.getImage().flush();
                //m_gridButtons[i][j].setIcon(whitePiece);
            }
        }

        /**
    	 * This method validates player two's moves and
    	 * then calls player2SwapPieces to swap the valid pieces
         * 
    	 * @param int x - x coordinate of point on board
    	 * @param int y - y coordinate of point on board
    	 * @return boolean - returns false if move could not be validated or
    	 * 					that they have no valid move to make.
    	 */
        static boolean player2Move(int x, int y){
            if(getGame().m_board.anyValid(m_player2Colour)){
                if(getGame().move(x,y,m_player2Colour)){
                	if(m_p1colour == Piece.OthelloPieceColour.WHITE){
                		m_gridButtons[x][y].setIcon(m_blackPiece);
                	}else{
                		m_gridButtons[x][y].setIcon(m_whitePiece);
                	}
                	
                   OthelloPiece piecesToSwap[][] = getGame().m_board.setPieces();

                   player2DoSwapPieces(piecesToSwap);
                   
                   getGame().setPlayer2Score(1);
                   getGame().m_board.clearPieces();
                   
                   if(m_p1colour == Piece.OthelloPieceColour.WHITE){
               			m_gridButtons[x][y].setIcon(m_blackPiece);
               		}else{
               			m_gridButtons[x][y].setIcon(m_whitePiece);
               		}
                   
                   return true;//valid move made
                }
                return false;//invalid move
            }
            return true; //no possible valid move
        }
        
        /**
         * This method will flip all pieces that need to be flipped.
         * 
         * @param OthelloPiece[][] piecesToSwap - array containing the 
         * pieces that need swapping
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
         * This method is used to swap player two's pieces
         * 
    	 * @param int i - x coord of point on board
    	 * @param int j - y coord of point on board
    	 */
        private static void player2SwapPieces(int i, int j){
            
        	ImageIcon p2Icon;
        	ImageIcon p2FlipIcon;
        	ImageIcon p2FlipIcon2;
        	
        	if(m_p1colour == Piece.OthelloPieceColour.WHITE){
        		p2Icon= m_whitePiece;
        		p2FlipIcon = m_blackToWhitePiece;
        		p2FlipIcon2 = m_whiteToBlackPiece;
        	}else{
        		p2Icon= m_blackPiece;
        		p2FlipIcon2 = m_blackToWhitePiece;
        		p2FlipIcon = m_whiteToBlackPiece;
        	}
        	
        	if(m_gridButtons[i][j].getIcon()==p2Icon||
               m_gridButtons[i][j].getIcon()==p2FlipIcon){
                m_gridButtons[i][j].setIcon(p2FlipIcon2);
                getGame().setPlayer2Score(1);
                getGame().setPlayer1Score(-1);

                m_whiteToBlackPiece.getImage().flush();
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
            
            if(!getGame().m_board.anyValid(m_player1Colour)&&
                    !getGame().m_board.anyValid(m_player2Colour)){
                
                if(getGame().getPlayer1Score()>getGame().getPlayer2Score()){
				    winner = PLAYER_ONE;
                }else if (getGame().getPlayer1Score() < 
                          getGame().getPlayer2Score()) {
                    winner = PLAYER_TWO;
                } else {
                    winner = DRAW;
                }
            }
            if(getGame().getPlayer1Score()==0||
               getGame().getPlayer2Score()==0){
                if(getGame().getPlayer1Score()>
                   getGame().getPlayer2Score()){
				    winner = PLAYER_ONE;
                }else{
                    winner = PLAYER_TWO;
                }
            }
            if(getGame().m_board.isFull()){
                if(getGame().getPlayer1Score()>
                   getGame().getPlayer2Score()){
					winner = PLAYER_ONE;
                }else{
                	winner = PLAYER_TWO;
                }
                
            }
            if (winner == PLAYER_ONE) {
                displayWinnerPieces(m_player1Colour);
                displayWinner(m_playerOne.getName());
                System.out.println("m_playerOne.getName()");
                displayPlayAgain("play again?");
            } else if (winner == PLAYER_TWO) {
                displayWinnerPieces(m_player2Colour);
                displayWinner(m_playerTwo.getName());
                System.out.println("m_playerTwo.getName()");
                displayPlayAgain("play again?");
            } else if (winner == DRAW) {
                displayWinner(m_playerOne.getName() + " and " + m_playerTwo.getName());
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
                                if(m_gridButtons[i][j].getIcon()==m_whitePiece||
                                   m_gridButtons[i][j].getIcon()==m_blackToWhitePiece){
                                    m_gridButtons[i][j].setIcon(m_whiteWinningPiece);
                                }
                            }
                        }
                    }else{
                        for(int i=0;i<TOTALHEIGHT;i++){
                            for(int j=0;j<TOTALWIDTH;j++){
                                if(m_gridButtons[i][j].getIcon()==m_blackPiece||
                                   m_gridButtons[i][j].getIcon()==
                                       m_whiteToBlackPiece){
                                    m_gridButtons[i][j].setIcon(m_blackWinningPiece);
                                }
                            }
                        }
                    }
                }
            }).start();
        }

	/** This is the main method containing the unit tests for this class. */
	public static void main(String[] args){
		final int WIDTH = 600, HEIGHT = 600;
        final int TOTAL_PLAYERS = 2;
        final int PLAYER_ONE = 0;
        final int PLAYER_TWO = 1;
		String[] s = new String[TOTAL_PLAYERS];
		s[PLAYER_ONE]="Jon";
		s[PLAYER_TWO]="Tom";
        Player playerOne = new OthelloHardComputerPlayer(s[0], "Black");
        Player playerTwo = new OthelloEasyComputerPlayer(s[1], "White");
		OthelloGameGUI displayExample = new OthelloGameGUI("Othello",WIDTH,HEIGHT, playerOne, playerTwo);
		displayExample.setPlayers(s);

		displayExample.creatingGui();
	}	
}
