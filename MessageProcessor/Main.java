package MessageProcessor;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static void main(String[] args) {
		MsgListener listener = new MsgListenerImpl();
		int processingInterval = 10000;
		
		Message m0 = new Message();
		Message m1 = new Message();
		Message m2 = new Message();
		Message m3 = new Message();
		Message m4 = new Message();
		Message m5 = new Message();
		
		
		m0.setSeqNum(0);
		m1.setSeqNum(1);
		m2.setSeqNum(2);
		m3.setSeqNum(3);
		m4.setSeqNum(4);
		m5.setSeqNum(5);
		
		
		listener.msgBuffer(m0);
		listener.msgBuffer(m1);
		listener.msgBuffer(m5);
		listener.msgBuffer(m4);
		
		listener.invokeProcessor(processingInterval);
		
		// scheduled to add few messages for later time
		new Timer().schedule(new TimerTask(){
		    public void run(){
		    	listener.msgBuffer(m2);
		    }
		},0,50000);
		
		new Timer().schedule(new TimerTask(){
		    public void run(){
		    	listener.msgBuffer(m3);
		    }
		},0,100000);

	}

}
