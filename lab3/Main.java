//importing the packages
import java.util.*;

//base class
class Employee{
    int emp_id;
    String emp_name;
    String emp_desig;
    double bonus;

    //class constructor
    Employee(int e_id, String e_name, String desig){
        this.emp_id = e_id;
        this.emp_name = e_name;
        this.emp_desig = desig;
        System.out.println("Employee Created!");
    }

    //function to update employee fields
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
        System.out.println("Employee Designation: "+this.emp_desig);
    }

    //function to display the employee details
    void display(){
        System.out.println("The employee details are");
        System.out.println("Employee ID: "+this.emp_id);
        System.out.println("Employee Name: "+this.emp_name);
        System.out.println("Employee Designation: "+this.emp_desig);
    }

    //function to calculate bonus of all the employees
    void calculateBonus(double sal){
        this.bonus = sal*0.25;
    }
}


class HourlyEmployee extends Employee{
    double hourlyRate;
    int hoursWorked;
    
    //constructor
    HourlyEmployee(int e_id, String e_name, String desig, double h_rate, int h_work){
        //using the constructor of the parent class
        super(e_id, e_name, desig);
        this.hourlyRate = h_rate;
        this.hoursWorked = h_work;
    }

    //function to calculate weekly salary
    double weekly_sal(){
        return hourlyRate*hoursWorked;
    }

    //overriding the calculateBonus method of the parent class
    void calculateBonus(){
        //using the base class function
        super.calculateBonus(this.weekly_sal());
        this.bonus = this.bonus + 1000;
    }

    //overriding the display function
    void display(){
        super.display();
        System.out.println("Weekly salary is: "+this.weekly_sal());
        this.calculateBonus();
        System.out.println("Bonus salary(per month) is: "+this.bonus);
    }
}

class SalariedEmployee extends Employee{
    double monthlySalary;
    //constructor
    SalariedEmployee(int e_id, String e_name, String desig, double m_sal){
        //using the constructor of the parent class
        super(e_id, e_name, desig);
        this.monthlySalary = m_sal;
    }

    //overriding the weekly_sal function of the base class
    double weekly_sal(){
        return monthlySalary/4;
    }

    //overriding the calculateBonus function of the base class
    void calculateBonus(){
        //using the base class function
        super.calculateBonus(this.weekly_sal());
        this.bonus = this.bonus + 2000;
    }

    //overiding the display function of the base class
    void display(){
        super.display();
        System.out.println("Weekly salary is: "+this.weekly_sal());
        this.calculateBonus();
        System.out.println("Bonus salary(per month) is: "+this.bonus);
    }
}

class ExecutiveEmployee extends SalariedEmployee{
    double bonusPercentage;

    //constructor
    ExecutiveEmployee(int e_id, String e_name, String desig, double m_sal, double bonus_p){
        //using the constructor of the base class 
        super(e_id, e_name, desig, m_sal);
        this.bonusPercentage = bonus_p;
    }

    //overriding the calculateBonus function of the base class
    void calculateBonus(){
        this.bonus = (this.monthlySalary*bonusPercentage)/100;
    }
}

//main function
public class Main{
    public static void main(String[] args){
    //instantiating the variables
    int id;
    String name;
    String designation;
    int end;
    int sal_st;
    double sal;
    Employee new_emp;
    int emp_type;
    double bp;
    int hwork;
    //variable to take user input
    Scanner sc = new Scanner(System.in);

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
        System.out.println("Enter the hourly salary: ");
        sal = sc.nextDouble();
        System.out.println("Enter the work hours: ");
        hwork = sc.nextInt();
        new_emp = new HourlyEmployee(id, name, designation, sal, hwork);
    }
    else{
        System.out.println("Is this a regular empolyee or an executive employee?");
        System.out.println("1 - Regular");
        System.out.println("2 - Executive");
        emp_type = sc.nextInt();
        if (emp_type == 1){
            System.out.println("Enter the monthly salary: ");
            sal = sc.nextDouble();
            new_emp = new SalariedEmployee(id, name, designation, sal);
        }
        else{
            System.out.println("Enter the monthly salary: ");
            sal = sc.nextDouble();
            System.out.println("Enter the bonus percentage: ");
            bp = sc.nextDouble();
            new_emp = new ExecutiveEmployee(id, name, designation, sal, bp);
        }
    }
    

    System.out.println("Do you want to edit any details? (1-Yes, 2-No)");
    end = sc.nextInt();

    //the loop will be executed till the user wants to edit employee details
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

    //displaying the employee details
    new_emp.display();
    }
}