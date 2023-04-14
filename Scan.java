import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

public class Scan 
{
    JFrame ScanPage;

    //Launches the application.
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    Scan window = new Scan();
                    window.ScanPage.setVisible(true);
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
    }

    //Creates the application.
    public Scan() 
    {
    	//Creates The GUI Frame
        ScanPage = new JFrame();
        ScanPage.setTitle("Scanner Page");
        ScanPage.setBounds(100, 100, 799, 400);
        ScanPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ScanPage.getContentPane().setLayout(null);

        //Add a button to the content pane using absolute layout
        JButton ScanNetworkButton = new JButton("Scan Network");
        ScanNetworkButton.setBounds(247, 96, 344, 138);
        ScanPage.getContentPane().add(ScanNetworkButton);
        ScanNetworkButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                    // Get the local IP address of the user's machine
                    InetAddress localhost = InetAddress.getLocalHost();
                    String localIpAddress = localhost.getHostAddress();
                    
                    //Changes the last digits in the local IP address to ".0/24" eg. 192.168.1.2 becomes 192.168.1.0/24
                    String networkAddress = localIpAddress.substring(0, localIpAddress.lastIndexOf(".")) + ".0/24";
                    
                    //Builds the Nmap command with the IP address to scan
                    ProcessBuilder pb = new ProcessBuilder("nmap", "-sS", "-O", networkAddress);

                    //Redirects Nmap output to a file called "nmap_output.txt"
                    File outputFile = new File("nmap_output.txt");
                    pb.redirectOutput(ProcessBuilder.Redirect.to(outputFile));

                    //Starts the Nmap process
                    Process process = pb.start();

                    //Waits for the Nmap process to finish
                    int exitCode = process.waitFor();
                    System.out.println("nmap scan finished with exit code " + exitCode);

                    //Opens the Results.java page
                    ScanPage.dispose(); // Dispose the current frame
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
        ScanPage.getContentPane().add(panel);
        panel.setLayout(null);

      //Creates Buttons and Sets Action Listeners to them
        JButton HomeButton = new JButton("Home");
	    HomeButton.setBounds(10, 11, 89, 23);
	    panel.add(HomeButton);
	    HomeButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	ScanPage.dispose(); //Closes the current frame
	        	Home.main(null); //Opens Home.java
	        }
	    });

	    JButton ScanButton = new JButton("Scan");
	    ScanButton.setBounds(10, 45, 89, 23);
	    panel.add(ScanButton);
	    ScanButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	ScanPage.dispose(); //Closes the current frame
	            Scan.main(null); //Opens Scan.java
	        }
	    });

	    JButton ResultButton = new JButton("Results");
	    ResultButton.setBounds(10, 79, 89, 23);
	    panel.add(ResultButton);
	    ResultButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	ScanPage.dispose(); //Closes the current frame
	            Result.main(null); //Opens Result.java
	        }
	    });

	    JButton UserButton = new JButton("User");
	    UserButton.setBounds(10, 113, 89, 23);
	    panel.add(UserButton);
	    UserButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	ScanPage.dispose(); //Closes the current frame
	            User.main(null); //Opens User.java
	        }
	    });
	    
	    JButton ContactButton = new JButton("Contact");
	    ContactButton.setBounds(10, 257, 89, 23);
	    panel.add(ContactButton);
	    ContactButton.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	ScanPage.dispose(); //Closes the current frame
	            Contacts.main(null); //Opens Contacts.java
	        }
	    });

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setBounds(108, 0, 675, 362);
        ScanPage.getContentPane().add(editorPane);
    }
}
