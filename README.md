#  Part I ：Github/ Maven / Spring /Spring Boot

## I.Github

###### Github是一个免费的远程远程仓库，与SVN不同的是，他是一个分布式的版本控制工具（下面是他的官方网站）

https://github.com/

#### Github的使用：

###### 网页中的Github: 

1. 创建Github账号，和普通的网站一样，Github上也需要创建账号来同步自己的代码
2. New Repositories ，新建一个代码库，这个代码库的名称就是 “你的账号”+库名称 （这个库是Github网站上面的库）
3. 接下来就是同步你的代码库，点开代码库之后有三个选项去同步代码库，克隆，下载压缩文件和使用url的方式同步到本地仓库，同步到本地的方式就可以放在各种工具中使用了，比如桌面版本的Github Desktop ，Eclipse，STS ，各种工具中使用。
4. 代码在本地和网站上可以互相进行同步，和版本回退的操作，从而对代码进行全方位的管理

###### pull和push操作

###### 当本地库和远程库有冲突的时候则两端商议并改成最总方案进行同步

######  git reset --mixed` 回退commit,保留源码,默认方式

###### git reset --soft` 回退至某个版本,只回退commit信息` 

###### git reset --hard` 彻底回退至某个版本

 ###### 进行pull操作的时候，如果有冲突的话，需要先对更改的内容进行commit 再pull代码，更改完冲突后再进行提交

## II.Maven

###### maven是一个管理jar包的项目对象模型（POM）（下面是它的jar包库网站）

https://mvnrepository.com/

### maven的使用

###### mavne下载配置，与java配置类似，安装到Eclipse和STS下，以便创建maven项目，不适用编辑器的时候，需要有指定的文件目录，这样才能使maven项目生效

###### maven中最终要的部分就是在项目根目录下的pom.xml文件，其中包括如下代码,下面一篇是从我的spring boot项目中粘贴过来的，其中包括项目坐标，项目版本号，打包方式，依赖jar包，可安装的插件等。又不需要的jar也可以进行排除依赖

###### maven本地库的配置，默认的是从官网中下载，但可以自定义库进行下载

> <?xml version="1.0" encoding="UTF-8"?>
> <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
> ​	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
> ​	<modelVersion>4.0.0</modelVersion>
>
> 	<groupId>com.example</groupId>
> 	<artifactId>demo-2</artifactId>
> 	<version>0.0.1-SNAPSHOT</version>
> 	<packaging>jar</packaging>
> 	
> 	<name>demo-2</name>
> 	<description>Demo project for Spring Boot</description>
> 	
> 	<parent>
> 		<groupId>org.springframework.boot</groupId>
> 		<artifactId>spring-boot-starter-parent</artifactId>
> 		<version>1.5.17.RELEASE</version>
> 		<relativePath/> <!-- lookup parent from repository -->
> 	</parent>
> 	
> 	<properties>
> 		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
> 		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
> 		<java.version>1.8</java.version>
> 		<spring-boot-admin.version>1.5.7</spring-boot-admin.version>
> 	</properties>
> 	
> 	<dependencies>
> 		<!--引入admin server依赖-->
>    		<dependency>
>    			<groupId>de.codecentric</groupId>
>    			<artifactId>spring-boot-admin-server</artifactId>
>    		</dependency>
>    		<!--admin server的展示-->
>    		<dependency>
>    			<groupId>de.codecentric</groupId>
>    			<artifactId>spring-boot-admin-server-ui</artifactId>
>    			</dependency>
>
> 		<dependency>
> 			<groupId>org.springframework.boot</groupId>
> 			<artifactId>spring-boot-starter-test</artifactId>
> 			<scope>test</scope>
> 		</dependency>
> 	</dependencies>
> 	
> 	<dependencyManagement>
> 		<dependencies>
> 			<dependency>
> 				<groupId>de.codecentric</groupId>
> 				<artifactId>spring-boot-admin-dependencies</artifactId>
> 				<version>${spring-boot-admin.version}</version>
> 				<type>pom</type>
> 				<scope>import</scope>
> 			</dependency>
> 		</dependencies>
> 	</dependencyManagement>
> 	
> 	<build>
> 		<plugins>
> 			<plugin>
> 				<groupId>org.springframework.boot</groupId>
> 				<artifactId>spring-boot-maven-plugin</artifactId>
> 			</plugin>
> 		</plugins>
> 	</build>
>
> </project>

###### 可以通过命令行的方式进行下载本地库中没有的依赖以便使用

###### 以上就是Maven的主要内容了，主要包括了安装配置，使用依赖，使用插件

## III.Spring

###### Spring解决了业务逻辑层和其他层的强耦合问题，是一个轻量级的Java框架

### Spring的思想：

#### IOC控制反转->就是将程序员控制对象的权利拿走，交给Spring容器进行管理

#### AOP面向切片编程->类似Servlet中的监听，把程序每个部分进行切片，插入相应函数，类似DEBUG

###### Spring有两种方式进行使用，其一是使用application.xml形势的配置，其二是使用扫描注解的方式进行书写

```java
@Autowired @Repository @Service @Controller @Component @Configuration @Value @Bean @Resource @PostConstruct(初始化)  @PreDestory(销毁) @Primary(首选者) @Lazy(true)(延迟初始化) @Qualifier(指定bean) @Resource @Named(与@Component功能相同，可以有值) @Inject(需要引用javax.inject.jar,与@Autowired有互换性) @Singleton(单例) @RequestMapping @Scope
```

https://blog.csdn.net/achenyuan/article/details/72786759     (注解详解)

###### Spring还有各种给对象赋值的方式

## IV.Spring Boot

###### SpringBoot 就是简化业务逻辑之前重复的配置工作的一个综合框架

### Spring Boot的使用

###### 创建Maven项目

http://start.spring.io/     下载并导入STS 或者直接从STS中创建Spring Starter Project

#### SpringBoot相关依赖

> <parent>
> ​    <groupId>org.springframework.boot</groupId>
> ​    <artifactId>spring-boot-starter-parent</artifactId>
> ​    <version>1.5.1.RELEASE</version>
> </parent>
> <dependencies>
> ​    <dependency>
> ​        <groupId>org.springframework.boot</groupId>
> ​        <artifactId>spring-boot-starter-web</artifactId>
> ​    </dependency>
>
> <dependencies>

##### 路径下的配置文件读取优先级（从上到下）

###### fiel：./config/

###### fiel：./   

###### :/src/main/resources/config/     classpath:/config/

###### :src/main/resources/(推荐使用） classpath:/

###### 使用方法比Spring简单



# Part II：Spring Boot Project

### 功能：

###### 修改Banner图

###### Application根据配置文件修改属性

###### 使用注解的方式注入所有的实体类数据

###### web应用



