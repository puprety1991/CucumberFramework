package steps;

import io.cucumber.java.bs.A;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import utils.CommonMethods;

public class PageInitializer {
    public static LoginPage login;
    public static DashboardPage dashboard;
    public static AddEmployeePage addEmployee;
    public static EmployeeListPage empList;

    public static void initializePageObjects(){
        login=new LoginPage();
        dashboard = new DashboardPage();
        addEmployee = new AddEmployeePage();
        empList = new EmployeeListPage();
    }
}
