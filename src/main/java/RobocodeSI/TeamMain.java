package RobocodeSI;

public class TeamMain {

	 public static void main(String args[]) {
		 @SuppressWarnings("unused")
		 ComprobarReglas d1 = new ComprobarReglas("RobocodeSI/reglas/reglas_robot.drl", "consulta_acciones", 1);
		 @SuppressWarnings("unused")
		 ComprobarReglas d2 = new ComprobarReglas("RobocodeSI/reglas/reglas_droid.drl", "consulta_acciones_2", 2);
	 }
}
