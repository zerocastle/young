package medios.cmmn.collection;

import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;

public class BeanToMDataConverter {
    public static MData toMData(Object bean) {
        MData mData = new MData();
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(bean);
        PropertyDescriptor[] props = beanWrapper.getPropertyDescriptors();

        ColumnMeta[] columnMetas = new ColumnMeta[props.length];
        for (int i = 0 ; i < props.length ; i++) {
            PropertyDescriptor prop = props[i];
            columnMetas[i] = new ColumnMeta(prop.getName(), prop.getPropertyType(), 0);
        }
        MMetaData metaData = new MMetaData(columnMetas);
        mData.setMMetaData(metaData);

        for (PropertyDescriptor prop : props) {
            String propName = prop.getName();
            Object propValue = beanWrapper.getPropertyValue(propName);
            mData.set(propName, propValue);
        }
        return mData;
    }

}
