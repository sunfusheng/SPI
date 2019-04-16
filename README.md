# SPI [![spi-api](https://api.bintray.com/packages/sfsheng0322/maven/spi-api/images/download.svg) ](https://bintray.com/sfsheng0322/maven/spi-api/_latestVersion)

Android开发中SPI的应用

#### Gradle:

    // project gradle
    buildscript {
        repositories {
            maven { url 'https://dl.bintray.com/sfsheng0322/maven' }
        }
        dependencies {
            classpath 'com.sunfusheng:spi-plugin:1.0.0'
        }
    }
    allprojects {
        repositories {
            maven { url 'https://dl.bintray.com/sfsheng0322/maven' }
        }
    }

    // module gradle
    apply plugin: 'com.sunfusheng.spi'
    dependencies {
        // ...
        implementation 'com.sunfusheng:spi-annotation:<latest-version>'
        implementation 'com.sunfusheng:spi-api:<latest-version>'
        annotationProcessor 'com.sunfusheng:spi-compiler:<latest-version>'
    }

<br/>

### 个人微信公众号

<img src="http://ourvm0t8d.bkt.clouddn.com/wx_gongzhonghao.png">

<br/>

### 打点赏给作者加点油^_^

<img src="http://ourvm0t8d.bkt.clouddn.com/wx_shoukuanma.png" >

<br/>

### 关于我

[GitHub: sunfusheng](https://github.com/sunfusheng)

[个人邮箱: sfsheng0322@126.com](https://mail.126.com/)

[个人博客: sunfusheng.com](http://sunfusheng.com/)

[简书主页](http://www.jianshu.com/users/88509e7e2ed1/latest_articles)

[新浪微博](http://weibo.com/u/3852192525)