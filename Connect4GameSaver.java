import org.w3c.dom.Element;

public class Connect4GameSaver extends GameSaver{
	public Connect4GameSaver(String fileName) {
		super(fileName);
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
				System.out.println("Width:" + piecesToBeSaved.length);
				System.out.println("Height:" + piecesToBeSaved[0].length);
				if(piecesToBeSaved[i][j].getPieceColour() == ConnectFourBoard.getRedPiece()){
					Element x = getDoc().createElement("x");
					x.appendChild(getDoc().createTextNode(i + ""));
					Element y = getDoc().createElement("y");
					y.appendChild(getDoc().createTextNode(j + ""));
					
					redPiece.appendChild(x);
					redPiece.appendChild(y);
				}else if(piecesToBeSaved[i][j].getPieceColour() == ConnectFourBoard.getYellowPiece()){
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
}
