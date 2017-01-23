package it.flp.telegram.bot.test;

import it.flp.telegram.bot.entities.UpdateMessage;
import it.flp.telegram.bot.entities.UpdateResponse;
import it.flp.telegram.bot.modules.Updater;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
		Updater updater = new Updater();
		
		UpdateResponse l = updater.getUpdates();
		
		System.out.println("ci sono "+l.getResult().size()+" messaggi");
		
		for (UpdateMessage mex : l.getResult()) {
			System.out.println("chat id: "+mex.getMessage().getChat().getId());
		}
		
	}

}
