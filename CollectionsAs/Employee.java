import java.util.*;
class Emp{
    String name;
    int age;
    int salary;
    Emp(String name,int i,int j)
    {
        this.name=name;
        age=i;
        salary=j;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
class SalaryComparator implements  Comparator
{

    @Override
    public int compare(Object o, Object t1) {
        Emp e=(Emp)o;
        Emp b=(Emp)t1;
        return b.salary-e.salary;
    }
}
class Employee {
    public static void main(String args[]) {

        ArrayList al = new ArrayList();
        al.add(new Emp("ram ", 24, 12000));
        al.add(new Emp("ajay", 43, 60000));
        al.add(new Emp("Jai", 53, 80000));

        System.out.println("Sorting by Salary");

        Collections.sort(al, new SalaryComparator());
        Iterator itr2 = al.iterator();
        while (itr2.hasNext()) {
            Emp em = (Emp) itr2.next();
            System.out.println(em.age + " " + em.name + " " + em.salary);
        }


    }
}