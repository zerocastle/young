package medios.cmmn.collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import com.google.common.collect.ComparisonChain;
//import com.google.common.collect.FluentIterable;

import medios.cmmn.exception.BizException;
import medios.cmmn.util.DateHelper;
import medios.cmmn.util.MapUtil;

//@Slf4j
public class MData extends LinkedHashMap<Object, Object> implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(MData.class);

	private static final long serialVersionUID = -4476885127029465716L;
	public static final int ORDER_ASC = 1;
	public static final int ORDER_DESC = 2;
	public static final String SHORT_DATE = "yyMMdd";
	public static final String LONG_DATE = "yyyyMMdd";
	public static final String SHORT_TIME = "HHmm";
	public static final String LONG_TIME = "HHmmss";
	public static final String SHORT_DATETIME = "yyMMddHHmm";
	public static final String LONG_DATETIME = "yyyyMMddHHmm";
	public static final String LONG_DATETIMES = "yyyyMMddHHmmss";
	private List<ValueRow> rows = new ArrayList<ValueRow>();

	private boolean nullToInitialize = true;
	private String name;
	private MMetaData metaData;

	// =================================================================================//
	// Initialize Method Start
	// =================================================================================//

	public MData() {
	}

	public MData(ValueRow row) {
		this.rows.add(new ValueRow(row));
	}

	public MData(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getKeys() {
		if (this.rows.size() == 0) {
			return null;
		}
		ValueRow firstRow = (ValueRow) getRow(0);
		return firstRow.getKeys();
	}

	/*
	 * public String[] getKeysToArray() { if (this.rows.size() == 0) { return null;
	 * } ValueRow firstRow = (ValueRow) getRow(0); Set<String> keys =
	 * firstRow.getKeys(); return (String[])
	 * FluentIterable.from(keys).toArray(String.class); }
	 */

	public MMetaData getMMetaData() {
		if (this.metaData != null)
			return this.metaData;
		if (this.rows.isEmpty()) {
			return new MMetaData();
		}
		if (this.rows.size() == 1) {
			ValueRow firstRow = (ValueRow) getRow(0);
			Set<String> keys = firstRow.getKeys();
			List<ColumnMeta> columnMetas = new ArrayList<ColumnMeta>();
			for (String key : keys) {
				columnMetas.add(new ColumnMeta(key, firstRow.getValueType(key), 0));
			}
			return new MMetaData(columnMetas);
		}
		int columnCount = 0;
		Set<String> allColumes = new HashSet<String>();
		HashMap<String, Class<?>> columnValueType = new HashMap<String, Class<?>>();

		for (int row = 0; row < this.rows.size(); row++) {
			ValueRow firstRow = (ValueRow) getRow(row);
			Set<String> keys = firstRow.getKeys();

			allColumes.addAll(keys);

			if (columnCount < allColumes.size()) {
				for (String key : allColumes) {
					columnValueType.put(key, firstRow.getValueType(key));
				}
				columnCount = allColumes.size();
			}
		}

		List<ColumnMeta> columnMetas = new ArrayList<ColumnMeta>();
		for (String key : allColumes) {
			columnMetas.add(new ColumnMeta(key, (Class<?>) columnValueType.get(key), 0));
		}
		return new MMetaData(columnMetas);
	}

	public MData setMMetaData(MMetaData metaData) {
		this.metaData = metaData;
		return this;
	}

	public boolean hasMMetaData() {
		return (this.metaData != null);
	}

	// =================================================================================//
	// Row Method Start
	// =================================================================================//
	public MData addRow(Map<String, Object> row) {
		this.rows.add(new ValueRow(row));
		return this;
	}

	public MData addRow(List<Map<String,Object>> rows) {
		if(rows != null && rows.size() > 0) {
			for(Map<String,Object> row : rows) {
				addRow(new ValueRow(row));
			}
		}
		return this;
	}

	public MData addRow(ValueRow valueRow) {
		this.rows.add(valueRow);
		return this;
	}

	public MData addMData(MData pData) {
//		ValueRow nRow = new ValueRow();

		for (int row = 0; row < pData.size(); row++) {
			MData mData = new MData(pData.getRow(row));
			this.rows.add(mData.getRow(0));
		}

		setMMetaData(getMMetaData());
		return this;
	}
	public MData addMData(int index, MData pData) {
//		ValueRow nRow = new ValueRow();

		for (int row = 0; row < pData.size(); row++) {
			MData mData = new MData(pData.getRow(row));
			this.rows.add(index, mData.getRow(0));
		}

		setMMetaData(getMMetaData());
		return this;
	}	

	public ValueRow getRow(int rowIdx) {
		if(this.rows != null && this.rows.size() > rowIdx)
			return (ValueRow) this.rows.get(rowIdx);
		else
			return new ValueRow();
	}

	// =================================================================================//
	// getString Method Start
	// =================================================================================//
	private String checkStringVlaue(String value) {
		if (this.nullToInitialize) {
			return Objects.toString(value, "");
		}
		return value;
	}

	public String getString(String key) {
		String value = getRow(0).getString(key);
		return checkStringVlaue(value);
	}

	public String getString(int index, String key) {
		String value = getRow(index).getString(key);
		return checkStringVlaue(value);
	}

	public String getString(String key, int index) {
		String value = getRow(index).getString(key);
		return checkStringVlaue(value);
	}

	public String getString(String key, String defaultValue) {
		String value = getRow(0).getString(key, defaultValue);
		return checkStringVlaue(value);
	}

	public String getString(int index, String key, String defaultValue) {
		String value = getRow(index).getString(key, defaultValue);
		return checkStringVlaue(value);
	}

	public String getDate(String key, String DateFormat) {
		String value = getRow(0).getString(key);
		value = DateHelper.formatter(value, DateFormat);
		return checkStringVlaue(value);
	}

	public String getDate(int index, String key, String DateFormat) {
		String value = getRow(index).getString(key);
		value = DateHelper.formatter(value, DateFormat);
		return checkStringVlaue(value);
	}

	public String getDate(String key, int index, String DateFormat) {
		String value = getRow(index).getString(key);
		value = DateHelper.formatter(value, DateFormat);
		return checkStringVlaue(value);
	}

	public String getDate(String key, String defaultValue, String DateFormat) {
		String value = getRow(0).getString(key, defaultValue);
		value = DateHelper.formatter(value, DateFormat);
		return checkStringVlaue(value);
	}

	public String getDate(int index, String key, String defaultValue, String DateFormat) {
		String value = getRow(index).getString(key, defaultValue);
		value = DateHelper.formatter(value, DateFormat);
		return checkStringVlaue(value);
	}

	private Object checkObjectVlaue(Object value) {
		if (this.nullToInitialize) {
			return Objects.toString(value, "");
		}
		return value;
	}

	public Object getObject(String key) {
		Object value = getRow(0).getObject(key);
		return checkObjectVlaue(value);
	}

	public Object getObject(int index, String key) {
		Object value = getRow(index).getObject(key);
		return checkObjectVlaue(value);
	}

	public Object getObject(String key, int index) {
		Object value = getRow(index).getObject(key);
		return checkObjectVlaue(value);
	}

	public Object getObject(String key, Object defaultValue) {
		Object value = getRow(0).getObject(key, defaultValue);
		return checkObjectVlaue(value);
	}

	public Object getObject(int index, String key, Object defaultValue) {
		Object value = getRow(index).getObject(key, defaultValue);
		return checkObjectVlaue(value);
	}

	public List<Object> getList(String key) {
		List<Object> lstObj = new ArrayList<Object>();
		for(int i=0; i<size(); i++) {
			lstObj.add(getRow(i).getObject(key));
		}
		return lstObj;
	}

	private Object checkBlobVlaue(Object value) {
		if (this.nullToInitialize) {
			if (Objects.isNull(value)) {
				return "";
			}
			return value;
		}

		return value;
	}

	private static final int BUFFER_SIZE = 1024;
	public static String convertClobToString(Object clob) {
		StringBuffer sb = new StringBuffer();
		
		if (clob != null) {
			int len = 0;
			char[] buffer = new char[BUFFER_SIZE];
			BufferedReader reader = null;
			
			try {
				reader = new BufferedReader(((Clob) clob).getCharacterStream());
				while ((len = reader.read(buffer)) >= 0) {
					sb.append(buffer, 0, len);
				}
				
			} catch (IOException | SQLException e) {
				LOGGER.error("convertClobToString : {}", e.getCause(), e);
				throw new BizException(e.getMessage(), e);
			} finally {
				if (reader != null) try {  reader.close(); reader = null; } catch (IOException e) {};
			}
		}
		
		return sb.toString();
	}
	public static String convertBlobToBase64(Object blob) {
		if(blob == null) return "";
		
		if(blob instanceof byte[]) {
			return Base64Utils.encodeToString((byte[]) blob);
		} else {
			return blob.toString();
		}
	}
	
	private String checkClobVlaue(Object clob) {
		return convertClobToString(clob);
	}

	private String encodeBase64(Object value) {
		return convertBlobToBase64(value);
	}

	public String getClob(String key) {
		return getClob(0, key, null);
	}

	public String getClob(int index, String key) {
		return getClob(index, key, null);
	}

	public String getClob(String key, int index) {
		return getClob(index, key, null);
	}

	public String getClob(String key, Object defaultValue) {
		return getClob(0, key, defaultValue);
	}

	public String getClob(int index, String key, Object defaultValue) {
		Object value = getRow(index).getObject(key, defaultValue);
		return checkClobVlaue(value);
	}

	public Object getBlob(String key) {
		return getBlob(0, key, null);
	}

	public Object getBlob(int index, String key) {
		return getBlob(index, key, null);
	}

	public Object getBlob(String key, int index) {
		return getBlob(index, key, null);
	}

	public Object getBlob(String key, Object defaultValue) {
		return getBlob(0, key, defaultValue);
	}

	public Object getBlob(int index, String key, Object defaultValue) {
		Object value = getRow(index).getObject(key, defaultValue);
		return checkBlobVlaue(value);
	}
	
	public String getBlobToBase64(String key) {
		return getBlobToBase64(0, key, null);
	}

	public String getBlobToBase64(int index, String key) {
		return getBlobToBase64(index, key, null);
	}

	public String getBlobToBase64(String key, int index) {
		return getBlobToBase64(index, key, null);
	}

	public String getBlobToBase64(String key, Object defaultValue) {
		return getBlobToBase64(0, key, defaultValue);
	}

	public String getBlobToBase64(int index, String key, Object defaultValue) {
		return encodeBase64(getBlob(index, key, defaultValue));
	}

	public int getInt(String key) {
		return getRow(0).getInt(key);
	}

	public int getInt(int index, String key) {
		return getRow(index).getInt(key);
	}

	public int getInt(String key, int defaultValue) {
		return getRow(0).getInt(key, defaultValue);
	}

	public int getInt(int index, String key, int defaultValue) {
		return getRow(index).getInt(key, defaultValue);
	}

	public long getLong(String key) {
		return getRow(0).getLong(key);
	}

	public long getLong(int index, String key) {
		return getRow(index).getLong(key);
	}

	public long getLong(String key, int index) {
		return getRow(index).getLong(key);
	}

	public long getLong(String key, long defaultValue) {
		return getRow(0).getLong(key, defaultValue);
	}

	public long getLong(int index, String key, long defaultValue) {
		return getRow(index).getLong(key, defaultValue);
	}

	public double getDouble(String key) {
		return getRow(0).getDouble(key);
	}

	public double getDouble(int index, String key) {
		return getRow(index).getDouble(key);
	}

	public double getDouble(String key, int index) {
		return getRow(index).getDouble(key);
	}

	public double getDouble(String key, double defaultValue) {
		return getRow(0).getDouble(key, defaultValue);
	}

	public double getDouble(int index, String key, double defaultValue) {
		return getRow(index).getDouble(key, defaultValue);
	}

	public float getFloat(String key) {
		return getRow(0).getFloat(key);
	}

	public float getFloat(int index, String key) {
		return getRow(index).getFloat(key);
	}

	public float getFloat(String key, int index) {
		return getRow(index).getFloat(key);
	}

	public float getFloat(String key, float defaultValue) {
		return getRow(0).getFloat(key, defaultValue);
	}

	public float getFloat(int index, String key, float defaultValue) {
		return getRow(index).getFloat(key, defaultValue);
	}

	public Map<String, Object> getRowAsMap(int index) {
		return getRow(index).toMap();
	}
	
	public List<Map<String, Object>> getRowAsMap() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		for(ValueRow row : this.rows) {
			result.add(row.toMap());
		}
		
		return result;
	}

	public MData getRowAsVo(int index) {
		MData mData = new MData(getRow(index));
		mData.setMMetaData(mData.getMMetaData());
		return mData;
	}

	public MData set(String key, String value) {
		setIntenal(key, value);
		return this;
	}

	public MData set(int index, String key, String value) {
		setIntenal(index, key, value);
		return this;
	}

	public MData set(String key, int value) {
		setIntenal(key, Integer.valueOf(value));
		return this;
	}

	public MData set(int index, String key, int value) {
		setIntenal(index, key, Integer.valueOf(value));
		return this;
	}

	public MData set(String key, long value) {
		setIntenal(key, Long.valueOf(value));
		return this;
	}

	public MData set(int index, String key, long value) {
		setIntenal(index, key, Long.valueOf(value));
		return this;
	}

	public MData set(String key, double value) {
		setIntenal(key, Double.valueOf(value));
		return this;
	}

	public MData set(int index, String key, double value) {
		setIntenal(index, key, Double.valueOf(value));
		return this;
	}

	public MData set(String key, float value) {
		setIntenal(key, Float.valueOf(value));
		return this;
	}

	public MData set(int index, String key, float value) {
		setIntenal(index, key, Float.valueOf(value));
		return this;
	}

	public MData set(String key, Object value) {
		setIntenal(key, value);
		return this;
	}

	public MData set(int index, String key, Object value) {
		setIntenal(index, key, value);
		return this;
	}

	private void setIntenal(String key, Object value) {
		if (isEmpty()) {
			addRow(new ValueRow());
		}
		getRow(0).set(key, value);
	}

	private void setIntenal(int index, String key, Object value) {
		getRow(index).set(key, value);
	}

	public int size() {
		return this.rows.size();
	}

	public boolean isEmpty() {
		return this.rows.isEmpty();
	}

	public boolean containsKey(String name) {
		return isEmpty() ? false : (this.nullToInitialize ? ((getObject(name) != "")) : ((getObject(name) != null)));
	}

	public RowType getRowType(int index) {
		return getRow(index).getRowType();
	}

	public void setRowType(int index, RowType rowType) {
		getRow(index).setRowType(rowType);
	}

	// =================================================================================//
	// dumpTable
	// =================================================================================//

	public void dumpTable(Logger logger) {
		if (this.rows.size() > 0) {

			this.metaData = null;
			setMMetaData(getMMetaData());
		}

		if (logger.isDebugEnabled()) {
			logger.debug((new MDataFormatter(this)).format());
		}
	}

	public boolean deleteRow(int index) throws BizException {
		if (this.rows.size() > 0 && this.rows.size() >= index) {
			this.rows.remove(index);
		}
		return true;
	}

	public void setNullToInitialize(boolean nullToInitialize) {
		this.nullToInitialize = nullToInitialize;
	}

	public static class MDataFormatter {
		private final MMetaData metaData;
		private final StringBuilder stringBuilder;
		private MData mdata;
		private int[] colsWidth;
		private int totalWidth;
		private String horizontalLine;

		public MDataFormatter(MData mdata) {
			this.mdata = mdata;
			this.metaData = mdata.getMMetaData();
			this.stringBuilder = new StringBuilder(mdata.size() * 80);
		}

		public String format() {
			formatCaption();
			formatTable();
			return stringBuilder.toString();
		}

		private void formatCaption() {
			stringBuilder.append("MData " + (mdata.getName() != null ? "[" + mdata.getName() + "] " : "") + "dump\n");
		}

		private void formatTable() {
			if (mdata.isEmpty() && (metaData == null || metaData.getColumnCount() == 0)) {
				stringBuilder.append("-------\n" + "no rows\n" + "-------\n");
			} else {
				calculateWitdh();
				horizontalLine = line(totalWidth);
				addLine();
				formatTableHeader();
				addLine();
				formatRows();
				addLine();
			}
		}

		private void addLine() {
			stringBuilder.append(horizontalLine + "\n");
		}

		private void calculateWitdh() {
			int[] colsWidth = new int[metaData.getColumnCount()];
			for (int i = 0; i < metaData.getColumnCount(); i++) {
				colsWidth[i] = metaData.getColumnMetas().get(i).getName().length();
			}
			if (!mdata.isEmpty()) {
				List<ColumnMeta> columnMetas = metaData.getColumnMetas();
				for (int row = 0; row < mdata.size(); row++) {
					for (int col = 0; col < metaData.getColumnCount(); col++) {
						String value = mdata.getString(row, columnMetas.get(col).getName());
						if (value == null) {
							if (colsWidth[col] < 6) { // 'null'�� 湲몄�� 6
								colsWidth[col] = 6;
							}
						} else {
							if (colsWidth[col] < value.length()) {
								colsWidth[col] = value.length();
							}
						}
					}
				}
			}
			int totalWidth = 1;
			for (int width : colsWidth) {
				totalWidth += width + 3;
			}
			this.colsWidth = colsWidth;
			this.totalWidth = totalWidth;

		}

		private String line(int length) {
			String result = "";
			for (int i = 0; i < length; i++) {
				result += "-";
			}
			return result;
		}

		private void formatTableHeader() {
			stringBuilder.append("|");
			for (int i = 0; i < metaData.getColumnMetas().size(); i++) {
				ColumnMeta columnMeta = metaData.getColumnMetas().get(i);
				stringBuilder.append(" " + rpad(columnMeta.getName(), colsWidth[i]) + " |");
			}
			stringBuilder.append("\n");
		}

		private void formatRows() {
			if (mdata.isEmpty())
				stringBuilder.append("| " + rpad("no rows", totalWidth - 4) + " |" + "\n");
			else {
				List<ColumnMeta> columnMetas = metaData.getColumnMetas();
				for (int row = 0; row < mdata.size(); row++) {
					stringBuilder.append("|");
					for (int col = 0; col < metaData.getColumnCount(); col++) {
						String value = mdata.getString(row, columnMetas.get(col).getName());
						if (value == null)
							value = "'null'";
						stringBuilder.append(" ").append(rpad(value, colsWidth[col])).append(" |");
					}
					stringBuilder.append("\n");
				}
			}
		}

		private String rpad(String str, int length) {
			String result = str;
			if (str.length() < length) {
				for (int i = str.length(); i < length; i++) {
					result += " ";
				}
			}
			return result;
		}
	}

	// =================================================================================//
	// Sort Method Start
	// =================================================================================//

	// single fields sort
	public void sort(String keyName, int order) throws BizException {

		String[] aKeyName = { keyName };
		this.sort(aKeyName, order);

	}

	// multi Fields sort
	public void sort(String[] keyName, int order) throws BizException {

		// �곗�댄�곌� ���쇰㈃. �����곕� return;
		if (this.rows.isEmpty())
			return;

		// Asc or Desc ����硫� Error
		if (order != 1 && order != 2) {
			RuntimeException e = new RuntimeException();
			LOGGER.error("convertClobToString : {}", e.getCause(), e);
			throw e;
		}

		// �대�� Key媛� ���쇰㈃, Error
		if (!this.containsKey(keyName[0])) {
			RuntimeException e = new RuntimeException();
			LOGGER.error("convertClobToString : {}", e.getCause(), e);
			throw e;
		}

		// Guava
		Collections.sort(this.rows, new Comparator<ValueRow>() {
			@Override
			public int compare(ValueRow arg0, ValueRow arg1) {

				String leftValue = arg0.getString(keyName[0]);
				String rightValue = arg1.getString(keyName[0]);

				ComparisonChain compator = ComparisonChain.start().compare(leftValue, rightValue);

				for (int i = 1; i < keyName.length; i++) {
					leftValue = arg0.getString(keyName[i]);
					rightValue = arg1.getString(keyName[i]);

					compator = SingleComparisonChain(compator, leftValue, rightValue);
				}

				return compator.result();

			}
		});

		if (order == 2) {
			Collections.reverse(this.rows);
		}

	}

	private ComparisonChain SingleComparisonChain(ComparisonChain cmp, String left, String right) {

		return cmp.compare(left, right);

	}

	// =================================================================================//
	// Sort Method Finish
	// =================================================================================//

	/**
	 * Copies all of the mappings from the specified map to this map. These mappings
	 * will replace any mappings that this map had for any of the keys currently in
	 * the specified map.
	 *
	 * @param m
	 *            mappings to be stored in this map
	 * @throws NullPointerException
	 *             if the specified map is null
	 */

	public void putAll(MData m) {

		MMetaData _metaData = m.getMMetaData();

		if (!rows.isEmpty()) {
			List<ColumnMeta> columnMetas = _metaData.getColumnMetas();
			for (int row = 0; row < m.size(); row++) {
				for (int col = 0; col < _metaData.getColumnCount(); col++) {
					Object value = m.getObject(row, columnMetas.get(col).getName());
					this.set(columnMetas.get(col).getName(), value);
				}
			}
		}
	}

	@Override
	public void clear() {
		rows.clear();
	}
	
	public Object get(Object key) {
		Object val = super.get(key);
		
		if(val == null && this.size() > 0) {
			val = this.getRow(0).toMap().get(key);
		}
		return val;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if(this.keySet().isEmpty()) {
			for(int i=0; i<this.size(); i++) {
				if(sb.length() > 0) sb.append(",");
				sb.append(this.getRow(i).toMap());
			}
			
			sb.insert(0, "[").append("]");
		} else {
			Iterator<?> iter = this.keySet().iterator();
			while(iter.hasNext()) {
				if(sb.length() > 0) sb.append(",");
				Object key = iter.next();
				Object val = this.get(key);
				if(val instanceof List || val instanceof Map) 
					sb.append(key + "=" + val);
				else if(val instanceof String ||
						val instanceof Integer ||
						val instanceof Long ||
						val instanceof Double ||
						val instanceof Float) {
					sb.append(key + "=" + val);
				}
				else
					sb.append(key + "=" + MapUtil.convertVo2Map(val));
			}
			sb.insert(0, "{").append("}");
		}

		return sb.toString();
	}
}
