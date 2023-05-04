# 瀚诚软件企业应用脚手架（Java 17 + Spring Boot 3）

![CI Status](https://github.com/highsoft-shanghai/hare/actions/workflows/main.yml/badge.svg)
[![Coverage](.github/badges/jacoco.svg)](https://github.com/highsoft-shanghai/hare/actions/workflows/main.yml)

本项目旨在为单体类应用提供基本的项目启动模板，以减少迭代0的技术准备工作。本模板集成了部分通用功能（如认证与授权、身份管理、权限管理、组织架构及其管理等），
并完成了典型项目的大部分技术事务（如全局异常处理、上下文、应用框架、测试基础框架等），基于此，开发者可以快速完成迭代0的准备工作，并尽可能早地进入业务交付。

## 常用命令

为方便日常开发，项目预置了部分常用脚本，可以帮助项目成员快速完成一些常见的任务。

### 静态代码检查

良好的编码风格和编码格式是高质量软件的基础，时刻注意编码格式和风格是推荐的优秀实践。如需快速获得编码风格的问题反馈，请使用以下命令：

```bash
./scripts/check-static
```

### 本地CI检查

为了持续集成，项目提供了必要的基础设施，质量门禁便是其中之一。质量门禁提供了静态代码检查、单元测试、集成测试、测试覆盖率等各项质量检查项，以帮助团队守住质量基线，为持续集成提供必要的基础。
**开发每次推送代码至代码库前务必在本地执行质量门禁检查**，并且在代码被推送至代码仓库后，构建服务器也会触发质量门禁。本地执行质量检测直接执行以下命令：

```bash
./scripts/check-all
```

如果计算机资源充足（多核CPU、大内存、SSD），可使用并行检查缩短反馈时间：

```bash
./scripts/check-all-parallel
```
