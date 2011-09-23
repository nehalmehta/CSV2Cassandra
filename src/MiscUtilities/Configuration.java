package MiscUtilities;

import java.io.IOException;




import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

public class Configuration {

	public String hostName = "";
	public String port = "";
	public String consistencyLevel = "";
	public String columnFamily = "";
	public String keySpace = "";
	public String importFile;
	public String delimeter;

	public Configuration() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			Debugger debugger = new Debugger("MiscUtilities.Configuration");
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document dom = (Document) db.parse("config/config.xml");
	        		
	 		Element docEle = (Element) dom.getDocumentElement();
        
	 		NodeList nl = docEle
					.getElementsByTagName("hostName");
			this.hostName = nl.item(0).getFirstChild().getNodeValue();
			nl = docEle
					.getElementsByTagName("port");
			this.port = nl.item(0).getFirstChild().getNodeValue();
			nl = docEle
					.getElementsByTagName("consistencyLevel");
			this.consistencyLevel = nl.item(0).getFirstChild().getNodeValue();
			nl = docEle
					.getElementsByTagName("keySpace");
			this.keySpace = nl.item(0).getFirstChild().getNodeValue();
			nl = docEle
					.getElementsByTagName("columnFamily");
			this.columnFamily = nl.item(0).getFirstChild().getNodeValue();
			nl = docEle
					.getElementsByTagName("importFile");
			this.importFile = nl.item(0).getFirstChild().getNodeValue();
			nl = docEle
					.getElementsByTagName("delimeter");
			this.delimeter = nl.item(0).getFirstChild().getNodeValue();		
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
