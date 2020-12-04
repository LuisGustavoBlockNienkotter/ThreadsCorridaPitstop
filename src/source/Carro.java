package source;

import java.util.Random;

public class Carro extends Thread{
	private String nome;
	private int distanciaPercorrida = 0;
	private Corrida corrida;
	private boolean fezPistop;

	public Carro(Corrida corrida, String nome) {
		super();
		this.nome = nome;
		this.corrida = corrida;
		this.fezPistop = false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDistanciaPercorrida() {
		return distanciaPercorrida;
	}

	public void setDistanciaPercorrida(int distanciaPercorrida) {
		this.distanciaPercorrida = distanciaPercorrida;
	}
	
	public Corrida getCorrida() {
		return corrida;
	}

	public void setCorrida(Corrida corrida) {
		this.corrida = corrida;
	}

	public void correr(int distancia) {
		this.distanciaPercorrida += distancia;
	}
	
	public void resetDistanciaPercorrida() {
		this.distanciaPercorrida = 0;
	}
	
	public boolean isFezPistop() {
		return fezPistop;
	}

	public void setFezPistop(boolean fezPistop) {
		this.fezPistop = fezPistop;
	}

	private synchronized void pitstop() throws InterruptedException {
		Random random = new Random();
		
		System.out.println(this.nome + " foi para o pitstop.");
		
		int tempo = random.nextInt(10000 - 1000) + 1000;
		
		Thread.sleep(tempo);
		
		System.out.println(this.nome + " saiu do pitstop e ficou " + (tempo/1000) + " segundos");
		
		this.setFezPistop(true);

	}
	
	@Override
    public void run() {
        System.out.println(this.nome + " distancia inicial: " + this.distanciaPercorrida);
        
        Random random = new Random();
        boolean acidente = false;
        
        while (((this.distanciaPercorrida < this.corrida.getDistancia()) && !acidente) || !this.fezPistop) {
        	this.correr(1);
        	
        	if (random.nextInt(1000) > 998) {
				acidente = true;
			}
        	
        	if (this.distanciaPercorrida == (this.corrida.getDistancia()/2)) {
        		try {
					this.pitstop();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
        	
        	System.out.println(this.nome + " distancia atual: " + this.distanciaPercorrida);
        }
        
        if (!acidente) {
        	this.corrida.setCampeao(this);
		} else {
			System.out.println(this.nome + " sofreu um acidente, mas correu até: " + this.distanciaPercorrida);
		}
        
    }
}
