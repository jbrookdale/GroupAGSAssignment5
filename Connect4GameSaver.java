import org.w3c.dom.Element;

public class Connect4GameSaver extends GameSaver{
	public Connect4GameSaver(String fileName) {
		super(fileName);
	}

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
	
	public void setGameTypeElement(){
		Element gametype = getDoc().createElement("gametype");
		gametype.appendChild(getDoc().createTextNode("Connect4"));
		getRootElement().appendChild(gametype);		
	}
	
	public void setPieces(){
		ConnectFourPiece[][] piecesToBeSaved = ConnectFourBoard.getPieces(); 
		
		for(int j = 0; j < piecesToBeSaved[0].length; j++){
			for(int i = 0; i < piecesToBeSaved.length; i++){
				if(piecesToBeSaved[i][j].getPieceColour() == Piece.ConnectFourPieceColour.RED){
					
					Element newRedPiece = getDoc().createElement("piece2");
					
					Element x = getDoc().createElement("x");
					x.appendChild(getDoc().createTextNode(i + ""));
					Element y = getDoc().createElement("y");
					y.appendChild(getDoc().createTextNode(j + ""));
					
					newRedPiece.appendChild(x);
					newRedPiece.appendChild(y);
					
					// NEEDS TO BE CHANGED WHEN COLOUR CHANGING IS IMPLEMENTED!
					getPlayers(1).appendChild(newRedPiece);
				}else if(piecesToBeSaved[i][j].getPieceColour() == Piece.ConnectFourPieceColour.YELLOW){
					Element newYellowPiece = getDoc().createElement("piece1");
					Element x = getDoc().createElement("x");
					x.appendChild(getDoc().createTextNode(i + ""));
					Element y = getDoc().createElement("y");
					y.appendChild(getDoc().createTextNode(j + ""));
					
					newYellowPiece.appendChild(x);
					newYellowPiece.appendChild(y);

					// NEEDS TO BE CHANGED WHEN COLOUR CHANGING IS IMPLEMENTED!
					getPlayers(0).appendChild(newYellowPiece);
				}else{}
			}
		}	
	}
	
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
}
