// Lösning skriven av Marie Paulsson
// Marie.Paulsson.0324@student.uu.se

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// Representerar ett kort i ett kortspel
public class Card {

    // Konstanter, kortspelets färger och valörer
    public final static String[] SUITS = {"d", "h", "s", "c"};
    public final static int[] RANKS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    // Kortets dimensioner
    public static int WIDTH = 71;
    public static int HEIGHT = 96;

    // Kortets placering/position
    private int x;
    private int y;

    // Kortets valör och färg
    private final int rank;
    private final String suit;

    // Kortets fram- och baksida samt vilken av sidorna som visas uppåt.
    private boolean faceUp;
    private Image frontside;
    private Image backside;

    Card(int rank, String suit, int x, int y) {
        this.rank = rank;
        this.suit = suit;
        this.x = x;
        this.y = y;
        faceUp = false;
    }

    public int getRank() { return rank; }

    public boolean isFaceUp() { return faceUp; }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void flipCard() { faceUp = !faceUp; }

    // Från DrawTest.java
    static Image readImage(String imageFile) {
        Image image;
        try {
            image = ImageIO.read(new File(imageFile));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public void setCardImage(String imageFile) {
        // Kommentera ut endast en av de nedanstående beroende på vilken metod
        // som används för att köra programmet

        // IntelliJ metod
        frontside = readImage("../cards/" + imageFile);
        backside = readImage("../cards/b1fv.gif");

        // kommandotolk metod
        //frontside = readImage("../bin/cards/" + imageFile);
        //backside = readImage("../bin/cards/b1fv.gif");
    }

    public Image getCardImage() {
        if(isFaceUp()) return frontside;
        return backside;
    }

    public void draw(final Graphics g) {
        g.drawImage(this.getCardImage(), getX(), getY(), null);
    }
}
