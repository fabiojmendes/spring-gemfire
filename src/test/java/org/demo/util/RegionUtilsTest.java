package org.demo.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.data.gemfire.mapping.Region;

/**
 * @author Fabio Mendes <fabio.mendes@navita.com.br> Dec 4, 2015
 *
 */
public class RegionUtilsTest {

	@Region("OtherName")
	static class RegionNameSet {}

	@Region
	static class RegionNameNotSet {}

	@Test
	public void testRegionNameSet() {
		assertThat(RegionUtils.getRegionName(RegionNameSet.class), is("OtherName"));
	}

	@Test
	public void testRegionNameNotSet() {
		assertThat(RegionUtils.getRegionName(RegionNameNotSet.class), is("RegionNameNotSet"));
	}

}
