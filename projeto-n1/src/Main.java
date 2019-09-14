import java.util.concurrent.Semaphore;


public class Main {

	public static void main(String[] args) {
		int capacity = 5;
		Parque park = new Parque(capacity);
		park.addKid(true,"vinicius",10,10);
		park.addKid(true,"hilbert",10,10);
		park.addKid(false,"johnson",10,10);
		park.addKid(true,"carol",10,10);
	}
}
