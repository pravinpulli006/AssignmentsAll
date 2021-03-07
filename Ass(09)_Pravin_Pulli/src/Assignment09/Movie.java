package Assignment09;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Movie implements Serializable  {

	private int mid;
	private String mname,category,language;
	private Date releaseDate;
	private ArrayList<String> casting;
	private double rating;
	private double collection;
	public Movie(int mid, String mname, String category, String language, Date d, ArrayList<String> casting,
			double rating, double collection) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.category = category;
		this.language = language;
		this.releaseDate = d;
		this.casting = casting;
		this.rating = rating;
		this.collection = collection;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public ArrayList<String> getCasting() {
		return casting;
	}
	public void setCasting(ArrayList<String> casting) {
		this.casting = casting;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getCollection() {
		return collection;
	}
	public void setCollection(double collection) {
		this.collection = collection;
	}
	@Override
	public String toString() {
		return "Movie [mid=" + mid + ", mname=" + mname + ", category=" + category + ", language=" + language
				+ ", releaseDate=" + releaseDate + ", casting=" + casting + ", rating=" + rating + ", collection="
				+ collection + "]";
	}
	
	
	
	
	
}
