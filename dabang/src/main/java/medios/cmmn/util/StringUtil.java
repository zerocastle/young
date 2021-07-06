package medios.cmmn.util;

import java.util.Collection;

import org.springframework.util.StringUtils;

/**
 * Class Name : StringUtil
 * Description : String을 다루는 공통 유틸 클래스
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
public final class StringUtil {
//	/**
//	 * Logger.
//	 */
//	private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

	/**
	 * 주어진 문자열이 Null일 경우 공백을 리턴한다.
	 * 
	 * @param sourceString
	 *            (String)
	 * @return String
	 * @exception
	 * @see
	 */
	public static String checkNull(String sourceString) {
		return checkNull(sourceString, "");
	}

	/**
	 * 주어진 문자열이 Null일 경우 주어진 문자열로 치환한다.
	 * 
	 * @param sourceString
	 *            (String)
	 * @param replaceString
	 *            (String)
	 * @return String
	 * @exception
	 * @see
	 */
	public static String checkNull(String sourceString, String replaceString) {
		String buffer = sourceString;

		if (buffer == null) {
			buffer = replaceString;
		}

		return buffer;
	}

	/**
	 * 
	 * @param arr
	 *            (String[])
	 * @param str
	 *            (String)
	 * @return String[]
	 */
	public static String[] addStringToArray(String[] arr, String str) {
		return StringUtils.addStringToArray(arr, str);
	}

	/**
	 * 파라미터로 주어진 문자열 배열을 문자열로 리턴한다.
	 * 
	 * @param arr
	 *            (String[])
	 * @return String
	 * @exception
	 * @see
	 */
	public static String arrayToString(String[] arr) {
		return StringUtils.arrayToCommaDelimitedString(arr);
	}

	/**
	 * 파라미터로 주어진 Collection 형식을 문자열로 리턴한다.
	 * 
	 * @param coll
	 *            (Collection<?>)
	 * @return String
	 * @exception
	 * @see
	 */
	public static String listToString(Collection<Object> coll) {
		return StringUtils.collectionToCommaDelimitedString(coll);
	}

	/**
	 * 파라미터로 주어진 두 개의 문자열 배열을 합쳐서 리턴한다.
	 * 
	 * @param array1
	 *            (String[])
	 * @param array2
	 *            (String[])
	 * @return String[]
	 * @exception
	 * @see
	 */
	public static String[] concatStringArrays(String[] array1, String[] array2) {
		return StringUtils.concatenateStringArrays(array1, array2);
	}

	/**
	 * 주어진 문자열에서 여백(Whitespace)이 있는지 여부를 리턴한다.
	 * 
	 * @param str
	 *            (String)
	 * @return boolean
	 * @exception
	 * @see
	 */
	public static boolean checkWhitespace(String str) {
		return StringUtils.containsWhitespace(str);
	}

	/**
	 * 주어진 문자열에서 특정 패턴을 제거한다.
	 * 
	 * @param inString
	 *            (String)
	 * @param pattern
	 *            (String)
	 * @return String
	 * @exception
	 * @see
	 */
	public static String delete(String inString, String pattern) {
		return StringUtils.delete(inString, pattern);
	}

	/**
	 * 주어진 문자열에서 특정 문자열(delimiter)를 기준으로 배열로 변환한 후 리턴한다.
	 * 
	 * @param str
	 *            (String)
	 * @param delimiter
	 *            (String)
	 * @return String[]
	 * @exception
	 * @see
	 */
	public static String[] stringToArray(String str, String delimiter) {
		return StringUtils.delimitedListToStringArray(str, delimiter);
	}

	/**
	 * 문자열의 시작이 특정 prefix로 시작하는지 여부를 리턴한다.
	 * 
	 * @param str
	 *            (String)
	 * @param prefix
	 *            (String)
	 * @return boolean
	 * @exception
	 * @see
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		return StringUtils.startsWithIgnoreCase(str, prefix);
	}

	/**
	 * 문자열의 마지막이 특정 suffix로 끝나는지 여부를 리턴한다.
	 * 
	 * @param str
	 *            (String)
	 * @param suffix
	 *            (String)
	 * @return boolean
	 * @exception
	 * @see
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		return StringUtils.endsWithIgnoreCase(str, suffix);
	}

	/**
	 * 주어진 문자열을 싱클쿼테이션('')으로 감싼 후 리턴한다.
	 * 
	 * @param str
	 *            (String)
	 * @return String
	 * @exception
	 * @see
	 */
	public static String quote(String str) {
		return StringUtils.quote(str);
	}

	/**
	 * 문자열 배열에서 중복되는 요소를 제거한다.
	 * 
	 * @param array
	 *            (String[])
	 * @return String[]
	 * @exception
	 * @see
	 */
	public static String[] removeDuplicateStrings(String[] array) {
		return StringUtils.removeDuplicateStrings(array);
	}

	/**
	 * 주어진 문자열에서 특정 패턴을 새로운 패턴으로 치환한다.
	 * 
	 * @param inString
	 *            (String)
	 * @param oldPattern
	 *            (String)
	 * @param newPattern
	 *            (String)
	 * @return String
	 * @exception
	 * @see
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		return StringUtils.replace(inString, oldPattern, newPattern);
	}

	/**
	 * 문자열 배열을 오름차순으로 Sorting하여 리턴한다.
	 * 
	 * @param array
	 *            (String[])
	 * @return String[]
	 * @exception
	 * @see
	 */
	public static String[] sortStringArray(String[] array) {
		return StringUtils.sortStringArray(array);
	}

	/**
	 * 주어진 문자열(filePath)에서 확장자를 제거한 후 리턴한다.
	 * 
	 * @param filePath
	 *            (String)
	 * @return String
	 * @exception
	 * @see
	 */
	public static String stripExtName(String filePath) {
		return StringUtils.stripFilenameExtension(filePath);
	}

	/**
	 * 주어진 문자열에서 앞뒤로 여백(Whitespace)를 제거하여 리턴한다.
	 * 
	 * @param str
	 *            (String)
	 * @return String
	 * @exception
	 * @see
	 */
	public static String trim(String str) {
		return StringUtils.trimWhitespace(str);
	}

	/**
	 * 주어진 문자열에서 모든 여백(Whitespace)를 제거하여 리턴한다.
	 * 
	 * @param str
	 *            (String)
	 * @return String
	 * @exception
	 * @see
	 */
	public static String trimAllWhitespace(String str) {
		return StringUtils.trimAllWhitespace(str);
	}

	/**
	 * 주어진 문자열의 왼쪽에서 특정 문자(Character) 제거하여 리턴한다.
	 * 
	 * @param str
	 *            (String)
	 * @param leadChar
	 *            char
	 * @return String
	 */
	public static String trimLeadingCharacter(String str, char leadChar) {
		return StringUtils.trimLeadingCharacter(str, leadChar);
	}

	/**
	 * 주어진 문자열의 오른쪽에서 특정 문자(Character) 제거하여 리턴한다.
	 * 
	 * @param str
	 *            (String)
	 * @param trailChar
	 *            char
	 * @return String
	 * @exception
	 * @see
	 */
	public static String trimTrailingCharacter(String str, char trailChar) {
		return StringUtils.trimTrailingCharacter(str, trailChar);
	}

	/**
	 * 문자열배열의 요소 중 좌우 공백이 있을 경우 제거한 후 리턴한다.
	 * 
	 * @param array
	 *            (String[])
	 * @return String
	 * @exception
	 * @see
	 */
	public static String[] trimArrayElements(String[] array) {
		return StringUtils.trimArrayElements(array);
	}
	
    /**
     * lPad 함수.
     * 
     * @param str
     *            String
     * @param num
     *            int
     * @param chr
     *            String           
     * @return String lPad 처리된 문자
     */
    public static String lPad(String str, int num, String chr) {
    	String sVal = str;
    	int iLen = sVal.length();

    	if (iLen == num) {
    		return sVal;
    	}

    	for (int i = 0; i < (num - iLen); i++) {
    		sVal = chr + sVal;
        }

        return sVal;    
    }
    
    /**
     * lPad 함수.
     * 
     * @param str
     *            String
     * @param num
     *            int
     * @param chr
     *            String           
     * @return String lPad 처리된 문자
     */
    public static String rPad(String str, int num, String chr) {
    	String sVal = str;
    	int iLen = sVal.length();

    	if (iLen == num) {
    		return sVal;
    	}

    	for (int i = 0; i < (num - iLen); i++) {
    		sVal = sVal + chr;
        }

        return sVal;    
    }
}
