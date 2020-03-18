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
	   String[] myCards = {"11","22", "A1", "B1", "C1", "D1"};
	   
	   int cardsDeployed = myCards.length;
	   int count;
	   int[] cardsSuit = {0,0,0,0};
	   
	   // Parse card hand
		for (int type =48; type<52;type++){
			count =-1;
			for (String cards : myCards){
				if (cards.charAt(1) == type && (cards.charAt(0) == 'A' || cards.charAt(0) == 'B' || cards.charAt(0) == 'C' || cards.charAt(0) == 'D' || cards.charAt(0) == 'E'))
					count+=1;
			}
			if (count != -1)
				cardsSuit[count] +=1;
		}
		System.out.println(cardsSuit[0]  + " " + cardsSuit[1]  + " " + cardsSuit[2]  + " " + cardsSuit[3]);
		
	   // Calculate Statistics:
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
		   count =0;
		   for (int i =0; i<4;i++){
			   count += cardsSuit[i];
		   }
		   sumOfChances += (4-count)*comb(52-5-cardsDeployed,7-5-cardsDeployed);
	   }
	   
	   System.out.println("Total Chances " + sumOfChances + " on " + comb(52-cardsDeployed,7-cardsDeployed) + " = " + (sumOfChances/comb(52-cardsDeployed,7-cardsDeployed)));   

   }
}