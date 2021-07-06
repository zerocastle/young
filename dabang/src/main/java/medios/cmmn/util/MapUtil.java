package medios.cmmn.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import medios.cmmn.MediosAbstractVO;
import medios.cmmn.exception.BizException;

/**
 * Class Name : MapUtil
 * Description : Map 공통 유틸 클래스
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
public class MapUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(MapUtil.class);

	/**
	 * list map 을 list vo로 변환
	 * List<Map<String,Object>> -> List<T> 로 변환
	 * 
	 * @param obj
	 * @param type
	 * @return
	 * @throws BizException
	 */
	public static <T extends Object> List<T> convertVo2List(Object obj, Class<T> type) throws BizException {
		ArrayList<T> list = new ArrayList<T>();
		for(Object o : (List<?>) obj) {
			list.add(MapUtil.convertMap2Vo((Map<?,?>) o, type));
		}
		return list;
	}
	
	/**
	 * map -> vo로 변환
	 * 
	 * Map<String,Object> -> T 로 변환
	 * 
	 * @param obj
	 * @param type
	 * @return
	 * @throws BizException
	 */
	public static final <T extends Object> T convertMap2Vo(Object obj, Class<T> type) throws BizException {
		return convertMap2Vo((Map<?,?>) obj, type);
	}
	
	private static <T extends Object> T convertMap2Vo(Map<?, ?> map, Class<T> type) throws BizException {
		T vo = null;
		
		try {
			vo = type.newInstance();
		} catch(Exception e) {
			LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
			throw new BizException(e);
		}
		setMap2VoAttr(map, vo);
		
		return vo;
	}

	private static void setMap2VoAttr(Map<?, ?> map, Object vo) {
		if(map != null && !map.isEmpty()) {
			Iterator<?> keys = map.keySet().iterator();
			while(keys.hasNext()) {
				String key = keys.next().toString();
				Object val = map.get(key);
				String methodName = "set" + key.substring(0,1).toUpperCase() + key.substring(1);
				
				try {
					Method method = null;
					if(val.getClass().getSuperclass().equals(Number.class)) {
						try {
							if(val.getClass().equals(Double.class)) {
								method = vo.getClass().getMethod(methodName, new Class[] {double.class});
							} else if(val.getClass().equals(Long.class)) {
								method = vo.getClass().getMethod(methodName, new Class[] {long.class});
							} else if(val.getClass().equals(Byte.class)) {
								method = vo.getClass().getMethod(methodName, new Class[] {byte.class});
							} else if(val.getClass().equals(Float.class)) {
								method = vo.getClass().getMethod(methodName, new Class[] {float.class});
							} else if(val.getClass().equals(Integer.class)) {
								method = vo.getClass().getMethod(methodName, new Class[] {int.class});
							} else if(val.getClass().equals(Short.class)) {
								method = vo.getClass().getMethod(methodName, new Class[] {short.class});
							}
						} catch(Exception e) {
							LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
							method = vo.getClass().getMethod(methodName, new Class[] {val.getClass()});
						}
					} else {
						method = vo.getClass().getMethod(methodName, new Class[] {val.getClass()});
					}
					
					method.invoke(vo, val);
					
				} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
					LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
				}
			}
		}		
	}

	public static Map<String,Object> convertVo2Map(Object obj) {
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		List<Field> fields = getFields(obj);
		if(fields != null && fields.size() > 0 && map != null) {
			for(Field field : fields) {
				if(!Modifier.isStatic(field.getModifiers())) {
					StringBuilder key = new StringBuilder(field.getName());
					key.setCharAt(0, Character.toUpperCase(key.charAt(0)));
					Object val = null;
					try {
						//Method method = obj.getClass().getDeclaredMethod("get" + key, new Class[] {});
						Method method = obj.getClass().getMethod("get" + key, new Class[] {});
						val = method.invoke(obj, new Object[] {});
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						try {
							Method method = obj.getClass().getDeclaredMethod("get" + key, new Class[] {});
							val = method.invoke(obj, new Object[] {});
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
							LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
						}
					}
					
					if("getCrtPassWd".equals("get" + key)) {
						map.put(field.getName(), (val!=null?val:"").toString().trim().isEmpty()?"0":"1");
					} else {
						map.put(field.getName(), val!=null?val:"");
					}
				}
			}
		}
		
		return map;
	}

	private static List<Field> getFields(Object obj) {
		return getFields(obj.getClass());
	}
	
	private static List<Field> getFields(Class<? extends Object> clazz) {
		List<Field> lst = new ArrayList<Field>();
		
		if(clazz.getName().equals(MediosAbstractVO.class.getName())) {
			return lst;
		}
		
		lst.addAll(Arrays.asList(clazz.getDeclaredFields()));

		Class<?> superClazz = clazz.getSuperclass();
		if(superClazz != null && !superClazz.getName().equals(MediosAbstractVO.class.getName())) {
			lst.addAll(getFields(clazz.getSuperclass()));
		}

		return lst;
	}
	
	/**
	 * List 객체를 문자열 배열로 변환해서 반환한다.
	 * 
	 * @param resultList 서비스의 메소드 실행 결과를 담은 List 객체
	 * @return String[] 문자열 배열
	 */
	public static String[] hashMapValuesToArray(List<HashMap <String, String>> resultList) {
		if (resultList.size() > 0) {
			String strArr[] = new String[resultList.size() * resultList.get(0).size()];
			try {
				int idx = 0;
				for (HashMap<String, String> hashMap : resultList) {
					for (String value : hashMap.values()) {
						if (value != null) {
							strArr[idx] = value;
						} else {
							strArr[idx] = "";
						}
						idx++;
					}
				}
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage() + " : {}", ex.getCause(), ex);
			}
			return strArr;
		} else {
			return null;
		}
	}
	public static String[] hashMapValuesToArray(List<HashMap <String, String>> resultList, String keyMap) {
		if (keyMap == null || keyMap.equals("")) return hashMapValuesToArray(resultList);
		
		if (resultList.size() > 0) {
			String[] keyArr = keyMap.split(",");
			String strArr[] = new String[resultList.size() * keyArr.length];
			try {
				int idx = 0;
				for (HashMap<String, String> hashMap : resultList) {
					for (int i=0; i < keyArr.length; i++) {
						String value = hashMap.get(keyArr[i]);
						if (value != null) {
							strArr[idx] = value;
						} else {
							strArr[idx] = "";
						}
						idx++;
					}
				}
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage() + " : {}", ex.getCause(), ex);
			}
			return strArr;
		} else {
			return null;
		}
	}


}
