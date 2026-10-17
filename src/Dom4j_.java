import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMNodeHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class Dom4j_ {

    @Test
    public void loadXML() throws SAXException, DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/students.xml"));
        System.out.println(document);
    }

    @Test
    public void listAllStudentsInfo() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/students.xml"));
        Element rootElement = document.getRootElement();
        List<Element> students = rootElement.elements("student");
        System.out.println(students.size());
        for (Element student : students) {
            Element name = student.element("name");
            Element gender = student.element("gender");
            Element age = student.element("age");
            Element resume = student.element("resume");
            System.out.println("学生信息 = " + name.getText() + " " + gender.getText() + " " + age.getText() + " " + resume.getText());
        }
    }


    @Test
    public void readOneStudent() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/students.xml"));
        Element rootElement = document.getRootElement();

        List<Element> students = rootElement.elements("student");
        Element student = students.get(1);
        System.out.println("该学生的信息= " + student.element("name").getText() + " " + student.element("gender").getText() + " " + student.element("age").getText() + " " + student.element("resume").getText());
        System.out.println("id=" + student.attributeValue("id"));
    }


    /**
     * 加元素(要求: 添加一个学生到xml中) [不要求，使用少，了解]
     * @throws Exception
     * <student id="04">
     *     <name>宋江</name>
     *     <age>23</age>
     *     <resume>梁山老大</resume>
     *   </student>
     */
    @Test
    public void addOneStudent() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/students.xml"));

        Element rootElement = document.getRootElement();

        Element student = DocumentHelper.createElement("student");
        student.addAttribute("id", "04");
        student.addElement("name").setText("宋江");
        student.addElement("age").setText("23");
        student.addElement("resume").setText("梁山老大");
        rootElement.add(student);

        OutputFormat prettyPrint = OutputFormat.createPrettyPrint();
        System.out.println("prettyPrint = " + prettyPrint);
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("src/students.xml")), prettyPrint);
        xmlWriter.write(document);
        xmlWriter.close();

        System.out.println("添加成功");
    }

    @Test
    public void deleteOneStudent() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/students.xml"));
        Element rootElement = document.getRootElement();
        List<Element> students = rootElement.elements("student");
        Element student = students.get(2);

        student.getParent().remove(student);

        OutputFormat prettyPrint = OutputFormat.createPrettyPrint();
        prettyPrint.setEncoding("UTF-8");

        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("src/students.xml")), prettyPrint);
        xmlWriter.write(document);
        xmlWriter.close();
        System.out.println("删除成功");
    }

    @Test
    public void updateAllStudentsAge() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/students.xml"));

        Element rootElement = document.getRootElement();
        List<Element> students = rootElement.elements("student");

        for (Element student : students) {
            Element age = student.element("age");
            age.setText(String.valueOf(Integer.parseInt(age.getText()) + 3));
        }
        OutputFormat prettyPrint = OutputFormat.createPrettyPrint();
        prettyPrint.setEncoding("UTF-8");

        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("src/students.xml")), prettyPrint);
        xmlWriter.write(document);
        xmlWriter.close();
        System.out.println("更新成功");
    }
}
