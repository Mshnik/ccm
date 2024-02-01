package com.redpup.ccm.controller.service;

import com.google.protobuf.Message;

/**
 * Encapsulates logic that validates a given type of request message.
 */
public interface RequestValidator<M extends Message> {

	/**
	 * Validates that {@code message} is valid. Throws an exception otherwise.
	 */
	abstract void validate(M message) throws Exception;
}
