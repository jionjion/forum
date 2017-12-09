---

title: 博客系统开发文档
date: 2017-09-23 12:51:53
tags: Building

---
----

# 介绍

	博客系统的设计,目前停留在原型界面设计,后端框架,数据库表结构已经确定,框架基本搭建.正在申请腾讯QQ和微信的接口,先暂停下.
	2017-12-09
	
一个博客留言设计.
## 核心功能
- 用户管理
	- 注册
	- 登录
	- 增删改查
- 安全设置
	- 角色授权
	- 权限设置
- 博客管理
	- 发表博客
	- 编辑博客
	- 删除博客
	- 博客分类
	- 设置标签
	- 上传图片
	- 模糊查询
	- 最新/最热排序
	- 阅读量统计
- 评论管理
	- 发表评论
	- 删除评论
	- 评论量统计
- 点赞管理
	- 点赞/取消点赞
	- 点赞量统计
- 分类管理
	- 创建分类
	- 编辑分类
	- 删除分类
	- 分类查询
- 标签管理
	- 创建标签
	- 删除标签
	- 标签查询
- 首页搜索
	- 全文检索
	- 最新文章
	- 最热文章

## 核心技术
- 前端
	- Bootstrap
	- Thymeleaf
	- jQuery
	- Html5
	- JavaScript
	- CSS
- 后端
	- Spring
	- Spring Boot
	- Spring MVC
	- Spring Data
	- Spring Security
	- Hibernate
- 数据库
	- MySQL
	- H2
	- MongoDB
- 其他
	- ElasticSearch
	- Maven

# Thymeleaf
Java模板引擎
自然模板,原形即页面
使用OGNL,SpringEL
遵循Web标准,支持HTML5

## Thymeleaf标准方言
- `<span th:text="...">`  使用th表示
- `<span data-th-text="...">`  使用data-th表示

### 标准表达式
**变量表达式**
`${...}`
例如: `<span th:text="${book.author.name}">`
获取后端文件中的值

**消息表达式**
例如: `<span th:text="${book.author.name}">`
常用在国际化中,通过key获取文本的值.

**选择表达式**
例如: `<div th:object="${book}"> <span th:text="*{author.name}"> </div>`
获取当前对象中的值

**连接表达式**
例如: `<a th:href="@{../documents/report}">`相对当前页路径
例如: `<a th:href="@{~/documents/report}">`相对服务器路径
例如: `<a th:href="@{//documents/report}">` 相对于协议路径
例如: `<a th:href="@{http://www.XX.com/index}">` 绝对路径

**分段表达式**
`<div th:fragment="copy">`
例如: `<div th:insert="~{footer :: copy}">`
例如: `<div th:replace="~{footer :: copy}">`

### 字面量
**文本**
`<span th:text="'单引号引住'">`

**数字**
`<span th:text="1"> `
`<span th:text="1+1"> `

**比较**
大于`> (gt)`,小于`< (lt)`, 大于等于`>= (ge)` , 小于等于`<= (le)` , 等于`== (eq)` , 不等于`!= (ne)`
`<span data-th-if="${page.totalPages le 7}">` 判断小于等于7
**布尔值**
`<span th:if="${user.isAdmin()} == false">`

**为空**
`<span th:if="${user.name} == null">`

**三目运算符**
`<tr th:class="${row.even}? 'even' : 'odd' ">`

**无操作**
`<span th:text="${user.name}? :_ ">未登录</span>`
### 设置属性值
**设置任意属性值`th:attr`**
设置
``` html
<form action="subscribe.html" th:attr="action=@{/subscribe}">
	<fieldset>
		<input type="text" name="email" />
		<input type="submit" value="subscribe" th:attr="value=#{subscribe.submit}"
	</fieldset>
</form
```
经过渲染后,为
``` html
<form action="/forum/subscribe">
	<fieldset>
		<input type="text" name="email" />
		<input type="submit" value="subscribe"/>
	</fieldset>
</form
```

**设置指定属性值**

``` html
<form action="subscribe.html" th:action="@{/subscribe}">
	<fieldset>
		<input type="text" name="email">
		<input type="submit" value="subscribe" th:value="#{subscribe.submit}"
	</fieldset>
</form>
```

**设定固定值布尔属性**
固定布尔值
``` html
<input type="checkbox" name="option1" checked>			<!-- HTML -->
<input type="checkbox" name="option2" checked="checked"> <!-- XHTML -->
```
固定布尔值
传入`true`/`false`进行渲染
``` html
<input type="checkbox" name="active" th:checked="${user.active}" />
```

### 迭代器
**基本迭代器**

``` 
<li th:each="book : ${books}" th:text="${book.title}">
	 
</li>
```
**状态变量**
- `index` 索引,从0开始
- `count` 索引,从1开始
- `size` 总数
- `current` 当前
- `even/odd` 奇数/偶数
- `first/last` 开始/最后

