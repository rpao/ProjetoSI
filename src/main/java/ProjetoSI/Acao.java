package ProjetoSI;

import robocode.AdvancedRobot;

public class Acao {
	private int        tipo;
	private double     parametro;
	private int        prioridade;

	private AdvancedRobot robot;   // Referncia al robot que ejecutara la accion

	public static final int AVANCAR=1;
	public static final int RETROCEDER=2;
	public static final int STOP=3;
	public static final int DISPARAR=4;
	public static final int GIRAR_TANQUE_DIR=5;
	public static final int GIRAR_TANQUE_ESQ=6;
	public static final int GIRAR_RADAR_DIR=7;
	public static final int GIRAR_RADAR_ESQ=8;
	public static final int GIRAR_CANO_DIR=9;
	public static final int GIRAR_CANO_ESQ=10;


	public Acao() {
	}

	public Acao(int tipo, double parametro, int prioridade) {
		this.tipo = tipo;
		this.parametro = parametro;
		this.prioridade = prioridade;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public double getParametro() {
		return parametro;
	}

	public void setParametro(double parametro) {
		this.parametro = parametro;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public AdvancedRobot getRobot() {
		return robot;
	}

	public void iniciarExecucao() {
		if (this.robot != null) {
			switch (this.tipo) {
			case Acao.DISPARAR: robot.setFire(parametro); break;
			case Acao.AVANCAR: robot.setAhead(parametro); break;
			case Acao.RETROCEDER: robot.setBack(parametro); break;
			case Acao.STOP: robot.setStop(); break;
			case Acao.GIRAR_CANO_DIR: robot.setTurnGunRight(parametro); break;
			case Acao.GIRAR_CANO_ESQ: robot.setTurnGunLeft(parametro); break;
			case Acao.GIRAR_RADAR_DIR: robot.setTurnRadarRight(parametro); break;
			case Acao.GIRAR_RADAR_ESQ: robot.setTurnRadarLeft(parametro); break;
			case Acao.GIRAR_TANQUE_DIR: robot.setTurnRight(parametro); break;
			case Acao.GIRAR_TANQUE_ESQ: robot.setTurnLeft(parametro); break;
			}
		}
	}

	void setRobot(AdvancedRobot robot) {
		this.robot = robot;
	}

	public String toString(){
		String etqTipo="";
		switch (this.tipo) {
		case Acao.DISPARAR:etqTipo="Disparar"; break;
		case Acao.AVANCAR: etqTipo="Avancar"; break;
		case Acao.RETROCEDER: etqTipo="Retroceder"; break;
		case Acao.STOP: etqTipo="Stop"; break;
		case Acao.GIRAR_CANO_DIR: etqTipo="Girar cano: direita"; break;
		case Acao.GIRAR_CANO_ESQ: etqTipo="Girar cano: esquerda"; break;
		case Acao.GIRAR_RADAR_DIR: etqTipo="Girar radar: direita"; break;
		case Acao.GIRAR_RADAR_ESQ: etqTipo="Girar radar: esquerda"; break;
		case Acao.GIRAR_TANQUE_DIR: etqTipo="Girar tanque: direita"; break;
		case Acao.GIRAR_TANQUE_ESQ: etqTipo="Girar tanque: esqueda"; break;
		}
		return "Acao> tipo: "+etqTipo+", parametro: "+parametro+", prioridade:"+prioridade;

	}

}
