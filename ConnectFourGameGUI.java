/**
 * @file ConnectFourGameGUI.java
 * @author Kristoffer Page, Jonathan Bailey
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConnectFourGameGUI extends GameGUI {
    /**< This is the height (amount of slots/places vertically) in the board */
    public static final int BOARD_HEIGHT = 7;
    
    /**< This is the width (amount of slots/places horizontally) in the board */
    public static final int BOARD_WIDTH = 10;
    
    /**< This is the panel that of draws the graphics to the screen */
    private static ConnectFourPanel m_panel;
    
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
    private int m_mouseX;
    
    /**< Stores an instance of ConnectFourGame */
    private static ConnectFourGame m_game;

    /**< Stores the numbers for where individual players are store in arrays */
    private int PLAYER_ONE = 0;
    private int PLAYER_TWO = 1;
        
    /**< Stores number of players in game */
    private int TOTAL_PLAYERS = 2;
    
    /**< Stores the length of time it takes to animate a piece dropping
     * in milliseconds */
    private final static int ANIMATION_TIME = 750;
    
    /**< Stores both player's details */
    private Player m_playerOne;
    private Player m_playerTwo;
    
    /**< Stores both player's piece colours */
    private Piece.ConnectFourPieceColour m_playerOnePieceColour;
    private Piece.ConnectFourPieceColour m_playerTwoPieceColour;
    
    /**< Stores two strings containing each player's type */
    private String m_playerOneType;
    private String m_playerTwoType;
    
    /**
     * Get method that returns panel for connect 4
     * @return panel
     * 
     */
    public static ConnectFourPanel getPanel(){
        return m_panel;
    }
    
    /**
     * Get method that returns game for connect 4
     * @return game
     * 
     */
    public static ConnectFourGame getGame(){
        return m_game;
    }
    
    /**
     * Set method that sets the piece colours
     * @param p1Colour - colour of player1, player2 is not 
     * needed as it must be opposite colour
     */
    public void setPieceColours(String p1Colour){
        if(p1Colour.equals("Red")){
            m_playerOnePieceColour = Piece.ConnectFourPieceColour.RED;
            m_playerTwoPieceColour = Piece.ConnectFourPieceColour.YELLOW;
        }else{
            m_playerOnePieceColour = Piece.ConnectFourPieceColour.YELLOW;
            m_playerTwoPieceColour = Piece.ConnectFourPieceColour.RED;
        }
    }
    
    /**
     * Set method that sets the types of the players
     * @param playerTypes[] - a string array that contains the types of both players
     * 
     */
    private void setPlayerTypes(String[] playerTypes) {
        m_playerOneType = playerTypes[PLAYER_ONE];
        m_playerTwoType = playerTypes[PLAYER_TWO];
    }
    
    /**
     * Get method that gets the piece colour of player 1
     * @return m_playerOnePieceColour - the colour of player one's piece
     * 
     */
    public Piece.ConnectFourPieceColour getPlayerOnePieceColour(){
        return m_playerOnePieceColour;
    }
    
    /**
     * Get method that gets the piece colour of player 2
     * @return m_playerTwoPieceColour - the colour of player two's piece
     * 
     */
    public Piece.ConnectFourPieceColour getPlayerTwoPieceColour(){
        return m_playerTwoPieceColour;
    }
    
    /**
     * Set method that sets the game as a new game of connect4
     * @Param newGame - This will set the game to the new game that is passed in
     */
    public static void setGame(ConnectFourGame newGame) {
        m_game = newGame;
    }
    
    /**
     * Constructor for ConnectFourGameGUI
     * @param playerOneName - String representation of player one's name
     * @param playerTwoName - String representation of player two's name
     * @param loaded        - Boolean will be true if it's loaded from file
     * @param loc           - String that stores file name of chosen save file
     */
    public ConnectFourGameGUI(String[] playerOneDetails,
                              String[] playerTwoDetails,
                              boolean loaded, String loc) {
        //Call super class constructor
        super(WINDOW_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        final int PLAYER_NAME = 0;
        final int PLAYER_TYPE = 1;
        final int PLAYER_COLOUR = 2;
        
        setPieceColours(playerOneDetails[PLAYER_COLOUR]);
        setPlayerTypes(new String[] {playerOneDetails[PLAYER_TYPE],
                                     playerTwoDetails[PLAYER_TYPE]});
        
        Player playerOne;
        Player playerTwo;
        
        if (m_playerOneType.equals("Computer: Easy")) {
            playerOne = new ConnectFourEasyComputerPlayer(
                            playerOneDetails[PLAYER_NAME],
                                getPlayerOnePieceColour());
        } else if (m_playerOneType.equals("Computer: Hard")) {
            playerOne = new ConnectFourHardComputerPlayer(
                            playerOneDetails[PLAYER_NAME],
                                getPlayerOnePieceColour());
        } else {
            playerOne = new HumanPlayer(
                            playerOneDetails[PLAYER_NAME],
                                getPlayerOnePieceColour());
        }
        
        if (m_playerTwoType.equals("Computer: Easy")) {
            playerTwo = new ConnectFourEasyComputerPlayer(
                            playerTwoDetails[PLAYER_NAME],
                                getPlayerTwoPieceColour());
        } else if (m_playerTwoType.equals("Computer: Hard")) {
            playerTwo = new ConnectFourHardComputerPlayer(
                            playerTwoDetails[PLAYER_NAME],
                                getPlayerTwoPieceColour());
        } else {
            playerTwo = new HumanPlayer(
                            playerTwoDetails[PLAYER_NAME],
                                getPlayerTwoPieceColour());
        }
        
        ConnectFourGame newGame = new ConnectFourGame(playerOne, playerTwo);
        
        setGame(newGame);
        
        m_panel = new ConnectFourPanel(getGame().getPieces());
        m_panel.updatePieces(getGame().getPieces());
        m_panel.setCurrentPiece(new ConnectFourPiece(
                                    getPlayerOnePieceColour()));
        
        setTimerLabel();
        setPlayerLabel(playerOneDetails[PLAYER_NAME],
                       playerOneDetails[PLAYER_COLOUR],
                       playerTwoDetails[PLAYER_NAME],
                       playerTwoDetails[PLAYER_COLOUR]);
        m_panel.add(getTimerLabel());
        m_panel.add(getPlayerLabel());
        
        //Add menu bar
        JMenu menu = new JMenu("Menu");
        add(menu);
        JMenuItem newGameButton = new JMenuItem("New Game");
        JMenuItem saveGameButton = new JMenuItem("Save Game");
        JMenuItem loadGameButton = new JMenuItem("Load Game");
        menu.add(newGameButton);
        menu.add(saveGameButton);
        menu.add(loadGameButton);
        //menu.add(new JMenuItem("Pause game"));
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
                String saveGame = JOptionPane.showInputDialog(
                                                    "Enter save name:");
                Date dNow = new Date( );
                SimpleDateFormat timeStamp =
                new SimpleDateFormat ("H.mm dd.MM.yy");
                
                System.out.println("Current Date: " + timeStamp.format(dNow));
                
                new ConnectFourGameSaver("saves\\connect4saves\\" + saveGame
                                      + "  [" + timeStamp.format(dNow) + "]"
                                      + ".xml");
                
                
            }
        });
        
        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("load game clicked");
                
                String saveFile = "";
                String fileExtension = "";
                
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File(
                                                ".\\saves\\connect4saves\\"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                                     "Game saves only", "xml");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(ConnectFourGameGUI.this);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    saveFile = chooser.getSelectedFile().getName();
                    fileExtension = saveFile.substring(saveFile.lastIndexOf(".")
                                                       + 1, saveFile.length());
                    
                }
                if(fileExtension.equals("xml")) { //checks if file selected is an xml file
                    if(saveFile != "") { //checks if the user selected cancel instead
                        new ConnectFourGameLoader("saves\\connect4saves\\"
                                                  + saveFile);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, 
                                        "You have not chosen a game save");
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
        add(m_panel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setIconImage(new ImageIcon(this.getClass()
                               .getResource("ConnectFour.jpeg")).getImage());
        setTitle("Connect Four   Player One: \""
                 + getGame().getPlayerName(Game.PLAYER_ONE)
                 + "\"    Player Two: \""
                 + getGame().getPlayerName(Game.PLAYER_TWO) +"\"");
        
        if (!getGame().getPlayer(getGame().getPlayerTurn() % TOTAL_PLAYERS)
                          .getPlayerType().equals("Human")) {
            int playerTurn = getGame().getPlayerTurn();
            int x;
            if ((getGame().getPlayerTurn() % TOTAL_PLAYERS == PLAYER_ONE
                && m_playerOneType == "Computer: Easy")
                || (getGame().getPlayerTurn() % TOTAL_PLAYERS == PLAYER_TWO
                && m_playerTwoType == "Computer: Easy")) {
                ConnectFourEasyComputerPlayer player =
                    (ConnectFourEasyComputerPlayer)getGame()
                        .getPlayer(playerTurn % TOTAL_PLAYERS);
                x = (int)player.makeAIMove(getGame().getBoard()).getX();
            } else {
                ConnectFourHardComputerPlayer player =
                    (ConnectFourHardComputerPlayer)getGame()
                        .getPlayer(playerTurn % TOTAL_PLAYERS);
                x = (int)player.makeAIMove(getGame().getBoard()).getX();
            }
            getGame().incrementTurn();
            performMove(x);
        }
        if(loaded) {
            loadgame(loc);
        }
    }
    
    /**
     * Method that retrieves the connect4 save file from menuGUI
     * @param loc - string that stores file name of chosen savefile
     * 
     */
    private void loadgame(String loc) {
        new ConnectFourGameLoader("saves\\connect4saves\\" + loc);
    }
    
    /**< private class for handling mouse clicks */
    private class ClickListener implements MouseListener {
        
        public void mouseReleased(MouseEvent e) {
            //Game Logic
            if (!m_panel.getAnimationThread().isAlive()) {
                if (getGame().getPlayer(getGame()
                                            .getPlayerTurn() % TOTAL_PLAYERS)
                                                .getPlayerType()
                                                    .equals("Human"))
                    if ((!getGame().gameWon() && !getGame().boardIsFull())
                        && !m_panel.getAnimationThread().isAlive()) {
                        int x = Math.round(m_mouseX /
                                           ConnectFourPanel.Y_SPACING);
                        
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
            if (!m_panel.getAnimationThread().isAlive()) {
                m_mouseX = e.getX();
                m_panel.setPieceX(m_mouseX);
            }
        }
    }
    
    /**
     * A method which will place a piece in column passed in
     * @param column - An integer representing the column
     for the piece to be placed in.
     */
    public void performMove(int column) {
        if (!m_panel.getAnimationThread().isAlive()) {

            if (column > BOARD_WIDTH) {
                column = BOARD_WIDTH - 1;
            }
            
            if (getGame().move(column, BOARD_HEIGHT, m_panel.getCurrentPiece()
                               .getPieceColour())) {
                //y is the height the piece will be dropped to
                int y = getGame().getLowestEmptySlot(column) + 1;
                
                if (y >= BOARD_HEIGHT + 1) {
                    y = BOARD_HEIGHT + 1;
                }
                
                m_panel.dropPiece(column, BOARD_HEIGHT - y,
                                        m_panel.getCurrentPiece()
                                            .getPieceColour());
                
                animate();
                
            } else {
                if (getGame().getPlayer(getGame()
                                            .getPlayerTurn() % TOTAL_PLAYERS)
                                                .getPlayerType() == "Human") {
                    javax.swing.JOptionPane.showMessageDialog(null,
                                                              "Invalid move");
                }
            }
            if (getGame().gameWon()) {
                getTimer().stop();
                if (m_panel.getCurrentPiece().getPieceColour()
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
            computerNextTurn();
        }
    }
    
    /**
     * A method which will work out if the next player's turn is a computer
     player, and then take the turn
     */
    private void computerNextTurn() {
        if (!getGame().gameWon()) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(ANIMATION_TIME);
                    } catch (Exception e) {
                        
                    }
                    if (!getGame().getPlayer(getGame()
                                             .getPlayerTurn() % TOTAL_PLAYERS)
                        .getPlayerType()
                        .equals("Human")) {
                        int playerTurn = getGame().getPlayerTurn();
                        int x;
                        if ((getGame()
                             .getPlayerTurn() % TOTAL_PLAYERS == PLAYER_ONE
                             && m_playerOneType == "Computer: Easy")
                            || (getGame()
                                .getPlayerTurn() % TOTAL_PLAYERS == PLAYER_TWO
                                && m_playerTwoType == "Computer: Easy")) {
                                ConnectFourEasyComputerPlayer player =
                                (ConnectFourEasyComputerPlayer)getGame()
                                .getPlayer(playerTurn
                                           % TOTAL_PLAYERS);
                                x = (int)player.makeAIMove(
                                                           getGame().getBoard()).getX();
                            } else {
                                ConnectFourHardComputerPlayer player
                                = (ConnectFourHardComputerPlayer)getGame()
                                .getPlayer(playerTurn % TOTAL_PLAYERS);
                                x = (int)player.makeAIMove(getGame()
                                                           .getBoard())
                                .getX();
                            }
                        performMove(x);
                        getGame().incrementTurn();
                    }
                }
            }).start();
        }
    }
    
    /**
     * A method which will call the animation Thread to start animation
     in the ConnectFourPanel
     */
    private void animate() {
        new Thread(new Runnable() {
            public void run() {
                
                while(m_panel.getAnimationThread().isAlive()){
                }
                
                m_panel.updatePieces(getGame().getPieces());
                if (!getGame().boardIsFull()) {
                    if (m_panel.getCurrentPiece().getPieceColour()
                        == ConnectFourPanel.YELLOW_PIECE) {
                        m_panel.setCurrentPiece(new ConnectFourPiece(
                                                    ConnectFourPanel
                                                        .RED_PIECE));
                    } else {
                        m_panel.setCurrentPiece(new ConnectFourPiece(
                                                    ConnectFourPanel
                                                        .YELLOW_PIECE));
                    }
                }
                m_panel.refreshDisplay();
            }
        }).start();
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
        int reply = JOptionPane.showConfirmDialog(null, message, message,
                                                  JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            resetTimer();
            Player playerOne;
            Player playerTwo;
            
            if (m_playerOneType.equals("Computer: Easy")) {
                playerOne = new ConnectFourEasyComputerPlayer(
                                getGame()
                                    .getPlayerName(
                                        Game.PLAYER_ONE),
                                getPlayerOnePieceColour());
            } else if (m_playerOneType.equals("Computer: Hard")) {
                playerOne = new ConnectFourHardComputerPlayer(
                                getGame()
                                    .getPlayerName(
                                        Game.PLAYER_ONE),
                                getPlayerOnePieceColour());
            } else {
                playerOne = new HumanPlayer(
                                getGame()
                                    .getPlayerName(
                                        Game.PLAYER_ONE),
                                getPlayerOnePieceColour());
            }
            
            if (m_playerTwoType.equals("Computer: Easy")) {
                playerTwo = new ConnectFourEasyComputerPlayer(
                                getGame()
                                    .getPlayerName(
                                        Game.PLAYER_TWO),
                                getPlayerTwoPieceColour());
            } else if (m_playerTwoType.equals("Computer: Hard")) {
                playerTwo = new ConnectFourHardComputerPlayer(
                                getGame()
                                    .getPlayerName(
                                        Game.PLAYER_TWO),
                                getPlayerTwoPieceColour());
            } else {
                playerTwo = new HumanPlayer(
                                getGame()
                                    .getPlayerName(
                                        Game.PLAYER_TWO),
                                getPlayerTwoPieceColour());
            }
            
            ConnectFourGame newGame = new ConnectFourGame(
                                          playerOne, playerTwo);
            
            setGame(newGame);
            
            if(win && (getGame().getPlayerTurn() % TOTAL_PLAYERS
                       == PLAYER_ONE)) {
                newGame.incrementTurn();
            }
            
            m_panel.updatePieces(getGame().getPieces());
            m_panel.setCurrentPiece(new ConnectFourPiece(
                                      getPlayerOnePieceColour()));
            m_panel.refreshDisplay();
        }
        return reply == JOptionPane.YES_OPTION;
    }

    /**
     * This is the main method, which is used for testing
     * @param args[] - This array of Strings contains all the arguments
     that are passed in when the program is run. These are not used though,
     so any arguments passed in will have no effect on the program or testing.
     */
    public static void main(String args[]) {
        final int TOTAL_PLAYER_COMPONENTS = 3;
        final int PLAYER_COMPONENT_ONE = 0;
        final int PLAYER_COMPONENT_TWO = 1;
        final int PLAYER_COMPONENT_THREE = 2;
        String[] s1 = new String[TOTAL_PLAYER_COMPONENTS];
        String[] s2 = new String[TOTAL_PLAYER_COMPONENTS];
        s1[PLAYER_COMPONENT_ONE] = "Tom";
        s1[PLAYER_COMPONENT_TWO] = "Human";
        s1[PLAYER_COMPONENT_THREE] = "Red";
        s2[PLAYER_COMPONENT_ONE] = "Jon";
        s2[PLAYER_COMPONENT_TWO] = "Human";
        s2[PLAYER_COMPONENT_THREE] = "Yellow";
        ConnectFourGameGUI g = new ConnectFourGameGUI(s1,s2,false,"");
        
        System.out.println("\nConnectFourGameGUI Tests:\n");
        System.out.println("ConnectFourGameGUI.displayPlayAgain() "
                                   + "- Begin");
        System.out.println("Expected output: true if yes pressed, " 
                                   + "false if no or cross pressed");
        System.out.println("");
        System.out.println("Actual output: "
                                   + g.displayPlayAgain("Play Again?", false));
        System.out.println("ConnectFourGameGUI.displayPlayAgain() "
                                   + "- End");
        System.out.println("");
        
        final int COLUMN_THREE = 2;
        final int COLUMN_SEVEN = 6;
        
        System.out.println("ConnectFourGameGUI.performMove() "
                                           + "- Begin");
        System.out.println("Expected output: first piece placed in the third "
                                           + "column");
        System.out.println("");
        System.out.println("Actual output: on screen.");
        g.performMove(COLUMN_THREE);
        System.out.println("ConnectFourGameGUI.performMove() "
                                           + "- End");
        System.out.println("");
        
        System.out.println("ConnectFourGameGUI.performMove() - Begin");
        System.out.println("Expected output: Nothing as move "
                                   + "shouldn't be performed until"
                                   + "the last animation has finished");
        System.out.println("");
        System.out.println("Actual output: on screen.");
        g.performMove(COLUMN_SEVEN);
        System.out.println("ConnectFourGameGUI.performMove() - End");
        System.out.println("");
        
        System.out.println("Next test in .75 seconds...");
        
        System.out.println("");
        
        
        
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (Exception e) {
            
        }
        
        System.out.println("ConnectFourGameGUI.performMove() - Begin");
        System.out.println("Expected output: opponent piece placed in the "
                                                   + "third column, ontop of"
                                                   + " the first piece");
        System.out.println("");
        System.out.println("Actual output: on screen.");
        g.performMove(COLUMN_THREE);
        System.out.println("ConnectFourGameGUI.performMove() - End");
        System.out.println("");
    }
}