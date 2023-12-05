package cn.bugstack.springframework.beans.factory.instantiate;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * JDK实例化bean
 * 通过反射机制实例化一个对象，首先根据构造函数的有无分别处理，然后通过反射调用构造函数来创建对象。
 * 在这个过程中，可能会捕获一些反射相关的异常，并将其转换为BeansException抛出。
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    /**
     * 接收一个BeanDefinition（Bean的定义信息）、bean的名称、构造函数（可能为null）、以及构造函数参数，并且可能抛出BeansException异常
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        // 从给定的BeanDefinition中获取Bean的Class对象。
        Class clazz = beanDefinition.getBeanClass();
        try {
            // 如果构造函数不为null，通过反射获取目标类的指定构造函数并使用newInstance方法创建实例，传递给定的构造函数参数。
            if (null != ctor) {
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                // 如果构造函数为null，直接通过反射获取无参构造函数并使用newInstance方法创建实例。
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }

}
