import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmptyPortsScanner {
    public static void main(String[] args) {
        String targetIpAddress = "192.168.0.22"; // Replace with the target device's IP address
        String nmapCommand = "nmap -p- " + targetIpAddress; // -p- option scans all 65535 ports
        boolean emptyPortsFound = false;

        try {
            ProcessBuilder processBuilder;
            Process process;

            if (System.getProperty("os.name").startsWith("Windows")) {
                // Use "cmd" and "/c" for Windows
                processBuilder = new ProcessBuilder("cmd", "/c", nmapCommand);
            } else {
                // Use "bash" and "-c" for other operating systems
                processBuilder = new ProcessBuilder("bash", "-c", nmapCommand);
            }

            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the output of Nmap to find open and empty ports
                if (line.contains("/tcp") && line.contains("closed")) {
                    // Here, you can process the empty port information as needed
                    System.out.println("Empty port found: " + line);
                    emptyPortsFound = true;
                }
            }

            process.waitFor();
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if (!emptyPortsFound) {
            System.out.println("No Empty Ports Found");
        }
    }
}
