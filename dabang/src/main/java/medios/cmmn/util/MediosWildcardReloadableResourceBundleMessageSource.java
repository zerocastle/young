package medios.cmmn.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * Class Name : MediosWildcardReloadableResourceBundleMessageSource
 * Description : 관리를 위하여 EgovWildcardReloadableResourceBundleMessageSource 복사하여 만듬
 *               다국어 properties 파일을 팩키지 구조의 폴더로 읽어드리는 MessageSource
 * @author 의료원연합회-박정호
 * @since 2019. 9. 2.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *   ----------- -------------    ----------------------
 *   2019. 9. 2.  의료원연합회-박정호           최초 생성
 * </pre>
 */
public class MediosWildcardReloadableResourceBundleMessageSource extends
        org.springframework.context.support.ReloadableResourceBundleMessageSource {

	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    /**
     * resource basename을 * 등으로 읽어서 해당하는 부분을 전체선택을 위한 클래스
     * 
     * @param basenames
     */
    public void setMediosBasenames(String... basenames) {
        if (basenames != null) {
            List<String> baseNames = new ArrayList<String>();
            for (int i = 0; i < basenames.length; i++) {
            	
                String basename = StringUtils.trimToEmpty(basenames[i]);
                if(basename.indexOf("classpath:/") > -1 ){
                	baseNames.add(basename);
                }else if(StringUtils.isNotBlank(basename)) {
                    try {
                    	
                        Resource[] resources = resourcePatternResolver.getResources(basename);

                        for (int j = 0; j < resources.length; j++) {
                            Resource resource = resources[j];
                            String uri = resource.getURI().toString();
                            String baseName = null;
                            
                            if(uri.indexOf(".properties") == -1){continue;}
                            
                            if (resource instanceof FileSystemResource) {
                                baseName = "classpath:" + StringUtils.substringBetween(uri, "/classes/", ".properties");
                                baseName = baseName.substring(0,baseName.indexOf("_"));
                                baseName = baseName.replaceAll("classpath:", "classpath:/");
                                if(baseNames.indexOf(baseName) > -1){continue;};
                                
                            } else if (resource instanceof ClassPathResource) {
                                baseName = StringUtils.substringBefore(uri, ".properties");
                                baseName = baseName.substring(0,baseName.indexOf("_"));
                                baseName = baseName.replaceAll("classpath:", "classpath:/");
                            } else if (resource instanceof UrlResource) {
                                baseName = "classpath:" + StringUtils.substringBetween(uri, ".jar!/", ".properties");
                                baseName = baseName.substring(0,baseName.indexOf("_"));
                                baseName = baseName.replaceAll("classpath:", "classpath:/");
                            }
                            if (baseName != null) {
                                String fullName = processBasename(baseName);
                                baseNames.add(fullName);
                            }
                        }
                    } catch (IOException e) {
                        logger.debug("No message source files found for basename " + basename + ".");
                    }
                }
     
               
            }
            
            logger.debug("MediosWildcardReloadableResourceBundleMessageSource>>basenames>["+baseNames+"}");
            setBasenames(baseNames.toArray(new String[baseNames.size()]));
        }
    }

    private String processBasename(String baseName) {
        String prefix = StringUtils.substringBeforeLast(baseName, "/");
        String name = StringUtils.substringAfterLast(baseName, "/");
        do {
            name = StringUtils.substringBeforeLast(name, "_");
        } while (name.contains("_"));
        return prefix + "/" + name;
    }
}