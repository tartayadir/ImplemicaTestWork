package Task1;

public class Main {

    public static void main(String[] args) {

        long numberOfProperlyClosedBrackets = Controller.NumberOfProperlyClosedBrackets(
                Controller.readBracketsFromConsole()
        );
        System.out.println("Number of properly closed brackets : " + numberOfProperlyClosedBrackets);
    }
}



