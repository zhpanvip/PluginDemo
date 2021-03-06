package site.zhangpan.settings.plugin

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.initialization.Settings
import org.gradle.api.Plugin
import org.gradle.api.invocation.Gradle

class SettingsPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {
        println("settings apply")
        settings.gradle.settingsEvaluated {
            println("gradle settingsEvaluated in listener")
        }
        settings.gradle.addBuildListener(object : BuildListener {
            override fun settingsEvaluated(settings: Settings) {
                println("gradle settingsEvaluated in listener")
            }

            override fun projectsLoaded(gradle: Gradle) {
                println("gradle projectsLoaded in listener")
            }

            override fun projectsEvaluated(gradle: Gradle) {
                println("gradle projectsEvaluated in listener")
            }

            override fun buildFinished(result: BuildResult) {
                println("gradle buildFinished in listener")
            }
        })
    }
}