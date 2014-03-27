/**
* @file GameSaver.java
* @author Ieuan Skinner
* @date 26 March 14
* @see http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
* 
* @brief This class writes the 'shared' assets of both games to an xml file.
* 
* This class will write the data which is shared by both games to an xml file
* and initialise/create said file. Such data includes player names, player 
* types and game time.
* 
 */

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GameSaver extends GameIOHandler {
	private DocumentBuilderFactory m_docFactory;
	private DocumentBuilder m_docBuilder;
	
	private TransformerFactory m_transformerFactory;
	private Transformer m_transformer;
	private DOMSource m_source;
	
	private static Document m_doc;
	private static File m_file;
	private StreamResult m_result; 
	
	private static Element m_rootElement;
	
	private static Element m_playerOne;
	private static Element m_playerTwo;

	/**
	 * This is the constructor for the GameSaver class. It calls all the methods
	 * needed to save all the required game data. It also initialises and 
	 * closes/writes to the file.
	 * 
	 * @param fileName	fileName is the location the user wants to save the game
	 * 					data to.
	 */
	public GameSaver(String fileName){
		m_file = new File(fileName);
		m_docFactory = DocumentBuilderFactory.newInstance();
		try {
			m_docBuilder = m_docFactory.newDocumentBuilder();
			m_doc = m_docBuilder.newDocument();
			
			setRootElement();
			
			setGameTypeElement();
			setTimeElement();
			setPlayerTurnElement();
			
			initPlayerElement();
			setPlayerName();
			setPlayerType();
			setColour();
			setScore();
			setPieces();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeFile();
	}
	
	/**
	 * This method will return the player 1 or 2 element dependent on the value
	 * of the parameter.
	 * 
	 * @param 		int x		Integer value corresponding to which player 
	 * 							you'd like. 0 = playerOne, Other = playerTwo.
	 * @return	player element
	 */
	public static Element getPlayers(int x){
		if(x == 0){
			return m_playerOne;
		}else{
			return m_playerTwo;
		}
	}
	
	/** This method will set the players score. Used only for Othello. */
	public void setScore(){}
	
	/** This method will set the "game type" element. (Connect 4 or Othello).*/
	public void setGameTypeElement(){}
	
	/** 
	 * This method will set the pieces that are on the board. Implemented 
	 * differently for both Connect 4 and Othello. 
	 */
	public void setPieces(){}
	
	/** This method will set the players chosen piecen colour.*/
	public void setColour(){}

	/** This method will set the players name. */
	public void setPlayerName(){}
	
	/**
	 * This method is an access method that will return the root element.
	 * 	
	 * @return root element
	 */
	public static Element getRootElement() {
		return m_rootElement;
	}
	
	/**
	 * This method will create the root element and append it to the document.
	 */
	public static void setRootElement(){
		m_rootElement = m_doc.createElement("game");
		m_doc.appendChild(m_rootElement);
	}
	
	/**
	 * This method is an access method that will return the document which
	 * is what you're writing the data to.
	 * 
	 * @return document
	 */
	public static Document getDoc(){
		return m_doc;
	}	
	
	/**
	 * This method will set the player 1 and 2 type (Human, 
	 * Computer: Easy or Computer: Hard) and append it to 
	 * their corresponding player id tag. 
	 */
	public void setPlayerType(){
		String p1Type = Game.getPlayer(0).getPlayerType();
		String p2Type = Game.getPlayer(1).getPlayerType();
		
		Element playerOneType = m_doc.createElement("type");
		Element playerTwoType = m_doc.createElement("type");
		
		if(p1Type.equals("ComputerEasy")){
			playerOneType.appendChild(m_doc.createTextNode("Computer: Easy"));
		}else if(p1Type.equals("Human")){
			playerOneType.appendChild(m_doc.createTextNode(p1Type));
		}else{
			playerOneType.appendChild(m_doc.createTextNode("Computer: Hard"));
		}
		
		if(p2Type.equals("ComputerEasy")){
			playerTwoType.appendChild(m_doc.createTextNode("Computer: Easy"));
		}else if(p2Type.equals("Human")){
			playerTwoType.appendChild(m_doc.createTextNode(p2Type));
		}else{
			playerTwoType.appendChild(m_doc.createTextNode("Computer: Hard"));
		}
		
		m_playerOne.appendChild(playerOneType);
		m_playerTwo.appendChild(playerTwoType);
	}
	
	/**
	 * This method will set the time value at the point of saving and append 
	 * it to the root element.
	 */
	public static void setTimeElement(){
		String gameTime = GameGUI.getTime() + "";
		Element time = m_doc.createElement("time");
		time.appendChild(m_doc.createTextNode(gameTime));
		m_rootElement.appendChild(time);
	}
	
	/**
	 * This method will set which players turn it is at the time of saving 
	 * and append it to the root element.
	 */
	public static void setPlayerTurnElement(){
		String turn;
		if(Game.getPlayerTurn() % 2 == 0){
			turn = "Player 1";
		}else{
			turn = "Player 2";
		}
	
		Element playerTurn = m_doc.createElement("turn");
		playerTurn.appendChild(m_doc.createTextNode(turn));
		m_rootElement.appendChild(playerTurn);
	}
	
	/**
	 * This access method returns the file being saved to.
	 * 
	 * @return m_file	The file being saved to.
	 */
	public static File getFile(){
		return m_file;
	}
	
	/**
	 * This method will initialise the player elements, set the "id" attribute
	 * and append the these to the root element.
	 */
	public static void initPlayerElement(){
		m_playerOne = m_doc.createElement("player");
		m_playerTwo = m_doc.createElement("player");

		m_rootElement.appendChild(m_playerOne);
		m_rootElement.appendChild(m_playerTwo);
		
		m_playerOne.setAttribute("id", "1");
		m_playerTwo.setAttribute("id", "2");
	}
	
	/**
	 * This method will write all the game data to the file.
	 */
	public void writeFile(){
		m_transformerFactory = TransformerFactory.newInstance();
		try {
			m_transformer = m_transformerFactory.newTransformer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		m_source = new DOMSource(m_doc);
		m_result = new StreamResult(m_file);
		
		try {
			m_transformer.transform(m_source, m_result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("File saved!");
	}
	
	/** This is the main method containing the unit tests for this class. */
	public static void main(String[] args){
		
		
		final String TEST_PLAYER_1_NAME = "X";
		final String TEST_PLAYER_2_NAME = "Y";
		
		final String TEST_PLAYER_1_COLOUR = "Red";
		final String TEST_PLAYER_2_COLOUR = "Yellow";
		
		Player testPlayer1 = new HumanPlayer(TEST_PLAYER_1_NAME, TEST_PLAYER_1_COLOUR);
		Player testPlayer2 = new HumanPlayer(TEST_PLAYER_2_NAME, TEST_PLAYER_2_COLOUR);
		
		Game testGame = new ConnectFourGame(testPlayer1, testPlayer2);
		
		GameSaver testSaving = new GameSaver("saves//testGameSaver.xml");
		
		if(getFile().exists()){
			System.out.println("Test passed.");
		}else{
			System.out.println("Test failed.");
		}
	}
}
