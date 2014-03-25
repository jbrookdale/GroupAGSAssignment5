/**
* @file ConnectFourGameGUI.java
* @author Kristoffer Page
* @date 31 Jan 2014
* @see JavaDoc for jFrames -  
*      http://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html
* 
* @brief This class extends GameGUI and is the graphical interface for the 
*        connect four game. It handles input from the user, as well as some 
*        logic of the game.
*
* This class extends GameGUI and is the graphical interface for the connect four
* game. It handles input from the user, as well as some game logic.
* It is responsible for for displaying the board, pieces and score.
* It uses ConnectFourPanel for drawing the board image and pieces.
*/

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class ConnectFourGameGUI extends GameGUI {
    /**< This is the height (amount of slots/places vertically) in the board */
    public static final int BOARD_HEIGHT = 7;

    /**< This is the width (amount of slots/places horizontally) in the board */
    public static final int BOARD_WIDTH = 10;

    /**< This is the panel that of draws the graphics to the screen */
    private static ConnectFourPanel panel;

    /**< Distance between the edge of the window to the board */
    final static int WINDOW_BORDER_WIDTH = ConnectFourPanel.BOARD_SIDE_SPACING;

    /**< Width of the Window. Width depends on the the board image's width */
    final static int WINDOW_WIDTH = ConnectFourPanel.BOARD_IMAGE_WIDTH 
        + WINDOW_BORDER_WIDTH * 2;

    /**< Height of the Window. Height depends on the the board image's height */
    private static final int WINDOW_HEIGHT = ConnectFourPanel.BOARD_IMAGE_HEIGHT
        + ConnectFourPanel.TOP_SPACING + 50;

    /**< Title of the JFrame */
    private static final String WINDOW_TITLE = "Connect Four";

    /**< Stores the mouse's X coordinate inside the window */
    public int mouseX;

    /**< Stores an instance of ConnectFourGame */
    private static ConnectFourGame game;
    
    private int TOTAL_PLAYERS = 2;
    
    private Player m_playerOne;
    private Player m_playerTwo;
    
    private Piece.ConnectFourPieceColour m_playerOnePieceColour;
    private Piece.ConnectFourPieceColour m_playerTwoPieceColour;
    
    private String m_playerOneType;
    private String m_playerTwoType;
    
    /**
    * Constructor for ConnectFourGameGUI
    * @param playerOneName - String representation of player one's name
    * @param playerTwoName - String representation of player two's name
    */
    public static ConnectFourPanel getPanel(){
    	return panel;
    }
    
    public static ConnectFourGame getGame(){
    	return game;
    }
    
    
    public void setPieceColours(String p1Colour){
    	if(p1Colour.equals("Red")){
    		m_playerOnePieceColour = Piece.ConnectFourPieceColour.RED;
    		m_playerTwoPieceColour = Piece.ConnectFourPieceColour.YELLOW;
    	}else{
    		m_playerOnePieceColour = Piece.ConnectFourPieceColour.YELLOW;
    		m_playerTwoPieceColour = Piece.ConnectFourPieceColour.RED;
    	}
    }
    
    private void setPlayerTypes(String[] playerTypes) {
        m_playerOneType = playerTypes[0];
        m_playerTwoType = playerTypes[1];
    }
    
    public Piece.ConnectFourPieceColour getPlayerOnePieceColour(){
		return m_playerOnePieceColour;
    }
    
    public Piece.ConnectFourPieceColour getPlayerTwoPieceColour(){
		return m_playerTwoPieceColour;
    }
    
    public static void setGame(ConnectFourGame newGame) {
		game = newGame;	
	}
    
    public ConnectFourGameGUI(String[] playerOneDetails, 
        String[] playerTwoDetails, boolean loaded, String loc) {
        //Call super class constructor
        super(WINDOW_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT);

        setPieceColours(playerOneDetails[2]);
        setPlayerTypes(new String[] {playerOneDetails[1],playerTwoDetails[1]});
        
        Player playerOne;
        Player playerTwo;
        
        if (m_playerOneType.equals("Computer: Easy")) {
            playerOne = new ConnectFourEasyComputerPlayer(
                                                       playerOneDetails[0], getPlayerOnePieceColour());
        } else if (m_playerOneType.equals("Computer: Hard")) {
            playerOne = new ConnectFourHardComputerPlayer(
                                           playerOneDetails[0], getPlayerOnePieceColour());
        } else {
            playerOne = new HumanPlayer(
                                           playerOneDetails[0], getPlayerOnePieceColour());
        }
        
        if (m_playerTwoType.equals("Computer: Easy")) {
            playerTwo = new ConnectFourEasyComputerPlayer(
                                           playerTwoDetails[0], getPlayerTwoPieceColour());
        } else if (m_playerTwoType.equals("Computer: Hard")) {
            playerTwo = new ConnectFourHardComputerPlayer(
                                                       playerTwoDetails[0], getPlayerTwoPieceColour());
        } else {
            playerTwo = new HumanPlayer(
                                           playerTwoDetails[0], getPlayerTwoPieceColour());
        }
        
        ConnectFourGame newGame = new ConnectFourGame(playerOne, playerTwo);
        
        setGame(newGame);
        
        panel = new ConnectFourPanel(getGame().getPieces());
        panel.updatePieces(getGame().getPieces());
        panel.setCurrentPiece(new ConnectFourPiece(getPlayerOnePieceColour()));
        
        setTimerLabel();
        setPlayerLabel(playerOneDetails[0], playerOneDetails[2], playerTwoDetails[0], playerTwoDetails[2]);
        panel.add(getTimerLabel());
        panel.add(getPlayerLabel());
        
        //Add menu bar
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

        //Add mouse listener for mouse movement
        addMouseMotionListener(new MotionListener());

        //Add mouse click listener
        addMouseListener(new ClickListener());

        //Add listener for new game button in menu
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                    System.out.println("new game clicked");
                    displayPlayAgain("Start new game?", false);
            }
        });

        saveGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                    System.out.println("save game clicked");
                    String saveGame = JOptionPane.showInputDialog("Enter save name:");
                    
                    
                    new Connect4GameSaver("saves\\" + saveGame + ".xml");
                    
            }
        });
        
        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                    System.out.println("load game clicked");
                    //String loadGame = JOptionPane.showInputDialog("Enter load name:");
                    
                    //new Connect4GameLoader("saves\\" + loadGame + ".xml");
                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new File(".\\saves"));
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Game saves only", "xml");
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showOpenDialog(ConnectFourGameGUI.this);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                    	new Connect4GameLoader("saves\\" +chooser.getSelectedFile().getName());
                       System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getName());
                    }
            }
        });
        
        //Add window listener (listens for window close event)
        addWindowListener(new WindowAdapter() {         
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to exit the program?", 
                "Exit Program Message Box",
                JOptionPane.YES_NO_OPTION);
                
                if (confirmed == JOptionPane.YES_OPTION) {
                    //Close this window
                    dispose();
                } 
            }
        });

        startTimer();
        
        //Initialize and display the the GUI
        add(panel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setIconImage(new ImageIcon(this.getClass()
                .getResource("ConnectFour.jpeg")).getImage());
        setTitle("Connect Four   Player One: \"" 
            + getGame().getPlayerName(Game.PLAYER_ONE) + "\"    Player Two: \"" 
            + getGame().getPlayerName(Game.PLAYER_TWO) +"\"");
            
            if (!getGame().getPlayer(getGame().getPlayerTurn() % TOTAL_PLAYERS).getPlayerType().equals("Human")) {
                                            int playerTurn = getGame().getPlayerTurn();
                                            int x;
                                            if ((getGame().getPlayerTurn() % TOTAL_PLAYERS == 0 && m_playerOneType == "Computer: Easy") 
                                             || (getGame().getPlayerTurn() % TOTAL_PLAYERS == 1 && m_playerTwoType == "Computer: Easy")) {
                                                ConnectFourEasyComputerPlayer player = (ConnectFourEasyComputerPlayer)getGame().getPlayer(playerTurn % TOTAL_PLAYERS);
                                                x = (int)player.makeAIMove(getGame().getBoard()).getX();
                                            } else {
                                                ConnectFourHardComputerPlayer player = (ConnectFourHardComputerPlayer)getGame().getPlayer(playerTurn % TOTAL_PLAYERS);
                                                x = (int)player.makeAIMove(getGame().getBoard()).getX();
                                            }
                                         getGame().incrementTurn();            
                                         performMove(x);
                                     }
            if(loaded) {
            	loadgame(loc);
            	
            }
    }
    private void loadgame(String loc) {
    	new Connect4GameLoader("saves\\" + loc);
    }
	/**< private class for handling mouse clicks */
    private class ClickListener implements MouseListener {

        public void mouseReleased(MouseEvent e) {
            //Game Logic
            if (!panel.animationThread.isAlive()) {
                if (getGame().getPlayer(getGame().getPlayerTurn() % TOTAL_PLAYERS).getPlayerType().equals("Human"))
                    if ((!getGame().gameWon() && !getGame().boardIsFull()) 
                        && !panel.animationThread.isAlive()) {
                        int x = Math.round(mouseX / ConnectFourPanel.Y_SPACING);
                        
                        performMove(x);
                        getGame().incrementTurn();
                
                }
            }
        }

        //Currently unused, but must be declared
        public void mouseClicked(MouseEvent e) {
        }

        //Currently unused, but must be declared
        public void mousePressed(MouseEvent e) {
            
        }

        //Currently unused, but must be declared
        public void mouseExited(MouseEvent e) {
        }

        //Currently unused, but must be declared
        public void mouseEntered(MouseEvent e) {
        }
    }
    /**< private class for detecting when mouse is moved */
    private class MotionListener implements MouseMotionListener {

        //Unused, but must be implemented
        public void mouseDragged(MouseEvent e) {
        }

        /**< Handle mouse moved event */
        public void mouseMoved(MouseEvent e) {
            //Dont allow the mouse to move when animating a piece dropping
            if (!panel.animationThread.isAlive()) {
                mouseX = e.getX();
                panel.setPieceX(mouseX);
            }
        }
    }
    
    public void performMove(int x) {
        if (!panel.animationThread.isAlive()) {
        System.out.println(getGame().getPlayer(getGame().getPlayerTurn() % TOTAL_PLAYERS).getName());
        if (x > BOARD_WIDTH) {
            x = BOARD_WIDTH - 1;
        }

        if (getGame().move(x, BOARD_HEIGHT, panel.getCurrentPiece()
                                .getPieceColour())) {
        //y is the height the piece will be dropped to
        int y = getGame().getLowestEmptySlot(x) + 1;

        if (y >= BOARD_HEIGHT + 1) {
            y = BOARD_HEIGHT + 1;
        }

         panel.dropPiece(x, BOARD_HEIGHT - y, panel.getCurrentPiece()
                                .getPieceColour());

         new Thread(new Runnable() {
             public void run() {

                 while(panel.animationThread.isAlive()){
                 }

                 panel.updatePieces(getGame().getPieces()); 
                 if (!getGame().boardIsFull()) {
                     if (panel.getCurrentPiece().getPieceColour()
                                            == ConnectFourPanel.YELLOW_PIECE) { 
                                            panel.setCurrentPiece(
                                                new ConnectFourPiece(
                                                ConnectFourPanel.RED_PIECE));
                                        } else {
                                            panel.setCurrentPiece(
                                                new ConnectFourPiece(
                                                ConnectFourPanel.YELLOW_PIECE));
                                        }
                                    }
                                    panel.refreshDisplay();
                                }
        }).start();
    } else {
        if (getGame().getPlayer(getGame().getPlayerTurn() % TOTAL_PLAYERS).getPlayerType() == "Human") {
            javax.swing.JOptionPane.showMessageDialog(null, 
                                            "Invalid move");
        }
    }
                    if (getGame().gameWon()) {
                        getTimer().stop();
                        if (panel.getCurrentPiece().getPieceColour() 
                                == getPlayerOnePieceColour()) {
                            displayWinner(getGame().getPlayerName(Game.PLAYER_ONE));
                        } else {
                            displayWinner(getGame().getPlayerName(Game.PLAYER_TWO));
                        }
                        if (!displayPlayAgain("Play again?", true)) {
                            dispose();
                        }
                    }
                    if (getGame().boardIsFull() && !getGame().gameWon()) {
                        displayDraw();
                        getTimer().stop();
                    }
                    
                    if (!getGame().gameWon()) {
                    final int ANIMATION_TIME = 750;
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                               Thread.sleep(ANIMATION_TIME); 
                            } catch (Exception e) {
                        
                            }
                            if (!getGame().getPlayer(getGame().getPlayerTurn() % TOTAL_PLAYERS).getPlayerType().equals("Human")) {
                                int playerTurn = getGame().getPlayerTurn();
                                int x;
                                if ((getGame().getPlayerTurn() % TOTAL_PLAYERS == 0 && m_playerOneType == "Computer: Easy") 
                                 || (getGame().getPlayerTurn() % TOTAL_PLAYERS == 1 && m_playerTwoType == "Computer: Easy")) {
                                    ConnectFourEasyComputerPlayer player = (ConnectFourEasyComputerPlayer)getGame().getPlayer(playerTurn % TOTAL_PLAYERS);
                                    x = (int)player.makeAIMove(getGame().getBoard()).getX();
                                } else {
                                    ConnectFourHardComputerPlayer player = (ConnectFourHardComputerPlayer)getGame().getPlayer(playerTurn % TOTAL_PLAYERS);
                                    x = (int)player.makeAIMove(getGame().getBoard()).getX();
                                }
                                
                                         
                                performMove(x);
                                getGame().incrementTurn();
                            }
                        }
                    }).start();
                    }
        }
                    
    }

    /**
    * Message dialog tells players the game is a draw
    * @param none
    */
    private boolean displayDraw() { 
        JOptionPane.showMessageDialog(null, "It's a draw!");
        displayPlayAgain("Play again?", true);
        return true;
    }

    /**
    * Message dialog asks players if they want to play again
    * @param message - A string with the message to display
    */
    private boolean displayPlayAgain(String message, boolean win) {
        int reply = JOptionPane.showConfirmDialog(null, message , message, 
            JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	resetTimer();
                Player playerOne;
                Player playerTwo;
                
                if (m_playerOneType.equals("Computer: Easy")) {
                            playerOne = new ConnectFourEasyComputerPlayer(getGame().getPlayerName(Game.PLAYER_ONE),
                                                                                getPlayerOnePieceColour());
                        } else if (m_playerOneType.equals("Computer: Hard")) {
                            playerOne = new ConnectFourHardComputerPlayer(getGame().getPlayerName(Game.PLAYER_ONE),
                                                                                getPlayerOnePieceColour());
                        } else {
                            playerOne = new HumanPlayer(getGame().getPlayerName(Game.PLAYER_ONE),
                                                                                getPlayerOnePieceColour());
                        }
                        
                        if (m_playerTwoType.equals("Computer: Easy")) {
                            playerTwo = new ConnectFourEasyComputerPlayer(getGame().getPlayerName(Game.PLAYER_TWO),
                                                            getPlayerTwoPieceColour());
                        } else if (m_playerTwoType.equals("Computer: Hard")) {
                            playerTwo = new ConnectFourHardComputerPlayer(getGame().getPlayerName(Game.PLAYER_TWO),
                                                            getPlayerTwoPieceColour());
                        } else {
                            playerTwo = new HumanPlayer(getGame().getPlayerName(Game.PLAYER_TWO),
                                                            getPlayerTwoPieceColour());
                        }

                ConnectFourGame newGame = new ConnectFourGame(playerOne, playerTwo);
                
                setGame(newGame);
                if(win){
                	newGame.incrementTurn();
                }else{
                    newGame.incrementTurn();
                    newGame.incrementTurn();
                }
            panel.updatePieces(getGame().getPieces());
            panel.setCurrentPiece(new ConnectFourPiece(getPlayerOnePieceColour()));
            panel.refreshDisplay();
        }
        return reply == JOptionPane.YES_OPTION;
    }
}