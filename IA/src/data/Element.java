package data;

public abstract class Element {
	private int coordX;
	private int coordY;
	
	
	public Element(int coordX, int coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}


	public int getCoordX() {
		return coordX;
	}


	public int getCoordY() {
		return coordY;
	}


	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}


	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	
	
}
