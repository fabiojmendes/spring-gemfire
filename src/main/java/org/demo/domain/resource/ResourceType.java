package org.demo.domain.resource;

import java.util.UUID;

import org.demo.domain.DomainEventPublisher;

public enum ResourceType {

	FILE {

		@Override
		public Resource createResource(UUID id, KeyGenerator keyGenerator, DomainEventPublisher domainEventPublisher) {
			File file = new File(id, domainEventPublisher);
			file.setKey(keyGenerator.generate());
			file.setPath("/");
			return file;
		}
	},

	STREAM {

		@Override
		public Resource createResource(UUID id, KeyGenerator keyGenerator, DomainEventPublisher domainEventPublisher) {
			return new Stream(id);
		}
	};

	/**
	 * @param id
	 * @param key
	 * @param domainEventPublisher
	 * @return
	 */
	public abstract Resource createResource(UUID id, KeyGenerator keyGenerator, DomainEventPublisher domainEventPublisher);
}
