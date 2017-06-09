package ProjetoSI;

public class EstadoBatalha {
	private double larguraCampo;
	private double alturaCampo;
	private int numeroRounds;
	private int roundAtual;
	private long tempo;
	private int numeroEnimigos;

	public EstadoBatalha(double larguraCampo, double alturaCampo, int numeroRounds, int roundAtual, long tempo, int numeroEnimigos) {
		this.larguraCampo = larguraCampo;
		this.alturaCampo = alturaCampo;
		this.numeroRounds = numeroRounds;
		this.roundAtual = roundAtual;
		this.tempo = tempo;
		this.numeroEnimigos = numeroEnimigos;
	}

	public double getLarguraCampo() {
		return larguraCampo;
	}

	public void setLarguraCampo(double larguraCampo) {
		this.larguraCampo = larguraCampo;
	}

	public double getAlturaCampo() {
		return alturaCampo;
	}

	public void setAlturaCampo(double alturaCampo) {
		this.alturaCampo = alturaCampo;
	}

	public int getNumeroRounds() {
		return numeroRounds;
	}

	public void setNumeroRounds(int numeroRounds) {
		this.numeroRounds = numeroRounds;
	}

	public int getRoundAtual() {
		return roundAtual;
	}

	public void setRoundAtual(int roundAtual) {
		this.roundAtual = roundAtual;
	}

	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}

	public int getNumeroEnimigos() {
		return numeroEnimigos;
	}

	public void setNumeroEnimigos(int numeroEnimigos) {
		this.numeroEnimigos = numeroEnimigos;
	}
}
