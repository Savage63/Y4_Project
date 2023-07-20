import java.io.IOException;
import java.net.Socket;

public class HomeNetworkPortCloser 
{
    public static void main(String[] args) 
    {
        String targetHost = "192.168.1.13"; // Replace with the IP address of the target device on your home network

        // Define a range of ports you want to check (e.g., 1 to 65535)
        int startPort = 1;
        int endPort = 65535;

        for (int port = startPort; port <= endPort; port++) 
        {
            if (isPortOpen(targetHost, port)) 
            {
                closePort(targetHost, port);
            }
        }
    }

    private static boolean isPortOpen(String host, int port) 
    {
        try (Socket socket = new Socket(host, port)) 
        {
            // If the socket can be created, the port is open and in use
            return true;
        } 
        catch (IOException e)
        {
            // If the socket creation fails, the port is open but not in use
            return false;
        }
    }

    private static void closePort(String host, int port) 
    {
        try 
        {
            // Use Java socket programming to connect to the open port and close the connection
            Socket socket = new Socket(host, port);
            socket.close();
            System.out.println("Port " + port + " on " + host + " has been closed.");
        } 
        catch (IOException e) 
        {
            System.err.println("Error occurred while trying to close port " + port + " on " + host + ": " + e.getMessage());
        }
    }
}
