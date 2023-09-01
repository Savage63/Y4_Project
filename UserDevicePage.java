import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserDevicePage 
{
    private JFrame devicePage;
    private JComboBox<String> comboBox;
    private JTable table;
    private JTextField userField;

    // Updated DeviceTableModel
    private class DeviceTableModel extends DefaultTableModel 
    {
        private static final long serialVersionUID = 1L;

        DeviceTableModel(Object[][] data, String[] columns) 
        {
            super(data, columns);
        }

        @Override
        public boolean isCellEditable(int row, int column) 
        {
            return false; // Set all cells as non-editable
        }
    }

    public static void openUserDevicePage(String userName, String userType) 
    {
        EventQueue.invokeLater(() -> 
        {
            try 
            {
                UserDevicePage window = new UserDevicePage(userName, userType);
                window.devicePage.setVisible(true);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        });
    }

    public UserDevicePage(String userName, String userType) 
    {
    	//Initialize the main JFrame for the User page
        devicePage = new JFrame();
        devicePage.setTitle("User Device Page");
        devicePage.setBounds(100, 100, 834, 366);
        devicePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        devicePage.getContentPane().setLayout(null);
        devicePage.setResizable(false);

        //Sets text field for User Name and User Type
        userField = new JTextField();
        userField.setEditable(false);
        userField.setHorizontalAlignment(JTextField.CENTER); // Center align the text
        userField.setBounds(10, 10, 797, 23); // Adjust the width to fit the frame
        userField.setText("User: " + userName + " | Type: " + userType);
        devicePage.getContentPane().add(userField);

        //Initialize the Dropbox
        comboBox = new JComboBox<>();
        comboBox.setBounds(150, 47, 250, 23);
        devicePage.getContentPane().add(comboBox);

        //Adds the text in the Dropbox to the JTable
        JButton actionButton = new JButton("Add Device");
        actionButton.setBounds(450, 47, 179, 23);
        devicePage.getContentPane().add(actionButton);
        actionButton.addActionListener(e -> 
        {
            String selectedItem = (String) comboBox.getSelectedItem();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Check if the device is already in the table
            boolean deviceExists = false;
            for (int row = 0; row < model.getRowCount(); row++) 
            {
                String deviceName = (String) model.getValueAt(row, 0);
                if (deviceName.equals(selectedItem)) 
                {
                    deviceExists = true;
                    break;
                }
            }
            if (!deviceExists) 
            {
                model.addRow(new Object[]{selectedItem, "Remove Device"});
                // Save the updated devices to the file
                saveDevicesToFile(userName, model);
            }
        });

        //Initializes the JTable with added devices
        table = new JTable();
        table.setModel(new DeviceTableModel(
            new Object[][] {},
            new String[] {"Device", "Remove Device"}
        ));
        table.setBounds(10, 85, 796, 230);
        devicePage.getContentPane().add(table);

        //Adds mouse listener to table cell to delete from table
        table.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());

                if (column == 1) 
                {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(row);
                    // Update file or perform any other necessary actions here
                    saveDevicesToFile(userName, model); // Update the file after removing a device
                }
            }
        });

        try 
        {
        	//Reads scan results for list of devices
            String homeDirectory = System.getProperty("user.home");
            String filePath = homeDirectory + File.separator + "Project" +  File.separator + "nmap_output.txt"; //reads the nmap_output.txt file
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) 
            {
                if (line.startsWith("Nmap scan report for ")) 
                {
                    String deviceName = line.substring("Nmap scan report for ".length()).trim(); //Checks all mentions of "Nmap scan report for " to get all device names
                    comboBox.addItem(deviceName);
                }
            }
            reader.close();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        
        try 
        {
        	//Creates a file to save the devices added to each user
            String homeDirectory = System.getProperty("user.home");
            String folderPath = homeDirectory + File.separator + "Project" + File.separator + "Users" + File.separator + userName;
            String filePath = folderPath + File.separator + "UserDevices.txt";

            File file = new File(filePath);
            if (file.exists()) 
            {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;
                while ((line = reader.readLine()) != null) 
                {
                    if (line.startsWith("Device: ")) 
                    {
                        String deviceName = line.substring("Device: ".length());
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.addRow(new Object[]{deviceName, "Remove Device"});
                    }
                }
                reader.close();
            }
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }

    private void saveDevicesToFile(String userName, DefaultTableModel model) 
    {
        try 
        {
            String homeDirectory = System.getProperty("user.home");
            String folderPath = homeDirectory + File.separator + "Project" +  File.separator + "Users" +File.separator + userName; // Create a folder with the user's username
            String filePath = folderPath + File.separator + "UserDevices.txt";
        
            File folder = new File(folderPath);
            folder.mkdirs(); // Create the folder if it doesn't exist
        
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        
            for (int row = 0; row < model.getRowCount(); row++) 
            {
                String deviceName = (String) model.getValueAt(row, 0);
                writer.write("Device: " + deviceName);
                writer.newLine();
            }
        
            writer.close();
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
}
