package medios.cmmn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

/**
 * Dispatcher 관리
 * @author 의료원연합회-박정호
 * @since 2019. 10. 19.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *   ----------- -------------    ----------------------
 *   2019. 10. 19.  의료원연합회-박정호           최초 생성
 * </pre>
 */
public class MediosDispatcherServlet extends DispatcherServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(MediosDispatcherServlet.class);

	private static final long serialVersionUID = 342481675143947842L;

	/**
	 * MediosHttpSessionApplicationInitializer애서 사용하는 생성자
	 * 
	 * @param xmlWebApplicationContext
	 */
	public MediosDispatcherServlet(XmlWebApplicationContext xmlWebApplicationContext) {
		super(xmlWebApplicationContext);
	}

	private boolean chkStartDebugEnabled(HttpServletRequest request) {
		boolean isSetRemoteAddr = false;
		
		if(LOGGER.isDebugEnabled()) {
//			if(MDC.get(CmmnConstants.REMOTE_ADDR) == null) {
//				isSetRemoteAddr = true;
//				String remoteAddr = GeneralUtils.changeLocalAddr(GeneralUtils.getIpAddress(request));
//				MDC.put(CmmnConstants.REMOTE_ADDR, remoteAddr);
//				MDC.put(CmmnConstants.CONTEXT_PATH, request.getContextPath().isEmpty()?"/":request.getContextPath());
//			}
		}
		
		return isSetRemoteAddr;
	}

	private void chkEndDebugEnabled(boolean isSetRemoteAddr) {
//		if(isSetRemoteAddr) {
//			MDC.remove(CmmnConstants.REMOTE_ADDR);
//			MDC.remove(CmmnConstants.CONTEXT_PATH);
//		}
	}
	
	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean isSetRemoteAddr = chkStartDebugEnabled(request);
		super.doService(request, response);
		chkEndDebugEnabled(isSetRemoteAddr);
	}

	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean isSetRemoteAddr = chkStartDebugEnabled(request);
		super.doDispatch(request, response);
		chkEndDebugEnabled(isSetRemoteAddr);
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isSetRemoteAddr = chkStartDebugEnabled(request);
		super.doOptions(request, response);
		chkEndDebugEnabled(isSetRemoteAddr);
	}

	@Override
	protected void doTrace(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isSetRemoteAddr = chkStartDebugEnabled(request);
		super.doTrace(request, response);
		chkEndDebugEnabled(isSetRemoteAddr);
	}
	
	@Override
	protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean isSetRemoteAddr = chkStartDebugEnabled(request);
		super.render(mv, request, response);
		chkEndDebugEnabled(isSetRemoteAddr);
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isSetRemoteAddr = chkStartDebugEnabled(request);
		super.service(request, response);
		chkEndDebugEnabled(isSetRemoteAddr);
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean isSetRemoteAddr = chkStartDebugEnabled(req);
		super.doHead(req, resp);
		chkEndDebugEnabled(isSetRemoteAddr);
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		boolean isSetRemoteAddr = chkStartDebugEnabled((HttpServletRequest) req);
		super.service(req, res);
		chkEndDebugEnabled(isSetRemoteAddr);
	}
}
