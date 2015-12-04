package org.demo.domain.resource;

import java.util.UUID;

import org.demo.domain.DomainEvent;
import org.demo.domain.DomainEventPublisher;
import org.demo.domain.DomainService;
import org.springframework.data.annotation.PersistenceConstructor;

public class File extends Resource {

	private String key;

	private String path;

	@DomainService
	private transient DomainEventPublisher eventPublisher;

	/**
	 * @param id
	 * @param eventPublisher
	 */
	@PersistenceConstructor
	public File(UUID id) {
		super(id);
	}

	/**
	 * @param id
	 * @param eventPublisher
	 */
	public File(UUID id, DomainEventPublisher eventPublisher) {
		this(id);
		this.eventPublisher = eventPublisher;
	}

	/**
	 * @see org.demo.domain.resource.Resource#getKey()
	 */
	@Override
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 */
	public void setKey(String key) {
		eventPublisher.publish(new DomainEvent("new key generated"));
		this.key = key;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @see org.demo.domain.resource.Resource#getType()
	 */
	@Override
	public ResourceType getType() {
		return ResourceType.FILE;
	}
}
