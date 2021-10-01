package MessageProcessor;

public interface MsgListener {
	
	public void msgBuffer(Message msg);
	
	public void shipValidBatch();
	
	public void invokeProcessor(int processingInterval);

}
