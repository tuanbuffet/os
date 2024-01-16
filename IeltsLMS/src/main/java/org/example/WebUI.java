package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.Normalizer;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class WebUI {


    public static boolean isNumer(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    public static String Unsigned(String str){
        switch (str){
            case "đ":
                str = "d";
                break;
            case "ê":
                str = "e";
                break;
            case "ư":
                str = "u";
                break;
            case "ô", "ơ":
                str = "o";
                break;
            case"ă", "â":
                str = "a";
                break;
        }
        return str;
    }

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        return temp.replaceAll("đ", "d");
    }
    public static String convertUser(String name) {

        String[] words = name.split("\\s");
        if (isNumer(words[words.length - 1])) {
            String firstusername = removeAccent(words[words.length - 2]).toLowerCase();
            /*System.out.print(removeAccent(words[words.length - 2]).toLowerCase());*/

            for (int j = 0; j < words.length - 2; j++) {
                /*System.out.println(removeAccent(String.valueOf(words[j].charAt(0))).toLowerCase());*/
                firstusername = firstusername + removeAccent(Unsigned(String.valueOf(words[j].charAt(0))).toLowerCase());
            }
            /*System.out.println(words[words.length - 1] + "2023");*/
            firstusername = firstusername +words[words.length - 1] ;
            return firstusername;
        } else {
            String firstusername = removeAccent(words[words.length - 1]).toLowerCase();
            /*System.out.print(removeAccent(words[words.length - 1]).toLowerCase());*/
            for (int j = 0; j < words.length - 1; j++) {
                /*System.out.print(removeAccent(words[j].toLowerCase()));*/
                firstusername = firstusername + removeAccent(Unsigned(String.valueOf(words[j].charAt(0))).toLowerCase());
            }
            /*System.out.println("2023");*/
            return firstusername;
        }
    }
    public static void waitElemenetDisplay(By by, int timeWait) throws InterruptedException {
        for (int i =0; i<= timeWait; i++){
            if (driver.findElement(by).isDisplayed()){
                break;
            }
            else {
                Thread.sleep(1000);
            }
        }
    }
    public static WebDriver driver;
    public static void anchrome(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
    }
    public static void openBrowser() {
        /*System.out.println("Chào Mừng bạn đến với AuToLMS IELTS");*/
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

    private static int EXPLICIT_WAIT_TIMEOUT = 10;
    private static int WAIT_PAGE_LEADED_TIMEOUT = 30;

    public WebUI() {
    }
    public static WebElement getWebElement(By by) {
        return driver.findElement(by);
    }
    public static void logConsole(String message) {
        /*System.out.println(message);*/
    }
    public static void hoverOnElement(By by) {
        waitForElementVisible(by);
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(by));
        logConsole("Hover on element " + by);
    }
    public static WebElement highLightElement(By by) {
        waitForElementVisible(by);
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        /*if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }*/
        return getWebElement(by);
    }

    public static void rightClickElement(By by) {
        waitForElementVisible(by);
        Actions action = new Actions(driver);
        action.contextClick(getWebElement(by));
        logConsole("Right click on element " + by);
    }

    public static void openURL(String URL) {
        driver.get(URL);
        waitForPageLoaded();
        logConsole("Open URL: " + URL);
    }

    public static String getCurrentUrl() {
        waitForPageLoaded();
        logConsole("Get Current URL: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    public static void clickElement(By by) {
        waitForElementVisible(by);
        highLightElement(by);
        getWebElement(by).click();
        logConsole("Click on element " + by);
        //Report
    }

    public static void setText(By by, String value) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(value);
        logConsole("Set text " + value + " on element " + by);
    }

    public static String getTextElement(By by) {
        waitForElementVisible(by);
        logConsole("Get text of element " + by);
        logConsole("==> Text: " + getWebElement(by).getText());
        return getWebElement(by).getText();
    }

    public static String getAttributeElement(By by, String attributeName) {
        waitForElementVisible(by);
        logConsole("Get attribute value of element " + by);
        logConsole("==> Attribute value: " + getWebElement(by).getAttribute(attributeName));
        return getWebElement(by).getAttribute(attributeName);
    }

    public static void scrollToElementWithJS(By by) {
        waitForElementPresent(by);
        //Dùng Actions class
        //Robot class
        //Dùng JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
        logConsole("Scroll to element " + by);
    }

    public static void scrollToElement(By by) {
        //Dùng Actions class

    }
    public static void scrollToElementWithRobot(By by) {
        //Dùng Robot class
    }
    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void waitForElementVisible(By by, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second), Duration.ofMillis(500));

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementPresent(By by, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementClickable(By by, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));

        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static boolean verifyElementVisible(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean verifyElementNotVisible(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int listElements(By by) {
        List<WebElement> listElement = driver.findElements(by);
        return listElement.size();
    }

    public static Boolean checkElementExist(String xpath) {
        List<WebElement> listElement = driver.findElements(By.xpath(xpath));

        if (listElement.size() > 0) {
            System.out.println("Element " + xpath + " existing.");
            return true;
        } else {
            System.out.println("Element " + xpath + " NOT exist.");
            return false;
        }
    }

    /**
     * Wait for Page loaded
     * Chờ đợi trang tải xong (Javascript tải xong)
     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_PAGE_LEADED_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };


    }
}

