package org.demo.domain.resource;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.gemfire.repository.GemfireRepository;

public interface ResourceRepository extends GemfireRepository<Resource, UUID> {

	/**
	 * @param id
	 * @return
	 */
	Optional<Resource> findById(UUID id);

}
