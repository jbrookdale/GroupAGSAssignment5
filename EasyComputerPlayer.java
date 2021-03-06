import java.awt.Point;
/**
* @file EasyComputerPlayer.java
* @author Jon Bailey
* @date  21st Mar 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition, for Abstract class
*
* @brief This is an Abstract EasyComputerPlayer class. 
*		
* This class calls the name, piece colour of player and pass in ComputerEasy player
*
*/

public abstract class EasyComputerPlayer <C> extends ComputerPlayer {
    /**
     * EasyComputerPlayer Constructor
     * @param newName -The name of the player
     * @param newPlayerColour - The piece colour of the player
     */
    public EasyComputerPlayer(String newName, C newPlayerColour) {
		super(newName, newPlayerColour,"ComputerEasy");
		
	}
}
