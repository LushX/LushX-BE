package cn.mailu.LushX.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.Iterator;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 1:19 2017-11-30
 * @Modified By:
 */
public class XmlUtils {
    static String realUrl = "";

    public static String parseFile(String xml){
        SAXReader reader = new SAXReader();
        String url = null;
        try {
            Document document = reader.read(new ByteArrayInputStream(xml.getBytes()));
            Element rootElement = document.getRootElement();
            url = listNodes(rootElement);
            return url;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return url;
    }

    //遍历当前节点下的所有节点
    public static String listNodes(Element node){

        //如果当前节点为file  且内容不为空，则输出
        if(!(node.getTextTrim().equals("")) && node.getName().equals("file")){
            realUrl = realUrl + node.getText() + ";";
        }
        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while(iterator.hasNext()){
            Element e = iterator.next();
            listNodes(e);
        }
        return realUrl;
    }

}
