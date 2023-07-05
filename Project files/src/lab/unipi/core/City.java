package lab.unipi.core;

public class City {
	private int code;//μοναδικος κωδικος
	private String city_name;
	private static int max_Code=0; //Counter για την δημιουργια του μοναδικου κωδικου.
	
	public City(String city_name) {
		this.code = max_Code+1;
		this.city_name = city_name;
		max_Code=max_Code+1;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}	
	
}
