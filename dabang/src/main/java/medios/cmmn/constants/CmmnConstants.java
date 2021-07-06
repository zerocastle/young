package medios.cmmn.constants;

/**
 * Class Name : CmmnConstants
 * Description : 내부에서 사용되는 상수값 모음
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
public final class CmmnConstants {

	/**
	 * 생성자 차단.
	 */
	private CmmnConstants() {
	}

	public static final String JSON_ERR_CD_401 = "ERR401";
	public static final String JSON_ERR_CD_403 = "ERR403";
	public static final String JSON_ERR_CD_404 = "ERR404";
	public static final String JSON_ERR_CD_405 = "ERR405";
	public static final String JSON_ERR_CD_500 = "ERR500";
	public static final String JSON_ERR_CD_600 = "ERR600";
	public static final String JSON_ERR_CD_601 = "ERR601";
	public static final String JSON_ERR_CD_602 = "ERR602";
	public static final String JSON_ERR_CD_603 = "ERR603";
	public static final String JSON_ERR_CD_700 = "ERR700";

	/** Spring MVC JSON View 이름. */
	public static final String JSON_VIEW = "jsonView";

	/** 결과값에 대한 메세지 key명 */
	public final static String MESSAGE_KEY = "rsMsg";

	/** Spring MVC 다운로드 View 이름. */
	public static final String DOWNLOAD_VIEW = "downloadView";

	/** 다운로드 View에 넘겨지는 객체명. */
	public static final String DOWNLOAD_FILE = "downloadFile";

	/** 다운로드 View에 넘겨지는 업로드 파일명. */
	public static final String DOWNLOAD_ORI_NAME = "downloadFileOriNm";
	
	/** 페이징 리스트 전체 Row 수. */
	public static final String TOTAL_ROW_COUNT = "TOTAL_COUNT";

	/** Controller에서 리턴되는 결과를 의미. */
	public static final String RESULT = "result";
	
	/** Controller에서 리턴되는 Session maxInactiveInterval를 의미. */
	public static final String MAX_INACTIVE_INTERVAL = "maxInactiveInterval";

	/** Controller에서 리턴되는 결과 리스트를 의미. */
	public static final String RESULT_LIST = "resultList";

	/** Controller에서 리턴되는 결과 맵을 의미. */
	public static final String RESULT_MAP = "resultMap";

	/** Controller에서 리턴되는 결과 리스트의 총 Row 수. */
	public static final String RESULT_COUNT = "resultCnt";

	/** status. */
	public static final String STATUS = "statusCode";

	/** message error code. */
	public static final String MESSAGE_ERROR = "errorCode";

	/** success. */
	public static final String SUCCESS = "S";

	/** error. */
	public static final String ERROR = "E";
	
	/** timeout. */
	public static final String TIMEOUT = "timeout";

	/** message code. */
	public static final String MESSAGE_CD = "msgCd";
	
	/** message. */
	public static final String MESSAGE = "message";

	/** message detail. */
	public static final String MESSAGE_DETAIL = "messageDetail";

	/** 서버로 전달되는 Request Parameter(DataList)에서 각 행(Row)별 상태값. */
	public static final String ROW_STATUS = "rowStatus";

	/** 서버로 전달되는 Request Parameter(DataList)에서 각 행(Row)별 상태값 중 Create 상태. */
	public static final String ROW_CREATE = "C";

	/** 서버로 전달되는 Request Parameter(DataList)에서 각 행(Row)별 상태값 중 Update 상태. */
	public static final String ROW_UPDATE = "U";

	/** 서버로 전달되는 Request Parameter(DataList)에서 각 행(Row)별 상태값 중 Delete 상태. */
	public static final String ROW_DELETE = "D";

	/** 서버로 전달되는 Request Parameter(DataList)에서 각 행(Row)별 상태값 중 Erase 상태. */
	public final static String ROW_ERASE = "E";

	/** Spring Security의 Authority 중 USER. */
	public static final String ROLE_USER = "ROLE_USER";

	/** Spring Security의 Authority 중 ADMIN. */
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	/** User 정보 객체 key */
	public static final String USER_SESSION = "loginVO";
	
	/** Websquare xml move page key */
	public static final String MOVE_PAGE	= "movePage";
	
	/** Websquare xml view page key */
	public static final String W2XPATH		= "w2xPath";

	/** Websquare xml view context key */
	public static final String W2XCTX		= "w2xCtx";

	/** Websquare xml view page key */
	public static final String JSON_ERR_CD = "jsonErrCd";
	
	/** Websquare xml view page key */
	public static final String JSON_ERR_MSG		= "jsonErrMsg";

	/** result page info key */
	public static final String RESULT_PAGE_INFO = "pageInfo";

	public static final String ROUTE_ID = "ROUTE_ID";

	public static final String SERVER_NAME = "env.servername";

	public static final String SERVER_INFO = "ROUTE_SERVER_INFO";

	public static final String LOCAL_ADDR = "LOCAL_ADDR";

	public static final String LOCAL_NAME = "LOCAL_NAME";

	public static final String SERVER_ADDR = "SERVER_ADDR";

	public static final String REMOTE_ADDR = "REMOTE_ADDR";

	public static final String CONTEXT_PATH = "CONTEXT_PATH";

	public static final String REQUEST_URI = "REQUEST_URI";

	public static final String SESSION_ID = "SESSION_ID";
	
	public static final String REQUEST_PARAMS = "REQUEST_PARAMS";
	
	public static final String WEBSOCKET_PATH = "/websocket";

	public static final String WEBSOCKET_MANAGER_PATH = "/websocket_manager";
	
	public static final String WEBSOCKET_EQUAL_UUID = "EQUAL_UUID";
	
	public static final String REQUEST_UUID_NAME = "REQUESTUUID";
	
	public static final String TRANSMIT_SERVER_INFO = "TRANSMIT_SERVER_INFO";
	
}
