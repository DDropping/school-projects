package PJ3;
import java.util.*;

/*
 * Ref: http://en.wikipedia.org/wiki/Video_poker
 *      http://www.freeslots.com/poker.htm
 *
 *
 * Short Description and Poker rules:
 *
 * Video poker is also known as draw poker. 
 * The dealer uses a 52-card deck, which is played fresh after each playerHand. 
 * The player is dealt one five-card poker playerHand. 
 * After the first draw, which is automatic, you may hold any of the cards and draw 
 * again to replace the cards that you haven't chosen to hold. 
 * Your cards are compared to a table of winning combinations. 
 * The object is to get the best possible combination so that you earn the highest 
 * payout on the bet you placed. 
 *
 * Winning Combinations
 *  
 * 1. Jacks or Better: a pair pays out only if the cards in the pair are Jacks, 
 * 	Queens, Kings, or Aces. Lower pairs do not pay out. 
 * 2. Two Pair: two sets of pairs of the same card denomination. 
 * 3. Three of a Kind: three cards of the same denomination. 
 * 4. Straight: five consecutive denomination cards of different suit. 
 * 5. Flush: five non-consecutive denomination cards of the same suit. 
 * 6. Full House: a set of three cards of the same denomination plus 
 * 	a set of two cards of the same denomination. 
 * 7. Four of a kind: four cards of the same denomination. 
 * 8. Straight Flush: five consecutive denomination cards of the same suit. 
 * 9. Royal Flush: five consecutive denomination cards of the same suit, 
 * 	starting from 10 and ending with an ace
 *
 */


/* This is the video poker game class.
 * It uses Decks and Card objects to implement video poker game.
 * Please do not modify any data fields or defined methods
 * You may add new data fields and methods
 * Note: You must implement defined methods
 */



public class VideoPoker {

    // default constant values
    private static final int startingBalance=100;
    private static final int numberOfCards=5;

    // default constant payout value and playerHand types
    private static final int[] multipliers={1,2,3,5,6,9,25,50,250};
    private static final String[] goodHandTypes={ 
	  "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	  "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

    // must use only one deck
    private final Decks oneDeck;

    // holding current poker 5-card hand, balance, bet    
    private List<Card> playerHand;
    private int playerBalance;
    private int playerBet;

    /** default constructor, set balance = startingBalance */
    public VideoPoker()
    {
	this(startingBalance);
    }

    /** constructor, set given balance */
    public VideoPoker(int balance)
    {
	this.playerBalance= balance;
        oneDeck = new Decks(1);
    }

    /** This display the payout table based on multipliers and goodHandTypes arrays */
    private void showPayoutTable()
    { 
	System.out.println("\n\n");
	System.out.println("Payout Table   	      Multiplier   ");
	System.out.println("=======================================");
	int size = multipliers.length;
	for (int i=size-1; i >= 0; i--) {
		System.out.println(goodHandTypes[i]+"\t|\t"+multipliers[i]);
	}
	System.out.println("\n\n");
    }

    /** Check current playerHand using multipliers and goodHandTypes arrays
     *  Must print yourHandType (default is "Sorry, you lost") at the end of function.
     *  This can be checked by testCheckHands() and main() method.
     */
    private void checkHands()
    {
        int handType;
        
        ArrayList<Card> copy = new ArrayList<Card>(playerHand);
        Collections.sort(playerHand, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2){
                return o1.getRank() - o2.getRank();
            }
            
        });
        
           int countCards[] = new int[14];
   for(Card c: playerHand)
       countCards[c.getRank()] ++;
  
