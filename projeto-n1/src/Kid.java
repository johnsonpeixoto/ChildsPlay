import java.util.concurrent.Semaphore;

public class Kid extends Thread {

	private boolean haveBall;
    private String nome;
    private long playTime;
    private long restTime;

	public Kid(String nome, boolean haveBall, long playTime, long restTime) {
		this.nome;
		this.haveBall = haveBall;
		this.playTime = playTime;
		this.restTime = restTime;
	}

	// Função para criar um tempo usando CPU Bound
	public void cpuBound(long tempo){
		long t = System.currentTimeMillis();
		long elapsed;
		while (true) {
			elapsed = System.currentTimeMillis() - t;
			System.out.println(elapsed/1000);
		}
	}

	public void toPlay(long playTime){
	    cpuBound(playTime);
	}

	public void toRest(long restTime){
		cpuBound(restTime);
	}

	public void guardaBola() {
		//animaçao
    }

    public void ficaQuieta() {
		//animaçao
	}

	public void comBola() {
	    toPlay(); //brinca
        //down(slots);
        //down(mutex);
        guardaBola();
        //up(mutex);
        //up(bolasCesto);
        ficaQuieta();
    }

    public void semBola() {
       // down(bolasCesto);
        //down(mutex);
        //cesta.retiraBola();
        //up(mutex);
        //up(vagas);
	}


	public void run() {
		while (true) {
			if (this.haveBall) {
				comBola();
			} else {
				semBola();
			}
		}
	}
}