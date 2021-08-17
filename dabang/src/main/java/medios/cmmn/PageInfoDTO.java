package medios.cmmn;

import java.util.HashMap;
import java.util.Map;

public class PageInfoDTO {

	private int perPage = 10;
	private int nextToken;
	private int start;
	private int end;

	public PageInfoDTO(int nextToken) {
		// TODO Auto-generated constructor stub
		this.nextToken = nextToken;
	}

	public Map<String, Object> makePageNumber() {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("start", (this.nextToken * this.perPage) - (this.perPage - 1));
		result.put("end", (this.nextToken * this.perPage));
		result.put("nextToken", this.nextToken + 1);
		result.put("perPage", this.perPage);

		return result;

	}

}
