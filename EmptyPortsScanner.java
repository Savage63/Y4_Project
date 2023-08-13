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
}
