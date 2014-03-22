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
	}	
	
	public void setPlayerData(Element element){
		if(element.getAttribute("id").equals("1")){
			m_playerOneType = element.getElementsByTagName("type").item(0).getTextContent();
			m_playerOneName = element.getElementsByTagName("name").item(0).getTextContent();
			m_playerOneColour = element.getElementsByTagName("colour").item(0).getTextContent();
			//m_playerOnePieceCount = Integer.parseInt(element.getElementsByTagName("pieceCount").item(0).getTextContent());
		}else{
			m_playerTwoType = element.getElementsByTagName("type").item(0).getTextContent();
			m_playerTwoName = element.getElementsByTagName("name").item(0).getTextContent();
			m_playerTwoColour = element.getElementsByTagName("colour").item(0).getTextContent();
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
			p1PiecesX.add(Integer.parseInt(playerOnePiecesX.get(i)));
			p1PiecesY.add(Integer.parseInt(playerOnePiecesY.get(i)));
		}
		
		for(int i = 0; i < playerTwoPiecesX.size(); i++){
			p2PiecesX.add(Integer.parseInt(playerTwoPiecesX.get(i)));
			p2PiecesY.add(Integer.parseInt(playerTwoPiecesY.get(i)));
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
    	m_playerTurn = element.getElementsByTagName("turn").item(0).getTextContent();
    }
    
    public void drawToTerminal(){
    	System.out.println("Game type: " + m_gametype);
    	System.out.println("Game time: " + m_time);
    	System.out.println("Players turn: " + m_playerTurn);
    	
    	System.out.println("-------------------------------------");
    	
    	System.out.println("Player one name: " + m_playerOneName);
    	System.out.println("Player one type: " + m_playerOneType);
    	System.out.println("Player one colour: " + m_playerOneColour);
    	System.out.println("Player one pieces: ");
    	for(int i = 0; i < p1PiecesX.size(); i++){
    		System.out.print("\n" + p1PiecesX.get(i) + "," + p1PiecesY.get(i));
    	}
    	
    	System.out.println("\n------------------------------------");
    
    	System.out.println("Player two name: " + m_playerTwoName);
    	System.out.println("Player two type: " + m_playerTwoType);
    	System.out.println("Player two colour: " + m_playerTwoColour);
    	System.out.println("Player two pieces: ");
    	for(int i = 0; i < p2PiecesX.size(); i++){
    		System.out.print("\n" + p2PiecesX.get(i) + "," + p2PiecesY.get(i));
    	}
    	
    }
}
