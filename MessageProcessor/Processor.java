package MessageProcessor;

import java.util.List;

public interface Processor {
	
	public void process(List<Message> batch);

}
