package cn.bugstack.springframework.beans.factory.instantiate;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import java.lang.reflect.Constructor;

/**
 * <p>
 * Bean 实例化策略
 */
public interface InstantiationStrategy {

    /**
     * instantiate 实例化
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
