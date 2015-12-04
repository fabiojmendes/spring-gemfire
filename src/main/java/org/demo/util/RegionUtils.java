package org.demo.util;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.gemfire.mapping.Region;
import org.springframework.util.StringUtils;

/**
 * @author Fabio Mendes <fabio.mendes@navita.com.br> Nov 26, 2015
 *
 */
public abstract class RegionUtils {

	/**
	 * @param class1
	 * @return
	 */
	public static String getRegionName(Class<?> clazz) {
		String annotationValue = AnnotationUtils.findAnnotation(clazz, Region.class).value();
		return StringUtils.hasText(annotationValue) ? annotationValue : clazz.getSimpleName();
	}

}
