package test2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;



public class ExampleBot {
	
	public static final String BASEURL = "https://api.telegram.org/bot";
	public static final String TOKEN = "304240201%3AAAF0YTE5NyNaQV8Z-GhxWRQb6ftXpsXZr4w";
	public static final String SEND_MESSAGE_PATH = "sendmessage";
	public static final String SEND_PHOTO_PATH = "sendPhoto";
	
	public static final String CHATID_FIELD = "chat_id";
	private static Integer chatId; /// < Unique identifier for the message recepient — User or GroupChat id
//	Campi necessari per inviare un messaggio
	public static final String TEXT_FIELD = "text";
	private static String text; 
//	Campi necessari per inviare una foto
	public static final String PHOTO_FIELD = "photo";
	private static File photo; 
//	Campi opzionali per altre operazioni
	public static final String REPLYTOMESSAGEID_FIELD = "reply_to_message_id";
	private static Integer replyToMessageId; 
	
	
	public static void main(String[] args) {
	
		text = "tento di mandarti una foto";
		chatId = 220202318;
		String photoPath = "D:\\workspaceIntelliJ\\TelegramBot\\src\\main\\resources\\photo.jpg";
		
		
		try {
			
			sendMessage();
			
			sendPhoto(photoPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	public static void sendMessage() throws ClientProtocolException, IOException{
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
		CloseableHttpResponse response = httpclient.execute( httppost);
		
		System.out.println(response.getStatusLine());
	}

	public static void sendPhoto(String photoPath) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpHost proxy = new HttpHost("10.68.64.37", 8081);
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		
		/// Create Http POST method and set correct headers
		String url = BASEURL + TOKEN + "/" + SEND_PHOTO_PATH;
		System.out.println(url);
		HttpPost httppost = new HttpPost(url);
//		httppost.addHeader("Content-type", "multipart/form-data");
		httppost.addHeader("charset", "UTF-8");

		/// Create list of parameters
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		/// Add chatid to the list
		nameValuePairs.add(new BasicNameValuePair(CHATID_FIELD, chatId + ""));
		
		HttpEntity multiPartEntity = MultipartEntityBuilder
			    .create()
			    .addBinaryBody(PHOTO_FIELD, new File(photoPath))
			    .build();
		
		/// Set list of parameters as entity of the Http POST method
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		
		httppost.setEntity(multiPartEntity);
		
		httppost.setConfig(config);
		
		CloseableHttpResponse response = httpclient.execute( httppost);
		
		System.out.println(response.getStatusLine());
	}
	
	
}

/*
var client = new RestClient("https://api.telegram.org/bot%3Ctoken%3E/sendPhoto");
var request = new RestRequest(Method.POST);
request.AddHeader("postman-token", "7bb24813-8e63-0e5a-aa55-420a7d89a82c");
request.AddHeader("cache-control", "no-cache");
request.AddHeader("content-type", "multipart/form-data; boundary=---011000010111000001101001");
request.AddParameter("multipart/form-data; boundary=---011000010111000001101001", "-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"photo\"; filename=\"[object Object]\"\r\nContent-Type: false\r\n\r\n\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"chat_id\"\r\n\r\n2314123\r\n-----011000010111000001101001--", ParameterType.RequestBody);
IRestResponse response = client.Execute(request);
*/