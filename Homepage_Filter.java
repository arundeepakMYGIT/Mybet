package VDAJavaPackage;

import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericClass.GenericClass;

public class Homepage_Filter {

    public GenericClass GC = null;
    public String projektName = "VDA";
    public int column = 3;

    Random ran = new Random();
    Alert alert;

    public Homepage_Filter(GenericClass GC) throws Exception {
        this.GC = GC;
        //GC.driver.get(URL);
        Thread.sleep(3000);
        PageFactory.initElements(GC.driver, this);
    }


    // ############################ Page Factory Initialialisierung START ############################

            @FindBy(xpath = "//*[@id=\"projectsFilterFormProjectTitle-input\"]")
            public
            WebElement projektBezeichnung;

            @FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[1]/div/div[3]/div[5]/button")
            public
            WebElement anwendenButton;

            @FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/div/div/div[1]/table/tbody/tr/td[2]/span")
            public
            WebElement projektTitelzuVerifizieren;

           // @FindBy(xpath = ".//*[@id='ProjectsResultView']//*[contains(text), '26.3 t2')]")
           // xpath = ".//*[@class=ProjectsCard]//div[2]"



            @FindBy(xpath = ".//*[@id='ProjectsResultView']//*[@class='card-header']//*[contains(text(),'26.3 t2')]")
            public
            WebElement tttt;

            //*[@id='ProjectsResultView']//*[@class='card-header']//*[contains(text(),'26.3 t2')]



            //*[@id='ProjectsResultView']//[contains(text), '26.3 t2')]

                  //*[@id="projectsFilterFormUpcomingAppointmentInputFieldDays"]

            //@FindBy(xpath = ".//*[@class='state']//*[contains(text(), 'Status: Offen')]")
            //WebElement statusOffen;


            // .//*[@class='animation-wrapper']//*[contains(text(), 'Geplante Versorgungsunterbrechung')]

            // ############################ Page Factory Initialialisierung END ############################
}
