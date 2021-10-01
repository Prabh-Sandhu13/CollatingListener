package MessageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;

public class MsgListenerImpl implements MsgListener{
	
	private PriorityQueue<Message> buffer;
		
	// index of last message of previous batch
	private int lastBatchIdx;
	
	Processor processor = new ProcessorImpl();
	
    public MsgListenerImpl() {
		
		// buffer will store messages in sorted order based on sequence number
		this.buffer = new PriorityQueue<Message>((a,b) -> 
		Integer.compare(a.getSeqNum(), b.getSeqNum() ));
		
		this.lastBatchIdx = -1;
	}

	// function called when new message is received
	@Override
	public void msgBuffer(Message msg) {
		buffer.add(msg);
		
	}

	@Override
	public void shipValidBatch() {
		List<Message> batch = new ArrayList<>();

		// message are added to list if indices are in sequence with 
		// no gaps and current batch index is next to previous index
		while(!buffer.isEmpty() && buffer.peek().getSeqNum() == lastBatchIdx + 1) {
			batch.add(buffer.poll());
			lastBatchIdx++;
		}
		
		// call process method for the valid batch
		if(batch.size() > 0) {
			processor.process(batch);
		}
		
	}

	@Override
	public void invokeProcessor(int processingInterval) {
		
		// timer is set to call shipValidBatch after given interval and
		// processing time can be configured accordingly
		new Timer().scheduleAtFixedRate(new TimerTask(){
		    public void run(){
		    	shipValidBatch();
		    }
		},0,processingInterval);
		
	}

}
