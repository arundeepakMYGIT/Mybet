package VDATestpackage;

import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import VDAJavaPackage.Einstellungen;
import VDAJavaPackage.Homepage_Filter;
import genericClass.GenericClass;

public class Einstellungen_Test {

    WebDriver driver;

    // Objekte erstellen von sogenannten Klassen
       public GenericClass GC = null;
       public Homepage_Filter T2 = null;
       public Einstellungen T3 = null;

       // Classname
       private String className = this.getClass().getName();

       // Column value to be printed in test results excel sheet
       public int column = 3; //nicht ändern

       public String methodName;
       public String projektName = "VDA";
       public String TC = "VDALogin";

       public String URL = "http://de-doc-101.cellent.int:3000/projectsoverview/default";

       public Einstellungen_Test() throws Exception {
               GC = new GenericClass();
               T2 = new Homepage_Filter(GC);
               T3 = new Einstellungen(GC);
               //SST = new StartSeiteTest();
       }

       @BeforeTest
       public void createExcelandInitial2() throws Exception {

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

//                       for(int i = 1; i < 100; i ++) //Status in Excel Sheet löschen
//                       {
//                               GC.SetCellDataTR("", i, column);
//                       }
               }
               catch (FileNotFoundException e) {
                       e.getMessage();
                       System.out.println(e);
               }
       }

       @Test(priority = 1, description = "TC 2: Stellvertretung prüfen")
       public void StellvertretungVerifizieren() throws Exception {

           try
           {
               GC.ExecuteActionOnPage(T3.menuButton, "click", "");

               GC.ExecuteActionOnPage(T3.einstellungButton, "click", "");

               GC.ExecuteActionOnPage(T3.stellvertretungTextfeld, "sendKeys", "Thomas Weber");

               T3.stellvertretungTextfeld.sendKeys(Keys.DOWN);

               T3.stellvertretungTextfeld.sendKeys(Keys.ENTER);

               GC.ExecuteActionOnPage(T3.einstellungStellvertretungSpeichernButton, "click", "");

               GC.ExecuteActionOnPage(T3.stellvertretungBeendenButton, "click", "");

               if(T3.stellvertretungBeendenMeldungButton.isDisplayed())
                 {
                     GC.TestCasePassDisplay(2, projektName, TC, "Stellvertretung prüfen", 2, column);
                 }
               else
               {
                   GC.TestCaseFailDisplay(2, projektName, TC, "Stellvertretung prüfen", 2, column);
               }
           }

           catch (Exception e)
           {
               GC.TestCaseFailDisplay(2, projektName, TC, "Stellvertretung prüfen", 2, column);
           }

//               if(T3.stellvertretungBeendenMeldungButton.isDisplayed())
//               {
//                   GC.TestCasePassDisplay(2, projektName, TC, "Stellvertretung prüfen", 2, column);
//               }
//               else
//               {
//                       GC.TestCaseFailDisplay(2, projektName, TC, "Stellvertretung prüfen", 2, column);
//               }
       }

       @AfterTest(alwaysRun = true)
       public void ExitEinstellungen_Test() throws Exception {

               GC.driver.close();
       }

}
