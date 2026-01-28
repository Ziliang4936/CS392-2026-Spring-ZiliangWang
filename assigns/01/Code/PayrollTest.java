public class PayrollTest {
    public static void main(String[] args) {
        System.out.println("=== Payroll Test Suite ===\n");
        
        // Test 1: Constructor and size()
        System.out.println("Test 1: Constructor and size()");
        Payroll payroll = new Payroll();
        System.out.println("Initial size: " + payroll.size());  // Should be 0
        System.out.println();
        
        // Test 2: add_employee()
        System.out.println("Test 2: add_employee()");
        payroll.add_employee(new Employee("John", 50000));
        payroll.add_employee(new Employee("Jane", 60000));
        payroll.add_employee(new Employee("Bob", 55000));
        System.out.println("Size after adding 3 employees: " + payroll.size());  // Should be 3
        System.out.println();
        
        // Test 3: print()
        System.out.println("Test 3: print()");
        payroll.print();
        System.out.println();
        
        // Test 4: find_employee()
        System.out.println("Test 4: find_employee()");
        try {
            int index = payroll.find_employee("Jane");
            System.out.println("Found Jane at index: " + index);
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            int index = payroll.find_employee("Alice");
            System.out.println("Found Alice at index: " + index);
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
        
        // Test 5: remove_employee()
        System.out.println("Test 5: remove_employee()");
        try {
            payroll.remove_employee(1);  // Remove Jane
            System.out.println("Removed employee at index 1");
            System.out.println("Size after removal: " + payroll.size());  // Should be 2
            payroll.print();
        } catch (EmployeeIndexException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
        
        // Test 6: remove_employee() with invalid index
        System.out.println("Test 6: remove_employee() with invalid index");
        try {
            payroll.remove_employee(100);  // Invalid index
        } catch (EmployeeIndexException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
        
        // Test 7: copy_payroll()
        System.out.println("Test 7: copy_payroll()");
        Payroll payroll2 = new Payroll();
        payroll2.copy_payroll(payroll);
        System.out.println("Copied payroll size: " + payroll2.size());
        System.out.println("Copied payroll contents:");
        payroll2.print();
        System.out.println();
        
        // Test 8: add_payroll()
        System.out.println("Test 8: add_payroll()");
        Payroll payroll3 = new Payroll();
        payroll3.add_employee(new Employee("Mike", 65000));
        payroll3.add_employee(new Employee("Sarah", 70000));
        System.out.println("Payroll1 before merge:");
        payroll.print();
        System.out.println("Payroll3 to merge:");
        payroll3.print();
        payroll.add_payroll(payroll3);
        System.out.println("Payroll1 after merge:");
        payroll.print();
        System.out.println("Size after merge: " + payroll.size());
    }
}