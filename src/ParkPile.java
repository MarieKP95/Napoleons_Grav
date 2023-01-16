// Lösning skriven av Marie Paulsson
// Marie.Paulsson.0324@student.uu.se

// Representerar högar som man kan "parkera" ett kort på
class ParkPile extends CardPile {
    public ParkPile(int x, int y) {
        super(x, y);
    }

    // Kan plocka kortet om den inte är tom
    public boolean canDrawCard() {
        return !cards.isEmpty();
    }

    // Ett kort kan läggas när högen är tom.
    public boolean canAddCard(Card card) {
        return cards.isEmpty();
    }
}