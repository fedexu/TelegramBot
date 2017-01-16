package it.flp.telegram.bot.entities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Chat implements Serializable {
	
	private static final long serialVersionUID = 4534097250459310969L;


	@SerializedName("id")
	@Expose
	private long id;
	
	@SerializedName("first_name")
	@Expose
	private String firstName;
	
	@SerializedName("last_name")
	@Expose
	private String lastName;
	
	@SerializedName("username")
	@Expose
	private String username;
	
	@SerializedName("type")
	@Expose
	private String type;
	
	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Chat() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @param lastName
	 * @param username
	 * @param type
	 * @param firstName
	 */
	public Chat(long id, String firstName, String lastName, String username, String type) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Chat withId(long id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Chat withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Chat withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Chat withUsername(String username) {
		this.username = username;
		return this;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Chat withType(String type) {
		this.type = type;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(firstName).append(lastName).append(username).append(type)
				.toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Chat) == false) {
			return false;
		}
		Chat rhs = ((Chat) other);
		return new EqualsBuilder().append(id, rhs.id).append(firstName, rhs.firstName).append(lastName, rhs.lastName)
				.append(username, rhs.username).append(type, rhs.type).isEquals();
	}

}