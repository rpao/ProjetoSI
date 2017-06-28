package ProjetoSI;

import robocode.MessageEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class Util {
	public static boolean NotFromTeam(String name) {
		if (name.contains("ProjetoSI") == true)
			return false;

		return true;
	}

	public static boolean isLeader(RobotDeathEvent r){
		if (r.getName().contains("ProjetoSI.Lider"))
			return true;

		return false;
	}

	public static Coordenada gerarCoordenada(EstadoRobot r, ScannedRobotEvent e){
		// Calculate enemy bearing
		double enemyBearing = r.getHeading() + e.getBearing();
		// Calculate enemy's position
		double X = r.getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
		double Y = r.getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));

		return new Coordenada(X, Y);
	}

	public static double recuperarCoordenada(EstadoRobot r, MessageEvent e) {
		double valor = 0; // verificacao de segurança
		if (e.getMessage() instanceof Coordenada) {
			Coordenada p = (Coordenada) e.getMessage();

			// Calcular x e y do alvo
			double dx = p.getX() - r.getX();
			double dy = p.getY() - r.getY();

			// Calculate angulo do alvo
			double theta = Math.toDegrees(Math.atan2(dx, dy));
			valor = normalRelativeAngleDegrees(theta - r.getGunHeading());
		}
		return valor;
	}

	public static double recuperarCoordenadaX(MessageEvent e){
		if (e.getMessage() instanceof Coordenada)
			return ((Coordenada)e.getMessage()).getX();
		return 0;
	}

	public static double recuperarCoordenadaY(MessageEvent e){
		if (e.getMessage() instanceof Coordenada)
			return ((Coordenada)e.getMessage()).getY();
		return 0;
	}

	public static double anguloAbsoluto(double anguloBase, double anguloRelativo) {
		double angulo = (anguloBase + anguloRelativo) % 360;

		if (angulo < 0) {
			angulo += 360;
		}

		return angulo;
	}

	public static double anguloRelativo(double anguloBase, double anguloDestino) {
		double angulo = (anguloDestino - anguloBase) % 360;
		if (angulo > 180) {
			angulo -= 360;
		} else if (angulo < -180) {
			angulo += 360;
		}

		return angulo;
	}

	public static double calcularX(double xBase, double anguloAbsoluto, double distancia) {
		double offsetX = (Math.sin(Math.toRadians(anguloAbsoluto)) * distancia);
		return xBase + offsetX;
	}

	public static double calcularY(double yBase, double anguloAbsoluto, double distancia) {
		double offsetY = (Math.cos(Math.toRadians(anguloAbsoluto)) * distancia);
		return yBase + offsetY;
	}

	public static double anguloAbsoluto(double xOrigen, double yOrigen, double xDestino, double yDestino) {
		double offsetX = xDestino - xOrigen;
		double offsetY = yDestino - yOrigen;

		return Math.toDegrees(Math.atan2(offsetX, offsetY));
	}

	public static double distancia(double xOrigen, double yOrigen, double xDestino, double yDestino) {
		double offsetX = xDestino - xOrigen;
		double offsetY = yDestino - yOrigen;

		return Math.sqrt(offsetX*offsetX + offsetY*offsetY);
	}

	/** Movimento Stop And Go **/
	public static double movementStopAndGo(double energyDrop){
		return ((3 + (int)(energyDrop*1.999999)) << 3 );
	}

	/** Robo de Menor Energia ** /
    public static ScannedRobotEvent getLesserLifeRobot(ArrayList<ScannedRobotEvent> robosEscaneados){

    	robosEscaneados.sort(new Comparator<ScannedRobotEvent>() {
			@Override
			public int compare(ScannedRobotEvent o1, ScannedRobotEvent o2) {
				return Double.compare(o1.getEnergy(), o2.getEnergy());
			}
		});

    	return robosEscaneados.get(0);
    }
	 */
}