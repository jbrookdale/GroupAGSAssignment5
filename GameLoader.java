import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;

public class GameLoader extends GameIOHandler{
	NodeList m_game;
	NodeList m_player;
	NodeList m_p1Piece;
	NodeList m_p2Piece;
	
	File m_fXmlFile;
	
	Document m_doc;
	
	private String m_gametype;
	private int m_time;
	private String m_playerTurn;
	
	private String m_playerOneName;
	private String m_playerOneType;
	private String m_playerOneColour;
	private int m_playerOnePieceCount; //Not yet implemented
	
	private String m_playerTwoName;
	private String m_playerTwoType;
	private String m_playerTwoColour;
	private int m_playerTwoPieceCount; //Not yet implemented
	
	private ArrayList<String> playerOnePiecesX = new ArrayList<String>();
	private ArrayList<String> playerOnePiecesY = new ArrayList<String>();

	private ArrayList<String> playerTwoPiecesX = new ArrayList<String>();
	private ArrayList<String> playerTwoPiecesY = new ArrayList<String>();
	
	private ArrayList<Integer> p1PiecesX = new ArrayList<Integer>();
	private ArrayList<Integer> p1PiecesY = new ArrayList<Integer>();
	
	private ArrayList<Integer> p2PiecesX = new ArrayList<Integer>();
	private ArrayList<Integer> p2PiecesY = new ArrayList<Integer>();
	
