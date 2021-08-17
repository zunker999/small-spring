package cn.bugstack.springframework.beans.factory.config;

/**
 * 单例注册表接口
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
