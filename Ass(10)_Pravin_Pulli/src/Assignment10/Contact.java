package Assignment10;

import java.io.Serializable;
import java.util.ArrayList;

public class Contact implements Serializable{

	int ContactID;
	String ContactName;
	String Email;
	ArrayList<String> contactNumber;
	public Contact(int contactID, String contactName, String email, ArrayList<String> contactNumber) {
		super();
		ContactID = contactID;
		ContactName = contactName;
		Email = email;
		this.contactNumber = contactNumber;
	}
	public int getContactID() {
		return ContactID;
	}
	public void setContactID(int contactID) {
		ContactID = contactID;
	}
	public String getContactName() {
		return ContactName;
	}
	public void setContactName(String contactName) {
		ContactName = contactName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public ArrayList<String> getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(ArrayList<String> contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Override
	public String toString() {
		return "Contact [ContactID=" + ContactID + ", ContactName=" + ContactName + ", Email=" + Email
				+ ", contactNumber=" + contactNumber + "]";
	}
	
}
