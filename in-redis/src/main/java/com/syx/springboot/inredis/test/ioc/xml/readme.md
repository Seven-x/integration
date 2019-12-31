# 手动实现IOC 从xml配置文件读取

##步骤
####1.加载xml文件
####2.遍历beans标签，获取标签中id和class属性对应的类，并创建bean
####3.遍历bean标签，获取属性值，并设置属性值到bean
####4.将bean注册到bean容器内

`SimpleIoc     -ioc实现类`
`SimpleIocTest   -ioc测试类`
`Person`
`Student`
`ioc.xml        bean配置文件`