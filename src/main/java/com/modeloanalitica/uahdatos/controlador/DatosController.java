package com.modeloanalitica.uahdatos.controlador;

import com.google.gson.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DatosController {

    public LocalDateTime aDate(String date) {
        try {
            String dateFix = date.substring(0, 22);
            LocalDateTime dateTime = LocalDateTime.parse(dateFix);
            return dateTime;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    @GetMapping("/cargar")
    public void cargarDatos() {

        Gson gson = new Gson();

        String ficheroFail = "";
        String fichero = "";


        File folder = new File("C://Users/jcher/OneDrive/Desktop/Escuela/TFM/test/");


        for (File file : folder.listFiles()) {
            System.out.println(file.getName());

            // try (BufferedReader br = new BufferedReader(new FileReader("C://Users/jcher/OneDrive/Desktop/Escuela/TFM/test/817--output-Friday-June-03-2022-07-47-58.json"))) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    ficheroFail += linea;
                }
                System.out.println("fichero = " + ficheroFail);
                int lastpos = ficheroFail.length();
                fichero = ficheroFail.substring(160, lastpos - 1);
                System.out.println("fichero arreglado = " + fichero);

                while (fichero.indexOf("sensor") != -1) {
                    int pos = fichero.indexOf("sensor");
                    fichero = fichero.substring(0, pos - 4) + "," + fichero.substring((pos - 4) + 163);
                }

                System.out.println("fichero arreglado = " + fichero);

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            JsonParser parser = new JsonParser();
            Object object = parser.parse(fichero);
            // Obtain Array
            JsonArray gsonArr = (JsonArray) object;
            //System.out.println(gsonArr.toString());

            // for each element of array
            for (JsonElement obj : gsonArr) {

                // Object of array
                JsonObject gsonObj = obj.getAsJsonObject();
                System.out.println(gsonObj.toString());

                // EVENTO
                if (!(gsonObj.get("id") == null)) {
                    String type = gsonObj.get("type").getAsString();
                    String id = gsonObj.get("id").getAsString();

                    System.out.println("=====================");
                    System.out.println("====    EVENT   =====");
                    System.out.println("=====================");
                    System.out.println("EVENT TIPO = " + type);
                    System.out.println("EVENT ID = " + id);
                }

                // ACTOR
                if (!(gsonObj.getAsJsonObject("actor") == null)) {

                    System.out.println("=====================");
                    System.out.println("====    ACTOR   =====");
                    System.out.println("=====================");
                    String actorType = gsonObj.getAsJsonObject("actor").get("type").getAsString();
                    System.out.println("ACTOR TYPE = " + actorType);

                    if (!(gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.id") == null)) {
                        String actorId = gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.id").getAsString();
                        System.out.println("ACTOR ID = " + actorId);
                    }
                    if (!(gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.externalId") == null)) {
                        String actorUser = gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.externalId").getAsString();
                        System.out.println("ACTOR USER = " + actorUser);
                    }
                }

                // ACTION
                if (!(gsonObj.get("action") == null)) {
                    String action = gsonObj.get("action").getAsString();

                    System.out.println("=====================");
                    System.out.println("=====   ACTION  =====");
                    System.out.println("=====================");
                    System.out.println("ACTION = " + action);
                }

                // OBJECT
                if (!(gsonObj.getAsJsonObject("object") == null)) {
                    String objectType = gsonObj.getAsJsonObject("object").get("type").getAsString();

                    System.out.println("=====================");
                    System.out.println("=====   OBJECT  =====");
                    System.out.println("=====================");
                    System.out.println("ACTION OBJECT TYPE = " + objectType);

                    if ((objectType.equals("SoftwareApplication"))) {
                        String objectHeaderAgent = gsonObj.getAsJsonObject("object").getAsJsonObject("extensions").get("bb:request.headers.agent").getAsString();
                        System.out.println("OBJECT HEADERS AGENT = " + objectHeaderAgent);
                        String objectHeaderIp = gsonObj.getAsJsonObject("object").getAsJsonObject("extensions").get("bb:request.headers.ipAddress").getAsString();
                        System.out.println("OBJECT HEADERS IP ADDRESS = " + objectHeaderIp);
                    }
                }

                // TARGET
                if (!(gsonObj.getAsJsonObject("target") == null)) {
                    String TargetType = gsonObj.getAsJsonObject("target").get("type").getAsString();

                    System.out.println("=====================");
                    System.out.println("====   TARGET  ======");
                    System.out.println("=====================");
                    System.out.println("TARGET TYPE = " + TargetType);
                }

                // DATETIME
                if (!(gsonObj.get("eventTime") == null)) {
                    String eventTime = gsonObj.get("eventTime").getAsString();
                    LocalDateTime dateTime = aDate(eventTime);

                    System.out.println("=====================");
                    System.out.println("==== EVENT TIME =====");
                    System.out.println("=====================");
                    System.out.println("EVENTTIME = " + dateTime);
                }

                // EDAPP
                if (!(gsonObj.getAsJsonObject("edApp") == null)) {
                    String edAppType = gsonObj.getAsJsonObject("edApp").get("type").getAsString();

                    System.out.println("=====================");
                    System.out.println("==== EDAPP TIME =====");
                    System.out.println("=====================");
                    System.out.println("EDAPP TYPE = " + edAppType);
                }

                // GRUPO
                if (!(gsonObj.getAsJsonObject("group") == null)) {
                    String grupoType = gsonObj.getAsJsonObject("group").get("type").getAsString();

                    System.out.println("=====================");
                    System.out.println("=====   GROUP   =====");
                    System.out.println("=====================");
                    System.out.println("GROUP TYPE = " + grupoType);

                    if (!(grupoType.equals("Group"))) {
                        String grupoId = gsonObj.getAsJsonObject("group").getAsJsonObject("extensions").get("bb:course.id").getAsString();
                        System.out.println("GROUP ID = " + grupoId);
                    }

                    if (!(gsonObj.getAsJsonObject("group").get("courseNumber") == null)) {
                        String grupoNumber = gsonObj.getAsJsonObject("group").get("courseNumber").getAsString();
                        System.out.println("GROUP NUMBER = " + grupoNumber);
                    }
                }

                // MEMBERSHIP
                if (!(gsonObj.getAsJsonObject("membership") == null)) {

                    System.out.println("=====================");
                    System.out.println("==== MEMBERSHIP =====");
                    System.out.println("=====================");
                    String membershipstatus = gsonObj.getAsJsonObject("membership").get("status").getAsString();
                    System.out.println("MEMBERSHIP STATUS = " + membershipstatus);
                    String membershipType = gsonObj.getAsJsonObject("membership").get("type").getAsString();
                    System.out.println("MEMBERSHIP TYPE = " + membershipType);
                    String membershipExtCourseId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:course.id").getAsString();
                    System.out.println("MEMBERSHIP COURSE ID = " + membershipExtCourseId);
                    String membershipExtCourseExtId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:course.externalId").getAsString();
                    System.out.println("MEMBERSHIP COURSE EXT ID = " + membershipExtCourseExtId);
                    String membershipExtUserId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:user.id").getAsString();
                    System.out.println("MEMBERSHIP USER ID = " + membershipExtUserId);
                    String membershipExtUserExtId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:user.externalId").getAsString();
                    System.out.println("MEMBERSHIP USER EXT ID = " + membershipExtUserExtId);

                    JsonArray data = gsonObj.getAsJsonObject("membership").get("roles").getAsJsonArray();
                    List roles = new ArrayList();
                    for (JsonElement event : data) {
                        roles.add(event.getAsString());
                    }
                    System.out.println("MEMBERSHIP ROLES = " + roles.toString());
                }

                // FEDERATED SESSION
                if (!(gsonObj.getAsJsonObject("federatedSession") == null)) {

                    System.out.println("=====================");
                    System.out.println("= FEDERATED SESSION =");
                    System.out.println("=====================");
                    String fsStartedAtTime = gsonObj.getAsJsonObject("federatedSession").get("startedAtTime").getAsString();
                    LocalDateTime federatedSessionStartedAtTime = aDate(fsStartedAtTime);
                    System.out.println("FEDERATED SESSION STARTED AT TIME = " + federatedSessionStartedAtTime);
                    String federatedSessionType = gsonObj.getAsJsonObject("federatedSession").get("type").getAsString();
                    System.out.println("FEDERATED SESSION TYPE = " + federatedSessionType);
                    String federatedSessionName = gsonObj.getAsJsonObject("federatedSession").get("name").getAsString();
                    System.out.println("FEDERATED SESSION NAME = " + federatedSessionName);
                    if (!(gsonObj.getAsJsonObject("federatedSession").get("dateCreated") == null)) {
                        String fsDateCreated = gsonObj.getAsJsonObject("federatedSession").get("dateCreated").getAsString();
                        LocalDateTime federatedSessionDateCreated = aDate(fsDateCreated);
                        System.out.println("FEDERATED SESSION DATE CREATED = " + federatedSessionDateCreated);
                    }
                    if (!(gsonObj.getAsJsonObject("federatedSession").get("dateModified") == null)) {
                        String fsDateModified = gsonObj.getAsJsonObject("federatedSession").get("dateModified").getAsString();
                        LocalDateTime federatedSessionDateModified = aDate(fsDateModified);
                        System.out.println("FEDERATED SESSION DATE MODIYIED = " + federatedSessionDateModified);
                    }
                    String federatedSessionUserType = gsonObj.getAsJsonObject("federatedSession").getAsJsonObject("user").get("type").getAsString();
                    System.out.println("FEDERATED SESSION USER TYPE = " + federatedSessionUserType);
                    String federatedSessionUserId = gsonObj.getAsJsonObject("federatedSession").getAsJsonObject("user").getAsJsonObject("extensions").get("bb:user.id").getAsString();
                    System.out.println("FEDERATED SESSION USER ID = " + federatedSessionUserId);
                    String federatedSessionUserExtId = gsonObj.getAsJsonObject("federatedSession").getAsJsonObject("user").getAsJsonObject("extensions").get("bb:user.externalId").getAsString();
                    System.out.println("FEDERATED SESSION USER EXT ID = " + federatedSessionUserExtId);
                }
            }
        }
    }
}
