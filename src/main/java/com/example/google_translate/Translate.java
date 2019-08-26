package com.example.google_translate;

import java.io.IOException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Translate {

    private static final char[] CHARS = new char[] { '<', '>', '"', '\\' };

    @SuppressWarnings({ "unchecked"})
    public static void main(String[] args) throws IOException, DocumentException {
        if (args == null || args.length == 0) {
            System.out.println("请输入正确参数！");
            return;
        }
        String to = args[0];

        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("in/strings.xml"));
        Element element = document.getRootElement();
        List<Element> elements = element.elements();
        GoogleTranslateHelper helper = new GoogleTranslateHelper();

        Document out = DocumentHelper.createDocument();
        Element outRoot = DocumentHelper.createElement("resources");
        out.setRootElement(outRoot);

        for (Element element2 : elements) {
            StringBuilder log = new StringBuilder();
            log.append(element2.attributeValue("name"));

            if ("true".equals(element2.attributeValue("translatable", "true"))) {
                String text = element2.getText();
                Element string = DocumentHelper.createElement(element2.getName());
                if (exist(text) || "".equals(text) || text == null) {
                    string.setText(text);
                } else {
                    string.setText(helper.translate(text, "en", to));
                }
                string.addAttribute("name", element2.attributeValue("name"));
                outRoot.add(string);
                log.append(":").append(text).append(" ==> ").append(string.getText());
            } else {
                log.append(":translatable = false");
            }

            System.out.println(log);
        }
        File file = new File("out/");
        if (!file.exists()) {
            file.mkdirs();
        }
        PrintWriter outXml = new PrintWriter(new File(file, "string-" + to +".xml"), "utf-8");
        outXml.print(out.asXML());
        outXml.flush();
        outXml.close();
    }

    public static boolean exist(String text) {
        for (char c : CHARS) {
            if (text.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }
}
