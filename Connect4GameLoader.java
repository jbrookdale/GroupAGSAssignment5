/**
* @file Connect4GameLoader.java
* @author Ieuan Skinner
* @date 26 March 14
* @see http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
* 
* @brief This class reads in game data specific to Connect4.
*
* 
* This class will read the xml file at the location passed to it and 
* set the data as expected. This class specifically deals with things 
* that are specific to Connect4 such as piece positions and colour.
 */

public class Connect4GameLoader extends GameLoader{

	/**
	 * This is the constructor for the Connect4GameLoader class.
	 * 
	 * @param fileName	fileName will be used to locate/refer to the file 
	 * 					the user wants to load.
	 */
	public Connect4GameLoader(String fileName) {
		super(fileName);
	}
	
	/**
	 * This method is used to check the "game type" value of the save file.
	 * 
	 * @return boolean	true or false will be returned if the selected file
	 * 					corresponds to Connect4 (true) or not (false).
	 */
	public boolean getGameType(){
		if(getM_gametype().equals("Connect4")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
		THINGS THAT ARE STILL NEEDED TO BE DONE:
			-- Needs to handle loading an already won??
	*/
	
	/**
	 * This method is used to initialise the game with all the data taken from
	 * the save file.
	 * 
	 * It first initialises the players and their piece colours etc. then sets 
	 * the board accordingly alongside the JLabels and timer allowing the user
	 * to continue from where the save left off. 
	 */
	public void setupBoard(){
		Player playerOne;
		Player playerTwo;
		
		Piece.ConnectFourPieceColour p1Colour;
		Piece.ConnectFourPieceColour p2Colour;
		
		if(getM_playerOneColour().equals("Red")){
			p1Colour = Piece.ConnectFourPieceColour.RED;
			p2Colour = Piece.ConnectFourPieceColour.YELLOW;
		}else{
			p1Colour = Piece.ConnectFourPieceColour.YELLOW;
			p2Colour = Piece.ConnectFourPieceColour.RED;
		}
		
		 if (getM_playerOneType().equals("Human")) {
	           playerOne = new HumanPlayer(getM_playerOneName(), p1Colour);
	     } else if(getM_playerOneType().equals("Computer: Easy")) {
	    	 playerOne = new ConnectFourEasyComputerPlayer(
	            			getM_playerOneName(), p1Colour);
	     }else{
	    	 playerOne = new ConnectFourHardComputerPlayer(getM_playerOneName(), p1Colour);
	     }

		 if (getM_playerTwoType().equals("Human")) {
			 playerTwo = new HumanPlayer(getM_playerTwoName(), p2Colour);
	     }else if(getM_playerTwoType().equals("Computer: Easy")){
	    	 playerTwo = new ConnectFourEasyComputerPlayer(getM_playerTwoName(), p2Colour);
	     }else{
	    	 playerTwo = new ConnectFourHardComputerPlayer(getM_playerTwoName(), p2Colour);
	     }
	        
		ConnectFourGame loadGame = new ConnectFourGame(playerOne, playerTwo);
		
		if(p1Colour == Piece.ConnectFourPieceColour.RED){
			//Setting the red pieces if player 1 was red.
			for(int i = 0; i < getP1PiecesX().size(); i++){
				ConnectFourBoard.setRedPieces(getP1PiecesX().get(i), getP1PiecesY().get(i));
			}	
			//Setting the yellow pieces if player 2 was yellow.
			for(int i = 0; i < getP2PiecesX().size(); i++){
				ConnectFourBoard.setYellowPieces(getP2PiecesX().get(i), 
												 getP2PiecesY().get(i));
			}
		}else{
			//Setting the yellow pieces if player 1 was yellow.
			for(int i = 0; i < getP1PiecesX().size(); i++){
				ConnectFourBoard.setYellowPieces(getP1PiecesX().get(i), getP1PiecesY().get(i));
			}
			//Setting the red pieces if player 2 was red.
			for(int i = 0; i < getP2PiecesX().size(); i++){
				ConnectFourBoard.setRedPieces(getP2PiecesX().get(i), getP2PiecesY().get(i));
			}
		}

		/* Setting the timer and player turn JLabel */
		ConnectFourGameGUI.setTime(getM_time());
		ConnectFourGameGUI.resetPlayerLabel(getM_playerOneName(), getM_playerOneColour(), 
											getM_playerTwoName(), getM_playerTwoColour());
		
		/* Setting the turn counter */
		if(getM_playerTurn().equals("Player 1")){
			ConnectFourGameGUI.getGame().setTurn(0);
			ConnectFourGameGUI.getPanel().setCurrentPiece(new ConnectFourPiece(p1Colour));
		}else{
			ConnectFourGameGUI.getGame().setTurn(1);
			ConnectFourGameGUI.getPanel().setCurrentPiece(new ConnectFourPiece(p2Colour));
		}
		
		ConnectFourGameGUI.setGame(loadGame);
		ConnectFourGameGUI.getPanel().updatePieces(loadGame.getPieces());
		ConnectFourGameGUI.getPanel().refreshDisplay();
	}
}
