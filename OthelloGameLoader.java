/**
* @file OthelloGameLoader.java
* @author Ieuan Skinner
* @date 26 March 14
* @see http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
* 
* @brief This class reads in game data specific to Othello.
*
* 
* This class will read the xml file at the location passed to it and 
* set the data as expected. This class specifically deals with things 
* that are specific to Othello such as player scores.
 */

import org.w3c.dom.Element;

public class OthelloGameLoader extends GameLoader{

	private int m_playerOneScore;
	private int m_playerTwoScore;
	
	/** Symbolic constant that stores the width of the othello board */
	private final int BOARD_SIZE = 8;
	
	/**
	 * Constructor that passes in the filename of the xml file 
	 * @param fileName - String that stores filepath of othello game save
	 */
	
	public OthelloGameLoader(String fileName) {
		super(fileName);
	}
	
	/**
	 * Get method that returns whether game is othello or connect 4
	 * @return true/false - true is othello is chosen
	 */
	public boolean getGameType(){
		if(getM_gametype().equals("Othello")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Set method that reads the xml file's details about the player
	 * @param Element - passes in elements from xml file
	 */
	public void setPlayerData(Element element){
		if(element.getAttribute("id").equals("1")){
			setM_playerOneType(element.getElementsByTagName("type").item(0).getTextContent());
			setM_playerOneName(element.getElementsByTagName("name").item(0).getTextContent());
			setM_playerOneColour(element.getElementsByTagName("colour").item(0).getTextContent());
			setM_playerOneScore(element.getElementsByTagName("score").item(0).getTextContent());
			//m_playerOnePieceCount = Integer.parseInt(element.getElementsByTagName("pieceCount").item(0).getTextContent());
		}else{
			setM_playerTwoType(element.getElementsByTagName("type").item(0).getTextContent());
			setM_playerTwoName(element.getElementsByTagName("name").item(0).getTextContent());
			setM_playerTwoColour(element.getElementsByTagName("colour").item(0).getTextContent());
			setM_playerTwoScore(element.getElementsByTagName("score").item(0).getTextContent());
			//m_playerOnePieceCount = Integer.parseInt(element.getElementsByTagName("pieceCount").item(0).getTextContent());
		}
	}
	
	/**
	 * Set method that sets player 2's score
	 * @param score - string storing player 2's score
	 */
	private void setM_playerTwoScore(String score) {
		m_playerTwoScore = Integer.parseInt(score);
	}
	
	/**
	 * Set method that sets player 1's score
	 * @param score - string storing player 1's score
	 */
	private void setM_playerOneScore(String score) {
		m_playerOneScore = Integer.parseInt(score);
	}
	
	/**
	 * Method that sets the board according to the data stored in the xml file
	 */
	public void setupBoard(){
		Player player1;
		Player player2;
		
		if(getM_playerOneType().equals("Human")){
			player1 = new HumanPlayer(getM_playerOneName(), getM_playerOneColour());
		}else if(getM_playerOneType().equals("Computer: Easy")){
			player1 = new OthelloEasyComputerPlayer(getM_playerOneName(), getM_playerOneColour());
		}else{
			player1 = new OthelloHardComputerPlayer(getM_playerOneName(), getM_playerOneColour());
		}
		
		if(getM_playerTwoType().equals("Human")){
			player2 = new HumanPlayer(getM_playerTwoName(), getM_playerTwoColour());
		}else if(getM_playerTwoType().equals("Computer: Easy")){
			player2 = new OthelloEasyComputerPlayer(getM_playerTwoName(), getM_playerTwoColour());
			System.out.println("Correct");
		}else{
			player2 = new OthelloHardComputerPlayer(getM_playerTwoName(), getM_playerTwoColour());
		}
		
		OthelloGame loadGame = new OthelloGame(player1, player2);

		for (int i = 0; i < BOARD_SIZE; i++){
			for (int j = 0; j < BOARD_SIZE; j++){
				OthelloGameGUI.setPieces(i, j, "Blank");
			}
		}
		
		OthelloBoard.clearPieces();
		OthelloBoard.clearPieces2();
		
		//testClearing();
		
		for(int k = 0; k < getP1PiecesX().size(); k++){
			OthelloGameGUI.setPieces(getP1PiecesX().get(k), getP1PiecesY().get(k), getM_playerOneColour());
			if(getM_playerOneColour().equals("Black")){
				OthelloBoard.setLoadedPieces(getP1PiecesX().get(k), getP1PiecesY().get(k), new OthelloPiece(Piece.OthelloPieceColour.BLACK));
			}else{
				OthelloBoard.setLoadedPieces(getP1PiecesX().get(k), getP1PiecesY().get(k), new OthelloPiece(Piece.OthelloPieceColour.WHITE));
			}
		}	
		
		for(int k2 = 0; k2 < getP2PiecesX().size(); k2++){
			OthelloGameGUI.setPieces(getP2PiecesX().get(k2), getP2PiecesY().get(k2), getM_playerTwoColour());
			if(getM_playerOneColour().equals("Black")){
				OthelloBoard.setLoadedPieces(getP2PiecesX().get(k2), getP2PiecesY().get(k2), new OthelloPiece(Piece.OthelloPieceColour.WHITE));
			}else{
				OthelloBoard.setLoadedPieces(getP2PiecesX().get(k2), getP2PiecesY().get(k2), new OthelloPiece(Piece.OthelloPieceColour.BLACK));
			}
		}	
		
		//testPieceSetup();
		
		if(getM_playerTurn().equals("Player 1")){
			OthelloGameGUI.getGame().setTurn(0);
		}else{
			OthelloGameGUI.getGame().setTurn(1);
		}
		
		OthelloGameGUI.setTime(getM_time());
		OthelloGameGUI.resetPlayerLabel(getM_playerOneName(), getM_playerOneColour(), 
				getM_playerTwoName(), getM_playerTwoColour());
		
		loadGame.setPlayer1Score(m_playerOneScore - loadGame.getPlayer1Score());
		loadGame.setPlayer2Score(m_playerTwoScore - loadGame.getPlayer2Score());

		OthelloGameGUI.setGame(loadGame);
		OthelloGameGUI.setPlayerOne(player1);
		OthelloGameGUI.setPlayerTwo(player2);
	}
	
	/**
	 * Test method that loops through othello board to check whether
	 * all pieces have been cleared. will print "right" if all spaces
	 * are empty
	 */
	public void testClearing(){
		OthelloPiece[][] test = OthelloBoard.getPieces();
		
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
				if(test[i][j].getPieceColour().equals(Piece.OthelloPieceColour.NONE)){
					System.out.println("Right");
					System.out.println(i + " " + j);
				}else{
					System.out.println("Wrong");
					System.out.println(i + " " + j);
				}					
			}
		}
	}
	
