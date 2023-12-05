package cn.bugstack.springframework.beans.factory.instantiate;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import java.lang.reflect.Constructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;


/**
 * cglib的实例化bean的方式
 * 使用了CGLIB库中的Enhancer类来创建一个被增强（enhanced）的对象
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    /**
     * 接收一个BeanDefinition（Bean的定义信息）、bean的名称、构造函数（可能为null）、以及构造函数参数，并且可能抛出BeansException异常
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        // 创建了一个CGLIB Enhancer对象，该对象用于创建被增强的类的实例 ，Enhancer(增强)
        Enhancer enhancer = new Enhancer();
        // 设置要增强的类，即beanDefinition中指定的类
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        // 设置一个回调，这里使用了一个NoOp（No Operation）类的匿名子类。这个回调类中重写了hashCode方法，但只是简单地调用了父类的hashCode方法
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (null == ctor) {
            return enhancer.create();
        }
        // 如果构造函数不为null，调用enhancer.create()方法并传递构造函数的参数类型和参数值数组来创建被增强的对象。
        return enhancer.create(ctor.getParameterTypes(), args);
    }

}
