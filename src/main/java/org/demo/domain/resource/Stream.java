package org.demo.domain.resource;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Fabio Mendes <fabio.mendes@navita.com.br> Dec 3, 2015
 *
 */
public class Stream extends Resource {

	private Integer index;

	/**
	 * @param id
	 */
	public Stream(UUID id) {
		super(id);
		this.index = 0;
	}

	/**
	 * @see org.demo.domain.resource.Resource#getKey()
	 */
	@Override
	public String getKey() {
		return Objects.toString(index);
	}

	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	public void step() {
		index++;
	}

	public void reset() {
		index = 0;
	}

	/**
	 * @see org.demo.domain.resource.Resource#getType()
	 */
	@Override
	public ResourceType getType() {
		return ResourceType.STREAM;
	}

}
