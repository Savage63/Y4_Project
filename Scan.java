import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JEditorPane;

public class Scan 
{
	JFrame frmScannerPage;


	//Launch the application.
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Scan window = new Scan();
					window.frmScannerPage.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

 	//Create the application.
	public Scan() 
	{
	    frmScannerPage = new JFrame();
	    frmScannerPage.setTitle("Scanner Page");
	    frmScannerPage.setBounds(100, 100, 799, 400);
	    frmScannerPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frmScannerPage.getContentPane().setLayout(null);

	    // Add a button to the content pane using absolute layout
	    JButton ScanNetworkButton = new JButton("Scan Network");
	    ScanNetworkButton.setBounds(247, 96, 344, 138);
	    frmScannerPage.getContentPane().add(ScanNetworkButton);
	    ScanNetworkButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	try 
		        {
		            // Build the nmap command with the IP address to scan
		            ProcessBuilder pb = new ProcessBuilder("nmap", "-sS", "-O", "192.168.1.0/24");

		            // Redirect nmap output to a file
		            File outputFile = new File("nmap_output.txt");
		            pb.redirectOutput(ProcessBuilder.Redirect.to(outputFile));

		            // Start the process
		            Process process = pb.start();

		            // Wait for the process to finish
		            int exitCode = process.waitFor();
		            System.out.println("nmap scan finished with exit code " + exitCode);

		            // Open the Results page
		            frmScannerPage.dispose(); // Dispose the current frame
		        	Result.main(null);

		        } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
            }
	        }
	    });

	    JPanel panel = new JPanel();
	    panel.setBounds(0, 0, 108, 362);
	    frmScannerPage.getContentPane().add(panel);
	    panel.setLayout(null);

	    JButton HomeButton = new JButton("Home");
	    HomeButton.setBounds(10, 11, 89, 23);
	    panel.add(HomeButton);
	    HomeButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	frmScannerPage.dispose(); // Dispose the current frame
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
	        	frmScannerPage.dispose(); // Dispose the current frame
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
	        	frmScannerPage.dispose(); // Dispose the current frame
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
	        	frmScannerPage.dispose(); // Dispose the current frame
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
	        	frmScannerPage.dispose(); // Dispose the current frame
	            Contacts.main(null);
	        }
	    });
	    	
	    JEditorPane editorPane = new JEditorPane();
	    editorPane.setEditable(false);
	    editorPane.setBounds(108, 0, 675, 362);
	    frmScannerPage.getContentPane().add(editorPane);
	   
	}
}

