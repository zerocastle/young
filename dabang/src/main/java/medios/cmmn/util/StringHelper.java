package medios.cmmn.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import medios.cmmn.exception.BizException;

public class StringHelper {
	private static final Logger log = LoggerFactory.getLogger(StringHelper.class);
	private static final Pattern unicodePattern = Pattern.compile("\\\\u([0-9A-Fa-f]{4})");

	protected StringHelper() {
	}

	public static String getParam(String source, String key, String delim, String defaultValue) {

		if (!isNull(source) && !isNull(key)) {
			int i = source.indexOf(key + "=");
			if (i < 0) {
				return defaultValue;
			} else {
				int j = i + key.length() + 1;
				int k = source.indexOf(delim, j);
				if (k < 0) {
					k = source.length();
				}

				try {
					return source.substring(j, k);
				} catch (Exception var8) {
					return defaultValue;
				}
			}
		} else {
			return defaultValue;
		}
	}

	public static String lPad(String source, int len, char pad) {
		return lPad(source, len, pad, false);
	}

	public static String lPad(String source, int len, char pad, boolean isTrim) {
		if (isTrim) {
			source = source.trim();
		}

		for (int i = source.length(); i < len; ++i) {
			source = pad + source;
		}

		return source;
	}

	public static String rPad(String source, int len, char pad) {
		return rPad(source, len, pad, false);
	}

	public static String rPad(String source, int len, char pad, boolean isTrim) {
		if (isTrim) {
			source = source.trim();
		}

		for (int i = source.length(); i < len; ++i) {
			source = source + pad;
		}

		return source;
	}

	public static String lTrim(String source) {
		int strIdx = 0;
		char[] val = source.toCharArray();

		for (int lenIdx = val.length; strIdx < lenIdx && Character.isWhitespace(val[strIdx]); ++strIdx) {
			;
		}

		return strIdx >= 0 ? source.substring(strIdx) : source;
	}

	public static String rlTrim(String source) {

		String tempVal = "";
		tempVal = Objects.toString(source, "");

		return tempVal.trim();

	}

	public static Object rlTrim(Object source) {

		Object tempVal = "";
		tempVal = Objects.toString(source, "").trim();

		return tempVal;

	}

	public static String rTrim(String source) {
		byte strIdx = 0;
		char[] val = source.toCharArray();
		int count = val.length;

		int lenIdx;
		for (lenIdx = count; strIdx < lenIdx && Character.isWhitespace(val[lenIdx - 1]); --lenIdx) {
			;
		}

		return lenIdx >= 0 ? source.substring(strIdx, lenIdx) : source;
	}

	public static String byteSubString(String source, int len) {
		if (isNull(source)) {
			return "";
		} else {
			String tmp = source;
			int slen = 0;
			int blen = 0;
			if (getByteLength(source) > len) {
				while (blen + 1 < len) {
					char c = tmp.charAt(slen);
					++blen;
					++slen;
					if (c > 127) {
						++blen;
					}
				}

				tmp = tmp.substring(0, slen);
			}

			return tmp;
		}
	}

	public static String alignLeft(String source, int length) {
		return alignLeft(source, length, false);
	}

	public static String alignLeft(String source, int length, boolean isEllipsis) {
		StringBuffer temp;
		if (source.length() > length) {
			if (isEllipsis) {
				temp = new StringBuffer(length);
				temp.append(source.substring(0, length - 3));
				temp.append("...");
				return temp.toString();
			} else {
				return source.substring(0, length);
			}
		} else {
			temp = new StringBuffer(source);

			for (int i = 0; i < length - source.length(); ++i) {
				temp.append(' ');
			}

			return temp.toString();
		}
	}

	public static String alignRight(String source, int length) {
		return alignRight(source, length, false);
	}

	public static String alignRight(String source, int length, boolean isEllipsis) {
		StringBuffer temp;
		if (source.length() > length) {
			if (isEllipsis) {
				temp = new StringBuffer(length);
				temp.append(source.substring(0, length - 3));
				temp.append("...");
				return temp.toString();
			} else {
				return source.substring(0, length);
			}
		} else {
			temp = new StringBuffer(length);

			for (int i = 0; i < length - source.length(); ++i) {
				temp.append(' ');
			}

			temp.append(source);
			return temp.toString();
		}
	}

	public static String alignCenter(String source, int length) {
		return alignCenter(source, length, false);
	}

