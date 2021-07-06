/**
 * 
 */
package medios.cmmn;

import lombok.Getter;
import lombok.Setter;

/**
 * Class Name : MediosPageAbstractVO
 * Description : 공통 페이징 VO 클레스
 *               페이징처리를 위한 기본 코드를 포함한다.
 * @author 의료원연합회-박정호
 * @since 2019. 9. 6.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *   ----------- -------------    ----------------------
 *   2019. 9. 6.  의료원연합회-박정호           최초 생성
 * </pre>
 */
@Getter @Setter
public abstract class MediosListAbstractVO extends MediosAbstractVO {

	private static final long serialVersionUID = 2240923910466704760L;

    /** 서버로 전달되는 Request Parameter(DataList)에서 각 행(Row)별 상태값.  */
	private String rowStatus = "";
}
