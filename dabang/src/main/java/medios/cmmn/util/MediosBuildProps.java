package medios.cmmn.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Slf4j
public class MediosBuildProps {

	private static final Logger LOGGER = LoggerFactory.getLogger(MediosBuildProps.class);

	private static final String buildinfo = "properties/buildinfo.properties";

	private static final File buildInfoFile = new File(System.getProperty("user.home") + File.separator + ".mediosBuildProp");;

	private static final Map<String,Object> getBuildInfo(String contextPath) {
		Map<String,Object> mapData = new HashMap<String,Object>();
		
		try {
//			Enumeration<URL> urls = ClassLoader.getSystemClassLoader().getResources(buildinfo);
			Enumeration<URL> urls = MediosBuildProps.class.getClassLoader().getResources(buildinfo);

			String contextName = contextPath.replaceAll("/", "");
			contextName = contextName.trim().isEmpty()?"root":contextName;
			
			while(urls.hasMoreElements()) {
				Properties properties = getBuildInfo(urls.nextElement());

				if(!properties.isEmpty()) {
					String title = (String) properties.get("build.title");
					
					if(title != null && !title.toString().isEmpty()) {
						String name = title.replaceAll("medios\\.", "");
						if(!name.equals(contextName)) name = contextName + "." + name;
						
						Enumeration<?> keys = properties.propertyNames();
						while(keys.hasMoreElements()) {
							String key = (String) keys.nextElement();
							Object val = properties.get(key);
							String subName = key.replaceAll("build", "");
							mapData.put(name + subName, val);
						}
					}
				}
			}
		} catch(Exception e) {
			LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
		}
		
		return mapData;
	}
	
	private static final Properties getBuildInfo(URL url) {
		Properties	properties	= new Properties();
		Reader		reader		= null;

		try {
			reader = new InputStreamReader(url.openStream());
			properties.load(reader);
		} catch(Exception e) {
			LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
			
		} finally {
			if(reader != null) try { reader.close(); reader = null; } catch(Exception e) {}
		}

		return properties;
	}
	
	private static final Properties readBuildProp() {
		Properties	properties	= new Properties();
		
		FileReader fr = null;
		try {
			fr = new FileReader(buildInfoFile);
			properties.load(fr);
		} catch(Exception e) {
			LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
			
		} finally {
			if(fr != null) try { fr.close(); fr = null; } catch(Exception e) {}
		}
		
		return properties;
	}
	
	private static final void writeBuildProp(Properties properties) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(buildInfoFile);
			properties.store(fw, "MediosBuildProps");
		} catch(Exception e) {
			LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
			
		} finally {
			if(fw != null) try { fw.close(); fw = null; } catch(Exception e) {}
		}
	}
	
	public static void addBuildInfo(String contextPath) {
		Properties	properties	= readBuildProp();
		
		Map<String,Object> map = getBuildInfo(contextPath);
		
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			properties.setProperty(key, (String) map.get(key));
		}
		
		writeBuildProp(properties);
	}

	public static void removeBuildInfo(String contextPath) {
		Properties	properties	= readBuildProp();
		
		Map<String,Object> map = getBuildInfo(contextPath);
		
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			properties.remove(key);
		}
		
		writeBuildProp(properties);
		
	}
	
	public static final Properties readBuildInfo() {
		return readBuildProp();
	}
}
