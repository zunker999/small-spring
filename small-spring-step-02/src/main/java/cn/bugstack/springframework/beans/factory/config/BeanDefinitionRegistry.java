package cn.bugstack.springframework.beans.factory.config;

/**
 * 定义一个bean注册的接口
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
