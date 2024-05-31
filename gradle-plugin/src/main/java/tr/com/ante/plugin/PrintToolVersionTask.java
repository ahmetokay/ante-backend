package tr.com.ante.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class PrintToolVersionTask extends DefaultTask {
    String tool;

    @TaskAction
    void printToolVersion() {
        switch (tool) {
            case "java":
                System.out.println(System.getProperty("java.version"));
                break;
            case "groovy":
                System.out.println("GroovySystem.version");
                break;
            default:
                throw new IllegalArgumentException("Unknown tool");
        }
    }
}