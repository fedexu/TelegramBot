package test2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	private static String text;
	// Campi necessari per inviare una foto
	public static final String PHOTO_FIELD = "photo";
	public static final String VIDEO_FIELD = "video";
	private static File photo;
	// Campi opzionali per altre operazioni
	public static final String REPLYTOMESSAGEID_FIELD = "reply_to_message_id";
	private static Integer replyToMessageId;

	public static void main(String[] args) {

		text = "tento di mandarti una foto e un video";
		chatId = 29337550;
		String photoPath = "D:\\Users\\clusardi\\git\\personale\\TelegramBot\\src\\main\\resources\\photo.jpg";
		String videoPath = "D:\\Users\\clusardi\\git\\personale\\TelegramBot\\src\\main\\resources\\sample_video.mp4";

		try {

			sendMessage();

			sendPhoto(photoPath);

			sendVideo(videoPath);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void sendMessage() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpHost proxy = new HttpHost("10.68.64.37", 8081);
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

		/// Create Http POST method and set correct headers
		String url = BASEURL + TOKEN + "/" + SEND_MESSAGE_PATH;
		HttpPost httppost = new HttpPost(url);
		httppost.addHeader("Content-type", "application/x-www-form-urlencoded");
		httppost.addHeader("charset", "UTF-8");

		/// Create list of parameters
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		/// Add chatid to the list
		nameValuePairs.add(new BasicNameValuePair(CHATID_FIELD, chatId + ""));
		/// Add text to the list
		nameValuePairs.add(new BasicNameValuePair(TEXT_FIELD, text));

		/// Set list of parameters as entity of the Http POST method
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

		httppost.setConfig(config);

		/// TODO Execute httppost using, for example
		CloseableHttpResponse response = httpclient.execute(httppost);

		System.out.println(response.getStatusLine());
	}

	

	public static void sendVideo(String videoPath) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = null;
		String url = BASEURL + TOKEN + "/" + SEND_VIDEO_PATH;
		try {
			HttpHost proxy = new HttpHost("10.68.64.37", 8081);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			File imgFile = new File(videoPath);
			HttpPost post = new HttpPost(url);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addBinaryBody(VIDEO_FIELD, imgFile, ContentType.DEFAULT_BINARY, imgFile.getName());
			builder.setBoundary("---Content Boundary");
			

			builder.addTextBody(CHATID_FIELD, String.valueOf(chatId), ContentType.TEXT_PLAIN);
			builder.setBoundary("---Content Boundary");

			httpclient = HttpClientBuilder.create().build();
			HttpEntity entity = builder.build();
			post.setEntity(entity);
			post.setConfig(config);
			HttpResponse response = httpclient.execute(post);
			if (response != null) {
				HttpEntity responseEntity = response.getEntity();
				if (responseEntity != null) {
					InputStream responseStream = responseEntity.getContent() ;
                    if (responseStream != null){
                        BufferedReader br = new BufferedReader (new InputStreamReader (responseStream)) ;
                        String responseLine = br.readLine() ;
                        String tempResponseString = "" ;
                        while (responseLine != null){
                            tempResponseString = tempResponseString + responseLine + System.getProperty("line.separator") ;
                            responseLine = br.readLine() ;
                        }
                        br.close() ;
                        if (tempResponseString.length() > 0){
                            System.out.println(tempResponseString);
                        }
                    }
                    responseStream.close();
				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}
	
	
	
	public static void sendPhoto(String imagePath) {
		CloseableHttpClient httpclient = null;
		String url = BASEURL + TOKEN + "/" + SEND_PHOTO_PATH;
		try {
			HttpHost proxy = new HttpHost("10.68.64.37", 8081);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			File imgFile = new File(imagePath);
			HttpPost post = new HttpPost(url);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addBinaryBody(PHOTO_FIELD, imgFile, ContentType.DEFAULT_BINARY, imgFile.getName());
			builder.setBoundary("---Content Boundary");
			

			builder.addTextBody(CHATID_FIELD, String.valueOf(chatId), ContentType.TEXT_PLAIN);
			builder.setBoundary("---Content Boundary");

			httpclient = HttpClientBuilder.create().build();
			HttpEntity entity = builder.build();
			post.setEntity(entity);
			post.setConfig(config);
			HttpResponse response = httpclient.execute(post);
			if (response != null) {
				HttpEntity responseEntity = response.getEntity();
				if (responseEntity != null) {
					InputStream responseStream = responseEntity.getContent() ;
                    if (responseStream != null){
                        BufferedReader br = new BufferedReader (new InputStreamReader (responseStream)) ;
                        String responseLine = br.readLine() ;
                        String tempResponseString = "" ;
                        while (responseLine != null){
                            tempResponseString = tempResponseString + responseLine + System.getProperty("line.separator") ;
                            responseLine = br.readLine() ;
                        }
                        br.close() ;
                        if (tempResponseString.length() > 0){
                            System.out.println(tempResponseString);
                        }
                    }
                    responseStream.close();
				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}	

}
