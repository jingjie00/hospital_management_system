/***************************************************************
 *                   The child of Person !!!                   *
 ***************************************************************/

public class Patient extends Person {

	private String disease;
	private String sex;
	private String admitStatus;
	private int age;

	// Constructor with overloading
	Patient() {
		newPatient();
	}

	Patient(String id, String name, String disease, String sex, String admitStatus, int age) {
		setId(id);
		setName(name);
		setDisease(disease);
		setSex(sex);
		setAdmitStatus(admitStatus);
		setAge(age);
	}

	// Mutator
	public void setDisease(String disease) {
		this.disease = disease;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setAdmitStatus(String admitStatus) {
		this.admitStatus = admitStatus;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// Ancestor
	public String getDisease() {
		return this.disease;
	}

	public String getSex() {
		return this.sex;
	}

	public String getAdmitStatus() {
		return this.admitStatus;
	}

	public int getAge() {
		return this.age;
	}

	// Prompt and get
	public void newPatient() {
		System.out.print("Enter patient's ID: ");
		super.setId(sc.next());

		System.out.print("Enter patient's name: ");
		super.setName(sc.next());

		System.out.print("Enter patient's disease: ");
		this.setDisease(sc.next());

		System.out.print("Enter patient's sex: ");
		this.setSex(sc.next());

		System.out.print("Enter patient's admitStatus: ");
		this.setAdmitStatus(sc.next());

		do {
			System.out.print("Enter patient's age: ");
			setAge(HospitalParent.intValidate(sc.next(), 0));
		} while (getAge() == -1);

		return;
	}
	
	//Override it parent
	@Override
	public String[] showInfo() {
		String[] out = new String[6];
		out[0] = getId();
		out[1] = getName();
		out[2] = getDisease();
		out[3] = getSex();
		out[4] = getAdmitStatus();
		out[5] = String.valueOf(getAge());
		return out;
	}
}
