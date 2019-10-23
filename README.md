## Spring Boot实现秒杀系统
### 基于SpringBoot搭建秒杀系统 
#### 项目描述
本项目是基于SpringBoot对秒杀系统的简单实现。
前台写了一个商品详情页，商品查询基于redis缓存技术实现，提高并发查询效率。
#### 技术栈
+ 后端技术
   + 后端：SpringBoot + MySQL + Redis 
   + 前端：Bootstrap + jQuery + Thymeleaf
   + 开发环境：Java 8 + IntelliJ IDEA + Maven + Git + Linux
+ 使用Redis缓存数据库查询结果，可以减小数据库的压力，实现高并发。
+ 模板引擎使用Thymeleaf，更有利于前后端分离。
+ 为模拟真实的业务场景，MySQL和Redis均部署于Linux环境中。
### 项目目录结构
```
├─db  -- 数据库约束文件
├─src  -- 源码目录
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─hyn
│  │  │          └─seckill
│  │  │              ├─controller -- MVC的web层
│  │  │              ├─dto  -- 统一封装的一些结果属性，和entity类似
│  │  │              ├─entity  -- 实体类，包含秒杀项目和秒杀订单
│  │  │              ├─enums  -- 自定义的表示订单状态的枚举类
│  │  │              ├─exception  -- 自定义异常类 
│  │  │              ├─mapper  -- Mybatis-Mapper层映射接口，或称为DAO层
│  │  │              ├─redis  --  redis相关类，包含redis配置类和redis操作类
│  │  │              │─service  -- 业务层
│  │  │              │    └─impl  -- 业务层实现类
│  │  │              ├── SpringbootSeckillApplication.java  -- SpringBoot启动器
│  │  └─resources
│  │      ├── application.yml  -- SpringBoot核心配置
│  │      ├─mapper  -- Mybatis-Mapper层XML映射文件
│  │      ├─static  -- 存放页面静态资源，可通过浏览器直接访问
│  │      │  ├─css  -- css相关代码
│  │      │  ├─js  -- js代码
│  │      │  └─lib  -- Bootstrap和jQuery等js相关代码
│  │      └─templates  -- 存放Thymeleaf模板引擎所需的HTML，不能在浏览器直接访问
│  │          ├─page  -- 前台页面，包含秒杀列表页和秒杀商品详情页
│  │          └─public  -- HTML页面公共组件（头部、尾部）
│  └─test  -- 测试类
├── pom.xml  -- 项目依赖
```

### 秒杀效果图
![图片无法显示](https://github.com/whoisHYN/aloe/blob/master/seckill_list.JPG)
![图片无法显示](https://github.com/whoisHYN/aloe/blob/master/seckill_detail1.JPG)
![图片无法显示](https://github.com/whoisHYN/aloe/blob/master/seckill_detail2.JPG)
![图片无法显示](https://github.com/whoisHYN/aloe/blob/master/seckill_detail3.JPG)
