package hash;

/**
 *
 * @author tackedev
 * @since Mar 14, 2021 3:37:38 PM
 */
public class StudentManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final String filename = "students.txt";
        Menu menu = new Menu();
        menu.add("Add new student");
        menu.add("Search student");
        menu.add("Remove a student");
        menu.add("Update a student");
        menu.add("Print the list");
        menu.add("Save the list to file");
        menu.add("Quit");
        
        int choice;
        StudentList list = new StudentList();
        list.loadFromFile(filename);
        do {
            System.out.println("\nSTUDENT MANAGER");
            choice = menu.getUserChoice();
            switch (choice) {
                case 1: list.addStudent(); break;
                case 2: list.searchStudent(); break;
                case 3: list.removeStudent(); break;
                case 4: list.updateStudent(); break;
                case 5: list.print(); break;
                case 6: list.saveToFile(filename); break;
            }
        } while (choice >= 0 && choice < 7);
    }
}
