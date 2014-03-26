import org.w3c.dom.Element;

public class OthelloGameLoader extends GameLoader{

	private int m_playerOneScore;
	private int m_playerTwoScore;
	private int START_SCORE = 2;
	
	public OthelloGameLoader(String fileName) {
		super(fileName);
	}
	
	public boolean getGameType(){
		if(getM_gametype().equals("Othello")){
			return true;
		}else{
			return false;
		}
	}
	
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
	
	private void setM_playerTwoScore(String score) {
		m_playerTwoScore = Integer.parseInt(score);
	}

	private void setM_playerOneScore(String score) {
		m_playerOneScore = Integer.parseInt(score);
	}

	/**
	 * Things still needed:
	 * 		-- Score updating and saving even.
	 * 		-- Player colour and type identification. (?)
	 * 		-- Player turn saving and loading.
	 * 
	 * 		The way I'm setting pieces is basically bullshit.
	 */
	
	public void setupBoard(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
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
		
		//testPieces();
		
		OthelloGame.setPlayer1Score(m_playerOneScore - START_SCORE);
		OthelloGame.setPlayer2Score(m_playerTwoScore - START_SCORE);
	}
	
	public static void testClearing(){
		OthelloPiece[][] test = OthelloBoard.getPieces();
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
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
}
