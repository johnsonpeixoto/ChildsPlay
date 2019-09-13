import java.util.concurrent.Semaphore;

public class Kid extends Thread {

	public Semaphore mutex;
	public Semaphore bolasCesto ;
	public Semaphore vagas;

	private boolean temBola;
    private String nome;
    private long tempoBrinca;
    private long tempoDorme;

	public Kid(Semaphore mutex, Semaphore bolasCesto, Semaphore vagas, boolean temBola, String nome, long tempoBrinca, long tempoDorme) {
		this.mutex = mutex;
		this.bolasCesto = bolasCesto;
		this.vagas = vagas;
		this.temBola = temBola;
		this.nome = nome;
		this.tempoBrinca = tempoBrinca;
		this.tempoDorme = tempoDorme;
	}

	// Função para criar um tempo usando CPU Bound
	/*
	public void cpuBound(long tempo){
		long t = System.currentTimeMillis();
		long elapsed;
		while (true) {
			elapsed = System.currentTimeMillis() - t;
			System.out.println(elapsed/1000);
		}
	}*/
	//funcao de teste p o programa
	public void cpuBound(long tempo){
		for (int count = 0 ; count < 100000; count++){
			System.out.println(nome + " " + count);
			for (int count2 = 0 ; count2 < 10000; count2++){
					long count3;
					count3 = count * count2;

			}
		}
	}

	//funcoes cpubound
	public void brincar(){
		cpuBound(tempoBrinca);
	}

	public void dormir(){
		cpuBound(tempoDorme);
	}

	//funcao consumidor
	public void pegaBola() throws InterruptedException {
		bolasCesto.acquire(); //down
		mutex.acquire();
		Cesta.bolas--;
		mutex.release();
		vagas.release();
	}

	//funcao produtor
	public void guardaBola() throws InterruptedException {
		 vagas.acquire();
		 mutex.acquire();
		 Cesta.bolas++;
		 mutex.release();
		 bolasCesto.release();
    }

    public void ficaQuieta() {
		//animaçao
	}



	public void run() {
		while (true) {
			if (this.temBola) {
				System.out.println(this.nome + " esta brincando");
				brincar();
				System.out.println(this.nome + " terminou de brincar");
				try {
					guardaBola();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(this.nome + " guardou a bola");
				this.temBola = false;
				System.out.println(this.nome + " esta dormindo");
				dormir();

			} else {
				System.out.println(this.nome + " vai pegar bola");
				try {
					pegaBola();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(this.nome + " pegou a bola");
				this.temBola = true;
			}
		}
	}
}