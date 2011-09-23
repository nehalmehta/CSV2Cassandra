package MiscUtilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Debugger {
    private static Logger logger;
	public Debugger(String className) {
		// TODO Auto-generated constructor stub
		FileHandler handler;
		boolean append = true;
		try {
			handler = new FileHandler("logs/CassandraImporter.log", append);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			handler.setFormatter(simpleFormatter);
			logger = Logger.getLogger(className);
		    logger.addHandler(handler);
		    
   
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void log(String message){
		logger.log(Level.INFO, message);
	}
	
}
