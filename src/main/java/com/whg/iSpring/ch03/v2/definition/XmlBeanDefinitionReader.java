package com.whg.iSpring.ch03.v2.definition;

import com.whg.iSpring.ch03.v2.bean.BeanResource;
import org.dom4j.Element;

import java.util.List;

public class XmlBeanDefinitionReader {

    private final SimpleBeanFactory beanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinition(BeanResource beanResource){
        while(beanResource.hasNext()){
            Element element = (Element) beanResource.next();
            String id = element.attributeValue("id");
            String className = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(id, className);

            List<Element> argElements = element.elements("constructor-arg");
            BeanConstructorArgs args = new BeanConstructorArgs();
            for(Element e:argElements){
                String type = e.attributeValue("type");
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                args.addArg(new BeanConstructorArg(type, name, value));
            }
            beanDefinition.setArgs(args);

            List<Element> propertyElements = element.elements("property");
            BeanProperties properties = new BeanProperties();
            for(Element e:propertyElements){
                String type = e.attributeValue("type");
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                String ref = e.attributeValue("ref");
                properties.addProperty(new BeanProperty(type, name, value, ref));
            }
            beanDefinition.setProperties(properties);

            beanFactory.registerBeanDefinition(beanDefinition);
        }
    }

}
