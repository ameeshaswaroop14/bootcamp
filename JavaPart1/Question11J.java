
    abstract class Bank
    {
        String name="";
        String type="";
        float roi;
        int branches;
        Bank()
        {

        }
        Bank(String name,float r,int b)
        {
            this.name=name;
            this.roi=r;
            this.branches=b;
        }
        public void getDetails()
        {
            System.out.println("Name: "+name);
            System.out.println("Type: "+type);
            System.out.println("Rate: "+roi);
        }

    }
    class SBI extends Bank
    {
        SBI()
        {
            name="State Bank of India";
            type="govt.";
            roi=4.5f;
        }
    }
    class BOI extends Bank
    {
        String slogan;
        BOI()
        {
            name="Bank Of India";
            type="govt.";
            roi=4.4f;
            slogan="relationships beyond banking";
        }
        public void getDetails()
        {
            super.getDetails();
            System.out.println("Slogan: "+slogan);
        }
    }
    class ICICI extends Bank
    {
        String facility;
        ICICI()
        {
            name="ICICI";
            type="private";
            roi=4.6f;
            facility="corporate banking";

        }

        public void getDetails()
        {
            super.getDetails();
            System.out.println("Facility: "+facility);
        }
    }
    class BankDetails
    {
        public static void main(String[] args)
        {
            SBI sbi=new SBI();
            BOI boi=new BOI();
            ICICI i=new ICICI();
            sbi.getDetails();
            boi.getDetails();
            i.getDetails();
        }
    }


