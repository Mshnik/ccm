package com.redpup.ccm.controller.service;

import com.google.protobuf.Message;

/**
 * Encapsulates logic handling a given request message and returns the
 * associated response message.
 */
public interface ActionHandler<Req extends Message, Res extends Message> {

	/**
	 * Handles the given {@code request} and returns the associated response.
	 * Throws an exception if an error occured in processing.
	 */
	Res handle(Req request) throws Exception;
}
