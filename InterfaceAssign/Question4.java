interface EmployeeData
{
    Question4 data(String name,int age,String city);
}
public class Question4{
    String name;
    int age;
    String city;

    public Question4( String name,int age,String city)
    {
        this.name=name;
        this.age=age;
        this.city=city;

    }
    public String toString()
    {
        return "name "+name+" age "+age+" city "+city;
    }
    public static void main(String[] args)
    {
        EmployeeData ob = Question4::new;
        Question4 emp=ob.data("Ameesha",21,"Allahabad");
        System.out.println(emp.toString());
    }

}