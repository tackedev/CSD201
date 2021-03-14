package hash;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author tackedev
 * @since Mar 14, 2021 12:08:44 PM
 */
public class StudentList extends Hashtable<String, Student> {
    
    private final Scanner sc = new Scanner(System.in);

    public StudentList() {
        super();
    }
    
    public boolean loadFromFile(String filename) {
        try {
            FileInputStream fi = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fi, "UTF8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                String[] ar = line.split("[,]");
                if (ar.length == 3) {
                    Student student = new Student(ar[0], ar[1], Integer.parseInt(ar[2]));
                    this.put(student.getCode(), student);
                }
            } 
            br.close(); isr.close(); fi.close();
            return true;
        } catch (FileNotFoundException ex) {
        } catch (UnsupportedEncodingException ex) {
        } catch (IOException ex) {
        }
        return false;
    }
    
    public boolean saveToFile(String filename) {
        try {
            FileOutputStream fo = new FileOutputStream(filename);
            OutputStreamWriter osw = new OutputStreamWriter(fo, "UTF8");
            PrintWriter pw = new PrintWriter(osw);
            Iterator<String> it = this.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                Student student = this.get(key);
                pw.print(student);
            }
            pw.close(); osw.close(); fo.close();
            return true;
        } catch (FileNotFoundException ex) {
        } catch (UnsupportedEncodingException ex) {  
        } catch (IOException ex) {
        }
        return false;
    }
    
    public Student search(String code) {
        return this.get(code);
    }
    
    public void addStudent() {
        String code, name;
        int mark;
        System.err.println("Enter data of new student:");
        boolean cont;
        do {            
            System.out.println("Code:");
            code = sc.nextLine().toUpperCase().trim();
            cont = search(code) != null;
            if (cont) {
                System.out.println("Code is duplicated!");
            }
        } while (cont);
        System.out.println("Name:");
        name = sc.nextLine().trim();
        System.out.println("Mark:");
        mark = Integer.parseInt(sc.nextLine());
        this.put(code, new Student(code, name, mark));
        System.out.println("New student " + code + " has been added.");
    }
    
    public void searchStudent() {
        String code;
        if (this.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        System.out.println("Enter student code for searching:");
        code = sc.nextLine().toUpperCase().trim();
        Student student = this.search(code);
        if (student == null) {
            System.out.println("This code doesn't exist!");
        } else {
            System.out.println(student);
        }
    }
    
    public void removeStudent() {
        String code;
        if (this.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        System.out.println("Enter student code for removing:");
        code = sc.nextLine().toUpperCase().trim();
        Student student =  this.search(code);
        if (student == null) {
            System.out.println("This code doesn't exist!");
        } else {
            this.remove(code);
            System.out.println("The student " + code + " has been removed.");
        }
    }
    
    public void updateStudent() {
        String code;
        if (this.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        System.out.println("ENter student code for updating:");
        code = sc.nextLine().toUpperCase().trim();
        Student student = this.search(code);
        if (student == null) {
            System.out.println("This code doesn't exist!");
        } else {
            student.update();
        }
    }
    
    public void print() {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        ArrayList<Student> list = new ArrayList<>(this.values());
        Collections.sort(list);
        list.forEach(student -> {
            System.out.println(student);
        });
    }
}
