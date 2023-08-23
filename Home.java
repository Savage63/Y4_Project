import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Home 
{
	private JFrame HomePage;


	//Launches the application.
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Home window = new Home();
					window.HomePage.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

 	//Creates the application.
	public Home() 
	{ 
		//Create The GUI Frame
	    HomePage = new JFrame();
	    HomePage.setTitle("Home Page");
	    HomePage.setBounds(100, 100, 1189, 551);
	    HomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    HomePage.getContentPane().setLayout(null);
	    HomePage.setResizable(false); // Make the frame not resizable

	    //Adds a JScrollPane to the GUI Frame
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(107, 11, 1066, 501);
	    HomePage.getContentPane().add(scrollPane);

	    //Creates a new JTextArea and adds it to the JScrollPane
	    JTextArea HomeText = new JTextArea();
	    HomeText.setEditable(false);
	    scrollPane.setViewportView(HomeText);

	    //Calls the displayText() method to set the text in the JTextArea
	    displayText(HomeText);

	    //Creates a Panel in the JFrame
	    JPanel panel = new JPanel();
	    panel.setBounds(0, 0, 108, 512);
	    HomePage.getContentPane().add(panel);
	    panel.setLayout(null);

	    //Creates Buttons and Sets Action Listeners to them
	    JButton HomeButton = new JButton("Home");
	    HomeButton.setBounds(10, 27, 89, 23);
	    panel.add(HomeButton);
	    HomeButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	HomePage.dispose(); //Closes the current frame
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
	        	HomePage.dispose(); //Closes the current frame
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
	        	HomePage.dispose(); //Closes the current frame
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
	        	HomePage.dispose(); //Closes the current frame
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
	        	HomePage.dispose(); //Closes the current frame
	            Contacts.main(null); //Opens Contacts.java
	        }
	    });
	}
	
	private void displayText(JTextArea textArea)
	{
	    
		//Sets the text Displayed in the JTextArea
	    textArea.setText("\t\t         			Welcome to the Home Page!\r\n\r\n\r\n\r\n  "
	    		
	    				+ "The aim for this Application is to provide our Users with a way to Scan their Network, and keep track of the devices and users that may be connected. "
	    				+ "The application also displays the information found from the performed scan and allows the user to view each device and its necessary information. \r\n  "
	    				+ "The hope is to allow the User to have their own form of Home Network Security \r\n\r\n\r\n  "
	    				
						+ "The design and layout of the project is to allow the User to navigate easily and without concern. \r\n  "
						+ "The only requirement for this project is to install Nmap. It can be installed at https://nmap.org/download.html \r\n\r\n\r\n\r\n  "
						
						+ "The functions for the buttons on the side are as follows: \r\n\r\n  "
	    				
	    				+ "When on a different Page, the 'Home' button will bring you back to this page. \r\n\r\n  "
	    				
	    				+ "The 'Scan' button brings you to a separate page that when the main 'Scan Network' button is pressed, it will use Nmap (A Network Scanner). "
	    				+ "This will perform a scan on your entire network by taking your devices local IP. This scan performs best when all devices in the household is powered on before commencing the scan.\r\n  "
	    				+ "Once the scan is finished it will open another page that will show you the results of any devices found in the scan and the information found for each device. "
	    				
	    				+ "The issue with Nmap is that it may not be very accurate so apologies if there is missing information.\r\n\r\n\r\n  "
	    				
	    				+ "The 'Results' button brings you to a seperate page that displays the results performed by the Scan. If there was no scan performed, the Results tab will prompt the user to perform a Scan before viewing results.\r\n\r\n\r\n  "
	    				
	    				+ "The 'Users' Button will allow you to keep track of the users that you have on the network. You will be able to create a user so that it is saved and recoded. You will then be able to mark each user as Permanent or Temporary,\r\n  "
	    				+ "depending on whether they are visiting or living in the household. It is advisable to keep this record updated as it can help secure your Network and keep track of its users.\r\n\r\n\r\n  "
	    				
	    				+ "The 'Contact' Button will show you the contact information for the Developer. If you have any issues or queries, please get in contact.\r\n");
	}
}
