/**
* @file GameGUI.java
* @author Callum Dicker
* @date 04 Feb 2014
* @see http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html,
*      For using swing in java.
*
* @brief This is the GameGUI superclass which contains all the methods that will 
*        be inherited from the subclasses ConnectFourGUI and OthelloGUI.
*		
* This class provides simple implementations of methods that can be inherited 
* and changed by the subclasses. These methods are displayMoveFeedback, 
* displayWinner, SetTitle, setHeight and setWidth. The other methods in this 
* class are all abstract methods that are implemented by the subclasses.
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public abstract class GameGUI extends GUI{

    private static Timer m_Timer;
    private static int m_Time;
    private static JLabel m_TimerLabel;
    private static JLabel m_PlayerLabel;
    
	/**
	* Constructor of this class, requires basic info such as Title of 
	* the frame, the width of the frame and the height and sets them
	* using methods in this class.
	*
	* @param title -This variable passes in the desired title of the frame.
	* @param width -This variable passes in the desired width of the frame.
	* @param height -This variable passes in the desired height of the frame.
	*/
	public GameGUI(String title, int width, int height) {
		super(title, width, height);
	}
	
	public static void setPlayerLabel(String p1Name, String p1Colour,
								String p2Name, String p2Colour){
		m_PlayerLabel = new JLabel("\nPlayer One: " + p1Name + "[" + p1Colour + "]" +
								 "\nPlayer Two: " + p2Name + "[" + p2Colour + "]");
	}
	
	public static void resetPlayerLabel(String p1Name, String p1Colour,
								String p2Name, String p2Colour){
		getPlayerLabel().setText("\nPlayer One: " + p1Name + "[" + p1Colour + "]" +
								 "\nPlayer Two: " + p2Name + "[" + p2Colour + "]");
	}
	
	public static JLabel getPlayerLabel(){
		return m_PlayerLabel;
	}
	
    public Timer getTimer(){
    	return m_Timer;
    }
    
    public static int getTime(){
    	return m_Time;
    }
    
    public static void setTime(int time){
    	m_Time = time;
    }
    
    public static void setTimerLabel(){
    	m_TimerLabel = new JLabel("Time elapsed: " + 
    String.format("%02d",(getTime() / 3600) % 60) + ":" + 
    String.format("%02d",(getTime() / 60) % 60) + ":" + 
    String.format("%02d",getTime() % 60));
    	//m_TimerLabel.setBorder(new EmptyBorder(0,0,0,getWidth() - 125));
    }
    
    public static JLabel getTimerLabel(){
    	return m_TimerLabel;
    }
    
    public void resetTimer(){
    	setTime(0);
    	getTimerLabel().setText("Time elapsed: " + 
    String.format("%02d",(getTime() / 3600) % 60) + ":" + 
    String.format("%02d",(getTime() / 60) % 60) + ":" + 
    String.format("%02d",getTime() % 60));
    	startTimer();
    }
    
    public static void beginTimer(int x){
    	setTime(x);
    	getTimerLabel().setText("Time elapsed: " + 
    String.format("%02d",(getTime() / 3600) % 60) + ":" + 
    String.format("%02d",(getTime() / 60) % 60) + ":" + 
    String.format("%02d",getTime() % 60));
    	startTimer();
    }
    
    public static void startTimer(){
    	ActionListener actListener = new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			setTime(getTime() + 1);
    			getTimerLabel().setText("Time elapsed: " + 
                String.format("%02d",(getTime() / 3600) % 60) + ":" + 
                String.format("%02d",(getTime() / 60) % 60) + ":" + 
                String.format("%02d",getTime() % 60));
    		}
    	};
    	m_Timer = new Timer(1000, actListener);
    	System.out.println("Timer started");
    	m_Timer.start();
    }

	/**
	* This will bring up a message box explaining the users move, for instance
	* if the move is an incorrect move, then a message box will appear.
	*
	* @param message -This is the message the message box will display
	*
	*/
	public boolean displayMoveFeedback(String message) {
	
		JOptionPane.showMessageDialog(this, message,"Your Move", 
			JOptionPane.PLAIN_MESSAGE);
        return true;
	}
	
	/**
	* This method will display a message box that shows which player is the 
	* winner of the game.
	*
	* @param winner - String containing winner's name
	*/
	public boolean displayWinner(String winner) {
	
		JOptionPane.showMessageDialog(this, "The Winner is " + winner,"Winner", 
			JOptionPane.PLAIN_MESSAGE);
        return true;
	}
}
