package site.zhangpan.plugins

import org.gradle.api.Action

open class AndroidExt {
    var compileSdk = 0
    val defaultConfig = DefaultConfig()
    
    fun defaultConfig(action: Action<DefaultConfig?>) {
        action.execute(defaultConfig)
    }
}

open class DefaultConfig {
    var applicationId: String? = null
    var versionCode = 0
}