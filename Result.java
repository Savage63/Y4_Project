import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Result 
{
	private JFrame frame;


	//Launch the application.
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Result window = new Result();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

 	//Create the application.
	public Result() 
	{ 
		
	    frame = new JFrame();
	    frame.setBounds(100, 100, 799, 401);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);

	    // Add a JScrollPane to the content pane
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(107, 11, 676, 351);
	    frame.getContentPane().add(scrollPane);

	    // Create a new JTextArea and add it to the JScrollPane
	    JTextArea txtrWelcomeToThe = new JTextArea();
	    txtrWelcomeToThe.setEditable(false);
	    scrollPane.setViewportView(txtrWelcomeToThe);

	    // Call the displayText() method to set the text of the JTextArea
	    displayText(txtrWelcomeToThe);

	    // Call the displayText() method to set the text of the JTextArea
	    displayText(txtrWelcomeToThe);

	    JPanel panel = new JPanel();
	    panel.setBounds(0, 0, 108, 362);
	    frame.getContentPane().add(panel);
	    panel.setLayout(null);

	    JButton HomeButton = new JButton("Home");
	    HomeButton.setBounds(10, 11, 89, 23);
	    panel.add(HomeButton);
	    HomeButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	frame.dispose(); // Dispose the current frame
	        	Home.main(null);
	        }
	    });

	    JButton ScanButton = new JButton("Scan");
	    ScanButton.setBounds(10, 45, 89, 23);
	    panel.add(ScanButton);
	    ScanButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	frame.dispose(); // Dispose the current frame
	            Scan.main(null);
	        }
	    });

	    JButton ResultButton = new JButton("Results");
	    ResultButton.setBounds(10, 79, 89, 23);
	    panel.add(ResultButton);
	    ResultButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	frame.dispose(); // Dispose the current frame
	            Result.main(null);
	        }
	    });

	    JButton UserButton = new JButton("User");
	    UserButton.setBounds(10, 113, 89, 23);
	    panel.add(UserButton);
	    UserButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	frame.dispose(); // Dispose the current frame
	            User.main(null);
	        }
	    });
	    
	    JButton ContactButton = new JButton("Contact");
	    ContactButton.setBounds(10, 257, 89, 23);
	    panel.add(ContactButton);
	    ContactButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	frame.dispose(); // Dispose the current frame
	            Contacts.main(null);
	        }
	    });
	}
	
	private void displayText(JTextArea textArea) {
	    // create a File object that represents the file to be read
	    File file = new File("nmap_output.txt");

	    // create a StringBuilder object to store the contents of the file
	    StringBuilder sb = new StringBuilder();

	    try {
	        // create a BufferedReader object to read the file
	        BufferedReader reader = new BufferedReader(new FileReader(file));
	        
	        // read the contents of the file line by line and append it to the StringBuilder object
	        String line;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line).append("\n");
	        }
	        
	        // close the reader
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // set the text of the JTextArea to the contents of the file
	    textArea.setText(sb.toString());
	}


	public Window getFrame() 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
