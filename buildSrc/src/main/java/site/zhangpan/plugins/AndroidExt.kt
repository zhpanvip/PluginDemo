package site.zhangpan.plugins;

import org.gradle.api.Action;

public class AndroidExt {
    private int compileSdk;
    private final DefaultConfig defaultConfig = new DefaultConfig();

    public int getCompileSdk() {
        return compileSdk;
    }

    public void setCompileSdk(int compileSdk) {
        this.compileSdk = compileSdk;
    }

    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    // public void setComputer(Closure closure) {
    //     ConfigureUtil.configure(closure, computer);
    // }

    public void defaultConfig(Action<DefaultConfig> action) {
        action.execute(defaultConfig);
    }

    static class DefaultConfig {
        private String applicationId;
        private int versionCode;

        public String getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(String applicationId) {
            this.applicationId = applicationId;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }
    }
}
