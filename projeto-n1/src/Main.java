import java.util.concurrent.Semaphore;


public class Main {

	public static void main(String[] args) {
		int capacity = 5;
		public Semaphore mutex = new Semaphore(1);
		public Semaphore bolasCesto = new Semaphore(0); // bolas no cesto
		public Semaphore vagas = new Semaphore(capacity); // vagas

		Kid t1 = new Kid ("Ceara");
		Kid t2 = new Kid ("Fortaleza");
		//t1.start ();
		//t2.start ();

		/*
		t1.setPriority(1);
		t2.setPriority(5);
		*/
	}
}
