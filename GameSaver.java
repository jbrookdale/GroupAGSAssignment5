
public class GameSaver extends GameIOHandler {
	
	private String m_Filename;
	
	public GameSaver(String fileName){
		setFileName(fileName);
		setPlayerName();
	}
	
	public void setFileName(String fileName){
		m_Filename = fileName;
	}
	
	public String getFileName(){
		return m_Filename;
	}
	
	public static void setPlayerName(){
		String playerOneName = Game.getPlayerName(-1);
		String playerTwoName = Game.getPlayerName(2);
	}
}
