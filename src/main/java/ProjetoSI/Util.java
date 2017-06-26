package ProjetoSI;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;

import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class Util {
	public static boolean NotFromTeam(String name) {
		if (name.contains("ProjetoSI") == true)
			return false;
		
		return true;
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