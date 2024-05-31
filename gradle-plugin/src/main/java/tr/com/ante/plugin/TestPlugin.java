package tr.com.ante.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class TestPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.task("hello")
                .doLast(task -> System.out.println("Hello Gradle!"));
        //project.getTasks().create("javaTask", PluginTask.class);
    }
}