import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGuesserGameGui extends JFrame {
    private int randomNumber;
    private int triesLeft = 10;
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
        setSize(300, 200);
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
        randomNumber = new Random().nextInt(100) + 1;
        messageLabel.setText("Guess a number between 1 and 100!");
        triesLeft = 10;
        inputField.setEnabled(true);
        guessButton.setEnabled(true);
        inputField.setText("");
    }

    private void makeGuess() {
        try {
            int guess = Integer.parseInt(inputField.getText());
            triesLeft--;
            if (guess == randomNumber) {
                messageLabel.setText("You won! The number was " + randomNumber);
                endGame();
            } else if (triesLeft == 0) {
                messageLabel.setText("Game Over! The number was " + randomNumber);
                endGame();
            } else {
                messageLabel.setText((guess < randomNumber ? "Try higher! " : "Try lower! ") + "Tries left: " + triesLeft);
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
