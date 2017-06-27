package ProjetoSI;

import java.io.IOException;

import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

public class AcaoTeam{
	private int		tipo;
	private double	parametro;
	private int		prioridade;
	private Coordenada coordenadaInimigo;

	private TeamRobot robot;   // Referncia al robot que ejecutara la accion

	public static final int AVANCAR=1;
	public static final int RETROCEDER=2;
	public static final int STOP=3;
	public static final int DISPARAR=4;
	public static final int GIRAR_TANQUE_DIR=5;
	public static final int GIRAR_TANQUE_ESQ=6;
	public static final int GIRAR_RADAR_DIR=7;
	public static final int GIRAR_RADAR_ESQ=8;
	public static final int GIRAR_CANHAO_DIR=9;
	public static final int GIRAR_CANHAO_ESQ=10;
	public static final int SEND_MESSAGE=11;

	public AcaoTeam() {
	}

	public AcaoTeam(int tipo, Coordenada coordenadaInimigo, int prioridade) {
		this.tipo = tipo;
		this.parametro = 0;
		this.prioridade = prioridade;
		this.coordenadaInimigo = coordenadaInimigo;
	}

	public AcaoTeam(int tipo, double parametro, int prioridade) {
		this.tipo = tipo;
		this.parametro = parametro;
		this.prioridade = prioridade;
		this.coordenadaInimigo = null;
	}

	public double getParametro() {
		return parametro;
	}

	public void setParametro(double parametro) {
		this.parametro = parametro; 
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public void iniciarExecucao() {
		if (this.robot != null) {
			switch (this.tipo) {
			case AcaoTeam.DISPARAR: robot.setFire(parametro); break;
			case AcaoTeam.AVANCAR: robot.setAhead(parametro); break;
			case AcaoTeam.RETROCEDER: robot.setBack(parametro); break;
			case AcaoTeam.STOP: robot.setStop(); break;
			case AcaoTeam.GIRAR_CANHAO_DIR: robot.setTurnGunRight(parametro); break;
			case AcaoTeam.GIRAR_CANHAO_ESQ: robot.setTurnGunLeft(parametro); break;
			case AcaoTeam.GIRAR_RADAR_DIR: robot.setTurnRadarRight(parametro); break;
			case AcaoTeam.GIRAR_RADAR_ESQ: robot.setTurnRadarLeft(parametro); break;
			case AcaoTeam.GIRAR_TANQUE_DIR: robot.setTurnRight(parametro); break;
			case AcaoTeam.GIRAR_TANQUE_ESQ: robot.setTurnLeft(parametro); break;
			case AcaoTeam.SEND_MESSAGE: 
				try {
					robot.broadcastMessage(coordenadaInimigo);
				} catch (IOException e) {
					DEBUG.mensagem("Erro ao enviar mensagem...\n"+e.getMessage()+"\n");
				}

				break;
			}
		}
	}

	void setRobot(TeamRobot robot) {
		this.robot = robot;
	}

	public String toString(){
		String etqTipo="";
		switch (this.tipo) {
		case AcaoTeam.DISPARAR:etqTipo="Disparar"; break;
		case AcaoTeam.AVANCAR: etqTipo="Avancar"; break;
		case AcaoTeam.RETROCEDER: etqTipo="Retroceder"; break;
		case AcaoTeam.STOP: etqTipo="Stop"; break;
		case AcaoTeam.GIRAR_CANHAO_DIR: etqTipo="Girar canhao direita"; break;
		case AcaoTeam.GIRAR_CANHAO_ESQ: etqTipo="Girar canhao esquerda"; break;
		case AcaoTeam.GIRAR_RADAR_DIR: etqTipo="Girar radar direita"; break;
		case AcaoTeam.GIRAR_RADAR_ESQ: etqTipo="Girar radar esquerda"; break;
		case AcaoTeam.GIRAR_TANQUE_DIR: etqTipo="Girar tanque direita"; break;
		case AcaoTeam.GIRAR_TANQUE_ESQ: etqTipo="Girar tanque esquerda"; break;
		case AcaoTeam.SEND_MESSAGE: etqTipo="Enviando mensagem"; break;
		}
		return "AcaoTeam[tipo:"+etqTipo+", param:"+parametro+", prioridade:"+prioridade+"]";
	}

}