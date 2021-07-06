package medios.cmmn.collection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.NativeWebRequest;

import medios.cmmn.MediosSaveVO;
import medios.cmmn.constants.CmmnConstants;
/*import medios.cmmn.mybatis.NameUtil;
import medios.cmmn.util.GeneralUtils;*/
import medios.cmmn.util.MapUtil;
import medios.cmmn.util.MediosMessageSourceHelper;
import medios.cmmn.util.RequestUtil;

//@Slf4j
public class MMultiData {

	private static final Logger LOGGER = LoggerFactory.getLogger(MData.class);
		
//    private Map<String, MData> innerData = new LinkedHashMap<>();
    private Map<String, Object> innerData = new LinkedHashMap<>();

    
    public boolean containsName(final List<ColumnMeta> list, final String name){
        return list.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
    }

	public MMultiData setMaxInactiveInterval(int maxInactiveInterval) {
		return setResult(CmmnConstants.MAX_INACTIVE_INTERVAL, maxInactiveInterval);
	}

	/*
	 * public MMultiData setServerInfo(NativeWebRequest webRequest) { MData result =
	 * new MData();
	 * 
	 * HttpServletRequest request = (HttpServletRequest)
	 * webRequest.getNativeRequest();
	 * result.set(NameUtil.toCamelCase(CmmnConstants.REMOTE_ADDR) ,
	 * GeneralUtils.changeLocalAddr(GeneralUtils.getIpAddress(request)));
	 * result.set(NameUtil.toCamelCase(CmmnConstants.LOCAL_NAME) ,
	 * GeneralUtils.changeLocalAddr(GeneralUtils.getLocalHostName()));
	 * result.set(NameUtil.toCamelCase(CmmnConstants.ROUTE_ID) ,
	 * RequestUtil.getRouteID());
	 * 
	 * return setResult(NameUtil.toCamelCase(CmmnConstants.SERVER_INFO), result); }
	 */

	public MMultiData setResult(Object obj) {
		return setResult(CmmnConstants.RESULT, obj);
	}

	@SuppressWarnings("unchecked")
	public MMultiData setResult(String name, Object obj) {
		if(obj instanceof List) {
			MData mData = new MData();
			for(Object o : (List<?>) obj) {
				if(o instanceof Map) {
					mData.addRow((Map<String, Object>) o);
				} else {
					mData.addRow(MapUtil.convertVo2Map(o));
				}
			}
			setResult(name, mData);
		}
		else if(obj instanceof String) {
			innerData.put(name, obj);
		}
		else if(obj instanceof Integer) {
			innerData.put(name, obj);
		}
		else if(obj instanceof Long) {
			innerData.put(name, obj);
		}
		else if(obj instanceof Double) {
			innerData.put(name, obj);
		}
		else if(obj instanceof Float) {
			innerData.put(name, obj);
		}
		else
			innerData.put(name, MapUtil.convertVo2Map(obj));

		return this;
	}

	public MMultiData setResult(MData mData) {
		return setResult(CmmnConstants.RESULT, mData);
	}

	public MMultiData setResult(String name, MData mData) {
		set(name, mData);
		return this;
	}

	public MMultiData setMessage(String msgCode, Object... args) {
		return setMessage(true, msgCode, args);
	}

