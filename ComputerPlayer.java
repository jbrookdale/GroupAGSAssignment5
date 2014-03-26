/**
* @file EasyComputerPlayer.java
* @author Jon Bailey
* @date  21st Mar 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition, for Abstract class
*
* @brief This is an Abstract ComputerPlayer class. 
*		
* This class stores the name, piece colour and player type
*
*/


abstract class ComputerPlayer <C> extends Player{
  
    
    /**
     * ComputerPlayer Constructor
     * @param newName -The name of the player
     * @param newPlayerColour - The piece colour of the player
     */
	public ComputerPlayer(String newName, C newPlayerColour, String playerType){
    	super(newName,newPlayerColour,playerType);
    	
    	
    }
	  /*
     * This is the main method that test this class
     * @param args -nil
     */
    public static void main(String[] args) {
    	
    	
        // Tests go here
    }
}