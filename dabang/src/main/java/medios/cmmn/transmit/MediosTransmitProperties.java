package medios.cmmn.transmit;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MediosTransmitProperties extends Properties {

	private static final long serialVersionUID = 491897843895643737L;

	private static final Logger LOGGER = LoggerFactory.getLogger(MediosTransmitProperties.class);

	private final String configFileName = "transmit.properties";
	private final String defaultConfigPath = System.getProperty("catalina.base") + File.separator + "conf" + File.separator + configFileName;
	private String classBasePath = "properties" + File.separator + configFileName;
	
	private static SSLSocketFactory sslSocketFactory = null;
	
	private String	protocol	= "http";
	private String	hostName	= "127.0.0.1";
	private Integer	hostPort	= 80;
	private String	contextName	= "/medios";

	private static MediosTransmitProperties properties;
	private static MediosTransmitProperties getInstance() {
		if(properties == null) properties = new MediosTransmitProperties();
		return properties;
	}

	private MediosTransmitProperties() {
		super();

		Reader		reader		= null;
		try {

			classBasePath = this.getClass().getClassLoader().getResource("").getPath() + classBasePath;
			
			if(new File(defaultConfigPath).exists()) {
				reader = new FileReader(defaultConfigPath);
			} else if(new File(classBasePath).exists()) {
				reader = new FileReader(classBasePath);
			}
			
			super.load(reader);

			if(get("transmit.protocol"   ) != null) protocol		= (String) get("transmit.protocol");
			if(get("transmit.hostname"   ) != null) hostName		= (String) get("transmit.hostname");
			if(get("transmit.hostport"   ) != null) hostPort		= Integer.valueOf((String) get("transmit.hostport"));
			if(get("transmit.contextname") != null) contextName	= (String) get("transmit.contextname");
			
		} catch(Exception e) {
				LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
		} finally {
			if(reader != null) try { reader.close(); reader = null; } catch(Exception e) {}
		}
	}

	public static URL getTransmitURL(HttpServletRequest request) throws MalformedURLException {
		String uri = null;
		if(request != null) {
			uri = request.getServletPath();
			String queryString = request.getQueryString()!=null?request.getQueryString():"";
			uri += !queryString.isEmpty()?("?" + request.getQueryString()):"";
		}
		
		return getTransmitURL(uri);
	}
	
	public static String getTransmitServerInfo() {
		return getInstance().protocol + "://" + getInstance().hostName + ":" + getInstance().hostPort;
	}
	
	public static URL getTransmitURL() throws MalformedURLException {
		return getTransmitURL("");
	}
	
	public static URL getTransmitURL(String uri) throws MalformedURLException {
		checkSSL();
		
		if(uri != null && !uri.trim().isEmpty())
			return new URL(getInstance().protocol, getInstance().hostName, getInstance().hostPort, getInstance().contextName + uri);
		else
			return new URL(getInstance().protocol, getInstance().hostName, getInstance().hostPort, getInstance().contextName);
	}

	private static void checkSSL() {
		if(sslSocketFactory == null && "https".toLowerCase().equals(getInstance().protocol)) {
			try {
				TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() { return null; }
					public void checkClientTrusted(X509Certificate[] certs, String authType) { }
					public void checkServerTrusted(X509Certificate[] certs, String authType) { }
				}};
				
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new SecureRandom());
				
				sslSocketFactory = sc.getSocketFactory();
				
				HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
			
			} catch(Exception e) {
				LOGGER.error("TrustManager setting error" + " : {}", e.getCause(), e);
			}
		}
	}
	
	@Override
	public synchronized void load(Reader reader) throws IOException {}


	@Override
	public synchronized void load(InputStream inStream) throws IOException {}


	@Override
	public void save(OutputStream out, String comments) {}


	@Override
	public void store(Writer writer, String comments) throws IOException {}


	@Override
	public void store(OutputStream out, String comments) throws IOException {}


	@Override
	public synchronized void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException {}


	@Override
	public void storeToXML(OutputStream os, String comment) throws IOException {}


	@Override
	public void storeToXML(OutputStream os, String comment, String encoding) throws IOException {}
}
