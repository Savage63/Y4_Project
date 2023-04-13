import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Result 
{
    private JFrame ScanResults;

 // Launch the application.
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    Result window = new Result();
                    window.ScanResults.setVisible(true);
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
    }

    // Create the application.
    public Result() 
    {
        ScanResults = new JFrame();
        ScanResults.setTitle("Scan Results");
        ScanResults.setBounds(100, 100, 1624, 400);
        ScanResults.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ScanResults.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 108, 361);
        ScanResults.getContentPane().add(panel);
        panel.setLayout(null);

        JButton HomeButton = new JButton("Home");
        HomeButton.setBounds(10, 11, 89, 23);
        panel.add(HomeButton);
        

        // Call displayText() method here
        File file = new File("nmap_output.txt");
        if (file.exists()) 
        {
            DefaultTableModel model = new DefaultTableModel()
            {
				private static final long serialVersionUID = 1L;

				@Override
                public boolean isCellEditable(int row, int column) 
				{
                    return false; // make cells uneditable
                }
            };
            
            // Set the column headers of the JTable
            model.addColumn("Device Name");
            model.addColumn("IP Address");
            model.addColumn("MAC Address");
            model.addColumn("OS");
            model.addColumn("Manufacturer");

            
            
            displayText(model);
            
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(109, 0, 1500, 400);
            ScanResults.getContentPane().add(scrollPane);
            JTable table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // set auto resize mode
            table.getTableHeader().setReorderingAllowed(false);
            table.setColumnSelectionAllowed(true);
            table.setEnabled(false);
            table.setCellSelectionEnabled(true);
            table.setShowVerticalLines(false);

            table.addMouseListener(new MouseAdapter() 
            {
                public void mouseClicked(MouseEvent e) 
                {
                    // Your code for handling the mouse click goes here
                }
            });

            
            // Disable column reordering
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.setColumnSelectionAllowed(false);
            columnModel.getColumn(0).setResizable(false);
            columnModel.getColumn(1).setResizable(false);
            columnModel.getColumn(2).setResizable(false);
            
            scrollPane.setViewportView(table);


        }
        
        // This else statement Prints "Please do a Network Scan" if the nmap_output.txt is not found
        else 
        {
            JTextArea textArea = new JTextArea("Please do a Network Scan");
            textArea.setEditable(false); // Sets the text area to be uneditable
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setBounds(108, 0, 928, 400);
            ScanResults.getContentPane().add(scrollPane);
        }
    }

    private void displayText(DefaultTableModel model) {
        try {
            File file = new File("nmap_output.txt");

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line;
				String deviceName = "";
				String ipAddress = "";
				String macAddress = "";
				String osDetails = "";
				String manufacturer = "";
				while ((line = reader.readLine()) != null) 
				{
				    if (line.startsWith("Nmap scan report for ")) 
				    {
				        if (!deviceName.isEmpty()) 
				        {
				            // remove the text within brackets from the MAC Address column and add it to the Manufacturer column e.g 00:F3:61:A3:E4:B4(Amazon Technologies)
				            int startIndex = macAddress.indexOf("(");
				            int endIndex = macAddress.indexOf(")");
				            if (startIndex != -1 && endIndex != -1) 
				            {
				                manufacturer = macAddress.substring(startIndex + 1, endIndex);
				                macAddress = macAddress.substring(0, startIndex).trim();
				            }
				            model.addRow(new Object[] { deviceName, ipAddress, macAddress, osDetails, manufacturer });
				        }
				        
				        // remove the text within brackets from the Device Name column and add it to the IP Address column e.g Samsung-S20.station(192.168.1.4)
				        deviceName = line.substring(21);
				        int startIndex = deviceName.indexOf("(");
				        int endIndex = deviceName.indexOf(")");
				        if (startIndex != -1 && endIndex != -1) 
				        {
				            ipAddress = deviceName.substring(startIndex + 1, endIndex);
				            deviceName = deviceName.substring(0, startIndex).trim();
				        } 
				        //If no brackets are found (No device is available) It sets the IP Address to the same Variable
				        else 
				        {
				            ipAddress = deviceName;
				        }
				        
				        macAddress = "";
				        osDetails = "";
				        manufacturer = "";
				    } 
				    else if (line.startsWith("MAC Address: ")) 
				    {
				        macAddress = line.substring(13);
				    } 
				    else if (line.startsWith("OS details: ")) 
				    {
				        osDetails = line.substring(12);
				    }
				    
				}
				//Add the Device that performs the scan to the table
				if (!deviceName.isEmpty()) 
				{
					// remove the text within brackets from the MAC Address column and add it to the Manufacturer column e.g 00:F3:61:A3:E4:B4(Amazon Technologies)
				    int startIndex = macAddress.indexOf("(");
				    int endIndex = macAddress.indexOf(")");
				    if (startIndex != -1 && endIndex != -1) 
				    {
				        manufacturer = macAddress.substring(startIndex + 1, endIndex);
				        macAddress = macAddress.substring(0, startIndex).trim();
				    }
				    model.addRow(new Object[] { deviceName, ipAddress, macAddress, osDetails, manufacturer });
				}
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
