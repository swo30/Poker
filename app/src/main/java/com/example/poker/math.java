package com.example.poker;

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
	  String[] myCards = {"d1", "d2", "d3"};
	  System.out.println("Royal Flush: ");
	  RoyalFlush(myCards);
	  System.out.println("Straight Flush: ");
	  StraightFlush(myCards);
	  System.out.println("Four of a Kind: ");
	  FourOfKind(myCards);
		ThreeOfAKind(myCards);

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
	   
	   int[] cardsSuit = {0,0,0,0,0}; //0,1,2,3,4
	   int cardsDeployed = myCards.length;
	   int count=0;
	   int[] map = {0,0,0,0,0,0,0,0,0,0,0,0,0};
	   
	   for (String card: myCards){
			if (card.charAt(0) < 58)
				map[card.charAt(0)-49] +=1;
			else
				map[card.charAt(0)-98+10] +=1;
	   }
	   for(int i =0; i<13;i++){
		   cardsSuit[map[i]]+=1;
	   }
	   
	   	if(cardsSuit[4] >0)
			return new double[] {1,1,1};
		
	   System.out.println(cardsSuit[0]  + " " + cardsSuit[1]  + " " + cardsSuit[2]  + " " + cardsSuit[3]);

	   int sumOfChances =0;
	   for (int i=0;i<4;i++) {
		   sumOfChances += (cardsSuit[i])*comb(52-(4-i)-cardsDeployed,7-(4-i)-cardsDeployed);
	   }
	   
	   System.out.println("Total Chances " + sumOfChances + " on " + comb(52-cardsDeployed,7-cardsDeployed) + " = " + (sumOfChances/comb(52-cardsDeployed,7-cardsDeployed)));   
	   return new double[] {sumOfChances,comb(52-cardsDeployed,7-cardsDeployed),(sumOfChances/comb(52-cardsDeployed,7-cardsDeployed))};
   }

	public static double[] FullHouse(String[] myCards){
		int[] cardsValue = {0,0,0,0,0}; //13-CD,singles,pairs,3kind,4kind
		int cardsDeployed = myCards.length;
		int[] map = {0,0,0,0,0,0,0,0,0,0,0,0,0};

		for (String card: myCards){
			if (card.charAt(0) < 58)
				map[card.charAt(0)-49] +=1;
			else
				map[card.charAt(0)-98+10] +=1;
		}
		for(int i =0; i<13;i++){
			cardsValue[map[i]]+=1;
		}
		if (((cardsValue[3] > 0) && (cardsValue[2] >0)) || ((cardsValue[4] > 0) && (cardsValue[2] >0))) {
			return new double[]{1, 1, 1};
		}

		//Stats
		int sumOfChances = 0;
		for (int i=0;i<3;i++) {
			sumOfChances += (cardsValue[i])*comb((4-i),(3-i))*comb(cardsValue[0],7-(cardsDeployed+3-i))*Math.pow(4,(7-(cardsDeployed+3-i)));
		}
		System.out.println("sumOfChances: " + sumOfChances);
		return new double[] {sumOfChances,comb(52-cardsDeployed,7-cardsDeployed),((sumOfChances)/comb(52-cardsDeployed,7-cardsDeployed))};
	}

	public static double[] ThreeOfAKind(String[] myCards){

		int[] cardsValue = {0,0,0,0,0}; //13-CD,singles,pairs,3kind,4kind
		int cardsDeployed = myCards.length;
		int[] map = {0,0,0,0,0,0,0,0,0,0,0,0,0};

		for (String card: myCards){
			if (card.charAt(0) < 58)
				map[card.charAt(0)-49] +=1;
			else
				map[card.charAt(0)-98+10] +=1;
		}
		for(int i =0; i<13;i++){
			cardsValue[map[i]]+=1;
		}
		if((cardsValue[3] > 0) || (cardsValue[4] >0)) {
			return new double[]{1, 1, 1};
		}

		int sumOfChances = 0;
		for (int i=0;i<3;i++) {
			sumOfChances += (cardsValue[i])*comb((4-i),(3-i))*comb(cardsValue[0],7-(cardsDeployed+3-i))*Math.pow(4,(7-(cardsDeployed+3-i)));
		}
		System.out.println("sumOfChances: " + sumOfChances);
		return new double[] {sumOfChances,comb(52-cardsDeployed,7-cardsDeployed),((sumOfChances)/comb(52-cardsDeployed,7-cardsDeployed))};
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
