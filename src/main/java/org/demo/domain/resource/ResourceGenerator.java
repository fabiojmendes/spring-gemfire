package org.demo.domain.resource;

import java.util.UUID;

import org.demo.domain.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fábio Jackson Mendes <fabio.mendes@navita.com.br> [May 12, 2015]
 *
 */
@Service
public class ResourceGenerator {

	private ResourceRepository resourceRepository;

	private DomainEventPublisher domainEventPublisher;

	private KeyGenerator keyGenerator;

	@Autowired
	public ResourceGenerator(ResourceRepository resourceRepository, DomainEventPublisher domainEventPublisher, KeyGenerator keyGenerator) {
		this.resourceRepository = resourceRepository;
		this.domainEventPublisher = domainEventPublisher;
		this.keyGenerator = keyGenerator;
	}

	public Resource generate(String name, ResourceType resourceType) {
		Resource resource = resourceType.createResource(UUID.randomUUID(), keyGenerator, domainEventPublisher);
		resource.setName(name);
		return resourceRepository.save(resource);
	}

	/**
	 * @param id
	 * @param name
	 * @return
	 */
	public Resource update(UUID id, String name, ResourceType type) {
		Resource resource = resourceRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		if (!resource.getType().equals(type)) {
			resource = type.createResource(id, keyGenerator, domainEventPublisher);
		}
		resource.setName(name);
		return resourceRepository.save(resource);
	}

	/**
	 * @param id
	 * @return
	 */
	public void delete(UUID id) {
		resourceRepository.delete(id);
	}
}
