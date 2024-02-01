package com.redpup.ccm.controller.service;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.redpup.ccm.proto.ServiceProtos.GetTemplatesRequest;

/**
 *
 */
public final class CCMServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<RequestValidator<GetTemplatesRequest>>() {})
				.to(GetTemplatesRequestValidator.class);
	}
}
