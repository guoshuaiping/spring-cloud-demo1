**Feign的最佳实践**

实现方案：将FeignClient抽取为独立模块，并且把接口有关的Domain实体、默认的Feign配置都放到这个模块中，提供给所有消费者使用

具体步骤如下：

1. 首先创建一个module，最简单的maven项目，命名为feign-api，然后引入feign的starter依赖
2. 将order-service中编写的UserClient、DefaultFeignConfiguration都复制到feign-api项目中
3. 将user-service中编写的User实体复制到feign-api项目中
4. 在order-service中引入feign-api的依赖
5. 修改order-service中的所有与上述组件有关的import部分，改成导入feign-api中的包
6. 重启测试

注意：当order-service引入该模块后，重启order-services时编译没有报错，但是启动时报：'com.gsp.feign.clients.UserClient' that could not be found
编译不报错，证明这个UserClient类存在，但是没有注入成功，证明这个类没有创建对象，所以在spring没有找到该类的对象。
究其原因，是因为order-service默认的包扫描范围是启动类所在的包：com.gsp.order.service，而会扫描UserClient所在的包：package com.gsp.feign.clients
有两种方式解决：
方式一：指定FeignClient所在包（省事，可能会扫描到多余的不需要的对象）
@EnableFeignClients(basePackages="cn.gsp.feign.clients")
方式二：指定FeignClient字节码(精确引入，推荐)
@EnableFeignClients(clients={UserClient.class})
