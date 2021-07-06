package medios.cmmn.collection;

public class ColumnMeta {

    private String name;
    private Class<?> type;
    private int size;

    public ColumnMeta(String name, Class<?> type, int size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public int getSize() {
        return size;
    }
    
    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof ColumnMeta)
        {
            sameSame = this.name == ((ColumnMeta)object).name;
        }

        return sameSame;
    }
    
}
