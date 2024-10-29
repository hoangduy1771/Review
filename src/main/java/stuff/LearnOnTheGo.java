package stuff;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LearnOnTheGo {

    public static ArrayList<Path> collectPaths(String env) {
        String directoryPath = String.format("%s/azure-pipelines/%s-regression-test", System.getProperty("user.dir"), env);
        ArrayList<Path> pipelineFilePaths = new ArrayList<>();

        try {
            pipelineFilePaths = Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pipelineFilePaths;
    }

    public static void commentYmlContentIfContains(String filePath, String keyword, int counter) {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
// Check if any line containing the schedule is already disabled
            boolean alreadyCommented = lines.stream()
                    .anyMatch(line -> line.contains(keyword) && line.startsWith("#"));

            if (alreadyCommented) {
                System.out.println(counter + " " + filePath + " Error: Schedule is already disabled");
                return;
            }

            List<String> modifiedLines = lines.stream()
                    .map(line -> (line.contains(keyword) && !line.startsWith("#")) ? "#" + line : line)
                    .collect(Collectors.toList());

            String content = String.join(System.lineSeparator(), modifiedLines);
            Files.write(path, content.getBytes());

            System.out.println(counter + " " + filePath + " Disable successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(counter + " " + filePath + " Disable unsuccessful.");
        }
    }

    public static void uncommentYmlContentIfContains(String filePath, String keyword, int counter) {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);

// Check if any line containing the schedule is already uncommented
            boolean alreadyUncommented = lines.stream()
                    .anyMatch(line -> line.contains(keyword) && !line.trim().startsWith("#"));
            if (alreadyUncommented) {
                System.out.println(counter + " " + filePath + " Error: Schedule is already enabled.");
                return;
            }

            List<String> modifiedLines = lines.stream()
                    .map(line -> line.trim().startsWith("#") && line.contains(keyword) ? line.substring(1) : line)
                    .collect(Collectors.toList());

            String content = String.join(System.lineSeparator(), modifiedLines);
            Files.write(path, content.getBytes());

            System.out.println(counter + " " + filePath + " Enable successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(counter + " " + filePath + " Enable unsuccessful.");
        }
    }

    public static void runSetupPipelineSprint(String enableEnv, String disableEnv, String keyword) {
        ArrayList<Path> pipelineEnabledFilePaths = collectPaths(enableEnv);
        ArrayList<Path> pipelineDisabledFilePaths = collectPaths(disableEnv);
        int c1FileCounter = 1;
        int ppFileCounter = 1;

        System.out.println("--Begin enable pipeline for " + enableEnv);
        for (Path pathEnable : pipelineEnabledFilePaths) {
            uncommentYmlContentIfContains(String.valueOf(pathEnable), keyword, c1FileCounter);
            c1FileCounter++;
        }
        System.out.println("--End enable pipeline for " + enableEnv);

        System.out.println("=======================================================");

        System.out.println("--Begin disable pipeline for " + disableEnv);
        for (Path pathDisable : pipelineDisabledFilePaths) {
            commentYmlContentIfContains(String.valueOf(pathDisable), keyword, ppFileCounter);
            ppFileCounter++;
        }
        System.out.println("--End disable pipeline for " + disableEnv);
        System.out.println("Expected " + enableEnv + " files should be: " + pipelineEnabledFilePaths.size());
        System.out.println("Expected " + disableEnv + " files should be: " + pipelineDisabledFilePaths.size());
    }

}

