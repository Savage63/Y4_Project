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

public class Contacts 
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
                    Contacts window = new Contacts();
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
    public Contacts() 
    {
        // Creates The GUI Frame
        Contact = new JFrame();
        Contact.setTitle("Contact Information");
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
        editorPane.setText("<html><body><h2>Contact Details</h2><br>"
                + "Name: Luke Savage<br>"
                + "Student Number: C00248077<br><br>"
                + "Personal Email: <a href=\"mailto:lukesavage6@hotmail.com\">lukesavage6@hotmail.com</a><br>"
                + "College Email: <a href=\"mailto:c00248077@itcarlow.com\">C00248077@itcarlow.com</a><br>"
                + "College: SETU Carlow - Cybercrime & IT Security (Year 4)<br><br>"
                + "LinkedIn: <a href=\"https://www.linkedin.com/in/lukesavage6/\">https://www.linkedin.com/in/lukesavage6/</a><br>"
                + "</body></html>");
    }
}
