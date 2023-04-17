import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Contacts 
{
	private JFrame Contact;

	//Launches the application.
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

 	//Creates the application.
	public Contacts() 
	{ 
		//Creates The GUI Frame
	    Contact = new JFrame();
	    Contact.setTitle("Contact Information");
	    Contact.setBounds(100, 100, 1189, 551);
	    Contact.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Contact.getContentPane().setLayout(null);

	    //Adds a JScrollPane to the GUI Frame
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(107, 11, 1066, 501);
	    Contact.getContentPane().add(scrollPane);

	    //Creates a new JTextArea and adds it to the JScrollPane
	    JTextArea txtrWelcomeToThe = new JTextArea();
	    txtrWelcomeToThe.setEditable(false);
	    scrollPane.setViewportView(txtrWelcomeToThe);

	    //Calls the displayText() method to set the text in the JTextArea
	    displayText(txtrWelcomeToThe);

	    //Creates a Panel in the JFrame
	    JPanel panel = new JPanel();
	    panel.setBounds(0, 0, 108, 512);
	    Contact.getContentPane().add(panel);
	    panel.setLayout(null);

	    //Creates Buttons and Sets Action Listeners to them
	    JButton HomeButton = new JButton("Home");
	    HomeButton.setBounds(10, 27, 89, 23);
	    panel.add(HomeButton);
	    HomeButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	Contact.dispose(); //Closes the current frame
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
	        	Contact.dispose(); //Closes the current frame
	            Contacts.main(null); //Opens Contacts.java
	        }
	    });
	}
	
	private void displayText(JTextArea textArea) 
	{
	    
		//Sets the text Displayed in the JTextArea
	    textArea.setText("\t\t         			Contact Details\r\n\r\n\r\n\r\n   "
	    		
						+ "Name: Luke Savage \r\n   "
	    				+ "Student Number: C00248077 \r\n\r\n\r\n   "
						
	    				+ "Personal Email: lukesavage6@hotmail.com \r\n   "
	    				+ "College Email: C00248077@itcarlow.ie \r\n\r\n\r\n   "
	    				+ "College: SETU Carlow - Cybercrime & IT Security (Year 4)  \r\n\r\n\r\n   "
	    				
	    				+ "LinkedIn: https://www.linkedin.com/in/lukesavage6/\n\n\n");
	}
}
