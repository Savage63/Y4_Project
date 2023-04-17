import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

    //Launches the application.
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

    //Creates the application.
    public Result() 
    {
    	//Creates The GUI Frame
        ScanResults = new JFrame();
        ScanResults.setTitle("Scan Results");
        ScanResults.setBounds(100, 100, 1624, 551);
        ScanResults.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ScanResults.getContentPane().setLayout(null);

        //Creates a Panel in the JFrame
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 108, 551);
        ScanResults.getContentPane().add(panel);
        panel.setLayout(null);

      //Creates Buttons and Sets Action Listeners to them
	    JButton HomeButton = new JButton("Home");
	    HomeButton.setBounds(10, 27, 89, 23);
	    panel.add(HomeButton);
	    HomeButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	ScanResults.dispose(); //Closes the current frame
	        	Home.main(null); //Opens Home.java
	        }
	    });

	    JButton ScanButton = new JButton("Scan");
	    ScanButton.setBounds(10, 61, 89, 23);
	    panel.add(ScanButton);
	    ScanButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	ScanResults.dispose(); //Closes the current frame
	            Scan.main(null); //Opens Scan.java
	        }
	    });

	    JButton ResultButton = new JButton("Results");
	    ResultButton.setBounds(10, 95, 89, 23);
	    panel.add(ResultButton);
	    ResultButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	ScanResults.dispose(); //Closes the current frame
	            Result.main(null); //Opens Result.java
	        }
	    });

	    JButton UserButton = new JButton("User");
	    UserButton.setBounds(10, 129, 89, 23);
	    panel.add(UserButton);
	    UserButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	ScanResults.dispose(); //Closes the current frame
	            User.main(null); //Opens User.java
	        }
	    });
	    
	    JButton ContactButton = new JButton("Contact");
	    ContactButton.setBounds(10, 423, 89, 23);
	    panel.add(ContactButton);
	    ContactButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	ScanResults.dispose(); //Closes the current frame
	            Contacts.main(null); //Opens Contacts.java
	        }
	    });
	    


	    String userHomeDirectory = System.getProperty("user.home");
	    String filePath = userHomeDirectory + File.separator + "Project" + File.separator + "nmap_output.txt";
	    File file = new File(filePath);
        //Checks if the file "nmap_output.txt" exists if it exists, it will display the information in the file. If not, it will Ask the user to do a scan
        if (file.exists()) 
        {
            DefaultTableModel model = new DefaultTableModel()
            {
				private static final long serialVersionUID = 1L;

				@Override
                public boolean isCellEditable(int row, int column) 
				{
                    return false; //make cells uneditable
                }
            };
            
            //Sets the column headers of the JTable
            model.addColumn("Device Name");
            model.addColumn("IP Address");
            model.addColumn("MAC Address");
            model.addColumn("OS");
            model.addColumn("Manufacturer");
            
            displayText(model);
            
            //Creates a Scroll Pane
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(109, 0, 1500, 510);
            ScanResults.getContentPane().add(scrollPane);
            JTable table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // set auto resize mode
            table.getTableHeader().setReorderingAllowed(false);
            table.setColumnSelectionAllowed(true);
            table.setEnabled(false);
            table.setCellSelectionEnabled(true);
            table.setShowVerticalLines(false);
            
            //Creates a mouse Listener for when a user clicks on a cell in the Table and opens a new page displaying the information in that Row.
            table.addMouseListener(new MouseAdapter() 
            {
            	public void mouseClicked(MouseEvent e) 
                {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    if (row >= 0 && col >= 0) 
                    {
                        //Get the values in the clicked row
                        Object[] rowData = new Object[table.getColumnCount()];
                        for (int i = 0; i < rowData.length; i++) 
                        {
                            rowData[i] = table.getValueAt(row, i);
                        }

                        //Create a new JFrame to display the information
                        JFrame frame = new JFrame();
                        frame.setTitle("Information for Row " + (row + 1));

                        //Create a JPanel to hold the information
                        JPanel panel = new JPanel(new GridLayout(0, 2));
                        for (int i = 0; i < table.getColumnCount(); i++) 
                        {
                            panel.add(new JLabel(table.getColumnName(i)));
                            panel.add(new JLabel(rowData[i].toString()));
                        }
                        frame.getContentPane().add(panel);
                	    frame.setBounds(100, 100, 100, 100);
                        frame.pack();
                        frame.setVisible(true);
                    }
                }
            });
            
            //Disable column reordering
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.setColumnSelectionAllowed(false);
            columnModel.getColumn(0).setResizable(false);
            columnModel.getColumn(1).setResizable(false);
            columnModel.getColumn(2).setResizable(false);
            
            scrollPane.setViewportView(table);

        }
        //This else statement Prints "Please do a Network Scan" if the nmap_output.txt is not found
        else 
        {
            JTextArea textArea = new JTextArea("Please do a Network Scan");
            textArea.setEditable(false); // Sets the text area to be uneditable
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setBounds(109, 0, 1500, 510);
            ScanResults.getContentPane().add(scrollPane);
        }
    }

    private void displayText(DefaultTableModel model) 
    {
        try 
        {
        	String userHomeDirectory = System.getProperty("user.home");
    	    String filePath = userHomeDirectory + File.separator + "Project" + File.separator + "nmap_output.txt";
    	    File file = new File(filePath);

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
            {
				String line;
				String deviceName = "";
				String ipAddress = "";
				String macAddress = "";
				String osDetails = "";
				String manufacturer = "";
				while ((line = reader.readLine()) != null) 
				{
					//Checks for lines that start with "Nmap scan report for " which is the same line as all of the Device Names in the "nmap_output.txt" file.
				    if (line.startsWith("Nmap scan report for ")) 
				    {
				        if (!deviceName.isEmpty()) 
				        {
				            //Removes the text within brackets from the MAC Address column and add it to the Manufacturer column e.g 00:F3:61:A3:E4:B4(Amazon Technologies)
				            int startIndex = macAddress.indexOf("(");
				            int endIndex = macAddress.indexOf(")");
				            if (startIndex != -1 && endIndex != -1) 
				            {
				                manufacturer = macAddress.substring(startIndex + 1, endIndex);
				                macAddress = macAddress.substring(0, startIndex).trim();
				            }
				            model.addRow(new Object[] { deviceName, ipAddress, macAddress, osDetails, manufacturer });
				        }
				        
				        //Removes the text within brackets from the Device Name column and add it to the IP Address column e.g Samsung-S20.station(192.168.1.4)
				        deviceName = line.substring(21);
				        int startIndex = deviceName.indexOf("(");
				        int endIndex = deviceName.indexOf(")");
				        if (startIndex != -1 && endIndex != -1) 
				        {
				            ipAddress = deviceName.substring(startIndex + 1, endIndex);
				            deviceName = deviceName.substring(0, startIndex).trim();
				        } 
				        //If no brackets are found (No Device Name is available) it sets the IP Address to the same Variable
				        else 
				        {
				            ipAddress = deviceName;
				        }
				        
				        macAddress = "";
				        osDetails = "";
				        manufacturer = "";
				    } 
				    //Checks for the MAC Address
				    else if (line.startsWith("MAC Address: ")) 
				    {
				        macAddress = line.substring(13);
				    } 
				    //Checks for the Operating System of the Device
				    else if (line.startsWith("OS details: ")) 
				    {
				        osDetails = line.substring(12);
				    }
				    
				}
				//Adds the Device that performs the scan to the table
				if (!deviceName.isEmpty()) 
				{
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
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
