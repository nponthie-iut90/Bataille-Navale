import boardifier.control.StageFactory;
import boardifier.model.GameException;
import boardifier.model.Model;
import boardifier.view.View;
import control.GameController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Method to start the game
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static boolean askRematch() {
        String answer = "";
        System.out.println("System > " + "Do you want a rematch ? (Y)");
        System.out.print("Players > ");
        try {
            answer = br.readLine();
        } catch (IOException e) {
        }

        if (!answer.isEmpty()) {
            if (answer.charAt(0) == 'Y') {
                return true;
            }
        }
        return false;
    }

    public static int askBotDifficulty() {
        System.out.println("System > " + "What difficulty do you want ? (1 or 2)");
        String answer;
        try {
            System.out.print("Players > ");
            answer = br.readLine();
            if (answer != null && answer.length() == 1) {
                if (answer.charAt(0) == '1') {
                    return 1;
                } else if (answer.charAt(0) == '2') {
                    return 2;
                }
            }
        } catch (IOException e) {}
        return 1;
    }

    /**
     * Do everything necessary to start the game
     *
     * @param args the mode of the game
     */
    public static void main(String[] args) {
        boolean ok = true;
        while (ok) {
            Model model = new Model();
            StageFactory.registerModelAndView("bataille_navale", "model.StageModel", "view.StageView");
            View StageView = new View(model);
            GameController control = new GameController(model, StageView);
            control.setRunningTrue();

            int mode = 0;
            if (args.length == 1) {
                try {
                    mode = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    mode = 0;
                }
            }
            if (mode == 0) {
                boolean ok2 = false;
                String name = "";
                while (!ok2) {
                    System.out.println("System  > What is the name of the first player ?");
                    System.out.print("Player1 > ");
                    try {
                        name = br.readLine();
                    } catch (IOException e) {
                    }
                    if (name.length() == 0) {
                        System.out.println("System > The name can not be empty !");
                    } else {
                        ok2 = true;
                    }
                }
                model.addHumanPlayer(name);
                boolean ok3 = false;
                while (!ok3) {
                    System.out.println("System  > What is the name of the second player ?");
                    System.out.print("Player2 > ");
                    try {
                        name = br.readLine();
                    } catch (IOException e) {
                    }
                    if (name.length() == 0) {
                        System.out.println("System > The name can not be empty !");
                    } else {
                        ok3 = true;
                    }
                }
                model.addHumanPlayer(name);
            } else if (mode == 1) {
                boolean ok4 = false;
                String name = "";
                while (!ok4) {
                    System.out.println("System > What is the name of the first player ?");
                    System.out.print("Player1 > ");
                    try {
                        name = br.readLine();
                    } catch (IOException e) {
                    }
                    if (name.length() == 0) {
                        System.out.println("System > The name can not be empty !");
                    } else {
                        ok4 = true;
                    }
                }
                model.addHumanPlayer(name);
                model.addComputerPlayer("computer1");
                control.setBotDifficulty(askBotDifficulty());
            } else if (mode == 2) {
                model.addComputerPlayer("computer1");
                model.addComputerPlayer("computer2");
            }

            control.setFirstStageName("bataille_navale");
            control.setReader(br);

            try {
                control.startGame();
                control.stageLoop();
            } catch (GameException e) {
            }
            ok = askRematch();
        }
    }
}
