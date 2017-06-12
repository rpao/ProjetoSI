package ProjetoSI;

import java.util.List;
import java.util.Vector;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.drools.runtime.rule.QueryResultsRow;

import robocode.*;

public class VerificarRegras {
	public static String REGRAS;
	public static String CONSULTA_ACOES = "consulta_acoes";
	
	private KnowledgeBuilder kbuilder;
	private KnowledgeBase kbase;                // Base de conocimientos
	private StatefulKnowledgeSession ksession;  // Memoria activa
	private Vector<FactHandle> refFatosAtuais = new Vector<FactHandle>();

	public VerificarRegras(String regras) {
		this.REGRAS = regras;
		
		String modoDebug = System.getProperty("robot.debug", "true");
		DEBUG.habilitarModoDebug(modoDebug.equals("true"));
		criarBC();
		carregarEventos();
	}

	public void carregarEventos() {
		ScannedRobotEvent e = new ScannedRobotEvent("pepe", 100, 10, 10, 10, 10);
		FactHandle refFatos = ksession.insert(e);
		refFatosAtuais.add(refFatos);
		// anadir mas hechos ....

		DEBUG.mensagem("fatos em memoria ativa");
		DEBUG.despejarAcoes(ksession);
		ksession.fireAllRules();
		List<Acao> acoes = recuperarAcoes();
		DEBUG.mensagem("acoes resultantes");
		DEBUG.despejarAcoes(acoes);

	}

	private void criarBC() {
		String ficheroRegras;
		ficheroRegras = System.getProperty("robot.regras", VerificarRegras.REGRAS);

		DEBUG.mensagem("criar BC");
		kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		DEBUG.mensagem("Carregar regras a partir de: "+ficheroRegras);
		kbuilder.add(ResourceFactory.newClassPathResource(ficheroRegras,VerificarRegras.class), ResourceType.DRL);
		if (kbuilder.hasErrors()) {
			System.err.println(kbuilder.getErrors().toString());
		}

		kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );

		DEBUG.mensagem("Criar secao em memoria ativa");
		ksession = kbase.newStatefulKnowledgeSession();
	}

	private List<Acao> recuperarAcoes() {
		Acao acao;
		Vector<Acao> listaAcoes = new Vector<Acao>();
		for (QueryResultsRow resultado : ksession.getQueryResults(VerificarRegras.CONSULTA_ACOES)) {
			acao = (Acao) resultado.get("Acao");
			acao.setRobot(null);
			listaAcoes.add(acao);
			ksession.retract(resultado.getFactHandle("Acao"));
		}

		return listaAcoes;
	}
}
