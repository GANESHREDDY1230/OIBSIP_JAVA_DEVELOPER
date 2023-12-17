import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ATMInterface extends JFrame {

    private JTextField amountField;
    private JTextArea outputArea;

    private double balance = 1000.0;

    public ATMInterface() {
        super("ATM Interface");

        // Create components
        amountField = new JTextField(10);
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);

        // Set layout manager
        setLayout(new FlowLayout());

        // Add components to the frame
        add(new JLabel("Enter Amount: "));
        add(amountField);
        add(withdrawButton);
        add(depositButton);
        add(new JScrollPane(outputArea));

        // Register event handlers
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                updateOutput("Withdrawal successful. Balance: $" + balance);
            } else {
                updateOutput("Invalid withdrawal amount.");
            }
        } catch (NumberFormatException ex) {
            updateOutput("Invalid input. Please enter a valid number.");
        }
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                balance += amount;
                updateOutput("Deposit successful. Balance: $" + balance);
            } else {
                updateOutput("Invalid deposit amount.");
            }
        } catch (NumberFormatException ex) {
            updateOutput("Invalid input. Please enter a valid number.");
        }
    }

    private void updateOutput(String message) {
        outputArea.append(message + "\n");
        amountField.setText("");
    }

    public static void main(String[] args) {
        ATMInterface atmInterface = new ATMInterface();
        atmInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        atmInterface.setSize(300, 300);
        atmInterface.setVisible(true);
    }
}
