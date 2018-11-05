
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
public class RodCutter {
   public int tests;
   public ArrayList<int[]> inputs;

   public RodCutter() throws FileNotFoundException {
      FileInputStream in = new FileInputStream(new File("rodOptTest.txt"));
      Scanner sc = new Scanner(in);
      this.tests = sc.nextInt();
      this.inputs = new ArrayList<int[]>();
      for(int i = 0; i < this.tests; i++) {
         int k = sc.nextInt();
         int[] l = new int[k+1];
         for (int j = 0; j < k; j++) {
            l[j+1] = sc.nextInt();
         }
         this.inputs.add(l);
      }
   }

   public static void cutRod(int price[], int length) {
      int[] res = new int[price.length];
      res[0] = 0;
      int[] cuts = new int[price.length];
      for (int j = 1; j < res.length; j++) {
         //iterate through each length/price
         int max_res = Integer.MIN_VALUE;
         for (int i = 1; i <= j; i++) {
            //while j < i, get the maximum price with the piece of j and its compliment
            if (max_res < price[i] + res[j-i]) {
               max_res = price[i] + res[j-i];
               cuts[j] = i;
            }
         }
         res[j] = max_res;
      }
      for (int x = 1; x < res.length; x++)
         System.out.printf("total for length %d \t = %d\n", x, res[x]);

      traceBack(cuts);
   }

   public static void traceBack(int[] cuts) {
      int i = cuts.length-1;
      HashMap<Integer, Integer> map = new HashMap<>();
      Set<Integer> keys;
      int n = 1;
      int prev = 0;
      while (i > 0) {
         if (map.containsKey(cuts[i])) {
            map.put(cuts[i], map.get(cuts[i]) +1);
         } else {
            map.put(cuts[i], 1);
         }
         i -= cuts[i];
      }
      keys = map.keySet();
      Integer[] array = keys.toArray(new Integer[keys.size()]);
      Arrays.sort(array);
      System.out.println("Optimal rod cutting");
      for (int j : array) {
         System.out.printf("Number of rods of length %d \t = %d\n", j, map.get(j));
      }

   }

   public static void main(String[] args) throws FileNotFoundException{
      RodCutter r = new RodCutter();
      int i = 1;
      for (int[] l : r.inputs) {
         System.out.printf("\nCase %d:\n", i++);
         r.cutRod(l, l.length);
      }
   }
}
