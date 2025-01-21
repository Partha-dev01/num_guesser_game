import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGuesserGameGui extends JFrame {
    private int randomNumber;
    private int triesLeft;
    private int start;
    private int end;
    private static final int DIFF = 10;
    private static final int MAX_TRIES = 15;
    private JTextField inputField;
    private JLabel messageLabel;
    private JButton guessButton;
    private JButton resetButton;

    public NumberGuesserGameGui() {
        setupUI();
        setupGame();
    }

    private void setupUI() {
        setTitle("Number Guesser");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new FlowLayout());
        messageLabel = new JLabel();
        inputField = new JTextField(10);
        guessButton = new JButton("Guess");
        resetButton = new JButton("Reset");

        centerPanel.add(messageLabel);
        centerPanel.add(inputField);
        centerPanel.add(guessButton);
        centerPanel.add(resetButton);

        add(centerPanel, BorderLayout.CENTER);
        add(Box.createVerticalStrut(30), BorderLayout.NORTH);
        addListeners();
        resetButton.setVisible(false);
    }

    private void addListeners() {
        guessButton.addActionListener(e -> makeGuess());
        resetButton.addActionListener(e -> resetGame());
    }

    private void setupGame() {
        Random rand = new Random();
        start = rand.nextInt(1000); 
        end = start + rand.nextInt(5000) + 1000; 
        randomNumber = rand.nextInt(end - start + 1) + start;
        messageLabel.setText("Guess a number between " + start + " and " + end + ". Allowed difference: " + DIFF);
        triesLeft = MAX_TRIES;
        inputField.setEnabled(true);
        guessButton.setEnabled(true);
        inputField.setText("");
        resetButton.setVisible(false);
    }
    

    private void makeGuess() {
        try {
            int guess = Integer.parseInt(inputField.getText());
            triesLeft--;

            int difference = Math.abs(randomNumber - guess);
            if (guess == randomNumber) {
                messageLabel.setText("Congratulations! You've won! Num was " + randomNumber);
                endGame();
            } else if (triesLeft == 0) {
                messageLabel.setText("Game Over! The number was " + randomNumber);
                endGame();
            } else {
                String rangeMessage = (difference > DIFF ? "Outside range." : "Within range.");
                String directionMessage = (guess < randomNumber ? " Try higher." : " Try lower.");
                messageLabel.setText(rangeMessage + directionMessage + " Tries left: " + triesLeft);
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter a valid number!");
        }
        inputField.setText("");
    }

    private void endGame() {
        inputField.setEnabled(false);
        guessButton.setEnabled(false);
        resetButton.setVisible(true);
    }

    private void resetGame() {
        dispose();
        new NumberGuesserGameGui().setVisible(true);
    }

    public static void main(String[] args) {
        new NumberGuesserGameGui().setVisible(true);
    }
}
