package CassandraUtilities;

import me.prettyprint.cassandra.service.OperationType;
import me.prettyprint.hector.api.ConsistencyLevelPolicy;
import me.prettyprint.hector.api.HConsistencyLevel;

public final class ConsistencyLevel implements ConsistencyLevelPolicy {
	HConsistencyLevel readConsistencyLevel;
	HConsistencyLevel writeConsistencyLevel;

	public ConsistencyLevel(HConsistencyLevel rConsistencyLevel,
			HConsistencyLevel wConsistencyLevel) {
		readConsistencyLevel = rConsistencyLevel;
		writeConsistencyLevel = wConsistencyLevel;
	}

	@Override
	public HConsistencyLevel get(OperationType op) {
		// TODO Auto-generated method stub
		switch (op) {
		case READ:
			return readConsistencyLevel;
		case WRITE:
			return writeConsistencyLevel;
		default:
			return HConsistencyLevel.QUORUM;
		}
	}

	@Override
	public HConsistencyLevel get(OperationType op, String columnFamily) {
		// TODO Auto-generated method stub
		switch (op) {
		case READ:
			return readConsistencyLevel;
		case WRITE:
			return writeConsistencyLevel;
		default:
			return HConsistencyLevel.QUORUM;
		}
	}
}
