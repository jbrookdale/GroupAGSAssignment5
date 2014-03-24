public class OthelloGameLoader extends GameLoader{

	public OthelloGameLoader(String fileName) {
		super(fileName);
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
		for(int i = 0; i < getP1PiecesX().size(); i++){
			OthelloGameGUI.setPlayerPieces(getP1PiecesX().get(i), getP1PiecesY().get(i), getM_playerOneColour());
		}
		
		for(int i = 0; i < getP2PiecesX().size(); i++){
			OthelloGameGUI.setPlayerPieces(getP2PiecesX().get(i), getP2PiecesY().get(i), getM_playerTwoColour());
		}
	}
}
