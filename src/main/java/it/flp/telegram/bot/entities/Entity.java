package it.flp.telegram.bot.entities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Entity implements Serializable {

	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("offset")
	@Expose
	private long offset;
	@SerializedName("length")
	@Expose
	private long length;
	private final static long serialVersionUID = 1283964933929331780L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Entity() {
		super();
	}

	/**
	 * 
	 * @param length
	 * @param offset
	 * @param type
	 */
	public Entity(String type, long offset, long length) {
		super();
		this.type = type;
		this.offset = offset;
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Entity withType(String type) {
		this.type = type;
		return this;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public Entity withOffset(long offset) {
		this.offset = offset;
		return this;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public Entity withLength(long length) {
		this.length = length;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(type).append(offset).append(length).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Entity) == false) {
			return false;
		}
		Entity rhs = ((Entity) other);
		return new EqualsBuilder().append(type, rhs.type).append(offset, rhs.offset).append(length, rhs.length)
				.isEquals();
	}

}