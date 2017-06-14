package ProjetoSI;

import java.awt.Color;
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

import robocode.AdvancedRobot;
import robocode.BulletHitBulletEvent;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

public class CrazyShooter extends AdvancedRobot {

	public static String REGRAS = "ProjetoSI/regras/CrazyShooter.drl";
	public static String CONSULTA_ACOES = "consulta_acoes";

	private KnowledgeBuilder kbuilder;
	private KnowledgeBase kbase;
	private StatefulKnowledgeSession ksession;
	private Vector<FactHandle> refFatosAtuais = new Vector<FactHandle>();


	public CrazyShooter(){
	}

	@Override
	public void run() {
		//setColors(Color.RED, Color.BLACK, Color.RED);
		setBodyColor(Color.WHITE);
		setGunColor(Color.BLACK);
		setRadarColor(Color.ORANGE);
		setScanColor(Color.BLACK);

		DEBUG.habilitarModoDebug(System.getProperty("robot.debug", "true").equals("true"));    	

		// Criar base de conhecimentos e carregar regras
		criarBC();

		// Tornar os movimentos independentes (tanque, cano e radar)
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);

		while (true) {
			DEBUG.mensagem("inicio turno");
			carregarEstadoRobot();
			carregarEstadoBatalha();

			// Lançar regras
			DEBUG.mensagem("Acoes na memoria");
			DEBUG.despejarAcoes(ksession);           
			ksession.fireAllRules();
			limparFatosAntigos();

			// Recuperar Acoes
			Vector<Acao> acoes = recuperarAcoes();
			DEBUG.mensagem("acoes resultantes");
			DEBUG.despejarAcoes(acoes);

			// Executar Acoes
			executarAcoes(acoes);
			DEBUG.mensagem("fim turno\n");
			execute();  // Informa fim do turno
		}
	}

	private void criarBC() {
		String ficheroRegras = System.getProperty("robot.regras", CrazyShooter.REGRAS);

		DEBUG.mensagem("criar BC");
		kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		DEBUG.mensagem("Carregar regras a partir de: "+ficheroRegras);
		kbuilder.add(ResourceFactory.newClassPathResource(ficheroRegras, CrazyShooter.class), ResourceType.DRL);
		if (kbuilder.hasErrors()) {
			System.err.println(kbuilder.getErrors().toString());
		}

		kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		DEBUG.mensagem("Criar secao em memoria ativa");
		ksession = kbase.newStatefulKnowledgeSession();
	}

	private void carregarEstadoRobot() {
		EstadoRobot estadoRobot = new EstadoRobot(this);
		refFatosAtuais.add(ksession.insert(estadoRobot));
	}

	private void carregarEstadoBatalha() {
		EstadoBatalha estadoBatalha =
				new EstadoBatalha(getBattleFieldWidth(), getBattleFieldHeight(),
						getNumRounds(), getRoundNum(),
						getTime(),
						getOthers());
		refFatosAtuais.add(ksession.insert(estadoBatalha));
	}

	private void limparFatosAntigos() {
		for (FactHandle refFato : this.refFatosAtuais) {
			ksession.retract(refFato);
		}
		this.refFatosAtuais.clear();
	}

	private Vector<Acao> recuperarAcoes() {
		Acao Acao;
		Vector<Acao> listaAcaoes = new Vector<Acao>();

		for (QueryResultsRow resultado : ksession.getQueryResults(CrazyShooter.CONSULTA_ACOES)) {
			Acao = (Acao) resultado.get("acao");
			Acao.setRobot(this);
			listaAcaoes.add(Acao);
			ksession.retract(resultado.getFactHandle("acao"));
		}

		return listaAcaoes;
	}

	private void executarAcoes(Vector<Acao> Acoes) {
		for (Acao Acao : Acoes) {
			Acao.iniciarExecucao();
		}
	}
 
	@Override
	public void onBulletHit(BulletHitEvent event) {
		refFatosAtuais.add(ksession.insert(event));
	}

	@Override
	public void onBulletHitBullet(BulletHitBulletEvent event) {
		refFatosAtuais.add(ksession.insert(event));
	}

	@Override
	public void onBulletMissed(BulletMissedEvent event) {
		refFatosAtuais.add(ksession.insert(event));
	}

	@Override
	public void onHitByBullet(HitByBulletEvent event) {
		refFatosAtuais.add(ksession.insert(event));
	}

	@Override
	public void onHitRobot(HitRobotEvent event) {
		refFatosAtuais.add(ksession.insert(event));
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		refFatosAtuais.add(ksession.insert(event));
	}

	@Override
	public void onRobotDeath(RobotDeathEvent event) {
		refFatosAtuais.add(ksession.insert(event));
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		refFatosAtuais.add(ksession.insert(event));
	}
}