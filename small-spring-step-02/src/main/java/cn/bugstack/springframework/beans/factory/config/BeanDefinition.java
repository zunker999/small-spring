package cn.bugstack.springframework.beans.factory.config;

/**
 * @Desc 容器内注册的是Bean的Class，并不是Bean的Object对象
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
