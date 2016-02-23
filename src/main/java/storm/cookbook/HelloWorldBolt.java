package storm.cookbook;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class HelloWorldBolt extends BaseRichBolt {

	private int myCount = 0;
	private Tuple input;
	public void execute(Tuple arg0) {
		String test = input.getStringByField("sentence");
		if ("Hello World".equals(test)) {
			myCount++;
			System.out.println("Find a Hello World! My Count is now:" + myCount);
		}
	}

	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {

	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub

	}

}
