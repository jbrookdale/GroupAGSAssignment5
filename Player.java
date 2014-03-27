import java.awt.Component;
import java.awt.Point;

/**
 * @file    -Player.java
 * @author  -Thomas Vass
 * @date    -03/02/14
 * @see     -Needs to be Changed!!!::Deitel and Deitel, Fig. 13.5, page 502
 *           from Java for Programmers, Prentice Hall
 *
 *  @brief This class is responsible for defining a player
 *
 *  The Player superclass defines a player. Each player will have a name,
 *  score, and colour. There is no setName or setColour method as once a player is 
 *  defined you do not need to change their name or colour. The player will need to 
 *  collaborate with the game through the move method where it will take input 
 * (from either the user or a computer-generated move); the move method is abstract,
 * as each type of player will have a different algorithm for deciding a move.
 *
 */
 


public abstract class Player <C> {
	/** Variable for the Player name */
    private String m_Name;
    /** Variable for the Player colour */
    private C m_PlayerColour;
    /** Variable that stores player's score */
    private int m_Score = 0;
    /** Variable for the type of Player */
    private String m_PlayerType;
    
    /**
     * This method gets the name of player
     * @return -return player name
     */
    public String getName () {
        return m_Name;
    }
    
    /**
     * This method gets the score of player
     * @return -return player's score
     */
    public int getScore() {
        return m_Score;
    }
    
    /**
     * This method gets the colour of player's piece
     * @return m_PlayerColour -return player's colour
     */
    public C getColour () {
        return m_PlayerColour;
    }
    
    /**
     * This method sets of player's score
     */
    public void setScore(int points) {
        m_Score+=points;
    }
    
    /**
     * This method gets the  player's type
     * @return m_PlayerType -return player type
     */
    public String getPlayerType() {
        return m_PlayerType;
    }
   /**
    * This is the constructor for Player class.
    * It initialise the player name, player colour and player typr
    * @param newName - Name of player
    * @param newPlayerColour -Colour of player piece
    * @param playerType - Type of player
    */
    public Player (String newName, C newPlayerColour, String playerType){
        m_Name = newName;
        m_PlayerColour = newPlayerColour;
        m_PlayerType = playerType;
    }
    
    /**
     * This method checks if move method is executed
     * @param x - Horizontal coordinate
     * @param y -vertical coordinate
     * @return false -Test if method execute
     */
     boolean move (int x, int y) {
		return false;
	}
}
