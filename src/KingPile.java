// Lösning skriven av Marie Paulsson
// Marie.Paulsson.0324@student.uu.se

// Representerar 7-högarna
class KingPile extends CardPile {
    public KingPile(int x, int y) {
        super(x, y);
    }

    public boolean canDrawCard() {
        return false;
    }

    // En 7a kan läggas när högen är tom.
    // Ett kort kan läggas när dens valör är 1 högre än toppen på högen.
    public boolean canAddCard(Card card) {
        if (card == null) { return false; }
        if (cards.isEmpty()) {
            return card.getRank() == 7;
        }
        return getCard().getRank() + 1 == card.getRank();
    }

    @Override
    public boolean isCompleted() {
        return cards.size() == 7 && getCard().getRank() == 13;
    }
}