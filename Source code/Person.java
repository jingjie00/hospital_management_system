import java.util.ArrayList;
/***************************************************************
 *          The child of Hospital Parent !!!                   *
 ***************************************************************
 *          The parent of the 3 person-related class!!!         *
 ***************************************************************/
public abstract class Person extends HospitalParent {
	private String id;
	private String name;

	// Mutator
	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	// Accessor
	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	// When add element to Person type array, we need to check the duplicate ID
	public static boolean duplicateCheck(ArrayList<Person> array, Person temp) {
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getId().equals(temp.getId())) {
				return false;
			}
		}
		return true;
	}
	
	//Override it parent
	@Override
	public String getIdentity() {
		return getId();
	}
	
	public abstract String[] showInfo();
}
