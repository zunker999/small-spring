充分体现了每个类各司其职的思想，同时又相互嵌套

DefaultListableBeanFactory
1、实现了接口BeanDefinitionRegistry中的注册bean的方法；
2、实现了父类AbstractBeanFactory中的getBeanDefinition获取bean定义信息的方法


AbstractAutowireCapableBeanFactory
1、实现了父类AbstractBeanFactory中的createBean方法来实例化bean


AbstractBeanFactory
1、实现了父类DefaultSingletonBeanRegistry中的getBean方法
2、定义了createBean()和getBeanDefinition()两个抽象方法
3、典型的模板模式，getBean() 中定义好方法的执行顺序：先getBeanDefinition() 再createBean()


DefaultSingletonBeanRegistry
1、实现了接口SingletonBeanRegistry的getSingleton()方法；
2、定义了Map<String, Object> singletonObjectsMap 缓存；
3、定义了添加缓存的方法addSingleton();