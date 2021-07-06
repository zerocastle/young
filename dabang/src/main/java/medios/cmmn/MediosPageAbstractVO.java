/**
 * 
 */
package medios.cmmn;

//import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * Class Name : MediosPageAbstractVO
 * Description : 怨듯�� ���댁� VO �대����
 *               ���댁�泥�由щ�� ���� 湲곕낯 肄���瑜� �ы�⑦����.
 * @author ��猷����고�⑺��-諛�����
 * @since 2019. 9. 6.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 媛����대��(Modification Information) >>
 *
 *   ������      ������           �����댁��
 *   ----------- -------------    ----------------------
 *   2019. 9. 6.  ��猷����고�⑺��-諛�����           理�珥� ����
 * </pre>
 */
@Getter @Setter
public abstract class MediosPageAbstractVO extends MediosAbstractVO {

	private static final long serialVersionUID = 2285787053804365356L;

	/** ���ы���댁� */
    private int currentPageNo = 1;

    /** ���댁�媛��� */
    private int pageUnit = 10;

    /** ���댁��ъ�댁� */
    private int pageSize = 10;

    /** START */
    private int START = 1;

    /** END */
    private int END = 1;

    /** ���댁��� ��肄��� �� */
    private int pageRow = 10;
    
    private int rnum = 0;
    
    private int totalCount = 0;
    
//    PaginationInfo paginationInfo = null;
}
