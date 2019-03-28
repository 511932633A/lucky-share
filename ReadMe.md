## 内容中心 ##
- 内容：	`/content/articles`
- 公告：`/content/notice/list`
- 兑换列表：`/content/exchange/list`
- 投稿：`/content/contribute`
- 我的投稿：`/content/article`

## 用户中心 ##
- 认证/授权： `/user/login`
## 积分中心 ##
- 签到： `/bonus/sign`
- 余额： `/bonus/balance`
- 明细： `/bonus/details`
- 交易： `/bonus/tran` (注意：该接口不对外暴露，只有内部服务调用)
## 注意：
### 1.为了开发方便，各服务共用同一个库，这里实际是独立的DB。
### 2.这里未接入微信，在登录的时候，openid为随机生成
### 3. 