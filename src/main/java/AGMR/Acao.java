package AGMR;

import robocode.Robot;
import robocode.AdvancedRobot;
import robocode.TeamRobot;
import java.io.IOException;

public class Acao {
	private int		tipo;
	private double	parametro;
	private int		prioridade;
	private Coordenada coordenadaInimigo;

	private Robot robot;

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
	public static final int SEND_MESSAGE=13;

	public Acao() {
	}

	public Acao(int tipo, double parametro, int prioridade) {
		this.tipo = tipo;
		this.parametro = parametro;
		this.prioridade = prioridade;
		this.coordenadaInimigo = null;
	}

	public Acao(int tipo, Coordenada coordenadaInimigo, int prioridade) {
		this.tipo = tipo;
		this.parametro = 0;
		this.prioridade = prioridade;
		this.coordenadaInimigo = coordenadaInimigo;
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

	public Coordenada getCoordenadaInimigo() {
		return coordenadaInimigo;
	}

	public void setCoordenadaInimigo(Coordenada coordenadaInimigo) {
		this.coordenadaInimigo = coordenadaInimigo;
	}
	
	void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	public Robot getRobot() {
		if (robot instanceof AdvancedRobot)
			return (AdvancedRobot)robot;
		return (TeamRobot)robot;
	}

	public void iniciarExecucao() {
		if (this.robot != null) {
			switch (this.tipo) {
			case Acao.DISPARAR: 
				if (robot instanceof AdvancedRobot)
					((AdvancedRobot)robot).setFire(parametro);
				else
					((TeamRobot)robot).setFire(parametro);
				break;

			case Acao.AVANCAR: 
				if (robot instanceof AdvancedRobot)
					((AdvancedRobot)robot).ahead(parametro);
				else
					((TeamRobot)robot).ahead(parametro);
				break;

			case Acao.RETROCEDER: 
				if (robot instanceof AdvancedRobot)
					((AdvancedRobot)robot).setBack(parametro);
				else
					((TeamRobot)robot).setBack(parametro);
				break;

			case Acao.STOP:
				if (robot instanceof AdvancedRobot)
					((AdvancedRobot)robot).setStop();
				else
					((TeamRobot)robot).setStop(); 
				break;

			case Acao.VELOCIDADE: 
				if (robot instanceof AdvancedRobot)
					((AdvancedRobot)robot).setMaxVelocity(parametro);
				else
					((TeamRobot)robot).setMaxVelocity(parametro);
				break;
			case Acao.GIRAR_CANHAO_DIR: 
				if (robot instanceof AdvancedRobot)
					((AdvancedRobot)robot).setTurnGunRight(parametro);
				else
					((TeamRobot)robot).setTurnGunRight(parametro);
				break;
			case Acao.GIRAR_CANHAO_ESQ: 
				if (robot instanceof AdvancedRobot)
					((AdvancedRobot)robot).setTurnGunLeft(parametro);
				else
					((TeamRobot)robot).setTurnGunLeft(parametro);
				break;
			case Acao.GIRAR_RADAR_DIR: 
				if (robot instanceof AdvancedRobot)
					((AdvancedRobot)robot).setTurnRadarRight(parametro); 
				else
					((TeamRobot)robot).setTurnRadarRight(parametro);
				break;
			case Acao.GIRAR_RADAR_ESQ: 
				if (robot instanceof AdvancedRobot)
					((AdvancedRobot)robot).setTurnRadarLeft(parametro);
				else
					((TeamRobot)robot).setTurnRadarLeft(parametro);
				break;
			case Acao.GIRAR_TANQUE_DIR: 
				if (robot instanceof AdvancedRobot)
					((AdvancedRobot)robot).setTurnRight(parametro); 
				else
					((TeamRobot)robot).setTurnRight(parametro); 
				break;
			case Acao.GIRAR_TANQUE_ESQ: 
				if (robot instanceof AdvancedRobot)
					((AdvancedRobot)robot).setTurnLeft(parametro);
				else
					((TeamRobot)robot).setTurnLeft(parametro);
				break;

			case Acao.MOVIMENTO_CIRCULO: 
				this.goCircle(parametro);
				break;

			case Acao.SEND_MESSAGE:
				if (robot instanceof TeamRobot){
					try {
						((TeamRobot)robot).broadcastMessage(coordenadaInimigo);
					} catch (IOException e) {
						DEBUG.mensagem("Erro ao enviar mensagem...\n"+e.getMessage()+"\n");
					}
				}else{
					DEBUG.mensagem("Apenas TeamRobots podem enviar mensagens\n");
				}
				break;
			}
		}
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
		case Acao.SEND_MESSAGE: etqTipo="Enviando mensagem"; break;
		}
		return "Acao> tipo: "+etqTipo+", parametro: "+parametro+", prioridade:"+prioridade;

	}

	public void goCircle(double parametro){
		if (robot instanceof AdvancedRobot){
			((AdvancedRobot)robot).setTurnRight(1000);
			((AdvancedRobot)robot).setMaxVelocity(5);
			((AdvancedRobot)robot).setBack(parametro);
		}else{
			((TeamRobot)robot).setTurnRight(1000);
			((TeamRobot)robot).setMaxVelocity(5);
			((TeamRobot)robot).setBack(parametro);
		}
	}
}
