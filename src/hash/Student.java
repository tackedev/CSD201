package hash;

import java.util.Scanner;

/**
 *
 * @author tackedev
 * @since Mar 14, 2021 11:58:17 AM
 */
public class Student implements Comparable {
    
    private String code;
    private String name;
    private int mark;
   
    private final Scanner sc = new Scanner(System.in);

    public Student(String code, String name, int mark) {
        this.code = code.toUpperCase().trim();
        this.name = name.toUpperCase().trim();
        this.mark = mark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return code + ',' + name + ',' + mark;
    }

    @Override
    public int compareTo(Object obj) {
        return code.compareTo(((Student) obj).code);
    }
    
    public void update() {
        System.out.println("New name, ENTER for bypassing: ");
        String newName = sc.nextLine().trim().toUpperCase();
        if (newName.length() > 0) {
            this.name = newName;
        }
        
        System.out.println("New mark, ENTER for bypassing: ");
        String newMarkStr = sc.nextLine().trim();
        if (newMarkStr.length() > 0) {
            this.mark = Integer.parseInt(newMarkStr);
        }
    }
}
