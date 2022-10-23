import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.ListIterator;

public class DiscardedStack {
    private LinkedList<Card> stack;

    public DiscardedStack() {
        stack = new LinkedList<>();
    }

    public void push(Card card) {
        stack.push(card);
    }

    public Card pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return stack.pop();
    }

    public Card peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void countStack() {
        int i = 0;
        System.out.println("Graveyard: ");

        ListIterator<Card> iterator = stack.listIterator();

        while (iterator.hasNext()) {
            //System.out.println(iterator.next());
            iterator.next();
            i++;
        }
        System.out.println("The graveyard has " + i + " Cards.");
    }

    public int returnCount() {
        int i = 0;

        ListIterator<Card> iterator = stack.listIterator();

        while (iterator.hasNext()) {
            //System.out.println(iterator.next());
            iterator.next();
            i++;
        }
        return i;
    }
}
