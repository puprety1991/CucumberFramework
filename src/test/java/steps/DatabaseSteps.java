package steps;

import utils.CommonMethods;

public class DatabaseSteps extends CommonMethods {
    public static String getFnameLnameQuery(){

        String query="select emp_firstname,emp_lastname " +
                "from hs_hr_employees " +
                "where employee_id=";
        return query;
    }

}
