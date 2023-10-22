package ru.dstu.laba3;

import ru.dstu.laba3.models.Book;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class Library {
    private List<Book> books = new ArrayList<>();

    // Геттеры и сеттеры

    public void addBook(Book book) {
        books.add(book); // Add the book to the internal list
        saveBooksToXML("D:/ooplabi/oop_laba3/Laba3/src/main/resources/books.xml"); // Update the XML file
    }


    public void removeBook(String title) {
        // Find the book to remove from the internal list
        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove != null) {
            books.remove(bookToRemove);
            saveBooksToXML("D:/ooplabi/oop_laba3/Laba3/src/main/resources/books.xml"); // Update the XML file
        }
    }


    public List<Book> filterBooks(String filter) {
        List<Book> filteredBooks = new ArrayList<>();


        for (Book book : books) {
//            if (book.getGenre().equalsIgnoreCase(filter)) {
//                filteredBooks.add(book);
//            }
            filteredBooks.add(book);
        }

        return filteredBooks;
    }




    public void loadBooksFromXML(String filePath) {
        try {
            File xmlFile = new File(filePath);

            if (xmlFile.exists()) {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);

                NodeList bookNodes = doc.getElementsByTagName("book");

                for (int i = 0; i < bookNodes.getLength(); i++) {
                    Element bookElement = (Element) bookNodes.item(i);
                    String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                    String author = bookElement.getElementsByTagName("author").item(0).getTextContent();
                    String genre = bookElement.getElementsByTagName("genre").item(0).getTextContent();

                    Book book = new Book(title, author, genre);
                    System.out.println(book.getTitle());
                    books.add(book);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveBooksToXML(String filePath) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("library");
                doc.appendChild(rootElement);

                for (Book book : books) {
                    Element bookElement = doc.createElement("book");
                    rootElement.appendChild(bookElement);

                    Element titleElement = doc.createElement("title");
                    titleElement.appendChild(doc.createTextNode(book.getTitle()));
                    bookElement.appendChild(titleElement);

                    Element authorElement = doc.createElement("author");
                    authorElement.appendChild(doc.createTextNode(book.getAuthor()));
                    bookElement.appendChild(authorElement);

                    Element genreElement = doc.createElement("genre");
                    genreElement.appendChild(doc.createTextNode(book.getGenre()));
                    bookElement.appendChild(genreElement);
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(filePath));

                transformer.transform(source, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
