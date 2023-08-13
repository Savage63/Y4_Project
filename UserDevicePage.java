import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDevicePage {
    private static JFrame frame;
    private static JComboBox<String> userTypeComboBox;

    public static void openUserDevicePage(String userName, String userType) {
        if (frame != null && frame.isVisible()) {
            frame.toFront();
        } else {
            frame = new JFrame();
            frame.setTitle("User Details");
            frame.setBounds(100, 100, 400, 350); // Adjusted height for the new components
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel usernameLabel = new JLabel("User Name: " + userName);
            JLabel userTypeLabel = new JLabel("User Type: " + userType);
            topPanel.add(usernameLabel);
            topPanel.add(userTypeLabel);

            userTypeComboBox = new JComboBox<>(new String[]{"Type 1", "Type 2", "Type 3"}); // Example options
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedUserType = (String) userTypeComboBox.getSelectedItem();
                    // Perform actions based on the selected user type
                }
            });

            JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            comboBoxPanel.add(new JLabel("Select User Type:"));
            comboBoxPanel.add(userTypeComboBox);
            comboBoxPanel.add(submitButton);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            panel.add(topPanel, BorderLayout.NORTH);
            panel.add(comboBoxPanel, BorderLayout.CENTER); // Add the new panel with the JComboBox and JButton
            panel.add(new JScrollPane(textArea), BorderLayout.SOUTH); // Adjusted position for the text area

            frame.getContentPane().add(panel);
            frame.setVisible(true);
        }
    }
}
