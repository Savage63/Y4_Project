import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmptyPortsScanner 
{
    private static String outputFolderPath = System.getProperty("user.home") + File.separator + "Project" + File.separator + "Devices";

    public static List<String> scan(String targetIpAddress) throws IOException
    {
        String nmapCommand = "nmap -p- " + targetIpAddress;
        List<String> openUnfilteredPortsList = new ArrayList<>();

        try 
        {
            ProcessBuilder processBuilder;
            Process process;

            File scanFolder = new File(outputFolderPath, targetIpAddress);
            if (!scanFolder.exists()) 
            {
                scanFolder.mkdirs();
            }

            if (System.getProperty("os.name").startsWith("Windows")) 
            {
                processBuilder = new ProcessBuilder("cmd", "/c", nmapCommand);
            } 
            else 
            {
                processBuilder = new ProcessBuilder("bash", "-c", nmapCommand);
            }

            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();

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

            process.waitFor();
        } 
        catch (IOException | InterruptedException e) 
        {
            e.printStackTrace();
        }

        return openUnfilteredPortsList;
    }


    public static void saveOpenUnfilteredPortsToFile(String targetIpAddress, List<String> ports, String outputFolderPath) 
    {
        File ipAddressFolder = new File(outputFolderPath, targetIpAddress);
        if (!ipAddressFolder.exists()) 
        {
            ipAddressFolder.mkdirs();
        }

        File portsFile = new File(ipAddressFolder, "EmptyPorts.txt");

        try (PrintWriter writer = new PrintWriter(new FileWriter(portsFile))) 
        {
            writer.println("Open and Unfiltered Ports for IP Address: " + targetIpAddress);
            for (String port : ports) 
            {
                writer.println(port);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
