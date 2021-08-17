package cn.bugstack.springframework;

/**
 * @Desc
 */
public class BeanDefinition {

    /**
     * 此处定义的Bean注册为Object对象，
     * 但是实际Spring中不会吧实例化信息注册到Spring容器中,真实的是注册Class到Spring容器中的
     */
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

}
