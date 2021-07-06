package medios.cmmn.servlet.view;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.InternalResourceView;

public class MediosInternalResourceView extends InternalResourceView {

	private static final Logger LOGGER = LoggerFactory.getLogger(MediosInternalResourceView.class);

	private String ctx;
	
	public MediosInternalResourceView(String forwardContextUrl) {
		super(forwardContextUrl.substring(forwardContextUrl.indexOf(":") + 1));
		if(forwardContextUrl != null && forwardContextUrl.indexOf(":") > 0) {
			ctx = forwardContextUrl.substring(0, forwardContextUrl.indexOf(":"));
		}
	}
	
	@Override
	protected RequestDispatcher getRequestDispatcher(HttpServletRequest request, String path) {
		if(ctx != null && !ctx.trim().isEmpty()) {
			return request.getServletContext().getContext(ctx).getRequestDispatcher(path);
		}
		return request.getRequestDispatcher(path);
	}

	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

//		boolean isSetRemoteAddr = false;

		if(LOGGER.isDebugEnabled()) {
//			if(MDC.get(CmmnConstants.REMOTE_ADDR) == null) {
//				isSetRemoteAddr = true;
//				String remoteAddr = GeneralUtils.changeLocalAddr(GeneralUtils.getIpAddress(request));
//				MDC.put(CmmnConstants.REMOTE_ADDR, remoteAddr);
//				MDC.put(CmmnConstants.CONTEXT_PATH, request.getContextPath().isEmpty()?"/":request.getContextPath());
//			}
		}

		super.renderMergedOutputModel( model, request, response);

//		if(isSetRemoteAddr) {
//			MDC.remove(CmmnConstants.REMOTE_ADDR);
//			MDC.remove(CmmnConstants.CONTEXT_PATH);
//		}
	}
}
