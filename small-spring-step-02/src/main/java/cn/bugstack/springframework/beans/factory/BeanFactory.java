package cn.bugstack.springframework.beans.factory;

import cn.bugstack.springframework.beans.BeansException;

/**
 * @Desc Bean工厂的接口
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

}
