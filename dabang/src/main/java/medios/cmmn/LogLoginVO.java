package medios.cmmn;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LogLoginVO  extends MediosAbstractVO {

	private static final long serialVersionUID = -3541498924199109652L;

	private String loginDate;
	private String sid;
	private String serverIp;
	private String userIp;
	private String userCd;
}
