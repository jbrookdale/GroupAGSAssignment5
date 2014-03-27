/**
* @file GameLoader.java
* @author Ieuan Skinner
* @date 26 March 14
* @see http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
* 
* @brief This class loads the 'shared' assets for both games.
* 
* This class will read the data from the selected xml file and load/set 
* this data to a bunch of private variables to be used by the specific 
* subclasses to actually initialise and setup the loaded game. It also 
* features a number of access methods to get at this data.
* 
 */

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
	
	private String m_playerTwoName;
	private String m_playerTwoType;
	private String m_playerTwoColour;
	
	private ArrayList<String> playerOnePiecesX = new ArrayList<String>();
	private ArrayList<String> playerOnePiecesY = new ArrayList<String>();

	private ArrayList<String> playerTwoPiecesX = new ArrayList<String>();
	private ArrayList<String> playerTwoPiecesY = new ArrayList<String>();
	
	private ArrayList<Integer> p1PiecesX = new ArrayList<Integer>();
	private ArrayList<Integer> p1PiecesY = new ArrayList<Integer>();
	
	private ArrayList<Integer> p2PiecesX = new ArrayList<Integer>();
	private ArrayList<Integer> p2PiecesY = new ArrayList<Integer>();
	
	/**
	 * This is the constructor for the GameLoader class which parses the 
	 * selected file and initialises the methods to read in the specific 
	 * data and set it to the member variables.
	 * 
	 * @param fileName	fileName is the location of the file that the user
	 * 					wants to load.
	 */
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
			
			if(getGameType()){
				
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
			}else{
				System.out.println("Wrong game type.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	

		if(getGameType()){
			convertToIntegers();
			drawToTerminal();
			setupBoard();
		}else{
			System.out.println("Wrong game type.");
		}
	}	
	
	/** 
	 * This method will return true or false if the game type of the file 
	 * selected matched the game you're trying to load. i.e. if you're trying
	 * to load a Connect 4 save file in a Connect 4 game then return true. Else
	 * return false.
	 * 
	 * @return boolean 	Returns true or false depending on the conditions as 
	 *					aforementioned.
	 */
	public boolean getGameType(){
		return true;
	}

	/** 
	 *	This method will read and set the players name, type and chosen piece 
	 *	colour to the private member variables storing this data.  
	 *
	 *	@param element	Element is the node these tags are appended to. 
	 *					(The reference material can explain this better).
	 */
	public void setPlayerData(Element element){
		if(element.getAttribute("id").equals("1")){
			setM_playerOneType(element.getElementsByTagName("type").item(0).getTextContent());
			setM_playerOneName(element.getElementsByTagName("name").item(0).getTextContent());
			setM_playerOneColour(element.getElementsByTagName("colour").item(0).getTextContent());
		}else{
			setM_playerTwoType(element.getElementsByTagName("type").item(0).getTextContent());
			setM_playerTwoName(element.getElementsByTagName("name").item(0).getTextContent());
			setM_playerTwoColour(element.getElementsByTagName("colour").item(0).getTextContent());
		}
	}
	
	/**
	 * This method will read in and set the the x and y values of the pieces 
	 * the players had set at the time of saving to the ArrayList member 
	 * variables.
	 * 
	 * @param element	Element corresponds to the node this data is appended
	 * 					to.
	 */
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
	
	/**
	 * This method will read in and set the time to the member variable. 
	 * 
	 * @param Element	Element is the node the time tag is appended to.
	 */
    public void setTime(Element element){
    	String time = element.getElementsByTagName("time").item(0).getTextContent();
    	
    	m_time = Integer.parseInt(time);
    }
  
    /**
     * This method will read in and set the game type to a member variable.
     * 
     * @param element	Element is the node the gametype tag is appended to.
     */
    public void setGametype(Element element){
    	setM_gametype(element.getElementsByTagName("gametype").item(0).getTextContent());
    }
    
    /**
     * This method will read in and set the turn to which players turn it was 
     * at the time of saving.
     * 
     * @param element	Element is the node the turn tag is appended to.
     */
    public void setPlayerTurn(Element element){
    	setM_playerTurn(element.getElementsByTagName("turn").item(0).getTextContent());
    }
    
    /** 
     * This access method will return the member variable corresponding to an 
     * array of player ones pieces 'x' (column) location.
     */
	public ArrayList<Integer> getP1PiecesX() {
		return p1PiecesX;
	}

	/** 
     * This access method will set the 'x' (column) values of player ones 
     * pieces.
     */
	public void setP1PiecesX(ArrayList<Integer> p1PiecesX) {
		this.p1PiecesX = p1PiecesX;
	}
	
    /** 
     * This access method will return the member variable corresponding to an 
     * array of player ones pieces 'y' (row) location.
     */
	public ArrayList<Integer> getP1PiecesY() {
		return p1PiecesY;
	}
	
	/** 
     * This access method will set the 'y' (row) values of player ones 
     * pieces.
     */
	public void setP1PiecesY(ArrayList<Integer> p1PiecesY) {
		this.p1PiecesY = p1PiecesY;
	}

    /** 
     * This access method will return the member variable corresponding to an 
     * array of player twos pieces 'x' (column) location.
     */
	public ArrayList<Integer> getP2PiecesX() {
		return p2PiecesX;
	}

	/** 
     * This access method will set the 'x' (column) values of player ones 
     * pieces.
     */
	public void setP2PiecesX(ArrayList<Integer> p2PiecesX) {
		this.p2PiecesX = p2PiecesX;
	}

    /** 
     * This access method will return the member variable corresponding to an 
     * array of player twos pieces 'y' (row) location.
     */
	public ArrayList<Integer> getP2PiecesY() {
		return p2PiecesY;
	}

	/** 
     * This access method will set the 'y' (row) values of player twos 
     * pieces.
     */
	public void setP2PiecesY(ArrayList<Integer> p2PiecesY) {
		this.p2PiecesY = p2PiecesY;
	}

	/** This access method will return player ones name. */
	public String getM_playerOneName() {
		return m_playerOneName;
	}
	
	/** This access method will set player ones name. */
	public void setM_playerOneName(String m_playerOneName) {
		this.m_playerOneName = m_playerOneName;
	}

	/** This access method will return player ones type. */
	public String getM_playerOneType() {
		return m_playerOneType;
	}

	/** This access method will set player ones type. */
	public void setM_playerOneType(String m_playerOneType) {
		this.m_playerOneType = m_playerOneType;
	}

	/** This access method will return player ones colour. */
	public String getM_playerOneColour() {
		return m_playerOneColour;
	}

	/** This access method will set player ones colour. */
	public void setM_playerOneColour(String m_playerOneColour) {
		this.m_playerOneColour = m_playerOneColour;
	}

	/** This access method will return player twos name. */
	public String getM_playerTwoName() {
		return m_playerTwoName;
	}

	/** This access method will set player twos name. */
	public void setM_playerTwoName(String m_playerTwoName) {
		this.m_playerTwoName = m_playerTwoName;
	}

	/** This access method will return player twos type. */
	public String getM_playerTwoType() {
		return m_playerTwoType;
	}

	/** This access method will set player twos type. */
	public void setM_playerTwoType(String m_playerTwoType) {
		this.m_playerTwoType = m_playerTwoType;
	}

	/** This access method will return player twos colour. */
	public String getM_playerTwoColour() {
		return m_playerTwoColour;
	}

	/** This access method will set player twos colour. */
	public void setM_playerTwoColour(String m_playerTwoColour) {
		this.m_playerTwoColour = m_playerTwoColour;
	}

	/** This access method will return the time. */
	public int getM_time() {
		return m_time;
	}

	/** This access method will set the time. */
	public void setM_time(int m_time) {
		this.m_time = m_time;
	}

	/** This access method will return the players turn. */
	public String getM_playerTurn() {
		return m_playerTurn;
	}

	/** This access method will set the players turn. */
	public void setM_playerTurn(String m_playerTurn) {
		this.m_playerTurn = m_playerTurn;
	}

	/** This access method will return the game type. */
	public String getM_gametype() {
		return m_gametype;
	}

	/** This access method will set the game type. */
	public void setM_gametype(String m_gametype) {
		this.m_gametype = m_gametype;
	}
    

	/** This method is used by the subclasses to set up/load the game. */
	public void setupBoard() {}
    
	/** 
	 * This method will convert the values in the String ArrayLists to 
	 * Integers and set them into a corresponding Integer ArrayList.
	 */
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
	
	/**
	 * This method will write all this loaded date to terminal in a nice 
	 * readable format which helps immensely with error checking.
	 */
    public void drawToTerminal(){
    	System.out.println("Game type: " + getM_gametype());
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
    	
    	System.out.println("\n-------------------------------------");
    
    	System.out.println("Player two name: " + getM_playerTwoName());
    	System.out.println("Player two type: " + getM_playerTwoType());
    	System.out.println("Player two colour: " + getM_playerTwoColour());
    	System.out.println("Player two pieces: ");
    	for(int i = 0; i < getP2PiecesX().size(); i++){
    		System.out.print("\n" + getP2PiecesX().get(i) + "," + getP2PiecesY().get(i));
    	}
    }

	/** This is the main method containing the unit tests for this class. */
    public static void main(String[] args){
    	
    	GameLoader testOthelloLoading = new GameLoader("saves\\othellosaves\\testOthello.xml");
    	GameLoader testConnectFourLoading = new GameLoader("saves\\connect4saves\\testConnect4.xml");
    	
    	//GameLoader testOthelloLoading = new OthelloGameLoader("saves\\othellosaves\\testOthello.xml");
    	//GameLoader testConnectFourLoading = new ConnectFourGameLoader("saves\\connect4saves\\testConnect4.xml");
    }
    
}
