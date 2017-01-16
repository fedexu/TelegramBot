package it.flp.telegram.bot.entities;

import java.io.Serializable;
import javax.validation.Valid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * 
 * @author Christian Lusardi
 * @version 1.
 *
 */
public class UpdateMessage implements Serializable {
	
	private static final long serialVersionUID = -8237374983099572551L;

	
	@SerializedName("update_id")
	@Expose
	private long updateId;
	
	
	@SerializedName("message")
	@Expose
	@Valid
	private Message message;
	

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public UpdateMessage() {
		super();
	}

	/**
	 * 
	 * @param message
	 * @param updateId
	 */
	public UpdateMessage(long updateId, Message message) {
		super();
		this.updateId = updateId;
		this.message = message;
	}

	public long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(long updateId) {
		this.updateId = updateId;
	}

	public UpdateMessage withUpdateId(long updateId) {
		this.updateId = updateId;
		return this;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public UpdateMessage withMessage(Message message) {
		this.message = message;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(updateId).append(message).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof UpdateMessage) == false) {
			return false;
		}
		UpdateMessage rhs = (UpdateMessage) other;
		return new EqualsBuilder().append(updateId, rhs.updateId).append(message, rhs.message).isEquals();
	}

}