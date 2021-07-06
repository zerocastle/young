package medios.cmmn.servlet.view;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class MediosUrlBasedViewResolver extends UrlBasedViewResolver {

	public static final String FORWARD_CONTEXT_URL_PREFIX = "forwardContext:";

	@Override
	protected View createView(String viewName, Locale locale) throws Exception {
		if(viewName.startsWith(FORWARD_CONTEXT_URL_PREFIX)) 
			return new MediosInternalResourceView(viewName.substring(FORWARD_CONTEXT_URL_PREFIX.length()));
		
		return super.createView(viewName, locale);
	}
}
