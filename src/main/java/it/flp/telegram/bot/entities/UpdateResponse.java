package it.flp.telegram.bot.entities;

import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * API Telegram for /getUpdates
 * 
 * @author Christian Lusardi
 * @version 1.0
 *
 */
public class UpdateResponse implements Serializable {
	
	private static final long serialVersionUID = -3462700368473359945L;

	
	@SerializedName("ok")
	@Expose
	private boolean ok;
	
	@SerializedName("result")
	@Expose
	@Valid
	private List<UpdateMessage> result = null;
	
	

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public UpdateResponse() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param result
	 * @param ok
	 */
	public UpdateResponse(boolean ok, List<UpdateMessage> result) {
		super();
		this.ok = ok;
		this.result = result;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public UpdateResponse withOk(boolean ok) {
		this.ok = ok;
		return this;
	}

	public List<UpdateMessage> getResult() {
		return result;
	}

	public void setResult(List<UpdateMessage> result) {
		this.result = result;
	}

	public UpdateResponse withResult(List<UpdateMessage> result) {
		this.result = result;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(ok).append(result).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof UpdateResponse) == false) {
			return false;
		}
		UpdateResponse rhs = (UpdateResponse) other;
		return new EqualsBuilder().append(ok, rhs.ok).append(result, rhs.result).isEquals();
	}

}