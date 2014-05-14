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
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
