package ProjetoSI;

public class Coordenada implements java.io.Serializable{
	double X;
	double Y;
	
	public Coordenada (double X, double Y){
		this.X = X;
		this.Y = Y;
	}
	
	public double getX() {
		return X;
	}
	public void setX(double x) {
		X = x;
	}
	public double getY() {
		return Y;
	}
	public void setY(double y) {
		Y = y;
	}
}
