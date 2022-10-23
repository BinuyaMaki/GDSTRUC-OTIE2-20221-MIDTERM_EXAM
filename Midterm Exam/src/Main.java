import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // stacks declaration
        CardStack cardStack = new CardStack();
        DiscardedStack discardedStack = new DiscardedStack();
        PlayerHandStack playerHandStack = new PlayerHandStack();

        // variables
        Random random = new Random();
        int x = 0;
        int command = 0;

        // card to cardStack
        cardStack.push(new Card(1, "Pot of Greed"));
        cardStack.push(new Card(2, "Dark Magician Girl"));
        cardStack.push(new Card(3, "Blue Eyes White Dragon"));
        cardStack.push(new Card(4, "Crimson Fire"));
        cardStack.push(new Card(5, "Negate Attack"));
        cardStack.push(new Card(6, "Yata-Garasu"));
        cardStack.push(new Card(7, "Cyber Dragon"));
        cardStack.push(new Card(8, "Exodia the Forbidden One"));
        cardStack.push(new Card(9, "Dark Magician"));
        cardStack.push(new Card(10, "Left Arm of the Forbidden One"));
        cardStack.push(new Card(11, "Tyr, the Vanquishing Warlord"));
        cardStack.push(new Card(12, "Obelisk the Tormentor"));
        cardStack.push(new Card(13, "Royal Decree"));
        cardStack.push(new Card(14, "Right Arm of the Forbidden One"));
        cardStack.push(new Card(15, "Trap Stun"));
        cardStack.push(new Card(16, "Left Leg of the Forbidden One"));
        cardStack.push(new Card(17, "Right Leg of the Forbidden One"));
        cardStack.push(new Card(18, "Morphing Jar"));
        cardStack.push(new Card(19, "The Winged Dragon of Ra"));
        cardStack.push(new Card(20, "Slifer the Sky Dragon"));
        cardStack.push(new Card(21, "Swords of Revealing Light"));
        cardStack.push(new Card(22, "Gold Sarcophagus"));
        cardStack.push(new Card(23, "The Creator God of Light, Horakhty"));
        cardStack.push(new Card(24, "Monster Reborn"));
        cardStack.push(new Card(25, "Blue Eyes Ultimate Dragon"));
        cardStack.push(new Card(26, "Black Luster Soldier"));
        cardStack.push(new Card(27, "Dark End Dragon"));
        cardStack.push(new Card(28, "Minerva, the Exalted Lightsworn"));
        cardStack.push(new Card(29, "Stardust Divinity"));
        cardStack.push(new Card(30, "Sky Striker Ace Raye"));

        // establish while loop until no more cards in deck (cardStack)
        while (cardStack.isEmpty() == false) {
            // round start UI
            System.out.println("Round start!");
            System.out.println("Randomizing values...");
            System.out.println("Input any character to continue:");

            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

            // x randomizer (1 to 5)
            x = random.nextInt(6);

            // command randomizer
            command = random.nextInt(4);

            if (cardStack.returnCount() <= 5) {
                x = changeDeckX(x, cardStack);
            }

            // command conditions
            // draw
            if (command == 1) {
                draw(x, cardStack, playerHandStack);
            }
            // discard
            else if (command == 2 && playerHandStack.isEmpty() == false) {
                if (x > playerHandStack.returnCount()) {
                    x = changeHandX(x, playerHandStack);
                }
                discard(x, discardedStack, playerHandStack);
            }
            // retrieve
            else if (command == 3 && discardedStack.isEmpty() == false){
                if (x > discardedStack.returnCount()) {
                    x = changeGraveyardX(x, discardedStack);
                }
                retrieve(x, cardStack, discardedStack);
            }
            // revert to command 1 if all other conditions fail
            else {
                draw(x, cardStack, playerHandStack);
            }

            // print info after round
            System.out.println("Deck information:");
            playerHandStack.printStack();
            cardStack.countStack();
            discardedStack.countStack();

            System.out.println("Input any character to continue:");

            scanner.nextLine();
        }

        // game end UI
        System.out.println("Game has ended! All cards in deck have been depleted!");
    }

    // draw x cards from deck to hand (playerHandStack)
    private static void draw(int x, CardStack cardStack, PlayerHandStack playerHandStack) {
        Card card;

        System.out.println("Action: Draw " + x + " card/s from the deck.");

        for (int i = 0; i < x; i++) {
            card = cardStack.peek();
            playerHandStack.push(card);
            cardStack.pop();
        }
    }

    // discard x cards from hand to graveyard (discardedStack)
    private static void discard(int x, DiscardedStack discardedStack, PlayerHandStack playerHandStack) {
        Card card;

        System.out.println("Action: Discard " + x + " card/s from hand to the graveyard.");

        for (int i = 0; i < x; i++) {
            card = playerHandStack.peek();
            discardedStack.push(card);
            playerHandStack.pop();
        }
    }

    // retrieve x cards from graveyard back to deck
    private static void retrieve(int x, CardStack cardStack, DiscardedStack discardedStack) {
        Card card;

        System.out.println("Action: Retrieve " + x + " card/s from the graveyard to the deck.");

        for (int i = 0; i < x; i++) {
           card = discardedStack.peek();
           cardStack.push(card);
           discardedStack.pop();
        }
    }

    // change x if any stack has less than or equal to 5 cards left
    private static int changeDeckX(int x, CardStack cardStack) {
        if (cardStack.returnCount() == 5) {
            x = 5;
        }
        else if (cardStack.returnCount() == 4) {
            x = 4;
        }
        else if (cardStack.returnCount() == 3) {
            x = 3;
        }
        else if (cardStack.returnCount() == 2) {
            x = 2;
        }
        else if (cardStack.returnCount() == 1) {
            x = 1;
        }
        return x;
    }

    private static int changeGraveyardX(int x, DiscardedStack discardedStack) {
        if (discardedStack.returnCount() == 5) {
            x = 5;
        }
        else if (discardedStack.returnCount() == 4) {
            x = 4;
        }
        else if (discardedStack.returnCount() == 3) {
            x = 3;
        }
        else if (discardedStack.returnCount() == 2) {
            x = 2;
        }
        else if (discardedStack.returnCount() == 1) {
            x = 1;
        }
        else if (discardedStack.returnCount() == 0) {
            x = 0;
        }
        return x;
    }

    private static int changeHandX(int x, PlayerHandStack playerHandStack) {
        if (playerHandStack.returnCount() == 5) {
            x = 5;
        }
        else if (playerHandStack.returnCount() == 4) {
            x = 4;
        }
        else if (playerHandStack.returnCount() == 3) {
            x = 3;
        }
        else if (playerHandStack.returnCount() == 2) {
            x = 2;
        }
        else if (playerHandStack.returnCount() == 1) {
            x = 1;
        }
        else if (playerHandStack.returnCount() == 0) {
            x = 0;
        }
        return x;
    }
}