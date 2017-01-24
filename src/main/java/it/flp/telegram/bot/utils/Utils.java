package it.flp.telegram.bot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import it.flp.telegram.bot.entities.Entity;

public class Utils {
	
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
	
	public static String getBotcommand(Entity entity, String message){
		String command = message.substring((int)entity.getOffset(), (int)entity.getLength());
		return command;
	}
}
