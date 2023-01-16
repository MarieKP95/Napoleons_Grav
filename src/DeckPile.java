// Lösning skriven av Marie Paulsson
// Marie.Paulsson.0324@student.uu.se

// Representerar högen där man drar kort ifrån
class DeckPile extends CardPile {
    public DeckPile(int x, int y) {
        super(x, y);
    }

    // Kan plocka kortet om den inte är tom
    public boolean canDrawCard() {
        return !cards.isEmpty();
    }

    // Kan inte lägga tillbaka kort
    public boolean canAddCard(Card card) {
        return false;
    }
}