package cn.bugstack.springframework.beans.factory.support.singleton;

import cn.bugstack.springframework.beans.factory.support.singleton.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例注册的实现类，主要是实现SingletonBeanRegistry中的getSingleton，
 * 同时实现一个受保护的addSingleton()供被继承者调用，用来缓存addSingleton()需要注册的bean
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 存储的是通过在DefaultSingletonBeanRegistry的singletonObjectsMap中存储的Bean的class通过单例生成的Bean的实例对象
     */
    private Map<String, Object> singletonObjectsMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjectsMap.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjectsMap.put(beanName, singletonObject);
    }

}
