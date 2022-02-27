# whale-tools 基础业务组件包

### 基础设施

| 功能 | 描述 | 可用 |
| --- | --- | --- |
| 业务组件-工具类 | 定义项目工具类，如分页、异常、日期、JSON转换等等 | Y |
| 业务组件-API 日志 | 包括 RESTful API 访问日志、异常日志两部分，方便排查 API 相关的问题 | Y |
| 业务组件-字典 | 定义项目动态业务字典，方便支持在线字典的实时变更 | Y |
| 文件服务 | 支持本地文件存储，同时支持兼容 （ALiYun、QiNiuYun）等协议的云存储组件 | N |
| 定时任务 | 在线（添加、修改、删除)任务调度包含执行结果日志 | N |
| 分布式锁 | 基于 Redis 实现分布式锁，满足并发场景 | N |

### model依赖库

| 框架 | 说明 |  版本 |
| --- | --- | --- |
| [Spring Boot](https://spring.io/projects/spring-boot) | 应用开发框架 | 2.5.6 |
| [MySQL](https://www.mysql.com/cn/) | 数据库服务器 | 8.0.27 |  |
| [MyBatis Plus](https://mp.baomidou.com/) | MyBatis 增强工具包 | 3.5.1 |
| [Dynamic Datasource](https://dynamic-datasource.com/) | 动态数据源 | 3.3.2 |
| [Redis](https://redis.io/) | key-value 数据库 | 5.0 |  |
| [Spring MVC](https://github.com/spring-projects/spring-framework/tree/master/spring-webmvc) | MVC 框架  | 5.4.6 |
| [Hibernate Validator](https://github.com/hibernate/hibernate-validator) | 参数校验组件 | 6.1.7 |
| [Knife4j](https://gitee.com/xiaoym/knife4j) | Swagger 增强 UI 实现 | 3.0.2 |
| [Jackson](https://github.com/FasterXML/jackson) | JSON 工具库 | 2.11.4 |  |
| [MapStruct](https://mapstruct.org/) | Java Bean 转换 | 1.4.1 |
| [Lombok](https://projectlombok.org/) | 消除冗余的 JavaBean 代码 | 1.18.12 |
| [HuTool](https://hutool.cn/) | Hutool是一个小而全的Java工具类库 | 5.6.1 |
| [EasyExcel](https://github.com/alibaba/easyexcel) | JAVA解析Excel工具EasyExcel | 2.2.7 |