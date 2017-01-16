package test2;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandDispatcher;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandQueue;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandWatcher;

public class Main {
	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {

		setProxy();
		
		System.out.println("proxy settati:");
		System.out.println("HTTP: "+System.getProperty("http.proxyHost"));
		System.out.println("HTTP: "+System.getProperty("http.proxyPort"));
		System.out.println("HTTPS: "+System.getProperty("https.proxyHost"));
		System.out.println("HTTPS: "+System.getProperty("https.proxyPort"));
		

		CloseableHttpClient httpclient = HttpClients.createDefault();
	
		
		HttpGet request = new HttpGet(
				"https://api.telegram.org/bot304240201:AAF0YTE5NyNaQV8Z-GhxWRQb6ftXpsXZr4w/getme");

		
		try {
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			// Read the contents of an entity and return it as a String.
			String content = EntityUtils.toString(entity);
			System.out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
		}

		DefaultCommandDispatcher commandDispatcher = new DefaultCommandDispatcher(10, 100, new DefaultCommandQueue());
		commandDispatcher.startUp();
		DefaultCommandWatcher commandWatcher = new DefaultCommandWatcher(2000, 100,
				"304240201:AAF0YTE5NyNaQV8Z-GhxWRQb6ftXpsXZr4w", commandDispatcher, new HelloWorldCommandFactory());
		commandWatcher.startUp();
	}
	


	public static void setProxy() {

		System.setProperty("http.proxyHost", "10.68.64.37");
		System.setProperty("http.proxyPort", "8081");
		System.setProperty("https.proxyHost", "10.68.64.37");
		System.setProperty("https.proxyPort", "8081");

	}
}
