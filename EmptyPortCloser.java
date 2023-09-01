import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class EmptyPortCloser 
{
    public void closeSpecifiedPorts(String targetIpAddress) 
    {
        // Get the user's home directory and create a path to the "Devices" folder for the target IP address
        String folderPath = System.getProperty("user.home") + File.separator + "Project" + File.separator + "Devices";
        File ipAddressFolder = new File(folderPath, targetIpAddress);
        
        // Create a File object for the "EmptyPorts.txt" file within the target IP address folder
        File portsFile = new File(ipAddressFolder, "EmptyPorts.txt");

        // Check if the "EmptyPorts.txt" file exists for the target IP address
        if (!portsFile.exists()) 
        {
            System.out.println("EmptyPorts.txt not found for " + targetIpAddress);
            return;
        }

        // Create a list to store port numbers to be closed
        List<String> portsToClose = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(portsFile))) 
        {
            String line;
            // Read each line from the "EmptyPorts.txt" file and add it to the list of ports to be closed
            while ((line = reader.readLine()) != null) 
            {
                portsToClose.add(line.trim());
            }
        } 
        catch (IOException e) 
        {
            // Print the stack trace if an IOException occurs (e.g., file reading error)
            e.printStackTrace();
            return;
        }

        // Iterate through each port to be closed
        for (String portString : portsToClose) 
        {
            try 
            {
                // Convert the port string to an integer
                int port = Integer.parseInt(portString);

                // Create a ServerSocket to bind to the specified port
                try (ServerSocket serverSocket = new ServerSocket(port)) 
                {
                    // Close the ServerSocket to "close" the port
                    System.out.println("Port " + port + " is now closed");
                } 
                catch (Exception e) 
                {
                    // Print an error message if the ServerSocket couldn't be created or closed
                    System.out.println("Failed to close port " + port + ": " + e.getMessage());
                }
            } 
            catch (NumberFormatException e) 
            {
                // Print an error message if the port string is not a valid number
                System.out.println("Invalid port number: " + portString);
            }
        }
    }
}
