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
}
