* 在 Bean 定义类中已经把上一章节中的 Object bean 替换为 Class，这样就可以把Bean 的实例化操作放到容器中处理了。
如果你有仔细阅读过上一章并做了相应的测试，那么你会发现 Bean 的实例化操作是放在初始化调用阶段传递给BeanDefinition 构造函数的。

* 在 DefaultSingletonBeanRegistry 中主要实现 getSingleton 方法，同时实现了一个
受保护的 addSingleton 方法，这个方法可以被继承此类的其他类调用。包括：
AbstractBeanFactory 以及继承的 DefaultListableBeanFactory 调用。

* AbstractBeanFactory 首先继承了 DefaultSingletonBeanRegistry，也就具备了使用单例注册类方法。

* 接下来很重要的一点是关于接口 BeanFactory 的实现，在方法 getBean 的实现过
程中可以看到，主要是对单例 Bean 对象的获取以及在获取不到时需要拿到 Bean
的定义做相应 Bean 实例化操作。那么 getBean 并没有自身的去实现这些方法，
而是只定义了调用过程以及提供了抽象方法，由实现此抽象类的其他类做相应实现。

* 后续继承抽象类 AbstractBeanFactory 的类有两个，包括：
AbstractAutowireCapableBeanFactory、DefaultListableBeanFactory，这两个类分别
做了相应的实现处理，接着往下看。

* 在 AbstractAutowireCapableBeanFactory 类中实现了 Bean 的实例化操作
newInstance，其实这块会埋下一个坑，有构造函数入参的对象怎么处理？可以
提前思考

* 在处理完 Bean 对象的实例化后，直接调用 addSingleton 方法存放到单例对
象的缓存中去。

* DefaultListableBeanFactory 在 Spring 源码中也是一个非常核心的类，在我们目前
的实现中也是逐步贴近于源码，与源码类名保持一致。

* DefaultListableBeanFactory 继承了 AbstractAutowireCapableBeanFactory 类，也
就具备了接口 BeanFactory 和 AbstractBeanFactory 等一连串的功能实现。所以有
时候你会看到一些类的强转，调用某些方法，也是因为你强转的类实现接口或继承
了某些类。

* 除此之外这个类还实现了接口 BeanDefinitionRegistry 中的
registerBeanDefinition(String beanName, BeanDefinition beanDefinition) 方法，当
然你还会看到一个 getBeanDefinition 的实现，这个方法我们文中提到过它是抽象
类 AbstractBeanFactory 中定义的抽象方法。现在注册 Bean 定义与获取 Bean 定义
就可以同时使用了，是不感觉这个套路还蛮深的。接口定义了注册，抽象类定义了
获取，都集中在 DefaultListableBeanFactory 中的 beanDefinitionMap 里

* 在此次的单元测试中除了包括；Bean 工厂、注册 Bean、获取 Bean，三个步骤，
还额外增加了一次对象的获取和调用。这里主要测试验证单例对象的是否正确的存
放到了缓存中。

* 此外与上一章节测试过程中不同的是，我们把 UserService.class 传递给了
BeanDefinition 而不是像上一章节那样直接 new UserService() 操作。

** TEST **

** 这里会有两次测试信息，一次是获取 Bean 时直接创建的对象，另外一次是从缓存中获取的实例化对象。 **

** 此外从调试的截图中也可以看到第二次获取单例对象，已经可以从内存中获取了， **