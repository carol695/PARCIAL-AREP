package edu.escuelaing.arem.app.services;

import edu.escuelaing.arem.app.controller.RequestMapping;

public class WebServices {
    @RequestMapping("/client")
    public static String client(){
        return "Hello World";
    }

    @RequestMapping("/Class")
    public static String Class(){
        return "Running";
    }

    @RequestMapping("/invoke")
    public static String invoke(){
        return "Invoke";
    }

    @RequestMapping("/unaryInvoke")
    public static String unaryInvoke(){
        return "Invoke";
    }

    @RequestMapping("/binaryInvoke")
    public static String binaryInvoke(){
        return "Invoke";
    }

}
