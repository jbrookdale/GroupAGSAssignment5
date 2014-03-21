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
		
		Element redPiece = getDoc().createElement("piece");
		Element yellowPiece = getDoc().createElement("piece");
		
		//Player one is yellow, player 2 is red NEEDS CHANGING!
		getPlayers(0).appendChild(yellowPiece);
		getPlayers(1).appendChild(redPiece);
		
		for(int j = 0; j < piecesToBeSaved[0].length; j++){
			for(int i = 0; i < piecesToBeSaved.length; i++){
				if(piecesToBeSaved[i][j].getPieceColour() == Piece.ConnectFourPieceColour.RED){
					Element x = getDoc().createElement("x");
					x.appendChild(getDoc().createTextNode(i + ""));
					Element y = getDoc().createElement("y");
					y.appendChild(getDoc().createTextNode(j + ""));
					
					redPiece.appendChild(x);
					redPiece.appendChild(y);
				}else if(piecesToBeSaved[i][j].getPieceColour() == Piece.ConnectFourPieceColour.YELLOW){
					Element x = getDoc().createElement("x");
					x.appendChild(getDoc().createTextNode(i + ""));
					Element y = getDoc().createElement("y");
					y.appendChild(getDoc().createTextNode(j + ""));
					
					yellowPiece.appendChild(x);
					yellowPiece.appendChild(y);
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
