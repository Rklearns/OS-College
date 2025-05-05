import java.util.*;
public class FIFO{
	public static void main(String args[])
	{
	  int pages[]={1,3,0,3,5,1,6};
	  int capacity=3;
	  Queue<Integer> q=new LinkedList<>();
	  HashSet<Integer> frame=new HashSet<>();

	  int fault=0;
	  for(int page:pages){
		if(frame.contains(page))
		{
	          continue; //this means that it is a hit 
		}
		else{
		 if(frame.size()==capacity)
		{
		   int removed=q.poll();	
		   frame.remove(removed);
		}
		 frame.add(page);q.add(page);fault++;}}

	     System.out.println("total faults is "+fault);
	     System.out.println("total hits is "+(pages.length -fault));

	}
}