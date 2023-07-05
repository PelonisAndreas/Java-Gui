package lab.unipi.core;

public class Car extends Vehicle {
	private int seat_number;
	private int door_number;
	
	public Car (String code,String model,String fuel,String type,int cube,double rent_price,int seat_number,int door_number) {
		super(code, model, fuel, type, cube, rent_price);
		this.seat_number=seat_number;
		this.door_number=door_number;
	}
	
	public int getSeat_number() {
		return seat_number;
	}

	public void setSeat_number(int seat_number) {
		this.seat_number = seat_number;
	}

	public int getDoor_number() {
		return door_number;
	}

	public void setDoor_number(int door_number) {
		this.door_number = door_number;
	}

}
