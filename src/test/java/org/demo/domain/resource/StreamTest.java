package org.demo.domain.resource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

/**
 * @author Fabio Mendes <fabio.mendes@navita.com.br> Dec 4, 2015
 *
 */
public class StreamTest {

	@Test
	public void testStep() {
		Stream s = new Stream(UUID.randomUUID());
		assertThat(s.getIndex(), is(0));
		s.step();
		assertThat(s.getIndex(), is(1));
		s.reset();
		assertThat(s.getIndex(), is(0));

		assertThat(s.getKey(), is("0"));
	}


}
