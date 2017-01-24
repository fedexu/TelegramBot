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

import it.flp.telegram.bot.constants.Constants;
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

	public UpdateResponse getUpdates() throws Exception{
		return getUpdatesWithProxy(null, 0);
	}
	public UpdateResponse getUpdatesWithProxy(String ip, int port) throws Exception {

		UpdateResponse updateResponse = null;
		String url = Constants.BASEURL + Constants.TOKEN + "/" + Constants.GET_UPDATES;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		log.debug("Start retrieve updates");

		if (ip != null) {
			HttpHost proxy = new HttpHost(ip, port);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			request.setConfig(config);
		}

		CloseableHttpResponse response = httpclient.execute(request);
		String entityString = EntityUtils.toString(response.getEntity());

		Gson gson = new Gson();
		updateResponse = gson.fromJson(entityString, UpdateResponse.class);

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