   int numPairs = 0;
   int numThreeOfKind = 0;
   int numFourOfKind = 0 ;
   for(int i = 1; i <= 13; i++)
   {
       switch(countCards[i])
       {
       case 2:
           numPairs ++;
           break;
       case 3:
           numThreeOfKind++;
           break;
       case 4:
           numFourOfKind++;
           break;
       }
   }
   
   
   //////////////////////////////
   
   
  ///////////////////////////////////
  
  
   handType = -1;
   if(checkRoyalFlush()){
       handType = 8;
       playerBalance += multipliers[handType]*playerBet;
       System.out.println("Balance: $"+playerBalance);
   }   
   else if(checkStraight() && checkFlush())
   {
       handType = 7; //straight flush
       playerBalance += multipliers[handType]*playerBet;
       System.out.println("Balance: $"+playerBalance);
   }
   else if(numFourOfKind == 1){
       handType = 6; //four of a kind
       playerBalance += multipliers[handType]*playerBet;
       System.out.println("Balance: $"+playerBalance);
   }
   else if(numThreeOfKind == 1 && numPairs == 1){
       handType = 5; //full house
       playerBalance += multipliers[handType]*playerBet;
       System.out.println("Balance: $"+playerBalance);
   }
   else if(checkFlush()){
       handType = 4; //flush
       playerBalance += multipliers[handType]*playerBet;
       System.out.println("Balance: $"+playerBalance);
   }
   else if(checkStraight())
   {
       handType = 3; //straight
       playerBalance += multipliers[handType]*playerBet;
       System.out.println("Balance: $"+playerBalance);
   }
   else if(numThreeOfKind == 1){
       handType = 2; //three of a kind
       playerBalance += multipliers[handType]*playerBet;
       System.out.println("Balance: $"+playerBalance);
   }
   else if(numPairs == 2){
           handType = 1; //2 pairs
           playerBalance += multipliers[handType]*playerBet;
           System.out.println("Balance: $"+playerBalance);
   }
   else if(checkRoyalPair() == 2){
           handType = 0; //Royal pair
           playerBalance += 2*multipliers[handType]*playerBet;
           System.out.println("Balance: $"+playerBalance);
   }
   else if(checkRoyalPair() == 1){
           handType = 0; //Royal pair
           playerBalance += multipliers[handType]*playerBet;
           System.out.println("Balance: $"+playerBalance);
   }
  
  
   if(handType == -1)
       System.out.println("Sorry, you lost!");
   else
       System.out.println("Hand: " + goodHandTypes[handType]);
   
   
  
  
   playerHand.clear();
   playerHand.addAll(copy);


    }
    
    
    
    
    

    
    private void getBetAmount(){
        
        Scanner keybd = new Scanner(System.in);
        System.out.println("Balance: $" +playerBalance);
        while(true){
            System.out.println("How much would you like to bet?");
            playerBet = keybd.nextInt();
            if (playerBet<=0 || playerBet > playerBalance){
                System.out.println("Invalid bet ammount");
            }
            else{
                break;
            }
        }
        keybd.nextLine(); //***************************************************
    }
    
    
    
    
    
  
   private int checkRoyalPair(){
       
       int count=0;
       
       int royalCard1=-1;
       int royalCard2=-2;
       int royalCard3=-3;
       int royalCard4=-4;
       int royalCard5=-5;
       
       
       
       if((playerHand.get(0).getRank()>10) || playerHand.get(0).getRank() == 1){
            royalCard1 = playerHand.get(0).getRank();
       }
       if((playerHand.get(1).getRank()>10) || playerHand.get(1).getRank() == 1){
            royalCard2 = playerHand.get(1).getRank();
       }
       if((playerHand.get(2).getRank()>10) || playerHand.get(2).getRank() == 1){
            royalCard3 = playerHand.get(2).getRank();
       }
       if((playerHand.get(3).getRank()>10) || playerHand.get(3).getRank() == 1){
            royalCard4 = playerHand.get(3).getRank();
       }
       if((playerHand.get(4).getRank()>10) || playerHand.get(4).getRank() == 1){
            royalCard5 = playerHand.get(4).getRank();
       }
       
       if(royalCard1==royalCard2)
           count++;
       if(royalCard1==royalCard3)
           count++;
       if(royalCard1==royalCard4)
           count++;
       if(royalCard1==royalCard5)
           count++;
       if(royalCard2==royalCard3)
           count++;
       if(royalCard2==royalCard4)
           count++;
       if(royalCard2==royalCard5)
           count++;
       if(royalCard3==royalCard4)
           count++;
       if(royalCard3==royalCard5)
           count++;
       if(royalCard4==royalCard5)
           count++;
       
               
    return count;
       
   }
    
    
    private boolean checkStraight(){
        int first = playerHand.get(0).getRank();
        int second = playerHand.get(1).getRank();
        
        if((second == first+1) || (first ==1 && second == 10)){
            for(int i = 2, j = second +1; i<5; i++, j++){
                if(playerHand.get(i).getRank() !=j){
                    return false;
                }
            }
            return true; 
        }
        else
            return false;
    }
    
    
    
    private boolean checkFlush(){
        int suit = playerHand.get(0).getSuit();
        for (int i=1; i<5;i++){                         //?????????????????????????????????????????????????????
            if(playerHand.get(i).getSuit() !=suit)
                return false;
        }
        return true;
    }
    
    
    private boolean checkRoyalFlush(){
        if(playerHand.get(0).getRank()==1){
            int suit = playerHand.get(0).getSuit();
            
            for(int i = 1,rank = 10; rank <= 13; i++, rank++){//?????????????????????????????????????????????
            Card c = playerHand.get(i);
            if(c.getRank() !=rank || c.getSuit() != suit)
                return false;
            }
            return true;
        }
        else
            return false;
    }
        
        
        
     
    
    

    /*************************************************
     *   add additional private methods here ....
     *
     *************************************************/
    
