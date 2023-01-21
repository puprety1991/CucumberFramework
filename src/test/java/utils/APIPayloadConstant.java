package utils;

import org.json.JSONObject;

public class APIPayloadConstant {
    public static String adminPayload(){
        String adminPayload ="{\n" +
                "  \"email\": \"oscar2@test.com\",\n" +
                "  \"password\": \"Test@123\"\n" +
                "}";
        return adminPayload;
    }
    public static String createEmployeeJsonBody(){
        JSONObject object = new JSONObject();
        object.put("emp_firstname","Henry");
        object.put("emp_lastname","Trump");
        object.put("emp_middle_name","ms");
        object.put("emp_gender","M");
        object.put("emp_birthday","1981-01-14");
        object.put("emp_status","confirmed");
        object.put("emp_job_title","Politician");
        return object.toString();

    }
    public static String createEmployeePayload(){
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Henry\",\n" +
                "  \"emp_lastname\": \"Trump\",\n" +
                "  \"emp_middle_name\": \"ms\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1981-01-14\",\n" +
                "  \"emp_status\": \"confirmed\",\n" +
                "  \"emp_job_title\": \"Politician\"\n" +
                "}";
        return createEmployeePayload;
    }
}
