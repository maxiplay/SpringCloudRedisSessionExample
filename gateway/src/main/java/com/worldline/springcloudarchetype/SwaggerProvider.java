package com.worldline.springcloudarchetype;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {

	private final Logger log = LoggerFactory.getLogger(SwaggerProvider.class);

	@Autowired
	private DiscoveryClientRouteLocator routeLocator;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();

		// Add the default swagger resource that correspond to the gateway's own
		// swagger doc
		resources.add(swaggerResource("default", "/v2/api-docs", "2.0"));

		// Add the registered microservices swagger docs as additional swagger
		// resources
		List<Route> routes = routeLocator.getRoutes();
		routes.forEach((route) -> {
			if (route.getPath().endsWith("**")) {
				resources.add(swaggerResource(route.getLocation(), route
						.getPath().replace("**", "v2/api-docs"), "2.0"));
			}
		});

		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location,
			String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}
}
