import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class Home 
{
    private JFrame Contact;

    // Launches the application.
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    Home window = new Home();
                    window.Contact.setVisible(true);
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
    }

    // Creates the application.
    public Home() 
    {
        // Creates The GUI Frame
        Contact = new JFrame();
        Contact.setTitle("Home Page");
        Contact.setBounds(100, 100, 1189, 551);
        Contact.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Contact.getContentPane().setLayout(null);
	    Contact.setResizable(false); // Make the frame not resizable

        // Adds a JScrollPane to the GUI Frame
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(107, 11, 1066, 501);
        Contact.getContentPane().add(scrollPane);

        // Creates a new JEditorPane and adds it to the JScrollPane
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setContentType("text/html"); // Set content type to HTML
        scrollPane.setViewportView(editorPane);

        // Add a HyperlinkListener to open links in a web browser
        editorPane.addHyperlinkListener(new HyperlinkListener() 
        {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) 
            {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) 
                {
                    try 
                    {
                        java.awt.Desktop.getDesktop().browse(e.getURL().toURI());
                    } 
                    catch (Exception ex) 
                    {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Calls the displayText() method to set the HTML content in the JEditorPane
        displayText(editorPane);

        // Creates a Panel in the JFrame
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 108, 512);
        Contact.getContentPane().add(panel);
        panel.setLayout(null);

        // Creates Buttons and Sets Action Listeners to them
        JButton HomeButton = new JButton("Home");
        HomeButton.setBounds(10, 27, 89, 23);
        panel.add(HomeButton);
        HomeButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                Contact.dispose(); // Closes the current frame
                Home.main(null); // Opens Home.java
            }
        });

	    JButton ScanButton = new JButton("Scan");
	    ScanButton.setBounds(10, 61, 89, 23);
	    panel.add(ScanButton);
	    ScanButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	Contact.dispose(); //Closes the current frame
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
	        	Contact.dispose(); //Closes the current frame
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
	        	Contact.dispose(); //Closes the current frame
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
                Contact.dispose(); // Closes the current frame
                Contacts.main(null); // Opens Contacts.java
            }
        });
    }

    private void displayText(JEditorPane editorPane) 
    {
        // Sets the HTML content with mailto link
        editorPane.setText("<html>"
                + "<h1>Welcome to the Home Page!</h1><br>"
                + "<h2>About the Application:</h2>"
                + "<p>The aim for this Application is to provide our Users with a way to Scan their Network, and keep track of the devices and users that may be connected. "
                + "The application also displays the information found from the performed scan and allows the user to view each device and its necessary information.</p>"
                + "<p>The hope is to allow the User to have their own form of Home Network Security.</p><br>"
                
                + "<h2>Design and Layout:</h2>"
                + "<p>The design and layout of the project is to allow the User to navigate easily and without concern. "
                + "The only requirement for this project is to install Nmap. It can be installed at <a href=\"https://nmap.org/download.html\">https://nmap.org/download.html</a>.</p><br>"
                + "<p>The issue with Nmap is that it may not be very accurate, so apologies if there is missing information.</p><br>"
                
                + "<h2>Button Functions:</h2>"
                + "<p><strong>Home Button:</strong> When on a different Page, the 'Home' button will bring you back to this page.</p>"
                + "<p><strong>Scan Button:</strong> Brings you to a separate page where pressing the main 'Scan Network' button will use Nmap (A Network Scanner). "
                + "This will perform a scan on your entire network by taking your devices local IP. This scan performs best when all devices in the household are powered on before commencing the scan."
                + "Once the scan is finished, it will open another page that will show you the results of any devices found in the scan and the information found for each device.</p><br>"
                
                + "<p><strong>Results Button:</strong> Brings you to a separate page that displays the results performed by the Scan. If no scan was performed, the Results tab will prompt the user to perform a Scan before viewing results.</p><br>"
                
                + "<p><strong>User Button:</strong> Allows you to keep track of the users on the network. You can create a user so that it is saved and recorded. You can mark each user as Permanent or Temporary, "
                + "depending on whether they are visiting or living in the household. It is advisable to keep this record updated to help secure your Network and keep track of its users. You can also assign each user a device found in the scan to keep track of each users devices.</p><br>"
                
                + "<p><strong>Contact Button:</strong> Shows you the contact information for the Developer. If you have any issues or queries, please get in contact.</p><br>"
                + "</html>");
    }
}
