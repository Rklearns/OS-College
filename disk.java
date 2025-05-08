import java.util.*;

public class disk{

    public static void main(String[] args) {
        int[] requests = {82,170,43,140,24,16,190};
        int head = 50;
        int diskSize = 200;

        System.out.println("FCFS: " + fcfs(requests, head));
        System.out.println("SSTF: " + sstf(requests, head));
        System.out.println("SCAN: " + scan(requests, head, diskSize, "right"));
        System.out.println("C-SCAN: " + cscan(requests, head, diskSize, "right"));
        System.out.println("LOOK: " + look(requests, head, "right"));
        //System.out.println("C-LOOK: " + clook(requests, head));
    }

   public static int fcfs(int[] requests, int head) {
        int total=0;
        for(int i=0;i<requests.length;i++)
        {
            total+=Math.abs(requests[i]-head);
            head=requests[i];        }
        return total;
    }

    static int sstf(int[] requests, int head) {
        int total=0;
        int count=0;//it keeps count on if every index of requests is vivisted or not
        boolean visited[]=new boolean[requests.length];
        while(count<requests.length)
        {
            int mini=Integer.MAX_VALUE;
            int index=-1;
            for(int i=0;i<requests.length;i++)
            {
                if(!visited[i])//if it is not visited we will take the minimum possible distance
                {
                    int dist=Math.abs(head-requests[i]);
                    if(dist<mini)
                    {
                        mini=dist;
                        index=i;
                    }
                }
            }
            total+=Math.abs(requests[index]-head);
            head=requests[index];
            visited[index]=true;
            count++;
        }
        return total;
    }

    static int scan(int[] requests, int head, int diskSize, String direction) {
      //if direction is left then it goes til 0 then comes back to extreme possible right end in requeests
      ArrayList<Integer> left=new ArrayList<>();
      ArrayList<Integer> right=new ArrayList<>();
      int total=0;
      for(int val:requests)
      {
        if(val>head)
        {
            right.add(val);
        }
        else{
            left.add(val);
        }
    }
        Collections.sort(left);
        Collections.sort(right);

        if(direction=="left")
        {
            Collections.reverse(left);
            for(int i:left)
            {
                total+=Math.abs(head-i);
                head=i;
            }
            //after this we have to fo till 0 therefore we will also add
            total+=(head-0);
            head=0;
            for(int j:right)
            {
                total+=Math.abs(head-j);
                head=j;
            }

        }
        else{
            for(int j:right)
            {
                total+=Math.abs(head-j);
                head=j;
            }
            //after this we have to fo till 0 therefore we will also add
            total+=Math.abs(head-(diskSize-1));
            head=diskSize-1;
            Collections.reverse(left);
            for(int i:left)
            {
                total+=Math.abs(head-i);
                head=i;
            }
            
            
        }
        return total;
      }
       
     static int cscan(int requests[],int head, int diskSize, String direction)
     {
        ArrayList<Integer> left=new ArrayList<>();
        ArrayList<Integer> right=new ArrayList<>();
        int total=0;
        for(int val:requests)
        {
          if(val>head)
          {
              right.add(val);
          }
          else{
              left.add(val);
          }
      }
          Collections.sort(left);
          Collections.sort(right);
  
          if(direction=="left")
          {
              Collections.reverse(left);
              for(int i:left)
              {
                  total+=Math.abs(head-i);
                  head=i;
              }
              //after this we have to fo till 0 therefore we will also add
              total+=(head-0);
              head=0;
              total+=((diskSize-1)-head);
              head=diskSize-1;

              Collections.reverse(right);
              for(int j:right)
              {
                  total+=Math.abs(head-j);
                  head=j;
              }
  
          }
          else{
              for(int j:right)
              {
                  total+=Math.abs(head-j);
                  head=j;
              }
              //after this we have to fo till 0 therefore we will also add
              total+=Math.abs(head-(diskSize-1));
              head=diskSize-1;
              total+=((diskSize-1)-0);//it goes to extreme left end
              head=0;

              for(int i:left)
              {
                  total+=Math.abs(head-i);
                  head=i;
              }
              
              
          }
          return total;
     }

     static int look(int requests[], int head, String direction)
     {
         //if direction is left then it goes til 0 then comes back to extreme possible right end in requeests
      ArrayList<Integer> left=new ArrayList<>();
      ArrayList<Integer> right=new ArrayList<>();
      int total=0;
      for(int val:requests)
      {
        if(val>head)
        {
            right.add(val);
        }
        else{
            left.add(val);
        }
    }
        Collections.sort(left);
        Collections.sort(right);

        if(direction=="left")
        {
            Collections.reverse(left);
            for(int i:left)
            {
                total+=Math.abs(head-i);
                head=i;
            }

            for(int j:right)
            {
                total+=Math.abs(head-j);
                head=j;
            }

        }
        else{
            for(int j:right)
            {
                total+=Math.abs(head-j);
                head=j;
            }
            Collections.reverse(left);
            for(int i:left)
            {
                total+=Math.abs(head-i);
                head=i;
            }
            
            
        }
        return total;
     }
    
}
