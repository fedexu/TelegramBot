package it.flp.telegram.bot.modules;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.flp.telegram.bot.constants.Constants;
import it.flp.telegram.bot.utils.Utils;

public class Sender {

	private static final Logger log = LoggerFactory.getLogger(Updater.class);

	public CloseableHttpResponse sendMessage(String text, Integer chatId) throws Exception {
		return sendMessageWithProxy(text, chatId, null, 0);
	}

	public CloseableHttpResponse sendMessageWithProxy(String text, Integer chatId, String ip, int port)
			throws Exception {
		log.debug("Start prepare HttpClient for send message request");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = Constants.BASEURL + Constants.TOKEN + "/" + Constants.SEND_MESSAGE_PATH;
		HttpPost httppost = new HttpPost(url);

		if (ip != null) {
			HttpHost proxy = new HttpHost(ip, port);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			httppost.setConfig(config);
		}

		httppost.addHeader("Content-type", "application/x-www-form-urlencoded");
		httppost.addHeader("charset", "UTF-8");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair(Constants.CHATID_FIELD, chatId + ""));
		nameValuePairs.add(new BasicNameValuePair(Constants.TEXT_FIELD, text));
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

		CloseableHttpResponse response = httpclient.execute(httppost);
		log.debug("Send message request completed");
		log.debug(Utils.getTextResponse(response));
		return response;
	}

	public CloseableHttpResponse sendPhoto(String imagePath, Integer chatId) throws Exception {
		return sendPhotoWithProxy(imagePath, chatId, null, 0);
	}

	public CloseableHttpResponse sendPhotoWithProxy(String imagePath, Integer chatId, String ip, int port)
			throws Exception {
		log.debug("Start prepare HttpClient for send Photo request");
		CloseableHttpClient httpclient = null;
		String url = Constants.BASEURL + Constants.TOKEN + "/" + Constants.SEND_PHOTO_PATH;
		HttpPost httppost = new HttpPost(url);
		if (ip != null) {
			HttpHost proxy = new HttpHost(ip, port);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			httppost.setConfig(config);
		}
		File imgFile = new File(imagePath);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addBinaryBody(Constants.PHOTO_FIELD, imgFile, ContentType.DEFAULT_BINARY, imgFile.getName());
		builder.setBoundary("---Content Boundary");
		builder.addTextBody(Constants.CHATID_FIELD, String.valueOf(chatId), ContentType.TEXT_PLAIN);
		builder.setBoundary("---Content Boundary");

		httpclient = HttpClientBuilder.create().build();
		HttpEntity entity = builder.build();
		httppost.setEntity(entity);

		CloseableHttpResponse response = httpclient.execute(httppost);
		log.debug("Send Photo request completed");
		log.debug(Utils.getTextResponse(response));
		return response;
	}

	public CloseableHttpResponse sendVideo(String videoPath, Integer chatId) throws Exception {
		return sendVideoWithProxy(videoPath, chatId, null, 0);
	}

	public CloseableHttpResponse sendVideoWithProxy(String videoPath, Integer chatId, String ip, int port)
			throws Exception {
		log.debug("Start prepare HttpClient for send Video request");
		CloseableHttpClient httpclient = null;
		String url = Constants.BASEURL + Constants.TOKEN + "/" + Constants.SEND_VIDEO_PATH;
		HttpPost httppost = new HttpPost(url);
		if (ip != null) {
			HttpHost proxy = new HttpHost(ip, port);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			httppost.setConfig(config);
		}
		File imgFile = new File(videoPath);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addBinaryBody(Constants.VIDEO_FIELD, imgFile, ContentType.DEFAULT_BINARY, imgFile.getName());
		builder.setBoundary("---Content Boundary");

		builder.addTextBody(Constants.CHATID_FIELD, String.valueOf(chatId), ContentType.TEXT_PLAIN);
		builder.setBoundary("---Content Boundary");

		httpclient = HttpClientBuilder.create().build();
		HttpEntity entity = builder.build();
		httppost.setEntity(entity);
		CloseableHttpResponse response = httpclient.execute(httppost);
		log.debug("Send Video request completed");
		log.debug(Utils.getTextResponse(response));
		return response;
	}

}
