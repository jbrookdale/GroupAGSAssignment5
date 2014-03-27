/**
* @file OthelloGameSaver.java
* @author Ieuan Skinner
* @date 26 March 14
* @see http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
* 
* @brief This class writes game data specific to Connect4 to an xml file.
* 
* This class will write the data specific to Othello to an xml file
* specified by the player. They have the option to name the save file but
* not choose it's location i.e. it defaults to "saves/othellosaves/ ... ". 
* Specific data includes its gametype, piece colour and piece locations as
* their boards do not index spaces the same.
 */

import org.w3c.dom.Element;

public class OthelloGameSaver extends GameSaver{

	/**
	 * This is the constructor for the OthelloGameSaver class.
	 * 
	 * @param fileName	fileName is used to locate/refer to the file 
	 * 					the user wants to save to. If fileName doesn't
	 * 					already exist a new instance of it will be 
	 * 					created.
	 */ 
	public OthelloGameSaver(String fileName) {
		super(fileName);
	}
	
	/**
	 * This method will write the players names to the xml file.
	 * Appending them to the appropriate player id tag.
	 */
	public void setPlayerName(){
		String playerOneName = OthelloGameGUI.getPlayerName(0);
		String playerTwoName = OthelloGameGUI.getPlayerName(1);
		Element p1Name = getDoc().createElement("name");
		Element p2Name = getDoc().createElement("name");
		
		p1Name.appendChild(getDoc().createTextNode(playerOneName));
		p2Name.appendChild(getDoc().createTextNode(playerTwoName));
	
		getPlayers(0).appendChild(p1Name);
		getPlayers(1).appendChild(p2Name);
	}
	
	/**
	 * This method will set the game type element in the xml file
	 * to Othello. This value is then used to test the file wanted
	 * to load is of the correct game. (i.e. so you can't load an 
	 * Connect4 game on to an Othello game).
	 * 
	 */
	public void setGameTypeElement(){
		Element gametype = getDoc().createElement("gametype");
		gametype.appendChild(getDoc().createTextNode("Othello"));
		getRootElement().appendChild(gametype);		
	}
	
	/**
	 * This method sets all the players pieces x and y value and appends
	 * them to the piece1 (player 1) or piece2 (player 2) tag. Easiest way
	 * to do this for loading.
	 */
	public void setPieces(){
		OthelloPiece[][] piecesToBeSaved = OthelloBoard.getPieces(); 
		
		for(int j = 0; j < piecesToBeSaved[0].length; j++){
			for(int i = 0; i < piecesToBeSaved.length; i++){
				if(piecesToBeSaved[i][j].getPieceColour() == Piece.OthelloPieceColour.BLACK){

					Element newBlackPiece;
					
					if(Game.getPlayer(0).getColour().equals("Black")){
						newBlackPiece = getDoc().createElement("piece1");
					}else{
						newBlackPiece = getDoc().createElement("piece2");
					}
					
					Element x = getDoc().createElement("x");
					x.appendChild(getDoc().createTextNode(i + ""));
					Element y = getDoc().createElement("y");
					y.appendChild(getDoc().createTextNode(j + ""));
					
					newBlackPiece.appendChild(x);
					newBlackPiece.appendChild(y);

					if(Game.getPlayer(0).getColour().equals("Black")){
						getPlayers(0).appendChild(newBlackPiece);
					}else{
						getPlayers(1).appendChild(newBlackPiece);
					}
				}else if(piecesToBeSaved[i][j].getPieceColour() == Piece.OthelloPieceColour.WHITE){

					Element newWhitePiece;
					
					if(Game.getPlayer(1).getColour().equals("Black")){
						newWhitePiece = getDoc().createElement("piece1");
					}else{
						newWhitePiece = getDoc().createElement("piece2");
					}
					
					Element x = getDoc().createElement("x");
					x.appendChild(getDoc().createTextNode(i + ""));
					Element y = getDoc().createElement("y");
					y.appendChild(getDoc().createTextNode(j + ""));
					
					newWhitePiece.appendChild(x);
					newWhitePiece.appendChild(y);
					
					if(Game.getPlayer(1).getColour().equals("Black")){
						getPlayers(0).appendChild(newWhitePiece);
					}else{
						getPlayers(1).appendChild(newWhitePiece);
					}
				}else{}
			}
		}	
	}
	
	/**
	 * This method sets the players piece colours and appends this value
	 * to the appropriate player id tag.
	 */
	public void setColour(){
		Element playerOneColour = getDoc().createElement("colour");
		Element playerTwoColour = getDoc().createElement("colour");	
		
		if(Game.getPlayer(0).getColour() == "Black"){
			playerOneColour.appendChild(getDoc().createTextNode("Black"));
			playerTwoColour.appendChild(getDoc().createTextNode("White"));
		}else{
			playerOneColour.appendChild(getDoc().createTextNode("White"));
			playerTwoColour.appendChild(getDoc().createTextNode("Black"));
		}
		
		getPlayers(0).appendChild(playerOneColour);
		getPlayers(1).appendChild(playerTwoColour);
	}

	/**
	 * This method sets the players scores and append this value to the
	 * appropriate player id tag.
	 */
	public void setScore(){
		Element playerOneScore = getDoc().createElement("score");
		Element playerTwoScore = getDoc().createElement("score");
		
		playerOneScore.appendChild(getDoc().createTextNode(OthelloGame.getPlayer1Score() + ""));
		playerTwoScore.appendChild(getDoc().createTextNode(OthelloGame.getPlayer2Score() + ""));
		
		getPlayers(0).appendChild(playerOneScore);
		getPlayers(1).appendChild(playerTwoScore);
		
	}
	
	/** This is the main method containing the unit tests for this class. */
	public static void main(String[] args){
		
		final String TEST_PLAYER_1_NAME = "X";
		final String TEST_PLAYER_2_NAME = "Y";
		
		final String TEST_PLAYER_1_COLOUR = "Red";
		final String TEST_PLAYER_2_COLOUR = "Yellow";
		
		Player testPlayer1 = new HumanPlayer(TEST_PLAYER_1_NAME, TEST_PLAYER_1_COLOUR);
		Player testPlayer2 = new HumanPlayer(TEST_PLAYER_2_NAME, TEST_PLAYER_2_COLOUR);
	
		OthelloGame testGame = new OthelloGame(testPlayer1, testPlayer2);
		OthelloGameGUI.setPlayerOne(testPlayer1);
		OthelloGameGUI.setPlayerTwo(testPlayer2);
		
		OthelloGameSaver testSaving = new OthelloGameSaver("saves//othellosaves//testOthelloGameSaver.xml");
	
		if(getFile().exists()){
			System.out.println("Test passed.");
		}else{
			System.out.println("Test failed.");
		}
	}
}
