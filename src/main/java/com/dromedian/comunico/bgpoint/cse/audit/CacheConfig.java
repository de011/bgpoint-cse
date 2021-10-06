package com.dromedian.comunico.bgpoint.cse.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;


/**
 * @author Deepak
 *
 */
@Configuration
public class CacheConfig {
	@Bean
	public Config configure() {
		return new Config().setInstanceName("hazlecast-insatnce")
				.addMapConfig(new MapConfig().setName("cache")
						.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
						.setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(2000));
	}
}
