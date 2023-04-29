import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class main {

    public static void main(String[] args) {
        if (args.length == 0) { // Checking whether an input file name is provided as a command-line argument
            System.out.println("To run the program: java ARXMLSorter <your_file> ");
            return;
        }

        String inputName = args[0];
        String outputName = getOutputName(inputName);

        try {
            validateFileExtension(inputName);
            
            File inputFile = new File(inputName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(inputFile);
            document.getDocumentElement().normalize();
            
            if (document.getDocumentElement().getChildNodes().getLength() == 0) {
                throw new EmptyAutosarFileException("Input ARXML file is empty.");
            }

            NodeList containerList = document.getElementsByTagName("CONTAINER");
            ArrayList<Element> containers = new ArrayList<Element>();
            for (int i = 0; i < containerList.getLength(); i++) {
                containers.add((Element) containerList.item(i));
            }

            Collections.sort(containers, new Comparator<Element>() {
                public int compare(Element e1, Element e2) {
                    String n1 = e1.getElementsByTagName("SHORT-NAME").item(0).getTextContent();
                    String n2 = e2.getElementsByTagName("SHORT-NAME").item(0).getTextContent();
                    return n1.compareTo(n2);
                }
            });

            Element rootElement = document.getDocumentElement();
            for (Element container : containers) {
                rootElement.appendChild(container);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(outputName));
            transformer.transform(source, result);
            
            System.out.println("Process is successed, new output is " + outputName);
        } catch (NotVaildAutosarFileException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (SAXException | IOException e) {
        	System.err.println("Error: Input file is EMPTY ! " );
        } catch (EmptyAutosarFileException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getOutputName(String inputName) {
        int dotIndex = inputName.lastIndexOf(".");
        String fileNameWithoutExtension = inputName.substring(0, dotIndex);
        String fileExtension = inputName.substring(dotIndex);
        return fileNameWithoutExtension + "_mod" + fileExtension;
    }

    private static void validateFileExtension(String fileName) throws NotVaildAutosarFileException {
        if (!fileName.endsWith(".arxml")) {
            throw new NotVaildAutosarFileException("Input file isn't .arxml extension. ");
        }
    }
}

@SuppressWarnings("serial") //  Telling the compiler to ignore this warning and not generate a warning message
class NotVaildAutosarFileException extends Exception {
    public NotVaildAutosarFileException(String message) {
        super(message);
    }
}

@SuppressWarnings("serial")
class EmptyAutosarFileException extends RuntimeException {
    public EmptyAutosarFileException(String message) {
        super(message);
    }
}
