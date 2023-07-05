package lab.unipi.core;

public class Vehicle {
	private String code;
	private String model;
	private String fuel;
	private String type;
	private int cube;
	private double rent_price;
	
	public Vehicle(String code,String model,String fuel,String type,int cube,double rent_price) {
		this.code=code;
		this.model=model;
		this.fuel=fuel;
		this.type=type;
		this.cube=cube;
		this.rent_price=rent_price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String lncode) {
		this.code = lncode;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCube() {
		return cube;
	}

	public void setCube(int cube) {
		this.cube = cube;
	}

	public double getRent_price() {
		return rent_price;
	}

	public void setRent_price(double rent_price) {
		this.rent_price = rent_price;
	}
}
