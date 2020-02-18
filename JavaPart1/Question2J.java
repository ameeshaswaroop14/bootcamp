
    import java.util.Scanner;

    public class Question2J {
        public static void main(String args[])
        {
            int count=0;
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter String");
            String S=sc.nextLine();
            int len=S.length();
            String words[]=S.split(" ");
            for(int x=0;x<words.length;x++)
            {
                count=1;
                for( int y=x+1;y<words.length;y++)
                {

                    if(words[x].equalsIgnoreCase(words[y]))
                    {
                        count++;
                    }
                    if(count>1)
                {
                    System.out.println(words[x]+" "+count);
                }

                }
            }





            }
}
