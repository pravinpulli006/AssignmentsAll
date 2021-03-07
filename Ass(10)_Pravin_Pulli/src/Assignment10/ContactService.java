package Assignment10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class ContactService {

	
	static ArrayList<Contact> contact =new ArrayList<Contact>();
	static int addcontactflag=0;
	static Set<Contact> c=new HashSet<Contact>();
	
	public static void addContact(ArrayList<Contact> contact) throws IOException, ClassNotFoundException, SQLException {
		
		
//		if(addcontactflag==0) {
//			addcontactflag=1;
//		
//		FileInputStream fi=new FileInputStream("D:\\Java\\Ass(10)_Pravin_Pulli\\src\\Assignment10\\Contact.txt");
//		BufferedReader br=new BufferedReader(new InputStreamReader(fi));
//		
//		ArrayList<String> con=new ArrayList<String>();
//		
//		String line;
//		while((line=br.readLine())!=null) {
//			
//			String[] words=line.split(",");
//			String data="";
//			for(int i=3;i<words.length;i++) {
//				con.add(words[i]);
//				if(i==words.length-1) {
//					data=data+words[i];
//				}
//				else
//				{
//					data=data+words[i]+",";
//				}
//				
//			}
//			
//			Contact c=new Contact(Integer.parseInt(words[0]),words[1],words[2],con);
//			contact.add(c);
//			System.out.println("Data Inserted into ArrayList ...");
//			
//			Connection connect=GetConnection.getConnection();
//			
//			
//			String str="Insert into contact_tbl values(?,?,?,?)";
//			
//			PreparedStatement ps=connect.prepareStatement(str);
//			
//			ps.setInt(1, c.getContactID());
//			ps.setString(2,c.getContactName());
//			ps.setString(3, c.getEmail());
//			
//			if(data.length()==0) {
//				ps.setNull(4, Types.NULL);
//			}
//			else {
//				ps.setString(4,data);
//			}
//			
//			
//			ps.executeUpdate();
//			
//			System.out.println("Data Inserted into Database ...");
//			
//			connect.close();
//			
//		}
//		}
//		else {
//			
//			Connection con=GetConnection.getConnection();
//			PreparedStatement ps=con.prepareStatement("select max(contactid) from contact_tbl ;");
//			
//			ResultSet rs=ps.executeQuery();
//			int max=0;
//			while(rs.next()) {
//				max=rs.getInt(1);
//			}
//			max=max+1;
//			System.out.println("Enter Contact Name :");
//			BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
//			String name=br1.readLine();
//			System.out.println("Enter Contact Email :");
//			String Email=br1.readLine();
//			System.out.println("Enter how many contacts you want to enter :");
//			int size=Integer.parseInt(br1.readLine());
//			String data="";
//			
//			ArrayList<String> obj=new ArrayList<String>();
//			
//			for(int i=0;i<size;i++) {
//				
//				
//				
//				String c=br1.readLine();
//				obj.add(c);
//				if(i==size-1) {
//					data=data+c;
//				}
//				else
//				{
//					data=data+c+",";
//				}
//			}
//			PreparedStatement ps1=con.prepareStatement("Insert into contact_tbl values(?,?,?,?)");
//			ps1.setInt(1, max);
//			ps1.setString(2, name);
//			ps1.setString(3, Email);
//			
//			if(data.length()==0) {
//				ps.setNull(4, Types.NULL);
//			}
//			else {
//				ps.setString(4,data);
//			}
//			
//			ps1.executeUpdate();
//			
//			con.close();
//			
//			String data1=max+","+name+","+Email;
//			if(data.length()==0) {
//				data1=data1+data;
//			}
//			else {
//				data1=data1+","+data;
//			}
//			Contact c=new Contact(max,name,Email,obj);
//			contact.add(c);
//			
//			File f=new File("D:\\Java\\Ass(10)_Pravin_Pulli\\src\\Assignment10\\Contact.txt");
//			FileWriter fw = new FileWriter(f,true);
//	    	//BufferedWriter writer give better performance
//	    	BufferedWriter bw = new BufferedWriter(fw);
//	    	PrintWriter pw = new PrintWriter(bw);
//	    	
//	    	pw.println(data);
//			pw.close();		
//		}
		
		BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Contact Id:");
		int id=Integer.parseInt(br1.readLine());
		System.out.println("Enter Contact Name :");
		
		String name=br1.readLine();
		System.out.println("Enter Contact Email :");
		String Email=br1.readLine();
		System.out.println("Enter how many contacts you want to enter :");
		int size=Integer.parseInt(br1.readLine());
		String data="";
		
		ArrayList<String> obj=new ArrayList<String>();
		
		for(int i=0;i<size;i++) {
			
			
			
			String c=br1.readLine();
			obj.add(c);
			if(i==size-1) {
				data=data+c;
			}
			else
			{
				data=data+c+",";
			}
		}
		
		int flag=0;
		for(int i=0;i<contact.size();i++) {
			
			if(contact.get(i).getContactName().equalsIgnoreCase(name)) {
				System.out.println("Already Contact Exist in List ");
				flag=1;
			}
			
		}
		if(flag==0) {
			Contact c1=new Contact(id,name,Email,obj);
			contact.add(c1);
			System.out.println("Contact Successfully Added into ArrayList.....");
		}
		
		
		
	}
	public static void Removecontact(ArrayList<Contact> contacts) throws ContactNotFoundException, IOException,
																	SQLException, ClassNotFoundException{
	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter Contact Name to Delete");
		String name=br.readLine();
		
		int flag=0;
		for(int i=0;i<contacts.size();i++)
		{
			if(contacts.get(i).getContactName().equalsIgnoreCase(name)) {
				flag=1;
				contacts.remove(i);
				System.out.println("Contact Deleted From List .");
//				Connection connect=GetConnection.getConnection();
//				
//				
//				String str="delete from contact_tbl where contactName=?";
//				
//				PreparedStatement ps=connect.prepareStatement(str);
//				
//				ps.setString(1, name);
//				
//				ps.executeUpdate();
//				
//				connect.close();
//				
//				System.out.println("Contact Record Also deleted from Database...");
				break;
			}
			
			
		}
	
		if(flag==0) {
			throw new ContactNotFoundException();
		}
		
	}
	
	public static void searchContactbyName(ArrayList<Contact> contact,String name) throws ContactNotFoundException {
		
		int flag=0;
		
		for(int i=0;i<contact.size();i++) {
			if(contact.get(i).getContactName().equalsIgnoreCase(name)) {
				flag=1;
				System.out.println(contact.get(i));
			}
		}
		if(flag==0) {
			throw new ContactNotFoundException();
		}
		
	}
	
	public static void searchByNumber(ArrayList<Contact> contact,String number) throws ContactNotFoundException {
		int flag=0;
		
		for(int i=0;i<contact.size();i++) {
			ArrayList<String> nos=contact.get(i).getContactNumber();
			for(int j=0;j<nos.size();j++) {
				
				if(nos.get(i).contains(number)) {
					flag=1;
					System.out.println(contact.get(i));
				}
			}
		}
		
		
		if(flag==0) {
			throw new ContactNotFoundException();
		}
		
	}
	
	public static void addContactNumber(int id,String nu,ArrayList<Contact> contact) {
		
		int flag=0;
		ArrayList<String> numbers;
		for(int i=0;i<contact.size();i++) {
			
			if(contact.get(i).getContactID()==id) {
				flag=1;
				numbers=contact.get(i).getContactNumber();
				
				numbers.add(nu);
				contact.get(i).setContactNumber(numbers);
				
				
				System.out.println("Update Done in Object List not in database ..");
				
			}
			
		}
		if(flag==0) {
			System.out.println("Entered Contact Id is not Present....");
		}
	}
	
	public static void SortContactByNames(ArrayList<Contact> contact) {
		
		Collections.sort(contact,new ContactComparator());
		System.out.println("After Sorting Contacts By names:");
		for(Contact c:contact) {
			System.out.println(c);
		}
		
	}
	
	public static void readContactsFromFile(ArrayList<Contact> contact,String Filename) throws NumberFormatException, IOException {
		
		FileInputStream fi=new FileInputStream(Filename);
		BufferedReader br=new BufferedReader(new InputStreamReader(fi));
		String line;
		ArrayList<String> con = new ArrayList<String>();
		while((line=br.readLine())!=null) {
			
			String[] words=line.split(",");
			String data="";
			for(int i=3;i<words.length;i++) {
				con.add(words[i]);
				if(i==words.length-1) {
					data=data+words[i];
				}
				else
				{
					data=data+words[i]+",";
				}
				
			}
			
			Contact c=new Contact(Integer.parseInt(words[0]),words[1],words[2],con);
			contact.add(c);
		}
			
			System.out.println("Data Inserted into ArrayList from file  ...");
		
	}
	
	public static void serializeContactDetails(ArrayList<Contact> contacts , String filename) throws IOException {
		
		System.out.println("Serialization started.....");
		File f=new File(filename);
		
		FileOutputStream fos=new FileOutputStream(f);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		System.out.println(contacts.size());
		
		for(int i=0;i<contacts.size();i++) {
			
			Contact m=contacts.get(i);
			
			oos.writeObject(m);
			System.out.println("Done "+(i+1));
		}
		oos.close();
	}
	
	public static ArrayList<Contact> deserializeContact(String filename) throws IOException, ClassNotFoundException{
		
		ArrayList<Contact> arr=new ArrayList<Contact>();
		File f=new File(filename);
		
		FileInputStream fos=new FileInputStream(f);
		ObjectInputStream oos=new ObjectInputStream(fos);
		
		System.out.println("Deserialization started...");
		
		for(int i=0;i<contact.size();i++) {
			Contact m=(Contact) oos.readObject();
			arr.add(m);
			
		}
		oos.close();
		return arr;
	}
	
	
	public static Set<Contact> populateContactFromDb() throws ClassNotFoundException, SQLException{
		
		
		
		Connection con =GetConnection.getConnection();
		
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery("select * from Contact_tbl");
		
		while(rs.next()) {
			
			ArrayList<String> a=new ArrayList<String>();
			a.add(rs.getString(4));
			Contact c1=new Contact(rs.getInt(1),rs.getString(2),rs.getString(3),a);
			
			c.add(c1);
			
			
		}
		
		
		return c;
		
		
		
	}
	
	public static boolean addContacts(ArrayList<Contact> existingContact,Set<Contact> newContacts) throws IOException {
		
		
		boolean res=false;
		
		BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Contact Id:");
		int id=Integer.parseInt(br1.readLine());
		System.out.println("Enter Contact Name :");
		
		String name=br1.readLine();
		System.out.println("Enter Contact Email :");
		String Email=br1.readLine();
		System.out.println("Enter how many contacts you want to enter :");
		int size=Integer.parseInt(br1.readLine());
		String data="";
		
		ArrayList<String> obj=new ArrayList<String>();
		
		for(int i=0;i<size;i++) {
			
			
			
			String c=br1.readLine();
			obj.add(c);
			if(i==size-1) {
				data=data+c;
			}
			else
			{
				data=data+c+",";
			}
		}
		
		int flag=0;
		for(int i=0;i<existingContact.size();i++) {
			
			if(existingContact.get(i).getContactName().equalsIgnoreCase(name)) {
				System.out.println("Already Contact Exist in List ");
				flag=1;
			}
			
		}
		if(flag==0) {
			Contact c1=new Contact(id,name,Email,obj);
			existingContact.add(c1);
			newContacts.add(c1);
			res=true;
		}
		
		
		
		return res;
		
		
	}
	
	
	
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, NumberFormatException, IOException, ContactNotFoundException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Set<Contact> sc = new HashSet<Contact>();
		while(true) {
			System.out.println("====================================================================================================\n");
			
			System.out.println("1. Add Contact By using Object  2.Remove Contact From List   3.Search Contact By name "
					+ "\n4.Search Contact By Number or Partial number  5. Add Contact Number only To existing Account"
					+ "\n6. Sort Contact By Names  7. Read Contact From File   8. Serialize Contact Details.."
					+ "\n9.Deserialize Contact From File   10. Add Contact From Database into Set  11 . Add Contact into List and Set.."
					+ "\n 12  EXIT  ");
			System.out.println("Enter Your Choice ");
			int ch=Integer.parseInt(br.readLine());
			
			switch(ch) {
			
			case 1:
					System.out.println("-----------------------------------------------------------------------------------------------");
					addContact(contact);
					break;
			case 2:
					System.out.println("-----------------------------------------------------------------------------------------------");
					Removecontact(contact);	
					break;
			case 3:
					System.out.println("-----------------------------------------------------------------------------------------------");
					System.out.println("Enter Name to Search  ..  :");
					String name=br.readLine();
					searchContactbyName(contact,name);
					break;
			case 4:
					System.out.println("-----------------------------------------------------------------------------------------------");
					System.out.println("Enter Number:");
					String num=br.readLine();
					searchByNumber(contact,num);
					
					break;
			case 5:
					System.out.println("-----------------------------------------------------------------------------------------------");
					System.out.println("Enter Valid ID :");
					int id=Integer.parseInt(br.readLine());
					System.out.println("Enter Numbers if want to add more than one by coma(,) :");
					String num1=br.readLine();
					addContactNumber(id,num1,contact);
					break;
			case 6:
					System.out.println("-----------------------------------------------------------------------------------------------");
					SortContactByNames(contact);
					
					break;
					
			case 7:
					System.out.println("-----------------------------------------------------------------------------------------------");
					readContactsFromFile(contact,"D:\\Java\\Ass(10)_Pravin_Pulli\\src\\Assignment10\\Contact.txt");	
					break;		
			case 8:
					System.out.println("-----------------------------------------------------------------------------------------------");
					serializeContactDetails(contact ,"D:\\Java\\Ass(10)_Pravin_Pulli\\src\\Assignment10\\obj.txt");
					break;
			case 9:
					//System.out.println("-----------------------------------------------------------------------------------------------");
					System.out.println("-----------------------------------------------------------------------------------------------");
					ArrayList<Contact> c=deserializeContact("D:\\Java\\Ass(10)_Pravin_Pulli\\src\\Assignment10\\obj.txt");	
					System.out.println("After Deserialization Data : ");
					for(int i=0;i<c.size();i++) {
						System.out.println(c.get(i));
					}
					break;
		
			case 10:
					System.out.println("-----------------------------------------------------------------------------------------------");
					System.out.println("After Adding Records From Database to Set");
					 sc=populateContactFromDb();
					for(Contact x:sc) {
						System.out.println(x);
					}
					
					break;
			case 11:
					System.out.println("-----------------------------------------------------------------------------------------------");
					boolean a=addContacts(contact,sc);
					break;
			case 12:
					System.out.println("Have A nice Day Good byee......");
					System.exit(1);
					break;
			
			default : System.out.println("Please Enter Valid Option ...");
			
			}
		}
	}

}
