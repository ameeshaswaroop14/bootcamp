public class Question6J2 {
    public static void main(String[] args) {
        int ar[]={2,2,4,4,6,3,3};
        int count;
        for(int i=0;i<ar.length;i++)
        {
            count=0;

            for(int j=0;j<ar.length;j++)
            {
                if(ar[i]==ar[j])
                {
                    count++;
                    //ar[j]=-1;
                }
            }
            if (count==1)
            {
                System.out.println(ar[i]);
                break;
            }
        }
    }
}
