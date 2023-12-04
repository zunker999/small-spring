package cn.bugstack.springframework.beans.factory;

import cn.bugstack.springframework.beans.BeansException;

/**
 * 定义一个获取Bean的工厂的接口，在实现类中对外就是getBean，但是实际上最终还是调取的#SingletonBeanRegistry里面的getSingleton()
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

}
