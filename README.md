# JustTest

GitHub 刷 contributions小绿点。

## java用法

cmd执行commit以及push，提交次数默认为1

- 本地commit
```
// 从20160506到20160507（含改天）每天提交两次
//                      起始时间      结束时间        次数
java -jar ss.jar -start 20160506 -end 20160507 -times 2

// 在20160901当天 提交两次
java -jar ss.jar 20160901 2
```

- push
```
git push origin master
```
