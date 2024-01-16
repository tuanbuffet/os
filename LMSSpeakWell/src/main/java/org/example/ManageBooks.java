package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


import static org.example.WebUI.*;
import static org.example.WebUI.*;

public class ManageBooks {
    String URL_SOLUTION_PRE_INTERMEDIATE = "https://moodle.speakup.vn/user/index.php?id=39";
    String URL_SOLUTION_ELEMENTARY = "https://moodle.speakup.vn/user/index.php?id=38";
    String URL_KB_Flyers = "https://moodle.speakup.vn/user/index.php?id=47";
    String URL_KB_Movers = "https://moodle.speakup.vn/user/index.php?id=46";
    String URL_KB_Starters = "https://moodle.speakup.vn/user/index.php?id=45";
    String URL_KB_Beginers = "https://moodle.speakup.vn/user/index.php?id=44";
    String URL_KB_Beginers_new = "https://moodle.speakup.vn/user/index.php?id=68";
    String URL_Milo_Level_1 = "https://moodle.speakup.vn/user/index.php?id=41";
    String URL_Milo_Level_2 = "https://moodle.speakup.vn/user/index.php?id=42";
    String URL_Milo_Level_3 = "https://moodle.speakup.vn/user/index.php?id=43";

    By Enrol_Button = By.xpath("//*[@id=\"enrolusersbutton-1\"]/div/input[1]");

    By SearchEmailInput = By.xpath("//div[@id='fitem_id_userlist']//div[@class='d-md-inline-block mr-md-2 position-relative']//input[@class='form-control']");
    By SelectEmailsButton = By.xpath("/html/body/div[5]/div[2]/div/div/div[2]/form/fieldset/div/div[1]/div[2]/ul/li");
    By SelectEmailButton = By.xpath("/html/body/div[5]/div[2]/div/div/div[2]/form/fieldset/div/div[1]/div[2]/ul/li[1]");
    By EnrolSelectUserButton = By.xpath("//div[@class='modal-footer']//button[@class='btn btn-primary']");
    By showAllButton = By.xpath("//*[@id=\"showall\"]/a");

