/**
 * 
 */
package medios.cmmn;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import medios.cmmn.util.MapUtil;

/**
 * Class Name : MediosAbstractVO
 * Description : 공통 VO 클레스
 *               VO를 Map로 출력기능 추가
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
public abstract class MediosAbstractVO implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(MediosAbstractVO.class);

	private static final long serialVersionUID = 6489224380239370226L;

	private void setFieldValue(Map<String,Object> map, Class<?> cls, Field[] fields) {
		if(fields != null && fields.length > 0 && map != null) {
			for(Field field : fields) {
				if(!Modifier.isStatic(field.getModifiers())) {
					StringBuilder key = new StringBuilder(field.getName());
					key.setCharAt(0, Character.toUpperCase(key.charAt(0)));
					Object val = null;
					try {
						Method method = cls.getDeclaredMethod("get" + key, new Class[] {});
						val = method.invoke(this, new Object[] {});
						
						if(		val != null &&
								!(val instanceof String) &&
								!(val instanceof Double) &&
								!(val instanceof Long) &&
								!(val instanceof Byte) &&
								!(val instanceof Float) &&
								!(val instanceof Integer) &&
								!(val instanceof Short) &&
								!(val instanceof Map) &&
								!(val instanceof List) &&
								(val instanceof MediosAbstractVO)) {
							
							val = MapUtil.convertVo2Map(val);
						}

					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
					}
					
					map.put(field.getName(), val!=null?val:"");
				}
			}
		}
	}
	
	/**
	 * VO를 Map형태로 전환하여 String 출력
	 * result map 데이터 
	 */
	@Override
	public String toString() {
		Map<String,Object> map = new HashMap<String,Object>();
		
		Class<?> cls = this.getClass();
		
		while(!cls.equals(MediosAbstractVO.class)) {
			setFieldValue(map, cls, cls.getDeclaredFields());
			cls = cls.getSuperclass();
		}
		return map.toString();
	}

}
