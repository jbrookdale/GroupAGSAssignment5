/**
* @file GameSaver.java
* @author Ieuan Skinner
* @date 26 March 14
* @see http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
* 
* @brief This class writes the shared attributes of both games to an xml file.
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
	private File m_file;
	private StreamResult m_result; 
	
	private static Element m_rootElement;
	
	private static Element m_playerOne;
	private static Element m_playerTwo;

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
			setPlayerPieceCount(); //Unused as of right now?
			setPieces();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeFile();
	}
	
	public void setScore(){}
	
	public void setGameTypeElement(){}
	
	public void setPieces(){}
	
	public void setColour(){}
	
	public void setPlayerPieceCount(){}
	
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
	
	public void setPieceCount(){}
	
	public static void setTimeElement(){
		String gameTime = GameGUI.getTime() + "";
		Element time = m_doc.createElement("time");
		time.appendChild(m_doc.createTextNode(gameTime));
		m_rootElement.appendChild(time);
	}
	
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
	
	public static void initPlayerElement(){
		m_playerOne = m_doc.createElement("player");
		m_playerTwo = m_doc.createElement("player");

		m_rootElement.appendChild(m_playerOne);
		m_rootElement.appendChild(m_playerTwo);
		
		m_playerOne.setAttribute("id", "1");
		m_playerTwo.setAttribute("id", "2");
	}
	
	public static void setRootElement(){
		m_rootElement = m_doc.createElement("game");
		m_doc.appendChild(m_rootElement);
	}
	
	public void setPlayerName(){}
	
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
	
	public static Element getPlayers(int x){
		if(x == 0){
			return m_playerOne;
		}else{
			return m_playerTwo;
		}
	}

	public static Element getRootElement() {
		return m_rootElement;
	}
	
	public static Document getDoc(){
		return m_doc;
	}
}
