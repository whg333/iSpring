package com.whg.iSpring.ch03.v2;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

public class ClassPathXmlResource implements BeanResource{

    private final Iterator<Element> elementIterator;

    public ClassPathXmlResource(String fileName){
        SAXReader saxReader = new SAXReader();
        URL xmlPath = getClass().getClassLoader().getResource(fileName);
        try {
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();
            elementIterator =  rootElement.elements().iterator();
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new BeanException(String.format("解析xml文件[%s]出错", fileName));
        }
    }

    @Override
    public boolean hasNext() {
        return elementIterator.hasNext();
    }

    @Override
    public Element next() {
        return elementIterator.next();
    }
}
