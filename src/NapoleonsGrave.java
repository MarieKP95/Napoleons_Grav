// Lösning skriven av Marie Paulsson
// Marie.Paulsson.0324@student.uu.se

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

// Klassen representerar kortspelet Napoleons Grav
public class NapoleonsGrave extends JPanel implements MouseListener {

    // Relativ startposition för de olika högarna
    static int xStart = 50;
    static int yStart = 50;

    // Marginal mellan högarna
    static int xSkip = CardPile.WIDTH + 25;
    static int ySkip = CardPile.HEIGHT + 25;

    // Lista på alla högar samt alla 52 kort
    private final static ArrayList<CardPile> piles = new ArrayList<CardPile>();
    private final ArrayList<Card> cardDeck = new ArrayList<Card>();

    // Variabler för att hjälpa spelets gång
    private boolean reusedTrashPile = false;
    private boolean fixed = false;
    private int seed = 1;
    public JLabel displayFixed = new JLabel("Fixed: false, Seed: 1");
    private CardPile selectedPile = null;

    NapoleonsGrave(ArrayList<Card> cardDeck) {
        this.cardDeck.addAll(cardDeck);
        setBackground( Color.WHITE );
        setForeground( Color.BLACK );
        createCardPiles();
        setUpDeckPile();
        createMenu();
        addMouseListener(this);
    }

    // Skapa alla högar
    public void createCardPiles() {
        piles.add(new KingPile(xStart, yStart));                           // a1
        piles.add(new ParkPile(xStart + xSkip, yStart));                // a2
        piles.add(new KingPile(xStart + xSkip*2, yStart));              // a3

        piles.add(new ParkPile(xStart, yStart + ySkip));                // b1
        piles.add(new EssPile(xStart + xSkip, yStart + ySkip));      // b2
        piles.add(new ParkPile(xStart + xSkip*2, yStart + ySkip));   // b3

        piles.add(new KingPile(xStart, yStart + ySkip*2));              // c1
        piles.add(new ParkPile(xStart + xSkip,yStart + ySkip*2));    // c2
        piles.add(new KingPile(xStart + xSkip*2,yStart + ySkip*2));  // c3

        piles.add(new DeckPile(xStart + xSkip*3, yStart));
        piles.add(new TrashPile(xStart + xSkip*3, yStart + ySkip));
    }

    // Lägg korten i kortdäcket och blanda korten
    public void setUpDeckPile() {
        // Rensa alla korthögar på kort
        piles.forEach(pile -> {
            pile.clear();
            pile.setSelected(false);
            pile.setPossiblePile(false);
        });

        CardPile deckPile = piles.get(9);
        for (Card card : cardDeck) {
            if (card.isFaceUp()) {
                card.flipCard();
            }
            deckPile.addCard(card);
        }
        deckPile.shufflePile(fixed, seed);
    }

