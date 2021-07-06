package medios.cmmn.util;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceCloseHelper {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceCloseHelper.class);

	public static void close(Closeable  ... resources) {
		for (Closeable resource : resources) {
			if (resource != null) {
				try {
					resource.close();
				} catch (IOException ignore) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
					LOGGER.warn("Occurred IOException to close resource is ingored!!");
				} catch (Exception ignore) {
					LOGGER.warn("Occurred Exception to close resource is ingored!!");
				}
			}
		}
	}

}
