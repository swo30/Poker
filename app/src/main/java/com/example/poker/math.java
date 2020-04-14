//package com.example.poker;

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
		String[] myCards = {};
		System.out.println("Royal Flush: ");
		RoyalFlush(myCards);
		System.out.println("Straight Flush: ");
		StraightFlush(myCards);
		System.out.println("Four of a Kind: ");
		FourOfKind(myCards);
		ThreeOfAKind(myCards);
	   System.out.println(comb(13,5));

	  //System.out.println("6 cards ");
	  //System.out.println("5 cards ");
   }
   
   public static double[] RoyalFlush(String[] myCards){

	   int cardsDeployed = myCards.length;
	   System.out.println("cardsDeployed: "+cardsDeployed);
	   int count;
	   int[] cardsSuit = {0,0,0,0,0};
	   
	   // Parse card hand
		for (int type = '1'; type<'5';type++){
			count =-1;
			for (String cards : myCards){
				if (cards.charAt(1) == type && (cards.charAt(0) == 'a' || cards.charAt(0) == 'b' || cards.charAt(0) == 'c' || cards.charAt(0) == 'd' || cards.charAt(0) == '1'))
					count+=1;
			}
			if (count != -1)
				cardsSuit[count] +=1;
		}
		System.out.println(cardsSuit[0]  + " " + cardsSuit[1]  + " " + cardsSuit[2]  + " " + cardsSuit[3]);
		
	   // Calculate Statistics:
	   int sumOfChances =0;
	   for (int i=0;i<4;i++) {
		   sumOfChances += cardsSuit[i]*comb(52-(4-i)-cardsDeployed,7-(4-i)-cardsDeployed);
	   }

	   if (7-cardsDeployed >=5){
		   count =0;
		   for (int i =0; i<4;i++){
			   count += cardsSuit[i];
		   }
		   sumOfChances += (4-count)*comb(52-5-cardsDeployed,7-5-cardsDeployed);
	   }
	   
	   System.out.println("Total Chances " + sumOfChances + " on " + comb(52-cardsDeployed,7-cardsDeployed) + " = " + (sumOfChances/comb(52-cardsDeployed,7-cardsDeployed)));
		return new double[] {sumOfChances,comb(52-cardsDeployed,7-cardsDeployed),(sumOfChances/comb(52-cardsDeployed,7-cardsDeployed))};

   }
   
   public static double[] StraightFlush(String[] myCards){

	   int[] cardsSuit = {0,0,0,0,0}; //0,1,2,3
	   int cardsDeployed = myCards.length;
	   int count=0;
	   for (int type = '1'; type <'5'; type++){
		   for (int i = 1; i<10; i++){
			   count =0;
			   for (int e = i; e<i+5; e++){
				   for (String cards : myCards){
					   if (cards.charAt(1) ==type && cards.charAt(0) == '1'+e){
						   count +=1;
					   }
				   }
			   }
			   cardsSuit[count]+=1;
		   }
	   }
	   
	   System.out.println(cardsSuit[0]  + " " + cardsSuit[1]  + " " + cardsSuit[2]  + " " + cardsSuit[3]);
	   
	   //Statistics
	   int sumOfChances =0;
	   for (int i=0;i<5;i++) {
		   sumOfChances += cardsSuit[i]*comb(52-(6-i)-cardsDeployed,7-(5-i)-cardsDeployed);
	   }
	   
		System.out.println("Total Chances " + sumOfChances + " on " + comb(52-cardsDeployed,7-cardsDeployed) + " = " + (sumOfChances/comb(52-cardsDeployed,7-cardsDeployed)));
		return new double[] {sumOfChances,comb(52-cardsDeployed,7-cardsDeployed),(sumOfChances/comb(52-cardsDeployed,7-cardsDeployed))};
   }
   
   public static double[] FourOfKind(String[] myCards){
	   
	   int[] cardsSuit = {13,0,0,0,0}; //0,1,2,3,4
	   int cardsDeployed = myCards.length;

	   //whats up brother
	   String DEEZNUTS = "GOT HIM";
	   int count=0;
	   
	   for (String cards: myCards){
		   count =0;
		   for (int i=0; i<cardsDeployed; i++){
			   if (cards.charAt(0) == myCards[i].charAt(0)){
				   count+=1;
			   }
		   }
		   cardsSuit[count]+=1;
		   cardsSuit[0]-=1;
	   }

	   System.out.println(cardsSuit[0]  + " " + cardsSuit[1]  + " " + cardsSuit[2]  + " " + cardsSuit[3]);

	   int sumOfChances =0;
	   for (int i=0;i<4;i++) {
		   sumOfChances += cardsSuit[i]*comb(52-(4-i)-cardsDeployed,7-(4-i)-cardsDeployed);
	   }
	   
	   System.out.println("Total Chances " + sumOfChances + " on " + comb(52-cardsDeployed,7-cardsDeployed) + " = " + (sumOfChances/comb(52-cardsDeployed,7-cardsDeployed)));   
	   return new double[] {sumOfChances,comb(52-cardsDeployed,7-cardsDeployed),(sumOfChances/comb(52-cardsDeployed,7-cardsDeployed))};
   }

	public static double[] FullHouse(String[] myCards){
		double[] arr={5,6,7,8,9};
		return arr;
	}

	public static double[] ThreeOfAKind(String[] myCards){
		int cardsDeployed = myCards.length;
		int[] cardPair = {0,0,0,0};
		int cnt;
		for (int i=0; i<cardsDeployed; i++){
			cnt = 0;
			for (int j=i+1; j<cardsDeployed; j++){
				if (myCards[i].charAt(0) == myCards[j].charAt(0)){
					cnt +=1;
				}
			}
			cardPair[cnt] += 1;
		}
		System.out.println("cardPair: " + cardPair[0] + " " + cardPair[1] + " " + cardPair[2] + " " + cardPair[3]);
		if (cardPair[2] >= 1){
			return new double []{1,1,1}; //you have a three of a kind -> 100%
		}
		return new double []{0,0,0};
	}

	public static double[] Pair(String[] myCards){
		int cardsDeployed = myCards.length;
		double riverPair  = comb(13-cardsDeployed,1)*comb(4,2)*comb(13-(cardsDeployed+1),7-(cardsDeployed+2))*Math.pow(4,(7-cardsDeployed-2));
		double handPair = cardsDeployed*comb(3,1)*comb(13-cardsDeployed,(7-(cardsDeployed+1)))*Math.pow(4,(7-(cardsDeployed+1)));
		boolean isPair = false;

		 for (int i=0; i<cardsDeployed; i++){
			for (int j=i+1; j<cardsDeployed; j++){
				if (myCards[i].charAt(0) == myCards[j].charAt(0)){
					isPair = true;
				}
			}
		}
		 if (isPair) {
		 	return new double []{1,1,1};
		 }

		System.out.println("River Pair: " + riverPair);
		System.out.println("Hand Pair: " + handPair);
		System.out.println("cardsDeployed: " + cardsDeployed);
		return new double[] {handPair+riverPair,comb(52-cardsDeployed,7-cardsDeployed),((riverPair+handPair)/comb(52-cardsDeployed,7-cardsDeployed))};
	}

}
