package sort;

import java.util.Comparator;

/**
 * @author tackedev
 * @since 3/17/21 3:35 PM
 */
public class Employee implements Comparable<Employee> {

    private String id;
    private String name;
    private int salary;

    public Employee(String id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + ',' + name + ',' + salary;
    }

    @Override
    public int compareTo(Employee employee) {
        return this.id.compareTo(employee.id);
    }

    public static Comparator<Employee> compareBySalary = new Comparator<Employee>() {
        @Override
        public int compare(Employee employee1, Employee employee2) {
            return Integer.compare(employee2.salary, employee1.salary);
        }
    };
}
