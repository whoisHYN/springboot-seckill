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
### 秒杀效果图
![图片无法显示](https://github.com/whoisHYN/aloe/blob/master/seckill_list.JPG)
![图片无法显示](https://github.com/whoisHYN/aloe/blob/master/seckill_detail1.JPG)
![图片无法显示](https://github.com/whoisHYN/aloe/blob/master/seckill_detail2.JPG)
![图片无法显示](https://github.com/whoisHYN/aloe/blob/master/seckill_detail3.JPG)
