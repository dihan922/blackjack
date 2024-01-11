import java.util.*;

class BlackJack {
  // Find special card names and values to be added to total
  public static Object[] convertCard(int card, String card_name){
    Object[] values = new Object[2];

		if (card > 10) {
      if (card == 11) {
        card_name = "J";
      } else if (card == 12) {
        card_name = "Q";
      } else if (card == 13) {
        card_name = "K";
      }
      card = 10;
    }
    else if (card == 1) {
      card_name = "A";
      card = 11;
    }
    else {
      card_name = Integer.toString(card);
    }

    // Put changed variables into array for returning
    values[0] = card;
    values[1] = card_name;
    return values;
	}
  
  public static void main(String[] args) throws Exception {
    Random r = new Random();
    Scanner input = new Scanner(System.in);

    int bank = 1000;
    int bet = 0;

    String user_card1_name = "";
    String user_card2_name = "";
    String dealer_card1_name = "";
    String dealer_card2_name = "";

    String user_display = "";
    String dealer_display = "";
    String extra_user_card_display = "";
    String extra_dealer_card_display = "";

    int extra_user_card = 0;
    String extra_user_card_name = "";
    int extra_dealer_card = 0;
    String extra_dealer_card_name = "";

    String hitOrStand = "";

    boolean playing = true;
    boolean invalid = true;
    while (playing) {
      System.out.print("\033[H\033[2J");
      System.out.flush();

      // Set a valid betting amount
      invalid = true;
      System.out.println("Bank: $" + bank);
      while (invalid) {
        System.out.print("Enter a bet amount: ");
        if (input.hasNextInt()) {
          bet = input.nextInt();
          if (bet <= bank && bet > 0) {
            invalid = false;
            input.nextLine();
          }
        } else {
          input.nextLine();
        }
        
      }
      System.out.println();

      boolean adding_cards = true;

      // Generate variables for user's cards and card values
      int user_card1 = 1 + r.nextInt(13);
      int user_card2 = 1 + r.nextInt(13);
      int user_card_count = 2;
      int user_total = 0;

      // Generate variables for dealer's cards and card values
      int dealer_card1 = 1 + r.nextInt(13);
      int dealer_card2 = 1 + r.nextInt(13);
      int dealer_card_count = 2;
      int dealer_total = 0;

      // Set user variables to proper card values using convertCard function
      Object[] user_card1_values = convertCard(user_card1, user_card1_name);
      user_card1 = (int)user_card1_values[0];
      user_card1_name = user_card1_values[1].toString();

      Object[] user_card2_values = convertCard(user_card2, user_card2_name);
      user_card2 = (int)user_card2_values[0];
      user_card2_name = user_card2_values[1].toString();

      // Add user's card values for total
      user_total = user_card1 + user_card2;
      if (user_total == 22) {
        user_total = 12;
      }


      // Set dealer variables to proper card values using convertCard function
      Object[] dealer_card1_values = convertCard(dealer_card1, dealer_card1_name);
      dealer_card1 = (int)dealer_card1_values[0];
      dealer_card1_name = dealer_card1_values[1].toString();
      
      Object[] dealer_card2_values = convertCard(dealer_card2, dealer_card2_name);
      dealer_card2 = (int)dealer_card2_values[0];
      dealer_card2_name = dealer_card2_values[1].toString();

      // Add dealer's card values for total
      dealer_total = dealer_card1 + dealer_card2;
      if (dealer_total == 22) {
        dealer_total = 12;
      }


      // Display game
      user_display = String.format(
        "★ User's Hand ★ %n" + 
        "Card 1: %s %n" +
        "Card 2: %s %n", 
        user_card1_name, user_card2_name
      );

      dealer_display = String.format(
        "★ Dealer's Hand ★ %n" + 
        "Card 1: %s %n" +
        "Card 2: ? %n",
        dealer_card1_name
      );
      
      System.out.println(user_display + "Total: " + user_total);
      System.out.println();
      System.out.println(dealer_display + "Total: ?");
      System.out.println();

      while (adding_cards) {
        invalid = true;
        while (invalid) {
          System.out.print("Hit or Stand? (h or s): ");
          hitOrStand = input.nextLine();
          if (hitOrStand.equalsIgnoreCase("s")) {
            invalid = false;
            adding_cards = false;
          }
          if (hitOrStand.equalsIgnoreCase("h")) {
            invalid = false;
          }
        }
        

        if (hitOrStand.equalsIgnoreCase("h")) {
          // Add cards to user's hands
          extra_user_card = 1 + r.nextInt(13);
          Object[] extra_card_values = convertCard(extra_user_card, extra_user_card_name);
          extra_user_card = (int)extra_card_values[0];
          extra_user_card_name = extra_card_values[1].toString();

          user_total += extra_user_card;
          user_card_count += 1;
          extra_user_card_display = String.format("Card %s: %s %n", user_card_count, extra_user_card_name);
          user_display += extra_user_card_display;

          System.out.println();
          System.out.println(user_display + "Total: " + user_total);
          System.out.println();
          System.out.println(dealer_display + "Total: ?");
          System.out.println();
          
          if (user_total > 20) {
            dealer_display = String.format(
              "★ Dealer's Hand ★ %n" + 
              "Card 1: %s %n" +
              "Card 2: %s %n",
              dealer_card1_name, dealer_card2_name
            );
            if (user_total > 21) {
              System.out.println("User busts!");
            }
            adding_cards = false;
          }
        }
      }
      
      // Declare dealer winner immediately if user busts
      if (user_total > 21) {
        System.out.println("Dealer wins!");
        bank -= bet;
      }
      
      else {
        if (dealer_total < 16) {
          System.out.println("\n~~ Dealer hits ~~\n");
        } else {
          System.out.println("\n~~ Dealer stands ~~\n");
        }

        dealer_display = String.format(
          "★ Dealer's Hand ★ %n" + 
          "Card 1: %s %n" +
          "Card 2: %s %n",
          dealer_card1_name, dealer_card2_name
        );

        // Add cards to dealer's hands
        while (dealer_total < 16) {
          extra_dealer_card = 1 + r.nextInt(13);
          Object[] extra_card_values = convertCard(extra_dealer_card, extra_dealer_card_name);
          extra_dealer_card = (int)extra_card_values[0];
          extra_dealer_card_name = extra_card_values[1].toString();

          dealer_total += extra_dealer_card;
          dealer_card_count += 1;
          extra_dealer_card_display = String.format("Card %s: %s %n", dealer_card_count, extra_dealer_card_name);
          dealer_display += extra_dealer_card_display;
        }

        System.out.println(user_display + "Total: " + user_total);
        System.out.println();
        System.out.println(dealer_display + "Total: " + dealer_total);
        System.out.println();

        if (dealer_total > 21) {
          System.out.println("Dealer busts!");
        }

        // Display winner and update bank
        if (dealer_total == 21) {
          System.out.println("Dealer wins!");
          bank -= bet;
        } else if (user_total == 21) {
          System.out.println("User wins!");
          bank += bet;
        } 
        else if (dealer_total > 21) {
          System.out.println("User wins!");
          bank += bet;
        } 
        else if (user_total > dealer_total && user_total < 22) {
          System.out.println("User wins!");
          bank += bet;
        } else if (dealer_total > user_total && dealer_total < 22) {
          System.out.println("Dealer wins!");
          bank -= bet;
        }
        else {
          System.out.println("It's a tie!");
        }
      }

      // Ends game if user's bank runs out of money
      if (bank <= 0) {
        System.out.println("You ran out of money!");
        Thread.sleep(2000);
        bank = 0;
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("You ran out of money!");
        System.out.println("Thanks for playing!");
        return;
      }

      // Adds another round or ends the game
      String playAgain = "";
      invalid = true;
      while (invalid) {
        System.out.print("\nPlay another round or cash out? (p or c): ");
        playAgain = input.nextLine();
        if (playAgain.equalsIgnoreCase("p") || playAgain.equalsIgnoreCase("c")) {
          invalid = false;
        }
      }
      if (playAgain.equalsIgnoreCase("c")) {
          playing = false;
          input.close();
          System.out.print("\033[H\033[2J");
          System.out.flush();
          System.out.println("Bank: $" + bank);
          System.out.println("Thanks for playing!");
        }
    }
  }
}
