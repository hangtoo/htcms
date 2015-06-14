一、
1.复用原则

	通过配置文件即可和现有的系统接入，涉及到的配置文件为spring.xml和struts.xml包含在
	目录“resources\common\component\组件名”下，其中spring.xml主要用来组装dao、service、action等
	其中struts.xml主要配置action的访问路径,系统可以通过该配置路径访问该模块的功能等。
	
	注意：需要struts2平台及其他组件及非组件的通用模块支持

2.复用方法
	
	2.1 在WEB-INF\web.xml的加入如下代码片段：
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			classpath:spring/applicationContext.xml
			classpath:common/component/permission/spring.xml
		</param-value>
	</context-param>
	其中“classpath:spring/applicationContext.xml”为spring默认配置
	“classpath:common/component/permission/spring.xml”为组件所需的配置
	
	2.2 在resources\struts.xml中
	加入代码片段如下：
	<include file="common/component/permission/struts.xml"/>
	
3.复用单元

	3.1组件组成
		src/common/component/组件
		resources/common/component/组件
		WebContent/common/component/组件
	由以上三部分组成，分别为源码、配置文件、展现界面等
	
二、
1.	hibernate作为持久层，涉及到直接用HQL语句来进行操作的，改为用SQL语句来完成
	（待考虑）
	
	
三、组件依赖关系
1.
permission->ectable、verifycode

cms->ectable、upload


bug:
catalog作为action要谨慎用，可能是关键字




	