package org.example;


import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    /*Comentario para la versión 2.0*/
    static String path="C:\\Users\\patricio\\Desktop\\cuarto\\DIS";
    static String name="usarios";

    public static void main(String[] args) {
        String json2Xml = jsonToXml(path+"\\"+name+".json");
        System.out.println("Parseando json to xml: ");
        System.out.println(json2Xml);

        crearArchivo(path, name, ".json", json2Xml);

        String xml2Json = getXmlFromFile(path+"\\"+name+"..xml");
        System.out.println("Parseando xml to json: ");
        System.out.println(xml2Json);
        crearArchivo(path, name, ".xml", xml2Json);
    }

    public static String jsonToXml(String path) {
        return getJsonFromFile(path);
    }

    public static String xmlToJson(String xml2String) {
        if(!xml2String.isEmpty()) {
            try {
                JSONObject json = XML.toJSONObject(xml2String);
                String jsonString = json.toString(4);
                System.out.println(jsonString);
                return jsonString;
            } catch (JSONException e) {
                // TODO: handle exception
                System.out.println(e.toString());
            }
        }
        return null;
    }

    public static String getXmlFromFile(String path) {
        String xml2String = "";
        try {
            File xmlFile = new File(path);
            Reader fileReader = new FileReader(xmlFile);
            BufferedReader bufReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line = bufReader.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = bufReader.readLine();
            }
            xml2String = sb.toString();
            System.out.println(xml2String);
            bufReader.close();
        } catch (Exception ex){

        }

        return xmlToJson(xml2String);
    }

    public static String getJsonFromFile(String path) {
        try {
            // create Gson instance
            Gson gson = new Gson();
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(path));
            // convert a JSON string to a User object
            Usuario user = gson.fromJson(reader,Usuario.class);
            JSONObject json = new JSONObject(user);
            String xml = XML.toString(json);
            // close reader
            reader.close();

            return xml;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    public static void crearArchivo (String path, String nameFile, String extension, String data)
    {
        try {
            FileWriter myWriter = new FileWriter(path+"\\"+nameFile+extension);
            myWriter.write(data);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}