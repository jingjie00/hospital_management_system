import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
__  _  _  ____   __  ____  ____  __   __ _  ____ 
(  )( \/ )(  _ \ /  \(  _ \(_  _)/ _\ (  ( \(_  _)    *********************************************************  
)( / \/ \ ) __/(  O ))   /  )( /    \/    /  )(      *         Please run by resolution with 100%            *
(__)\_)(_/(__)   \__/(__\_) (__)\_/\_/\_)__) (__)     *********************************************************

*/
public class CommandLineInterface {

	static Scanner sca = new Scanner(System.in);

	// CLI main function call
	public static void cliMain() {

		int object = 0;
		int function = 0;
		int switchFuction = 0;

		do {
			if (switchFuction != 1) // skip, aim: to let it remain the particular object
				object = menu();

			if (object == 7)
				break;

			function = function(object);

			if (function == 4)
				break;

			if (function != 3) // skip, return to main_menu
				switchFuction = switchFuction(object, function);
			else if (function == 3)
				switchFuction = 0; // refresh main_menu variable, prevent cannot enter

		} while (true);

		System.out.println(
				"*******************************************************************************************************");
		System.out.println(
				"*                                   Thank you for using our system                                    *");
		System.out.println(
				"*******************************************************************************************************");
		sca.close();
	}

	// Select the type of object (Doctor, Staff, Patient and etc.)
	public static int menu() {
		int select;
		do {
			DateFormat datef = new SimpleDateFormat("yyyy/MM/dd");
			DateFormat timef = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			System.out.println(
					"*******************************************************************************************************");
			System.out.println(
					"*                                                 Menu                                                *");
			System.out.println(
					"*******************************************************************************************************");
			System.out.println("Date: " + datef.format(date) + "\t\t\t\t\t\t\t\t\t Time: " + timef.format(date));
			System.out.println(
					"*******************************************************************************************************");
			System.out.println("                                         Press <?> to select.\n");
			System.out.println(
					"<1> Doctor      <2> Staff      <3> Patient      <4> Medical      <5> Lab      <6> Facility      <7>Exit");
			System.out.print("\n Input-->");
			try {
				select = sca.nextInt();
				if (select < 1 || select > 7)
					System.out.println("Invalid value");
				else
					return select;
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid value");
				sca.next();
			}

		} while (true);
	}

	// Select the type of function (Add or View apply)
	public static int function(int select) {
		int function;
		do {
			System.out.println(
					"\n\n\n*******************************************************************************************************");
			switch (select) {
			case 1:
				System.out.println(
						"*                                                Doctor                                               *");
				break;
			case 2:
				System.out.println(
						"*                                                Staff                                                *");
				break;
			case 3:
				System.out.println(
						"*                                               Patient                                               *");
				break;
			case 4:
				System.out.println(
						"*                                               Medical                                               *");
				break;
			case 5:
				System.out.println(
						"*                                                Lab                                                  *");
				break;
			case 6:
				System.out.println(
						"*                                              Facility                                               *");
				break;
			default:
				System.out.println("Error");
			}
			System.out.println(
					"*******************************************************************************************************");
			System.out.println("                                         Press <?> to select.\n");
			System.out.println("<1> Add               <2> View                 <3> Return menu               <4> Exit");
			System.out.print("\n Input-->");
			try {
				function = sca.nextInt();
				if (function < 1 || function > 4)
					System.out.println("Invalid value");
				else
					return function;
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid value");
				sca.next();
			}

		} while (true);
	}

