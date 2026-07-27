<div align="center">

<a href="README.md">English</a>

![](o2o-logo.png)

# small-o2o-compare：Oracle ↔ OceanBase 数据库元数据对比工具

**Oracle 与 OceanBase（OB-Oracle 模式）数据库对象对比。输出 Excel 差异报告和修复 SQL，专为"去O"迁移场景设计。**

[![Java CI](https://img.shields.io/github/actions/workflow/status/sm-rose/small-o2o-compare/maven.yml?logo=github&label=build)](https://github.com/sm-rose/small-o2o-compare/actions)
[![Java](https://img.shields.io/badge/Java-1.8+-blue)](https://java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.14-green)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-GPL%203.0-blue)](LICENSE)
[![GitHub release](https://img.shields.io/github/v/release/sm-rose/small-o2o-compare)](https://github.com/sm-rose/small-o2o-compare/releases)
[![GitHub Stars](https://img.shields.io/github/stars/sm-rose/small-o2o-compare?logo=github)](https://github.com/sm-rose/small-o2o-compare)
[![博客](https://img.shields.io/badge/blog-@small.rose-ff69b4)](https://zhangxiaocai.cn)

</div>

---

## 诞生背景

"去O"过程中经常需要对比源端（Oracle）和目标端（OceanBase）的数据库对象差异。虽然可以用 SQL + Excel 手工对比，但横向对齐度和修复语句生成比较麻烦。本工具解决了这些问题。

### 支持对比类型

| 类型 | 支持 | 修复 SQL |
|------|------|---------|
| OB-Oracle ↔ Oracle | ✅ | ✅ |
| Oracle ↔ Oracle | ✅ | ✅ |
| OB-Oracle ↔ OB-Oracle | ✅ | ✅ |
| OB-MySQL ↔ MySQL | 🚧 规划中 | 🚧 |
| MySQL ↔ MySQL | 🚧 规划中 | 🚧 |

### 数据库对象覆盖

- [x] 表、分区表、临时表
- [x] 列（名称、类型、长度、非空、默认值）
- [x] 索引（唯一索引、普通索引、函数索引）
- [x] 主键
- [x] 序列
- [x] 视图
- [x] 函数
- [x] 存储过程
- [x] 包（PACKAGE + PACKAGE BODY）
- [x] TYPE 集合
- [x] 同义词（规划中）

---

## 快速开始

### 环境要求

- Java 8+
- Maven 3.6+
- Oracle 11gR2+ / OceanBase（OB-Oracle 模式）

### 数据源配置

编辑 `src/main/resources/application-dev.yml`：

```yaml
spring:
  datasource:
    # 源端数据库（如 Oracle）
    source:
      url: jdbc:oracle:thin:@host:1521:orcl
      username: your_user
      password: your_pass
    # 目标端数据库（如 OceanBase）
    target:
      url: jdbc:oceanbase://host:2883/oceanbase
      username: your_user
      password: your_pass
```

### 构建与启动

```bash
git clone https://github.com/sm-rose/small-o2o-compare.git
cd small-o2o-compare
mvn clean package -DskipTests
java -jar target/small-o2o-compare-0.0.1-SNAPSHOT.jar
```

### API 接口

启动后访问 Swagger UI：

```
http://localhost:8080/swagger-ui.html
```

- **元数据对比** → `/compare/execute`
- **导出 Excel 报告** → `/compare/export`
- **生成修复 SQL** → `/compare/repair`

---

## 源码构建

```bash
git clone https://github.com/sm-rose/small-o2o-compare.git
cd small-o2o-compare
mvn clean package -DskipTests
```

JAR 包位置：`target/small-o2o-compare-0.0.1-SNAPSHOT.jar`

---

## SQL 参考

元数据对比所用的 SQL 查询语句详见原 README 备份及 `docs/` 目录。

辅助函数：
- `LONG_TO_CHAR` — 将 `DATA_DEFAULT` 的 LONG 类型转为可读字符串
- `INDEX_COLUMN_EXPRESSION` — 提取函数索引的表达式

---

## 更新日志

见 GitHub Releases。

---

## 许可证

[GNU General Public License v3.0](LICENSE)
