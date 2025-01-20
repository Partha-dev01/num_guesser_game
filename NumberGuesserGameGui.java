import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGuesserGameGui extends JFrame {
    private int randomNumber;
    private int triesLeft = 5;
    private JTextField inputField;
    private JLabel messageLabel;
    private JButton guessButton;

    public NumberGuesserGameGui() {
        setTitle("Number Guesser");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        randomNumber = new Random().nextInt(100) + 1;
        messageLabel = new JLabel("Guess a number between 1 and 100!");
        inputField = new JTextField(10);
        guessButton = new JButton("Guess");

        add(messageLabel);
        add(inputField);
        add(guessButton);
        guessButton.addActionListener(e -> makeGuess());
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
                String message = guess < randomNumber ? "Try higher! " : "Try lower! ";
                messageLabel.setText(message + "Tries left: " + triesLeft);
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number!");
        }
        inputField.setText("");
    }

    private void endGame() {
        inputField.setEnabled(false);
        guessButton.setEnabled(false);
    }

    public static void main(String[] args) {
        new NumberGuesserGameGui().setVisible(true);
    }
}
