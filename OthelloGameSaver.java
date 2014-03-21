import org.w3c.dom.Element;


public class OthelloGameSaver extends GameSaver{

	public OthelloGameSaver(String fileName) {
		super(fileName);
	}
	
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
	
	public void setGameTypeElement(){
		Element gametype = getDoc().createElement("gametype");
		gametype.appendChild(getDoc().createTextNode("Othello"));
		getRootElement().appendChild(gametype);		
	}
	
	public void setPieces(){
		OthelloPiece[][] piecesToBeSaved = OthelloBoard.getPieces(); 
		
		Element blackPiece = getDoc().createElement("piece");
		Element whitePiece = getDoc().createElement("piece");
		
		//Player one is white, player 2 is black NEEDS CHANGING!
		getPlayers(0).appendChild(whitePiece);
		getPlayers(1).appendChild(blackPiece);
		
		for(int j = 0; j < piecesToBeSaved[0].length; j++){
			for(int i = 0; i < piecesToBeSaved.length; i++){
				if(piecesToBeSaved[i][j].getPieceColour() == Piece.OthelloPieceColour.BLACK){
					Element x = getDoc().createElement("x");
					x.appendChild(getDoc().createTextNode(i + ""));
					Element y = getDoc().createElement("y");
					y.appendChild(getDoc().createTextNode(j + ""));
					
					blackPiece.appendChild(x);
					blackPiece.appendChild(y);
				}else if(piecesToBeSaved[i][j].getPieceColour() == Piece.OthelloPieceColour.WHITE){
					Element x = getDoc().createElement("x");
					x.appendChild(getDoc().createTextNode(i + ""));
					Element y = getDoc().createElement("y");
					y.appendChild(getDoc().createTextNode(j + ""));
					
					whitePiece.appendChild(x);
					whitePiece.appendChild(y);
				}else{}
			}
		}	
	}
	
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
