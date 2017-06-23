/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RobocodeSI;

import java.util.ArrayList;
import java.util.HashSet;

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
    		robosEscaneados.add(e); 	
    		DEBUG.mensaje("ROBO ADICIONADO" + e.getName());
    }
    
    public boolean hasRobotEscaneado(ScannedRobotEvent e){
    	DEBUG.mensaje("Já CONTEM ROBO" + e.getName());
    	return robosEscaneados.contains(e);
    	
    }
    	
    public void cleanRoboEscaneado(){
    	DEBUG.mensaje("Limpou Robos");
    	robosEscaneados.clear();
    
    }

}
