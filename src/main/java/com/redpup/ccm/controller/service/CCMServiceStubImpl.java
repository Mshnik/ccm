package com.redpup.ccm.controller.service;

import com.google.inject.Inject;
import com.redpup.ccm.proto.CCMServiceGrpc.CCMServiceImplBase;
import com.redpup.ccm.proto.ServiceProtos.GetTemplatesRequest;
import com.redpup.ccm.proto.ServiceProtos.GetTemplatesResponse;
import io.grpc.stub.StreamObserver;

/**
 * Implementation of {@link CCMServiceImplBase}.
 */
public final class CCMServiceStubImpl extends CCMServiceImplBase {


	private final RequestValidator<GetTemplatesRequest> getTemplatesRequestValidator;
	private final ActionHandler<GetTemplatesRequest, GetTemplatesResponse> getTemplatesActionHandler;

	@Inject
	CCMServiceStubImpl(
			RequestValidator<GetTemplatesRequest> getTemplatesRequestValidator,
			ActionHandler<GetTemplatesRequest, GetTemplatesResponse> getTemplatesActionHandler) {
		this.getTemplatesRequestValidator = getTemplatesRequestValidator;
		this.getTemplatesActionHandler = getTemplatesActionHandler;
	}

	@Override
	public void getTemplates(GetTemplatesRequest request,
			StreamObserver<GetTemplatesResponse> responseObserver) {
		try {
			getTemplatesRequestValidator.validate(request);
			responseObserver.onNext(getTemplatesActionHandler.handle(request));
		} catch (Exception e) {
			responseObserver.onError(e);
		}

		responseObserver.onCompleted();
	}
}
