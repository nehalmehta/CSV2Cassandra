package CassandraUtilities;

import MiscUtilities.Configuration;
import MiscUtilities.Debugger;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.HConsistencyLevel;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;

public class CRUDUtilities {
	Cluster cluster;
	Keyspace keyspaceOperator;
	Debugger debugger;
	private static StringSerializer stringSerializer = StringSerializer.get();

	public CRUDUtilities(CassandraConnector connector, String keyspaceName,
			Configuration configuration) {
		this.cluster = connector.retrieveCluster();
		this.debugger = new Debugger("CassandraUtilities.CRUDUtilities");
		ConsistencyLevel consistencyLevel;
		if (configuration.consistencyLevel.equals("HConsistencyLevel.ONE")) {
			consistencyLevel = new ConsistencyLevel(HConsistencyLevel.ONE,
					HConsistencyLevel.ONE);
		} else if (configuration.consistencyLevel
				.equals("HConsistencyLevel.ANY")) {
			consistencyLevel = new ConsistencyLevel(HConsistencyLevel.ONE,
					HConsistencyLevel.ANY);
		} else if (configuration.consistencyLevel
				.equals("HConsistencyLevel.QUORUM")) {
			consistencyLevel = new ConsistencyLevel(HConsistencyLevel.ONE,
					HConsistencyLevel.QUORUM);
		} else if (configuration.consistencyLevel
				.equals("HConsistencyLevel.LOCAL_QUORUM")) {
			consistencyLevel = new ConsistencyLevel(HConsistencyLevel.ONE,
					HConsistencyLevel.LOCAL_QUORUM);
		} else if (configuration.consistencyLevel
				.equals("HConsistencyLevel.EACH_QUORUM")) {
			consistencyLevel = new ConsistencyLevel(HConsistencyLevel.ONE,
					HConsistencyLevel.EACH_QUORUM);
		} else if (configuration.consistencyLevel
				.equals("HConsistencyLevel.ALL")) {
			consistencyLevel = new ConsistencyLevel(HConsistencyLevel.ONE,
					HConsistencyLevel.ALL);
		} else {
			consistencyLevel = new ConsistencyLevel(HConsistencyLevel.ONE,
					HConsistencyLevel.ONE);
		}

		this.keyspaceOperator = HFactory.createKeyspace(keyspaceName, cluster,
				consistencyLevel);
	}

	public void insertValue(String columnFamilyName, String columnName,
			String key, String value) {
		Mutator<String> mutator = HFactory.createMutator(keyspaceOperator,
				stringSerializer);
		mutator.insert(key, columnFamilyName,
				HFactory.createStringColumn(columnName, value));
		debugger.log("Inserted Records for Column:" + columnName);
	}
}