import java.util.Arrays;
import java.util.Comparator;
import java.util.*;
public class WgtIntScheduler {
   public WgtIntScheduler(){};
   public int[] getOptSet(int[] stime, int[] ftime, int[] weight) {
      ArrayList<Job> arr = new ArrayList<Job>();
      for (int i = 0; i < stime.length; i++) {
         arr.add(new Job(stime[i], ftime[i], weight[i], i+1));
      }
      // sort by finish time (fTime)
      Comparator<Job> fTimeComp = (o1, o2)->o1.fTime.compareTo(o2.fTime);
      arr.sort(fTimeComp);
      // for(Job a: arr){
      //   System.out.println("ID: " + a.id + " Start time: " + a.sTime + " Finish time: " + a.fTime + " Weight: " + a.weight);
      // }
      for (int i = 1; i < arr.size(); i++) {
         for (int j = 0; j < i; j++) {
           // if no overlap
            if (!isOverlap(arr.get(j), arr.get(i)) && (arr.get(i).weight + arr.get(j).res > arr.get(i).res)) {
               arr.get(i).res = arr.get(j).res + arr.get(i).weight;
               arr.get(i).prevIndex = j;
            }

         }
      }
      // for(Job a: arr){
      //   System.out.println("ID: " + a.id + " Start time: " + a.sTime + " Finish time: " + a.fTime + " Weight: " + a.weight + " Total: " + a.res + " Prev Index: " + a.prevIndex);
      // }
      return finish(arr);
   }
   public int[] finish(ArrayList<Job> res) {
      ArrayList<Integer> ret = new ArrayList<>();
      int i = 0;
      int curr = res.get(0).res;
      // find the one with highest total weight
      for (int j = 0; j < res.size(); j++) {
         if (res.get(j).res > curr) {
            curr = res.get(j).res;
            i = j;
         }
      } // i == index of max element. Then traceback. Back track based on prevIndex
      while (i != -1) {
         ret.add(res.get(i).id);
         i = res.get(i).prevIndex;
      }
      // sort based on ascending id
      Collections.sort(ret);
      int[] fin = new int[ret.size()];
      for (int x = 0; x < ret.size(); x++) {
         fin[x] = ret.get(x);
      }
      return fin;
   }


   public boolean isOverlap(Job j1, Job j2) {
      // does j1 overlap into j2?
      if (j1.fTime > j2.sTime) {return true;}
      return false;
   }
   private class Job {
      public Integer sTime, fTime, weight, id, res, prevIndex;
      public Job(int sTime, int fTime, int weight, int id) {
         this.sTime = sTime;
         this.fTime = fTime;
         this.weight = weight;
         this.res = weight;
         this.id = id;
         this.prevIndex = -1;
      }
   }
   public static void main(String[] args) {
     WgtIntScheduler s = new WgtIntScheduler();

  }

  /*
  A. C(current time interval, # time intervals sorted before current) = max weight that can be achieved without overlapping intervals 
  B. C(i, j) = max(C(j)) + i
  C. The table is linear and same size of intervals, each item represents the max weight that can be retrieved using the current interval.
  D. Sort the Jobs by ascending end time. For every iteration in i, loop through from 0 to i - 1 to compute the maximum weight for the ith interval.
  E. Find the max weight from the table, use that as a starting point to trace the attribute of “prevIndex” to find where the other weights belong. Sort this in ascending order of job id.
  F. O(n^2) because every interval needs to iterate through the previous end time intervals to compute the greatest weight with the current interval.

  */
}
