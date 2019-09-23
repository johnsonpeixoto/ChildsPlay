import java.util.concurrent.Semaphore;

public class Kid extends Thread {

	public Semaphore mutex;
	public Semaphore bolasCesto ;
	public Semaphore vagas;

	private boolean temBola;
    private String nome;
    private long tempoBrinca;
    private long tempoDorme;

    private long aux, aux2;

    private CallBacks calback;



	public Kid(Semaphore mutex, Semaphore bolasCesto, Semaphore vagas, boolean temBola, String nome, long tempoBrinca, long tempoDorme) {
		this.mutex = mutex;
		this.bolasCesto = bolasCesto;
		this.vagas = vagas;
		this.temBola = temBola;
		this.nome = nome;
		this.tempoBrinca = tempoBrinca;
		this.tempoDorme = tempoDorme;
		aux = tempoBrinca;
		aux2 = tempoDorme;
	}

	// Funcao para criar um tempo usando CPU Bound
	
	public void cpuBound(long tempo) {
	    System.out.println("Entrou no cpuBound!");
	    long tempoAtual = System.currentTimeMillis();
	    long tempoDecorrido = 0, milisegundos = 0;
	    while (tempoDecorrido < tempo) {
	    	milisegundos = (System.currentTimeMillis() - tempoAtual);
	    	if( (milisegundos / 1000) > tempoDecorrido) {
	    		System.out.println("Se passaram: " + tempoDecorrido + " segundos Crianca: " +
	    							nome);
	    		calback.updateTable();
	    	}
	    	if(temBola){
				tempoBrinca = tempoDecorrido;
			}else{
	    		tempoDorme = tempoDecorrido;
			}

	    	tempoDecorrido = milisegundos / 1000;
	    }
	    System.out.println("Saiu!");
        tempoBrinca = aux;
        tempoDorme = aux2;
	    
	}
	
	//funcoes cpubound
	public void brincar() {
		cpuBound(tempoBrinca);
	}

	public void dormir() {
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
		//animacao
	}



	public void run() {
		while (true) {
		    tempoBrinca = aux;
		    tempoDorme = aux2;
			if (this.temBola) {
				calback.updateLog(this.nome + " esta brincando\n");
				brincar();
				calback.updateLog(this.nome + " terminou de brincar\n");

				try {
					guardaBola();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                calback.updateCesta();
                calback.updateLog(this.nome + " guardou a bola\n");
				this.temBola = false;
				calback.updateLog(this.nome + " esta dormindo\n");
				dormir();
			} 
			else {
				calback.updateLog(this.nome + " vai pegar bola\n");
				try {
					pegaBola();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				calback.updateCesta();
				calback.updateLog(this.nome + " pegou a bola\n");
				this.temBola = true;
			}
		}
	}

	public Semaphore getMutex() {
		return mutex;
	}

	public void setMutex(Semaphore mutex) {
		this.mutex = mutex;
	}

	public Semaphore getBolasCesto() {
		return bolasCesto;
	}

	public void setBolasCesto(Semaphore bolasCesto) {
		this.bolasCesto = bolasCesto;
	}

	public Semaphore getVagas() {
		return vagas;
	}

	public void setVagas(Semaphore vagas) {
		this.vagas = vagas;
	}

	public boolean isTemBola() {
		return temBola;
	}

	public void setTemBola(boolean temBola) {
		this.temBola = temBola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getTempoBrinca() {
		return tempoBrinca;
	}

	public void setTempoBrinca(long tempoBrinca) {
		this.tempoBrinca = tempoBrinca;
	}

	public long getTempoDorme() {
		return tempoDorme;
	}

	public void setTempoDorme(long tempoDorme) {
		this.tempoDorme = tempoDorme;
	}

	public CallBacks getCalback() {
		return calback;
	}

	public void setCalback(CallBacks calback) {
		this.calback = calback;
	}
}