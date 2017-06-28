package ProjetoSI;

import ProjetoSI.VerificarRegras;

public class VerificarTodasRegras {

	public static void main(String args[]) {
		VerificarRegras regrasLazy = new VerificarRegras("ProjetoSI/regras/Lazy.drl", VerificarRegras.ADVANCED);
		VerificarRegras regrasLazySpin = new VerificarRegras("ProjetoSI/regras/Looper.drl", VerificarRegras.ADVANCED);
		VerificarRegras regrasSmart = new VerificarRegras("ProjetoSI/regras/Smart.drl", VerificarRegras.ADVANCED);

		VerificarRegras regrasLider = new VerificarRegras("ProjetoSI/regras/Lider.drl", VerificarRegras.ADVANCED);
		VerificarRegras regrasDroids = new VerificarRegras("ProjetoSI/regras/Droids.drl", VerificarRegras.ADVANCED);
	}
}