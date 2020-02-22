import java.util.*;
class Student{
    String name;
    int score;
    int age;
   Student(String name,int score1,int age1)
    {
        this.name=name;
        score=score1;
        age=age1;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score=score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }
}
class ScoreComparator implements  Comparator<Student> {

    @Override
    public int compare(Student o, Student t1) {
        Student e = (Student) o;
        Student b = (Student) t1;
        if(e.score > b.score)
            return 1;
        else if(e.score < b.score)
            return -1;
        else
            return new NameComparator().compare(o,t1);
    }
}

class NameComparator implements Comparator<Student>
{
    @Override
    public int compare(Student o, Student t1)
    {
        return o.name.compareTo(t1.name);
    }
}

class Students {
    public static void main(String args[]) {

        ArrayList al = new ArrayList();
        al.add(new Student("ram ", 43, 12));
        al.add(new Student("ajay", 43, 60));
        al.add(new Student("Jai", 53, 80));

        System.out.println("Sorting by Score");

        Collections.sort(al, new ScoreComparator());
        Iterator itr2 = al.iterator();
        while (itr2.hasNext()) {
            Student stu = (Student) itr2.next();

            System.out.println(stu.name + " " + stu.score + " " + stu.age);
        }


    }}