	public GameLoader(String fileName){
    	try {
			m_fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			m_doc = dBuilder.parse(m_fXmlFile);
			m_doc.getDocumentElement().normalize();
	
			m_game = m_doc.getElementsByTagName("game");
			m_player= m_doc.getElementsByTagName("player");
			m_p1Piece = m_doc.getElementsByTagName("piece1");
			m_p2Piece = m_doc.getElementsByTagName("piece2");
		
			Node game = m_game.item(0);
			
			Element element = (Element) game;

			setGametype(element);
			setTime(element);
			setPlayerTurn(element);
			
			for (int i = 0; i < m_player.getLength(); i++) {
				
				Node nNode = m_player.item(i);
	
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					setPlayerData(eElement);
					setPlayerPieces(eElement);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	

		convertToIntegers();
		
		drawToTerminal();
		
		setupBoard();
		System.out.println("Passed setup board");
	}	
	
	public void setupBoard() {}

	public void setPlayerData(Element element){
		if(element.getAttribute("id").equals("1")){
			setM_playerOneType(element.getElementsByTagName("type").item(0).getTextContent());
			setM_playerOneName(element.getElementsByTagName("name").item(0).getTextContent());
			setM_playerOneColour(element.getElementsByTagName("colour").item(0).getTextContent());
			//m_playerOnePieceCount = Integer.parseInt(element.getElementsByTagName("pieceCount").item(0).getTextContent());
		}else{
			setM_playerTwoType(element.getElementsByTagName("type").item(0).getTextContent());
			setM_playerTwoName(element.getElementsByTagName("name").item(0).getTextContent());
			setM_playerTwoColour(element.getElementsByTagName("colour").item(0).getTextContent());
			//m_playerOnePieceCount = Integer.parseInt(element.getElementsByTagName("pieceCount").item(0).getTextContent());
		}
	}
	
	public void setPlayerPieces(Element element){
		NodeList tempNodeList;
		boolean isPlayerOne = false;
		if(element.getAttribute("id").equals("1")){
			tempNodeList = m_p1Piece;
			isPlayerOne = true;
		}else{
			tempNodeList = m_p2Piece;
		}
		for(int i = 0; i < tempNodeList.getLength(); i++){
			Node nNode2 = tempNodeList.item(i);

			Element eElement2 = (Element) nNode2;	

			if(isPlayerOne){
				playerOnePiecesX.add(eElement2.getElementsByTagName("x").item(0).getTextContent());
				playerOnePiecesY.add(eElement2.getElementsByTagName("y").item(0).getTextContent());
			}else{
				playerTwoPiecesX.add(eElement2.getElementsByTagName("x").item(0).getTextContent());
				playerTwoPiecesY.add(eElement2.getElementsByTagName("y").item(0).getTextContent());
			}
		}	
	}
	
	public void convertToIntegers(){
		for(int i = 0; i < playerOnePiecesX.size(); i++){
			getP1PiecesX().add(Integer.parseInt(playerOnePiecesX.get(i)));
			getP1PiecesY().add(Integer.parseInt(playerOnePiecesY.get(i)));
		}
		
		for(int i = 0; i < playerTwoPiecesX.size(); i++){
			getP2PiecesX().add(Integer.parseInt(playerTwoPiecesX.get(i)));
			getP2PiecesY().add(Integer.parseInt(playerTwoPiecesY.get(i)));
		}
	}
	
    public void setTime(Element element){
    	String time = element.getElementsByTagName("time").item(0).getTextContent();
    	
    	m_time = Integer.parseInt(time);
    }
  
    public void setGametype(Element element){
    	m_gametype = element.getElementsByTagName("gametype").item(0).getTextContent();
    }
    
    public void setPlayerTurn(Element element){
    	setM_playerTurn(element.getElementsByTagName("turn").item(0).getTextContent());
    }
    
    public void drawToTerminal(){
    	System.out.println("Game type: " + m_gametype);
    	System.out.println("Game time: " + getM_time());
    	System.out.println("Players turn: " + getM_playerTurn());
    	
    	System.out.println("-------------------------------------");
    	
    	System.out.println("Player one name: " + getM_playerOneName());
    	System.out.println("Player one type: " + getM_playerOneType());
    	System.out.println("Player one colour: " + getM_playerOneColour());
    	System.out.println("Player one pieces: ");
    	for(int i = 0; i < getP1PiecesX().size(); i++){
    		System.out.print("\n" + getP1PiecesX().get(i) + "," + getP1PiecesY().get(i));
    	}
    	
    	System.out.println("\n------------------------------------");
    
    	System.out.println("Player two name: " + getM_playerTwoName());
    	System.out.println("Player two type: " + getM_playerTwoType());
    	System.out.println("Player two colour: " + getM_playerTwoColour());
    	System.out.println("Player two pieces: ");
    	for(int i = 0; i < getP2PiecesX().size(); i++){
    		System.out.print("\n" + getP2PiecesX().get(i) + "," + getP2PiecesY().get(i));
    	}
    	
    }

	public ArrayList<Integer> getP1PiecesX() {
		return p1PiecesX;
	}

	public void setP1PiecesX(ArrayList<Integer> p1PiecesX) {
		this.p1PiecesX = p1PiecesX;
	}

	public ArrayList<Integer> getP1PiecesY() {
		return p1PiecesY;
	}

	public void setP1PiecesY(ArrayList<Integer> p1PiecesY) {
		this.p1PiecesY = p1PiecesY;
	}

	public ArrayList<Integer> getP2PiecesX() {
		return p2PiecesX;
	}

	public void setP2PiecesX(ArrayList<Integer> p2PiecesX) {
		this.p2PiecesX = p2PiecesX;
	}

	public ArrayList<Integer> getP2PiecesY() {
		return p2PiecesY;
	}

	public void setP2PiecesY(ArrayList<Integer> p2PiecesY) {
		this.p2PiecesY = p2PiecesY;
	}

	public String getM_playerOneName() {
		return m_playerOneName;
	}

	public void setM_playerOneName(String m_playerOneName) {
		this.m_playerOneName = m_playerOneName;
	}

	public String getM_playerOneType() {
		return m_playerOneType;
	}

	public void setM_playerOneType(String m_playerOneType) {
		this.m_playerOneType = m_playerOneType;
	}

	public String getM_playerOneColour() {
		return m_playerOneColour;
	}

	public void setM_playerOneColour(String m_playerOneColour) {
		this.m_playerOneColour = m_playerOneColour;
	}

	public String getM_playerTwoName() {
		return m_playerTwoName;
	}

	public void setM_playerTwoName(String m_playerTwoName) {
		this.m_playerTwoName = m_playerTwoName;
	}

	public String getM_playerTwoType() {
		return m_playerTwoType;
	}

	public void setM_playerTwoType(String m_playerTwoType) {
		this.m_playerTwoType = m_playerTwoType;
	}

	public String getM_playerTwoColour() {
		return m_playerTwoColour;
	}

	public void setM_playerTwoColour(String m_playerTwoColour) {
		this.m_playerTwoColour = m_playerTwoColour;
	}

	public int getM_time() {
		return m_time;
	}

	public void setM_time(int m_time) {
		this.m_time = m_time;
	}

	public String getM_playerTurn() {
		return m_playerTurn;
	}

	public void setM_playerTurn(String m_playerTurn) {
		this.m_playerTurn = m_playerTurn;
	}
}
