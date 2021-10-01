package MessageProcessor;

import java.util.List;

public class ProcessorImpl implements Processor {

	@Override
	public void process(List<Message> batch) {
		// TODO: processing logic goes here
		for(Message m: batch) {
			System.out.println(m.getSeqNum() +" message processed");
		}

	}

}
