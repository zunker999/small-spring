package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.beans.factory.support.singleton.DefaultSingletonBeanRegistry;

/**
 * 抽象工厂，继承DefaultSingletonBeanRegistry，使其具备了单例注册和获取的功能
 *
 * @see #getBean 的方法供上层工厂实现类使用
 *
 * 下面两个方法相当于是模板方法的封装
 * @see #getBeanDefinition
 * @see #createBean
 *
 * AbstractBeanFactory 继承了 DefaultSingletonBeanRegistry ，是一个非常完整且强大的抽象类了，也能非常好的体现出它对模板模式的抽象定义
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 主要是对单例bean对象的获取，
     * 如果拿不到bean时，使用抽象方法#getBeanDefinition拿到bean的定义信息并调用另一个抽象方法#createBean来创建bean，具体实现由继承他的子类来实现
     */
    @Override
    public Object getBean(String name) throws BeansException {
        // 从singleton缓存中取值，如果不存在则创建并存入缓存
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        // 当singleton缓存缓存中没有的时候，则从子类DefaultListableBeanFactory的map缓存中查询，如果也没有则表示该bean没有注册
        BeanDefinition beanDefinition = getBeanDefinition(name);
        // 将从子类DefaultListableBeanFactory的map缓存中查询到的bean实例再次缓存到Singleton缓存map中，方便下次取值时直接从Singleton缓存中获取
        return createBean(name, beanDefinition);
    }

    /**
     * 只定义一个创建bean的抽象方法，具体实现由子类去实现
     */

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;


    /**
     * 只定义一个获取bean定义的抽象方法，具体实现由子类去实现
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;


}
