package org.demo.domain.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.demo.domain.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * @author Fábio Jackson Mendes <fabio.mendes@navita.com.br> [May 12, 2015]
 *
 */
@Service
@ConfigurationProperties(prefix = "application")
public class ResourceGenerator {

	private int bufferSize;

	private ResourceRepository resourceRepository;

	private DomainEventPublisher domainEventPublisher;

	@Autowired
	public ResourceGenerator(ResourceRepository resourceRepository, DomainEventPublisher domainEventPublisher) {
		bufferSize = 1024;
		this.resourceRepository = resourceRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public Resource generate(String name) {
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

		String key = DigestUtils.sha1Hex(list.toString());
		Resource resource = new Resource(UUID.randomUUID(), domainEventPublisher);
		resource.setKey(key);
		resource.setName(name);
		return resourceRepository.save(resource);
	}

	/**
	 * @param id
	 * @param name
	 * @return
	 */
	public void update(UUID id, String name) {
		Resource resource = resourceRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		resource.setName(name);
		resourceRepository.save(resource);
	}

	/**
	 * @param id
	 * @return
	 */
	public void delete(UUID id) {
		resourceRepository.delete(id);
	}
}
