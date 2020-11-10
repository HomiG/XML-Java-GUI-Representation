package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.*;
import org.xml.sax.SAXException;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField titleTExtbox;

    @FXML
    private TextField professorTextbox;

    @FXML
    private TextField dayTextbox;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TableView<TableViewObject> tableView;
    @FXML
    private Label label;


    public ObservableList<TableViewObject> data = FXCollections.observableArrayList();



    @FXML
    private TableColumn<TableViewObject, String> titleCol;

    @FXML
    private TableColumn<TableViewObject, String> professorCol;

    @FXML
    private TableColumn<TableViewObject, String> dayCol;

    String filePathString = "src/sample/9_RIGHT_schedule.xml";

    @FXML
    private void comboAction(ActionEvent event) {

        ObservableList<TableViewObject> tempList = FXCollections.observableArrayList();

        System.out.println(data);


        tableView.getItems().clear();

        //creating a constructor of file class and parsing an XML file
        File file = new File(filePathString);
        //an instance of factory that gives a document builder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //an instance of builder to parse the specified xml file
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = db.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();
        //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        List<NodeList> nodes = new ArrayList<NodeList>();

        NodeList nodeList = doc.getElementsByTagName("Lesson");
        NodeList nodeList1 = doc.getElementsByTagName("Seminar");

        nodes.add(nodeList);
        nodes.add(nodeList1);


        String title;
        String professor;
        String day;

        for(int j = 0; j < nodes.size(); j ++){
            title = "";
            professor = "";
            day = "";
            for (int itr = 0; itr < nodes.get(j).getLength(); itr++) {
                Node node = nodes.get(j).item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    title = eElement.getElementsByTagName("Title").item(0).getTextContent();
                    //System.out.println("Title: " + eElement.getElementsByTagName("Title").item(0).getTextContent());
                    if(eElement.getElementsByTagName("Professor").item(0) != null)
                        professor = eElement.getElementsByTagName("Professor").item(0).getTextContent();
                    //System.out.println("Professor: " + eElement.getElementsByTagName("Professor").item(0).getTextContent());

                    if (eElement.getElementsByTagName("Lecture").getLength() >= 1){
                        Element eElement1 = (Element) eElement;

                        for (int i=0; i<eElement.getElementsByTagName("Lecture").getLength() ; i++){

                            System.out.println("Lecture: " + eElement1.getElementsByTagName("Day").item(i).getTextContent());
                            day = eElement1.getElementsByTagName("Day").item(i).getTextContent();
                            TableViewObject object;
                            object = new TableViewObject(title, professor, day);
                            if(day.equals(combobox.getValue())){
                                tempList.add(object);
                                tableView.setItems(tempList);
                            }
                            if(combobox.getValue().equals("AllDays")){
                                tempList.add(object);
                                tableView.setItems(tempList);
                            }
                        }
                    }
                }
            }
        }



    }









    public static boolean validateXMLSchema(String xsdPath, String xmlPath){

        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {



        //Validate XML
        if (validateXMLSchema("src/com/company/9.xsd", filePathString))
            System.out.println("Trccue");
        else
            System.out.println("fasle");


        //creating a constructor of file class and parsing an XML file
        File file = new File(filePathString);
        //an instance of factory that gives a document builder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //an instance of builder to parse the specified xml file
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = db.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();
        //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        List<NodeList> nodes = new ArrayList<NodeList>();

        NodeList nodeList = doc.getElementsByTagName("Lesson");
        NodeList nodeList1 = doc.getElementsByTagName("Seminar");

        nodes.add(nodeList);
        nodes.add(nodeList1);


        String title;
        String professor;
        String day;

        for(int j = 0; j < nodes.size(); j ++){
            title = "";
            professor = "";
            day = "";
            for (int itr = 0; itr < nodes.get(j).getLength(); itr++) {
                Node node = nodes.get(j).item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    title = eElement.getElementsByTagName("Title").item(0).getTextContent();
                    //System.out.println("Title: " + eElement.getElementsByTagName("Title").item(0).getTextContent());
                    if(eElement.getElementsByTagName("Professor").item(0) != null)
                        professor = eElement.getElementsByTagName("Professor").item(0).getTextContent();
                    //System.out.println("Professor: " + eElement.getElementsByTagName("Professor").item(0).getTextContent());

                    if (eElement.getElementsByTagName("Lecture").getLength() >= 1){
                        Element eElement1 = (Element) eElement;

                        for (int i=0; i<eElement.getElementsByTagName("Lecture").getLength() ; i++){

                            System.out.println("Lecture: " + eElement1.getElementsByTagName("Day").item(i).getTextContent());
                            day = eElement1.getElementsByTagName("Day").item(i).getTextContent();
                            TableViewObject object;
                            object = new TableViewObject(title, professor, day);
                            data.add(object);
                        }
                    }
                }
            }
        }




        titleCol.setCellValueFactory(new PropertyValueFactory<TableViewObject, String>("title"));
        professorCol.setCellValueFactory(new PropertyValueFactory<TableViewObject, String>("professor"));
        dayCol.setCellValueFactory(new PropertyValueFactory<TableViewObject, String>("day"));

        tableView.setEditable(false);
        tableView.setItems(data);





        combobox.getItems().addAll(
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "AllDays"
        );
        combobox.setValue("AllDays");


    }


    public void buttonClick(ActionEvent actionEvent) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(filePathString);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        Element root = document.getDocumentElement();
        Element newLesson = document.createElement("Lesson");
        root.appendChild(newLesson);

        


    }
}


