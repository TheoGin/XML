import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class Dom4jPraticeTest {
    @Test
    public void createBooks() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/books.xml"));

        Element rootElement = document.getRootElement();
        List<Element> books = rootElement.elements("book");

        for (Element book : books) {
            String id = book.attributeValue("id");
            String name = book.element("name").getText();
            String author = book.element("author").getText();
            double price = Double.parseDouble(book.element("price").getText());
            System.out.println(new Book(id, name, author, price));
        }

    }

}
