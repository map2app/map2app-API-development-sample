/**
 * 
 */
package com.map2app.test;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author micheleorsi
 * 
 */
public class UserAccountInfoTest {
	private static final Logger log = Logger
			.getLogger(UserAccountInfoTest.class.getName());

	private static final String USER_KEY = "<put-here-your-user-key>";
	private static final String API_SECRET = "<put-here-your-user-secret>";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInfo() throws IOException, HttpException {
		HttpParams params = new SyncBasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "UTF-8");
		HttpProtocolParams.setUserAgent(params,
				"map2app-API-development-sample/0.0.1-SNAPSHOT");
		HttpProtocolParams.setUseExpectContinue(params, true);

		HttpProcessor httpproc = new ImmutableHttpProcessor(
				new HttpRequestInterceptor[] {
						// Required protocol interceptors
						new RequestContent(), new RequestTargetHost(),
						// Recommended protocol interceptors
						new RequestConnControl(), new RequestUserAgent(),
						new RequestExpectContinue() });

		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

		HttpContext context = new BasicHttpContext(null);
		HttpHost host = new HttpHost("maptoapp.appspot.com", 80);

		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
		context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
		context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);

		try {

			String target = "/api/useraccounts/" + USER_KEY + "?userKey="
					+ USER_KEY + "&apiSecret=" + API_SECRET;

			Socket socket = new Socket(host.getHostName(), host.getPort());
			conn.bind(socket, params);

			BasicHttpRequest request = new BasicHttpRequest("GET", target);
			request.addHeader("accept", "application/json");
			log.info(">> Request URI: " + request.getRequestLine().getUri());

			request.setParams(params);
			httpexecutor.preProcess(request, httpproc, context);
			HttpResponse response = httpexecutor
					.execute(request, conn, context);
			response.setParams(params);
			httpexecutor.postProcess(response, httpproc, context);

			log.info("<< Response: " + response.getStatusLine());
			log.info(EntityUtils.toString(response.getEntity()));
			conn.close();
			log.info("Connection closed!");

		} finally {
			conn.close();
			log.info("Connection closed!");
		}
	}

}
