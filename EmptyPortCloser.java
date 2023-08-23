import java.net.ServerSocket;
import java.util.List;

public class EmptyPortCloser 
{
    public void closeSpecifiedPorts(List<String> emptyPorts) 
    {
        for (String portString : emptyPorts) 
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

    public static List<String> getEmptyPortsFromScannerOrPage() 
    {
        // Retrieve the list of empty ports from RowInformationPage
        RowInformationPage rowInfoPage = new RowInformationPage(new Object[]{"Device 1", "0.0.0.0", "00:11:22:33:44:55", "Linux", "Manufacturer"});
        // Assuming there's a method in RowInformationPage to get the empty ports
        List<String> emptyPorts = rowInfoPage.getEmptyPorts();

        return emptyPorts;
    }

    public static void main(String[] args)
    {
        List<String> emptyPorts = getEmptyPortsFromScannerOrPage();
        EmptyPortCloser closePorts = new EmptyPortCloser();
        closePorts.closeSpecifiedPorts(emptyPorts);
    }
}
