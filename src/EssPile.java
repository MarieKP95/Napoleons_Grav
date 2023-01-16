// Lösning skriven av Marie Paulsson
// Marie.Paulsson.0324@student.uu.se

// Representerar 6-högen
class EssPile extends CardPile {
    public EssPile(int x, int y) {
        super(x, y);
    }

    public boolean canDrawCard() {
        return false;
    }

    // En 6a kan läggas när högen är tom eller på ett Ess.
    // Ett kort kan läggas när dens valör är 1 mindre än toppen på högen.
    public boolean canAddCard(Card card) {
        if (card == null) { return false;}
        if (card.getRank() == 6) {
            return (cards.isEmpty() || getCard().getRank() == 1 );
        }
        if(!cards.isEmpty()) {
            return getCard().getRank() - 1 == card.getRank();
        }
        return false;
    }
    @Override
    public boolean isCompleted() {
        return cards.size() == 24 && getCard().getRank() == 1;
    }
}
