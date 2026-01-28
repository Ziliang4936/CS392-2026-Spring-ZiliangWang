
public class Employee {
	public String name;
	public int ID;
	public double salary;
	
	// Constructor
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
		this.ID = 0;
	}
	
	// toString() method for printing
	@Override
	public String toString() {
		return name + " - ID: " + ID + " - $" + salary;
	}
}
