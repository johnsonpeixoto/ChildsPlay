import java.util.concurrent.Semaphore;


public class Main {

	public static void main(String[] args) {
		int capacity = 5;
		Parque park = new Parque(capacity);
		park.addKid(true,"vinicius",10,10);
		park.addKid(true,"vini",10,10);
		park.addKid(false,"vin",10,10);
		park.addKid(true,"icius",10,10);
	}
}
