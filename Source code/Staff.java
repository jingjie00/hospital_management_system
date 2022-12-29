/***************************************************************
 *                   The child of Person !!!                   *
 ***************************************************************/

public class Staff extends Person {

	private String designation;
	private String sex;
	private int salary;

	// Constructor with overloading
	Staff() {
		newStaff();
	}

	Staff(String id, String name, String designation, String sex, int salary) {
		this.setId(id);
		this.setName(name);
		this.setDesignation(designation);
		this.setSex(sex);
		this.setSalary(salary);
	}

	// Mutator
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	// Accessor
	public String getDesignation() {
		return this.designation;
	}

	public String getSex() {
		return this.sex;
	}

	public int getSalary() {
		return this.salary;
	}

	// Prompt and get
	public void newStaff() {
		System.out.print("Enter staff's ID: ");
		super.setId(sc.next());

		System.out.print("Enter staff's name: ");
		super.setName(sc.next());

		System.out.print("Enter staff's designation: ");
		this.setDesignation(sc.next());

		System.out.print("Enter staff's sex: ");
		this.setSex(sc.next());

		do {
			System.out.print("Enter staff's salary: ");
			setSalary(HospitalParent.intValidate(sc.next(), 0));
		} while (getSalary() == -1);

		return;
	}
	
	//Override it parent
	@Override
	public String[] showInfo() {
		String[] out = new String[5];
		out[0] = getId();
		out[1] = getName();
		out[2] = getDesignation();
		out[3] = getSex();
		out[4] = String.valueOf(getSalary());
		return out;
	}

}
