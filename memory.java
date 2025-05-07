import java.util.*;
public class memory
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
       
        //we will take 5 memory blocks and 5 process 
        int b[]=new int[5];
        System.out.println("Enter sizes of memory blocks:");
        for (int i = 0; i < 5; i++) {
            b[i] = sc.nextInt();
        }

        int[] p = new int[5];
        System.out.println("Enter sizes of processes:");
        for (int i = 0; i < 5; i++) {
            p[i] = sc.nextInt();
        }
       
        bestFit(b,p);
        
        sc.close();
    
    }
    public static void firstFit(int b[], int p[])
    {
        int block[]=b.clone();//this clones the b that is block sizes
        boolean flag=false;
        for(int i=0;i<5;i++)
        {
            flag=false;
            for(int j=0;j<5;j++)
            {
                if(p[i]<=block[j])
                {
                    flag=true;
                    block[j]=block[j]-p[i];
                    System.out.println((i+1)+" process is allocated to a block "+(j+1));
                    break;
                }
            }
            if(!flag)
            {
                System.out.println("process "+(i+1)+" was not allocated to any blocks");
            }
        }
        showholes(block);
    }
    public static void bestFit(int b[], int p[])
    {
        int block[]=b.clone();
        for(int i=0;i<5;i++)
        {
            boolean flag=false;
            int bestidx=-1;
            for(int j=0;j<5;j++)
            {
                if(block[j]>=p[i])
                {
                    if(bestidx==-1 || block[bestidx]>block[j])
                    {
                        bestidx=j;
                        flag=true;
                    }
                }
            }
            if(flag)
            {
                block[bestidx]-=p[i];
                System.out.println((i+1)+" block was filled with process "+p[i]);
            }
            else{
                System.out.println((i+1)+" block was not allocated ");
            }
        }
        showholes(block);

    }
    public static void worstFit(int b[], int p[])
    {
        int block[]=b.clone();
        for(int i=0;i<5;i++)
        {
            boolean flag=false;
            int bestidx=-1;
            for(int j=0;j<5;j++)
            {
                if(block[j]>=p[i])
                {
                    if(bestidx==-1 || block[bestidx]<block[j])
                    {
                        bestidx=j;
                        flag=true;
                    }
                }
            }
            if(flag)
            {
                block[bestidx]-=p[i];
                System.out.println((i+1)+" block was filled with process "+p[i]);
            }
            else{
                System.out.println((i+1)+" block was not allocated ");
            }
        }
        showholes(block);

    }
    public static void showholes(int block[])
    {
        for(int i=0;i<block.length;i++)
        {
            System.out.println("hole for block number "+(i+1)+" is "+block[i]);
        }
    }
   
}