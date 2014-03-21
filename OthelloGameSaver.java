import org.w3c.dom.Element;


public class OthelloGameSaver extends GameSaver{

	public OthelloGameSaver(String fileName) {
		super(fileName);
	}
	
	public void setGameTypeElement(){
		Element gametype = getDoc().createElement("gametype");
		gametype.appendChild(getDoc().createTextNode("Othello"));
		getRootElement().appendChild(gametype);		
	}
	
	public void setPieces(){}
	
	public void setColour(){
		Element playerOneColour = getDoc().createElement("colour");
		Element playerTwoColour = getDoc().createElement("colour");	
		
		if(Game.getPlayer(0).getColour() == Piece.OthelloPieceColour.BLACK){
			playerOneColour.appendChild(getDoc().createTextNode("Black"));
			playerTwoColour.appendChild(getDoc().createTextNode("White"));
		}else{
			playerOneColour.appendChild(getDoc().createTextNode("White"));
			playerTwoColour.appendChild(getDoc().createTextNode("Black"));
		}
		
		getPlayers(0).appendChild(playerOneColour);
		getPlayers(1).appendChild(playerTwoColour);
	}

}
