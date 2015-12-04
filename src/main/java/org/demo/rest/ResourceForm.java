package org.demo.rest;

import org.demo.domain.resource.ResourceType;

/**
 * @author Fabio Mendes <fabio.mendes@navita.com.br> Oct 5, 2015
 *
 */
public class ResourceForm {

	private String name;

	private ResourceType type;

	/**
	 *
	 */
	public ResourceForm() {
		this("", ResourceType.FILE);
	}

	/**
	 * @param name
	 */
	public ResourceForm(String name) {
		this(name, ResourceType.FILE);
	}

	/**
	 * @param name
	 * @param type
	 */
	public ResourceForm(String name, ResourceType type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public ResourceType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ResourceType type) {
		this.type = type;
	}
}
