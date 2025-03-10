**使用**
(1) application.properties 中配置数据库url , 密码信息
(2) 创建数据库 examSystem ， 创建数据库表 topic_table 。 
(3) 启动项目
(4) 访问http://localhost:8080

介绍内容：
https://www.bilibili.com/video/BV17L98YXEMS



**Exam System**
Web 服务设计

答题服务：
/prepare :  准备界面
/start ： 开始答题
/commit :  提交答题 ， 返回成绩页面 。 


存储题库服务：
/store : 存储题页面


数据库设计
Topic table
create table topic_table (
  tid int primary key auto_increment , 
  title varchar(1024) , 
answer varchar(1024) , 
  content text , 
  set_ int ,
  type int
 ) ; 

content : 题内容 ，服务器解析内容 ， |-| 进行分割 , 每一项中.前必须是答案。 D. String 
set :  题集 ， java面试题 1 ， html/js面试题 2 
type : 单项选择题 0 , 多项选择题 1 , 判断 2  
