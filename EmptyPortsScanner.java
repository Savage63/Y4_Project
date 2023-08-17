import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmptyPortsScanner 
{
    private static String outputFolderPath = System.getProperty("user.home") + File.separator + "Project" + File.separator + "Devices";

    public static boolean scan(String targetIpAddress, String outputFilePath) throws IOException 
    {
        String nmapCommand = "nmap -p- " + targetIpAddress;
        boolean emptyPortsFound = false;

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
                processBuilder = new ProcessBuilder("cmd", "/c", nmapCommand + " > " + outputFilePath);
            } 
            else 
            {
                processBuilder = new ProcessBuilder("bash", "-c", nmapCommand + " > " + outputFilePath);
            }

            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();
            process.waitFor();

        } 
        catch (IOException | InterruptedException e) 
        {
            e.printStackTrace();
        }

        return emptyPortsFound;
    }

    public static String getEmptyPorts(String scanFilePath) throws IOException 
    {
        List<String> emptyPortsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(scanFilePath)))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                if (line.contains("/tcp") && line.contains("closed")) 
                {
                    int startIndex = line.indexOf("/") + 1;
                    int endIndex = line.indexOf("/tcp");
                    String port = line.substring(startIndex, endIndex);
                    emptyPortsList.add(port);
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return String.join(", ", emptyPortsList);
    }

    public static void saveEmptyPortsToFile(String targetIpAddress, String emptyPorts, String outputFolderPath) throws IOException 
    {
        File emptyPortsFile = new File(outputFolderPath, targetIpAddress + "_empty_ports.txt");

        try (PrintWriter writer = new PrintWriter(emptyPortsFile)) 
        {
            writer.println("Empty Ports for IP Address: " + targetIpAddress);
            writer.println(emptyPorts);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
        RowInformationPage rowInfoPage = new RowInformationPage(new Object[]{"Device 1", "0.0.0.0", "00:11:22:33:44:55", "Linux", "Manufacturer"});
        rowInfoPage.show();
    }

}
