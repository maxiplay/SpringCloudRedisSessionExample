package com.springcloud.sessionexample.config;

import io.jmnarloch.spring.cloud.ribbon.rule.DiscoveryEnabledRule;
import io.jmnarloch.spring.cloud.ribbon.rule.MetadataAwareRule;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList;

/**
 * The Ribbon discovery filter auto configuration.
 *
 * @author a525125
 */
@Configuration
@ConditionalOnClass(DiscoveryEnabledNIWSServerList.class)
@AutoConfigureBefore(RibbonClientConfiguration.class)
@ConditionalOnProperty(value = "ribbon.filter.metadata.enabled", matchIfMissing = true)
public class RibbonConfiguration {

	@Bean
	@Scope("prototype")
	public DiscoveryEnabledRule metadataAwareRule() {
		return new MetadataAwareRule();
	}
}
