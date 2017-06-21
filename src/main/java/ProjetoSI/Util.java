package ProjetoSI;

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
}