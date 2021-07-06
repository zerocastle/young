package medios.cmmn.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;

import medios.cmmn.exception.BizException;

/**
 * Class Name : FileUtil
 * Description : 파일 공통 유틸 클래스
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
public final class FileUtil {
//	/**
//	 * Logger.
//	 */
//	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
    /**
     * messages-cs.properties
     */
    @Resource(name = "messageSource")
    private MessageSource messageSource;
    
	/**
	 * 생성자 차단.
	 */
	private FileUtil() {
	}

	/**
	 * 파일을 byte[]로 읽기
	 * 
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws BizException
	 */
	public static byte[] getBlobInfo(String filePath, String fileName) throws BizException {

		BufferedInputStream bis = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			if (fileName != null || !"".equals(fileName)) {
				String fileFullPath = filePath + "/" + fileName;
				File file = new File(pathFilter(fileFullPath));
				byte[] buffer = new byte[(int) file.length()];
				bis = new BufferedInputStream(new FileInputStream(file));

				int len = 0;
				while ((len = bis.read(buffer)) >= 0) {
					bos.write(buffer, 0, len);
				}

				return bos.toByteArray();
			}
		} catch (IOException e) {
			throw new BizException(e.getMessage(), e);
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				throw new BizException(e.getMessage(), e);
			}
		}

		return bos.toByteArray();
	}
	
	/**
	 * path filter 상위 폴더로 조회 불가하도록 하는 기능
	 * 
	 * @param filePath
	 * @return
	 * @throws BizException
	 */
	public static String pathFilter(String filePath) throws BizException {
		if (filePath.indexOf("..") != -1) {
			throw new BizException("File path is invalid.");
		}
		
		return filePath;
	}
}
