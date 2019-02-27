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



注解：@SpringBootApplication		提示这是一个Springboot项目，springbootApplication相当于@Configuration+@EnableAutoConfiguration+ComponentScan

​		@RestController	提示这是一个restful风格的Controller

​	   @RequestMapping(mappingURL,methodType)	路由，将指定value的请求映射到当前方法，method是指定路由的类型

​	   @EnableAutoConfiguration	自动配置，

​	   

