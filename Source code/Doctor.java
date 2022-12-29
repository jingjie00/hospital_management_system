/***************************************************************
 *                   The child of Person !!!                   *
 ***************************************************************/
public class Doctor extends Person {

	private String specialist;
	private String workTime;
	private String qualification;
	private int room;

	// Constructor with overloading
	Doctor() {
		newDoctor();
	}

	Doctor(String id, String name, String specialist, String workTime, String qualification, int room) {
		super.setId(id);
		super.setName(name);
		this.setSpecialist(specialist);
		this.setWorkTime(workTime);
		this.setQualification(qualification);
		this.setRoom(room);
	}

	// Mutator
	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	// Accessor
	public String getSpecialist() {
		return this.specialist;
	}

	public String getWorkTime() {
		return this.workTime;
	}

	public String getQualification() {
		return this.qualification;
	}

	public int getRoom() {
		return this.room;
	}

	// Prompt and get
	public void newDoctor() {

		System.out.print("Enter doctor's ID: ");
		super.setId(sc.next());

		System.out.print("Enter doctor's name: Dr.");
		super.setName(sc.next());

		System.out.print("Enter doctor's specialist: ");
		this.setSpecialist(sc.next());

		do {
			System.out.print("Enter doctor's work time [tt]-[tt][?M], Example 11-12AM: ");
			this.setWorkTime(sc.next());
			if (this.getWorkTime().matches("(1[0-2]|0?[1-9])-(1[0-2]|0?[1-9])([AP][M])"))
				break;
			else
				System.out.println("Invalid work format");
		} while (true);

		System.out.print("Enter doctor's qualification: ");
		this.setQualification(sc.next());

		do {
			System.out.print("Enter doctor's room: ");
			setRoom(HospitalParent.intValidate(sc.next(), 1));
		} while (getRoom() == -1);
		return;
	}

	//Override it parent
	@Override
	public String[] showInfo() {
		String[] out = new String[6];
		out[0] = getId();
		out[1] = "Dr. " + getName();
		out[2] = getSpecialist();
		out[3] = getWorkTime();
		out[4] = getQualification();
		out[5] = String.valueOf(getRoom());
		return out;
	}
}
