package cc.tinker.tool;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;

public class HttpClientImpl {

	private volatile static HttpClientImpl httpClientImpl = null;
	private PoolingHttpClientConnectionManager connectionManager;

	private HttpClientImpl() {
		initConnectionManager();
	}

	public static HttpClientImpl getInstance() {
		if (httpClientImpl == null) {
			synchronized (HttpClientImpl.class) {
				if (httpClientImpl == null) {
					httpClientImpl = new HttpClientImpl();
				}
			}
		}
		return httpClientImpl;
	}

	private void initConnectionManager() {
		try {
			SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
			sslcontext.init(null, new TrustManager[] { new CustomTrustManager() }, new SecureRandom());
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", new SSLConnectionSocketFactory(sslcontext)).build();
			connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private CloseableHttpClient getHttpClient() {
		return HttpClients.custom().setConnectionManager(connectionManager).build();
	}

	public String getMethod(String url, Map<String, String> params) throws Exception {
		URIBuilder uriBuilder = new URIBuilder(url);
		if (params != null && !params.isEmpty()) {
			ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> param : params.entrySet()) {
				pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
			}
			uriBuilder.setParameters(pairs);
		}
		uriBuilder.setCharset(Charset.forName("UTF-8"));
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		return executeMethod(httpGet);
	}

	public String postMethod(String url, Map<String, String> params) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		if (params != null && !params.isEmpty()) {
			ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> param : params.entrySet()) {
				pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, "UTF-8");
			httpPost.setEntity(entity);
		}
		return executeMethod(httpPost);
	}

	public String postString(String url, String body) {
		HttpPost httpPost = new HttpPost(url);
		if (body != null) {
			httpPost.setEntity(new StringEntity(body, "UTF-8"));
		}
		return executeMethod(httpPost);
	}

	public String postMultipart() {
		return null;
	}

	private String executeMethod(HttpUriRequest request) {
		String result = null;
		CloseableHttpClient httpCilent = getHttpClient();
		try (CloseableHttpResponse response = httpCilent.execute(request)) {
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == 200 && entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
