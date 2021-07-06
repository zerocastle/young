package medios.cmmn.util;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import medios.cmmn.constants.CmmnConstants;

public class RequestUtil {

	private static ServletRequestAttributes getServletRequestAttributes() {
		return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	}
	public static String getSessionId() {
		ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
		if(servletRequestAttributes != null)
			return servletRequestAttributes.getSessionId();
		
		return null;
	}
	
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
		if(servletRequestAttributes != null)
			return servletRequestAttributes.getRequest();

		return null;
	}

	public static String getCookie(String cookieName) {
		return getCookie(getRequest(), cookieName);
	}
	
	public static String getCookie(HttpServletRequest request, String cookieName) {
		if(request != null && cookieName != null && !cookieName.trim().isEmpty()) {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for (Cookie cookie : cookies) {
					if(cookie != null && cookieName.equals(cookie.getName())) {
						return cookie.getValue();
					}
				}
			}
		}
		
		return null;
	}
	

	public static void setCookie(HttpServletResponse response,
			String cookieName, String cookieValue) {
		
		setCookie(response, cookieName, cookieValue, false, null, 0);
	}
	

	public static void setCookie(HttpServletResponse response,
			String cookieName, String cookieValue,
			boolean httpOnly, String path, int maxAge) {
		
		Cookie cookie = new Cookie(cookieName, cookieValue);
		
		if(httpOnly)		cookie.setHttpOnly(true);
		if(path != null)	cookie.setPath(path);
		if(maxAge > 0)		cookie.setMaxAge(maxAge);
		
		response.addCookie(cookie);
	}
	public static String getRequestUUID() {
		return getRequestUUID(getRequest());
	}
	public static String getRequestUUID(HttpServletRequest request) {
		if(request == null) return null;
		
		String uuid = getCookie(request, CmmnConstants.REQUEST_UUID_NAME);
		if(uuid == null) uuid = (String) request.getAttribute(CmmnConstants.REQUEST_UUID_NAME);
		
		return uuid;
	}
	
	public static String setRequestUUID(HttpServletRequest request,
			HttpServletResponse response) {
		
		if(request == null || response == null) return null;
		
		String uuid = getRequestUUID(request);
		if(uuid == null) {
			uuid = UUID.randomUUID().toString();//.replaceAll("-", "");
			setCookie(response, CmmnConstants.REQUEST_UUID_NAME, uuid, true, "/", 0);
		}
		
		request.setAttribute(CmmnConstants.REQUEST_UUID_NAME, uuid);
		
		return uuid;
	}
	
	public static String getRouteID() {
		return System.getProperty(CmmnConstants.SERVER_NAME)!=null?System.getProperty(CmmnConstants.SERVER_NAME):"";
	}
}
