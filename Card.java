/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import ca.sheridancollege.project.game.enums.CardNumber;
import ca.sheridancollege.project.game.enums.Suit;


public abstract class Card {
    
    protected CardNumber number;
    protected Suit suit;

    public Card() {}
    
    public Card(Suit suit, CardNumber number){
        this.suit = suit;
        this.number = number;
    }

    @Override
    public abstract String toString();

}
