package medios.cmmn.exception;

/**
 * Class Name : BizException
 * Description : 업무예외처리를 위한 클래스
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
public class BizException extends RuntimeException {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -752041893732009003L;

	/**
     * 기본 생성자.
     */
	public BizException() {
		super();
	}

	/**
	 * @param msg
	 *            String
	 */
	public BizException(String msg) {
		super(msg);
	}

	/**
	 * @param cause
	 *            Throwable
	 */
	public BizException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param msg
	 *            String
	 * @param cause
	 *            Throwable
	 */
	public BizException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
