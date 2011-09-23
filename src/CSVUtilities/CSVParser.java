package CSVUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import CassandraUtilities.CRUDUtilities;
import CassandraUtilities.CassandraConnector;
import MiscUtilities.Configuration;
import MiscUtilities.Debugger;

public class CSVParser {
	FileReader fileReader;
	BufferedReader bufferedReader;
	String lineContent;

	public CSVParser(String fileName, Configuration configuration) {
		try {

			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			Debugger debugger = new Debugger("CSVUtilites.CSVParser");
			debugger.log("Start");
			try {
				boolean isHeader = true;
				String[] header = null;
				while ((lineContent = bufferedReader.readLine()) != null) {
					if (isHeader) {
						header = lineContent.split(configuration.delimeter);
						isHeader = false;
					} else {
						String[] columns = lineContent.split(configuration.delimeter);

						CassandraConnector cassandraConnector = new CassandraConnector(
								configuration.hostName, configuration.port);

						CRUDUtilities crudUtilities = new CRUDUtilities(
								cassandraConnector, configuration.keySpace, configuration);
						debugger.log("Inserting Records for Key:" + columns[0]);
						for (int countColumns = 1; countColumns < columns.length; countColumns++) {
							crudUtilities.insertValue(configuration.columnFamily,
									header[countColumns], columns[0],
									columns[countColumns]);
						}
						debugger.log("Inserted Records for Key:" + columns[0]);

					}
				}
				debugger.log("Completed");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
