package ProjetoSI;

import ProjetoSI.VerificarRegras;

public class VerificarTodasRegras {

	public static void main(String args[]) {
		VerificarRegras regrasLazy = new VerificarRegras("ProjetoSI/regras/Lazy.drl");
		VerificarRegras regrasLazySpin = new VerificarRegras("ProjetoSI/regras/Looper.drl");
		VerificarRegras regrasSmart = new VerificarRegras("ProjetoSI/regras/Smart.drl");

		VerificarRegras regrasLider = new VerificarRegras("ProjetoSI/regras/Lider.drl");
		VerificarRegras regrasDroids = new VerificarRegras("ProjetoSI/regras/Droids.drl");
	}
}