
public class GameSaver extends GameIOHandler {
	
	private String m_Filename;
	
	public GameSaver(String fileName){
		setFileName(fileName);
		
	}
	
	public void setFileName(String fileName){
		m_Filename = fileName;
	}
	
	public String getFileName(){
		return m_Filename;
	}
}
