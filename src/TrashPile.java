// Lösning skriven av Marie Paulsson
// Marie.Paulsson.0324@student.uu.se

// Representerar kasthögen
class TrashPile extends CardPile {
    public TrashPile(int x, int y) {
        super(x, y);
    }

    // Kan plocka kortet om högen inte är tom
    public boolean canDrawCard() {
        return !cards.isEmpty();
    }

    // Kan alltid lägga ett kort
    public boolean canAddCard(Card card) {
        return true;
    }
}