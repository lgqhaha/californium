/*******************************************************************************
 * Copyright (c) 2015 Institute for Pervasive Computing, ETH Zurich and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v20.html
 * and the Eclipse Distribution License is available at
 *    http://www.eclipse.org/org/documents/edl-v10.html.
 * 
 * Contributors:
 *    Matthias Kovatsch - creator and main architect
 ******************************************************************************/
package org.eclipse.californium.plugtests.tests;

import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.coap.Token;
import org.eclipse.californium.plugtests.TestClientAbstract;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.CoAP.Type;

/**
 * TD_COAP_CORE_12: Perform GET transaction not containing Token option (CON
 * mode)
 */
public class CC12 extends TestClientAbstract {

	public static final String RESOURCE_URI = "/test";
	public final ResponseCode EXPECTED_RESPONSE_CODE = ResponseCode.CONTENT;

	public CC12(String serverURI) {
		super(CC12.class.getSimpleName());

		// create the request
		Request request = new Request(Code.GET, Type.CON);
		// request.requiresToken(false); // TODO
		request.setToken(Token.EMPTY);
		// set the parameters and execute the request
		executeRequest(request, serverURI, RESOURCE_URI);
	}

	protected boolean checkResponse(Request request, Response response) {
		boolean success = true;

		success &= checkType(Type.ACK, response.getType());
		success &= checkCode(EXPECTED_RESPONSE_CODE, response.getCode());
		success &= hasNoToken(response);
		success &= hasContentType(response);
		success &= hasNonEmptyPayload(response);

		return success;
	}
}
