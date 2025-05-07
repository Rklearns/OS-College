import java.util.*;
public class pages {
    public static void main(String args[])
    {
        int[] pages = {7,0,1,2,0,3,0,4,2,3};
        int capacity = 3;
        Optimal(pages,capacity);
        LRU(pages, capacity);
        LFU(pages,capacity);

    }

    //here we dont use hashmap just check from next page onwards which is farthest 
    public static void Optimal(int pages[], int capacity)
    {
        //this basically sees after the current page agar fault ho toh uske aage present frames mei kaun last aata
        HashSet<Integer> set =new HashSet<>();//these are basically frames
        int fault=0;
        for(int i=0;i<pages.length;i++)
        {
            int page=pages[i];
            if(!set.contains(page))
            {
               if(set.size()==capacity)
               {
                 int farthest=-1; int removed=-1;
                 for(int frame: set)
                 {
                     int j;
                     for( j=i+1;j<pages.length;j++)
                     {
                        if(frame==pages[j])
                        {
                            break;
                        }
                     }
                     if(j>farthest)
                     {
                        farthest=j;
                        removed=frame;
                     }
                 }
                 set.remove(removed);
                 
               }
               set.add(page);
               fault++;
            }

        }
        System.out.println("total faults is "+fault);
	     System.out.println("total hits is "+(pages.length -fault));
		 System.out.println("hit ratio is"+((float)(pages.length -fault/pages.length)));


    }
    public static void LRU(int[] pages, int capacity) {
        Set<Integer> frames = new HashSet<>();
        Map<Integer, Integer> recent = new HashMap<>();
        int faults = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];

            if (!frames.contains(page)) {
                if (frames.size() == capacity) {
                    int lru = Integer.MAX_VALUE, valueToRemove = -1;
                    for (int frame : frames) {
                        int lastUsed = recent.getOrDefault(frame, -1);
                        if (lastUsed < lru) {
                            lru = lastUsed;
                            valueToRemove = frame;
                        }
                    }
                    frames.remove(valueToRemove);
                }
                frames.add(page);
                faults++;
            }
            recent.put(page, i);
        }

        System.out.println("Total faults: " + faults);
        System.out.println("Total hits: " + (pages.length - faults));
        System.out.println("Hit ratio: " + ((float)(pages.length - faults) / pages.length));
}
public static void LFU(int[] pages, int capacity) {
    Set<Integer> frames = new HashSet<>();
    Map<Integer, Integer> freq = new HashMap<>();
    Map<Integer, Integer> time = new HashMap<>();
    int faults = 0;

    for (int i = 0; i < pages.length; i++) {
        int page = pages[i];

        if (frames.contains(page)) {
            freq.put(page, freq.get(page) + 1);
        } else {
            faults++;
            if (frames.size() == capacity) {
                int lfu = Integer.MAX_VALUE;
                int oldest = Integer.MAX_VALUE;
                int pageToRemove = -1;

                for (int frame : frames) {
                    int f = freq.get(frame);
                    int t = time.get(frame);
                    if (f < lfu || (f == lfu && t < oldest)) {
                        lfu = f;
                        oldest = t;
                        pageToRemove = frame;
                    }
                }

                frames.remove(pageToRemove);
                freq.remove(pageToRemove);
                time.remove(pageToRemove);
            }
            frames.add(page);
            freq.put(page, 1);
            time.put(page, i); // for tie-breaking: least recently used
        }
    }

    System.out.println("Total faults: " + faults);
    System.out.println("Total hits: " + (pages.length - faults));
    System.out.println("Hit ratio: " + ((float)(pages.length - faults) / pages.length));
}
}