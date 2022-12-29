/***************************************************************
 *          The child of Hospital Parent !!!                   *
 ***************************************************************/
public class Lab extends HospitalParent {
	private String lab;
	private int cost;

	// Constructor with overloading
	Lab() {
		this.newLab();
	}

	Lab(String lab, int cost) {
		setLab(lab);
		setCost(cost);
	}

	// Mutator
	public void setLab(String lab) {
		this.lab = lab;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	// Accessor
	public String getLab() {
		return this.lab;
	}

	public int getCost() {
		return this.cost;
	}

	// Prompt and get
	public void newLab() {
		System.out.print("Enter Lab: ");
		this.setLab(sc.next());

		do {
			System.out.print("Enter Lab cost: RM");
			setCost(HospitalParent.intValidate(sc.next(), 0));
		} while (getCost() == -1);

		return;

	}
	
	//Override it parent
	
	@Override
	public String getIdentity() {
		return getLab();
	}

	
	@Override
	public String[] showInfo() {
		String[] out = new String[2];
		out[0] = getLab();
		out[1] = "RM " + String.valueOf(getCost());
		return out;
	}
}
