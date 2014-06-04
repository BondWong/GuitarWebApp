package security.validation;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class UserRep {
	@NotBlank(groups={LoginGroup.class})
	@Pattern(regexp = "\\d{10}", groups={LoginGroup.class})
	private String ID;
	@NotBlank(groups={LoginGroup.class})
	@Pattern(regexp = "\\w{8,16}", groups={LoginGroup.class})
	private String password;
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
}	
