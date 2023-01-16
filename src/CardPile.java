// Lösning skriven av Marie Paulsson
// Marie.Paulsson.0324@student.uu.se

import java.awt.*;
import java.util.*;

// Representerar en hög av kort
public abstract class CardPile {

    // Högens dimensioner, lite större än ett kort
    public final static int WIDTH = 75;
    public final static int HEIGHT = 100;

    // Högens position/placering
    protected int x;
    protected int y;

    // Högens kort
    protected final Stack<Card> cards;

    // Anger om det går att placera ett kort på högen
    private boolean possiblePile;
    // Anger om korthögen är markerad/vald
    private boolean selected;

    public CardPile(int x, int y) {
        this.x = x;
        this.y = y;
        cards = new Stack<>();
        possiblePile = false;
        selected = false;
    }

    public final void clear() {
        cards.clear();
    }

    // Från Budds Solitare
    public boolean isEmpty() {
        return cards.empty();
    }

    // Få översta kortet
    public Card getCard() {
        if(cards.isEmpty()) {
            return null;
        }
        return cards.peek();
    }

    // Lägg kortet högst upp
    public void addCard(Card card) {
        card.setPos(x+2, y+2);
        cards.push(card);
    }


    // Ta bort kortet högst upp
    // Från Budds solitare
    public Card removeCard() {
        try {
            return cards.pop();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    // Blanda kortleken
    public void shufflePile(boolean fixed, int seed) {
        if(fixed) {
            Collections.shuffle(cards, new Random(seed));
        }
        else {
            Collections.shuffle(cards, new Random());
        }
    }

    // Från MoveTest.java
    public boolean contains(int x1, int y1) {
        return x <= x1 && x1 <= x + WIDTH
                && y <= y1 && y1 <= y + HEIGHT;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setPossiblePile(boolean possiblePile) {
        this.possiblePile = possiblePile;
    }

    public boolean isPossiblePile() { return possiblePile; }

    public void draw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, WIDTH, HEIGHT);

        if (selected) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLUE);
            g2.setStroke(new BasicStroke(3.0f));
            g2.drawRect(x, y, WIDTH, HEIGHT);
        }

        if (possiblePile) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.GREEN);
            g2.setStroke(new BasicStroke(3.0f));
            g2.drawRect(x, y, WIDTH, HEIGHT);
        }

        if (!cards.empty()) {
            for (Card card : cards) {
                card.draw(g);
            }
        }
    }
    public boolean isCompleted() { return cards.isEmpty(); }

    abstract public boolean canDrawCard();

    abstract public boolean canAddCard(Card card);
}