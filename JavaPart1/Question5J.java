public class Question5J {
    public static void main(String[] args) {
        int ar1[]={4,3,6,7,9,};
        int ar2[]={4,7,10,14,18};
        for(int i=0;i<=4;i++)
        {
            for(int j=0;j<=4;j++)
            {
                if(ar1[i]==ar2[j])
                {
                    System.out.println(ar1[i]);
                }
            }
        }


    }
}
