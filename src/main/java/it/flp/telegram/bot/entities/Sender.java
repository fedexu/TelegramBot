package it.flp.telegram.bot.entities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Sender of a message
 * 
 * @author Christian Lusardi
 * @version 1.0
 *
 */
public class Sender implements Serializable {
	
	private static final long serialVersionUID = 2228368312763430495L;
	

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
	
	

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Sender() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @param lastName
	 * @param username
	 * @param firstName
	 */
	public Sender(long id, String firstName, String lastName, String username) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sender withId(long id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Sender withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Sender withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Sender withUsername(String username) {
		this.username = username;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(firstName).append(lastName).append(username).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Sender) == false) {
			return false;
		}
		Sender rhs = ((Sender) other);
		return new EqualsBuilder().append(id, rhs.id).append(firstName, rhs.firstName).append(lastName, rhs.lastName)
				.append(username, rhs.username).isEquals();
	}

}