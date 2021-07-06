package medios.cmmn.util;

import java.math.BigDecimal;

public class NumericHelper {

	public static final int ROUND_UP = 0;
	public static final int ROUND_DOWN = 1;
	public static final int ROUND_HALF_UP = 4;
	public static final int ROUND_FLOOR = 3;
	public static final int ROUND_CEILING = 2;
	public static final int ROUND_UNNECESSARY = 7;
	
	protected NumericHelper() {
	}
	
	public static boolean isNumber(String source) {
	    if(StringHelper.isNull(source)) {
	        return false;
	    } else {
	        try {
	            Double ex = new Double(source);
	            return !ex.isNaN();
	        } catch (NumberFormatException var2) {
	            return false;
	        }
	    }
	}
	
	public static String add(String val1, String val2) {
	    String result = null;
	    BigDecimal one = new BigDecimal(val1 == null?"0":val1);
	    BigDecimal two = new BigDecimal(val2 == null?"0":val2);
	    result = one.add(two).toString();
	    return result;
	}
	
	public static String add(String val1, String val2, int scale) {
	    return add(val1, val2, scale, 7);
	}
	
	public static String add(String val1, String val2, int scale, int roundMode) {
	    String result = null;
	    BigDecimal one = new BigDecimal(val1 == null?"0":val1);
	    BigDecimal two = new BigDecimal(val2 == null?"0":val2);
	    result = one.add(two).setScale(scale, roundMode).toString();
	    return result;
	}
	
	public static String subtract(String val1, String val2) {
	    String result = null;
	    BigDecimal one = new BigDecimal(val1 == null?"0":val1);
	    BigDecimal two = new BigDecimal(val2 == null?"0":val2);
	    result = one.subtract(two).toString();
	    return result;
	}
	
	public static String subtract(String val1, String val2, int scale) {
	    return subtract(val1, val2, scale, 7);
	}
	
	public static String subtract(String val1, String val2, int scale, int roundMode) {
	    String result = null;
	    BigDecimal one = new BigDecimal(val1 == null?"0":val1);
	    BigDecimal two = new BigDecimal(val2 == null?"0":val2);
	    result = one.subtract(two).setScale(scale, roundMode).toString();
	    return result;
	}
	
	public static String multiply(String val1, String val2) {
	    String result = null;
	    BigDecimal one = new BigDecimal(val1 == null?"0":val1);
	    BigDecimal two = new BigDecimal(val2 == null?"0":val2);
	    result = one.multiply(two).toString();
	    return result;
	}
	
	public static String multiply(String val1, String val2, int scale) {
	    return multiply(val1, val2, scale, 7);
	}
	
	public static String multiply(String val1, String val2, int scale, int roundMode) {
	    String result = null;
	    BigDecimal one = new BigDecimal(val1 == null?"0":val1);
	    BigDecimal two = new BigDecimal(val2 == null?"0":val2);
	    result = one.multiply(two).setScale(scale, roundMode).toString();
	    return result;
	}
	
	public static String divide(String val1, String val2, int roundMode) {
	    String result = null;
	    BigDecimal one = new BigDecimal(val1 == null?"0":val1);
	    BigDecimal two = new BigDecimal(val2 == null?"0":val2);
	    result = one.divide(two, roundMode).toString();
	    return result;
	}
	
	public static String divide(String val1, String val2, int scale, int roundMode) {
	    String result = null;
	    BigDecimal one = new BigDecimal(val1 == null?"0":val1);
	    BigDecimal two = new BigDecimal(val2 == null?"0":val2);
	    result = one.divide(two, scale, roundMode).toString();
	    return result;
	}
	
	public static String setScale(String thisVal, int scale, int roundMode) {
	    BigDecimal one = new BigDecimal(thisVal == null?"0":thisVal);
	    return one.setScale(scale, roundMode).toString();
	}
	
	public static BigDecimal round(BigDecimal rounder, int scale, int roundMode) {
	    if(0 > scale) {
	        int digit = Math.abs(scale);
	        rounder = rounder.movePointLeft(digit).setScale(0, roundMode).movePointRight(digit);
	    } else {
	        rounder = rounder.setScale(scale, roundMode);
	    }
	
	    return rounder;
	}
	
	public static double round(double rounder, int scale, int roundMode) {
	    return round(new BigDecimal(rounder), scale, roundMode).doubleValue();
	}
	
	public static String round(String rounder, int scale, int roundMode) {
	    return round(new BigDecimal(rounder), scale, roundMode).toString();
	}
	
	public static long round(long rounder, int scale, int roundMode) {
	    return round(new BigDecimal(rounder), scale, roundMode).longValue();
	}
	
	public static boolean isNumeric(String str) {
	    int dotCount = 0;
	
	    for(int i = 0; i < str.length(); ++i) {
	        char c = str.charAt(i);
	        if(c == 46) {
	            ++dotCount;
	            if(dotCount > 1) {
	                return false;
	            }
	        } else if(!Character.isDigit(c)) {
	            if(c != 45) {
	                return false;
	            }
	
	            if(i > 0) {
	                return false;
	            }
	        }
	    }
	
	    return true;
	}
}
