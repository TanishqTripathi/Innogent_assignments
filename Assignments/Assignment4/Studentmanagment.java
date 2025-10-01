// package Assignment4;

import java.util.*;
import java.io.*;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String str) {
        super(str);
    }
}

class Invalidmarksexception extends Exception {
    public Invalidmarksexception(String str) {
        super(str);
    }
}

class InvalidDeleteException extends Exception {
    public InvalidDeleteException(String str) {
        super(str);
    }
}

class Class {
    static int id_counter;
    int id;
    String name;

    Class(String name) {

        this.id = id_counter;
        this.name = name;
    }
}

class Students {
    static int id_count;
    int id;
    String name;
    int class_id;
    int marks;
    String gender;
    int age;

    Students(String name, int class_id, int marks, String gender, int age) {
        this.id = id_count;
        this.name = name;
        this.class_id = class_id;
        this.marks = marks;
        this.gender = gender.toLowerCase();
        this.age = age;
    }
}

class Address {
    static int id_counts;
    int id;
    int pincode;
    String city;
    int student_id;

    Address(int id, int pincode, String city, int student_id) {
        id_counts++;
        this.id = id_counts;
        this.pincode = pincode;
        this.city = city;
        this.student_id = student_id;
    }
}

class studentmanagmentsystem {
    List<Class> classes = new ArrayList<>();
    List<Students> students = new ArrayList<>();
    List<Address> addresses = new ArrayList<>();

    void addClass(String name) {
        if (name != null && name.matches("[a-zA-Z]+")) {
            Class newClass = new Class(name);
            classes.add(newClass);
            System.out.println("Class '" + name + "' added successfully with ID: " + newClass.id);
        } else {
            System.out.println("Invalid class name! Class name should contain only letters.");
        }
    }

    void addStudent(Students s, List<Address> addr) throws InvalidAgeException, Invalidmarksexception {
        if (s.age > 20) {
            throw new InvalidAgeException("Invalid Age: " + s.age + " Age should be less than 20!");
        }
        if (s.marks < 0 || s.marks > 100) {
            throw new Invalidmarksexception("Invalid Marks: " + s.marks + " Marks should be between 0 and 100!");
        }

        Students.id_count++;
        s.id = Students.id_count;
        students.add(s);
        addresses.addAll(addr);
    }

    void getAllStudents() {
        for (Students s : students) {
            System.out.println("ID: " + s.id + ", Name: " + s.name + ", Class ID: " + s.class_id + ", Marks: " + s.marks
                    + ", Gender: " + s.gender + ", Age: " + s.age);
        }
    }

