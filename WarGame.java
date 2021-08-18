package ca.sheridancollege.project.game.solution;

import java.util.*;

import ca.sheridancollege.project.Game;
import ca.sheridancollege.project.Card;
import ca.sheridancollege.project.GroupOfCards;

import ca.sheridancollege.project.game.enums.Suit;
import ca.sheridancollege.project.game.enums.CardNumber;

public class WarGame extends Game{

    private List<WarPlayer> players;

    public WarGame(String name) {
        super(name);
    }

    private ArrayList<Card> getInitializedCards() {
        ArrayList<Card> cards= new ArrayList<>();

        for (Suit suit: Suit.values()) {
            for(CardNumber cardNumber: CardNumber.values()) {
                cards.add(new WarCard(suit, cardNumber));
            }
        }

        return cards;
    }

    public List<WarPlayer> getWarPlayers() {
        return players;
    }

    public void setWarPlayers(List<WarPlayer> players) {
        this.players = players;
    }

    public void play(Scanner scanner) {
        WarPlayer computer = getWarPlayers().get(0);
        WarPlayer user = getWarPlayers().get(1);
        List<WarCard> betCards = new ArrayList<>();

        while(true) {
            WarCard userCard = user.playeMove();
            WarCard compCard = computer.playeMove();

            if(compCard.getCardNumber() > userCard.getCardNumber()) {
                user.removeCard(userCard);
                computer.addCard(userCard);
                computer.addcardList(betCards);
                endRound(false, computer, betCards, scanner);
            }
            else if(compCard.getCardNumber() < userCard.getCardNumber()) {
                computer.removeCard(compCard);
                user.addCard(compCard);
                user.addcardList(betCards);
                endRound(false, user, betCards, scanner);
            }
            else {
                user.removeCard(userCard);
                computer.removeCard(compCard);
                betCards.add(compCard);
                betCards.add(userCard);
                endRound(true, null, null, scanner);
            }

            if(!computer.haveCards() || !user.haveCards()) {
                declareWinner();
                break;
            }

            println("Press enter to continue to next round...");
            scanner.nextLine();
        }
    }

    private void endRound(boolean isTie, WarPlayer warPlayer, List<WarCard> betCards, Scanner scanner) {
        if(!isTie) {
            betCards.clear();
            println("\n" + warPlayer.getActiveName() + " won the round.\n");
        }
        else{
            println("Round got the tie.\n");
        }
    }

    public void declareWinner() {
        WarPlayer computer = getWarPlayers().get(0);
        WarPlayer user = getWarPlayers().get(1);
        String winner = computer.haveCards() ? computer.getActiveName() : user.getActiveName();

        println(winner + " wins the battle!\n");
    }

    public WarCardGroup convertToLinkedList(GroupOfCards cardGroup, int start, int end) {
        List<WarCard> cards = new LinkedList<>();

        for(int idx = start; idx < end; idx++) {
            cards.add((WarCard) cardGroup.getCards().get(idx));
        }

        return new WarCardGroup(cards);
    }

    private static void println(String messsage) {
        System.out.println(messsage);
    }

    private static void print(String messsage) {
        System.out.print(messsage);
    }

    public static void main(String[] args) {
        String gameName = "Card War";
        String instructions = "You an d computer will be given random 26 cards out of shuffled deck. You and computer will draw one card in each round and who ever will be having bigger number, will win that round and takes the opponent's card. In case of a tie, drwn cards will be kept as bet and game will continue uptil one wins and takes the bet then. Game ends when one is left with no card and other with more cards wins.";
        println("Welcome to " + gameName);
        println("\nInstructions: ");
        println(instructions);
        print("\n Please enter your name:");

        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();

        WarGame game = new WarGame(gameName);
        GroupOfCards cardGroup = new GroupOfCards(52, game.getInitializedCards());
        cardGroup.shuffle();


        WarPlayer computer = new WarPlayer("computer", game.convertToLinkedList(cardGroup, 0, 26));
        WarPlayer p1 = new WarPlayer(playerName, game.convertToLinkedList(cardGroup, 26, 52));
        game.setWarPlayers(Arrays.asList(computer, p1));

        game.play(scanner);;
    }
}
