package test2;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * How to send a request via proxy.
 *
 * @since 4.0
 */
public class ClientExecuteProxy {

	public static void main(String[] args) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
	
			HttpHost proxy = new HttpHost("10.68.64.37", 8081);

			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			HttpGet request = new HttpGet("http://api.telegram.org/bot304240201%3AAAF0YTE5NyNaQV8Z-GhxWRQb6ftXpsXZr4w/getUpdates");
			request.setConfig(config);

			CloseableHttpResponse response = httpclient.execute( request);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				System.out.println(EntityUtils.toString(response.getEntity()));
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}



}