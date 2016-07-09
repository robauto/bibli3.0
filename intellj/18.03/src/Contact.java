/**
 * purpose- arrange list and get a list from a category
 * Created by pi on 5/19/16.
 */
public class Contact {
	private String Name ;
	private String relation ;
	private String bday ;
	private String emailAddress ;
	private String phoneNumber ;
	
	
	
	//Constructor 
	public Contact(String name, String relation, String bday,
			String  phoneNumber, String emailAddress) {
		
		this.Name = name;
		this.relation = relation;
		this.bday = bday;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}
	
	
	//generate setter and getter
	
	
	public String getName() {
		return Name;
	}
	

	public void setName(String name) {
		Name = name;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getBday() {
		return bday;
	}
	public void setBday(String bday) {
		this.bday = bday;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	public String toString() {
		return " Name=" + Name + ", relation=" + relation + ", bday="
				+ bday + ", emailAddress=" + emailAddress + ", phoneNumber="
				+ phoneNumber + " ";
	
	
	}
	

}
