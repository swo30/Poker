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
	   if(r ==0) return 1;
	   if(r<0 || n <=0) return 0;
	   return factorial(n) / (factorial(r) * factorial(n-r));
   }
   
   static double perm(int n, int r){
	   if(r ==0) return 1;
	   if(r<0 || n <=0) return 0;
		return factorial(n) / factorial(n-r);
   }
   
   public static void main(String args[]) {
	  System.out.println("Royal Flush: ");
	  RoyalFlush();
	  System.out.println("6 cards ");
	  System.out.println("5 cards ");
   }
   
   public static void RoyalFlush(){
	   
	   // Parameters:
	   int cardsDeployed =2;
	   int[] cardsSuit = {0,1,0,0};
	   
	   // Code:
	   int sumOfChances =0;
	   if (7-cardsDeployed+cardsSuit[0] >=4){
		   sumOfChances += cardsSuit[0]*comb(52-cardsDeployed-4,7-4-cardsDeployed);
	   }
	   if (7-cardsDeployed+cardsSuit[1] >=3){
		   sumOfChances += cardsSuit[1]*comb(52-cardsDeployed-3,7-3-cardsDeployed);
	   }
	   if (7-cardsDeployed+cardsSuit[2] >=2){
		   sumOfChances += cardsSuit[2]*comb(52-cardsDeployed-2,7-2-cardsDeployed);
	   }
	   if (7-cardsDeployed+cardsSuit[3] >=1){
		   sumOfChances += cardsSuit[3]*comb(52-cardsDeployed-1,7-1-cardsDeployed);
	   }
	   if (7-cardsDeployed >=5){
		   int count =0;
		   for (int i =0; i<4;i++){
			   count += cardsSuit[i];
		   }
		   sumOfChances += (4-count)*comb(52-5-cardsDeployed,7-5-cardsDeployed);
	   }
	   
	   System.out.println("Total Chances " + sumOfChances + " on " + comb(52-cardsDeployed,7-cardsDeployed) + " = " + (sumOfChances/comb(52-cardsDeployed,7-cardsDeployed)));   

   }
}