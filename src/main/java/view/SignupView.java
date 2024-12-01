package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;

/**
 * View for the signup process.
 */
public class SignupView extends JPanel {
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final SignupController signupController;

    public SignupView(SignupController signupController, SignupViewModel signupViewModel) {
        this.signupController = signupController;
        signupViewModel.addPropertyChangeListener(evt -> {
            final String errorMessage = (String) evt.getNewValue();
            if (errorMessage != null) {
                JOptionPane.showMessageDialog(this, errorMessage);
            }
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Username:"));
        add(usernameInputField);
        add(new JLabel("Password:"));
        add(passwordInputField);
        add(new JLabel("Repeat Password:"));
        add(repeatPasswordInputField);

        final JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signupController.execute(
                        usernameInputField.getText(),
                        new String(passwordInputField.getPassword()),
                        new String(repeatPasswordInputField.getPassword())
                );
            }
        });

        add(signUpButton);
    }
}