    // Meny för att avsluta, starta om och få fixed blandning
    public void createMenu() {
        JButton quitButton = new JButton("Quit");
        JButton restartButton = new JButton("Restart");
        JButton fixedSeedButton = new JButton("On/Off \nfixed seed");
        JTextField seed = new JTextField("Enter seed",6);

        quitButton.addActionListener((event) -> System.exit(0));
        restartButton.addActionListener((event) -> restart());
        fixedSeedButton.addActionListener((event) -> setFixedSeed(seed.getText()));

        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(seed);
        buttonPanel.add(fixedSeedButton);
        buttonPanel.add(displayFixed);
        buttonPanel.add(restartButton);
        buttonPanel.add(quitButton);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    // Skapa ett fullt kortlek med 52 kort
    public static ArrayList<Card> createCards() {
        ArrayList<Card> cardDeck = new ArrayList<Card>();
        for (String suit:Card.SUITS) {
            for (int rank:Card.RANKS) {
                String filename = suit + "";
                if (rank == 11) {filename = filename + "j";}
                else if (rank == 12) {filename = filename + "q";}
                else if (rank == 13) {filename = filename + "k";}
                else {
                    filename = filename + String.valueOf(rank);
                }
                filename = filename + ".gif";
                Card card = new Card(rank, suit, 0, 0);
                card.setCardImage(filename);
                cardDeck.add(card);
            }
        }
        return cardDeck;
    }

    // Från MoveTest.java
    public CardPile findPile(int x, int y) {
        for (CardPile p : piles) {
            if (p.contains(x, y)) {
                return p;
            }
        }
        return null;
    }

    // Startar om spelet
    public void restart() {
        setUpDeckPile();
        reusedTrashPile = false;
        repaint();

    }

    // Sätter på/av fixed blandning och startar om spelet
    public void setFixedSeed(String seed) {
        this.fixed = !fixed;
        this.seed = Integer.parseInt(seed);
        displayFixed.setText("Fixed: " + fixed + ", Seed: " + this.seed);
        restart();
    }

    // Checkar spelets tillstånd, även om skräphögen ska återanvändas
    public void checkGameState() {
        CardPile deck = piles.get(9);
        CardPile trash = piles.get(10);
        if (deck.isEmpty() && !reusedTrashPile) {
            while(!trash.isEmpty()) {
                deck.addCard(trash.removeCard());
                deck.getCard().flipCard();

            }
            reusedTrashPile = true;
        }

        boolean a1, a3, b2, c1, c3;
        a1 = piles.get(0).isCompleted();
        a3 = piles.get(2).isCompleted();
        b2 = piles.get(4).isCompleted();
        c1 = piles.get(6).isCompleted();
        c3 = piles.get(8).isCompleted();

        if(a1 && a3 && b2 && c1 && c3) {
            JOptionPane.showMessageDialog(this, "You solved it!");
            restart();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        piles.forEach(pile -> pile.draw(g));
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawString("A", xStart/4, yStart + Card.HEIGHT/2);
        g.drawString("B", xStart/4, yStart + ySkip + Card.HEIGHT/2);
        g.drawString("C", xStart/4, yStart + ySkip*2 + Card.HEIGHT/2);
        g.drawString("1", xStart + Card.WIDTH/2, 35);
        g.drawString("2", xStart + xSkip + Card.WIDTH/2, 35);
        g.drawString("3", xStart + xSkip*2 + Card.WIDTH/2, 35);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Deck", xStart + xSkip*3, yStart);
        g.drawString("Trash", xStart + xSkip*3, yStart + ySkip);

    }

    // Markerar den tryckta högen om det går att dra kort från den och placera på en annan hög
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        CardPile currentPile = findPile(x, y);

        if (currentPile != null) {
            if (currentPile.canDrawCard() && !currentPile.isPossiblePile()) {
                if(selectedPile != null) { selectedPile.setSelected(false);}
                selectedPile = currentPile;
                currentPile.setSelected(true);
                if (!currentPile.getCard().isFaceUp()) {
                    currentPile.getCard().flipCard();
                }
                for (CardPile pile : piles) {
                    if (currentPile == pile) { continue; }
                    if (pile.canAddCard(currentPile.getCard())) {
                        pile.setPossiblePile(true);
                    }
                }
            }
            else if (selectedPile != null && currentPile.canAddCard(selectedPile.getCard())) {
                currentPile.addCard(selectedPile.removeCard());
                selectedPile.setSelected(false);
                selectedPile = null;
                for (CardPile pile : piles) {
                    pile.setPossiblePile(false);
                }
            }
        } else {
            selectedPile = null;
            for (CardPile pile : piles) {
                pile.setPossiblePile(false);
                pile.setSelected(false);
            }
        }
        repaint();
        checkGameState();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public static void main(String[] args) {
        ArrayList<Card> cards = createCards();
        JFrame w = new JFrame("Napoleons Grav");
        JPanel np = new NapoleonsGrave(cards);

        w.getContentPane().add(np);
        w.setBounds(0, 0, 540, 500);
        w.setDefaultCloseOperation(w.EXIT_ON_CLOSE);
        w.setVisible(true);
    }
}