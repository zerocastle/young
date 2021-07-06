package medios.cmmn;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MediosSaveVO extends MediosAbstractVO {

	private static final long serialVersionUID = 7279640351894209927L;

	private int insert = 0;
	private int update = 0;
	private int delete = 0;
	private int erase = 0;
	private int all = 0;
	private int succ = 0;
	private int fail = 0;
	
	public void addInsert(int cnt) {
		if(cnt > 0) insert++;
		addResult(cnt);
	}
	
	public void addUpdate(int cnt) {
		if(cnt > 0) update++;
		addResult(cnt);
	}
	
	public void addDelete(int cnt) {
		if(cnt > 0) delete++;
		addResult(cnt);
	}
	
	public void addErase(int cnt) {
		if(cnt > 0) erase++;
		addResult(cnt);
	}
	
	private void addResult(int cnt) {
		if(cnt > 0) {
			succ++;
		} else {
			fail++;
		}
		all++;
	}
	
	public boolean isSucc() {
		return succ == all;
	}
	
	public Object[] getArray() {
		return new Object[] {all, succ, fail, insert, update, delete, erase};
	}
}