	/**
	 * Test method that checks whether all the pieces that have been set
	 * are in their correct places. Will print "right" if the piece count is
	 * equal to actual number of pieces.
	 */
	public void testPieceSetup(){
		OthelloPiece[][] test = OthelloBoard.getPieces();
	
		for(int k = 0; k < getP1PiecesX().size(); k++){
			if(getM_playerOneColour().equals("Black")){
				if(test[getP1PiecesX().get(k)][getP1PiecesY().get(k)].getPieceColour().equals(Piece.OthelloPieceColour.BLACK)){
					System.out.println("Right");
					System.out.println(getP1PiecesX().get(k) + " " + getP1PiecesY().get(k));
				}
			}else{
				if(test[getP1PiecesX().get(k)][getP1PiecesY().get(k)].getPieceColour().equals(Piece.OthelloPieceColour.WHITE)){
					System.out.println("Right");
					System.out.println(getP1PiecesX().get(k) + " " + getP1PiecesY().get(k));
				}
			}
		}
		System.out.println("PieceCount: " + getP1PiecesX().size());	
		
		for(int k2 = 0; k2 < getP2PiecesX().size(); k2++){
			if(getM_playerTwoColour().equals("Black")){
				if(test[getP2PiecesX().get(k2)][getP2PiecesY().get(k2)].getPieceColour().equals(Piece.OthelloPieceColour.BLACK)){
					System.out.println("Right");
					System.out.println(getP2PiecesX().get(k2) + " " + getP2PiecesY().get(k2));
				}
			}else{
				if(test[getP2PiecesX().get(k2)][getP2PiecesY().get(k2)].getPieceColour().equals(Piece.OthelloPieceColour.WHITE)){
					System.out.println("Right");
					System.out.println(getP2PiecesX().get(k2) + " " + getP2PiecesY().get(k2));
				}
			}
		}
		System.out.println("PieceCount2: " + getP2PiecesX().size());
	}
	
	/** This is the main method containing the unit tests for this class. */
	public static void main(String[] args) {
		Player testPlayer1 = new HumanPlayer("X", "Black");
    	Player testPlayer2 = new HumanPlayer("Y", "Black");
    	
    	OthelloGameGUI testOthello = new OthelloGameGUI("Othello", 8, 8, testPlayer1, testPlayer2);
		testOthello.creatingGui();
		OthelloGameLoader test = new OthelloGameLoader("saves\\othellosaves\\testOthello.xml");
	}
	
}
