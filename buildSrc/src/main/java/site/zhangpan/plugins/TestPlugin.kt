package site.zhangpan.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class TestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("MyPlugin applied")
    }
    
    fun test(){
        println("just a test plugin method")
    }
}