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

    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    // Create an instance of the Scan class and display the GUI
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

    public Scan() 
    {
        // Initialize the main frame
        ScanPage = new JFrame();
        ScanPage.setTitle("Scanner Page");
        ScanPage.setBounds(100, 100, 1189, 551);
        ScanPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ScanPage.getContentPane().setLayout(null);
	    ScanPage.setResizable(false);

        // Create and configure a button to initiate network scanning
        JButton ScanNetworkButton = new JButton("Scan Network");
        ScanNetworkButton.setBounds(406, 149, 400, 166);
        ScanPage.getContentPane().add(ScanNetworkButton);
        ScanNetworkButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                    // Get local IP address and create a network address range for scanning
                    InetAddress localhost = InetAddress.getLocalHost();
                    String localIpAddress = localhost.getHostAddress(); //Gets the Local IP Address of device eg. 192.169.1.2
                    String networkAddress = localIpAddress.substring(0, localIpAddress.lastIndexOf(".")) + ".0/24"; //changes everything  after last . to .0/24 eg. 192.168.1.2 -> 192.168.1.0/24

                    // Build and execute the appropriate nmap command based on the operating system
                    String osName = System.getProperty("os.name").toLowerCase();
                    ProcessBuilder pb;
                    String command;
                    if (osName.contains("win")) //If OS is Windows or not it will execute the OS specific command
                    {
                        command = "nmap -T4 -A -F " + networkAddress; //Sets Nmap Command
                        pb = new ProcessBuilder("cmd", "/c", command); //Executes command with Windows command prompt commands
                    } 
                    else 
                    {
                        command = "nmap -T4 -A -F " + networkAddress; //Sets Nmap Command
                        pb = new ProcessBuilder("bash", "-c", command); //Executes command with Linux command prompt commands
                    }

                    // Create directory and output file to store nmap results
                    File projectDir = new File(System.getProperty("user.home") + File.separator + "Project");
                    projectDir.mkdir();
                    File outputFile = new File(projectDir, "nmap_output.txt");

                    try 
                    {
                        // Delete output file if it already exists
                        if (outputFile.exists()) 
                        {
                            outputFile.delete();
                        }

                        // Redirect nmap output to the output file
                        pb.redirectOutput(ProcessBuilder.Redirect.to(outputFile));

                        // Start nmap scan process and wait for its completion
                        Process process = pb.start();
                        int exitCode = process.waitFor();
                        System.out.println("nmap scan finished with exit code " + exitCode);

                        // Close the current frame and open the Result window
                        ScanPage.dispose();
                        Result.main(null);
                    } 
                    catch (Exception e1) 
                    {
                        e1.printStackTrace();
                    }
                } 
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                }
            }
        });

        // Creates Panel holding the buttons
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 108, 512);
        ScanPage.getContentPane().add(panel);
        panel.setLayout(null);
        
        //Creates Buttons and Sets Action Listeners to them
	    JButton HomeButton = new JButton("Home");
	    HomeButton.setBounds(10, 27, 89, 23);
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
	    ScanButton.setBounds(10, 61, 89, 23);
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
	    ResultButton.setBounds(10, 95, 89, 23);
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
	    UserButton.setBounds(10, 129, 89, 23);
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
	    ContactButton.setBounds(10, 423, 89, 23);
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
        editorPane.setBounds(108, 0, 1065, 512);
        ScanPage.getContentPane().add(editorPane);
    }
}