	public static String alignCenter(String source, int length, boolean isEllipsis) {
		StringBuffer temp;
		if (source.length() > length) {
			if (isEllipsis) {
				temp = new StringBuffer(length);
				temp.append(source.substring(0, length - 3));
				temp.append("...");
				return temp.toString();
			} else {
				return source.substring(0, length);
			}
		} else {
			temp = new StringBuffer(length);
			int leftMargin = (length - source.length()) / 2;
			int rightMargin;
			if (leftMargin * 2 == length - source.length()) {
				rightMargin = leftMargin;
			} else {
				rightMargin = leftMargin + 1;
			}

			int i;
			for (i = 0; i < leftMargin; ++i) {
				temp.append(' ');
			}

			temp.append(source);

			for (i = 0; i < rightMargin; ++i) {
				temp.append(' ');
			}

			return temp.toString();
		}
	}

	public static String capitalize(String source) {
		return !isNull(source) ? source.substring(0, 1).toUpperCase() + source.substring(1).toLowerCase() : source;
	}

	public static int search(String source, String target) {
		int result = 0;
		String strCheck = new String(source);

		for (int i = 0; i < source.length(); strCheck = strCheck.substring(i)) {
			int loc = strCheck.indexOf(target);
			if (loc == -1) {
				break;
			}

			++result;
			i = loc + target.length();
		}

		return result;
	}

	public static int getByteLength(String source) {
		return source.getBytes().length;
	}

	public static boolean isNull(String source) {

		return isNull(source, true);
	}

	public static boolean isNull(Object source) {
		return source == null;
	}

	public static boolean isNull(String source, boolean isTrim) {
		boolean isNullString = false;
		if (isTrim && source != null) {
			source = source.trim();
		}

		if (source == null || "".equals(source)) {
			isNullString = true;
		}

		return isNullString;
	}

	public static boolean isEmpty(Object source) {

		if (isNull(source))
			return true;

		if (source instanceof String)
			return source == null || "".equals(source.toString().trim());
		else if (source instanceof List)
			return source == null || ((List<?>) source).isEmpty();
		else if (source instanceof Map)
			return source == null || ((Map<?, ?>) source).isEmpty();
		else if (source instanceof Object[])
			return source == null || Array.getLength(source) == 0;
		else
			return source == null;

		/*
		 * if (source instanceof String) { return "".equals(((String) source)); } else
		 * if (source instanceof List) { return ((List) source).isEmpty(); } else if
		 * (source instanceof MData) { return ((MData) source).isEmpty(); } else if
		 * (source instanceof Object[]) { return Array.getLength(source) == 0; } else {
		 * return source == null; }
		 */
	}

	public static boolean isNotEmpty(Object source) {

		return !isEmpty(source);

	}

	public static void sortStringArray(String[] source) {
		Arrays.sort(source);
	}

	public static String[] sortStringArray(Enumeration<String> source) {

		Vector<String> buf = new Vector<String>();

		while (source.hasMoreElements()) {
			buf.add(source.nextElement());
		}

		String[] buf2 = new String[buf.size()];

		for (int i = 0; i < buf.size(); ++i) {
			buf2[i] = (String) buf.get(i);
		}

		Arrays.sort(buf2);
		return buf2;
	}

	public static String null2void(String source) {
		if (isNull(source)) {
			source = "";
		}

		return source;
	}

	public static int null2zero(String source) {
		return isNull(source) ? 0 : Integer.parseInt(source);
	}

	public static float null2float(String source) {
		return isNull(source) ? 0.0F : Float.parseFloat(source);
	}

	public static double null2double(String source) {
		return isNull(source) ? 0.0D : Double.parseDouble(source);
	}

	public static long null2long(String source) {
		return isNull(source) ? 0L : Long.parseLong(source);
	}

	public static String null2string(String source, String value) {
		return isNull(source) ? value : new String(source);
	}

	public static boolean strEquals(String source, String target) {
		return null2void(source).equals(null2void(target));
	}

	public static String toSubString(String source, int beginIndex, int endIndex) {
		return strEquals(source, "") ? source
				: (source.length() < beginIndex ? ""
						: (source.length() < endIndex ? source.substring(beginIndex)
								: source.substring(beginIndex, endIndex)));
	}

	public static String toSubString(String source, int beginIndex) {
		return strEquals(source, "") ? source : (source.length() < beginIndex ? "" : source.substring(beginIndex));
	}

	public static List<String> tokenize(String str, String delims) {
		return tokenize(str, delims, false);
	}

