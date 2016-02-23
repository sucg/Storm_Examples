package storm.cookbook;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.AuthorizationException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

public class HelloWorldTopology {

	public static void main(String[] args) {
		TopologyBuilder builder = new TopologyBuilder();

		builder.setSpout("randomHelloWorld", new HelloWorldSpout(), 10);
		builder.setBolt("HelloWorldBolt", new HelloWorldBolt(), 3).shuffleGrouping("randomHelloWorld");
		Config conf = new Config();
		conf.setDebug(true);

		try {
			
			if (args != null && args.length > 0) {
				conf.setNumWorkers(1);
				StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
			} else {
				LocalCluster cluster = new LocalCluster();
				cluster.submitTopology("test", conf, builder.createTopology());
				Utils.sleep(10000);
				cluster.killTopology("test");
				cluster.shutdown();
			}
			
		} catch (AlreadyAliveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTopologyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthorizationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
