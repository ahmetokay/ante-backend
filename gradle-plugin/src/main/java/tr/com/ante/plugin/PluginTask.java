package tr.com.ante.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.tooling.model.GradleTask;

public class PluginTask extends DefaultTask {

    @TaskAction
    public void javaTask() {
        System.out.println("Hello from MyJavaTask");
    }
}
