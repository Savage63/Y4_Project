import java.net.ServerSocket;

public class EmptyPortCloser 
{

    public void closeSpecifiedPorts() 
    {
        int[] portsToClose = {80, 443, 8080}; //Ports to close

        for (int port : portsToClose) 
        {
            try 
            {
                // Create a ServerSocket to bind to the specified port
                ServerSocket serverSocket = new ServerSocket(port);

                // Close the ServerSocket to "close" the port
                serverSocket.close();

                System.out.println("Port " + port + " is now closed\n");
            } 
            catch (Exception e)
            {
                System.out.println("Failed to close port " + port + ": " + e.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args)
    {
        EmptyPortCloser closePorts = new EmptyPortCloser();
        closePorts.closeSpecifiedPorts();
    }
}
