// package Assignment3_studentmangment;

import java.util.*;

class Class {
    int id;
    String name;

    Class(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Student {
    int id;
    String name;
    int class_id;
    int marks;
    String gender;
    int age;

    Student(int id, String name, int class_id, int marks, String gender, int age) {
        this.id = id;
        this.name = name;
        this.class_id = class_id;
        this.marks = marks;
        this.gender = gender;
        this.age = age;
    }
}

class Address {
    int id;
    int pincode;
    String city;
    int student_id;

    Address(int id, int pincode, String city, int student_id) {
        this.id = id;
        this.pincode = pincode;
        this.city = city.toLowerCase();
        this.student_id = student_id;
    }
}

class studentmanagmentsystem {
    List<Class> classes = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    List<Address> addresses = new ArrayList<>();

    void addclass(Class c) {
        if (c.name == null || c.name.isEmpty()) {
            System.out.println("Class name cannot be empty");
            return;
        }
        if (c.id <= 0) {
            System.out.println("Class id should be positive");
            return;
        }
        if (classes.contains(c)) {
            System.out.println("Class already exists");
            return;
        }
        classes.add(c);
    }

    void addstudent(Student s, List<Address> a) {
        if (s.age > 20) {
            System.out.println("Age should be less than 20");
            return;
        }
        students.add(s);
        addresses.addAll(a);
    }

    void rank() {
        students.sort((s1, s2) -> s2.marks - s1.marks);
        int rank = 1;
        for (int i = 0; i < students.size(); i++) {
            if (i > 0 && students.get(i).marks == students.get(i - 1).marks) {
                System.out.println(
                        "Rank " + (rank - 1) + ": " + students.get(i).name + " Marks: " + students.get(i).marks);
            } else {
                System.out.println("Rank " + rank + ": " + students.get(i).name + " Marks: " + students.get(i).marks);
                rank++;
            }
        }
    }

    void findbycity(String city) {
        city = city.toLowerCase();
        if (city == null || city.isEmpty()) {
            System.out.println("City name cannot be empty");
            return;
        }
        System.out.println("Students in city " + city + ": ");
        for (Address a : addresses) {
            if (a.city.equals(city)) {
                for (Student s : students) {
                    if (s.id == a.student_id) {
                        System.out.println("Student Name: " + s.name + " Marks: " + s.marks + " City: " + city);
                    }
                }
            }
        }
    }

    void findbypincode(int pincode) {
        System.out.println("Students with pincode " + pincode + ": ");
        for (Address a : addresses) {
            if (a.pincode == pincode) {
                for (Student s : students) {
                    if (s.id == a.student_id) {
                        System.out.println("Student Name: " + s.name + " Marks: " + s.marks + " Pincode: " + pincode);
                    }
                }
            }
        }
    }

    void findbyclass(int class_id) {
        System.out.println("Students in class id " + class_id + ": ");
        String x = null;
        for (Class c : classes) {
            if (c.id == class_id) {
                x = c.name;
                break;
            }
        }
        for (Student s : students) {
            if (s.class_id == class_id) {
                System.out.println("Student Name: " + s.name + " Marks: " + s.marks + "Class: " + x);
            }
        }
    }

    void ispassed() {
        System.out.println("Students who passed: ");
        for (Student s : students) {
            if (s.marks >= 50) {
                System.out.println("Student Name: " + s.name + " Marks: " + s.marks);
            }
        }
    }

    void failed() {
        System.out.println("Students who failed: ");
        for (Student s : students) {
            if (s.marks < 50) {
                System.out.println("Student Name: " + s.name + " Marks: " + s.marks);
            }
        }
    }

    void deletestudent(int student_id) {
        students.removeIf(s -> s.id == student_id);
        addresses.removeIf(a -> a.student_id == student_id);
        System.out.println("Student with id " + student_id + " deleted");
    }

    void pagination_marks(int start, int end) {
        Collections.sort(students, (s1, s2) -> s2.marks - s1.marks);
        System.out.println("Students from index " + start + " to " + end + ": ");
        for (int i = start; i < end && i < students.size(); i++) {

            Student s = students.get(i);
            if (s.gender.equals("F")) {
                System.out.println("Student Name: " + s.name + " Marks: " + s.marks + " Gender: " + s.gender);
            }
        }
    }

    void pagination_name(int start, int end) {
        Collections.sort(students, (s1, s2) -> s2.name.compareTo(s1.name));
        System.out.println("Students from index " + start + " to " + end + ": ");
        for (int i = start; i < end && i < students.size(); i++) {
            Student s = students.get(i);
            System.out.println("Student Name: " + s.name + " Marks: " + s.marks);
        }

    }

    void showtable() {
        System.out.printf("%-5s %-10s %-10s %-7s %-7s %-10s %-10s %-10s\n", "ID", "Name", "ClassID", "Marks", "Age",
                "Gender", "City", "Pincode");
        for (Class c : classes) {
            for (Student s : students) {
                if (s.class_id == c.id) {
                    List<Address> addr = new ArrayList<>();
                    for (Address a : addresses) {
                        if (a.student_id == s.id) {
                            addr.add(a);
                        }
                    }

                    if (addr.isEmpty()) {
                        System.out.printf("%-5d %-10s %-10s %-7d %-7d %-10s\n", s.id, s.name, c.name, s.marks, s.age,
                                s.gender);
                    } else {
                        for (Address a : addr) {
                            System.out.printf("%-5s %-10s %-10s %-7s %-7s %-10s %-10s %-10s\n", s.id, s.name, c.name,
                                    s.marks, s.age, s.gender, a.city, a.pincode);
                        }
                    }
                }
            }
        }
    }
}

public class Student_managment {
    public static void main(String[] args) {
        studentmanagmentsystem sms = new studentmanagmentsystem();
        sms.addclass(new Class(1, "A"));
        sms.addclass(new Class(2, "B"));
        sms.addclass(new Class(3, "C"));
        sms.addclass(new Class(4, "D"));

        sms.addstudent(new Student(1, "stud01", 1, 88, "F", 10),
                Arrays.asList(new Address(1, 452002, "indore", 1), new Address(2, 422002, "delhi", 1)));

        sms.addstudent(new Student(2, "stud02", 1, 70, "F", 11), Arrays.asList(new Address(3, 442002, "indore", 2)));

        sms.addstudent(new Student(3, "stud03", 2, 88, "M", 22), Arrays.asList(new Address(4, 462002, "delhi", 3)));

        sms.addstudent(new Student(4, "stud04", 2, 55, "M", 33), Arrays.asList(new Address(5, 472002, "indore", 4)));

        sms.addstudent(new Student(5, "stud05", 1, 50, "F", 14),
                Arrays.asList(new Address(6, 452002, "indore", 5), new Address(7, 452002, "delhi", 5)));

        sms.addstudent(new Student(6, "stud06", 3, 50, "F", 13), Arrays.asList(new Address(8, 482002, "mumbai", 6)));

        sms.addstudent(new Student(7, "stud07", 3, 10, "F", 22), Arrays.asList(new Address(9, 482002, "bhopal", 7)));

        sms.addstudent(new Student(8, "stud08", 3, 0, "M", 11), Arrays.asList(new Address(10, 482002, "indore", 8)));

        sms.addstudent(new Student(9, "stud09", 1, 88, "F", 10), Arrays.asList(new Address(1, 452002, "Indore", 9)));

        sms.addstudent(new Student(10, "stud10", 2, 70, "M", 11), Arrays.asList(new Address(2, 422002, "Delhi", 10)));

        sms.addstudent(new Student(11, "stud11", 3, 95, "M", 12), Arrays.asList(new Address(3, 462002, "Mumbai", 11)));

        sms.addstudent(new Student(12, "stud12", 4, 55, "F", 13), Arrays.asList(new Address(4, 472002, "Bhopal", 12)));

        sms.addstudent(new Student(13, "stud13", 1, 50, "F", 14), Arrays.asList(new Address(5, 452002, "Indore", 13)));

        sms.addstudent(new Student(14, "stud14", 2, 60, "M", 13), Arrays.asList(new Address(6, 482002, "Mumbai", 14)));

        sms.addstudent(new Student(15, "stud15", 3, 75, "F", 12), Arrays.asList(new Address(7, 482002, "Bhopal", 15)));

        sms.addstudent(new Student(16, "stud16", 4, 80, "M", 11),
                Arrays.asList(new Address(8, 482002, "Indore", 16), new Address(12, 462002, "Delhi", 16)));

        sms.addstudent(new Student(17, "stud17", 1, 65, "F", 14), Arrays.asList(new Address(9, 492002, "Delhi", 17)));

        sms.addstudent(new Student(18, "stud18", 2, 90, "M", 15), Arrays.asList(new Address(10, 492002, "Mumbai", 18)));

        sms.addstudent(new Student(19, "stud19", 3, 85, "F", 13), Arrays.asList(new Address(11, 492002, "Bhopal", 19)));

        sms.addstudent(new Student(20, "stud20", 4, 78, "M", 12), Arrays.asList(new Address(12, 462002, "Indore", 20)));

        sms.showtable();
        System.out.println();
        sms.rank();
        System.out.println();
        sms.findbycity("Indore");
        System.out.println();
        sms.findbyclass(3);
        System.out.println();
        sms.findbypincode(482002);
        System.out.println();
        sms.ispassed();
        System.out.println();
        sms.failed();
        System.out.println();
        sms.deletestudent(1);
        sms.showtable();
        System.out.println();
        sms.pagination_marks(1, 20);
        sms.pagination_name(1, 20);
    }
}
