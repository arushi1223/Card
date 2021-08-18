package ca.sheridancollege.project.game.solution;

import ca.sheridancollege.project.Card;
import ca.sheridancollege.project.Player;

import java.util.List;
import java.util.Scanner;

public class WarPlayer extends Player {

    private WarCardGroup cardGroup;
    private Scanner scanner;

    public WarPlayer(String name, WarCardGroup cardGroup) {
        super(name);
        this.cardGroup = cardGroup;
        this.scanner = new Scanner(System.in);
    }

    public WarPlayer(String name) {
        super(name);
    }

    public String getActiveName() {
        return getName().equals("computer") ? "Computer" : "You";
    }

    public boolean haveCards() {
        return cardGroup.containCards();
    }

    public WarCard playeMove() {
        if (getName().equals("computer")) {
            WarCard warCard = cardGroup.getRandomCard();
            println("Computer's Selected Card: " + warCard.toString());
            return warCard;
        }

        println("Your latest deck:");
        cardGroup.sort();
        println(cardGroup.toString());
        int move = 0;
        boolean scannedOnce = false;
        do {
            print(!scannedOnce ? "Please select any of the above card: " : "Please select the valid card: ");
            move = scanner.nextInt();
            scannedOnce = true;
        }
        while (move < 1 || move > cardGroup.getSize());

        WarCard warCard = cardGroup.getWarCardByIndex(move - 1);
        println("\nYour Selected Card: " + warCard.toString());

        return warCard;
    }

    public void addCard(WarCard warCard) {
        cardGroup.addcard(warCard);
    }

    public void addcardList(List<WarCard> warCards) {
        cardGroup.addcardList(warCards);
    }

    public void removeCard(WarCard warCard) {
        cardGroup.removeCard(warCard);
    }

    @Override
    public Card play() {
        return playeMove();
    }

    private void println(String message) {
        System.out.println(message);
    }

    private void print(String message) {
        System.out.print(message);
    }
}