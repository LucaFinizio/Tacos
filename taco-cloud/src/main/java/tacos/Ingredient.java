package tacos;

//Lombok library will generate getters and setters at runtime
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {
	  private String id;
	  private String name;
	  private Type type;
	  
	  public static enum Type {
	    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	  }
	
	public Ingredient(String id, String name, Type type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public Object getType() {
		// TODO Auto-generated method stub
		return type;
	}
}
