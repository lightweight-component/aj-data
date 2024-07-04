[![Maven Central](https://img.shields.io/maven-central/v/com.ajaxjs/ajaxjs-data?label=Latest%20Release)](https://central.sonatype.com/artifact/com.ajaxjs/ajaxjs-data)
[![Javadoc](https://img.shields.io/badge/javadoc-1.1.6-brightgreen.svg?)](https://dev.ajaxjs.com/docs/javadoc/aj-data/)
[![License](https://img.shields.io/badge/license-Apache--2.0-green.svg?longCache=true&style=flat)](http://www.apache.org/licenses/LICENSE-2.0.txt)
[![Email](https://img.shields.io/badge/Contact--me-Email-orange.svg)](mailto:frank@ajaxjs.com)
[![QQ群](https://framework.ajaxjs.com/static/qq.svg)](https://shang.qq.com/wpa/qunwpa?idkey=3877893a4ed3a5f0be01e809e7ac120e346102bd550deb6692239bb42de38e22)

# Lightweight Data Access Object in Java

Tutorial: https://framework.ajaxjs.com/docs/aj/?section=data. Java Documents: https://dev.ajaxjs.com/docs/javadoc/aj-data/.

AJ-Data boosts the CRUD development, not just an ORM library. There's three layers on architecture:

- JDBC Connection, JDBC reader and writer. Inputs SQL String and Map params then outputs a value, or a Map, or a Java Bean, or a List.
- CRUD Service, is a layer on top of the first layer, also the extender: FastCRUD provides Common Implementation for rapidly CRUD without a lot of coding, plus a simple REST API is ready.
- DataService, is a layer on top of the second layer, to write your own SQL or logic on the Web page, then REST API is ready.

There's two main Data Format that we support, regardless of input or output parameters:

- Map<String, Object>
- Java Bean

Supports Database:

- MySQL
- SQLite
- Derby

# Install
Requires Java 1.8+, Maven Snippets:

```xml
<dependency>
    <groupId>com.ajaxjs</groupId>
    <artifactId>ajaxjs-data</artifactId>
    <version>1.1.6</version>
</dependency>
```


# 3rd Party Component that using
This project based on Spring Framework, tested on Spring 5.x.

- Tomcat JDBC Connection Pool
- [Snowflake](https://github.com/twitter/snowflake) 使用了 Twitter 的分布式自增 ID 算法 Snowflake：雪花生成器
- JSqlParser, for list paging and SQL Injection protection
- Apache Derby for unit test

