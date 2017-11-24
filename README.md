## 项目开发文档
#### 一. 项目代码规范
- TODO规范：
  1. 每个人都会有自己名字的TODO，如果你将自己的TODO完成之后，在自己的名字后面加上冒号，完成时间，和完成。（原因是不知道你们写的东西是否正确，我需要检查，方便修改）举例如下：
   ```
   //TODO：2017/11/17加一个判断跟处理，破产后不再花钱：蔡寒均:2017/11/19完成
   ```

- 代码格式规范：
   1. 每个类 先把getter setter放前面 

      然后是构造器 

      把其他方法放后面 

      方便浏览。

   2. 命名规范：

      基本操作哦，如果有问题，参照文章：[JAVA学习之规范篇](http://www.jianshu.com/p/45325263c17d)

- 日志规范：

   1. 在日志内将自己的完成工作和，对项目的意见提出，或者团队管理方式提出，或者将分工方式，工作量的事可以提一提。如果是项目没看懂或者代码不会写就线下问了。当然如果想写也可以写。总之可以随便写。
   2. 格式参照现有日志。


## 项目开发日志：

> 11.17	20:40	何剑冲
>
> model-各个小区加了一些未实现的方法 希望将每次航程抽象成一个方法在Game 中 View中希望添加一个信息面板 把SYSTEM.OUT的东西放进去
> 希望统一代码格式 即 每个类 先把getter setter放前面 然后是构造器 把其他方法放后面 方便浏览

>11.18	00:49	何剑冲
>
>加了MODEL一些TODO 明天没空 蔡总先去研究每一轮航程的实现 想办法抽象出去

> 11.19	22:14	蔡寒均
>
> 将项目内的一些TODO分工，规定一些规范。

> 11.20	22:00	何剑冲
>
> 已完成部分todo 望添加部分方法

> 11.22	23:22	蔡寒均
>
> 将海盗和保险公司的View画了。可以开始写他们的Controller。写Controller时可以参照PlayGroundView中的添加监听事件的位置和方法。
>
> 新加入类解释：AreaView类是区域的抽象类，PirateAreaView和InsuranceAreaView继承至AreaView，方便画位置。
>
> 出现的问题：重置小船的按钮失去了作用，希望何剑冲看一下。

>11.23 21.34 范贤明
>
>完成了对破产的判断和对之后的支付相关的情况进行判断 对持有股票状态的读取和修改

>11.23 23.03 何剑冲
>
>加入了船长竞选的检测 初步完成海盗和保险公司的加入

>11.23  22:56 蔡寒均
>
>完成了海盗区和保险公司View的加入。

>11.20  22:56 蔡寒均
>
>修船厂的加入，但未加入船厂中船的位置

>11.23 21:50 郑抗
>
>shipyard中todo实现

>11.24 20:11 郑抗
>
>avigator中todo实现以及修改shipyard的实现
