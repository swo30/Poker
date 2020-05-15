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

	static int[] sum_array(int[] arr1, int[] arr2){
		for (int i =0; i<arr1.length;i++){
			arr1[i] += arr2[i];
		}
		return arr1;
	}

	///		105669616	35766640	10287472	5824000	1613040	3514992	224848	247540
	///		52 choose 7 (133784560)

   
   public static void main(String args[]) {
	  String[] myCards = {"d1", "d2", "d3"};
	  RoyalFlush(myCards);
	  StraightFlush(myCards);
	  FourOfKind(myCards);
	   Flush(myCards);
	  ThreeOfAKind(myCards);
	  TwoPair(myCards);

   }
   
   public static double[] RoyalFlush(String[] myCards){

	   int cardsDeployed = myCards.length;
	   System.out.println("cardsDeployed: "+cardsDeployed);
	   int count;
	   int[] cardsSuit = {0,0,0,0,0}; //13-CD,singles,pairs,3kind,4kind
	   
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
		if (cardsSuit[4] == 1) return new double[]{1,1,1};

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
		double denom = comb(52-cardsDeployed,7-cardsDeployed);
		switch(cardsDeployed) {
			case 7:
				return new double[]{0, 0, 0};

			case 6:
				if  (cardsValue[1] == cardsDeployed) return new double[]{0, 0, 0};
				if ((cardsValue[1] == cardsDeployed-2)&&(cardsValue[2] == 1)) return new double[]{0, 0, 0};
				if ((cardsValue[1] == 2)&&(cardsValue[2] == 2)) return new double[]{4.0, denom, 4.0/denom};
				if  (cardsValue[2] == 3) return new double[]{6.0,denom, 6.0/denom};
				if  (cardsValue[3] == 1) return new double[]{9.0,denom, 9.0/denom};
				if  (cardsValue[4] == 1) return new double[]{6.0,denom, 6.0/denom};
				break;

			case 5:
				if  (cardsValue[1] == cardsDeployed) return new double[]{0, 0, 0};
				if ((cardsValue[1] == cardsDeployed-2)&&(cardsValue[2] == 1)) return new double[]{27.0, denom, 27.0/denom};
				if ((cardsValue[1] == cardsDeployed-4)&&(cardsValue[2] == 2)) return new double[]{181.0, denom, 181.0/denom};
				if  (cardsValue[3] == 1) return new double[]{321.0, denom, 321.0/denom};
				if  (cardsValue[4] == 1) return new double[]{201.0, denom, 201.0/denom};
				break;

			case 4:
				if  (cardsValue[1] == cardsDeployed) return new double[]{108.0, denom, 108.0/denom};
				if ((cardsValue[1] == cardsDeployed-2)&&(cardsValue[2] == 1)) return new double[]{936.0, denom, 936.0/denom};
				if ((cardsValue[1] == cardsDeployed-4)&&(cardsValue[2] == 2)) return new double[]{4096.0, denom, 4096.0/denom};
				if  (cardsValue[3] == 1) return new double[]{5856.0, denom, 5856.0/denom};
				if  (cardsValue[4] == 1) return new double[]{3216.0, denom, 3216.0/denom};
				break;

			case 3:
				if  (cardsValue[1] == cardsDeployed) return new double[]{3186.0, denom, 3186.0/denom};
				if ((cardsValue[1] == cardsDeployed-2)&&(cardsValue[2] == 1)) return new double[]{16296.0, denom, 16296.0/denom};
				if  (cardsValue[3] == 1) return new double[]{71076.0, denom, 71076.0/denom};
				break;

			case 2:
				if  (cardsValue[1] == cardsDeployed) return new double[]{47592.0, denom, 47592.0/denom};
				if ((cardsValue[1] == cardsDeployed-2)&&(cardsValue[2] == 1)) return new double[]{184872.0, denom, 184872.0/denom};
				break;

			case 1:
				return new double[]{473172.0, denom, 473172.0/denom};

			case 0:
				return new double[]{3514992.0, denom, 3514992.0/denom};

		}
		return new double[]{69,69,69}; //This line will never happen
	}

	public static double[] Flush(String[] myCards){
		int[] cardsSuit = {0,0,0,0,0}; //null, clubs, diamonds, hearts, spade

		for (String cards : myCards){
			cardsSuit[Character.getNumericValue(cards.charAt(1))] +=1;
		}
		for (int i=0; i<5; i++){
			if (cardsSuit[i] >=5) return new double[] {1,1,1};
		}
		System.out.println("cardsSuit:" + cardsSuit[1] + cardsSuit[2] + cardsSuit[3] + cardsSuit[4]);
		double sumOfChances = 0;
		int cardsDeployed = myCards.length;

		for (int suit = 1; suit <= 4; suit++) {
			sumOfChances += comb(13-cardsSuit[suit],5-cardsSuit[suit])*comb(52-13-cardsDeployed+cardsSuit[suit],7-5-cardsDeployed+cardsSuit[suit]);
			sumOfChances += comb(13-cardsSuit[suit],6-cardsSuit[suit])*comb(52-13-cardsDeployed+cardsSuit[suit],7-6-cardsDeployed+cardsSuit[suit]);
			sumOfChances += comb(13-cardsSuit[suit],7-cardsSuit[suit])*comb(52-13-cardsDeployed+cardsSuit[suit],7-7-cardsDeployed+cardsSuit[suit]);
		}

		return new double[] {sumOfChances,comb(52-cardsDeployed,7-cardsDeployed),((sumOfChances)/comb(52-cardsDeployed,7-cardsDeployed))};
	}


	public static int[] Straight(String[] myCards, int pos, int limit, int count){
		int[] value ={0,0,0,0,0,0};
		int num =0;
		if (pos == limit){
			value[count] +=1;
			return value;
		}

		for(int type= '1'; type<'5';type++){
			num =0;
			for (String cards : myCards){
				if (cards.charAt(1) ==type && cards.charAt(0) == '0'+pos){
					num +=1;
				}
			}
			value = sum_array(value, Straight(myCards, pos+1, limit, count + num));
		}
		return value;
	}

	public static double[] Straight(String[] myCards){

		int[] cardsSuit = {0,0,0,0,0,0}; //0,1,2,3
		int cardsDeployed = myCards.length;
		int count=0;
		for (int i = 1; i<11; i++){
			cardsSuit = sum_array(cardsSuit, Straight(myCards, i, i+5,0));
		}

		System.out.println(cardsSuit[0]  + " " + cardsSuit[1]  + " " + cardsSuit[2]  + " " + cardsSuit[3] + " " + cardsSuit[4] + " " + cardsSuit[5]);

		//Statistics
		int sumOfChances =0;
		for (int i=0;i<5;i++) {
			//sumOfChances += cardsSuit[i]*comb(52-(9-i)-cardsDeployed,7-(5-i)-cardsDeployed); //Not the good way
			sumOfChances += cardsSuit[i]*comb(52-(5-i)-cardsDeployed,7-(5-i)-cardsDeployed); //The way we think maybe
		}

		System.out.println("Total Chances " + sumOfChances + " on " + comb(52-cardsDeployed,7-cardsDeployed) + " = " + (sumOfChances/comb(52-cardsDeployed,7-cardsDeployed)));
		return new double[] {sumOfChances,comb(52-cardsDeployed,7-cardsDeployed),(sumOfChances/comb(52-cardsDeployed,7-cardsDeployed))};

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

	public static double[] TwoPair(String[] myCards){
		int[] cardsValue = {0,0,0,0,0}; //Non-Values,singles,pairs,3kind,4kind
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
		if ((cardsValue[2] > 1) || (cardsValue[4] > 0) || ((cardsValue[3] > 0)&&(cardsValue[2] > 0))){
			return new double[]{1, 1, 1};
		}

		//Stats
		double denom = comb(52-cardsDeployed,7-cardsDeployed);
		switch(cardsDeployed) {
			case 7:
				return new double[]{0, 0, 0};
			case 6:
				if  (cardsValue[1] == cardsDeployed) return new double[]{0, 0, 0};
				if ((cardsValue[1] == cardsDeployed-2)&&(cardsValue[2] == 1)) return new double[]{12.0, denom, 12.0/denom};
				if  (cardsValue[3] == 1) return new double[]{10.0,denom, 10.0/denom};
				break;

			case 5:
				if  (cardsValue[1] == cardsDeployed) return new double[]{90.0, denom, 90.0/denom};
				if ((cardsValue[1] == cardsDeployed-2)&&(cardsValue[2] == 1)) return new double[]{433.0, denom, 433.0/denom};
				if  (cardsValue[3] == 1) return new double[]{355.0, denom, 355.0/denom};
				break;

			case 4:
				if  (cardsValue[1] == cardsDeployed) return new double[]{2812.0, denom, 2812.0/denom};
				if ((cardsValue[1] == cardsDeployed-2)&&(cardsValue[2] == 1)) return new double[]{8164.0, denom, 8164.0/denom};
				if  (cardsValue[3] == 1) return new double[]{6560.0, denom, 6560.0/denom};
				break;

			case 3:
				if  (cardsValue[1] == cardsDeployed) return new double[]{46489.0, denom, 46489.0/denom};
				if ((cardsValue[1] == cardsDeployed-2)&&(cardsValue[2] == 1)) return new double[]{105924.0, denom, 105924.0/denom};
				if  (cardsValue[3] == 1) return new double[]{83044.0, denom, 83044.0/denom};
				break;

			case 2:
				if  (cardsValue[1] == cardsDeployed) return new double[]{536212.0, denom, 536212.0/denom};
				if ((cardsValue[1] == cardsDeployed-2)&&(cardsValue[2] == 1)) return new double[]{1050088.0, denom, 1050088.0/denom};
				break;

			case 1:
				return new double[]{4814740.0, denom, 4814740.0/denom};

			case 0:
				return new double[]{35766640.0, denom, 35766640.0/denom};

		}
		return new double[]{69,69,69}; //This line will never happen
	}



	public static double[] Pair(String[] myCards){
		int cardsDeployed = myCards.length;
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
		double sumOfChances = comb(13-cardsDeployed,7-cardsDeployed)*Math.pow(4,7-cardsDeployed);
		return new double[] {sumOfChances,comb(52-cardsDeployed,7-cardsDeployed),1-(sumOfChances/comb(52-cardsDeployed,7-cardsDeployed))};
	}

}
