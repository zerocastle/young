package medios.cmmn.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import medios.cmmn.collection.MData;
import medios.cmmn.collection.ValueRow;
import medios.cmmn.exception.BizException;

//@Slf4j
public class MDataUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(MDataUtil.class);

	private static <T extends Object> void setValueRow2VoAttr(ValueRow row, T vo) {
		Map<?, ?> map = row.toMap();
		
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
	
	private static <T extends Object> T convertValueRow2Vo(ValueRow row, Class<T> type) throws BizException{
		T vo = null;
		
		try {
			vo = type.newInstance();
		} catch(Exception e) {
			LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
			throw new BizException(e);
		}
		
		setValueRow2VoAttr(row, vo);
		
		return vo;
	}

	public static final <T extends Object> List<T> convertMData2VoList(MData mData, Class<T> type) throws BizException {
		List<T> list = new ArrayList<T>();
		if(mData != null) {
			for(int i=0; i<mData.size(); i++) {
				ValueRow row = mData.getRow(i);
				list.add(convertValueRow2Vo(row, type));
			}
		}
		
		return list;
	}

	public static final <T extends Object> T convertMData2Vo(MData mData, Class<T> type) throws BizException {
		T vo = null;
		
		if(mData != null && mData.size() > 0) {
			vo = convertValueRow2Vo(mData.getRow(0), type);
		}
		
		return vo;
	}

}
