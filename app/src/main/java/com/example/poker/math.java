public class math {
   static double factorial(int n) {
      double fact = 1;
      int i = 1;
      while(i <= n) {
         fact *= i;
         i++;
      }
      return fact;
   }
   
   static double comb(int n , int r){
	   return factorial(n) / (factorial(r) * factorial(n-r));
   }
   
   static double perm(int n, int r){
		return factorial(n) / factorial(n-r);
   }
   
   public static void main(String args[]) {
      /* int n = 7, r = 3, comb, per;
      per = factorial(n) / factorial(n-r);
      System.out.println("Permutation: " + per);
      comb = factorial(n) / (factorial(r) * factorial(n-r));
      System.out.println("Combination: " + comb); */
	  
	  System.out.println("Royal Flush: ");
	  RoyalFlush();
	  System.out.println("6 cards ");
	  System.out.println("5 cards ");
   }
   
   public static void RoyalFlush(){
	   double comb;
	   comb = comb(52,5);
	   System.out.println((4 / comb(52,5)));
	   System.out.println((4*comb(52,2) / comb(52,7)));
	   System.out.println((4*comb(52,1) / comb(52,7)));
	   System.out.println((4*comb(52,1) / comb(52,6)));
	   
   }
}