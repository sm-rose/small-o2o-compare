<div align="center">

<a href="README_CN.md">中文版</a>

![](o2o-logo.png)

# small-o2o-compare: Oracle ↔ OceanBase Metadata Comparison Tool

**Compare database objects between Oracle and OceanBase (OB-Oracle mode). Generate Excel diff reports and fix SQL scripts — built for "去O" (de-Oracle) migration.**

[![Java CI](https://img.shields.io/github/actions/workflow/status/sm-rose/small-o2o-compare/maven.yml?logo=github&label=build)](https://github.com/sm-rose/small-o2o-compare/actions)
[![Java](https://img.shields.io/badge/Java-1.8+-blue)](https://java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.14-green)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-GPL%203.0-blue)](LICENSE)
[![GitHub release](https://img.shields.io/github/v/release/sm-rose/small-o2o-compare)](https://github.com/sm-rose/small-o2o-compare/releases)
[![GitHub Stars](https://img.shields.io/github/stars/sm-rose/small-o2o-compare?logo=github)](https://github.com/sm-rose/small-o2o-compare)
[![Blog](https://img.shields.io/badge/blog-@small.rose-ff69b4)](https://zhangxiaocai.cn)

</div>

---

## Background

During Oracle-to-OceanBase ("去O") migration, comparing database objects between source and target is essential. While it's possible with raw SQL + Excel, this tool provides **horizontal alignment** and **automatic fix-SQL generation**, making the process much more efficient.

### Supported Comparisons

| Category | Supported | Fix SQL Gen |
|----------|-----------|-------------|
| OB-Oracle ↔ Oracle | ✅ | ✅ |
| Oracle ↔ Oracle | ✅ | ✅ |
| OB-Oracle ↔ OB-Oracle | ✅ | ✅ |
| OB-MySQL ↔ MySQL | 🚧 Planned | 🚧 |
| MySQL ↔ MySQL | 🚧 Planned | 🚧 |

### Database Objects

- [x] Tables & Partition Tables & Temp Tables
- [x] Columns (name, type, length, nullable, default value)
- [x] Indexes (unique, normal, function-based)
- [x] Primary Keys
- [x] Sequences
- [x] Views
- [x] Functions
- [x] Procedures
- [x] Packages & Package Bodies
- [x] Types
- [x] Synonyms (planned)

---

## Quick Start

### Prerequisites

- Java 8+
- Maven 3.6+
- Oracle 11gR2+ / OceanBase (OB-Oracle mode) instance

### Configuration

Edit `src/main/resources/application-dev.yml`:

```yaml
spring:
  datasource:
    # Source database (e.g. Oracle)
    source:
      url: jdbc:oracle:thin:@host:1521:orcl
      username: your_user
      password: your_pass
    # Target database (e.g. OceanBase)
    target:
      url: jdbc:oceanbase://host:2883/oceanbase
      username: your_user
      password: your_pass
```

### Build & Run

```bash
git clone https://github.com/sm-rose/small-o2o-compare.git
cd small-o2o-compare
mvn clean package -DskipTests
java -jar target/small-o2o-compare-0.0.1-SNAPSHOT.jar
```

### API

Once started, access Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

- **Compare metadata** → `/compare/execute`
- **Export Excel report** → `/compare/export`
- **Generate fix SQL** → `/compare/repair`

---

## Build from Source

```bash
git clone https://github.com/sm-rose/small-o2o-compare.git
cd small-o2o-compare
mvn clean package -DskipTests
```

Output JAR: `target/small-o2o-compare-0.0.1-SNAPSHOT.jar`

---

## SQL Reference

The SQL queries used for metadata comparison are documented in the original [README](https://github.com/sm-rose/small-o2o-compare) (backup) and the `docs/` directory.

Key helper functions:
- `LONG_TO_CHAR` — Convert `DATA_DEFAULT` LONG column to readable string
- `INDEX_COLUMN_EXPRESSION` — Extract function-based index expressions

---

## Change Log

See [change_log.md](change_log.md) (if available) or GitHub Releases.

---

## License

[GNU General Public License v3.0](LICENSE)
