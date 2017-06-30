package AGMR;

import AGMR.VerificarRegras;

public class VerificarTodasRegras {

	public static void main(String args[]) {
		VerificarRegras regrasLazy = new VerificarRegras("AGMR/regras/Lazy.drl");
		VerificarRegras regrasLazySpin = new VerificarRegras("AGMR/regras/Looper.drl");
		VerificarRegras regrasSmart = new VerificarRegras("AGMR/regras/Smart.drl");

		VerificarRegras regrasLider = new VerificarRegras("AGMR/regras/Lider.drl");
		VerificarRegras regrasDroids = new VerificarRegras("AGMR/regras/Droids.drl");
	}
}