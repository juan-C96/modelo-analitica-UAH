package com.modeloanalitica.uahdatos.controlador;

import com.google.gson.*;
import com.modeloanalitica.uahdatos.modelo.Evento;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class DatosController {

    public LocalDateTime aDate(String date){
        try {
            String dateFix = date.substring(0,22);
            LocalDateTime dateTime = LocalDateTime.parse(dateFix);
            return dateTime;
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    @GetMapping("/cargar")
    public void cargarDatos() {
        Gson gson = new Gson();

        String fichero = "";

        try (BufferedReader br = new BufferedReader(new FileReader("C://Users/jcher/OneDrive/Desktop/Escuela/TFM/1--output-Friday-June-03-2022-07-46-27.json"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
            //System.out.println(fichero);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        JsonParser parser = new JsonParser();
        Object object =  parser.parse(fichero);
        // Obtain Array
        JsonArray gsonArr = (JsonArray) object;
        //System.out.println(gsonArr.toString());

        // for each element of array
        for (JsonElement obj : gsonArr) {

            // Object of array
            JsonObject gsonObj = obj.getAsJsonObject();
            System.out.println(gsonObj.toString());

            // Primitives elements of object
            String type = gsonObj.get("type").getAsString();
            String action = gsonObj.get("action").getAsString();
            String eventTime = gsonObj.get("eventTime").getAsString();
            LocalDateTime dateTime = aDate(eventTime);
            System.out.println("TIPO = " + type);
            System.out.println("ACTION = " + action);
            System.out.println("EVENTTIME = " + dateTime);



            // List of primitive elements
 /*          JsonArray data = gsonObj.get("roles").getAsJsonArray();
            List actorm = new ArrayList();
            for (JsonElement event : data) {
                actorm.add(event.getAsString());
                System.out.println("ROLES = " + actorm.toString());
            }

            // Object Constructor
                FootballPlayer iniesta = new FootballPlayer(dorsal, name,
                        listDemarcation, team);
                System.out.println(iniesta);
            }
            for (int i = 0; i < listDemarcation.size(); i++) {
                System.out.println(listDemarcation.get(i).toString());
            }
            */
        }
    }
}
