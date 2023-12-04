package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

/**
 * @Desc 实现生成bean实例的方法
 *
 * 这里就体现了类实现过程中的各司其职，你只需要关心属于你的内容
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 实现了父类中的createBean()方法，父类中的getBeanDefinition()方法由再次继承该类的子类来实现，各司其职
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 存放到单例缓存中
        addSingleton(beanName, bean);
        return bean;
    }

}