	public static List<String> tokenize(String str, String delims, boolean returnDelims) {
		if (str == null) {
			return null;
		} else {
			ArrayList<String> tokens = new ArrayList<String>();
			if (isNull(delims)) {
				tokens.add(str);
			} else {
				StringTokenizer tokenizer = new StringTokenizer(str, delims, returnDelims);

				while (tokenizer.hasMoreElements()) {
					tokens.add(tokenizer.nextToken());
				}
			}

			return tokens;
		}
	}

	public static List<String> split(String str, String delim) {
		if (str == null) {
			return null;
		} else {
			if (delim == null) {
				delim = "";
			}

			int beginIdx = 0;
			ArrayList<String> splitList = new ArrayList<String>();
			int strLen = str.length();
			int delimLen = delim.length();

			int endIdx1;
			do {
				endIdx1 = str.indexOf(delim, beginIdx);
				if (endIdx1 >= 0) {
					splitList.add(str.substring(beginIdx, endIdx1));
				} else {
					splitList.add(str.substring(beginIdx, strLen));
				}

				beginIdx = endIdx1 + delimLen;
			} while (endIdx1 >= 0);

			return splitList;
		}
	}

	public static void split(String str, String delim, String[] dest, String defStr) {
		if (dest != null && dest.length != 0) {
			int cnt = dest.length;
			if (str == null) {
				str = "";
			}

			if (delim == null) {
				delim = " ";
			}

			int beginIdx = 0;
			int strLen = str.length();
			int delimLen = delim.length();
			int count = 0;

			int var11;
			do {
				var11 = str.indexOf(delim, beginIdx);
				if (var11 > 0) {
					dest[count] = str.substring(beginIdx, var11);
				} else {
					dest[count] = str.substring(beginIdx, strLen);
				}

				beginIdx = var11 + delimLen;
				++count;
			} while (var11 > 0 && count < cnt);

			for (int i = count; i < cnt; ++i) {
				dest[i] = defStr;
			}

		}
	}

	public static List<String> split(String str, String delim, int cnt, String defStr) {
		if (str == null) {
			return null;
		} else {
			if (delim == null) {
				delim = " ";
			}

			if (cnt <= 0) {
				return new ArrayList<String>();
			} else {
				ArrayList<String> splitList = new ArrayList<String>(cnt);
				int beginIdx = 0;
				int strLen = str.length();
				int delimLen = delim.length();
				int count = 0;

				int var11;
				do {
					var11 = str.indexOf(delim, beginIdx);
					if (var11 > 0) {
						splitList.add(str.substring(beginIdx, var11));
					} else {
						splitList.add(str.substring(beginIdx, strLen));
					}

					beginIdx = var11 + delimLen;
					++count;
				} while (var11 > 0 && count < cnt);

				for (int i = count; i < cnt; ++i) {
					splitList.add(defStr);
				}

				return splitList;
			}
		}
	}

	public static String replaceLeftSpace(String source, String repStr) {
		Matcher matcher = Pattern.compile("^\\s*").matcher(source);
		StringBuilder sb = new StringBuilder();

		while (matcher.find()) {
			byte[] findGroup = matcher.group().getBytes();

			for (int i = 0; i < findGroup.length; ++i) {
				if (Character.isWhitespace((char) findGroup[i])) {
					sb.append(repStr);
				}
			}
		}

		return sb.toString().concat(matcher.replaceAll(""));
	}

	public static String replaceRightSpace(String source, String repStr) {
		Matcher matcher = Pattern.compile("\\s*$").matcher(source);
		StringBuilder sb = new StringBuilder();

		while (matcher.find()) {
			char[] findGroup = matcher.group().toCharArray();

			for (int i = 0; i < findGroup.length; ++i) {
				if (Character.isWhitespace(findGroup[i])) {
					sb.append(repStr);
				}
			}
		}

		return matcher.replaceAll("").concat(sb.toString());
	}

	public static String unescapeUnicode(String s) {
		String res = s;

		for (Matcher m = unicodePattern.matcher(s); m.find(); res = res.replaceAll("\\" + m.group(0),
				Character.toString((char) Integer.parseInt(m.group(1), 16)))) {
			;
		}

		return res;
	}

	public static byte[] hexStringToByteArray(String hexStr) {
		byte[] byteArr = new byte[hexStr.length() / 2];

		for (int i = 0; i < byteArr.length; ++i) {
			int index = i * 2;
			int byteValue = Integer.parseInt(hexStr.substring(index, index + 2), 16);
			byteArr[i] = (byte) byteValue;
		}

		return byteArr;
	}

	public static void slpForTm(int time) throws BizException {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

			log.debug("Error in StringHelper.slpForTm(" + time + ") : " + e.getMessage());
		}
	}
}
