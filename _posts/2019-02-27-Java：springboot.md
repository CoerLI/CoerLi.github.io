---
layout:     post   				        	# 使用的布局
title:      Java：				  	# 标题 
subtitle:   深挖Java 继承的实现原理          			# 副标题
date:       2019-02-21 				      		# 时间
author:     Lih 						# 作者
header-img: img/office03.jpg 					# 这篇文章标题背景图片
catalog: true 							# 是否归档
tags:								# 标签
    - Java
    - JVM


---

## Springboot

yaml配置；

​	缩进表示层级关系，键值对定义变量

​	yaml可以定义字符串、数组（用—分行）、Map（键值对集合），可以通过@ConfigurationProperties（prefix=“”）来进行自动注入类，其中prefix表示yml中的前缀

​	多环境配置：在Spring Boot中多环境配置文件名需要满足application-{profile}.properties/yml的格式，其中{profile}对应你的环境标识	

stater：

一个配置文件和jar的集合，可以简化需要引入依赖，简化依赖管理

注解：

> @SpringBootApplication		提示这是一个Springboot项目，springbootApplication相当于
> @Configuration+@EnableAutoConfiguration+ComponentScan
> @EnableAutoConfiguration		自动配置
> @Autowried	自动注入
> @Controller、@Service、@Component		区分bean类型
> @RestController	提示这是一个restful风格的Controller
> @RequestMapping(mappingURL,methodType)	路由，将指定value的请求映射到当前方法，method是指定路由的类型
> @RunWith(SpringRunner.class)					测试使用，测试时启动spring



整合mybatis：

​	添加依赖：

```xml
<dependency>
   <groupId>org.mybatis.spring.boot</groupId>
   <artifactId>mybatis-spring-boot-starter</artifactId>
   <version>1.3.2</version>
</dependency>
```

​	yml中配置属性：

```yaml
mybatis:
 type-aliases-package: entity:con.lihang.springboot.entity
```

在springboot主程序上加入@MapperScan("mapperURL")，在指定路径扫描mapper文件

​	或者在使用@Mapper注明这是一个Mapper类（注解方式，sql可以直接写在类中）

​	注解方式：

```java
//	使用#{id} 占位变量
@Delete("DELETE FROM student WHERE id = #{id}")	
void deleteStudentById(int id);
// 可以直接使用属性名称，通过自动装配封装和自动拆分
@Update("UPDATE student SET name = #{name}, age = #{age} WHERE id=#{id}")
void updateStudent(Student student);
```