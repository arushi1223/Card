package ca.sheridancollege.project.game.solution;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WarCardGroup {

    private List<WarCard> cards;

    public WarCardGroup(List<WarCard> cards) {
        this.cards = cards;
    }

    public List<WarCard> getCards() {
        return cards;
    }

    public int getSize() {
        return cards.size();
    }

    public void sort() {
        Collections.sort(this.cards, (c1, c2) -> {
            return c1.getCardNumber() == c2.getCardNumber() ? c1.getSuit().compareTo(c2.getSuit()) : c1.getCardNumber() - c2.getCardNumber();
        });
    }

    public WarCard getRandomCard() {
        return cards.get(new Random().nextInt(cards.size()));
    }

    public WarCard getWarCardByIndex(int index) {
        return cards.get(index);
    }

    public void addcard(WarCard warCard) {
        cards.add(warCard);
    }

    public void addcardList(List<WarCard> warCards) {
        cards.addAll(warCards);
    }

    public void removeCard(WarCard warCard) {
        cards.remove(warCard);
    }

    public boolean containCards() {
        return !this.cards.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 1;

        for(WarCard card: cards) {
            sb.append(index++).append(". ").append(card.toString()).append("\n");
        }

        return sb.toString();
    }
}
