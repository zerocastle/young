package medios.cmmn.util;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Class Name : MediosMessageSourceHelper
 * Description : 메시지 조회를 위한 Helper 클래스
 *               message_**.properties에 등록된 메시지를 조회한다.
 * @author 의료원연합회-박정호
 * @since 2019. 9. 6.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *   ----------- -------------    ----------------------
 *   2019. 9. 6.  의료원연합회-박정호           최초 생성
 * </pre>
 */
//@Slf4j
public class MediosMessageSourceHelper extends ReloadableResourceBundleMessageSource {

	private static final Logger LOGGER = LoggerFactory.getLogger(MediosMessageSourceHelper.class);

	/**
	 * 메시지 소스.
	 */
	static MessageSource messageSource;

	/**
	 * 메시지 소스 bean을 전달한다.
	 * 
	 * @return
	 */
	public MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * 메시지 소스 bean을 등록한다.
	 * 
	 * @param messageSource
	 */
	public void setMessageSource(MessageSource messageSource) {
		MediosMessageSourceHelper.messageSource = messageSource;
	}

	/**
	 * 메시지 소스 bean에 등록된 메시지를 다국어 설정에 따라 파라미터와 함께 조회한다.
	 * 
	 * @param code
	 * @param args
	 * @return
	 * @throws NoSuchMessageException
	 */
	public static String getMessage(String code, Object... args) throws NoSuchMessageException {
		String message = null;
		try {
			message = messageSource.getMessage(code, args, Locale.getDefault());
		} catch(Exception e) {
			//LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
			LOGGER.error(e.getMessage());
			message = code;
		}
		
		return message;
	}
}
