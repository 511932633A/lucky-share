代码挺赞的，基本挑不出什么问题，非常贴近实际项目。
特别是，做安全的方案市面上没有见到，和课上讲的玩法有点类似，但不通过zuul做转发，这样也挺好的。

存在的缺点：
配置混用:
```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: ALWAYS
  security:
      enabled: false
```
混用了spring boot 1.x和2.x的配置。

目前用的edgware sr1，建议实际项目采用greenwich，edgware快停止维护了。