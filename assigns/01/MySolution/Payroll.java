
public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;

    public Payroll() {
	/* your code */
    current_size = 0;
    maximum_size = 512;

    people = new Employee[maximum_size];
    }
    
    // size() return current_size.
    public int size() {
        return current_size;
    }

    // print() to print the Employees currently on the Payroll, one per line.
    public void print() {
        for ( int i = 0; i < current_size; i++ ) {
            System.out.println(people[i].toString());
        }
    }

    // add a new Employee to the payroll, making more room on the payroll if necessary.
    public void add_employee(Employee newbie) {
	/* your code */
        if ( current_size < maximum_size ) {
            people[current_size] = newbie;
            current_size++;
        } else {
            System.out.println("Payroll is full.");
        }

    }

    // remove Employee in people[i], shifting other Employees over to fill its place 
    // (or just replacing it with the last Employee: remove is not guaranteed to keep order). 
    // Throw an exception if there is no entry at position i.
    public void remove_employee(int i) throws EmployeeIndexException {
    /* your code */
        if (i < 0 || i >= current_size) {
            throw new EmployeeIndexException("There is no entry at position " + i);
        } else {
            people[i] = people[current_size - 1];
            people[current_size - 1] = null;
            current_size--;
        }
    }
    
    //return the first i such that people[i].name == target_name. 
    // Throw an exception if no such i is found.
    public int find_employee(String name) throws EmployeeNotFoundException {
	/* your code */
        for ( int i = 0; i < current_size; i++) {
            if (people[i].name.equals(name)) {
                return i;
            }
        }
        throw new EmployeeNotFoundException("No such employee with name " + name);
    }

    // assign one Payroll to another.
    public void add_payroll(Payroll source) {
	/* your code */
        for (int i = 0; i < source.current_size; i++) {
                if (this.current_size < this.maximum_size) {
                    this.people[this.current_size] = source.people[i];
                    this.current_size++;
                } else {
                    System.out.println("Payroll is full. Cannot add more employees.");
                    break;
                }
            }
    }

    public void copy_payroll(Payroll source) {
	/* your code */
        this.current_size = source.current_size;
        this.maximum_size = source.maximum_size;
        
        // Create new array
        this.people = new Employee[source.maximum_size];
        
        // Copy all employees
        for (int i = 0; i < source.current_size; i++) {
            this.people[i] = source.people[i];
        }
    }

    private Employee people[];
    private int maximum_size;
    private int current_size;
}
