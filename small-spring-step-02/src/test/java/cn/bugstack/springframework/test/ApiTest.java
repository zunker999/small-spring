package cn.bugstack.springframework.test;

import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.bugstack.springframework.test.bean.UserService;
import org.junit.Test;


/**
 * @Desc
 */
public class ApiTest {

    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean 会调用beanFactory的各种父类的方法来获取bean，
        // 实际上单例DefaultSingletonBeanRegistry的singletonObjectsMap中是需要存储实例对象的，
        // beanDefinitionMap里面注册的只是bean的class，并不是对象，等到需要获取bean的时候，
        // 在通过class来实例化对象然后存到DefaultSingletonBeanRegistry的singletonObjectsMap
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4.第二次获取 bean from Singleton 直接从内存中就可以获取了
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }

}
