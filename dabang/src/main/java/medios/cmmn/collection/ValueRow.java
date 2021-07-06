package medios.cmmn.collection;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ValueRow implements Serializable {
	private static final long serialVersionUID = 4055827071522774400L;
	private RowType rowType;
	private Map<String, Object> innerData;

	public ValueRow(Map<String, Object> map) {
		this.rowType = RowType.NORMAL;
		this.innerData = new LinkedHashMap<String, Object>(map);
	}

	public ValueRow(ValueRow row) {
		this.rowType = RowType.NORMAL;
		this.innerData = row.toMap();
		this.rowType = row.getRowType();
	}

	public ValueRow() {
		this.rowType = RowType.NORMAL;
		this.innerData = new LinkedHashMap<String, Object>();
	}

	public Set<String> getKeys() {
		return innerData.keySet();
	}

	private <T> T getOrDefault(String key, T defaultValue, TypeConverter<T> converter) {
		Object value = this.innerData.get(key);
		if (value == null && defaultValue != null) {
			return defaultValue;
		}
		return (T) converter.convert(value);
	}

	public String getString(String key) {
		return (String) getOrDefault(key, null, stringConverter);
	}

	public String getString(String key, String defaultValue) {
		return (String) getOrDefault(key, defaultValue, stringConverter);
	}

	public int getInt(String key) {
		return ((Integer) getOrDefault(key, null, intConverter)).intValue();
	}

	public int getInt(String key, int defaultValue) {
		return ((Integer) getOrDefault(key, Integer.valueOf(defaultValue), intConverter)).intValue();
	}

	public long getLong(String key) {
		return ((Long) getOrDefault(key, null, longConverter)).longValue();
	}

	public long getLong(String key, long defaultValue) {
		return ((Long) getOrDefault(key, Long.valueOf(defaultValue), longConverter)).longValue();
	}

	public double getDouble(String key) {
		return ((Double) getOrDefault(key, null, doubleConverter)).doubleValue();
	}

	public double getDouble(String key, double defaultValue) {
		return ((Double) getOrDefault(key, Double.valueOf(defaultValue), doubleConverter)).doubleValue();
	}

	public float getFloat(String key) {
		return ((Float) getOrDefault(key, null, floatConverter)).floatValue();
	}

	public float getFloat(String key, float defaultValue) {
		return ((Float) getOrDefault(key, Float.valueOf(defaultValue), floatConverter)).floatValue();
	}

	public Object getObject(String key) {
		return getOrDefault(key, null, x -> x);
	}

	public Object getObject(String key, Object defaultValue) {
		return getOrDefault(key, defaultValue, x -> x);
	}

	public Map<String, Object> toMap() {
		return new LinkedHashMap<String, Object>(this.innerData);
	}

	public void set(String key, Object value) {
		this.innerData.put(key, value);
	}

	public RowType getRowType() {
		return this.rowType;
	}

	public void setRowType(RowType rowType) {
		this.rowType = rowType;
	}

	public Class<?> getValueType(String key) {
		Object value = getObject(key);
		if (value == null) {
			return null;
		}
		return value.getClass();
	}

	private static TypeConverter<String> stringConverter = new TypeConverter<String>() {
		public String convert(Object value) {
			if (value == null)
				return null;
			return value.toString();
		}
	};

	private static TypeConverter<Integer> intConverter = new TypeConverter<Integer>() {
		public Integer convert(Object value) {
			if (value == null)
				return Integer.valueOf(0);
			if (value instanceof Number) {
				return Integer.valueOf(((Number) value).intValue());
			}
			return Integer.valueOf(Integer.parseInt(value.toString()));
		}
	};

	private static TypeConverter<Long> longConverter = new TypeConverter<Long>() {
		public Long convert(Object value) {
			if (value == null)
				return Long.valueOf(0L);
			if (value instanceof Number) {
				return Long.valueOf(((Number) value).longValue());
			}
			return Long.valueOf(Long.parseLong(value.toString()));
		}
	};

	private static TypeConverter<Double> doubleConverter = new TypeConverter<Double>() {
		public Double convert(Object value) {
			if (value == null)
				return Double.valueOf(0.0D);
			if (value instanceof Number) {
				return Double.valueOf(((Number) value).doubleValue());
			}
			return Double.valueOf(Double.parseDouble(value.toString()));
		}
	};

	private static TypeConverter<Float> floatConverter = new TypeConverter<Float>() {
		public Float convert(Object value) {
			if (value == null)
				return Float.valueOf(0.0F);
			if (value instanceof Number) {
				return Float.valueOf(((Number) value).floatValue());
			}
			return Float.valueOf(Float.parseFloat(value.toString()));
		}
	};

	public void clear() {
		this.innerData.clear();
	}

	private static interface TypeConverter<T> {
		T convert(Object param1Object);
	}
}
