
public class Side {

	private Color color;
	private int part;
	
	public Side(Color color, int part) {
		super();
		this.color = color;
		this.part = part;
	}

	public Side() {
		
	}
	
	public Side clone() {
		return new Side(this.color, this.part);
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}

}
