package com.stuff;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

class ProcessPlayTest {
    private static String JAVA_HOME;
    @BeforeAll
    static void setUp() {
        JAVA_HOME = System.getenv().get("JAVA_HOME");
    }

    @Test
    void runJavaVersion() {
        try {
            Path javaFile = Paths.get(JAVA_HOME, "bin", "java");

            System.out.println(javaFile.toFile().toString());
            String [] commandAndParameters = new String[2];
            commandAndParameters[0] = javaFile.toString();
            commandAndParameters[1] = "-version";

            var process = Runtime.getRuntime().exec(commandAndParameters);
            var inputStream = process.getInputStream();
            var errorStream = process.getErrorStream();

            String line;

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = inputReader.readLine()) != null) {
                System.out.println(line);
            }

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
            while ((line = errorReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void runAndStopTheProgram() throws URISyntaxException {
        var jarPath = Paths.get(
                Objects.requireNonNull(
                        this.getClass().getClassLoader().getResource("process-test.jar")).toURI());
        System.out.println(jarPath.toString());
    }

    @Test
    void runTheProgramAndConsumeTheLogs(){

    }

    @Test
    void runTheProgramConsumeTheLogsAndStreamThem(){

    }

    @Test
    void processBuilderTest() {
        try {

            System.out.println(JAVA_HOME + "bin\\java");
            var builder = new ProcessBuilder(JAVA_HOME +
                    "\\bin\\java", "-version");
            builder.redirectErrorStream(true);
            Process process = builder.start();
            Thread.sleep(1000);
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = inputReader.readLine()) != null) {
                System.out.println("\u001B[31m" + line + "\u001B[0m");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void runtimePlay() throws IOException {
//        var process = Runtime.getRuntime().exec("java -version");
        System.out.println(System.getenv("JAVA_HOME"));
        var process = Runtime.getRuntime().exec("c:\\Program Files\\Git\\usr\\bin\\which.exe java");
        var inputStream = process.getInputStream();
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = inputReader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
