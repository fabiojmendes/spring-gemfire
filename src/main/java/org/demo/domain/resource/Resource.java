package org.demo.domain.resource;

import java.util.UUID;

import org.demo.domain.DomainEvent;
import org.demo.domain.DomainEventPublisher;
import org.demo.domain.DomainService;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

@Region
public class Resource {

	@Id
	private UUID id;

	private String key;

	private String name;

	@DomainService
	private transient DomainEventPublisher eventPublisher;

	/**
	 * @param id
	 * @param eventPublisher
	 */
	@PersistenceConstructor
	public Resource(UUID id) {
		this.id = id;
	}

	/**
	 * @param id
	 * @param eventPublisher
	 */
	public Resource(UUID id, DomainEventPublisher eventPublisher) {
		this(id);
		this.eventPublisher = eventPublisher;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		eventPublisher.publish(new DomainEvent("new key generated"));
		this.key = key;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
