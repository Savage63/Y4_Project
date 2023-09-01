import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmptyPortsScanner 
{
    private static String outputFolderPath = System.getProperty("user.home") + File.separator + "Project" + File.separator + "Devices";

    public static List<String> scan(String targetIpAddress) throws IOException 
    {
        // Create a command to run the nmap tool to scan for all ports on the target IP address.
        String nmapCommand = "nmap -p- " + targetIpAddress;
        List<String> openUnfilteredPortsList = new ArrayList<>(); // List to store open and unfiltered port numbers.

        try 
        {
            ProcessBuilder processBuilder;
            Process process;

            // Create a folder to store scan results for the specific target IP.
            File scanFolder = new File(outputFolderPath, targetIpAddress);
            if (!scanFolder.exists()) 
            {
                scanFolder.mkdirs();
            }

            // Determine the command to run based on the operating system (Windows or non-Windows).
            if (System.getProperty("os.name").startsWith("Windows")) 
            {
                processBuilder = new ProcessBuilder("cmd", "/c", nmapCommand);
            } 
            else 
            {
                processBuilder = new ProcessBuilder("bash", "-c", nmapCommand);
            }

            // Redirect the error stream to the same stream as the input stream.
            processBuilder.redirectErrorStream(true);
            process = processBuilder.start(); // Start the process.

            // Read the output of the process and extract open/unfiltered ports.
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) 
            {
                String line;
                while ((line = reader.readLine()) != null) 
                {
                    if (line.contains("/tcp") && (line.contains("open") || line.contains("unfiltered"))) 
                    {
                        int startIndex = line.indexOf("/") + 1;
                        int endIndex = line.indexOf("/tcp", startIndex);
                        if (endIndex > startIndex) 
                        {
                            String port = line.substring(startIndex, endIndex);
                            openUnfilteredPortsList.add(port);
                        }
                    }
                }
            }

            process.waitFor(); // Wait for the process to finish.
        } 
        catch (IOException | InterruptedException e) 
        {
            e.printStackTrace();
        }

        return openUnfilteredPortsList; // Return the list of open and unfiltered port numbers.
    }

    public static void saveOpenUnfilteredPortsToFile(String targetIpAddress, List<String> ports, String outputFolderPath) 
    {
        // Create a folder for the specific target IP if it doesn't exist.
        File ipAddressFolder = new File(outputFolderPath, targetIpAddress);
        if (!ipAddressFolder.exists()) 
        {
            ipAddressFolder.mkdirs();
        }

        // Create a file to save the open and unfiltered port numbers.
        File portsFile = new File(ipAddressFolder, "EmptyPorts.txt");

        try (PrintWriter writer = new PrintWriter(new FileWriter(portsFile))) 
        {
            // Write header and port numbers to the file.
            writer.println("Open and Unfiltered Ports for IP Address: " + targetIpAddress);
            for (String port : ports) 
            {
                writer.println(port);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
