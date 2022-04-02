package com.onlineexam.app.dto.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserMasterCreateDTO {

	@NotBlank(message = "Name is required")
	@Length(max = 80, message = "Maximum length should not exceed 80")
	private String userName;
	@Digits(fraction = 0, integer = 10)
	@NotBlank(message = "Mobile Number is required")
	@Length(max = 10, message = "Mobile Number length should be 10")
	@Pattern(regexp = "[6-9][0-9]{9}", message = "Mobile number should be started with 6,7,8 or 9 and length should be 10 digit")
	private long mobileNumber;
	@NotBlank(message = "Email is required")
	@Length(max = 80, message = "Maximum length should not exceed 80")
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$", message = "Please enter valid email address")
	private String email;
	@Min(value = 1, message = "Role is required")
	private int roleId;
	private long createdBy;
	@JsonIgnore
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
