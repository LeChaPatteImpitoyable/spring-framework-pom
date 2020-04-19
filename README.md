# Spring Framework Pom

[Spring|Spring framework|Maven]
>**注意：** `此代码仅用于学习目的，便于新手阅读Spring Framework源码使用`
>

## 1、说明：
- **我只是对官方源码进行处理，并没有任何改动，使用的Spring Framework版本为5.1.X，当前为5.1.15，请知晓。**
- **由于当前官方源码使用Gradle编译，对于新手极为不方便，而且困难，对于Groovy脚本语言没有一点基础则无法理解，我只是对源码进行转换，未做改动。**
## 2、转换过程
### **①下载源码**
>https://github.com/spring-projects/spring-framework.git

### **②切换分支**
>git branch -a
>git checkout remotes/origin/5.1.x

### **③查看说明**
>查看官方说明README.md 、import-into-idea.md、import-into-eclipse.md

### **④编译**
>根据官方文档操作步骤如下
``` markdown
1. Precompile `spring-oxm` with `./gradlew :spring-oxm:compileTestJava`
```

### **⑤人工移动**
>I、将编译成功后每个项目中的build/poms/pom-default.xml移动到项目中，然后删除*.gradle和build；
>II、然后在pom中增加：
```xml
<build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.3</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>2.6</version>
        <executions>
          <execution>
            <id>Jar Package</id>
            <phase>package</phase>
            <goals>
              <goal>jar</goal>
            </goals>
          </execution>
          <execution>
            <id>Jar Tests Package</id>
            <phase>package</phase>
            <goals>
              <goal>test-jar</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```
>III、用于每次编译是不能知道编译的版本，maven-jar-plugin用于打test的jar包，Spring Framework中的test会应用另一个包中的test，所以需要单独打包；
>IV、最后一步是一个一个的导入项目，然后编译报错，加maven包。。。
- **方法比较笨拙，应该可以编写脚本减轻工作量**

### **⑥、注意点**
>I、spring-context-indexer项目中会有一个主意点，如下：
```xml
<build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
          <version>3.5.1</version>
        <configuration>
          <!-- TODO 问题出处 Processor in META-INF/services/ with the configuration of the annotation processor's class. In order to fix the problem I had to add the following to the pom.xml configuration of my processor project:
          https://stackoverflow.com/questions/38926255/maven-annotation-processing-processor-not-found-->
          <compilerArgument>
            -proc:none
          </compilerArgument>
          <source>8</source>
          <target>8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
```
>II、pom中导入的包没有经过精挑细选，需要自行确认；
>III、测试代码最好写在spring-webmvc、spring-websocket中，以免其他项目又引入了某个项目而造成循环依赖。

