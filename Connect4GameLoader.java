import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Connect4GameLoader extends GameLoader{

	public Connect4GameLoader(String fileName) {
		super(fileName);
	}
	
	public void setupBoard(){
		System.out.println("Setting up board");
		
		Player playerOne = new HumanPlayer(getM_playerOneName(), Piece.ConnectFourPieceColour.YELLOW);
		Player playerTwo = new ConnectFourEasyComputerPlayer(getM_playerTwoName(), Piece.ConnectFourPieceColour.RED);
		
		ConnectFourGame loadGame = new ConnectFourGame(playerOne, playerTwo);

		//Setting the red pieces
		for(int i = 0; i < getP1PiecesX().size(); i++){
			ConnectFourBoard.setRedPieces(getP1PiecesX().get(i), getP1PiecesY().get(i));
		}
		
		//Setting the yellow pieces
		for(int i = 0; i < getP2PiecesX().size(); i++){
			ConnectFourBoard.setYellowPieces(getP2PiecesX().get(i), getP2PiecesY().get(i));
		}
		
		ConnectFourGameGUI.getPanel().updatePieces(loadGame.getPieces());
		ConnectFourGameGUI.getPanel().refreshDisplay();
	}
	
	
}
