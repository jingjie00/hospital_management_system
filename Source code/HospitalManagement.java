import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
	__  _  _  ____   __  ____  ____  __   __ _  ____ 
	(  )( \/ )(  _ \ /  \(  _ \(_  _)/ _\ (  ( \(_  _)    *********************************************************  
	)( / \/ \ ) __/(  O ))   /  )( /    \/    /  )(       *         Please run by resolution with 100%            *
	(__)\_)(_/(__)   \__/(__\_) (__)\_/\_/\_)__) (__)     *********************************************************

            	   ________                            _                                                    
           		  /_  __/ /_  ___     ____ ___  ____ _(_)___     ____  _________  ____ __________ _____ ___ 
            	   / / / __ \/ _ \   / __ `__ \/ __ `/ / __ \   / __ \/ ___/ __ \/ __ `/ ___/ __ `/ __ `__ \
           	 	  / / / / / /  __/  / / / / / / /_/ / / / / /  / /_/ / /  / /_/ / /_/ / /  / /_/ / / / / / /
          	 	 /_/ /_/ /_/\___/  /_/ /_/ /_/\__,_/_/_/ /_/  / .___/_/   \____/\__, /_/   \__,_/_/ /_/ /_/ 
                                                      /_/               /____/           

	1) It will switch to CommandLineInterface (Console) or GraphicUserInterface(GUI) Class
	2) The parent of the six main class is HospitalParent.
   		The person-related class parent is Person.
   		Person's parent is Hospital Parent
	3) The visualisation like below:

	             	HospitalParent
	      		 /     |       |    \
			Person   Medical   Lab  Facility
	  	/   |   \
	Doctor Staff Patient

	4) Duplicate ID is not allowed!!!!!!!
	
	5) Any issue, please contact : 011-3810 0852. Tan Jing Jie 1804560

*/

public class HospitalManagement {

	static ArrayList<Person> staffs = new ArrayList<Person>();
	static ArrayList<Person> doctors = new ArrayList<Person>();
	static ArrayList<Person> patients = new ArrayList<Person>();
	static ArrayList<Facility> facilities = new ArrayList<Facility>();
	static ArrayList<Lab> laboratories = new ArrayList<Lab>();
	static ArrayList<Medical> medicals = new ArrayList<Medical>();

	// User Interface switch
	// ************************************************************************************

	public static void main(String args[]) {
		initialise();
		JOptionPane.showMessageDialog(null, "Please comfigurate system display scale setting to 100% to have best view",
				"Reminder", JOptionPane.INFORMATION_MESSAGE);
		String[] option = { "Command Line Interface", "Graphical User Interface" };
		int selection = JOptionPane.showOptionDialog(null, "Dear User,\nProgram continue with what Interface:\n\n ",
				"User Interface Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option,
				option[1]);

		if (selection == 0)
			CommandLineInterface.cliMain();
		else if (selection == 1)
			GraphicalUserInterface.guiMain();
		else
			System.out.println("No UI choosen. Program runned and terminate successfully");
	}

	// Initialise three content to each type of object array
	// ******************************************************
	public static void initialise() {
		// Dr. prefix will be automatic append
		doctors.add(new Doctor("560", "Tan Jing Jie", "Surgeon", "8-11AM", "MBBS, MD", 11));
		doctors.add(new Doctor("561", "John", "Physician", "10-4PM", "MBBS, MS", 12));
		doctors.add(new Doctor("562", "Ali", "Surgeon", "7-11AM", "MBBS, MD", 13));
		doctors.add(new Doctor("180", "Jo", "Surgeon", "9-11AM", "MBBS, MD", 11));
		doctors.add(new Doctor("181", "OOPP", "Physician", "10-8PM", "MBBS, MS", 12));
		doctors.add(new Doctor("182", "Lili", "Surgeon", "7-11AM", "MBBS, MD", 13));

		patients.add(new Patient("560", "Tan Jing Jie", "Fewer", "Male", "Leave", 20));
		patients.add(new Patient("561", "Lili", "Fewer", "Female", "ICU", 90));
		patients.add(new Patient("562", "Jo", "Fewer", "Male", "Ward", 46));
		patients.add(new Patient("180", "Ali", "Fewer", "Male", "Leave", 20));
		patients.add(new Patient("181", "Chuan", "Fewer", "Female", "ICU", 30));
		patients.add(new Patient("182", "Moham", "Fewer", "Male", "Ward", 46));

		staffs.add(new Staff("560", "Tan Jing Jie", "Manager", "Male", 4000));
		staffs.add(new Staff("561", "Peter", "General Maneger", "Male", 5000));
		staffs.add(new Staff("563", "Aliza", "CEO", "Female", 18000));
		staffs.add(new Staff("180", "Mogi", "Cleaner", "Female", 2000));
		staffs.add(new Staff("181", "Hahim", "Human Resources", "Female", 4000));
		staffs.add(new Staff("182", "Jones", "Technical", "Male", 5000));

		medicals.add(new Medical("Paracetamol 150mg", "Europe Medical", "11-12-2023", 15, 900));
		medicals.add(new Medical("Paracetamol 250mg", "China Medical", "11-12-2023", 30, 500));
		medicals.add(new Medical("Paracetamol 170mg", "Local Malaysia Medical", "11-12-2023", 80, 350));
		medicals.add(new Medical("Amlodipine 150mg", "Europe Medical", "11-12-2023", 15, 900));
		medicals.add(new Medical("Amoxicillin 250mg", "China Medical", "11-12-2023", 30, 500));
		medicals.add(new Medical("Amlodipine 170mg", "Local Malaysia Medical", "11-12-2023", 80, 350));

		laboratories.add(new Lab("Chemical 1", 200));
		laboratories.add(new Lab("BioHazard", 300));
		laboratories.add(new Lab("X-ray 1", 700));
		laboratories.add(new Lab("Chemical 2", 300));
		laboratories.add(new Lab("Blood Test1", 300));
		laboratories.add(new Lab("X-ray 2", 700));

		facilities.add(new Facility("Wad Room"));
		facilities.add(new Facility("Gymnasium"));
		facilities.add(new Facility("Swimming Pool"));
		facilities.add(new Facility("Guard House"));
		facilities.add(new Facility("Garden"));
		facilities.add(new Facility("Lobby"));
		facilities.add(new Facility("Surgeory Room"));
	}

}
