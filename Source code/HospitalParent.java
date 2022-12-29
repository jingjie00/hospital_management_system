import java.util.ArrayList;
import java.util.Scanner;

/***************************************************************
 *     The main parent of Person, Lab, Medical and facility    *
 ***************************************************************/

public abstract class HospitalParent {
	Scanner sc = new Scanner(System.in);

	public static int intValidate(String item, int min) {
		int assign;
		if (item.matches("[0-9]+")) {
			assign = Integer.parseInt(item);
			if (assign >= min)
				return assign;
		}
		System.out.println("Not valid input");
		return -1;
	}
	
	public static void sort(ArrayList<Person> a, ArrayList<Medical> b, ArrayList<Lab> c, ArrayList<Facility> d) {
		if (a != null) {
			for (int j = 0; j < a.size(); j++)
				for (int i = 0; i < a.size(); i++) {
					int test = a.get(i).getIdentity().toString().compareTo(a.get(j).getIdentity().toString());
					if (test > 0) {
						a.add(a.get(i));
						a.set(i, a.get(j));
						a.set(j, a.get(a.size() - 1));
						a.remove(a.size() - 1);
					}
				}
		} else if (b != null) {
			for (int j = 0; j < b.size(); j++)
				for (int i = 0; i < b.size(); i++) {
					int test = b.get(i).getIdentity().toString().compareTo(b.get(j).getIdentity().toString());
					if (test > 0) {
						b.add(b.get(i));
						b.set(i, b.get(j));
						b.set(j, b.get(b.size() - 1));
						b.remove(b.size() - 1);
					}
				}
		} else if (c != null) {
			for (int j = 0; j < c.size(); j++)
				for (int i = 0; i < c.size(); i++) {
					int test = c.get(i).getIdentity().toString().compareTo(c.get(j).getIdentity().toString());
					if (test > 0) {
						c.add(c.get(i));
						c.set(i, c.get(j));
						c.set(j, c.get(c.size() - 1));
						c.remove(c.size() - 1);
					}
				}
		} else if (d != null) {
			for (int j = 0; j < d.size(); j++)
				for (int i = 0; i < d.size(); i++) {
					int test = d.get(i).getIdentity().toString().compareTo(d.get(j).getIdentity().toString());
					if (test > 0) {
						d.add(d.get(i));
						d.set(i, d.get(j));
						d.set(j, d.get(d.size() - 1));
						d.remove(d.size() - 1);
					}
				}
		}
	}

	public abstract String getIdentity();//for sort purpose

	public abstract String[] showInfo();
}