    public void addSolutionElementary(String inforBook[][]) throws InterruptedException {
        openURL(URL_SOLUTION_ELEMENTARY);
        clickElement(Enrol_Button);
        Thread.sleep(1000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Elementary")) {
                setText(SearchEmailInput, Keys.CONTROL + "a");
                setText(SearchEmailInput, inforBook[i][0]);
                Thread.sleep(1500);
                int mailBook = listElements(SelectEmailsButton);
                if (mailBook == 0) {
                    System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Elementary k thấy mail");
                } else {
                    for (int j = 1; j <= mailBook; j++) {
                        By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
                        String nameOnWeb = getTextElement(OptionUserName);
                        if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(inforBook[i][1].toLowerCase()))||removeAccent(inforBook[i][1].toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                            clickElement(OptionUserName);
                            Thread.sleep(500);
                            mailBook =listElements(SelectEmailsButton);
                            j = 1;
                        }
                        else if (j==mailBook){
                            System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Milo Level 1 k thấy name");
                        }
                    }
                }
                for (int j = 1; j <= mailBook; j++) {
                    By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
                    String nameOnWeb = getTextElement(OptionUserName);
                    if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(inforBook[i][1].toLowerCase()))||removeAccent(inforBook[i][1].toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                        clickElement(OptionUserName);
                        Thread.sleep(500);
                        mailBook =listElements(SelectEmailsButton);
                        j = 1;
                    }
                    else if (j==mailBook){
                        System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Milo Level 1 k thấy name");
                    }
                }
            }
        }
        clickElement(EnrolSelectUserButton);
    }

    public void addSolutionPreIntermediate(String inforBook[][]) throws InterruptedException {
        openURL(URL_SOLUTION_PRE_INTERMEDIATE);
        clickElement(Enrol_Button);
        Thread.sleep(1000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Intermediate")) {
                setText(SearchEmailInput, Keys.CONTROL + "a");
                setText(SearchEmailInput, inforBook[i][0]);
                Thread.sleep(1500);
                int mailBook = listElements(SelectEmailsButton);
                if (mailBook == 0) {
                    System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Intermediate k thấy mail");
                } else {
                    for (int j = 1; j <= mailBook; j++) {
                        By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
                        String nameOnWeb = getTextElement(OptionUserName);
                        if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(inforBook[i][1].toLowerCase()))||removeAccent(inforBook[i][1].toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                            clickElement(OptionUserName);
                            Thread.sleep(500);
                            mailBook =listElements(SelectEmailsButton);
                            j = 1;
                        }
                        else if (j==mailBook){
                            System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Milo Level 1 k thấy name");
                        }
                    }
                }
            }
        }
        clickElement(EnrolSelectUserButton);
    }

    public void Milos(String inforBook[][]) throws InterruptedException {
        //add sách Milo Level 1
        openURL(URL_Milo_Level_1);
        clickElement(Enrol_Button);
        Thread.sleep(2000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Milo")) {
                setText(SearchEmailInput, Keys.CONTROL + "a");
                setText(SearchEmailInput, inforBook[i][0]);
                Thread.sleep(2000);
                int mailBook = listElements(SelectEmailsButton);
                if (mailBook == 0) {
                    System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Milo Level 1 k thấy mail");
                } else {
                    for (int j = 1; j <= mailBook; j++) {
                        By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
                        String nameOnWeb = getTextElement(OptionUserName);
                        if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(inforBook[i][1].toLowerCase()))||removeAccent(inforBook[i][1].toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                            clickElement(OptionUserName);
                            Thread.sleep(500);
                            j = 1;
                            mailBook =listElements(SelectEmailsButton);
                        }
                        else if (j==mailBook){
                            System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Milo Level 1 k thấy name");
                        }
                    }
                }

            }
        }
        clickElement(EnrolSelectUserButton);
        //add sách Milo Level 2
        openURL(URL_Milo_Level_2);
        clickElement(Enrol_Button);
        Thread.sleep(2000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Milo")) {
                setText(SearchEmailInput, Keys.CONTROL + "a");
                setText(SearchEmailInput, inforBook[i][0]);
                Thread.sleep(2000);
                int mailBook = listElements(SelectEmailsButton);
                if (mailBook == 0) {
                    System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Milo Level 2 k thấy mail");
                } else {
                    for (int j = 1; j <= mailBook; j++) {
                        By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
                        String nameOnWeb = getTextElement(OptionUserName);
                        if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(inforBook[i][1].toLowerCase()))||removeAccent(inforBook[i][1].toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                            clickElement(OptionUserName);
                            Thread.sleep(500);
                            mailBook =listElements(SelectEmailsButton);
                            j = 1;
                        }
                        else if (j==mailBook){
                            System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Milo Level 1 k thấy name");
                        }
                    }
                }
            }
        }
        clickElement(EnrolSelectUserButton);
        //add sách Milo Level 3
        openURL(URL_Milo_Level_3);
        clickElement(Enrol_Button);
        Thread.sleep(2000);
        for (int i = 1; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Milo")) {
                setText(SearchEmailInput, Keys.CONTROL + "a");
                setText(SearchEmailInput, inforBook[i][0]);
                Thread.sleep(2000);
                int mailBook = listElements(SelectEmailsButton);
                if (mailBook == 0) {
                    System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Milo Level 3 k thấy mail");
                } else {
                    for (int j = 1; j <= mailBook; j++) {
                        By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
                        String nameOnWeb = getTextElement(OptionUserName);
                        if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(inforBook[i][1].toLowerCase()))||removeAccent(inforBook[i][1].toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                            clickElement(OptionUserName);
                            Thread.sleep(500);
                            mailBook =listElements(SelectEmailsButton);
                            j = 1;
                        }
                        else if (j==mailBook){
                            System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Milo Level 1 k thấy name");
                        }
                    }
                }

            }
        }
        clickElement(EnrolSelectUserButton);
    }

    public void addMovers(String inforBook[][]) throws InterruptedException {
        openURL(URL_KB_Movers);
        clickElement(Enrol_Button);
        Thread.sleep(2000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Movers")) {
                setText(SearchEmailInput, Keys.CONTROL + "a");
                setText(SearchEmailInput, inforBook[i][0]);
                Thread.sleep(2000);
                int mailBook = listElements(SelectEmailsButton);
                if (mailBook == 0) {
                    System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Movers  k thấy mail");
                } else {
                    for (int j = 1; j <= mailBook; j++) {
                        By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
                        String nameOnWeb = getTextElement(OptionUserName);
                        if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(inforBook[i][1].toLowerCase()))||removeAccent(inforBook[i][1].toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                            clickElement(OptionUserName);
                            Thread.sleep(500);
                            mailBook =listElements(SelectEmailsButton);
                            j = 1;
                        }
                        else if (j==mailBook){
                            System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Milo Level 1 k thấy name");
                        }
                    }
                }
            }
        }
        clickElement(EnrolSelectUserButton);
    }

    public void addBeginners(String inforBook[][]) throws InterruptedException {
        openURL(URL_KB_Beginers);
        clickElement(Enrol_Button);
        Thread.sleep(2000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Beginners") && !inforBook[i][4].contains("Kid's Box Beginners (48)")) {
                setText(SearchEmailInput, Keys.CONTROL + "a");
                setText(SearchEmailInput, inforBook[i][0]);
                Thread.sleep(2000);
                int mailBook = listElements(SelectEmailsButton);
                if (mailBook == 0) {
                    System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Beginners k thấy mail");
                } else {
                    System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "done add Sách Beginners");
                }
                for (int j = 1; j <= mailBook; j++) {
                    By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
                    String nameOnWeb = getTextElement(OptionUserName);
                    if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(inforBook[i][1].toLowerCase()))||removeAccent(inforBook[i][1].toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                        clickElement(OptionUserName);
                        Thread.sleep(500);
                        mailBook =listElements(SelectEmailsButton);
                        j = 1;
                    }
                }
            }
        }
        clickElement(EnrolSelectUserButton);
    }

    public void addBeginnersNEW(String inforBook[][]) throws InterruptedException {
        openURL(URL_KB_Beginers_new);
        clickElement(Enrol_Button);
        Thread.sleep(2000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Beginners")||inforBook[i][4].contains("beginners")) {
                setText(SearchEmailInput, Keys.CONTROL + "a");
                setText(SearchEmailInput, inforBook[i][0]);
                Thread.sleep(2000);
                int mailBook = listElements(SelectEmailsButton);
                if (mailBook == 0) {
                    System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add BeginnersNEW k thấy mail");
                } else {
                    for (int j = 1; j <= mailBook; j++) {
                        By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
                        String nameOnWeb = getTextElement(OptionUserName);
                        if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(inforBook[i][1].toLowerCase()))||removeAccent(inforBook[i][1].toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                            clickElement(OptionUserName);
                            Thread.sleep(500);
                            mailBook =listElements(SelectEmailsButton);
                            j = 1;
                        }
                        else if (j==mailBook){
                            System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Beginners k thấy name");
                        }
                    }
                }
            }
        }
        clickElement(EnrolSelectUserButton);
    }

    public void addFlyers(String inforBook[][]) throws InterruptedException {
        openURL(URL_KB_Flyers);
        clickElement(Enrol_Button);
        Thread.sleep(2000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Flyers")) {
                setText(SearchEmailInput, Keys.CONTROL + "a");
                setText(SearchEmailInput, inforBook[i][0]);
                Thread.sleep(1500);
                int mailBook = listElements(SelectEmailsButton);
                if (mailBook == 0) {
                    System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Flyers k thấy mail");
                } else {
                    for (int j = 1; j <= mailBook; j++) {
                        By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
                        String nameOnWeb = getTextElement(OptionUserName);
                        if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(inforBook[i][1].toLowerCase()))||removeAccent(inforBook[i][1].toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                            clickElement(OptionUserName);
                            Thread.sleep(500);
                            mailBook =listElements(SelectEmailsButton);
                            j = 1;
                        }
                        else if (j==mailBook){
                            System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Flyes k thấy name");
                        }
                    }
                }
            }
        }
        clickElement(EnrolSelectUserButton);
    }
    public void addStarters(String inforBook[][]) throws InterruptedException {
        openURL(URL_KB_Starters);
        clickElement(Enrol_Button);
        Thread.sleep(2000);
        for (int i = 0; i < inforBook.length; i++) {
            if (inforBook[i][4].contains("Starters")) {
                setText(SearchEmailInput, Keys.CONTROL + "a");
                setText(SearchEmailInput, inforBook[i][0]);
                Thread.sleep(1500);
                int mailBook = listElements(SelectEmailsButton);
                if (mailBook == 0) {
                    System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Starters k thấy mail");
                } else {
                    for (int j = 1; j <= mailBook; j++) {
                        By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
                        String nameOnWeb = getTextElement(OptionUserName);
                        if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(inforBook[i][1].toLowerCase()))||removeAccent(inforBook[i][1].toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                            clickElement(OptionUserName);
                            Thread.sleep(500);
                            mailBook =listElements(SelectEmailsButton);
                            j = 1;
                        }
                        else if (j==mailBook){
                            System.out.println(inforBook[i][0] + " " + inforBook[i][2] + " " + "add Starters k thấy name");
                        }
                    }
                }
            }
        }
        clickElement(EnrolSelectUserButton);
    }

    public void addBook(String inforBook[][]) throws InterruptedException {
        System.out.println("ADD MOVERS");
        addMovers(inforBook);
        System.out.println("ADD BEGINNERS_NEW");
        addBeginnersNEW(inforBook);
        System.out.println("ADD FLYERS");
        addFlyers(inforBook);
        System.out.println("ADD STARTERS");
        addStarters(inforBook);
        System.out.println("ADD MILOS");
        Milos(inforBook);
        System.out.println("ADD SOLUTION ELEMENTARY");
        addSolutionElementary(inforBook);
        System.out.println("ADD SOLUTION PRE INTERMEDIATE");
        addSolutionPreIntermediate(inforBook);
    }

    public void addBooks(String URL_BOOKS, String email, String name, String namebooks, String contains, String notContains) throws InterruptedException {
        openURL(URL_BOOKS);
        clickElement(Enrol_Button);
        Thread.sleep(1000);
        if (notContains.isEmpty()) {
            if (namebooks.contains(contains)) {
                setText(SearchEmailInput, Keys.CONTROL + "a");
                setText(SearchEmailInput, email);
                Thread.sleep(1500);
                int mailBook = listElements(SelectEmailsButton);
                if (mailBook == 0) {
                    System.out.println(email + " " + name + " " + "add Flyers k thấy mail");
                } else {
                    System.out.println(email + " " + name + " " + "done add Sách Flyers");
                }
                for (int j = 1; j <= mailBook; j++) {
                    By OptionUserName = By.xpath("//*[@id=\"fitem_id_userlist\"]//li[" + j + "]/span/span[1]");
                    String nameOnWeb = getTextElement(OptionUserName);
                    if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(namebooks.toLowerCase()))||removeAccent(namebooks.toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                        clickElement(OptionUserName);
                        Thread.sleep(500);
                        mailBook =listElements(SelectEmailsButton);
                        j = 1;
                    }
                }
            }
        } else {
            if (namebooks.contains(contains) && !namebooks.contains(notContains)) {
                setText(SearchEmailInput, Keys.CONTROL + "a");
                setText(SearchEmailInput, email);
                Thread.sleep(1500);
                int mailBook = listElements(SelectEmailsButton);
                for (int j = 0; j < mailBook; j++) {
                    clickElement(SelectEmailButton);
                    Thread.sleep(1500);
                }
                if (mailBook == 0) {
                    System.out.println(email + " " + name + " " + "add Flyers k thấy mail");
                } else {
                    System.out.println(email + " " + name + " " + "done add Sách Flyers");
                }
            }
        }

        clickElement(EnrolSelectUserButton);
    }

    public void manageBooks() {
    }
    public void DeleteBooks() throws InterruptedException {
        openURL("https://moodle.speakup.vn/user/index.php?id=44");
        try {
            clickElement(showAllButton);
        }
        catch (Exception E){
                clickElement(showAllButton);
        }
        for (int i = 0; i< 5; i++){
            if (getTextElement(showAllButton).contains("Show 20 per page")){
                System.out.println(getTextElement(showAllButton));
                break;
            }
            else {
                i--;
                System.out.println(getTextElement(showAllButton));
                Thread.sleep(1000);
            }
        }
    }
}