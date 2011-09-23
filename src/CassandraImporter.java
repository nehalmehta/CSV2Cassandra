import CSVUtilities.CSVParser;

import MiscUtilities.Configuration;

public class CassandraImporter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Configuration configuration = new Configuration();
		CSVParser csvParser = new CSVParser(configuration.importFile,
				configuration);
	}

}