	public MMultiData setMessage(boolean isSucc, String msgCode, Object... args) {
		MData result = new MData();
		
		String msgCd	= null;
		String message	= null;
		
		if(args.length == 1 && args[0] instanceof MediosSaveVO) {
			args = ((MediosSaveVO) args[0]).getArray();
		} else if(args.length == 1 && args[0] instanceof String) {
			Field[] fields = CmmnConstants.class.getFields();
			for(Field field : fields) {
				if(field.getName().startsWith("JSON_ERR_CD_")) {
					try {
						if(field.get(CmmnConstants.class).equals(args[0])) {
							msgCd = args[0].toString();
							break;
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						LOGGER.error("setMessage : {}", e.getCause(), e);
					}
				}
			}
		}
		
		if(msgCode != null) {
			message = MediosMessageSourceHelper.getMessage(msgCode, args);
			if(message == null || msgCode.equals(message)) {
				message = MediosMessageSourceHelper.getMessage(msgCode + (isSucc?".succ":".fail"), args);
				if(message != null && message.equals(msgCode + (isSucc?".succ":".fail"))) message = null;
			}
		}
		
		result.put(CmmnConstants.STATUS, (isSucc?CmmnConstants.SUCCESS:CmmnConstants.ERROR));
		result.put(CmmnConstants.MESSAGE, message!=null?message:msgCode);
		if(msgCd != null) result.put(CmmnConstants.MESSAGE_CD, msgCd);
		
		innerData.put(CmmnConstants.MESSAGE_KEY, result);

		return this;
	}

	public MMultiData setPageInfo(MData pageInfo) {
		return setPageInfo(CmmnConstants.RESULT_PAGE_INFO, pageInfo);
	}
	public MMultiData setPageInfo(String name, MData pageInfo) {
		innerData.put(name, pageInfo);
		return this;
	}
   
    public void set(String name, MData pData) {
    	
    	MData mData = (MData)pData.clone();

    	if (mData.getName() == null || !mData.getName().equals(name)) {
    		mData.setName(name);
    	}

    	List<ColumnMeta> newColumnMetas = new ArrayList<>();
    	
    	//Data媛� ���쇰㈃, MetaData瑜� ��寃���怨�, ���쇰㈃, 洹몃�� Skip
    	if(mData.size() >0){
    		ValueRow firstRow = mData.getRow(0);
    		Set<String> keys = firstRow.getKeys();
    		
    		MMetaData bmData = mData.getMMetaData();
    		List<ColumnMeta> columnMetas =  bmData.getColumnMetas();
    		
    		if(keys.size() != bmData.getColumnCount()){
    			for (String key : keys) {
    				//���쇰㈃...
    				if(!this.containsName(columnMetas, key)){
    					newColumnMetas.add(new ColumnMeta(key, firstRow.getValueType(key), 0));
    				}
    			}
    		}
    		
    		bmData.Add(newColumnMetas);
    		mData.setMMetaData(bmData);
    	}

        innerData.put(name, mData);
        
    }

    public Object getObject(String name) {
        return innerData.get(name);
    }

    public MData get(String name) {
        return (MData)innerData.get(name);
    }
    
    public MData get(int index){
    	if(index <0 || index > innerData.size()){
    		return null;
    	}
    	return (MData)innerData.values().toArray()[index];
    }

    public Set<String> getMDataNames() {
        return innerData.keySet();
    }

    public int getDataCount() {
        Set<String> var1 = innerData.keySet();
        
        Iterator<String> var2 = var1.iterator();
        if(var2.hasNext()) {
            Object var3 = var2.next();
            return ((MData)innerData.get(var3)).size();
        } else {
            return 0;
        }
    }
    
    public int getDataCount(Object var1) {
    	return innerData.containsKey(var1)?((MData)innerData.get(var1)).size():0;
    }

    public void dump(Logger logger) {
        Set<String> keys = innerData.keySet();
        for (String key : keys) {
            ((MData)innerData.get(key)).dumpTable(logger);
        }
    }
    
    //=================================================================================//
    // Etc Method Start
    //=================================================================================//
    public int size() {
        return innerData.size();
    }
    
    public void clear() {
    	innerData.clear();
    }

	public void addMData(String var1, Object var2) {
    	
        MData mData;
        MData pData = (MData)var2;
        
        if(!innerData.containsKey(var1)) {
        	this.set(var1, (MData)var2);
        } else {
            mData = (MData) innerData.get(var1);
            for (int i = 0 ; i < pData.size() ; i++) {
                mData.addRow(pData.getRow(i));
            }
        }
        
    }

	@Override
	public String toString() {
		return innerData.toString();
	}

	public boolean isMessage() {
		return innerData.get(CmmnConstants.MESSAGE_KEY)!=null?true:false;
	}

	public boolean isResult() {
		return innerData.get(CmmnConstants.RESULT)!=null?true:false;
	}
}
