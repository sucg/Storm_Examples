package storm.cookbook;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class HelloWorldSpout extends BaseRichSpout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int referenceRadom;
	private final static int MAX_RADOM = 10;
	private SpoutOutputCollector collector;
	
	public HelloWorldSpout() {
		final Random rand = new Random();
		referenceRadom = rand.nextInt(MAX_RADOM);
	}
	
	public void nextTuple() {
		Utils.sleep(100);
		final Random rand = new Random();
		int instanceRandon = rand.nextInt(MAX_RADOM);
		if (instanceRandon == referenceRadom) {
			collector.emit(new Values("Hello World"));
			System.out.println("++++++++++++++++++++emit Hello World");
		}else {
			collector.emit(new Values("OtherRandom Word"));
		}
	}

	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		this.collector = arg2;
	}

	public void declareOutputFields(OutputFieldsDeclarer declear) {
		declear.declare(new Fields("sentence"));
	}

}