	// The real switching function (Use selection of user from menu() and
	// function())
	public static int switchFuction(int object, int function) {
		int decision = 0;
		Person temp;
		do {
			switch (object) {
			case 1:
				if (function == 1) {
					do {
						temp = new Doctor();
						if (!Person.duplicateCheck(HospitalManagement.doctors, temp))
							System.out.println("ID is duplicate.");
						else {
							HospitalManagement.doctors.add(temp);
							break;
						}
					} while (true);
				} else {
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					System.out.println(String.format("%-15s %-25s %-25s %-25s %-10s", "ID", "Name", "Specialist",
							"Timming", "Qualification"));
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					for (int i = 0; i < HospitalManagement.doctors.size(); i++) {
						String array[] = HospitalManagement.doctors.get(i).showInfo();
						System.out.println(String.format("%-15s %-25s %-25s %-25s %-10s", array[0], array[1], array[2],
								array[3], array[4]));
					}
				}
				break;
			case 2:
				if (function == 1) {
					do {
						temp = new Staff();
						if (!Person.duplicateCheck(HospitalManagement.staffs, temp))
							System.out.println("ID is duplicate.");
						else {
							HospitalManagement.staffs.add(temp);
							break;
						}
					} while (true);
				} else {
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					System.out.println(
							String.format("%-15s %-25s %-25s %10s %15s", "ID", "Name", "Designation", "Sex", "Salary"));
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					for (int i = 0; i < HospitalManagement.staffs.size(); i++) {
						String array[] = HospitalManagement.staffs.get(i).showInfo();
						System.out.println(String.format("%-15s %-25s %-25s %10s %15s", array[0], array[1], array[2],
								array[3], array[4]));
					}
				}
				break;
			case 3:
				if (function == 1) {
					do {
						temp = new Patient();
						if (!Person.duplicateCheck(HospitalManagement.patients, temp))
							System.out.println("ID is duplicate.");
						else {
							HospitalManagement.patients.add(temp);
							break;
						}
					} while (true);
				} else {
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					System.out.println(String.format("%-10s %-25s %-30s %6s %20s", "ID", "Name", "Disease", "Sex",
							"Admit Status"));
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					for (int i = 0; i < HospitalManagement.patients.size(); i++) {
						String array[] = HospitalManagement.patients.get(i).showInfo();
						System.out.println(String.format("%-10s %-25s %-30s %6s %20s", array[0], array[1], array[2],
								array[3], array[4]));
					}
				}
				break;

			case 4:
				if (function == 1) {
					Medical tempo = new Medical();
					HospitalManagement.medicals.add(tempo);
				} else {
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					System.out.println(String.format("%-25s %-25s %-25s %-15s %-10s", "Name", "Manufacturer",
							"Expiry Date", "Cost", "Count"));
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");

					for (int i = 0; i < HospitalManagement.medicals.size(); i++) {
						String array[] = HospitalManagement.medicals.get(i).showInfo();
						System.out.println(String.format("%-25s %-25s %-25s %-15s %-10s", array[0], array[1], array[2],
								array[3], array[4]));
					}
				}
				break;
			case 5:
				if (function == 1) {
					Lab tempo = new Lab();
					HospitalManagement.laboratories.add(tempo);
				} else {
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					System.out.println(String.format("%-30s %10s", "Lab", "Cost"));
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");

					for (int i = 0; i < HospitalManagement.laboratories.size(); i++) {
						String array[] = HospitalManagement.laboratories.get(i).showInfo();
						System.out.println(String.format("%-30s %10s", array[0], array[1]));
					}
				}

				break;
			case 6:
				if (function == 1) {
					Facility tempo = new Facility();
					HospitalManagement.facilities.add(tempo);
				} else {
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					System.out.println(String.format("%30s", "Facility"));
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");

					for (int i = 0; i < HospitalManagement.facilities.size(); i++) {
						String array[] = HospitalManagement.facilities.get(i).showInfo();
						System.out.println(String.format("  %30s", array[0]));
					}
				}
				break;
			}

			System.out.println(
					"\n\n\n*******************************************************************************************************");
			System.out.println("<1> Back \t <2> Back to main menu \t <3> Exit");
			System.out.print("\n Input-->");
			
			try {
				decision = sca.nextInt();
				if (decision < 1 || decision > 3)
					System.out.println("Invalid value");
				else
					return decision;
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid value");
				sca.next();
			}
		} while (true);
	}

}
