package medios.cmmn.collection;

import java.util.Arrays;
import java.util.List;

public class MMetaData {
	
    private ColumnMeta[] columnMetas;

    public MMetaData() {
        columnMetas = new ColumnMeta[0];
    }

    public MMetaData(ColumnMeta[] columnMetas) {
        this.columnMetas = columnMetas;
    }

    public MMetaData(List<ColumnMeta> columnMetaList) {
        this.columnMetas = new ColumnMeta[columnMetaList.size()];
        columnMetaList.toArray(this.columnMetas);
    }

    public List<ColumnMeta> getColumnMetas() {
        return Arrays.asList(columnMetas);
    }

    public int getColumnCount() {
        return columnMetas.length;
    }
    
    public void Add(List<ColumnMeta> pColumnMetas){
    	
    	ColumnMeta[] oldColumnMetas = this.columnMetas;
    	ColumnMeta[] paraColumnMetas = new ColumnMeta[pColumnMetas.size()];
    	ColumnMeta[] newColumnMetas = new ColumnMeta[oldColumnMetas.length + paraColumnMetas.length];
    	
    	//기존값을 모두 옮기고...
    	System.arraycopy(oldColumnMetas, 0, newColumnMetas, 0, this.columnMetas.length);
    	pColumnMetas.toArray(paraColumnMetas);
    	
    	//추가로 전달되어온 MetaData를 넣어준다.
    	System.arraycopy(paraColumnMetas, 0, newColumnMetas, oldColumnMetas.length, paraColumnMetas.length);
    	
    	this.columnMetas = newColumnMetas;
    	    	    	
    }
    
}
