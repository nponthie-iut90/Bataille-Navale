package control;

/*public class Cai extends Decider {
    int player = 0;

    public int defineCurrentPlayer() {
        if (model.getIdPlayer() == 0) {
            player = 1;
        } else {
            player = 2;
        }
        return player;
    }

    StageModel stageModel = (StageModel) model.getGameStage();

    public Cai(Model model, Controller control) {
        super(model, control);
    }

    public boolean generateRandomLetterHV() {
        Random rand = new Random();
        int num = rand.nextInt(2);

        if (num == 0) {
            return true;
        } else {
            return false;
        }
    }

    public char generateRandomLetter() {
        int num = generateRandomNumber();
        char letter = (char) ('A' + num); // converts the number to a letter between 'A' and 'J'

        return letter;
    }

    public int generateRandomNumber() {
        Random rand = new Random();
        int num = rand.nextInt(10); // generates a random number between 0 and 9

        return num;
    }

    public String generateLine() {
        boolean letterHV = generateRandomLetterHV();
        char letterAJ = generateRandomLetter();
        int number = generateRandomNumber();

        return "" + letterHV + letterAJ + number;
    }

    @Override
    public ActionList decide() {
        GameController gameController = (GameController) control;
        int shipToPlace = gameController.botShipIndex;
        player = defineCurrentPlayer();
        Ship ship = stageModel.getPlayer1ShipsV1().get(shipToPlace);
        int H = generateRandomNumber();
        int A = generateRandomNumber();
        stageModel.calcValideCells(player);
        while (! stageModel.isShipWillBeInBoardAndLegal(generateRandomLetterHV(), H, A, shipToPlace, 1, player)) {
            System.out.println("pas ok");
            H = generateRandomNumber();
            A = generateRandomNumber();
        }
        //ActionList actionList = ActionFactory.generatePutInContainer(model, ship, "bataille navale", H, A);
        //actionList.setDoEndOfTurn(true);
        return new ActionList();
    }
}*/