package medios.cmmn.util;

import org.springframework.util.NumberUtils;

/**
 * Class Name : NumericUtil
 * Description : 숫자를 다루는 공통 유틸 클래스
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
public final class NumericUtil {
//	/**
//	 * Logger.
//	 */
//	private static final Logger LOGGER = LoggerFactory.getLogger(NumericUtil.class);
	
	/**
	 * 생성자 차단.
	 */
	private NumericUtil() {
	}

	/**
	 * 주어진 문자열이 숫자 형식인지 체크 후 리턴한다.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		String regEx = "^[+-|0-9][0-9]+$";
		return str.matches(regEx);

	}

	/**
	 * 주어진 숫자가 짝수인지를 체크 후 리턴한다.
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isEven(int num) {
		return num % 2 == 0;
	}

	/**
	 * 주어진 숫자가 홀수인지를 체크 후 리턴한다.
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isOdd(int num) {

		int checkNumber = 0;

		if (num < 0) {
			checkNumber = -1;
		} else {
			checkNumber = 1;
		}

		return checkNumber * num % 2 == 1;
	}

	/**
	 * 주어진 문자열을 Double 타입으로 변환하여 리턴한다.
	 * 
	 * @param str
	 * @return
	 */
	public static double parseDouble(String str) {
		return NumberUtils.parseNumber(str, Double.class);
	}

	/**
	 * 주어진 문자열을 float 타입으로 변환하여 리턴한다.
	 * 
	 * @param str
	 * @return
	 */
	public static float parseFloat(String str) {
		return NumberUtils.parseNumber(str, Float.class);
	}

	/**
	 * 주어진 문자열을 long 타입으로 변환하여 리턴한다.
	 * 
	 * @param str
	 * @return
	 */
	public static long parseLong(String str) {
		return NumberUtils.parseNumber(str, Long.class);
	}

	/**
	 * 주어진 문자열을 int 타입으로 변환하여 리턴한다.
	 * 
	 * @param str
	 * @return
	 */
	public static int parseInt(String str) {
		return NumberUtils.parseNumber(str, Integer.class);
	}

	/**
	 * 주어진 문자열을 Short 타입으로 변환하여 리턴한다.
	 * 
	 * @param str
	 * @return
	 */
	public static short parseShort(String str) {
		return NumberUtils.parseNumber(str, Short.class);
	}

	/**
	 * 주어진 double 데이터를 주어진 자리수에 맞춰 반올림한 후 리턴한다.
	 * 
	 * @param data
	 * @param roundPosition
	 * @return
	 */
	public static double round(double data, int roundPosition) {

		double tmpValue = Math.pow(10, (roundPosition - 1));
		return Math.round(data * tmpValue) / tmpValue;
	}

	/**
	 * 주어진 double 데이터를 주어진 자리수에 맞춰 내림 후 리턴한다.
	 * 
	 * @param data
	 * @param roundPosition
	 * @return
	 */
	public static double floor(double data, int roundPosition) {

		double tmpValue = Math.pow(10, (roundPosition - 1));
		return Math.floor(data * tmpValue) / tmpValue;
	}

	/**
	 * 주어진 double 데이터를 주어진 자리수에 맞춰 올림한 후 리턴한다.
	 * 
	 * @param data
	 * @param roundPosition
	 * @return
	 */
	public static double ceil(double data, int roundPosition) {

		double tmpValue = Math.pow(10, (roundPosition - 1));
		return Math.ceil(data * tmpValue) / tmpValue;
	}

	/**
	 * 입력받은 문자 1자리가 숫자인지 여부
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDigit(String str) {
		String regEx = "[0-9]";
		return str.matches(regEx);

	};

	/**
	 * 입력받은 문자열이 숫자이면 true 아니면 false를 리턴한다.
	 * 
	 * @param word
	 * @return
	 */
	public static boolean isNewNumeric(String word) {
	    String sChar = "";
	    String sSubChar = "";
	    boolean bReturnValue = true;
	    int iLengthValue = word.length();

	    if ("".equals(StringUtil.checkNull(word))) {
	        bReturnValue = false;
	    }
	    
	    if (bReturnValue) {
	        if (word.split(".").length > 2) {
	            bReturnValue = false;
	        }

	        if (bReturnValue) {
	            for (int i = 0; i < iLengthValue; i++) {
	                sChar = word.substring(i, i + 1);

	                if (i == 0) {
	                    if (NumericUtil.isDigit(sChar) || ("-".equals(sChar) && NumericUtil.isDigit(word.substring(1, 2))) || ("+".equals(sChar) && NumericUtil.isDigit(word.substring(1, 2)))) {
	                        bReturnValue = true;
	                    } else {
	                        bReturnValue = false;
	                        break;
	                    }
	                } else {
	                    if (",".equals(sChar)) {
	                        if ((iLengthValue - i - 1) < 3) {
	                            bReturnValue = false;
	                            break;
	                        }

	                        for (int j = i + 1; j <= (i + 3); j++) {
	                            sSubChar = word.substring(j, j + 1);
	                            
	                            if (!NumericUtil.isDigit(sSubChar)) {
	                                bReturnValue = false;
	                                break;
	                            }
	                        }
	                        
	                        if (!bReturnValue) {
	                            break;
	                        }
	                    } else {
	                        if (NumericUtil.isDigit(sChar) || ".".equals(sChar)) {
	                            bReturnValue = true;
	                        } else {
	                            bReturnValue = false;
	                            break;
	                        }
	                    }
	                }
	            }
	        }
	    }

	    return bReturnValue;
	};
}
