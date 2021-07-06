package medios.cmmn.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import medios.cmmn.collection.MData;

//@Slf4j
public class DateHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateHelper.class);
	  
	public static final int YEAR = 1;
	public static final int MONTH = 2;
	public static final int DAY = 3;
	public static final int MONTH_DAY = 4;
	public static final int YEAR_MONTH = 5;
	public static final int YEAR_DAY = 6;
	public static final int YEAR_MONTH_DAY = 7;
	private static String[] koreanWeekStrings = new String[]{"일", "월", "화", "수", "목", "금", "토"};
	private static String[] englishWeekStrings = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};
	public static final int FROM_DAY_ADD = 1;
	public static final int TO_DAY_ADD = 2;
	public static final int BOTH_DAY_ADD = 3;
	public static final int NONE_DAY_ADD = 4;

	protected DateHelper() {
	}

	public static String getCalcDateAsString(String sYearPara, String sMonthPara, String sDayPara, int iTerm, String sGuBun) {
	    GregorianCalendar cd = new GregorianCalendar(Integer.parseInt(sYearPara), Integer.parseInt(sMonthPara) - 1, Integer.parseInt(sDayPara));
	    if(StringHelper.strEquals(sGuBun, "day")) {
	        cd.add(5, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "month")) {
	        cd.add(2, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "year")) {
	        cd.add(1, iTerm);
	    }
	
    	return getFormalYear(cd) + getFormalMonth(cd) + getFormalDay(cd);
	}
	
	public static String getCalcYearAsString(String sYearPara, String sMonthPara, String sDayPara, int iTerm, String sGuBun) {
	    GregorianCalendar cd = new GregorianCalendar(Integer.parseInt(sYearPara), Integer.parseInt(sMonthPara) - 1, Integer.parseInt(sDayPara));
	    if(StringHelper.strEquals(sGuBun, "day")) {
	        cd.add(5, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "month")) {
	        cd.add(2, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "year")) {
	        cd.add(1, iTerm);
	    }
	
	    return getFormalYear(cd);
	}
	
	public static String getCalcMonthAsString(String sYearPara, String sMonthPara, String sDayPara, int iTerm, String sGuBun) {
	    GregorianCalendar cd = new GregorianCalendar(Integer.parseInt(sYearPara), Integer.parseInt(sMonthPara) - 1, Integer.parseInt(sDayPara));
	    if(StringHelper.strEquals(sGuBun, "day")) {
	        cd.add(5, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "month")) {
	        cd.add(2, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "year")) {
	        cd.add(1, iTerm);
	    }
	
	    return getFormalMonth(cd);
	}
	
	public static String getCalcDayAsString(String sYearPara, String sMonthPara, String sDayPara, int iTerm, String sGuBun) {
	    GregorianCalendar cd = new GregorianCalendar(Integer.parseInt(sYearPara), Integer.parseInt(sMonthPara) - 1, Integer.parseInt(sDayPara));
	    if(StringHelper.strEquals(sGuBun, "day")) {
	        cd.add(5, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "month")) {
	        cd.add(2, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "year")) {
	        cd.add(1, iTerm);
	    }
	
	    return getFormalDay(cd);
	}
	
	public static int getCalcYearAsInt(String sYearPara, String sMonthPara, String sDayPara, int iTerm, String sGuBun) {
	    GregorianCalendar cd = new GregorianCalendar(Integer.parseInt(sYearPara), Integer.parseInt(sMonthPara) - 1, Integer.parseInt(sDayPara));
	    if(StringHelper.strEquals(sGuBun, "day")) {
	        cd.add(5, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "month")) {
	        cd.add(2, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "year")) {
	        cd.add(1, iTerm);
	    }
	
	    return cd.get(1);
	}
	
	public static int getCalcMonthAsInt(String sYearPara, String sMonthPara, String sDayPara, int iTerm, String sGuBun) {
	    GregorianCalendar cd = new GregorianCalendar(Integer.parseInt(sYearPara), Integer.parseInt(sMonthPara) - 1, Integer.parseInt(sDayPara));
	    if(StringHelper.strEquals(sGuBun, "day")) {
	        cd.add(5, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "month")) {
	        cd.add(2, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "year")) {
	        cd.add(1, iTerm);
	    }
	
	    return cd.get(2) + 1;
	}
	
	public static int getCalcDayAsInt(String sYearPara, String sMonthPara, String sDayPara, int iTerm, String sGuBun) {
	    GregorianCalendar cd = new GregorianCalendar(Integer.parseInt(sYearPara), Integer.parseInt(sMonthPara) - 1, Integer.parseInt(sDayPara));
	    if(StringHelper.strEquals(sGuBun, "day")) {
	        cd.add(5, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "month")) {
	        cd.add(2, iTerm);
	    } else if(StringHelper.strEquals(sGuBun, "year")) {
	        cd.add(1, iTerm);
	    }
	
	    return cd.get(5);
	}
	
	public static int getCurrentYearAsInt() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return cd.get(1);
	}
	
	public static int getCurrentMonthAsInt() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return cd.get(2) + 1;
	}
	
	public static int getCurrentDayAsInt() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return cd.get(5);
	}
	
	public static int getCurrentHourAsInt() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return cd.get(11);
	}
	
	public static int getCurrentMinuteAsInt() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return cd.get(12);
	}
	
	public static int getCurrentMilliSecondAsInt() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return cd.get(14);
	}
	
	public static String getCurrentYearAsString() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return getFormalYear(cd);
	}
	
	public static String getCurrentMonthAsString() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return getFormalMonth(cd);
	}
	
	public static String getCurrentDayAsString() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return getFormalDay(cd);
	}
	
	public static String getCurrentHourAsString() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return getFormalHour(cd);
	}
	
	public static String getCurrentMinuteAsString() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return getFormalMin(cd);
	}
	
	public static String getCurrentSecondAsString() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return getFormalSec(cd);
	}
	
	public static String getCurrentMilliSecondAsString() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return getFormalMSec(cd);
	}
	
	public static String getCurrentDateAsString() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return getFormalYear(cd) + getFormalMonth(cd) + getFormalDay(cd);
	}
	
	public static String getCurrentTimeAsString() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return getFormalHour(cd) + getFormalMin(cd) + getFormalSec(cd);
	}
	
	public static String getCurrentDateTimeAsString() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return getFormalYear(cd) + getFormalMonth(cd) + getFormalDay(cd) + getFormalHour(cd) + getFormalMin(cd) + getFormalSec(cd);
	}
	
	public static String getCurrentDateTimeMilliSecondAsString() {
	    GregorianCalendar cd = new GregorianCalendar(Locale.KOREA);
	    return getFormalYear(cd) + getFormalMonth(cd) + getFormalDay(cd) + getFormalHour(cd) + getFormalMin(cd) + getFormalSec(cd) + getFormalMSec(cd);
	}
	
	public static String getDayOfWeekAsString(String sYear, String sMonth, String sDay) {
	    GregorianCalendar cd = new GregorianCalendar(Integer.parseInt(sYear), Integer.parseInt(sMonth) - 1, Integer.parseInt(sDay));
	    SimpleDateFormat sdf = new SimpleDateFormat("EEE", Locale.KOREA);
	    Date d1 = cd.getTime();
	    return sdf.format(d1);
	}
	
	public static int getFullAge(String socialNo, String keyDate) {
	    String birthDate = null;
	    birthDate = getSocialNo2DateFormat(socialNo);
	    return getFullAgeByBirthDate(birthDate, keyDate);
	}
	
	public static int getFullAgeByBirthDate(String birthDate, String keyDate) {
	    return Integer.parseInt(keyDate.substring(4, 8)) < Integer.parseInt(birthDate.substring(4, 8))?Integer.parseInt(keyDate.substring(0, 4)) - Integer.parseInt(birthDate.substring(0, 4)) - 1:Integer.parseInt(keyDate.substring(0, 4)) - Integer.parseInt(birthDate.substring(0, 4));
	}
	
	public static String getSocialNo2DateFormat(String socialNo) {
	    String birthDate = null;
	    if(StringHelper.null2void(socialNo).length() < 13) {
	        throw new IllegalArgumentException(" place possibility must be an above 6 places.(socialNo)");
	    } else {
	        if(!StringHelper.strEquals(StringHelper.toSubString(socialNo, 6, 7), "0") && !StringHelper.strEquals(StringHelper.toSubString(socialNo, 6, 7), "9")) {
	            if(!StringHelper.strEquals(StringHelper.toSubString(socialNo, 6, 7), "1") && !StringHelper.strEquals(StringHelper.toSubString(socialNo, 6, 7), "2") && !StringHelper.strEquals(StringHelper.toSubString(socialNo, 6, 7), "5") && !StringHelper.strEquals(StringHelper.toSubString(socialNo, 6, 7), "6")) {
	                if(!StringHelper.strEquals(StringHelper.toSubString(socialNo, 6, 7), "3") && !StringHelper.strEquals(StringHelper.toSubString(socialNo, 6, 7), "4") && !StringHelper.strEquals(StringHelper.toSubString(socialNo, 6, 7), "7") && !StringHelper.strEquals(StringHelper.toSubString(socialNo, 6, 7), "8")) {
	                    throw new IllegalArgumentException(" wrong flag [7].");
	                }
	
	                birthDate = "20" + socialNo.substring(0, 6);
	            } else {
	                birthDate = "19" + socialNo.substring(0, 6);
	            }
	        } else {
	            birthDate = "18" + socialNo.substring(0, 6);
	        }
	
	        return birthDate;
	    }
	}
	
	public static int getCurrentFullAge(String socialNo) {
	    String sCurrentDate = getCurrentYearAsString() + getCurrentMonthAsString() + getCurrentDayAsString();
	    return getFullAge(socialNo, sCurrentDate);
	}
	
	public static int getDayCountForMonth(int year, int month) {
	    int[] DOMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	    int[] lDOMonth = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	    return isLeapYear(year)?lDOMonth[month - 1]:DOMonth[month - 1];
	}
	
	public static boolean isLeapYear(String year) {
	    return (new GregorianCalendar()).isLeapYear(Integer.parseInt(year));
	}
	
	public static boolean isLeapYear(int year) {
	    return (new GregorianCalendar()).isLeapYear(year);
	}
	
	public static int getDayCount(String from, String to) throws ParseException {
	    return getDayCountWithFormatter(from, to, "yyyyMMdd");
	}
	
	public static Date dateFormatCheck(String source) throws ParseException {
	    return dateFormatCheck(source, "yyyyMMdd");
	}
	
	public static Date dateFormatCheck(String source, String format) throws ParseException {
	    if(source == null) {
	        throw new ParseException("date string to check is null", 0);
	    } else if(format == null) {
	        throw new ParseException("format string to check date is null", 0);
	    } else {
	        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
	        Date date = null;
	
	        try {
	            date = formatter.parse(source);
	        } catch (ParseException var5) {
	            throw new ParseException(" wrong date:\"" + source + "\" with format \"" + format + "\"", 0);
	        }
	
	        if(!formatter.format(date).equals(source)) {
	            throw new ParseException("Out of bound date:\"" + source + "\" with format \"" + format + "\"", 0);
	        } else {
	            return date;
	        }
	    }
	}
	
	public static int getDayCountWithFormatter(String from, String to, String format) throws ParseException {
	    long duration = getTimeCount(from, to, format);
	    return (int)(duration / 86400000L);
	}
	
	protected static String getFormatStringWithDate(String date) throws ParseException {
	    String format = null;
	    if(date.length() == 4) {
	        format = "HHmm";
	    } else if(date.length() == 8) {
	        format = "yyyyMMdd";
	    } else if(date.length() == 10) {
	        format = "yyyyMMddHH";
	    } else if(date.length() == 12) {
	        format = "yyyyMMddHHmm";
	    } else if(date.length() == 14) {
	        format = "yyyyMMddHHmmss";
	    } else {
	        if(date.length() != 17) {
	            throw new ParseException(" wrong date format!:\"" + format + "\"", 0);
	        }
	
	        format = "yyyyMMddHHmmssSSS";
	    }
	
	    return format;
	}
	
	public static long getTimeCount(String from, String to) throws ParseException {
	    String format = getFormatStringWithDate(from);
	    return getTimeCount(from, to, format);
	}
	
	public static long getTimeCount(String from, String to, String format) throws ParseException {
	    Date d1 = dateFormatCheck(from, format);
	    Date d2 = dateFormatCheck(to, format);
	    long duration = d2.getTime() - d1.getTime();
	    return duration;
	}
	
	public static int getDayOfWeekCount(String from, String to, String yoil) throws ParseException {
	    int first = 0;
	    byte count = 0;
	    String[] sYoil = new String[]{"일", "월", "화", "수", "목", "금", "토"};
	    int betweenDays = getDayCount(from, to);
	    GregorianCalendar cd = new GregorianCalendar(Integer.parseInt(from.substring(0, 4)), Integer.parseInt(from.substring(4, 6)) - 1, Integer.parseInt(from.substring(6, 8)));
	    int dayOfWeek = cd.get(7);
	    if(yoil.length() == 3) {
	        yoil = yoil.substring(0, 1);
	    }
	
	    while(!sYoil[dayOfWeek - 1].equals(yoil)) {
	        ++dayOfWeek;
	        ++first;
	    }
	
	    if(betweenDays - first < 0) {
	        return 0;
	    } else {
	        int var9 = count + 1;
	        var9 += (betweenDays - first) / 7;
	        return var9;
	    }
	}
	
	private static String getFormalYear(Calendar cd) {
	    return toString(cd.getTime(), "yyyy", Locale.KOREA);
	}
	
	private static String getFormalMonth(Calendar cd) {
	    return toString(cd.getTime(), "MM", Locale.KOREA);
	}
	
	private static String getFormalDay(Calendar cd) {
	    return toString(cd.getTime(), "dd", Locale.KOREA);
	}
	
	private static String getFormalHour(Calendar cd) {
	    return toString(cd.getTime(), "HH", Locale.KOREA);
	}
	
	private static String getFormalMin(Calendar cd) {
	    return toString(cd.getTime(), "mm", Locale.KOREA);
	}
	
	private static String getFormalSec(Calendar cd) {
	    return toString(cd.getTime(), "ss", Locale.KOREA);
	}
	
	private static String getFormalMSec(Calendar cd) {
	    return toString(cd.getTime(), "SSS", Locale.KOREA);
	}
	
	public static String toString(Date date, String format, Locale locale) {
	    if(StringHelper.isNull(format)) {
	        format = "yyyy-MM-dd HH:mm:ss";
	    }
	
	    if(StringHelper.isNull(locale)) {
	        locale = Locale.KOREA;
	    }
	
	    SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
	    String tmp = sdf.format(date);
	    return tmp;
	}
	
	public static String formatter(String strDate, String format) {
	    Calendar calendar = Calendar.getInstance(Locale.KOREA);
	    if(strDate.length() == 8) {
	        calendar.set(Integer.parseInt(strDate.substring(0, 4)), Integer.parseInt(strDate.substring(4, 6)) - 1, Integer.parseInt(strDate.substring(6)));
	    } else if(strDate.length() == 12) {
	        calendar.set(Integer.parseInt(strDate.substring(0, 4)), Integer.parseInt(strDate.substring(4, 6)) - 1, Integer.parseInt(strDate.substring(6, 8)), Integer.parseInt(strDate.substring(8, 10)), Integer.parseInt(strDate.substring(10)));
	    } else {
	        if(strDate.length() != 14) {
	            return strDate;
	        }
	
	        calendar.set(Integer.parseInt(strDate.substring(0, 4)), Integer.parseInt(strDate.substring(4, 6)) - 1, Integer.parseInt(strDate.substring(6, 8)), Integer.parseInt(strDate.substring(8, 10)), Integer.parseInt(strDate.substring(10, 12)), Integer.parseInt(strDate.substring(12)));
	    }
	
	    return toString(calendar.getTime(), format, Locale.KOREA);
	}
	
	public static String getDateInterval(String from, String to, int format) throws ParseException {
	 
	    int monthDue = 0;
	    int yearDue = 0;
	    String result = "";
	    int var12 = getDayCount(from, to);
	    if(format == 3) {
	        return String.valueOf(var12);
	    } else {
	        int year = Integer.parseInt(StringHelper.toSubString(from, 0, 4));
	        int month = Integer.parseInt(StringHelper.toSubString(from, 4, 6));
	        int day = Integer.parseInt(StringHelper.toSubString(from, 6));
	        if(day != 1) {
	            ++month;
	        }
	
	        do {
	            if(month > 12) {
	                month = 1;
	                ++year;
	            }
	
	            int var11 = getDayCountForMonth(year, month);
	            if(var12 < var11) {
	                break;
	            }
	
	            var12 -= var11;
	            ++month;
	            ++monthDue;
	        } while(format != 6 || var12 >= 365 || monthDue % 12 != 0);
	
	        if(format != 4 && format != 2 && monthDue >= 12) {
	            yearDue = monthDue / 12;
	            monthDue %= 12;
	        }
	
	        if(format == 1) {
	            result = String.valueOf(yearDue);
	        } else if(format == 2) {
	            result = String.valueOf(monthDue);
	        } else if(format == 4) {
	            result = monthDue + "/" + var12;
	        } else if(format == 5) {
	            result = yearDue + "/" + monthDue;
	        } else if(format == 6) {
	            result = yearDue + "/" + var12;
	        } else {
	            result = yearDue + "/" + monthDue + "/" + var12;
	        }
	
	        return result;
	    }
	}
	
	public static String getDateTimeInterval(String from, String to) throws ParseException {
	 
	    String fformat = getFormatStringWithDate(from);
	    String tformat = getFormatStringWithDate(to);
	    Date fdate = dateFormatCheck(from, fformat);
	    Date tdate = dateFormatCheck(to, tformat);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    from = sdf.format(fdate);
	    to = sdf.format(tdate);
	    int fyear = Integer.parseInt(StringHelper.toSubString(from, 0, 4));
	    int fmonth = Integer.parseInt(StringHelper.toSubString(from, 4, 6));
	    int fday = Integer.parseInt(StringHelper.toSubString(from, 6, 8));
	    int fhour = Integer.parseInt(StringHelper.toSubString(from, 8, 10));
	    int fmin = Integer.parseInt(StringHelper.toSubString(from, 10, 12));
	    int fsec = Integer.parseInt(StringHelper.toSubString(from, 12, 14));
	    int fmsec = Integer.parseInt(StringHelper.toSubString(from, 14));
	    int tyear = Integer.parseInt(StringHelper.toSubString(to, 0, 4));
	    int tmonth = Integer.parseInt(StringHelper.toSubString(to, 4, 6));
	    int tday = Integer.parseInt(StringHelper.toSubString(to, 6, 8));
	    int thour = Integer.parseInt(StringHelper.toSubString(to, 8, 10));
	    int tmin = Integer.parseInt(StringHelper.toSubString(to, 10, 12));
	    int tsec = Integer.parseInt(StringHelper.toSubString(to, 12, 14));
	    int tmsec = Integer.parseInt(StringHelper.toSubString(to, 14));
	    int emsec = tmsec - fmsec;
	    if(emsec < 0) {
	        emsec += 1000;
	        --tsec;
	    }
	
	    int esec = tsec - fsec;
	    if(esec < 0) {
	        esec += 60;
	        --tmin;
	    }
	
	    int emin = tmin - fmin;
	    if(emin < 0) {
	        emin += 60;
	        --thour;
	    }
	
	    int ehour = thour - fhour;
	    if(ehour < 0) {
	        ehour += 24;
	        --tday;
	    }
	
	    int fmaxdays = getDayCountForMonth(fyear, fmonth);
	    int eday = tday - fday;
	    if(eday < 0) {
	        eday += fmaxdays;
	        ++fmonth;
	    }
	
	    if(fmonth > 12) {
	        fmonth = 1;
	        ++fyear;
	    }
	
	    int emonth = tmonth - fmonth;
	    if(emonth < 0) {
	        emonth += 12;
	        --tyear;
	    }
	
	    int eyear = tyear - fyear;
	    StringBuilder sb = new StringBuilder(17);
	    sb.append(StringHelper.lPad(Integer.toString(eyear), 4, '0'));
	    sb.append(StringHelper.lPad(Integer.toString(emonth), 2, '0'));
	    sb.append(StringHelper.lPad(Integer.toString(eday), 2, '0'));
	    sb.append(StringHelper.lPad(Integer.toString(ehour), 2, '0'));
	    sb.append(StringHelper.lPad(Integer.toString(emin), 2, '0'));
	    sb.append(StringHelper.lPad(Integer.toString(esec), 2, '0'));
	    sb.append(StringHelper.lPad(Integer.toString(emsec), 3, '0'));
	    return sb.toString();
	}
	
	public static String getDateInterval(String from, String to) throws ParseException {
	    return getDateInterval(from, to, 7);
	}
	
	public static String getMaxDayOfMonth(String day) {
	    Calendar cal = stringToCalendar(day);
	    return StringHelper.lPad(Integer.toString(cal.getActualMaximum(5)), 2, '0');
	}
	
	public static String getMaxDay(String day) {
	    return day.substring(0, 6) + getMaxDayOfMonth(day);
	}
	
	public static String getDay() {
	    Calendar cdar = Calendar.getInstance();
	    return getFormalYear(cdar) + getFormalMonth(cdar) + getFormalDay(cdar);
	}
	
	public static String getYear() {
	    return getFormalYear(Calendar.getInstance());
	}
	
	public static String getMonth() {
	    return getFormalMonth(Calendar.getInstance());
	}
	
	public static String getDayOfMonth() {
	    return getFormalDay(Calendar.getInstance());
	}
	
	public static String getDayOfWeek() {
	    int i = Calendar.getInstance().get(7);
	    return koreanWeekStrings[i - 1];
	}
	
	public static int getDayOfWeekOrdinal() {
	    int i = Calendar.getInstance().get(7);
	    return i - 1;
	}
	
	public static String getDayOfWeek(String day) {
	    int i = stringToCalendar(day).get(7);
	    return koreanWeekStrings[i - 1];
	}
	
	public static int getDayOfWeekOrdinal(String day) {
	    int i = stringToCalendar(day).get(7);
	    return i - 1;
	}
	
	public static String getHour() {
	    return getFormalHour(Calendar.getInstance());
	}
	
	public static String getMinute() {
	    return getFormalMin(Calendar.getInstance());
	}
	
	public static String getSecond() {
	    return getFormalSec(Calendar.getInstance());
	}
	
	public static String addYears(String day, int addNum) {
	    return add(day, 1, addNum);
	}
	
	public static String addMonths(String day, int addNum) {
	    return add(day, 2, addNum);
	}
	
	public static String addDays(String day, int addNum) {
	    return add(day, 5, addNum);
	}
	
	public static int getDays(String from, String to) {
	    Calendar calFrom = stringToCalendar(from);
	    Calendar calTo = stringToCalendar(to);
	    long gab = calTo.getTimeInMillis() - calFrom.getTimeInMillis();
	    long lDays = gab / 86400000L;
	    return (int)lDays + 1;
	}
	
	public static int getDays(String from, String to, int type) {
	    int days = 0;
	    if(type == 1) {
	        days = getDays(from, to) - 1;
	    } else if(type == 2) {
	        days = getDays(from, to) - 1;
	    } else if(type == 3) {
	        days = getDays(from, to);
	    } else if(type == 4) {
	        days = getDays(from, to) - 2;
	    }
	
	    return days;
	}
	
	public static boolean isValidDate(String date) {
	    boolean isValid = true;
	
	    try {
	        dateFormatCheck(date);
	    } catch (ParseException var3) {
	        isValid = false;
	    }
	
	    return isValid;
	}
	
	public static int getMonths(String from, String to) {
	    Calendar calFrom = stringToCalendar(from);
	    Calendar calTo = stringToCalendar(to);
	    int fromMonth = calFrom.get(2);
	    int fromYear = calFrom.get(1);
	    int toMonth = calTo.get(2);
	    int toYear = calTo.get(1);
	    int yearGab = toYear - fromYear;
	    int monthGab = toMonth - fromMonth;
	    int gab = yearGab * 12 + monthGab;
	    return gab;
	}
	
	public static int getYears(String from, String strTo) {
	    Calendar calFrom = stringToCalendar(from);
	    Calendar calTo = stringToCalendar(strTo);
	    int fromYear = calFrom.get(1);
	    int toYear = calTo.get(1);
	    int gab = toYear - fromYear;
	    return gab;
	}
	
	public static long getTimesInMillis(String from, String to) {
	    Calendar calFrom = stringToCalendar(from);
	    Calendar calTo = stringToCalendar(to);
	    return calTo.getTimeInMillis() - calFrom.getTimeInMillis();
	}
	
	
	public static Date stringToDate(String day) {
		return stringToCalendar(day).getTime();
	}

	public static Date stringToDate(String date, String dateFormat) {
		Date result = null;

		try {
			DateFormat dateForm = new SimpleDateFormat(dateFormat);
			result = dateForm.parse(date);
		} catch (Exception e) {
			LOGGER.debug("Error in HeaComnServiceUtil.toDate(" + date + "," + dateFormat + ") : " + e.getMessage());
		}

		return result;
	}
	
	public static String getTermTimeAsString(String from, String to) throws ParseException {
	    String currentDateStr = getCurrentDateAsString();
	    if(StringHelper.null2void(from).length() != 4) {
	        throw new ParseException("from hour must be \'hhmm\' format.", 0);
	    } else if(StringHelper.null2void(to).length() != 4) {
	        throw new ParseException("to hour must be \'hhmm\' format.", 0);
	    } else {
	        from = currentDateStr.concat(from);
	        to = currentDateStr.concat(to);
	        long duration = getTimeCount(from, to);
	        int hour = (int)(duration / 3600000L);
	        int minute = (int)(duration % 3600000L) / '\uea60';
	        return StringHelper.lPad(Integer.toString(hour), 2, '0').concat(StringHelper.lPad(Integer.toString(minute), 2, '0'));
	    }
	}
	
	public static String getCalcTimeAsString(String from, String to) throws ParseException {
	    if(StringHelper.null2void(from).length() != 4) {
	        throw new ParseException("from hour must be \'hhmm\' format.", 0);
	    } else if(StringHelper.null2void(to).length() != 4) {
	        throw new ParseException("to hour must be \'hhmm\' format.", 0);
	    } else {
	        int fromMinute = Integer.parseInt(hourToMinute(from));
	        int toMinute = Integer.parseInt(hourToMinute(to));
	        int sumMinute = fromMinute + toMinute;
	        return minuteToHour(String.valueOf(sumMinute));
	    }
	}
	
	public static String minuteToHour(String minute) {
	    long duration = Long.parseLong(minute);
	    int hour = (int)(duration / 60L);
	    int min = (int)(duration % 60L);
	    return StringHelper.lPad(Integer.toString(hour), 2, '0').concat(StringHelper.lPad(Integer.toString(min), 2, '0'));
	}
	
	public static String hourToMinute(String time) throws ParseException {
	    if(StringHelper.null2void(time).length() != 4) {
	        throw new ParseException("time must be \'hhmm\' format.", 0);
	    } else {
	        int hour = Integer.parseInt(StringHelper.toSubString(time, 0, 2));
	        int min = Integer.parseInt(StringHelper.toSubString(time, 2, 4));
	        hour *= 60;
	        min += hour;
	        return String.valueOf(min);
	    }
	}
	
	public static MData getCalendarInfo(String sDate, boolean korean) throws ParseException {
	 
		MData resultVO = new MData();
	    String[] weekname = null;
	    if(korean) {
	        weekname = koreanWeekStrings;
	    } else {
	        weekname = englishWeekStrings;
	    }
	
	    int var10;
	    int var11;
	    if(StringHelper.isNull(sDate)) {
	        var10 = getCurrentYearAsInt();
	        var11 = getCurrentMonthAsInt();
	    } else {
	        dateFormatCheck(sDate);
	        var10 = Integer.parseInt(sDate.substring(0, 4));
	        var11 = Integer.parseInt(sDate.substring(4, 6));
	    }
	
	    int startIdx = getDayOfWeek(var10, var11, 1);
	    int endIdx = getDayCountForMonth(var10, var11);
	    int i = 0;
	
	    for(int day = 0; i < 42; ++i) {
	        if(i >= startIdx - 1 && i < startIdx + endIdx - 1) {
	            ++day;
	            resultVO.set(i / 7, weekname[i % 7], day);
	        } else {
	            resultVO.set(i / 7, weekname[i % 7], "");
	        }
	    }
	
	    return resultVO;
	}
	
	public static MData getCalendarInfo(String sDate) throws ParseException {
	    return getCalendarInfo(sDate, false);
	}
	
	public static int getDayOfWeek(int yyyy, int mm, int dd) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(yyyy, mm - 1, dd);
	    return calendar.get(7);
	}
	
	private static Calendar stringToCalendar(String day) {
	    String strYear = "";
	    String strMonth = "";
	    String strDate = "";
	    strYear = day.substring(0, 4);
	    strMonth = day.substring(4, 6);
	    strDate = day.substring(6, 8);
	    int iYear = Integer.parseInt(strYear);
	    int iMonth = Integer.parseInt(strMonth) - 1;
	    int iDate = Integer.parseInt(strDate);
	    Calendar cz_Tmp = Calendar.getInstance();
	    if(day.length() <= 8) {
	        cz_Tmp.set(iYear, iMonth, iDate);
	    } else {
	        day = day + "000000";
	        String strHour = day.substring(8, 10);
	        String strMin = day.substring(10, 12);
	        String strSec = day.substring(12, 14);
	        int iHour = Integer.parseInt(strHour);
	        int iMin = Integer.parseInt(strMin);
	        int iSec = Integer.parseInt(strSec);
	        cz_Tmp.set(iYear, iMonth, iDate, iHour, iMin, iSec);
	    }
	
	    return cz_Tmp;
	}
	
	public static String add(String day, int i, int addNum) {
	    Date d = stringToDate(day);
	    Calendar cdar = Calendar.getInstance();
	    cdar.clear();
	    cdar.setTime(d);
	    cdar.add(i, addNum);
	    return getFormalYear(cdar) + getFormalMonth(cdar) + getFormalDay(cdar);
	}
	
	
	public static String getToDay(){

		return getCurrentDateAsString();
		
	}
	
	public static String getNowTime(){
		
		return getCurrentTimeAsString();
		
	}
	
}
