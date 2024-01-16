package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static org.example.WebUI.*;
public class ManageBooks {
    String URL_PUBLIC_SPEAKING = "https://english.ican.vn/user/index.php?id=82";
    String URL_INTRODUCTION = "https://english.ican.vn/user/index.php?id=44";
    String URL_FOUNDATION = "https://english.ican.vn/user/index.php?id=73";
    String URL_INTENSIVE = "https://english.ican.vn/user/index.php?id=46";
    String URL_PREPARATION = "https://english.ican.vn/user/index.php?id=41";
    String URL_MASTER = "https://english.ican.vn/user/index.php?id=90";
    By Enrol_Button = By.xpath("//*[@id=\"enrolusersbutton-1\"]/div/input[1]");
    By SearchEmailInput = By.xpath("//div[@id='fitem_id_userlist']//div[@class='d-md-inline-block mr-md-2 position-relative']//input[@class='form-control']");
    By SelectEmailsButton = By.xpath("/html/body/div[5]/div[2]/div/div/div[2]/form/fieldset/div/div[1]/div[2]/ul/li");
    By EnrolSelectUserButton = By.xpath("//div[@class='modal-footer']//button[@class='btn btn-primary']");
    public void manageBooks(String[][] inforBook) throws InterruptedException {
        System.out.println("ADD  PUBLIC SPEAKING");
        ADD_PUBLIC_SPEAKING(inforBook);
        System.out.println("ADD FOUNDATION");
        ADD_FOUNDATION(inforBook);
        System.out.println("ADD INTRODUCTION");
        ADD_INTRODUCTION(inforBook);
        System.out.println("ADD INTENSIVE");
        ADD_INTENSIVE(inforBook);
        System.out.println("ADD PREPARATION");
        ADD_PREPARATION(inforBook);
        System.out.println("ADD MASTER");
        ADD_MASTER(inforBook);
    }
    public void ADD_MASTER(String[][] inforBook) throws InterruptedException {
        openURL(URL_MASTER);
        clickElement(Enrol_Button);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Master")) {
                addBooks(inforBook[i][0], inforBook[i][1], "Master");
            }
        }
        SelectEndDate();
        clickElement(EnrolSelectUserButton);
    }
    public void ADD_PUBLIC_SPEAKING(String[][] inforBook) throws InterruptedException {
        openURL(URL_PUBLIC_SPEAKING);
        clickElement(Enrol_Button);
        Thread.sleep(1000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Public Speaking") || inforBook[i][4].contains("Combo 6 khóa")) {
                addBooks(inforBook[i][0], inforBook[i][1], "Public Speaking");
            }
        }
        SelectEndDate();
        clickElement(EnrolSelectUserButton);
    }

    public void ADD_FOUNDATION(String[][] inforBook) throws InterruptedException {
        openURL(URL_FOUNDATION);
        clickElement(Enrol_Button);
        Thread.sleep(1000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Foundation") || inforBook[i][4].contains("Combo 6 khóa")) {
                addBooks(inforBook[i][0], inforBook[i][1], "Foundation");
            }
        }
        SelectEndDate();
        clickElement(EnrolSelectUserButton);
    }

    public void ADD_INTRODUCTION(String[][] inforBook) throws InterruptedException {
        openURL(URL_INTRODUCTION);
        clickElement(Enrol_Button);
        Thread.sleep(1000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Introduction") || inforBook[i][4].contains("Combo 6 khóa")) {
                addBooks(inforBook[i][0], inforBook[i][1], "Introduction");
            }
        }
        SelectEndDate();
        clickElement(EnrolSelectUserButton);
    }
    public void ADD_PREPARATION(String[][] inforBook) throws InterruptedException {
        openURL(URL_PREPARATION);
        clickElement(Enrol_Button);
        Thread.sleep(1000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Preparation") || inforBook[i][4].contains("Combo 6 khóa")) {
                addBooks(inforBook[i][0], inforBook[i][1], "Preparation");
            }
        }
        SelectEndDate();
        clickElement(EnrolSelectUserButton);
    }

    public void ADD_INTENSIVE(String[][] inforBook) throws InterruptedException {
        openURL(URL_INTENSIVE);
        clickElement(Enrol_Button);
        Thread.sleep(1000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Intensive") || inforBook[i][4].contains("Combo 6 khóa")) {
                addBooks(inforBook[i][0], inforBook[i][1], "Intensive");
            }
        }
        SelectEndDate();
        clickElement(EnrolSelectUserButton);
    }

    By ShowMoreButton = By.xpath("//*[@id=\"id_main\"]/div/div[4]/div/a");
    By EnableButton = By.xpath("//*[@id=\"id_timeend_enabled\"]");
    By TimeEndYearButton = By.xpath("//*[@id=\"id_timeend_year\"]");
    By Y2024 = By.xpath("//*[@id=\"id_timeend_year\"]/option[126]");

    public void addBooks(String email, String name, String namebook) throws InterruptedException {
        setText(SearchEmailInput, Keys.CONTROL + "a");
        setText(SearchEmailInput, email.replaceAll(" ",""));
        Thread.sleep(2000);
        int mailBook = listElements(SelectEmailsButton);
        if (mailBook == 0) {
            System.out.println(email + " " + name + " " + "add " + namebook + "  k thấy mail, có thể đã được add trước đó rồi!");
        }
        for (int j = 1; j <= mailBook; j++) {
            By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
            String nameOnWeb = getTextElement(OptionUserName);
            if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(name.toLowerCase())) || removeAccent(name.toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                clickElement(OptionUserName);
                Thread.sleep(500);
                mailBook = listElements(SelectEmailsButton);
                j = 1;
            }
        }
    }
    public void SelectEndDate() throws InterruptedException {
        clickElement(ShowMoreButton);
        Thread.sleep(500);
        clickElement(EnableButton);
        Thread.sleep(500);
        clickElement(TimeEndYearButton);
        Thread.sleep(500);
        clickElement(Y2024);
        Thread.sleep(500);
    }
}