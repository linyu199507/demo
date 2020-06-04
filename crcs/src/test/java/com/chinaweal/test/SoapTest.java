package com.chinaweal.test;

import com.chinaweal.sdcs.crcs.business.entity.Materialinfo;
import org.junit.Test;

import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SoapTest {

    @Test
    public void test1() throws Exception {
        String query = "公司";
        String url = " http://172.22.80.36:6888/aic/EntitySearchService.jws";
//        String url = "http://10.1.1.35:6888/aic/EntitySearchService.jws";
        // 创建连接
        SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConn = soapConnFactory.createConnection();
        // 创建消息对象
        MessageFactory messageFactory = MessageFactory.newInstance();
//        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPMessage soapMessage = messageFactory.createMessage();
        // 创建soap消息主体
        SOAPEnvelope envelope = soapMessage.getSOAPPart().getEnvelope();
        envelope.setPrefix("soapenv");
        envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
        envelope.addNamespaceDeclaration("def", "http://DefaultNamespace");
        envelope.removeNamespaceDeclaration("SOAP-ENV");
        envelope.getHeader().setPrefix("soapenv");
        SOAPBody body = envelope.getBody();
        body.setPrefix("soapenv");
        SOAPElement searchEntityElement = body.addChildElement("searchEntity", "def");
        searchEntityElement.setEncodingStyle(SOAPConstants.URI_NS_SOAP_ENCODING);
        searchEntityElement.addChildElement("entityname").addTextNode(query).setAttribute("xsi:type", "xsd:string");
        soapMessage.saveChanges();
        //输出请求消息体
        soapMessage.writeTo(System.out);
        System.out.println();
        /*
         * 实际的消息是使用 call()方法发送的，该方法接收消息本身和目的地作为参数，并返回第二个 SOAPMessage 作为响应。
         * call方法的message对象为发送的soap报文，url为mule配置的inbound端口地址。
         */
        URL urlObject = new URL(url);
        // 响应消息
        SOAPMessage response = soapConn.call(soapMessage, urlObject);
        String textContent = response.getSOAPBody().getFirstChild().getFirstChild().getTextContent();
        // 提取消息内容
        System.out.println(textContent);
        //输出流
        //sourceContent实现此接口的对象包含充当源输入（XML 源或转换指令）所需的信息
        //result充当转换结果的持有者，可以为 XML、纯文本、HTML 或某些其他格式的标记
        //返回结果
//        String xmlData = new String(out.toByteArray());
        //输出到控制台
//        System.out.println(xmlData);
//        //关闭连接
        soapConn.close();
    }

    @Test
    public void teset3() throws SOAPException, IOException, TransformerException {
        SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = soapConnFactory.createConnection();
        //Next, create the actual message
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();

        //Create objects for the message parts
        SOAPPart soapPart = message.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPBody body = envelope.getBody();
        SOAPElement bodyElement = body.addChildElement(envelope.createName("searchEntity", "def", "http://schemas.xmlsoap.org/soap/encoding/"));
        //Add content
        bodyElement.addChildElement("entityname").addTextNode("美的");
        //Save the message
        message.saveChanges();
        //Check the input
        System.out.println("\nREQUEST:\n");
        message.writeTo(System.out);
        System.out.println();
        //Set the destination
        URL destination = new URL("http://192.168.100.45:6888/aic/EntitySearchService.jws?wsdl");
        //Send the message
        SOAPMessage reply = connection.call(message, destination);
        //Check the output
        System.out.println("\nRESPONSE:\n");
        //Create the transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //Extract the content of the reply
        Source sourceContent = reply.getSOAPPart().getContent();
        //Set the output for the transformation
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
        System.out.println();
        //Close the connection
        connection.close();
    }

    @Test
    public void test3() {
//        String path = "\\download\\1440606002019111443085633\\QQ截图20191113162139.png";
//        System.out.println(path.substring(path.indexOf("download") + 8));
        List<Materialinfo> list = new ArrayList<>();
        list.add(new Materialinfo());
        System.out.println(list.isEmpty());
    }

}
