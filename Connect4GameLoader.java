import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Connect4GameLoader extends GameLoader{

	public Connect4GameLoader(String fileName) {
		super(fileName);
	}
	
	public boolean getGameType(){
		if(getM_gametype().equals("Connect4")){
			return true;
		}else{
			return false;
		}
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
		
		Player playerOne;
		Player playerTwo;
		
		Piece.ConnectFourPieceColour p1Colour;
		Piece.ConnectFourPieceColour p2Colour;
		
		if(getM_playerOneColour().equals("Red")){
			p1Colour = Piece.ConnectFourPieceColour.RED;
			p2Colour = Piece.ConnectFourPieceColour.YELLOW;
		}else{
			p1Colour = Piece.ConnectFourPieceColour.YELLOW;
			p2Colour = Piece.ConnectFourPieceColour.RED;
		}
		
		 if (getM_playerOneType().equals("Human")) {
	           playerOne = new HumanPlayer(getM_playerOneName(), p1Colour);
	     } else {
	    	 playerOne = new ConnectFourEasyComputerPlayer(
	            			getM_playerOneName(), p1Colour);
	        }
	        
	        if (getM_playerTwoType().equals("Human")) {
	            playerTwo = new HumanPlayer(getM_playerTwoName(), p2Colour);
	        } else {
	            playerTwo = new ConnectFourEasyComputerPlayer(
	            			getM_playerTwoName(), p2Colour);
	        }
	        
		ConnectFourGame loadGame = new ConnectFourGame(playerOne, playerTwo);
		
		if(p1Colour == Piece.ConnectFourPieceColour.RED){
			//Setting the red pieces
			for(int i = 0; i < getP1PiecesX().size(); i++){
				ConnectFourBoard.setRedPieces(getP1PiecesX().get(i), getP1PiecesY().get(i));
			}	
		
			//Setting the yellow pieces
			for(int i = 0; i < getP2PiecesX().size(); i++){
				ConnectFourBoard.setYellowPieces(getP2PiecesX().get(i), getP2PiecesY().get(i));
			}
		}else{
			//Setting the yellow pieces
			for(int i = 0; i < getP1PiecesX().size(); i++){
				ConnectFourBoard.setYellowPieces(getP1PiecesX().get(i), getP1PiecesY().get(i));
			}	
		
			//Setting the red pieces
			for(int i = 0; i < getP2PiecesX().size(); i++){
				ConnectFourBoard.setRedPieces(getP2PiecesX().get(i), getP2PiecesY().get(i));
			}
		}
		
		ConnectFourGameGUI.beginTimer(getM_time());
		ConnectFourGameGUI.resetPlayerLabel(getM_playerOneName(), getM_playerOneColour(), getM_playerTwoName(), getM_playerTwoColour());
		
		if(getM_playerTurn().equals("Player 1")){
			System.out.println("Wrong");
			ConnectFourGameGUI.getGame().setTurn(0);
			ConnectFourGameGUI.getPanel().setCurrentPiece(new ConnectFourPiece(p1Colour));
		}else{
			ConnectFourGameGUI.getGame().setTurn(1);
			ConnectFourGameGUI.getPanel().setCurrentPiece(new ConnectFourPiece(p2Colour));
		}
		
		ConnectFourGameGUI.setGame(loadGame);
		
		ConnectFourGameGUI.getPanel().updatePieces(loadGame.getPieces());
		ConnectFourGameGUI.getPanel().refreshDisplay();
		
		System.out.println("Player 1 Name" + getM_playerOneName());
	}
	
	
}
