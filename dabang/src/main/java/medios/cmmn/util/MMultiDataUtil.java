package medios.cmmn.util;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.json.simple.JSONObject;

import medios.cmmn.MediosAbstractVO;
import medios.cmmn.collection.MData;
import medios.cmmn.collection.MMultiData;
import medios.cmmn.collection.ValueRow;
import medios.cmmn.constants.CmmnConstants;

/**
 * Class Name : MMultiDataUtil
 * Description : Controller���� 諛������� MMultiData�� ���� 吏��� �대����
 * @author ��猷����고�⑺��-諛�����
 * @since 2019. 9. 25.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 媛����대��(Modification Information) >>
 *
 *   ������      ������           �����댁��
 *   ----------- -------------    ----------------------
 *   2019. 9. 25.  ��猷����고�⑺��-諛�����           理�珥� ����
 * </pre>
 */
public class MMultiDataUtil {

	public static MMultiData setMaxInactiveInterval(int maxInactiveInterval) {
		return new MMultiData().setMaxInactiveInterval(maxInactiveInterval);
	}

	public static MMultiData setResult(Object obj) {
		return new MMultiData().setResult(CmmnConstants.RESULT, obj);
	}

	public static MMultiData setResult(String name, Object obj) {
		return new MMultiData().setResult(name, obj);
	}

	public static MMultiData setResult(MData mData) {
		return new MMultiData().setResult(CmmnConstants.RESULT, mData);
	}

	public static MMultiData setResult(String name, MData mData) {
		return new MMultiData().setResult(name, mData);
	}
	
	public static MMultiData setMessage(String msgCode, Object... args) {
		return new MMultiData().setMessage(true, msgCode, args);
	}

	public static MMultiData setMessage(boolean isSucc, String msgCode, Object... args) {
		return new MMultiData().setMessage(isSucc, msgCode, args);
	}
	
	public static MMultiData setPageInfo(MData pageInfo) {
		return new MMultiData().setPageInfo(pageInfo);
	}
	public static MMultiData setPageInfo(String name, MData pageInfo) {
		return new MMultiData().setPageInfo(name, pageInfo);
	}

	public static String toJSONString(MMultiData mMultiData) {
		return JSONObject.toJSONString(toMap(mMultiData));
	}

	public static Map<String,Object> toMap(MMultiData mMultiData) {
		Map<String,Object> mapParams = new HashMap<String,Object>();
		
		Iterator<String> mmDataIter = mMultiData.getMDataNames().iterator();
		while (mmDataIter.hasNext()) {
			String mmDataKey = mmDataIter.next();
			Object mmDataVal = convertValue(mMultiData.getObject(mmDataKey));
			mapParams.put(mmDataKey, mmDataVal);
		}
		
		return mapParams;
	}
	
	private static Object convertValue(Object pValue) {
		Object rValue = null;
		
		if(pValue != null) {
			if(pValue instanceof MData) {
				MData mData = (MData) pValue;
				if(mData.isEmpty() && !mData.keySet().isEmpty()) {
					Map<Object,Object> row = new HashMap<Object,Object>();
					Iterator<Object> rowKeys = mData.keySet().iterator();
					while(rowKeys.hasNext()) {
						Object rowKey = rowKeys.next();
						Object rowVal = convertValue(mData.get(rowKey));
						row.put(rowKey, Objects.toString(rowVal, ""));
					}
					rValue = row;
					
				} else {
					List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
					for(int i = 0; i < mData.size(); i++) {
						Map<String,Object> row = new HashMap<String,Object>();
						ValueRow valueRow  = mData.getRow(i);
						Iterator<String> rowKeys = valueRow.getKeys().iterator();
						while(rowKeys.hasNext()) {
							String rowKey = rowKeys.next();
							Object rowVal = convertValue(valueRow.getObject(rowKey));
							row.put(rowKey, Objects.toString(rowVal, ""));
						}
						rows.add(row);
					}
					rValue = rows;
				}
				
			} else if(pValue instanceof MediosAbstractVO) {
//				rValue = Objects.toString(MapUtil.convertVo2Map(pValue), "");
			} else if(pValue instanceof Clob) {
				rValue = MData.convertClobToString(pValue);
			} else if(pValue instanceof byte[]) {
				rValue = MData.convertBlobToBase64(pValue);
			} else if(pValue instanceof List) {
				rValue = pValue;
			} else if(pValue instanceof Map) {
				rValue = pValue;
			} else {
				rValue = Objects.toString(pValue, "");
			}
		}
		
		return rValue;
	}

}
