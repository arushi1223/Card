package ca.sheridancollege.project.game.solution;

import ca.sheridancollege.project.Card;
import ca.sheridancollege.project.game.enums.Suit;
import ca.sheridancollege.project.game.enums.CardNumber;

public class WarCard extends Card{
   
    public WarCard() {
        super();
    }
    
    public WarCard(Suit suit, CardNumber number){
        super(suit, number);
    }
   
    public int getCardNumber(){
        return number.getId();
    }

    public String getSuit() {
        return suit.getValue();
    }

    @Override
    public String toString() {
        StringBuilder card = new StringBuilder();
        
        return card.append(this.number.getValue()).append(" of ").append(suit.getValue()).toString();
    }
}