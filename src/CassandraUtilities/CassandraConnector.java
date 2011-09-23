package CassandraUtilities;

import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.factory.HFactory;

public class CassandraConnector {

	Cluster cluster;
	String hostName, port;

	public CassandraConnector(String hostName, String port) {
		this.hostName = hostName;
		this.port = port;
		this.cluster = HFactory.getOrCreateCluster("Test Cluster",
				this.hostName + ":" + this.port);
	}

	public Cluster retrieveCluster() {
		return this.cluster;
	}
}
