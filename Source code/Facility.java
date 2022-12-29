/***************************************************************
 *          The child of Hospital Parent !!!                   *
 ***************************************************************/
public class Facility extends HospitalParent {
	private String facility;

	// Constructor with overloading
	Facility() {
		this.newFacility();
	}

	Facility(String facility) {
		setFacility(facility);
	}

	// Mutator
	public void setFacility(String facility) {
		this.facility = facility;
	}

	// Accessor
	public String getFacility() {
		return this.facility;
	}

	// Prompt and get
	public void newFacility() {

		System.out.print("Enter facility: ");
		this.setFacility(sc.next());

		return;
	}

	//Override it parent
	
	@Override
	public String getIdentity() {
		return getFacility();
	}
	
	@Override
	public String[] showInfo() {
		String[] out = new String[1];
		out[0] = getFacility();
		return out;
	}

}
