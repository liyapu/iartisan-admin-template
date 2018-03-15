# iartisan-admin-template
基于spring-boot，快速构建后台系统
`集成shiro，sitemesh，mybatis-plus，freemaker` 
基础功能都已完成：用户管理、菜单管理、权限管理

## 演示地址
- http://140.143.246.200:8080/
- 用户名/密码  admin  /  admin

##前端框架：
- layui
- layuiCMS 2.0


### 1.项目部署

- 执行doc/sql下的sql脚本

- 修改数据库连接配置，找到文件env.properties 
  修改配置项：`jdbc.url，jdbc.username，jdbc.password`

- 运行类：WebBootRun.java

- 访问：http://localhost:8080/login 当然端口是可改的，需要在env.properties
  文件添加配置项：`server.port`

先来两张图压压惊
![输入图片说明](https://gitee.com/uploads/images/2018/0308/153537_c0248355_639234.png "image.png")

![输入图片说明](https://gitee.com/uploads/images/2018/0308/153556_33363968_639234.png "image1.png")

![输入图片说明](https://gitee.com/uploads/images/2018/0308/153608_b9525e7d_639234.png "image2.png")