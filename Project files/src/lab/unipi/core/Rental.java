package lab.unipi.core;
import java.util.Date;

public class Rental {

	private int rentalCode;
	private Date dateOfRent;
	private Date dateOfReturn;
	private String clientFirstName;
	private String clientLastName;
	private String vehicleLicence;
	private String storeRent;
	private String storeReturn;
	private double cost;
	private static int MAX_CODE=0;//Counter για την δημιουργια του μοναδικου κωδικου.
	
	public Rental(Date dateOfRent, Date dateOfReturn, String clientFirstName, String clientLastName, String vehicleLicence, String storeRent, String storeReturn,double cost) {
		this.rentalCode=MAX_CODE+1;
		this.dateOfRent = dateOfRent;
		this.dateOfReturn = dateOfReturn;
		this.clientFirstName = clientFirstName;
		this.clientLastName = clientLastName;
		this.vehicleLicence = vehicleLicence;
		this.storeRent = storeRent;
		this.storeReturn = storeReturn;
		this.cost=cost;
		MAX_CODE++;
	}
	
	public int getRentalCode() {
		return rentalCode;
	}
	public void setRentalCode(int rentalCode) {
		this.rentalCode = rentalCode;
	}
	public Date getDateOfRent() {
		return dateOfRent;
	}
	public void setDateOfRent(Date dateOfRent) {
		this.dateOfRent = dateOfRent;
	}
	public Date getDateOfReturn() {
		return dateOfReturn;
	}
	public void setDateOfReturn(Date dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}
	public String getClientFirstName() {
		return clientFirstName;
	}
	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}
	public String getClientLastName() {
		return clientLastName;
	}
	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}
	public String getVehicleLicence() {
		return vehicleLicence;
	}
	public void setVehicleLicence(String vehicleLicence) {
		this.vehicleLicence = vehicleLicence;
	}
	public String getStoreRent() {
		return storeRent;
	}
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setStoreRent(String storeRent) {
		this.storeRent = storeRent;
	}
	public String getStoreReturn() {
		return storeReturn;
	}
	public void setStoreReturn(String storeReturn) {
		this.storeReturn = storeReturn;
	}

	
	
}