/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RobocodeSI;

import java.util.ArrayList;
import java.util.Comparator;

import robocode.ScannedRobotEvent;
/**
 *
 * @author ribadas
 */
public class EstadoBatalla {
    private double anchoCampo;
    private double altoCampo;
    private int numeroRondas;
    private int rondaActual;
    private long tiempo;
    private int numeroEnemigos;

    public EstadoBatalla(double anchoCampo, double altoCampo, int numeroRondas, int rondaActual, long tiempo, int numeroEnemigos) {
        this.anchoCampo = anchoCampo;
        this.altoCampo = altoCampo;
        this.numeroRondas = numeroRondas;
        this.rondaActual = rondaActual;
        this.tiempo = tiempo;
        this.numeroEnemigos = numeroEnemigos;
    }

    public double getAltoCampo() {
        return altoCampo;
    }

    public void setAltoCampo(double altoCampo) {
        this.altoCampo = altoCampo;
    }

    public double getAnchoCampo() {
        return anchoCampo;
    }

    public void setAnchoCampo(double anchoCampo) {
        this.anchoCampo = anchoCampo;
    }

    public int getNumeroEnemigos() {
        return numeroEnemigos;
    }

    public void setNumeroEnemigos(int numeroEnemigos) {
        this.numeroEnemigos = numeroEnemigos;
    }

    public int getNumeroRondas() {
        return numeroRondas;
    }

    public void setNumeroRondas(int numeroRondas) {
        this.numeroRondas = numeroRondas;
    }

    public int getRondaActual() {
        return rondaActual;
    }

    public void setRondaActual(int rondaActual) {
        this.rondaActual = rondaActual;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }
    
    private ArrayList<ScannedRobotEvent> robosEscaneados = new ArrayList<ScannedRobotEvent>();

    public void addRoboEscaneado(ScannedRobotEvent e){
    	if (e.getDistance() <= 40)	{
    		robosEscaneados.add(e); 	
    		DEBUG.mensaje("ROBO ADICIONADO" + e.getName());
    	}
    	
    	robosEscaneados.sort(new Comparator<ScannedRobotEvent>() {
			@Override
			public int compare(ScannedRobotEvent o1, ScannedRobotEvent o2) {
				return Double.compare(o1.getEnergy(), o2.getEnergy());
			}
		});
    }
    
    public boolean hasRobotEscaneado(ScannedRobotEvent e){
    	DEBUG.mensaje("J� CONTEM ROBO" + e.getName());
    	return robosEscaneados.contains(e);
    	
    }
    
    public ScannedRobotEvent getLesserLifeRobot(){
    	
    	robosEscaneados.sort(new Comparator<ScannedRobotEvent>() {
			@Override
			public int compare(ScannedRobotEvent o1, ScannedRobotEvent o2) {
				return Double.compare(o1.getEnergy(), o2.getEnergy());
			}
		});
    	
    	return robosEscaneados.get(0);
    	
    	
    }
    
    public boolean isEmptyRoboEscaneados(){
    	return robosEscaneados.isEmpty();
    }
   
    public ScannedRobotEvent getRoboInimigo(ScannedRobotEvent e){
    	
    	for (ScannedRobotEvent i : robosEscaneados){
    		if (i.getName() == e.getName()){
    			return i;
    		}
    	}
    	
    	return null;
    }
    
    public void cleanRoboEscaneado(){
    	DEBUG.mensaje("Limpou Robos");
    	robosEscaneados.clear();
    
    }
}
