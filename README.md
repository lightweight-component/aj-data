[![Maven Central](https://img.shields.io/maven-central/v/com.ajaxjs/ajaxjs-data?label=Latest%20Release)](https://central.sonatype.com/artifact/com.ajaxjs/ajaxjs-data)
[![Javadoc](https://img.shields.io/badge/javadoc-1.1.6-brightgreen.svg?)](https://dev.ajaxjs.com/docs/javadoc/aj-data/)
[![License](https://img.shields.io/badge/license-Apache--2.0-green.svg?longCache=true&style=flat)](http://www.apache.org/licenses/LICENSE-2.0.txt)
[![Email](https://img.shields.io/badge/Contact--me-Email-orange.svg)](mailto:frank@ajaxjs.com)
[![QQ群](https://framework.ajaxjs.com/static/qq.svg)](https://shang.qq.com/wpa/qunwpa?idkey=3877893a4ed3a5f0be01e809e7ac120e346102bd550deb6692239bb42de38e22)

# Lightweight Data Access Object in Java

Tutorial: https://framework.ajaxjs.com/docs/aj/?section=data. Java Documents: https://dev.ajaxjs.com/docs/javadoc/aj-data/.

AJ-Data boosts the CRUD development, not just an ORM library. There's three layers in architecture:

1. JDBC Connection, JDBC reader and writer, is like Spring JDBCTemplate or Apache Dbutils. It accepts SQL String and Map params then outputs a value, or a Map, or a Java Bean, or a List.
1. CRUD Service, is a layer on top of the first layer, also the extender: FastCRUD provides Common Implementation for rapidly CRUD operations without a lot of coding, plus a simple REST API is readily available.
1. DataService, is a layer on top of the second layer, to write your own SQL or logic on the Web page, then REST API is prepared.

The style of API is based on CRUD(Create, Read, Update, Delete), For greater precision, let's delineate the topic into the following specific aspects:

- Single Value Retrieval: to get a value, int or other numbers, or string, boolean, etc.
- Detail Retrieval: to get a detail(info), a Map or a Java Bean, return as JSON Object
- List Retrieval: to get list, might be a list of Map or a list of Java Bean, return as JSON Array
- Paged List Retrieval: to get paging list, with the field 'total' for the count
- Record Creation: to create a record, return the ID of the record
- Record Update: to update a record, return the boolean value that the record is updated
- Record Deletion: to delete a record, return the boolean value that the record is deleted
- Batch Updates: Performing multiple update operations in a single batch process
- Custom SQL Execution: Executing custom SQL queries, complemented by Java code to ensure the completion of complex business logic

We support two primary data formats for both input and output parameters:


- Map<String, Object>
- Java Bean

Currently supported databases (with more to come):

- MySQL
- SQLite
- Derby

For more information, please visit https://framework.ajaxjs.com/docs/aj/?section=data.

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
This project's based on Spring Framework, tested on Spring 5.x.

- Tomcat JDBC Connection Pool
- [Snowflake](https://github.com/twitter/snowflake) 使用了 Twitter 的分布式自增 ID 算法 Snowflake：雪花生成器
- JSqlParser, for list paging and SQL Injection protection
- Apache Derby for unit test

