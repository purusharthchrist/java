import java.util.*;

class Employee{
    int emp_id;
    String emp_name;
    String emp_desig;
    Employee(int e_id, String e_name, String desig){
        this.emp_id = e_id;
        this.emp_name = e_name;
        this.emp_desig = desig;
        System.out.println("Employee Created!");
    }

    void setter(int val){
        Scanner sc = new Scanner(System.in);
        switch (val){
            case 1:
                System.out.println("Enter the new employee ID: ");
                this.emp_id = sc.nextInt();
                break;
            case 2:
                System.out.println("Enter the new employee name: ");
                this.emp_name = sc.next();
                break;
            case 3:
                System.out.println("Enter the new employee designation: ");
                this.emp_desig = sc.next();
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        System.out.println("The new employee details are");
        System.out.println("Employee ID: "+this.emp_id);
        System.out.println("Employee Name: "+this.emp_name);
        System.out.println("EMployee Designation: "+this.emp_desig);
    }

    void display(){
        System.out.println("The employee details are");
        System.out.println("Employee ID: "+this.emp_id);
        System.out.println("Employee Name: "+this.emp_name);
        System.out.println("Employee Designation: "+this.emp_desig);
    }

    double calculateBonus(){
        
    }
}



class HourlyEmployee extends Employee{
    double hourlyRate;
    int hoursWorked;
    HourlyEmployee(int e_id, String e_name, String desig, double h_rate, int h_work){
        super(e_id, e_name, desig);
        this.hourlyRate = h_rate;
        this.hoursWorked = h_work;
    }

    double weekly_sal(){
        return hourlyRate*hoursWorked;
    }

    void display(){
        super.display();
        System.out.println("Weekly salary is: "+this.weekly_sal());
    }
}

class SalariedEmployee extends Employee{
    double monthlySalary;
    SalariedEmployee(int e_id, String e_name, String desig, double m_sal){
        super(e_id, e_name, desig);
        this.monthlySalary = m_sal;
    }

    double weekly_sal(){
        return monthlySalary/4;
    }

    void display(){
        super.display();
        System.out.println("Weekly salary is: "+this.weekly_sal());
    }
}

public class Main{
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int id;
    String name;
    String designation;
    int end;
    int sal_st;
    double sal;
    Employee new_emp;

    System.out.println("Enter the employee ID: ");
    id = sc.nextInt();
    sc.nextLine();
    System.out.println("Enter the employee name: ");
    name = sc.nextLine();
    System.out.println("Enter the employee designation: ");
    designation = sc.nextLine();

    System.out.println("What will be the salary structure of the employee?");
    System.out.println("1 - Hourly");
    System.out.println("2 - Monthly");
    sal_st = sc.nextInt();
    sc.nextLine();

    if (sal_st==1){
        int hwork;
        System.out.println("Enter the hourly salary: ");
        sal = sc.nextDouble();
        System.out.println("Enter the work hours: ");
        hwork = sc.nextInt();
        new_emp = new HourlyEmployee(id, name, designation, sal, hwork);
    }
    else{
        System.out.println("Enter the monthly salary");
        sal = sc.nextDouble();
        new_emp = new SalariedEmployee(id, name, designation, sal);
    }
    

    System.out.println("Do you want to edit any details? (1-Yes, 2-No)");
    end = sc.nextInt();

    while(end == 1){
            int change;
            System.out.println("Which field do you want to edit?");
            System.out.println("1 - Employee ID");
            System.out.println("2 - Employee Name");
            System.out.println("3 - Employee Designation");
            change = sc.nextInt();
            new_emp.setter(change);

            System.out.println("Do you wish to make any further changes?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");
            end = sc.nextInt();
        }

    System.out.println("Details of the employee: ");
    new_emp.display();
    }
}