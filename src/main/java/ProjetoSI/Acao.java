package ProjetoSI;

import robocode.AdvancedRobot;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class Acao {
	private int		tipo;
	private double	parametro;
	private int		prioridade;

	private AdvancedRobot robot;

	// Ações gerais
	public static final int AVANCAR=1;
	public static final int RETROCEDER=2;
	public static final int STOP=3;
	public static final int DISPARAR=4;
	public static final int VELOCIDADE = 5;
	public static final int GIRAR_TANQUE_DIR=6;
	public static final int GIRAR_TANQUE_ESQ=7;
	public static final int GIRAR_RADAR_DIR=8;
	public static final int GIRAR_RADAR_ESQ=9;
	public static final int GIRAR_CANHAO_DIR=10;
	public static final int GIRAR_CANHAO_ESQ=11;

	// Ações especificas
	public static final int MOVIMENTO_CIRCULO=12;

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
			case Acao.AVANCAR: robot.ahead(parametro);break;//robot.setAhead(parametro); break;
			case Acao.RETROCEDER: robot.setBack(parametro); break;
			case Acao.STOP: robot.setStop(); break;
			case Acao.VELOCIDADE: robot.setMaxVelocity(parametro);break;
			case Acao.GIRAR_CANHAO_DIR: robot.setTurnGunRight(parametro); break;
			case Acao.GIRAR_CANHAO_ESQ: robot.setTurnGunLeft(parametro); break;
			case Acao.GIRAR_RADAR_DIR: robot.setTurnRadarRight(parametro); break;
			case Acao.GIRAR_RADAR_ESQ: robot.setTurnRadarLeft(parametro); break;
			case Acao.GIRAR_TANQUE_DIR: robot.setTurnRight(parametro); break;
			case Acao.GIRAR_TANQUE_ESQ: robot.setTurnLeft(parametro); break;
			case Acao.MOVIMENTO_CIRCULO: this.goCircle(parametro); break;
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
		case Acao.GIRAR_CANHAO_DIR: etqTipo="Girar cano: direita"; break;
		case Acao.GIRAR_CANHAO_ESQ: etqTipo="Girar cano: esquerda"; break;
		case Acao.GIRAR_RADAR_DIR: etqTipo="Girar radar: direita"; break;
		case Acao.GIRAR_RADAR_ESQ: etqTipo="Girar radar: esquerda"; break;
		case Acao.GIRAR_TANQUE_DIR: etqTipo="Girar tanque: direita"; break;
		case Acao.GIRAR_TANQUE_ESQ: etqTipo="Girar tanque: esqueda"; break;
		case Acao.MOVIMENTO_CIRCULO: etqTipo="Movimento circular"; break;
		}
		return "Acao> tipo: "+etqTipo+", parametro: "+parametro+", prioridade:"+prioridade;

	}

	public void goCircle(double parametro){
		robot.setTurnRight(1000);
		robot.setMaxVelocity(5);
		robot.setBack(parametro);
	}
}
