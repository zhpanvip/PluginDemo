package site.zhangpan.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidPluginTest : Plugin<Project> {
    override fun apply(project: Project) {
        
        // -------------------------Project 的生命周期 START-------------------------
        // 配置阶段开始前回调
        project.beforeEvaluate {
            // 这里监听不到..
            println("beforeEvaluate")
        }
        // 配置阶段结束后回调
        project.afterEvaluate {
            println("afterEvaluate")
        }
        // Gradle 执行完毕的回调监听
        project.gradle.buildFinished {
            println("gradle buildFinished")
        }
        
        // project.gradle.addBuildListener(object :BuildListener{
        //     override fun settingsEvaluated(settings: Settings) {
        //         // 这里监听不到..
        //         println("gradle settingsEvaluated in listener")
        //     }
        //
        //     override fun projectsLoaded(gradle: Gradle) {
        //         // 这里监听不到..
        //         println("gradle projectsLoaded in listener")
        //     }
        //
        //     override fun projectsEvaluated(gradle: Gradle) {
        //         println("gradle projectsEvaluated in listener")
        //     }
        //
        //     override fun buildFinished(result: BuildResult) {
        //         println("gradle buildFinished in listener")
        //     }
        // })
        
        // -------------------------Project 的生命周期 END-------------------------
        
        // -------------------------Project 常用属性 START-------------------------
        
        // 获取当前 project 的名字，每个模块都对应一个 project
        println("project.name = ${project.name}")
        println("project.displayName:${project.displayName}")
        // 获取当前 project 的根 project
        val rootProject = project.rootProject
        // 获取 rootProject 的所有 project,包含自身
        println("all projects:${rootProject.allprojects}")
        // 获取 rootProject 的所有子 project
        println("all projects:${rootProject.subprojects}")
        println("child project:${project.childProjects}")
        println("parent project:${project.parent}")
        
        println("build dir:${project.buildDir}")
        println("build file:${project.buildFile}")
        
        // 修改 app project 的配置
        project.rootProject.project("app") {
            project.configurations.all {
                resolutionStrategy {
                    println("resolutionStrategy111")
                    // 为 app 工程强制依赖 3.5.6 版本
                    force("com.github.zhpanvip:bannerviewpager:3.5.6")
                }
            }
        }
        
        // -------------------------Project 常用属性 END-------------------------
        
        // -------------------------Project Plugins START-------------------------
        
        // 如果没有依赖 MyPlugin，则添加依赖
        if (!project.plugins.hasPlugin(TestPlugin::class.java)) {
            println("Not contains SettingsPluginTest")
            project.plugins.apply(TestPlugin::class.java)
            // 使用 id 引用插件
            // project.plugins.apply("site.zhangpan.myplugin")
        }
        // 获取 MyPlugin 插件
        val testPlugin = project.plugins.findPlugin(TestPlugin::class.java)
        testPlugin?.test()
        // 通过 id 获取插件
        val applicationPlugin = project.plugins.findPlugin("com.android.application")
        println("findPlugin:${applicationPlugin.toString()}")
        
        // -------------------------Project Plugins END-------------------------
        
        // -------------------------Project gradle START-------------------------
        println("gradle version:${project.gradle.gradleVersion}")
        // 获取gradle执行的任务，例如clean/rebuild/assembleXXX
        val taskNames = project.gradle.startParameter.taskNames
        println("gradleTaskNames:$taskNames")
        for (taskName in taskNames) {
            println("gradleTaskName:${taskName}")
        }
        // -------------------------Project Plugins END-------------------------
        
        // -------------------------Project TASK START-------------------------
        // 注册一个task
        project.tasks.register("testPlugin") {
            this.doFirst {
                println("TestPlugin Start")
            }
            
            this.doLast {
                println("testPlugin End")
            }
        }
        project.tasks.forEach {
            println("task name:${it.name}")
        }
        // -------------------------Project TASK END-------------------------
        
        // -------------------------Project Extension START-------------------------
        
        // 创建一个名字为 "androidExt" 类型为 AndroidExt 的扩展
        project.extensions.create("androidExt", AndroidExt::class.java)
        project.afterEvaluate {
            val androidExt = project.extensions.getByType(AndroidExt::class.java)
            println("AndroidExt.compileSdk = ${androidExt.compileSdk}")
            println("AndroidExt.defaultConfig.applicationId = ${androidExt.defaultConfig.applicationId}")
            println("AndroidExt.defaultConfig.versionCode = ${androidExt.defaultConfig.versionCode}")
        }
        
        // -------------------------Project Extension END-------------------------
    }
}