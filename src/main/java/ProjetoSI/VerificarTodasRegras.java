package ProjetoSI;

import ProjetoSI.VerificarRegras;

public class VerificarTodasRegras {

	public static void main(String args[]) {
		VerificarRegras regrasLazy = new VerificarRegras("ProjetoSI/regras/Lazy.drl");
		VerificarRegras regrasLazySpin = new VerificarRegras("ProjetoSI/regras/LazySpin.drl");
	}
}