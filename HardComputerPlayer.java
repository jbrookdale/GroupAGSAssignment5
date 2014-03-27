/**
* @file HardComputerPlayer.java
* @author Jon Bailey
* @date  21st Mar 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition, for Abstract class
*
* @brief This is an Abstract ComputerPlayer class.The class extends Computer Player
*		
* This class stores the name, piece colour and player type
*
*/
public abstract class HardComputerPlayer <C> extends ComputerPlayer {
	 
    /**
     * HardComputerPlayer Constructor
     * @param newName -The name of the player
     * @param newPlayerColour - The piece colour of the player
     */
    
    public HardComputerPlayer(String newName, C newPlayerColour) {
    	super(newName, newPlayerColour,"ComputerHard");
		
	}
}
