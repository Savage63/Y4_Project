import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

public class User
{
    private JFrame UserPage;
    private JTextField textField;
    private JTable table;

    //Launches the application.
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    User window = new User();
                    window.UserPage.setVisible(true);
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
    }

    //Creates the application.
    public User() 
    {
    	//Creates The GUI Frame
        UserPage = new JFrame();
        UserPage.setTitle("User Page");
        UserPage.setBounds(100, 100, 1189, 551);
        UserPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UserPage.getContentPane().setLayout(null);

        //Creates a Panel in the JFrame
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 108, 512);
        UserPage.getContentPane().add(panel);
        panel.setLayout(null);
        
      //Creates Buttons and Sets Action Listeners to them
	    JButton HomeButton = new JButton("Home");
	    HomeButton.setBounds(10, 27, 89, 23);
	    panel.add(HomeButton);
	    HomeButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	UserPage.dispose(); //Closes the current frame
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
	        	UserPage.dispose(); //Closes the current frame
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
	        	UserPage.dispose(); //Closes the current frame
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
	        	UserPage.dispose(); //Closes the current frame
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
	        	UserPage.dispose(); //Closes the current frame
	            Contacts.main(null); //Opens Contacts.java
	        }
	    });
        
        //Creates a Text Field
        textField = new JTextField();
        textField.setBounds(269, 31, 251, 23);
        UserPage.getContentPane().add(textField);
        textField.setColumns(10);

        //Creates a Drop Box that gives the option 'Permanent' of Temporary'
        JComboBox<Object> comboBox = new JComboBox<Object>();
        comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "Permanent", "Temporary" }));
        comboBox.setBounds(589, 31, 179, 23);
        UserPage.getContentPane().add(comboBox);

        //Creates a JTable that stores the variable in the Text Field and Drop Box
        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] 
            {
            },
            new String[]     
            {
                "Name", "Type", "Button"
            }
        ));
        table.setBounds(118, 65, 1045, 436);
        UserPage.getContentPane().add(table);
        table.setEnabled(false);

        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
      //Read data from myusers.txt and add to the table
      String userHomeDirectory = System.getProperty("user.home");
      String filePath = userHomeDirectory + File.separator + "Project" + File.separator + "myusers.txt";
      File file = new File(filePath);

      //Scanner reads the file
      try (Scanner scanner = new Scanner(file)) 
      {
          while (scanner.hasNextLine()) 
          {
        	  //Scanner reads each line of the file and Displays the information into a table.
              String line = scanner.nextLine();
              String[] parts = line.split(",");
              model.addRow(new Object[]{parts[0], parts[1], "Click to Delete User"});
          }
      } 
      catch (FileNotFoundException e) 
      {
          e.printStackTrace();
      }



        // Add mouse listener to the Third cell of each row.
        table.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());

                // Check if third cell is clicked.
                if (column == 2) 
                {
                	// Remove row from table
                	model.removeRow(row);

                	//Update "myusers.txt" to remove the user
                	String userHomeDirectory = System.getProperty("user.home");
                	String filePath = userHomeDirectory + File.separator + "Project" + File.separator + "myusers.txt";
                	File file = new File(filePath);

                	try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) 
                	{
                	    for (int i = 0; i < model.getRowCount(); i++) 
                	    {
                	        bw.write(model.getValueAt(i, 0) + "," + model.getValueAt(i, 1));
                	        bw.newLine();
                	    }
                	} 
                	catch (IOException e1) 
                	{
                	    e1.printStackTrace();
                	}

                }
            }
        });

        //Adds a button to Add a user into the table.
        JButton btnNewButton = new JButton("Add User");
        btnNewButton.setBounds(819, 31, 125, 23);
        UserPage.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        	    String name = textField.getText(); //Gets the test from the text field.
        	    if (name.isEmpty()) 
        	    {
        	        //When the button is pressed it will check if the Text Field has any text in it and if no text is found, it will tell the user to enter a name.
        	        JOptionPane.showMessageDialog(UserPage, "Please enter a name");
        	    } 
        	    else 
        	    {
        	        //Takes the Text and the Drop Box and stores their values
        	        String type = comboBox.getSelectedItem().toString();
        	        model.addRow(new Object[] { name, type });

        	        //Creates folder in home directory if the folder doesn't already exist
        	        String userHomeDirectory = System.getProperty("user.home");
        	        String folderPath = userHomeDirectory + "/Project";
        	        File folder = new File(folderPath);
        	        
        	        if (!folder.exists()) 
        	        {
        	            folder.mkdir(); // creates the folder if it doesn't already exist
        	        }
        	        
        	        //Creates "myusers.txt" in the folder called Project in the home directory
        	        String filePath = folderPath + "/myusers.txt";
        	        File file = new File(filePath);

        	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) 
        	        {
        	            bw.write(name + "," + type);
        	            bw.newLine();
        	        } 
        	        catch (IOException e1) 
        	        {
        	            e1.printStackTrace();
        	        }


        	        //Clear text field and update table
        	        textField.setText("");
        	        table.setModel(model);
        	        UserPage.dispose(); //Closes the current frame
        	        User.main(null); //Opens User.java
        	    }
        	}

        });
    }
}
