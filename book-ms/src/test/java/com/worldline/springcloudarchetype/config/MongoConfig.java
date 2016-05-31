package com.worldline.springcloudarchetype.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;

/**
 * Mongo config for tests
 * 
 * @author A525125
 *
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "test";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new Fongo(getDatabaseName()).getMongo();
	}

}
