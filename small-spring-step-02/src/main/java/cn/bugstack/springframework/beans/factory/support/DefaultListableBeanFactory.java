package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc 通过模板模式，各司其职，继承父类的方法完成对bean的创建获取等
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

  // 存储的是Bean注册进来的class，在获取Bean的时候会生成实例然后存储在DefaultSingletonBeanRegistry的singletonObjectsMap中，
  // 等到下次获取时直接就拿到的是单例生成的对象了，不会再次生成对象
  private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

  @Override
  public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
    beanDefinitionMap.put(beanName, beanDefinition);
  }

  @Override
  public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
    BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
    if (beanDefinition == null) {
      throw new BeansException("No bean named '" + beanName + "' is defined");
    }
    return beanDefinition;
  }

}
