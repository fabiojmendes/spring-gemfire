package org.demo.domain.resource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import org.demo.domain.DomainEventPublisher;
import org.demo.domain.resource.File;
import org.demo.domain.resource.ResourceGenerator;
import org.demo.domain.resource.ResourceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResourceGeneratorTest {

	private static final String NAME = "Test Name";

	@Mock
	private ResourceRepository resourceRepositoryMock;

	@Mock
	private DomainEventPublisher domainEventPublisher;

	@Mock
	private KeyGenerator keyGenerator;

	private File testResource;

	@InjectMocks
	private ResourceGenerator generator;

	@Before
	public void setup() {
		testResource = new File(UUID.randomUUID(), domainEventPublisher);
		testResource.setKey("1234");
		testResource.setName(NAME);

		when(keyGenerator.generate()).thenReturn("1234567890");
		when(resourceRepositoryMock.save(Matchers.isA(Resource.class))).thenAnswer(invocation -> {
			return invocation.getArgumentAt(0, Resource.class);
		});
	}

	@Test
	public void testGenerate() {
		Resource result = generator.generate(NAME, ResourceType.FILE);
		assertThat(result, notNullValue());
		assertThat(result.getKey(), is("1234567890"));
		assertThat(result.getName(), is(NAME));
		verify(resourceRepositoryMock).save(Matchers.isA(Resource.class));
	}

	@Test
	public void testUpdate() {
		when(resourceRepositoryMock.findById(testResource.getId())).thenReturn(Optional.of(testResource));
		Resource res = generator.update(testResource.getId(), "New Name", ResourceType.FILE);
		assertThat(res.getName(), is("New Name"));
	}

	@Test
	public void testUpdateNewType() {
		when(resourceRepositoryMock.findById(testResource.getId())).thenReturn(Optional.of(testResource));
		Resource res = generator.update(testResource.getId(), "New Name", ResourceType.STREAM);
		assertThat(res.getName(), is("New Name"));
		assertThat(res.getType(), is(ResourceType.STREAM));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateWithInvalidId() {
		when(resourceRepositoryMock.findById(anyObject())).thenReturn(Optional.empty());
		generator.update(UUID.randomUUID(), "New Name", ResourceType.FILE);
	}

	@Test
	public void testDelete() {
		generator.delete(testResource.getId());
		verify(resourceRepositoryMock).delete(testResource.getId());
	}

}
