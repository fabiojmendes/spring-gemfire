package org.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.mapping.MappingPdxSerializer;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;

@Configuration
@EnableGemfireRepositories
public class GemfireConfiguration {
	
	@Value("${gemfire.locator.host}")
	private String gemfireLocatorHost;

	@Bean
	public ClientCache clientCache() {
		return new ClientCacheFactory()
				.addPoolLocator(gemfireLocatorHost, 10334)
				.setPdxSerializer(new MappingPdxSerializer())
				.create();
	}
	
	@Bean
	public ClientRegionFactory<?, ?> clientCacheFactory() {
		return clientCache().createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY);
	}
	
	@Bean
	public Region<?, ?> resourceRegion() {
		return clientCacheFactory().create("Resource");
	}

}
