package it.flp.telegram.bot.entities;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Messagge's chat
 * 
 * @author Christian Lusardi
 * @version 1.0
 *
 */
public class Message implements Serializable {
	
	private static final long serialVersionUID = -7522018692433776909L;

	@SerializedName("message_id")
	@Expose
	private long messageId;
	
	@SerializedName("from")
	@Expose
	@Valid
	private Sender sender;
	
	@SerializedName("chat")
	@Expose
	@Valid
	private Chat chat;
	
	@SerializedName("date")
	@Expose
	private long date;
	
	@SerializedName("text")
	@Expose
	private String text;
	
	@SerializedName("entities")
	@Expose
	@Valid
	private List<Entity> entities = null;
	
	

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Message() {
	}

	/**
	 * 
	 * @param text
	 * @param chat
	 * @param from
	 * @param date
	 * @param messageId
	 * @param entities
	 */
	public Message(long messageId, Sender from, Chat chat, long date, String text, List<Entity> entities) {
		super();
		this.messageId = messageId;
		this.sender = from;
		this.chat = chat;
		this.date = date;
		this.text = text;
		this.entities = entities;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public Message withMessageId(long messageId) {
		this.messageId = messageId;
		return this;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender from) {
		this.sender = from;
	}

	public Message withFrom(Sender from) {
		this.sender = from;
		return this;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public Message withChat(Chat chat) {
		this.chat = chat;
		return this;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public Message withDate(long date) {
		this.date = date;
		return this;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Message withText(String text) {
		this.text = text;
		return this;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public Message withEntities(List<Entity> entities) {
		this.entities = entities;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(messageId).append(sender).append(chat).append(date).append(text)
				.append(entities).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Message) == false) {
			return false;
		}
		Message rhs = ((Message) other);
		return new EqualsBuilder().append(messageId, rhs.messageId).append(sender, rhs.sender).append(chat, rhs.chat)
				.append(date, rhs.date).append(text, rhs.text).append(entities, rhs.entities).isEquals();
	}

}