import entity.Player;
import entity.PlayerCreator;
import world.GameWorld;

public class Main {

    public static void main(String[] args) throws Exception {
        printBanner();
        Player user = PlayerCreator.createPlayer();

        GameWorld gameWorld = new GameWorld();
        GameLoop gameLoop = new GameLoop(user, gameWorld);
        gameLoop.start();
    }

    private static void printBanner() {
        System.out.println(
            " _    _        _                               _ \n" +
            "| |  | |      | |                             | |\n" +
            "| |  | |  ___ | |  ___   ___  _ __ ___    ___ | |\n" +
            "| |/\\| | / _ \\| | / __| / _ \\| '_ ` _ \\  / _ \\| |\n" +
            "\\  /\\  /|  __/| || (__ | (_)|| | | | | ||  __/|_|\n" +
            " \\/  \\/  \\___||_| \\___| \\___/|_| |_| |_| \\___|(_)\n" +
            "                                                  "
        );
    }
}
