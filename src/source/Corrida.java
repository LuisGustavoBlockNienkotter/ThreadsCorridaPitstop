package source;

public class Corrida {
	private int distancia;
	private Carro campeao = null;

	public Corrida(int distancia) {
		super();
		this.distancia = distancia;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	public Carro getCampeao() {
		return campeao;
	}

	public void setCampeao(Carro campeao) {
		if (this.campeao == null) {
			this.campeao = campeao;
		}
	}

	public Carro comecarCorrida() {
		try {
			Carro carro1 = new Carro(this, "Relampago Marquinhos");
			Carro carro2 = new Carro(this, "Hamilton");
			Carro carro3 = new Carro(this, "Vettel");
			Carro carro4 = new Carro(this, "Bottas");
			Carro carro5 = new Carro(this, "Senna");
			
			carro1.start();
			carro2.start();
			carro3.start();
			carro4.start();
			carro5.start();
			
			carro1.join();
			carro2.join();
			carro3.join();
			carro4.join();
			carro5.join();
			
			if (this.campeao != null) {
				System.out.println("O " + this.campeao.getNome() + " é o campeão");
			} else {
				System.out.println("Todos sofreram um acidente");
				
				return null;
			}
			
			
			return this.campeao;
		} catch (Exception e) {
			System.out.println(e);
			
			return null;
		}
	}

	@Override
	public String toString() {
		return "Corrida [distancia=" + distancia + "]";
	}
}
