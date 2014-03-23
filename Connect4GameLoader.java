import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Connect4GameLoader extends GameLoader{

	public Connect4GameLoader(String fileName) {
		super(fileName);
	}
	
	/**
		THINGS THAT ARE STILL NEEDED TO BE DONE:
			-- Player turn needs to be taken into account.
			   Okay so player turn now works but the colour of the piece doesn't change
			   so it's pretty useless and just flips the players.
			-- Timer is incrementing too fast.
			-- Needs to handle loading an already won??
			-- Needs to take into account both players colour and type,
			   as of right now it's assumed player one is human and yellow 
			   whilst player two is an easy computer player and red.
	*/
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
		
		
		System.out.println(getM_time());
		ConnectFourGameGUI.beginTimer(getM_time());
		ConnectFourGameGUI.resetPlayerLabel(getM_playerOneName(), getM_playerOneColour(), getM_playerTwoName(), getM_playerTwoColour());
		
		if(getM_playerTurn().equals("Player 1")){
			ConnectFourGameGUI.getGame().setTurn(0);
		}else{
			ConnectFourGameGUI.getGame().setTurn(1);
		}
		
		ConnectFourGameGUI.getPanel().updatePieces(loadGame.getPieces());
		ConnectFourGameGUI.getPanel().refreshDisplay();
	}
	
	
}
