package storm.cookbook;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class HelloWorldBolt extends BaseRichBolt {

	private int myCount = 0;

	public void execute(Tuple input) {
		String test = input.getStringByField("sentence");
		if ("Hello World".equals(test)) {
			myCount++;
			System.out.println("////////////////////////////" + this.hashCode() + "Find a Hello World! My Count is now:" + myCount);
		}
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub

	}

}
