/**
* @file Connect4GameSaver.java
* @author Ieuan Skinner
* @date 26 March 14
* @see http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
* 
* @brief This class writes game data specific to Connect4 to an xml file.
* 
* This class will write the data specific to Connect4 to an xml file
* specified by the player. They have the option to name the save file but
* not choose it's location i.e. it defaults to "saves/connect4saves ... ". 
* Specific data includes its gametype, piece colour and piece locations as 
* their boards do not index spaces the same.
 */

import org.w3c.dom.Element;

public class ConnectFourGameSaver extends GameSaver{
	
	/**
	 * This is the constructor for the Connect4GameSaver class.
	 * 
	 * @param fileName	fileName is used to locate/refer to the file 
	 * 					the user wants to save to. If fileName doesn't
	 * 					already exist a new instance of it will be 
	 * 					created.
	 */ 
	public ConnectFourGameSaver(String fileName) {
		super(fileName);
	}

	
	/**
	 * This method will write the players names to the xml file.
	 * Appending them to the appropriate player id tag.
	 */
	public void setPlayerName(){
		String playerOneName = Game.getPlayerName(-1);
		String playerTwoName = Game.getPlayerName(2);
		Element p1Name = getDoc().createElement("name");
		Element p2Name = getDoc().createElement("name");
		
		p1Name.appendChild(getDoc().createTextNode(playerOneName));
		p2Name.appendChild(getDoc().createTextNode(playerTwoName));
	
		getPlayers(0).appendChild(p1Name);
		getPlayers(1).appendChild(p2Name);
	}
	
	/**
	 * This method will set the game type element in the xml file
	 * to Connect4. This value is then used to test the file wanted
	 * to load is of the correct game. (i.e. so you can't load an 
	 * Othello game on to a Connect4 game).
	 * 
	 */
	public void setGameTypeElement(){
		Element gametype = getDoc().createElement("gametype");
		gametype.appendChild(getDoc().createTextNode("Connect4"));
		getRootElement().appendChild(gametype);		
	}
	
	/**
	 * This method sets all the players pieces x and y value and appends
	 * them to the piece1 (player 1) or piece2 (player 2) tag. Easiest way
	 * to do this for loading.
	 */
	public void setPieces(){
		ConnectFourPiece[][] piecesToBeSaved = ConnectFourBoard.getPieces(); 
		
		for(int j = 0; j < piecesToBeSaved[0].length; j++){
			for(int i = 0; i < piecesToBeSaved.length; i++){
				if(piecesToBeSaved[i][j].getPieceColour() == Piece.ConnectFourPieceColour.RED){
					Element newRedPiece;
					
					if(Game.getPlayer(0).getColour() == Piece.ConnectFourPieceColour.RED){
						newRedPiece = getDoc().createElement("piece1");
					}else{	
						newRedPiece = getDoc().createElement("piece2");
					}
					
					Element x = getDoc().createElement("x");
					x.appendChild(getDoc().createTextNode(i + ""));
					Element y = getDoc().createElement("y");
					y.appendChild(getDoc().createTextNode(j + ""));
					
					newRedPiece.appendChild(x);
					newRedPiece.appendChild(y);
					
					if(Game.getPlayer(0).getColour() == Piece.ConnectFourPieceColour.RED){
						getPlayers(0).appendChild(newRedPiece);
					}else{	
						getPlayers(1).appendChild(newRedPiece);
					}
				}else if(piecesToBeSaved[i][j].getPieceColour() == 
														Piece.ConnectFourPieceColour.YELLOW){
					Element newYellowPiece;
					
					if(Game.getPlayer(1).getColour() == Piece.ConnectFourPieceColour.RED){
						newYellowPiece = getDoc().createElement("piece1");
					}else{	
						newYellowPiece = getDoc().createElement("piece2");
					}
					
					
					Element x = getDoc().createElement("x");
					x.appendChild(getDoc().createTextNode(i + ""));
					Element y = getDoc().createElement("y");
					y.appendChild(getDoc().createTextNode(j + ""));
					
					newYellowPiece.appendChild(x);
					newYellowPiece.appendChild(y);

					if(Game.getPlayer(1).getColour() == Piece.ConnectFourPieceColour.RED){
						getPlayers(0).appendChild(newYellowPiece);
					}else{	
						getPlayers(1).appendChild(newYellowPiece);
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
		
		if(Game.getPlayer(0).getColour() == Piece.ConnectFourPieceColour.RED){
			playerOneColour.appendChild(getDoc().createTextNode("Red"));
			playerTwoColour.appendChild(getDoc().createTextNode("Yellow"));
		}else{
			playerOneColour.appendChild(getDoc().createTextNode("Yellow"));
			playerTwoColour.appendChild(getDoc().createTextNode("Red"));
		}
		
		getPlayers(0).appendChild(playerOneColour);
		getPlayers(1).appendChild(playerTwoColour);
	}

	/** This is the main method containing the unit tests for this class. */
	public static void main(String[] args){Player testPlayer1 = new HumanPlayer("X", "Red");
	Player testPlayer2 = new HumanPlayer("Y", "Yellow");
	
	Game testGame = new ConnectFourGame(testPlayer1, testPlayer2);
	
	GameSaver testSaving = new GameSaver("saves//testGameSaver.xml");
	
	if(getFile().exists()){
		System.out.println("Test passed.");
	}else{
		System.out.println("Test failed.");
	}
	}
}
