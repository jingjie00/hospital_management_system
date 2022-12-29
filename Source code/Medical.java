/***************************************************************
 *          The child of Hospital Parent !!!                   *
 ***************************************************************/

public class Medical extends HospitalParent {
	private String name;
	private String manufacturer;
	private String expiryDate;
	private int cost;
	private int count;

	// Constructor with overloading
	Medical() {
		newMedical();
	}

	Medical(String name, String manufacturer, String expiryDate, int cost, int count) {
		setName(name);
		setManufacturer(manufacturer);
		setExpiryDate(expiryDate);
		setCost(cost);
		setCount(count);
	}

	// Mutator
	public void setName(String name) {
		this.name = name;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// Accessor
	public String getName() {
		return this.name;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public String getExpiryDate() {
		return this.expiryDate;
	}

	public int getCost() {
		return this.cost;
	}

	public int getCount() {
		return this.count;
	}

	// Prompt and get
	public void newMedical() {

		System.out.print("Enter Medical's name: ");
		this.setName(sc.next());

		System.out.print("Enter Medical's manufacturer: ");
		this.setManufacturer(sc.next());

		do {
			System.out.print("Enter Medical's expiryDate [dd-mm-yyyy]: ");
			this.setExpiryDate(sc.next());
			if (this.getExpiryDate().matches("(31[-](0[13578]|1[02])[-](18|19|20|21|22)[0-9]{2})" // date regex
																									// validation
					+ "|((29|30)[-](01|0[3-9]|1[1-2])[-](18|19|20|21|22)[0-9]{2})"
					+ "|((0[1-9]|1[0-9]|2[0-8])[-](0[1-9]|1[0-2])[-](18|19|20|21|22)[0-9]{2})"
					+ "|(29[-](02)[-](((18|19|20|21|22)(04|08|[2468][048]|[13579][26]))|2000))"))
				break;
			else
				System.out.println("Invalid date entered");
		} while (true);

		do {
			System.out.print("Enter Medical's cost: ");
			setCost(HospitalParent.intValidate(sc.next(), 0));
		} while (getCost() == -1);

		do {
			System.out.print("Enter Medical's count: ");
			setCost(HospitalParent.intValidate(sc.next(), 0));
		} while (getCost() == -1);

		return;
	}
	
	//Override it parent
	@Override
	public String getIdentity() {
		return getName();
	}
	
	@Override
	public String[] showInfo() {
		String[] out = new String[5];
		out[0] = getName();
		out[1] = getManufacturer();
		out[2] = getExpiryDate();
		out[3] = String.valueOf(getCost());
		out[4] = String.valueOf(getCount());
		return out;
	}
}
