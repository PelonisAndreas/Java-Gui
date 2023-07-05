package lab.unipi.core;

public class Client {

	private String AM;
	private String licenceNumber;
	private String name;
	private String surname;
	private String email;
	private String address;
	private long phoneNumber;
	
	
	public Client( String AM , String licenceNumber , String name , String surname , String email , String address , long phoneNumber)
	{
		this.AM =AM;
		this.licenceNumber = licenceNumber;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}


	public String getAM() {
		return AM;
	}


	public void setAM(String aM) {
		AM = aM;
	}


	public String getLicenceNumber() {
		return licenceNumber;
	}


	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
	
	
}
