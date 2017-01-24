package it.flp.telegram.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.flp.telegram.bot.constants.Constants;
import it.flp.telegram.bot.modules.Sender;
import it.flp.telegram.bot.modules.Updater;

public class Bot {
	public final String TOKEN;
	private static final Logger log = LoggerFactory.getLogger(Updater.class);
	private Updater updater;
	private Sender sender;
	
	public Bot (String token){
		this.TOKEN = token;
		Constants.TOKEN = this.TOKEN;
		this.updater = new Updater();
		this.sender = new Sender();
	}

	public Updater getUpdater() {
		return updater;
	}

	public Sender getSender() {
		return sender;
	}
	
	

}
