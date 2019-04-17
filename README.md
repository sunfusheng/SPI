# SPI [![spi-api](https://api.bintray.com/packages/sfsheng0322/maven/spi-api/images/download.svg) ](https://bintray.com/sfsheng0322/maven/spi-api/_latestVersion)

Android模块化开发中SPI的助力，SPI全名：Service Provider Interface

### Gradle配置:

// project gradle

``` gradle
    dependencies {
        // ...
        classpath 'com.sunfusheng:spi-plugin:<latest-version>'
    }
```

// module gradle

``` gradle
    apply plugin: 'com.sunfusheng.spi'

    dependencies {
        // ...
        implementation 'com.sunfusheng:spi-annotation:<latest-version>'
        implementation 'com.sunfusheng:spi-api:<latest-version>'
        annotationProcessor 'com.sunfusheng:spi-compiler:<latest-version>'
    }
```

### 具体场景与使用

在组件化开发中，主工程（app module）会依赖各个子模块（sub module），有这么一个功能，我们会在主工程中初始化各个子模块，
通常我们的做法是在主工程中分别调用每个子模块的初始化和反初始化方法，这时我将各个子模块的初始化类当成一种需要加载的服务，
每个子模块的初始化类都继承一个抽象类或实现一个接口，这样我只需要在主模块中找到那些实现固定抽象类或接口的服务然后统一操作就可以了，
具体分解下使用步骤如下：

#### 1、根据需求定义抽象类或接口

``` java
    abstract public class AbsApplicationDelegate {

        abstract public void onCreate(Application application);

        public void onLowMemory() { }

        public void onTrimMemory(int level) { }

        public void onTerminate() { }
    }
```

#### 2、子模块实现类继承抽象类或实现接口并在添加注解@Provide(AbsApplicationDelegate.class)

``` java
    @Provide(AbsApplicationDelegate.class)
    public class AApplicationDelegate extends AbsApplicationDelegate {

        @Override
        public void onCreate(Application application) {
            Log.d("SPI", "AApplicationDelegate::onCreate()");
        }
    }
```

#### 3、主工程中实现所有服务的Providers管理

``` java
    public class ApplicationDelegateManager {
        private static final ApplicationDelegateManager mDelegatesManager = new ApplicationDelegateManager();
        private List<AbsApplicationDelegate> mDelegates;

        private ApplicationDelegateManager() {
            mDelegates = ServiceProvider.getProviders(AbsApplicationDelegate.class);
        }

        public static ApplicationDelegateManager getInstant() {
            return mDelegatesManager;
        }

        public void onCreate(Application application) {
            if (mDelegates.size() > 0) {
                for (AbsApplicationDelegate delegate : mDelegates) {
                    delegate.onCreate(application);
                }
            }
        }

        public void onLowMemory() {
            if (mDelegates.size() > 0) {
                for (AbsApplicationDelegate delegate : mDelegates) {
                    delegate.onLowMemory();
                }
            }
        }

        public void onTrimMemory(int level) {
            if (mDelegates.size() > 0) {
                for (AbsApplicationDelegate delegate : mDelegates) {
                    delegate.onTrimMemory(level);
                }
            }
        }

        public void onTerminate() {
            if (mDelegates.size() > 0) {
                for (AbsApplicationDelegate delegate : mDelegates) {
                    delegate.onTerminate();
                }
            }
        }
    }
```

#### 4、最后调用即可，也可以根据自己的需求解锁其他姿势

``` java
    public class BaseApplication extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            ServiceProvider.init();
            ApplicationDelegateManager.getInstant().onCreate(this);
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            ApplicationDelegateManager.getInstant().onLowMemory();
        }

        @Override
        public void onTrimMemory(int level) {
            super.onTrimMemory(level);
            ApplicationDelegateManager.getInstant().onTrimMemory(level);
        }

        @Override
        public void onTerminate() {
            super.onTerminate();
            ApplicationDelegateManager.getInstant().onTerminate();
            ServiceProvider.destroy();
        }
    }
```

<br/>

### 个人微信公众号

<img src="http://ourvm0t8d.bkt.clouddn.com/wx_gongzhonghao.png">

<br/>

### 关于我

[GitHub: sunfusheng](https://github.com/sunfusheng)

[个人邮箱: sfsheng0322@126.com](https://mail.126.com/)

[个人博客: sunfusheng.com](http://sunfusheng.com/)

[简书主页](http://www.jianshu.com/users/88509e7e2ed1/latest_articles)

[新浪微博](http://weibo.com/u/3852192525)