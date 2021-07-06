package medios.cmmn.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

//@Slf4j
public class ResponseWriterUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseWriterUtil.class);

	public static void write(ModelAndViewContainer mavContainer, NativeWebRequest webRequest, String sendJson) {
		OutputStream os = null;
		try  {
			mavContainer.setRequestHandled(true);
			HttpServletResponse response = (HttpServletResponse)webRequest.getNativeResponse(HttpServletResponse.class);
			
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_OK);
			os = response.getOutputStream();
			byte[] bytes = sendJson.getBytes("UTF-8");
			os.write(bytes,0,bytes.length);
			os.flush();
			os.close();		
		} catch (IOException e) {
			LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
		} finally {
			if(os != null) try { os.close(); } catch (IOException e) { }
		}
	}
}
