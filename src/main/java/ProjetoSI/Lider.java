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

import robocode.BulletHitBulletEvent;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.DeathEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.MessageEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

public class Lider extends TeamRobot{
	public static String REGRAS = "ProjetoSI/regras/Lider.drl";
	public static String CONSULTA_ACOES = "consulta_acoes";

	private KnowledgeBuilder kbuilder;
	private KnowledgeBase kbase;
	private StatefulKnowledgeSession ksession;
	private Vector<FactHandle> refAcoesAtuais = new Vector<FactHandle>();

	public Lider(){
	}

	@Override
	public void run() {
		// Set colors
		setBodyColor(Color.BLACK);
		setGunColor(Color.BLACK);
		setRadarColor(Color.YELLOW);
		setScanColor(Color.YELLOW);

		DEBUG.habilitarModoDebug(System.getProperty("robot.debug", "true").equals("true"));    	

		criarBC();

		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);


		while (true) {
			DEBUG.mensagem("inicio do turno");
			cargarEstadoRobot();
			cargarEstadoBatalha();

			DEBUG.mensagem("acoes em memoria ativa");
			DEBUG.despejarAcoes(ksession);           
			ksession.fireAllRules();
			limparAcoesAntigas();

			Vector<AcaoTeam> acoes = recuperarAcoesTeam();
			DEBUG.mensagem("acoes resultantes");
			DEBUG.despejarAcoesTeam(acoes);

			executarAcoesTeam(acoes);
			DEBUG.mensagem("fim do turno\n");
			execute();
		}

	}

	private void criarBC() {
		String ficheroRegras = System.getProperty("robot.regras", Lider.REGRAS);

		DEBUG.mensagem("criar BC");
		kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		DEBUG.mensagem("carregar regras a partir de: "+ficheroRegras);
		kbuilder.add(ResourceFactory.newClassPathResource(ficheroRegras, Lider.class), ResourceType.DRL);
		if (kbuilder.hasErrors()) {
			System.err.println(kbuilder.getErrors().toString());
		}

		kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		DEBUG.mensagem("criar secao de memoria ativa");
		ksession = kbase.newStatefulKnowledgeSession();
	}



	private void cargarEstadoRobot() {
		EstadoRobot estadoRobot = new EstadoRobot(this);
		refAcoesAtuais.add(ksession.insert(estadoRobot));
	}

	private void cargarEstadoBatalha() {
		EstadoBatalha estadoBatalha =
				new EstadoBatalha(getBattleFieldWidth(), getBattleFieldHeight(),
						getNumRounds(), getRoundNum(),
						getTime(),
						getOthers());
		refAcoesAtuais.add(ksession.insert(estadoBatalha));
	}

	private void limparAcoesAntigas() {
		for (FactHandle referenciaAcoes : this.refAcoesAtuais) {
			ksession.retract(referenciaAcoes);
		}
		this.refAcoesAtuais.clear();
	}

	private Vector<AcaoTeam> recuperarAcoesTeam() {
		AcaoTeam acao;
		Vector<AcaoTeam> listaAcoes = new Vector<AcaoTeam>();

		for (QueryResultsRow resultado : ksession.getQueryResults(Lider.CONSULTA_ACOES)) {
			acao = (AcaoTeam) resultado.get("acao");
			acao.setRobot(this);
			listaAcoes.add(acao);
			ksession.retract(resultado.getFactHandle("acao"));
		}

		return listaAcoes;
	}

	private void executarAcoesTeam(Vector<AcaoTeam> acoes) {
		for (AcaoTeam acao : acoes) {
			acao.iniciarExecucao();
		}
	}

	@Override
	public void onBulletHit(BulletHitEvent event) {
		refAcoesAtuais.add(ksession.insert(event));
	}

	@Override
	public void onBulletHitBullet(BulletHitBulletEvent event) {
		refAcoesAtuais.add(ksession.insert(event));
	}

	@Override
	public void onBulletMissed(BulletMissedEvent event) {
		refAcoesAtuais.add(ksession.insert(event));
	}

	@Override
	public void onHitByBullet(HitByBulletEvent event) {
		refAcoesAtuais.add(ksession.insert(event));
	}

	@Override
	public void onHitRobot(HitRobotEvent event) {
		refAcoesAtuais.add(ksession.insert(event));
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		refAcoesAtuais.add(ksession.insert(event));
	}

	@Override
	public void onRobotDeath(RobotDeathEvent event) {
		refAcoesAtuais.add(ksession.insert(event));
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		refAcoesAtuais.add(ksession.insert(event));
	}

	@Override
	public void onMessageReceived(MessageEvent event) {
		refAcoesAtuais.add(ksession.insert(event));
	}

	@Override
	public void onDeath(DeathEvent event) {
		refAcoesAtuais.add(ksession.insert(event));
	}
}