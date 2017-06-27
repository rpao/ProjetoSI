package ProjetoSI;
import java.util.List;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

public final class DEBUG {
	public static boolean modoDebugHabilitado = false;

	public static void habilitarModoDebug(boolean b) {
		modoDebugHabilitado = b;
	}

	public static void mensagem(String string) {
		if (modoDebugHabilitado) {
			System.out.println("DEBUG:"+string);			
		}		
	}

	public static void despejarAcoes(StatefulKnowledgeSession ksession) {
		if (modoDebugHabilitado){
			for (FactHandle f: ksession.getFactHandles()){
				System.out.println("  "+ksession.getObject(f));				
			}			
		}		
	}

	public static void despejarAcoes (List<Acao> acoes) {
		if (modoDebugHabilitado){
			for (Acao a: acoes){
				System.out.println("  "+a.toString());				
			}
		}		
	}

	public static void despejarAcoesTeam (List<AcaoTeam> acoes) {
		if (modoDebugHabilitado){
			for (AcaoTeam a: acoes){
				System.out.println("  "+a.toString());				
			}
		}		
	}
}
