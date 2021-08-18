package cn.bugstack.springframework.test;

import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.bugstack.springframework.test.bean.UserService;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Desc
 */
public class ApiTest {


    /**
     * 从测试结果来看，跟上一章节对比，最大的变化就是可以满足带有构造函数的对象，可以被实例化了。
     * 可以尝试分别使用两种不同的实例化策略，来进行实例化。
     * @see #SimpleInstantiationStrategy()
     * @see #CglibSubclassingInstantiationStrategy()
     *
     * 在此次的单元测试中除了包括；Bean 工厂、注册 Bean、获取 Bean，三个步骤，
     * 还额外增加了一次对象的获取和调用。这里主要测试验证单例对象的是否正确的存放到了缓存中。
     *
     * 此外与上一章节测试过程中不同的是，我们把 UserService.class 传递给了BeanDefinition
     * 而不是像上一章节那样直接 new UserService() 操作。
     *
     */
    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 3. 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 4.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "小傅哥");
        userService.queryUserInfo();
    }



    /************************************************* 其他不同方式的实例化 **************************************************/
    /**
     * cglib实例化
     */
    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"小傅哥"});
        System.out.println(obj);
    }


    /**
     * 无构造函数的实例化
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void test_newInstance() throws IllegalAccessException, InstantiationException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }


    /**
     * 有构造函数的实例化
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void test_constructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("小傅哥");
        System.out.println(userService);
    }


    /**
     * 获取构造函数信息
     *
     * 这个案例中其实最核心的点在于获取一个类中所有的构造函数，其实也就是这个
     * 方法的使用 beanClass.getDeclaredConstructors()
     *
     * @throws Exception
     */
    @Test
    public void test_parameterTypes() throws Exception {
        Class<UserService> beanClass = UserService.class;
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor<?> constructor = declaredConstructors[0];
        Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService userService = declaredConstructor.newInstance("小傅哥");
        System.out.println(userService);
    }

}
