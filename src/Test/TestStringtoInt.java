package Test;

public class TestStringtoInt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String days="AUD $ 235";
		int nRooms=Integer.parseInt(days.replaceAll("[\\D]", ""));
		System.out.println(nRooms);
	}

}
