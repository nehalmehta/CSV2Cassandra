package CassandraUtilities;

import MiscUtilities.Configuration;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.factory.HFactory;

public class CassandraConnector {

	Cluster cluster;
	String hostName, port;

	public CassandraConnector(Configuration configuration) {
		this.hostName = configuration.hostName;
		this.port = configuration.port;
		this.cluster = HFactory.getOrCreateCluster(configuration.clusterName,
				this.hostName + ":" + this.port);
	}

	public Cluster retrieveCluster() {
		return this.cluster;
	}
}
