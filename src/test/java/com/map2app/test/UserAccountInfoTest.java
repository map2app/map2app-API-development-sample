/**
 * 
 */
package com.map2app.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
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
		HttpClient httpclient = new DefaultHttpClient();
		
		HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
		
		try {
			HttpGet httpget = new HttpGet("https://maptoapp.appspot.com/api/useraccounts/" + USER_KEY + "?userKey="+ USER_KEY + "&apiSecret=" + API_SECRET);
			httpget.setHeader("accept", "application/json");
			log.info(">> Request: " + httpget.getURI());

			// Create a response handler
            HttpResponse response = httpclient.execute(httpget);
            assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
            log.info("<< Response: " +EntityUtils.toString(response.getEntity()));

        } finally {
            httpclient.getConnectionManager().shutdown();
        }
	}

}
