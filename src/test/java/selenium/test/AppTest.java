package selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AppTest {
	//Webdriver inst
    public static WebDriver driver;
    //Location of the adblocker plugin
    private static File path_to_addblock = new File("/Users/daft_/Documents/5.0.4_0");
    //WebDriverWait inst
    public static WebDriverWait wait;
    //Webelements inst
    private WebElement inputFirstName,inputLastName, inputEmail, inputGender1,inputGender2,
    inputGender3, mobileNumber, dateOfBirth, subjectsInput, hobbiesInput1,hobbiesInput2,hobbiesInput3
    ,pictureInput, addressInput, stateInput, cityInput, searchButton;
    @BeforeMethod
    public void beforeMethod() {
    	/*Indentify webelements on page*/
        inputFirstName = driver.findElement(By.id("firstName"));
        inputLastName = driver.findElement(By.id("lastName"));
        inputEmail = driver.findElement(By.id("userEmail"));
        inputGender1 = driver.findElement(By.id("gender-radio-1"));
        inputGender2 = driver.findElement(By.id("gender-radio-2"));
        inputGender3 = driver.findElement(By.id("gender-radio-3"));
        mobileNumber = driver.findElement(By.id("userNumber"));
        dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        subjectsInput = driver.findElement(By.id("subjectsInput"));
        hobbiesInput1= driver.findElement(By.id("hobbies-checkbox-1"));
        hobbiesInput2 = driver.findElement(By.id("hobbies-checkbox-2"));
        hobbiesInput3 = driver.findElement(By.id("hobbies-checkbox-3"));
        pictureInput = driver.findElement(By.id("uploadPicture"));
        addressInput = driver.findElement(By.id("currentAddress"));
        stateInput = driver.findElement(By.id("react-select-3-input"));
        cityInput = driver.findElement(By.id("react-select-4-input"));
        searchButton = driver.findElement(By.id("submit"));
    }
    @BeforeSuite
    public static void settingEnvironment() {
    	//Set chromedriver location and property
    	System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
    	//Load adblocker extension
    	ChromeOptions options = new ChromeOptions();
        options.addArguments("load-extension="+path_to_addblock.getAbsolutePath());
        //init driver
        driver = new ChromeDriver(options);
        //Set WebDriverWait
    	wait = new WebDriverWait(driver, Duration.ofMillis(10000));
    	//Mazimize window
        driver.manage().window().maximize();
        //Wait to open new tab
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        //Tab logic for switch to original tab
        int nTabs = driver.getWindowHandles().size();
        List<String> winHandles = new ArrayList<String>(nTabs);
        for(String s:driver.getWindowHandles()) winHandles.add(s);
        driver.switchTo().window(winHandles.get(0));
        //Open demowa page
        driver.get("https://demoqa.com/automation-practice-form");
        //Wait until page title
        wait.until(ExpectedConditions.titleIs("ToolsQA"));
    }

    @Test
    public void happyPath() throws InterruptedException {
        /*Set actions to perform operations over radio and check buttons*/
        Actions actions = new Actions(driver);
        /*Fill the elements of the page*/
        //name
        inputFirstName.sendKeys("Jorge");
        inputLastName.sendKeys("Fuentes");
        //email
        inputEmail.sendKeys("fake@mail.com");
        //gender
        actions.moveToElement(inputGender1).click().perform();
        actions.moveToElement(inputGender2).click().perform();
        actions.moveToElement(inputGender3).click().perform();
        //mobile
        mobileNumber.sendKeys("0123456789");
        //dateOfBirth
        dateOfBirth.sendKeys("10 Sep 1997");
        dateOfBirth.sendKeys(Keys.ENTER);
        //subjects
        subjectsInput.sendKeys("HelloWorld");
        //hobbies
        actions.moveToElement(hobbiesInput1).click().perform();
        actions.moveToElement(hobbiesInput2).click().perform();
        actions.moveToElement(hobbiesInput3).click().perform();
        //picture
        File file = new File("/Users/daft_/Pictures/images.jpg");
        pictureInput.sendKeys(file.getAbsolutePath());
        //currentaddress
        addressInput.sendKeys("Asndanskjdnajsndjansdkjnakjsdnkansdjs");
        //stateandcity
        stateInput.sendKeys("NCR");
        stateInput.sendKeys(Keys.ENTER);
        cityInput.sendKeys("Delhi");
        cityInput.sendKeys(Keys.ENTER);
        //submit
        searchButton.sendKeys(Keys.ENTER);
        /*Once completed, wait for the modal button to close the modal and verify 
         * correct execution*/
        wait.until(ExpectedConditions.elementToBeClickable(By.id("closeLargeModal")));
        actions.moveToElement(driver.findElement(By.id("closeLargeModal")))
        .click().perform();
        
    }

    //All the fields are empty.
    @Test
    public void EmptyFields() throws InterruptedException {
        Actions actions = new Actions(driver);
        /*Indentify webelements on page*/
        //name
        WebElement inputFirstName = driver.findElement(By.id("firstName"));
        inputFirstName.clear();
        WebElement inputLastName = driver.findElement(By.id("lastName"));
        inputLastName.clear();
        //email
        WebElement inputEmail = driver.findElement(By.id("userEmail"));
        inputEmail.clear();
        //mobile
        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.clear();
        //dateOfBirth
        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirth.clear();
        dateOfBirth.sendKeys(Keys.ENTER);
        //subjects
        WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
        subjectsInput.clear();

        //currentaddress
        WebElement addressInput = driver.findElement(By.id("currentAddress"));
        addressInput.clear();

        //submit
        WebElement searchButton = driver.findElement(By.id("submit"));
        searchButton.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("closeLargeModal")));
        actions.moveToElement(driver.findElement(By.id("closeLargeModal"))).click().perform();

    }
    
    //All the mandatory fields are empty.
    @Test
    public void EmptyMandatoryFields() throws InterruptedException {
        //Set actions to perform operations over radio and check buttons
        Actions actions = new Actions(driver);
        /*Indentify webelements on page*/
        //name
        WebElement inputFirstName = driver.findElement(By.id("firstName"));
        inputFirstName.sendKeys("");
        WebElement inputLastName = driver.findElement(By.id("lastName"));
        inputLastName.sendKeys("");
        //email
        WebElement inputEmail = driver.findElement(By.id("userEmail"));
        inputEmail.sendKeys("lidia@gmail.com");

        //mobile
        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.sendKeys("");
        //dateOfBirth
        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirth.sendKeys("10 Sep 1997");
        dateOfBirth.sendKeys(Keys.ENTER);
        //subjects
        WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
        subjectsInput.sendKeys("HelloWorld");
        //hobbies
        WebElement hobbiesInput1,hobbiesInput2,hobbiesInput3;
        hobbiesInput1= driver.findElement(By.id("hobbies-checkbox-1"));
        actions.moveToElement(hobbiesInput1).click().perform();
        hobbiesInput2 = driver.findElement(By.id("hobbies-checkbox-2"));
        actions.moveToElement(hobbiesInput2).click().perform();
        hobbiesInput3 = driver.findElement(By.id("hobbies-checkbox-3"));
        actions.moveToElement(hobbiesInput3).click().perform();
        //picture
        WebElement pictureInput = driver.findElement(By.id("uploadPicture"));
        File file = new File("/Users/daft_/Pictures/images.jpg");
        pictureInput.sendKeys(file.getAbsolutePath());
        //currentaddress
        WebElement addressInput = driver.findElement(By.id("currentAddress"));
        addressInput.sendKeys("Asndanskjdnajsndjansdkjnakjsdnkansdjs");
        //stateandcity
        WebElement stateInput = driver.findElement(By.id("react-select-3-input"));
        stateInput.sendKeys("NCR");
        stateInput.sendKeys(Keys.ENTER);
        WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-4-input")));
        cityInput.sendKeys("Delhi");
        cityInput.sendKeys(Keys.ENTER);
        //submit
        WebElement searchButton = driver.findElement(By.id("submit"));
        searchButton.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("closeLargeModal")));
        actions.moveToElement(driver.findElement(By.id("closeLargeModal"))).click().perform();
    }

    //Wrong or incorrect BirthDay
    @Test
    public void DateBirthDay() throws InterruptedException {
    	//Check if the title is correct
        //Set actions to perform operations over radio and check buttons
        Actions actions = new Actions(driver);
        /*Indentify webelements on page*/
        //name
        WebElement inputFirstName = driver.findElement(By.id("firstName"));
        inputFirstName.sendKeys("Lidia");
        WebElement inputLastName = driver.findElement(By.id("lastName"));
        inputLastName.sendKeys("Garcia");
        //email
        WebElement inputEmail = driver.findElement(By.id("userEmail"));
        inputEmail.sendKeys("example@email.com");
        //gender
        WebElement inputGender1,inputGender2,inputGender3;
        inputGender1 = driver.findElement(By.id("gender-radio-1"));
        actions.moveToElement(inputGender1).click().perform();
        inputGender2 = driver.findElement(By.id("gender-radio-2"));
        actions.moveToElement(inputGender2).click().perform();
        inputGender3 = driver.findElement(By.id("gender-radio-3"));
        actions.moveToElement(inputGender3).click().perform();
        //mobile
        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.sendKeys("0123456789");
        //dateOfBirth
        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirth.sendKeys("13 Feb 2053");
        //subjects
        WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
        subjectsInput.sendKeys("Example");
        //hobbies
        WebElement hobbiesInput1,hobbiesInput2,hobbiesInput3;
        hobbiesInput1= driver.findElement(By.id("hobbies-checkbox-1"));
        actions.moveToElement(hobbiesInput1).click().perform();
        hobbiesInput2 = driver.findElement(By.id("hobbies-checkbox-2"));
        actions.moveToElement(hobbiesInput2).click().perform();
        hobbiesInput3 = driver.findElement(By.id("hobbies-checkbox-3"));
        actions.moveToElement(hobbiesInput3).click().perform();
        //picture
        WebElement pictureInput = driver.findElement(By.id("uploadPicture"));
        File file = new File("/Users/daft_/Pictures/images.jpg");
        pictureInput.sendKeys(file.getAbsolutePath());
        //currentaddress
        WebElement addressInput = driver.findElement(By.id("currentAddress"));
        addressInput.sendKeys("esteesunejemplodeprueba");
        //stateandcity
        WebElement stateInput = driver.findElement(By.id("react-select-3-input"));
        stateInput.sendKeys("NCR");
        stateInput.sendKeys(Keys.ENTER);
        WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-4-input")));
        cityInput.sendKeys("Delhi");
        cityInput.sendKeys(Keys.ENTER);
        //submit
        WebElement searchButton = driver.findElement(By.id("submit"));
        searchButton.sendKeys(Keys.ENTER);
        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("closeLargeModal")));
        actions.moveToElement(driver.findElement(By.id("closeLargeModal"))).click().perform();
        
    }
    

    //Wrong or incorrect name.
    @Test
    public void WrongName() throws InterruptedException {
    	//Check if the title is correct
        //Set actions to perform operations over radio and check buttons
        Actions actions = new Actions(driver);
        /*Indentify webelements on page*/
        //name
        WebElement inputFirstName = driver.findElement(By.id("firstName"));
        inputFirstName.sendKeys("Ju@n%$23%");
        WebElement inputLastName = driver.findElement(By.id("lastName"));
        inputLastName.sendKeys("Garcia");
        //email
        WebElement inputEmail = driver.findElement(By.id("userEmail"));
        inputEmail.sendKeys("example@email.com");
        //gender
        WebElement inputGender1,inputGender2,inputGender3;
        inputGender1 = driver.findElement(By.id("gender-radio-1"));
        actions.moveToElement(inputGender1).click().perform();
        inputGender2 = driver.findElement(By.id("gender-radio-2"));
        actions.moveToElement(inputGender2).click().perform();
        inputGender3 = driver.findElement(By.id("gender-radio-3"));
        actions.moveToElement(inputGender3).click().perform();
        //mobile
        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.sendKeys("0123456789");
        //dateOfBirth
        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirth.sendKeys("10 Sep 1997");
        //subjects
        WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
        subjectsInput.sendKeys("HelloWorld");
        //hobbies
        WebElement hobbiesInput1,hobbiesInput2,hobbiesInput3;
        hobbiesInput1= driver.findElement(By.id("hobbies-checkbox-1"));
        actions.moveToElement(hobbiesInput1).click().perform();
        hobbiesInput2 = driver.findElement(By.id("hobbies-checkbox-2"));
        actions.moveToElement(hobbiesInput2).click().perform();
        hobbiesInput3 = driver.findElement(By.id("hobbies-checkbox-3"));
        actions.moveToElement(hobbiesInput3).click().perform();
        //picture
        WebElement pictureInput = driver.findElement(By.id("uploadPicture"));
        File file = new File("/Users/daft_/Pictures/images.jpg");
        pictureInput.sendKeys(file.getAbsolutePath());
        //currentaddress
        WebElement addressInput = driver.findElement(By.id("currentAddress"));
        addressInput.sendKeys("Asndanskjdnajsndjansdkjnakjsdnkansdjs");
        //stateandcity
        WebElement stateInput = driver.findElement(By.id("react-select-3-input"));
        stateInput.sendKeys("NCR");
        stateInput.sendKeys(Keys.ENTER);
        WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-4-input")));
        cityInput.sendKeys("Delhi");
        cityInput.sendKeys(Keys.ENTER);
        //submit
        WebElement searchButton = driver.findElement(By.id("submit"));
        searchButton.sendKeys(Keys.ENTER);
        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("closeLargeModal")));
        actions.moveToElement(driver.findElement(By.id("closeLargeModal"))).click().perform();
    }
    

    //Wrong or incorrect last name.
    @Test
    public void WrongLastName() throws InterruptedException {
    	//Check if the title is correct
        //Set actions to perform operations over radio and check buttons
        Actions actions = new Actions(driver);
        /*Indentify webelements on page*/
        //name
        WebElement inputFirstName = driver.findElement(By.id("firstName"));
        inputFirstName.sendKeys("Juan");
        WebElement inputLastName = driver.findElement(By.id("lastName"));
        inputLastName.sendKeys("G@rc14#34932");
        //email
        WebElement inputEmail = driver.findElement(By.id("userEmail"));
        inputEmail.sendKeys("example@email.com");
        //gender
        WebElement inputGender1,inputGender2,inputGender3;
        inputGender1 = driver.findElement(By.id("gender-radio-1"));
        actions.moveToElement(inputGender1).click().perform();
        inputGender2 = driver.findElement(By.id("gender-radio-2"));
        actions.moveToElement(inputGender2).click().perform();
        inputGender3 = driver.findElement(By.id("gender-radio-3"));
        actions.moveToElement(inputGender3).click().perform();
        //mobile
        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.sendKeys("0123456789");
        //dateOfBirth
        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirth.sendKeys("10 Sep 1997");
        //subjects
        WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
        subjectsInput.sendKeys("HelloWorld");
        //hobbies
        WebElement hobbiesInput1,hobbiesInput2,hobbiesInput3;
        hobbiesInput1= driver.findElement(By.id("hobbies-checkbox-1"));
        actions.moveToElement(hobbiesInput1).click().perform();
        hobbiesInput2 = driver.findElement(By.id("hobbies-checkbox-2"));
        actions.moveToElement(hobbiesInput2).click().perform();
        hobbiesInput3 = driver.findElement(By.id("hobbies-checkbox-3"));
        actions.moveToElement(hobbiesInput3).click().perform();
        //picture
        WebElement pictureInput = driver.findElement(By.id("uploadPicture"));
        File file = new File("/Users/daft_/Pictures/images.jpg");
        pictureInput.sendKeys(file.getAbsolutePath());
        //currentaddress
        WebElement addressInput = driver.findElement(By.id("currentAddress"));
        addressInput.sendKeys("Asndanskjdnajsndjansdkjnakjsdnkansdjs");
        //stateandcity
        WebElement stateInput = driver.findElement(By.id("react-select-3-input"));
        stateInput.sendKeys("NCR");
        stateInput.sendKeys(Keys.ENTER);
        WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-4-input")));
        cityInput.sendKeys("Delhi");
        cityInput.sendKeys(Keys.ENTER);
        //submit
        WebElement searchButton = driver.findElement(By.id("submit"));
        searchButton.sendKeys(Keys.ENTER);
        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("closeLargeModal")));
        actions.moveToElement(driver.findElement(By.id("closeLargeModal"))).click().perform();
    }


    @AfterMethod
    public void clean() {
    	/*Refresh web browser to clean form and data*/
    	driver.navigate().refresh();
    }
    
    @AfterSuite
    public static void finishJob() {
    	/*Quit browser once suite is finished*/
    	driver.quit();
    }
}
