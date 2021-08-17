package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

/**
 * 抽象工厂，继承Bean工厂的单例实现类，并创造通用的统一的
 * @see #getBean 的方法供上层工厂实现类使用
 *
 * 下面两个方法相当于是模板方法的封装
 * @see #getBeanDefinition
 * @see #createBean
 *
 * AbstractBeanFactory 继承了 DefaultSingletonBeanRegistry ，是一个非常完整且强大的抽象类了，也能非常好的体现出它对模板模式的抽象定义
 *
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

}