### 条件语句
**`th:if`**

``` html
<a href="commens.html"
	th:href="@{/product/comments(prodId=${prod.id})}"
	th:if="${not #lists.isEmpty(prod.comments)}">
	view
</a>
```


**`th:unless`**
不成立的时候执行

**`switch`**
从前往后判断,只能有一个被判断为真
``` html
<div th:switch="${user.role}">
	<p th:case="'admin'">超级管理员</p>
	<p th:case="#{roles.manager}">管理员</p>
	<p th:case="*">普通用户</p>	
</div>
```

### 模板布局
通过预先设定的模板片段,便于在其他地方进行引用.

**定义片段**
``` html
<div th:fragment="copy">
	版权所有
</div>
```
**引用片段**
`footer`页面名称.`::`引用
``` html
<div th:insert="~{footer :: copy}"></div>
```

**通过ID引用**

``` html
<div id="copy">
	版权所有
</div>

<div th:insert="~{footer :: #copy}"></div>
```
**`th:insert`,`th:replace`,`th:include`三者区别**
- `th:insert` 简单地插入指定的片段作为正文的主标签.
- `th:replace` 用指定实际片段代替主标签
- `th:include` 类似于`th:insert`,但是只插入片段的内容

### 属性优先级
片段包含>>遍历标签>>条件判断>>本地修改>>属性修改>>特定属性修改>>属性
### 注释
**标准注释**
``` 
<!-- 注释 -->
```
**解析器注释**
执行时,删除内容
``` 
<!--/*-->
	这是注释内容
<!--*/-->
```
**原型注释块**
静态模板时被注释,执行时打开
``` 
<!--/*/
	<div> 执行时输出 </div>
/*/-->
```

### 内联
直接将文本写入到标签中.
- `[[...]]`或者`[(...)]`分别对应于`th:text` 和 `th:utext`.区别:会/不会转义特殊字符
- `th:inline="none"`禁用内联表达式,当出现在[]中括号较多的时候.
- `th:inline="css"` CSS内联样式
### 基本对象
- `#ctx` 上下文对象.
- `#locale` 本地相关联的请求.
- `param` 检索请求参数
- `session` 检索session属性
- `application` 检索application/servlet上下文属性
- `#request` 访问与当前请求相关的
- `#session` 访问与session相关的
- `#servletContext` 访问与servletContext相关的
### 工具对象

# 模块设计
## 用户模块
### API接口
| 方法 | API | 说明 |
|------|------|--------|
|GET | /users | 返回用户列表的 list.html 页面|
|GET | /users/{id} | 展现用户的 view.html 页面|
|GET | /user/form | 返回新增或者修改用户的form.html |
|POST| /users | 新增或者修改用户,成功后重定向到 list.html |
|GET | /users/delete/{id} | 根据用户id删除相应的用户数据,成功后重定向到list.html页面|
|GET| /users/modify/{id} | 根据id获取相应的用户数据,并返回form.html页面来执行修改|

### `User`用户模型类

### `UserRepository`用户资源库类

### `UserController`前端控制器类

### 页面编码
- `list.html` 展现用户列表
- `form.html` 新增或者修改用户资料
- `view.html` 查看用户资料
- `header.html` 共用的头部页面
- `footer.html` 共用的底部页面

页面默认放在`templates`目录下,其Spring已为我们增加了后缀`.html`

### 神坑
1. 页面不能有取值错误,否则页面显示错误,看后台报错


## Spring-Data实现数据持久化

H2在持久化后,数据库默认不再使用.


## 全文搜索
1. 建立文本库
2. 建立索引
3. 执行搜索
4. 过滤结果

### 开源实现
- Luncene
- ElasticSearch
- Solr

### ElasticSearch
分析,搜索引擎
分布式,高可用,多类型,多API,面向文档,异步导入,近实时,开源

**注意:**
需要下载搜索到本地,并开启

## 当前架构

**表示层:**
前端控制器,视图
**业务层:**
服务,实体,对象
**数据访问层:**
资源存储库

## 职责划分
之间通过RestFul的API进行交互
**博客系统**
关系型数据库储存业务
非关系数据库储存检索
**文件系统**
MongoDB


# 权限管理
## Spring Security
Spring全家桶,作为安全框架.

**认证:** 建立主体的过程.
**授权:** 是否决定主体进行某些操作

## 注意
注意与伪造跨站点访问csrf验证相驳论,关闭csrf访问
或者开启tooken验证

## 授权方式
- HTTP BASIC
- HTTP Digest
- HTTP X.509
- LDAP
- 基于表单认证
- OenID
- 单点登录
- Remember-Me
- 匿名

## 核心