    void save_students_tofile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Students s : students) {
                writer.write("ID: " + s.id + ", Name: " + s.name + ", Class ID: " + s.class_id + ", Marks: "
                        + s.marks + ", Gender: " + s.gender + ", Age: " + s.age + "\n");
            }
            writer.close();
            System.out.println("Students data saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }

    void save_student_class(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Students s : students) {
                String className = "";
                for (Class c : classes) {
                    if (c.id == s.class_id) {
                        className = c.name;
                        break;
                    }
                }
                writer.write("Student Name: " + s.name + "Class_id: " + s.id + ", Class Name: " + className + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }

    void save_student_address(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Students s : students) {
                writer.write("Student Name: " + s.name + "\n");
                for (Address a : addresses) {
                    if (a.student_id == s.id) {
                        writer.write("Address ID: " + a.id + ", Pincode: " + a.pincode + ", City: " + a.city + "\n");
                    }
                }
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }

    void add_allstudents_data(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Students s : students) {
                String className = "";
                for (Class c : classes) {
                    if (c.id == s.class_id) {
                        className = c.name;
                        break;
                    }
                }
                for (Address a : addresses) {
                    if (a.student_id == s.id) {
                        writer.write("Student Name: " + s.name + "Student_id" + s.id + "Marks: " + s.marks
                                + "Class Name: " + className
                                + ", Address ID: " + a.id + ", Pincode: " + a.pincode + ", City: " + a.city + "\n");

                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }

    void deleteclass(String name) throws InvalidDeleteException {
        Class classtodelete = null;

        for (Class c : classes) {
            if (c.name.equals(name)) {
                classtodelete = c;
                break;
            }
        }
        if (classtodelete == null) {
            throw new InvalidDeleteException("Class with name " + name + " does not exist!");
        }

        for (Students s : students) {
            if (s.class_id == classtodelete.id) {
                throw new InvalidDeleteException(
                        "Class with name " + name + " cannot be deleted as it has associated students!");
            }
        }

        classes.remove(classtodelete);
        System.out.println("Class with name " + name + " deleted successfully!");
    }

    void deletestudent(int id) throws InvalidDeleteException {
        Students studenttodelete = null;

        for (Students s : students) {
            if (s.id == id) {
                studenttodelete = s;
                break;
            }
        }
        if (studenttodelete == null) {
            throw new InvalidDeleteException("Student with ID " + id + " does not exist!");
        }

        students.remove(studenttodelete);
        addresses.removeIf(a -> a.student_id == id);
        System.out.println("Student with ID " + id + " deleted successfully!");
    }

    void saveTop5Students(String filename) {
        students.sort((s1, s2) -> s2.marks - s1.marks);

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("--- Top 5 Students by Marks ---\n");

            int count = Math.min(5, students.size());
            for (int i = 0; i < count; i++) {
                Students s = students.get(i);
                String className = "";
                for (Class c : classes) {
                    if (c.id == s.class_id) {
                        className = c.name;
                        break;
                    }
                }
                writer.write(
                        "Rank " + (i + 1) + ": " + s.name + " | Marks: " + s.marks + " | Class: " + className + "\n");
            }

            writer.close();
            System.out.println("Top 5 students saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving top 5 students: " + e.getMessage());
        }
    }

    public int getClassIdByName(String name) {
        for (Class c : classes) {
            if (c.name.equalsIgnoreCase(name.trim())) {
                return c.id;
            }
        }
        return -1;
    }

    public void readFile(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("File " + filename + " does not exist!");
                return;
            }

            Scanner sc = new Scanner(file);
            System.out.println("\n--- Contents of " + filename + " ---");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
            sc.close();
            System.out.println("--- End of file ---\n");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

public class Studentmanagment {
    public static void main(String args[]) {
        studentmanagmentsystem sms = new studentmanagmentsystem();
        Scanner sc = new Scanner(System.in);

        int numClasses = 0;
        while (true) {
            try {
                System.out.print("Enter number of classes to add: ");
                numClasses = sc.nextInt();
                if (numClasses <= 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a positive integer.");
            }
            sc.nextLine();
        }

        int classesAdded = 0;
        while (classesAdded < numClasses) {
            System.out.print("Enter name of class " + (classesAdded + 1) + ": ");
            String className = sc.nextLine().trim();

            if (className.isEmpty()) {
                System.out.println("Class name cannot be empty!");
                continue; // retry
            }

            if (!className.matches("[a-zA-Z ]+")) {
                System.out.println("Class name can contain only letters!");
                continue;
            }

            sms.addClass(className);
            classesAdded++;
        }

        int numStudents = 0;
        while (true) {
            try {
                System.out.print("Enter number of students to add: ");
                numStudents = sc.nextInt();
                sc.nextLine();
                if (numStudents <= 0)
                    throw new InputMismatchException();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a positive integer.");
                sc.nextLine();
            }
        }

        for (int i = 0; i < numStudents; i++) {
            System.out.println("\n--- Enter details for Student " + (i + 1) + " ---");

            String name = "";
            while (true) {
                System.out.print("Name: ");
                name = sc.nextLine().trim();
                if (!name.isEmpty() && name.matches("[a-zA-Z ]+"))
                    break;
                System.out.println("Invalid name! Only letters and spaces are allowed.");
            }

            int class_id = -1;
            while (true) {
                System.out.print("Class Name: ");
                String className = sc.nextLine().trim();
                class_id = sms.getClassIdByName(className);
                if (class_id != -1)
                    break;
                System.out.println("Class not found! Enter a valid class name.");
            }

            int marks = -1;
            while (true) {
                try {
                    System.out.print("Marks (0-100): ");
                    marks = sc.nextInt();
                    sc.nextLine();
                    if (marks < 0 || marks > 100)
                        throw new InputMismatchException();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid marks! Enter an integer between 0 and 100.");
                    sc.nextLine();
                }
            }

            String gender = "";
            while (true) {
                System.out.print("Gender (M/F): ");
                gender = sc.nextLine().trim().toUpperCase();
                if (gender.equals("M") || gender.equals("F"))
                    break;
                System.out.println("Invalid input! Enter M or F.");
            }

            int age = -1;
            while (true) {
                try {
                    System.out.print("Age (<=20): ");
                    age = sc.nextInt();
                    sc.nextLine();
                    if (age <= 0 || age > 20)
                        throw new InputMismatchException();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid age! Enter a positive integer less than or equal to 20.");
                    sc.nextLine();
                }
            }

            Students s = new Students(name, class_id, marks, gender, age);

            List<Address> addr = new ArrayList<>();
            int numAddresses = 0;
            while (true) {
                try {
                    System.out.print("Enter number of addresses for this student: ");
                    numAddresses = sc.nextInt();
                    sc.nextLine();
                    if (numAddresses < 0)
                        throw new InputMismatchException();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Enter a non-negative integer.");
                    sc.nextLine();
                }
            }

            for (int j = 0; j < numAddresses; j++) {
                System.out.println("\n--- Enter details for Address " + (j + 1) + " ---");

                int pincode = 0;
                while (true) {
                    try {
                        System.out.print("Pincode: ");
                        pincode = sc.nextInt();
                        sc.nextLine();
                        if (pincode <= 0)
                            throw new InputMismatchException();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid pincode! Enter numbers only.");
                        sc.nextLine();
                    }
                }

                System.out.print("City: ");
                String city = sc.nextLine().trim();
                while (city.isEmpty()) {
                    System.out.println("City cannot be empty!");
                    city = sc.nextLine().trim();
                }

                Address a = new Address(0, pincode, city, s.id);
                addr.add(a);
            }

            try {
                sms.addStudent(s, addr);
                System.out.println("Student added successfully!");
            } catch (InvalidAgeException | Invalidmarksexception e) {
                System.out.println("Error adding student: " + e.getMessage());
            }
        }

        System.out.println("\n--- All Students ---");
        sms.getAllStudents();

        sms.save_students_tofile("students.txt");
        sms.save_student_class("students_class.txt");
        sms.save_student_address("students_address.txt");
        sms.add_allstudents_data("all_students_data.txt");
        sms.saveTop5Students("top5students.txt");
        sms.readFile("top5students.txt");

        System.out.println("\nData saved to files successfully!");
        sc.close();
    }
}