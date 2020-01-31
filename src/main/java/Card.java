import java.util.HashMap;
import java.util.Map;

public class Card {

	private Map<String, Side> sides;

	public Card(Side top, Side right, Side bottom, Side left) {
		super();
		sides = new HashMap<String, Side>();
		sides.put("top", top);
		sides.put("right", right);
		sides.put("bottom", bottom);
		sides.put("left", left);
	}

	public Card(Map<String, Side> sides) {
		this.sides = sides;
	}
	
    public Card clone() {
        Map<String, Side> newSides = new HashMap<String,Side>();

        newSides.put("top", sides.get("top").clone());
        newSides.put("right", sides.get("right").clone());
        newSides.put("bottom", sides.get("bottom").clone());
        newSides.put("left", sides.get("left").clone());

        return new Card(newSides);
    }
	
	public Card() {
		sides = new HashMap<String, Side>();
	}
	
	public Map<String, Side> getSides() {
		return sides;
	}

	public void setSides(Map<String, Side> sides) {
		this.sides = sides;
	}
	
	public Side getSideByDirection(String direction) {
		return sides.get(direction);
	}
	
	public Card rotate90Degrees() {
		return new Card(this.getSideByDirection("left"), this.getSideByDirection("top"), this.getSideByDirection("right"), this.getSideByDirection("bottom"));
	}
}