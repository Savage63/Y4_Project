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
        String folderPath = System.getProperty("user.home") + File.separator + "Project" + File.separator + "Devices";
        File ipAddressFolder = new File(folderPath, targetIpAddress);
        File portsFile = new File(ipAddressFolder, "EmptyPorts.txt");

        if (!portsFile.exists()) 
        {
            System.out.println("EmptyPorts.txt not found for " + targetIpAddress);
            return;
        }

        List<String> portsToClose = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(portsFile))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                portsToClose.add(line.trim());
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return;
        }

        for (String portString : portsToClose) 
        {
            try 
            {
                int port = Integer.parseInt(portString);

                // Create a ServerSocket to bind to the specified port
                try (ServerSocket serverSocket = new ServerSocket(port)) 
                {
                    // Close the ServerSocket to "close" the port
                    System.out.println("Port " + port + " is now closed");
                } 
                catch (Exception e) 
                {
                    System.out.println("Failed to close port " + port + ": " + e.getMessage());
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid port number: " + portString);
            }
        }
    }

}
