package edu.escuelaing.arem.app;

import edu.escuelaing.arem.app.controller.RequestMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HttpServer {


    private static HashMap<String, Method> methods = new HashMap<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean runing = true;
        while(runing) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine, request = "";
            boolean primeraLinea = true;
            String ruta = "" ;
            String comando = "" ;
            StringBuffer body = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {

                System.out.println("Received: " + inputLine);

                if(primeraLinea){
                    String[] recibido = inputLine.split(" ");
                    ruta =   recibido[1];//TENER EN SPLIT la posicion de la ruta
                    if(ruta.startsWith("/consulta")){
                        if(ruta.contains("?")){
                            comando = ruta.split("=")[1];
                        }
                        else {
                            comando = "Query";
                        }
                        body = HtttpConection.getApi(comando);


                    } else if (ruta.startsWith("/cliente")) {
                        body = getForm();

                    }
                    else{
                        System.out.println("ERROR");
                    }
                    primeraLinea = false;
                }
                if (!in.ready()) {
                    break;
                }

                if(request.split("\\(")[0].equals("Class")){
                    Class<?> classes = Class.forName(request.split("\\(")[1].split("\\)")[0]);
                    Method[] metodos = classes.getMethods();
                    Field[] atributos = classes.getFields();
                    outputLine = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: text/html\r\n"
                            + "\r\n" +
                            "<!DOCTYPE html>"+
                            "<html>"+
                            "<head>"+
                            "<title>chatGPT</title>\n"+
                            "<meta charset=\"UTF-8\">\n"+
                            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"+
                            "</head>\n"+
                            "<body>\n"+
                            "<h1>CHAT GPT</h1>\n"+
                            "<form action=\"/chat\">\n"+
                            "    <label for=\"name\">metodo:</label><br>\n"+
                            "    <input type=\"text\" id=\"name\" name=\"name\" value=\"\"><br><br>\n"+
                            "</form>\n"+
                            "<div id=\"getrespmsg\">" +
                            "Metodos" + Arrays.toString(metodos) + "\n" + "Campos" + Arrays.toString(atributos) +
                            "</div>\n"+
                            "</body>\n"+
                            "</html>";
                }
//                else if (request.split("\\(")[0].equals("Invoke")){
//
//                    Object req = m.invoke(null);
//                    outputLine = "HTTP/1.1 200 OK\r\n"
//                            + "Content-Type: text/html\r\n"
//                            + "\r\n" +
//                            "<!DOCTYPE html>"+
//                            "<html>"+
//                            "<head>"+
//                            "<title>chatGPT</title>\n"+
//                            "<meta charset=\"UTF-8\">\n"+
//                            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"+
//                            "</head>\n"+
//                            "<body>\n"+
//                            "<h1>CHAT GPT</h1>\n"+
//                            "<form action=\"/chat\">\n"+
//                            "    <label for=\"name\">metodo:</label><br>\n"+
//                            "    <input type=\"text\" id=\"name\" name=\"name\" value=\"\"><br><br>\n"+
//                            "</form>\n"+
//                            "<div id=\"getrespmsg\">" +
//                            "Metodos" +
//                            "</div>\n"+
//                            "</body>\n"+
//                            "</html>";


            }
            outputLine = "HTTP/1.1 200 OK\r\n"
                    +"Content-type: text/html\r\n"
                    +"\r\n"
                    +body;

            out.println(outputLine);


            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }
    public static StringBuffer getForm(){
        return new StringBuffer("\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Form Example</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Form with GET</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"class\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let name = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/consulta?comando=\"+name);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "    </body>\n" +
                "</html>");
    }
}

