package Assignment10;

import java.util.Comparator;

public class ContactComparator implements Comparator<Contact> {

	@Override
	public int compare(Contact o1, Contact o2) {
		// TODO Auto-generated method stub
		return o1.getContactName().compareTo(o2.getContactName());
	}
	

}
