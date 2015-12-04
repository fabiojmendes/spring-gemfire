package org.demo;

import org.demo.domain.resource.Resource;
import org.demo.util.RegionUtils;
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

	@Value("${gemfire.locator.hosts}")
	private String locatorHosts;

	@Bean
	public ClientCache clientCache() {
		ClientCacheFactory factory = new ClientCacheFactory();
		factory.setPdxSerializer(new MappingPdxSerializer());
		for (String host : locatorHosts.split(",")) {
			String[] hostPort = host.split(":");
			factory.addPoolLocator(hostPort[0].trim(), Integer.parseInt(hostPort[1]));
		}
		return factory.create();
	}

	@Bean
	public ClientRegionFactory<?, ?> clientCacheFactory() {
		return clientCache().createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY);
	}

	@Bean
	public Region<?, ?> resourceRegion() {
		return clientCacheFactory().create(RegionUtils.getRegionName(Resource.class));
	}

}
