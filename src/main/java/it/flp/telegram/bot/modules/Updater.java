package it.flp.telegram.bot.modules;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import it.flp.telegram.bot.entities.UpdateResponse;

/**
 * Modulo per l'aggiornamento dei messaggi
 * 
 * @author Christian Lusardi
 * @version 1.0
 *
 */
public class Updater {

	private static final Logger log = LoggerFactory.getLogger(Updater.class);

	private int seconds;

	public Updater() {
		super();

	}

	public UpdateResponse getUpdates() throws Exception {

		UpdateResponse updateResponse = null;

		log.debug("Start retrieve updates");

		try (CloseableHttpClient httpclient = HttpClients.createDefault();) {

			HttpHost proxy = new HttpHost("10.68.64.37", 8081);

			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			HttpGet request = new HttpGet(
					"http://api.telegram.org/bot304240201%3AAAF0YTE5NyNaQV8Z-GhxWRQb6ftXpsXZr4w/getUpdates");
			request.setConfig(config);

			try (CloseableHttpResponse response = httpclient.execute(request);) {
				String entityString = EntityUtils.toString(response.getEntity());

				Gson gson = new Gson();
				updateResponse = gson.fromJson(entityString, UpdateResponse.class);
			} catch (Exception e) {
				log.error("Error: ", e.getMessage(), e);
			}

		} catch (Exception e) {
			log.error("Error duringe");
		}

		log.debug("End retrieve updates");

		return updateResponse;

	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

}