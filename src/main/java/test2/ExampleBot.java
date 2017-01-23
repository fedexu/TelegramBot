package test2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
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

public class ExampleBot {

	public static final String BASEURL = "https://api.telegram.org/bot";
	public static final String TOKEN = "304240201%3AAAF0YTE5NyNaQV8Z-GhxWRQb6ftXpsXZr4w";
	public static final String SEND_MESSAGE_PATH = "sendmessage";
	public static final String SEND_PHOTO_PATH = "sendPhoto";
	public static final String SEND_VIDEO_PATH = "sendVideo";

	public static final String CHATID_FIELD = "chat_id";
	private static Integer chatId; /// < Unique identifier for the message
									/// recepient — User or GroupChat id
	// Campi necessari per inviare un messaggio
	public static final String TEXT_FIELD = "text";

	// Campi necessari per inviare una foto
	public static final String PHOTO_FIELD = "photo";

	public static final String VIDEO_FIELD = "video";

	// Campi opzionali per altre operazioni
	public static final String REPLYTOMESSAGEID_FIELD = "reply_to_message_id";
	private static Integer replyToMessageId;

	public static void main(String[] args) {

		String text = "ce provo";
		chatId = 220202318;
		String photoPath = "D:\\workspaceIntelliJ\\TelegramBot\\src\\main\\resources\\photo.jpg";
		String videoPath = "D:\\workspaceIntelliJ\\TelegramBot\\src\\main\\resources\\sample_video.mp4";
		String ip = "10.68.64.37";
		int port = 8081;

		try {
			
			System.out.println(getTextResponse(sendMessageWithProxy(text, ip, port)));

			System.out.println(getTextResponse(sendPhotoWithProxy(photoPath, ip, port)));

			System.out.println(getTextResponse(sendVideoWithProxy(videoPath, ip, port)));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static CloseableHttpResponse sendMessage(String text) throws ClientProtocolException, IOException {
		return sendMessageWithProxy(text, null, 0);
	}

	public static CloseableHttpResponse sendMessageWithProxy(String text, String ip, int port)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = BASEURL + TOKEN + "/" + SEND_MESSAGE_PATH;
		HttpPost httppost = new HttpPost(url);

		if (ip != null) {
			HttpHost proxy = new HttpHost(ip, port);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			httppost.setConfig(config);
		}

		httppost.addHeader("Content-type", "application/x-www-form-urlencoded");
		httppost.addHeader("charset", "UTF-8");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair(CHATID_FIELD, chatId + ""));
		nameValuePairs.add(new BasicNameValuePair(TEXT_FIELD, text));
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		return httpclient.execute(httppost);

	}

	public static CloseableHttpResponse sendVide(String videoPath) throws ClientProtocolException, IOException{
		return sendVideoWithProxy(videoPath, null, 0);
	}
	
	public static CloseableHttpResponse sendVideoWithProxy(String videoPath, String ip, int port)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = null;
		String url = BASEURL + TOKEN + "/" + SEND_VIDEO_PATH;
		HttpPost post = new HttpPost(url);
		if (ip != null) {
			HttpHost proxy = new HttpHost("10.68.64.37", 8081);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			post.setConfig(config);
		}
		File imgFile = new File(videoPath);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addBinaryBody(VIDEO_FIELD, imgFile, ContentType.DEFAULT_BINARY, imgFile.getName());
		builder.setBoundary("---Content Boundary");

		builder.addTextBody(CHATID_FIELD, String.valueOf(chatId), ContentType.TEXT_PLAIN);
		builder.setBoundary("---Content Boundary");

		httpclient = HttpClientBuilder.create().build();
		HttpEntity entity = builder.build();
		post.setEntity(entity);

		return httpclient.execute(post);

	}

	public static CloseableHttpResponse sendPhoto(String imagePath) throws ClientProtocolException, IOException {
		return sendPhotoWithProxy(imagePath, null, 0);
	}

	public static CloseableHttpResponse sendPhotoWithProxy(String imagePath, String ip, int port)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = null;
		String url = BASEURL + TOKEN + "/" + SEND_PHOTO_PATH;
		HttpPost httppost = new HttpPost(url);
		if (ip != null) {
			HttpHost proxy = new HttpHost("10.68.64.37", 8081);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			httppost.setConfig(config);
		}
		File imgFile = new File(imagePath);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addBinaryBody(PHOTO_FIELD, imgFile, ContentType.DEFAULT_BINARY, imgFile.getName());
		builder.setBoundary("---Content Boundary");
		builder.addTextBody(CHATID_FIELD, String.valueOf(chatId), ContentType.TEXT_PLAIN);
		builder.setBoundary("---Content Boundary");

		httpclient = HttpClientBuilder.create().build();
		HttpEntity entity = builder.build();
		httppost.setEntity(entity);

		return httpclient.execute(httppost);

	}

	public static String getTextResponse(CloseableHttpResponse response)
			throws UnsupportedOperationException, IOException {
		String resp = "";
		if (response != null) {
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				InputStream responseStream = responseEntity.getContent();
				if (responseStream != null) {
					BufferedReader br = new BufferedReader(new InputStreamReader(responseStream));
					String responseLine = br.readLine();
					String tempResponseString = "";
					while (responseLine != null) {
						tempResponseString = tempResponseString + responseLine + System.getProperty("line.separator");
						responseLine = br.readLine();
					}
					br.close();
					if (tempResponseString.length() > 0) {
						resp += tempResponseString;
					}
				}
				responseStream.close();
			}
		}
		return resp;
	}

}
