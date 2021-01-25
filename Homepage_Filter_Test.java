package VDATestpackage;

import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import VDAJavaPackage.Homepage_Filter;
import VDAJavaPackage.Login;
import genericClass.GenericClass;

public class Homepage_Filter_Test {

    WebDriver driver;

 // Objekte erstellen von sogenannten Klassen
    public GenericClass GC = null;
    public Homepage_Filter T2 = null;

    // Classname
    private String className = this.getClass().getName();

    // Column value to be printed in test results excel sheet
    public int column = 3;

    public String methodName;
    public String projektName = "VDA";
    public String TC = "VDALogin";

    public String URL = "http://de-doc-101.cellent.int:3000/projectsoverview/default";

    public Homepage_Filter_Test() throws Exception {
            GC = new GenericClass();
            T2 = new Homepage_Filter(GC);
            //SST = new StartSeiteTest();
    }

    @SuppressWarnings("unused")
    @BeforeTest(alwaysRun = true)
    public void createExcelandInitial1() throws Exception {

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            GC.Loginit(className);
            //GC.ChromeSetup();
            //GC.EdgeSetup();
            //GC.FirefoxSetup();
            //GC.IESetup();
            GC.driver.get(URL);
            Thread.sleep(5000);
            try {
                    GC.SetExcelFileTD("Testdata.xls", "VDA_Testdata");
                    GC.SetExcelFileTR("Testergebnisse/VDA_RegressionsTesting.xls", "TestfallUndErgebnisse");

                    for(int i = 1; i < 100; i ++) //Status in Excel Sheet löschen
                    {
                            GC.SetCellDataTRPASS("", i, column);
                    }
            }

            catch (FileNotFoundException e) {
                    e.getMessage();
                    System.out.println(e);
            }
    }

    @Test(priority = 1, description = "1. Filter prüfen")
    public void LoginVerifizieren() throws Exception {
            //T1.PortalSpringLink().click(); //auf Portal Spring klicken

        try
        {
            GC.ExplicitWait(T2.projektBezeichnung, 10000);

            GC.ExecuteActionOnPage(T2.projektBezeichnung, "sendKeys", "26.3 t2");

            GC.ExecuteActionOnPage(T2.anwendenButton, "click", "");

            GC.ExplicitWait(T2.projektTitelzuVerifizieren, 10000); // wartet bis Login Feld sichtbar ist

//            try
//            {
//                T2.projektTitelzuVerifizieren.getSize();
//                GC.TestCasePassDisplay(1, projektName, TC, "LoginVerifizieren", 1, column);
//            }
//            catch (Exception e)
//            {
//                GC.TestCaseFailDisplay(1, projektName, TC, "LoginVerifizieren", 1, column);
//            }


            //if(T2.projektTitelzuVerifizieren.isDisplayed())
                if(T2.tttt.isDisplayed())
            {
                GC.TestCasePassDisplay(1, projektName, TC, "LoginVerifizieren", 1, column);
            }
            else
            {
                    GC.TestCaseFailDisplay(1, projektName, TC, "LoginVerifizieren", 1, column);
            }
        }

        catch (Exception e)
        {
            GC.TestCaseFailDisplay(1, projektName, TC, "LoginVerifizieren", 1, column);
        }


    }

    @AfterTest(alwaysRun = true)
    public void ExitEinstellungen_Test() throws Exception {

            GC.driver.close();
    }

}
