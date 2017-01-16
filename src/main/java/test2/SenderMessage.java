package test2;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SenderMessage {
	
	public static void main(String[] args) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
	
			HttpHost proxy = new HttpHost("10.68.64.37", 8081);

			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			HttpPost request = new HttpPost("http://api.telegram.org/bot304240201%3AAAF0YTE5NyNaQV8Z-GhxWRQb6ftXpsXZr4w/sendMessage");
			request.addHeader("Content-type", "application/x-www-form-urlencoded");
			request.addHeader("charset", "UTF-8");


			
			/// Create list of parameters
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			/// Add chatid to the list
			nameValuePairs.add(new BasicNameValuePair("chat_id", "220202318"));
			/// Add text to the list
			nameValuePairs.add(new BasicNameValuePair("text", "sei gay"));
			
			


			/// Set list of parameters as entity of the Http POST method
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			
			request.setConfig(config);

			CloseableHttpResponse response = httpclient.execute( request);
			
			
			System.out.println("status line -->"+response.getStatusLine());
			
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
