package VDAJavaPackage;

import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericClass.GenericClass;

public class Einstellungen {

    public GenericClass GC = null;
    public String projektName = "VDA";
    public int column = 3;

    Random ran = new Random();
    Alert alert;

    public Einstellungen(GenericClass GC) throws Exception {
        this.GC = GC;
        //GC.driver.get(URL);
        Thread.sleep(3000);
        PageFactory.initElements(GC.driver, this);
    }


    // ############################ Page Factory Initialialisierung START ############################

            @FindBy(xpath = "/html/body/div[1]/div/div[1]/nav/div[1]/div[2]/div[2]/div/button")
            public
            WebElement menuButton;

            @FindBy(xpath = "//*[@id=\"settings-btn\"]")
            public
            WebElement einstellungButton;

            @FindBy(xpath = "//*[@id=\"deputySettingsConfiguration-autocomplete-input\"]")
            public
            WebElement stellvertretungTextfeld;

            @FindBy(xpath = "//*[@id=\"settings-deputy-save-button\"]")
            public
            WebElement einstellungStellvertretungSpeichernButton;

            @FindBy(xpath = "//*[@id=\"deputySettingsConfiguration-byDeputyStop-button\"]")
            public
            WebElement stellvertretungBeendenButton;

            @FindBy(xpath = "/html/body/div[1]/div/div[2]/div")
            public
            WebElement stellvertretungBeendenMeldungButton;

            // ############################ Page Factory Initialialisierung END ############################

}
