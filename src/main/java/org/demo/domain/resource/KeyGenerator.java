package org.demo.domain.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author  Fabio Mendes <fabio.mendes@navita.com.br> Dec 4, 2015
 */
@Component
@ConfigurationProperties(prefix = "application")
public class KeyGenerator {

	private int bufferSize;

	/**
	 * @param bufferSize
	 */
	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	/**
	 * @return
	 */
	public String generate() {
		List<Double> buffer = new ArrayList<>(bufferSize);
		for (int i = 0; i < bufferSize; i++) {
			buffer.add(0.0);
		}
		Random rnd = new Random(new Date().getTime());
		List<Double> list = buffer.stream()
				.map((d) -> rnd.nextDouble())
				.map(Math::sqrt)
				.sorted()
				.collect(Collectors.toList());

		return DigestUtils.sha1Hex(list.toString());
	}

}
