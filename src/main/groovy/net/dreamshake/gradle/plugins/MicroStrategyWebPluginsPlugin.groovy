package net.dreamshake.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.Task
import org.gradle.api.tasks.bundling.War

class MicroStrategyWebPluginsPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.pluginManager.apply("war")
        def mstrPlugin = project.extensions.create("mstrPlugin", MicroStrategyWebPluginExtension)
        project.tasks.withType(War.class) { War warTask ->
            warTask.archiveExtension.set("zip")
            warTask.into({ mstrPlugin.folder })
        }
        Task aliasTask = project.tasks.create("pluginZip")
        aliasTask.dependsOn(War.TASK_NAME)
    }
}