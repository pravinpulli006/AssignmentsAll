package Assignment09;

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
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



public class OperationsClass {

	public static Connection con=null;
	
	public static ArrayList<Movie> Movies=new ArrayList<Movie>();
	
	public static ArrayList<Movie> populateMovies(String filename){
		
		
		try {
			FileInputStream fi=new FileInputStream(filename);
			
			BufferedReader br=new BufferedReader(new InputStreamReader(fi));
			
			String line;
			
			while((line=br.readLine())!=null) {
				
				String[] words=line.split(",");
				
				ArrayList<String> cast=new ArrayList<String>();
				for(int i=5;i<words.length-2;i++) {
					cast.add(words[i]);
				}
				
				String d=words[4];
				System.out.println(d);
				Movie m=new Movie(Integer.parseInt(words[0]),words[1],words[2],words[3],Date.valueOf(d),cast,
						Double.parseDouble(words[words.length-2]),
						Double.parseDouble(words[words.length-1]));
				Movies.add(m);
			}
		}
		catch(Exception e) {
			
			System.out.println(e);
		}
		return Movies;
	}
	
	public static void allMoviesInDB(ArrayList<Movie> movies) throws ClassNotFoundException, SQLException {
		
		
		con=GetConnection.getConnection();
		
		
		
		
		
		for(int i=0;i<movies.size();i++)
		{
			String str="insert into Movies values(?,?,?,?,To_Date(?,'DD-MM-YYYY'),?,?,?)";
			PreparedStatement ps=con.prepareStatement(str);
			ps.setInt(1, Movies.get(i).getMid());
			ps.setString(2, Movies.get(i).getMname());
			ps.setString(3, Movies.get(i).getCategory());
			ps.setString(4, Movies.get(i).getLanguage());
			ps.setDate(5, (java.sql.Date) Movies.get(i).getReleaseDate());
			
			
			ArrayList m=Movies.get(i).getCasting();
			String cast = "";
			for(int j=0;j<m.size();j++) {
				cast+=m.get(j)+" ";
			}
			ps.setString(6,cast);
			ps.setDouble(7, Movies.get(i).getRating());
			ps.setDouble(8, Movies.get(i).getCollection());
			ps.executeUpdate();
			
			System.out.println("Record Inserted...."+(i+1));
			
		}
		
		con.close();
	}
	
	public static void display(ArrayList<Movie> movies) {
		
		for(int i=1;i<movies.size();i++)
		{
			System.out.println(movies.get(i));
		}
	}
	
	public static void addMovieinList(ArrayList<Movie> movies,String filename) throws IOException, ClassNotFoundException, SQLException {
		
		FileInputStream fi=new FileInputStream(filename);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(fi));
		
		String line;
		int no = 0;
		while((line=br.readLine())!=null) {
			
			String[] words=line.split(",");
			no=Integer.parseInt(words[0]);
		}
		
		no=no+1;
		
		System.out.println("Enter Movie Name:");
		BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
		
		String name,cat,lan,today; 
		ArrayList<String> casting = new ArrayList<String>();
		double rating,collection;
		
		name=br1.readLine();
		int flag=0;
		
		for(int i=0;i<movies.size();i++)
		{
			if(movies.get(i).getMname().contains(name)) {
				System.out.println("Movie is Already Exist in the List..");
				flag=1;
			}
		}
		
