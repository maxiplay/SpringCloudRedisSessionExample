package com.worldline.springcloudarchetype.filter;

import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;

import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Version filter
 * 
 * @author a525125
 *
 */
public class VersionFilter extends ZuulFilter {

	private static final String DEFAULT_VERSION = "1.1";
	public static final String EUREKA_METADATA_VERSION = "version";

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		String version = context.getRequest().getHeader("Version");
		if (!StringUtils.hasText(version)) {
			version = DEFAULT_VERSION;
		}
		RibbonFilterContextHolder.getCurrentContext().add(
				EUREKA_METADATA_VERSION, version);
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 10;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
