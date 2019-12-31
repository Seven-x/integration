package com.syx.springboot.inredis.test.ioc.xml;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

/**
 * @author shaoyx
 * @date 9:46  2019/12/31
 */
public class SimpleIoc {

    private Map<String, Object> beanMap = Maps.newHashMap();

    public SimpleIoc(String location) throws Exception {
        loadBeans(location);
    }

    /**
     * 加载配置文件
     *
     * @param location
     * @return
     */
    private void loadBeans(String location) throws Exception {
        // 1 加载配置文件
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        NodeList list = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream inputStream = new FileInputStream(location);
            Document document = builder.parse(inputStream);
            Element element = document.getDocumentElement();
            list = element.getChildNodes();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(list)){
            throw new IllegalArgumentException("NodeList is null");
        }

        // 2 遍历beans标签，获取标签中id和class属性对应的类，并创建bean
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node instanceof Element){
                Element element = (Element) node;
                String id = element.getAttribute("id");
                String className = element.getAttribute("class");

                // 加载class
                Class clazz = Class.forName(className);

                if (Objects.isNull(clazz)){
                    continue;
                }
                // 创建bean
                Object bean = clazz.newInstance();

                // 3 遍历bean标签，获取属性值，并设置属性值到bean
                NodeList properties = element.getElementsByTagName("property");
                for (int j = 0; j < properties.getLength(); j++) {
                    Node property = properties.item(i);
                    if (property instanceof Element){
                        Element elementProperty = (Element) property;
                        String name = elementProperty.getAttribute("name");
                        String value = elementProperty.getAttribute("value");

                        // 利用反射将 bean 相关字段访问权限设为可访问
                        Field declaredField = bean.getClass().getDeclaredField(name);
                        declaredField.setAccessible(true);

                        if (StringUtils.isNotBlank(value)){
                            declaredField.set(bean, value);
                        }
                    }
                }

                // 4 将bean注册到bean容器内
                registerBean(id, bean);
            }
        }
    }

    /**
     * 根据id获取bean
     *
     * @param id
     * @return
     */
    public Object getBean(String id) {
        Object bean = beanMap.get(id);
        if (bean == null) {
            throw new IllegalArgumentException("不存在bean， beanName:{}" + id);
        }
        return bean;
    }

    /**
     * 注册bean
     *
     * @param id
     * @param bean
     * @return
     */
    private void registerBean(String id, Object bean) {
        beanMap.put(id, bean);
    }
}
