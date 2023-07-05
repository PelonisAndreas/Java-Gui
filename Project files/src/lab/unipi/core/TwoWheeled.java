package lab.unipi.core;

public class TwoWheeled extends Vehicle {
	private double seat_height;
	private String luggage_place;
	
	
	public TwoWheeled (String code,String model,String fuel,String type,int cube,double rent_price,double seat_height,String luggage_place) {
		super(code, model, fuel, type, cube, rent_price);
		this.seat_height=seat_height;
		this.luggage_place=luggage_place;
	}


	public double getSeat_height() {
		return seat_height;
	}


	public void setSeat_height(double seat_height) {
		this.seat_height = seat_height;
	}


	public String getLuggage_place() {
		return luggage_place;
	}


	public void setLuggage_place(String luggage_place) {
		this.luggage_place = luggage_place;
	}
}
