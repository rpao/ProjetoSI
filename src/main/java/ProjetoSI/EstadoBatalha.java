package ProjetoSI;

import java.util.ArrayList;
import java.util.Comparator;

import robocode.ScannedRobotEvent;

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

	private ArrayList<ScannedRobotEvent> robosEscaneados = new ArrayList<ScannedRobotEvent>();

	public void addRoboEscaneado(ScannedRobotEvent e){
		if (e.getDistance() <= 40)	{
			robosEscaneados.add(e); 	
			DEBUG.mensagem("ROBO ADICIONADO" + e.getName());
		}
		/*
		robosEscaneados.sort(new Comparator<ScannedRobotEvent>() {
			//@Override
			public int compare(ScannedRobotEvent o1, ScannedRobotEvent o2) {
				return Double.compare(o1.getEnergy(), o2.getEnergy());
			}
		});
		*/
	}

	public boolean hasRobotEscaneado(ScannedRobotEvent e){
		DEBUG.mensagem("Já CONTEM ROBO" + e.getName());
		return robosEscaneados.contains(e);

	}

	public ScannedRobotEvent getLesserLifeRobot(){
		/*
		robosEscaneados.sort(new Comparator<ScannedRobotEvent>() {
			//@Override
			public int compare(ScannedRobotEvent o1, ScannedRobotEvent o2) {
				return Double.compare(o1.getEnergy(), o2.getEnergy());
			}
		});
		*/
		return robosEscaneados.get(0);


	}

	public boolean isEmptyRoboEscaneados(){
		return robosEscaneados.isEmpty();
	}

	public ScannedRobotEvent getRoboInimigo(ScannedRobotEvent e){

		for (ScannedRobotEvent i : robosEscaneados){
			if (i.getName() == e.getName()){
				return i;
			}
		}

		return null;
	}

	public void cleanRoboEscaneado(){
		DEBUG.mensagem("Limpou Robos");
		robosEscaneados.clear();

	}
}
