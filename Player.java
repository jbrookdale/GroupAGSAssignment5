/*--80 Characters Wide---------- Remove before Submitting --------------------*/

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
 


abstract class Player <C> {
    String m_Name;
    C connectFourPlayerColour;
    private int m_Score = 0;

    public Player (String newName, C newPlayerColour){
        m_Name = newName;
        connectFourPlayerColour = newPlayerColour;

    }
    	
     boolean move (int x, int y) {
		return false;
	}

    public String getName () {
        return m_Name;
    }
    
    public int getScore() {
        return m_Score;
    }

    public C getColour () {
            return connectFourPlayerColour;
    }
    public void setScore(int points) {
        m_Score+=points;
    }
}