//    private int checkCombination(){
//        int combination =0;
//        if(checkRoyalPair())
//            combination =1;
//        if(checkTwoPair())
//            combination =2;
//        if(checkThreeOfAKind())
//            combination=3;
//        if(checkStraight())
//            combination=4;
//        if(checkFlush())
//            combination=5;
//        if(checkFullHouse())
//            combination=6;
//        if(checkFourOfAKind())
//            combination=7;
//        if(checkStraightFlush())
//            combination=8;
//        if(checkRoyalFlush())
//            combination=9;
//        
//        return combination;
//    }
//    
//    private boolean checkRoyalPair(){
//        Card[] array=sortRank();
//        int countPair=0;
//        
//    }


    public void play() 
    {
    /** The main algorithm for single player poker game 
     *
     * Steps:
     * 		showPayoutTable()
     *
     * 		++	
     * 		show balance, get bet 
     *		verify bet value, update balance
     *		reset deck, shuffle deck, 
     *		deal cards and display cards
     *		ask for positions of cards to keep  
     *          get positions in one input line
     *		update cards
     *		check hands, display proper messages
     *		update balance if there is a payout
     *		if balance = O:
     *			end of program 
     *		else
     *			ask if the player wants to play a new game
     *			if the answer is "no" : end of program
     *			else : showPayoutTable() if user wants to see it
     *			goto ++
     */

        // implement this method!
        
        
        showPayoutTable();
        while(true){
            getBetAmount();
            playerBalance -=playerBet;
            oneDeck.reset();
            oneDeck.shuffle();
            try{
                playerHand = oneDeck.deal(5);
                showCards(playerHand, "Current hand: ");
                replaceCards();
                checkHands();
            
                if(!repeatGame())
                    break;
            }
            catch(PlayingCardException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    
    private boolean repeatGame(){
        if(playerBalance ==0)
            return false;
        else{
            Scanner keybd = new Scanner(System.in);
            while(true){
                System.out.println("Would you like to play again? (y or n)");
                String ans = keybd.next();
                if(ans.charAt(0)=='n'||ans.charAt(0)=='N')
                    return false;
                else if(ans.charAt(0)=='y'|| ans.charAt(0)=='Y'){
                    System.out.println("Would you like to view the payout table? (y or n)");
                    ans = keybd.next();
                    if(ans.charAt(0)=='y'|| ans.charAt(0)=='Y')
                        showPayoutTable();
                    
                    return true;
                }  
            }
        }
    }
    
    
    
    private void replaceCards(){
        Scanner keybd = new Scanner(System.in);
        boolean loop = true;
        int i;
        int positions[] = null;
        while(loop){
            System.out.println("What cards would you like to keep? (e.g. 1 3 5): ") ;
            String input = keybd.nextLine().trim();
            if(input.equals(""))
                break;
            String tokens[] = input.split("\\s");
            positions = new int[tokens.length];
            for (i=0;i<tokens.length; i++){
                try{
                    positions[i]=Integer.parseInt(tokens[i]);
                    if(positions[i]<1 || positions[i]>5){
                        System.out.println("Card position should be a value between 1-5");
                        break;
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("invalid card position");
                    break;
                }
            }
            if (i==tokens.length)
                loop = false;
        }
        ArrayList<Card> retained = new ArrayList<Card>();
        if(positions != null){
            for(i=0;i<positions.length; i++){
                retained.add(playerHand.get(positions[i]-1));
            }
        }
        playerHand.clear();
        playerHand.addAll(retained);
        showCards(playerHand, "Cards with you:");
        try{
           playerHand.addAll(oneDeck.deal(numberOfCards - retained.size()));
        }
        catch (PlayingCardException e){
            System.out.println(e.getMessage());
        }
        showCards(playerHand,"New Hand: ");
    }
    
    private void showCards(List<Card> cards, String message){
        System.out.println(message + " " + Arrays.toString(cards.toArray()));
    }
    
    
        
        

    
    


    /*************************************************
     *   Do not modify methods below
    /*************************************************

    /** testCheckHands() is used to test checkHands() method 
     *  checkHands() should print your current hand type
     */ 
    public void testCheckHands()
    {
      	try {
    		playerHand = new ArrayList<Card>();

		// set Royal Flush
		playerHand.add(new Card(1,3));
		playerHand.add(new Card(10,3));
		playerHand.add(new Card(12,3));
		playerHand.add(new Card(11,3));
		playerHand.add(new Card(13,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight Flush
		playerHand.set(0,new Card(9,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight
		playerHand.set(4, new Card(8,1));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Flush 
		playerHand.set(4, new Card(5,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	 	// "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

		// set Four of a Kind
		playerHand.clear();
		playerHand.add(new Card(8,3));
		playerHand.add(new Card(8,0));
		playerHand.add(new Card(12,3));
		playerHand.add(new Card(8,1));
		playerHand.add(new Card(8,2));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Three of a Kind
		playerHand.set(4, new Card(11,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Full House
		playerHand.set(2, new Card(11,1));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Two Pairs
		playerHand.set(1, new Card(9,1));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Royal Pair
		playerHand.set(0, new Card(3,1));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// non Royal Pair
		playerHand.set(2, new Card(3,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");
      	}
      	catch (Exception e)
      	{
		System.out.println(e.getMessage());
      	}
    }

    /* Quick testCheckHands() */
    public static void main(String args[]) 
    {
	VideoPoker pokergame = new VideoPoker();
	pokergame.testCheckHands();
    }
}
