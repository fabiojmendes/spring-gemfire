package org.demo.domain.resource;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Region;

/**
 * @author Fabio Mendes <fabio.mendes@navita.com.br> Dec 3, 2015
 *
 */
@Region
public abstract class Resource {

	@Id
	private UUID id;

	private String name;

	/**
	 * @param id
	 */
	public Resource(UUID id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
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

	/**
	 * @return the key
	 */
	public abstract String getKey();

	/**
	 * @return
	 */
	public abstract ResourceType getType();

}