		if(flag==0) {
			System.out.println("Enter Movie Category : ");
			cat=br1.readLine();
			System.out.println("Enter Movie Language : ");
			lan=br1.readLine();
			System.out.println("Enter Release Date in 'YYYY-MM-DD' :");
			today=br1.readLine();
			String data=no+","+name+","+cat+","+lan+","+today;
			
			System.out.println("How Many Characters name want to enter:");
			int count=Integer.parseInt(br1.readLine());
			for(int k=0;k<count;k++) {
				System.out.println("Enter "+(k+1)+" Name:");
				String data1=br1.readLine();
				data=data+","+data1;
				casting.add(data1);
			}
			
			System.out.println("Enter Rating :");
			rating=Double.parseDouble(br1.readLine());
			data=data+","+rating;
			System.out.println("Enter Total Collection  :");
			collection=Double.parseDouble(br1.readLine());
			data=data+","+collection;
			
		System.out.println(data);
		Movie m=new Movie(no,name,cat,lan,Date.valueOf(today),casting,rating,collection);
		Movies.add(m);
		int i=Movies.size()-1;
		System.out.println(i);
		
		con=GetConnection.getConnection();
		
		String str="insert into Movies values(?,?,?,?,To_Date(?,'DD-MM-YYYY'),?,?,?)";
		PreparedStatement ps=con.prepareStatement(str);
		ps.setInt(1, Movies.get(i).getMid());
		ps.setString(2, Movies.get(i).getMname());
		ps.setString(3, Movies.get(i).getCategory());
		ps.setString(4, Movies.get(i).getLanguage());
		ps.setDate(5, (java.sql.Date) Movies.get(i).getReleaseDate());
		
		
		ArrayList m1=Movies.get(i).getCasting();
		String cast = "";
		for(int j=0;j<m1.size();j++) {
			cast+=m1.get(j)+" ";
		}
		ps.setString(6,cast);
		ps.setDouble(7, Movies.get(i).getRating());
		ps.setDouble(8, Movies.get(i).getCollection());
		ps.executeUpdate();
		
		System.out.println("Record Inserted into database...."+(i+1));
		
		
		
		con.close();
		
		File file =new File("D:\\Java\\Ass(09)_Pravin_Pulli\\src\\Assignment09\\Movies.txt");
		FileWriter fw = new FileWriter(file,true);
    	//BufferedWriter writer give better performance
    	BufferedWriter bw = new BufferedWriter(fw);
    	PrintWriter pw = new PrintWriter(bw);
    	
    	pw.println(data);
		pw.close();
		
		
		
		}
		
		
		
		
	}
	
	public static void SerializeMovies(ArrayList<Movie> movies,String filename) throws IOException {
		
		File f=new File(filename);
		
		FileOutputStream fos=new FileOutputStream(f);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		System.out.println(movies.size());
		for(int i=0;i<movies.size();i++) {
			Movie m=movies.get(i);
			
			oos.writeObject(m);
			System.out.println("Done "+(i+1));
		}
		
		
	}
	
	public static ArrayList<Movie> deserializeMovie(String filename) throws ClassNotFoundException, IOException{
		
		
		ArrayList<Movie> arr=new ArrayList<Movie>();
		File f=new File(filename);
		
		FileInputStream fos=new FileInputStream(f);
		ObjectInputStream oos=new ObjectInputStream(fos);
		
		System.out.println("Deserialization started...");
		
		for(int i=0;i<6;i++) {
			Movie m=(Movie) oos.readObject();
			arr.add(m);
			
		}
		
		return arr;
		
		
	}
	
	
	public static ArrayList<Movie> getMoviesRealeasedInYear(int year){
		
		ArrayList<Movie> m=new ArrayList<Movie>();
		
		
		for(int i=0;i<Movies.size();i++)
		{
			if((Movies.get(i).getReleaseDate().getYear()+1900)==year)
			{
				//System.out.println(Movies.get(i));
				m.add(Movies.get(i));
				
			}
			
		}
		
		
		
		return m;
		
		
	}
	
	public static ArrayList<Movie> getMoviesByActor(String... Actornames){
		
		ArrayList<Movie> m=new ArrayList<Movie>();
		ArrayList<String> n=new ArrayList<String>();
		
		
		for(String name:Actornames) {
			
		
			for(int i=0;i<Movies.size();i++)
			{
				if(Movies.get(i).getCasting().contains(name))
				{
					if(!n.contains(Movies.get(i).getMname())) {
						m.add(Movies.get(i));
						n.add(Movies.get(i).getMname());
					}
					//System.out.println(Movies.get(i));
					
				}
				
			}
			
		}
		
		
		
		return m;
		
		
	}
	
	public static void UpdateRating(ArrayList<Movie> movies,double rating,String mname ) throws ClassNotFoundException, SQLException {
		
		int flag=0;int i;
		for(i=0;i<movies.size();i++) {
			
			if(Movies.get(i).getMname().equalsIgnoreCase(mname)) {
				
				Movies.get(i).setRating(rating);
				
				System.out.println("Successfully Updated Rating for "+mname);
				flag=1;
				
				con=GetConnection.getConnection();
				String up="update Movies set rate=? where m_name=?";
				
				PreparedStatement ps=con.prepareStatement(up);
				
				ps.setDouble(1, rating);
				ps.setString(2, mname);
				
				ps.executeUpdate();
				con.close();
				
				break;
			}
		}
		
		if(flag==0) {
			System.out.println("Enetered Movie Name not Present...");
		}
		else {
			System.out.println("Updated Data :");
			System.out.println(Movies.get(i));
		}
		
		
		
	}
	
	
	public static void UpdateCollections(ArrayList<Movie> movies,double Collection,String mname ) throws ClassNotFoundException, SQLException {
		
		int flag=0;int i;
		for(i=0;i<movies.size();i++) {
			
			if(Movies.get(i).getMname().equalsIgnoreCase(mname)) {
				
				Movies.get(i).setCollection(Collection);
				
				System.out.println("Successfully Updated Business Done for "+mname);
				flag=1;
				
				con=GetConnection.getConnection();
				String up="update Movies set collection=? where m_name=?";
				
				PreparedStatement ps=con.prepareStatement(up);
				
				ps.setDouble(1, Collection);
				ps.setString(2, mname);
				
				ps.executeUpdate();
				con.close();
				
				break;
			}
		}
		
		if(flag==0) {
			System.out.println("Enetered Movie Name not Present...");
		}
		else {
			System.out.println("Updated Data :");
			System.out.println(Movies.get(i));
		}
		
		
		
	}
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		String a="D:\\Java\\Ass(09)_Pravin_Pulli\\src\\Assignment09\\Movies.txt";
		String b="D:\\Java\\Ass(09)_Pravin_Pulli\\src\\Assignment09\\Object.txt";
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		
		int flagfor1=0;
		int flagfor2=0;
		while(true) {
			System.out.println("=====================================================================================");
			System.out.println(" 1. Read Data From File and Put into List 2. store Movie Data into Database.."
						  + "\n 3.Add Movie into List and Datatbase      "
						  + "\n 4. Serialize Data      5. Deserialze Data "
						  + "\n 6. Find Movies by year 7.Find Movies by Actors"
						  + "\n 8.Update Movie Rating  9.Update Businees Done 10.Dispaly All Movie Details 11.Exit "
						  + "\n Enter Choice  :  ");
			int ch=Integer.parseInt(br.readLine());
			switch(ch) {
			case 1:System.out.println("--------------------------------------------------------------------------------");
					if(flagfor1!=1) {
						ArrayList<Movie> m=populateMovies(a);
						
					}else {
						System.out.println("You are not allowed To call 1 again its already Done .");
					}
				
					flagfor1=1;
					break;
			case 2:
				System.out.println("--------------------------------------------------------------------------------");
					if(flagfor2!=1) {
						
						allMoviesInDB(Movies);
					}else {
						System.out.println("You are not allowed To call 2 again its already Done .");
					}
					flagfor2=1;
					break;
			case 3:
				System.out.println("--------------------------------------------------------------------------------");
					addMovieinList(Movies,a);
					break;
			case 4:
				System.out.println("--------------------------------------------------------------------------------");
					SerializeMovies(Movies,b);
					break;
			case 5:
				System.out.println("--------------------------------------------------------------------------------");
						ArrayList<Movie> m1=deserializeMovie(b);
						for(int i=0;i<m1.size();i++) {
							
							System.out.println(m1.get(i));
						}
					break;
					
				case 6:
						System.out.println("--------------------------------------------------------------------------------");
						System.out.println("Enter Year :");
						int year=Integer.parseInt(br.readLine());
						ArrayList<Movie> m21=getMoviesRealeasedInYear(year);
						for(int i=0;i<m21.size();i++) {
							
							System.out.println(m21.get(i));
						}
				
						break;
			case 7:
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Enter 3 Character name :");
					String n1=br.readLine();
					String n2=br.readLine();
					String n3=br.readLine();
					
					ArrayList<Movie> m2=getMoviesByActor(n1,n2,n3);
					for(int i=0;i<m2.size();i++) {
						
						System.out.println(m2.get(i));
					}
					
					break;
			case 8:
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Enter Movie name :");
					String name1=br.readLine();
					
					System.out.println("Enter Movie rating  :");
					double rating =Double.parseDouble(br.readLine());
					
					UpdateRating(Movies,rating,name1);
						break;
			case 9:
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Enter Movie name :");
					String name2=br.readLine();
					
					System.out.println("Enter Movie Business Done Total :");
					double money =Double.parseDouble(br.readLine());
					
					UpdateCollections(Movies,money,name2);
					break;
			case 10:
					System.out.println("=============================All Movie Details================================ ");
					for(int i=0;i<Movies.size();i++) {
						System.out.println(Movies.get(i));
					}
					break;
			case 11:System.exit(1);
			default:System.out.println("Enter valid Option ....");
			
			}
			
		}
		
		
		
		
	}

}
