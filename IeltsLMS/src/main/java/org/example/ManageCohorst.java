package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import javax.swing.plaf.TableHeaderUI;

public class ManageCohorst extends WebUI {
    String URL_COHORTS = "https://english.ican.vn/cohort/index.php";
    By cohortsBasic = By.xpath("//*[@id=\"cohorts\"]/tbody/tr[2]/td[6]/a[4]/i");
    String URL_PREMIUM = "https://english.ican.vn/cohort/assign.php?id=2&returnurl=%2Fcohort%2Findex.php%3Fpage%3D0";
    String URL_BACSIC = "https://english.ican.vn/cohort/assign.php?id=3&returnurl=%2Fcohort%2Findex.php%3Fpage%3D0";
    String URL_FOUNDATION = "https://english.ican.vn/cohort/assign.php?id=7&returnurl=%2Fcohort%2Findex.php%3Fpage%3D0";
    String URL_INTENSIVE = "https://english.ican.vn/cohort/assign.php?id=8&returnurl=%2Fcohort%2Findex.php%3Fpage%3D0";
    String URL_INTRODUCTION = "https://english.ican.vn/cohort/assign.php?id=6&returnurl=%2Fcohort%2Findex.php%3Fpage%3D0";
    String URL_PREPARATION = "https://english.ican.vn/cohort/assign.php?id=9&returnurl=%2Fcohort%2Findex.php%3Fpage%3D0";

    By cohortsFoundation = By.xpath("//*[@id=\"cohorts\"]/tbody/tr[3]/td[6]/a[4]/i");

    By cohortsPremium = By.xpath("//*[@id=\"cohorts\"]/tbody/tr[7]/td[6]/a[4]/i");


    By addEmailInput = By.xpath("//*[@id=\"addselect_searchtext\"]");
    By removeEmailInput = By.xpath("//*[@id=\"removeselect_searchtext\"]");
    By ADD = By.xpath("//*[@id=\"add\"]");
    By listAddSelectButton = By.xpath("//*[@id=\"addselect\"]//option");
    By addSelectLastButton = By.xpath("//*[@id=\"addselect\"]//option[1]");
    By removeSelectButton = By.xpath("//*[@id=\"removeselect\"]//option");

    By REMOVE = By.xpath("//*[@id=\"removeselect\"]//option");

    public void RUN(String[][] information) throws InterruptedException {
        AddCohorts(information);
    }

    public void AddCohorts(String[][] information) throws InterruptedException {
        openURL(URL_COHORTS);
        clickElement(cohortsBasic);
        Thread.sleep(1000);
        for (int i = 0; i < information.length; i++) {
            AddBasic(information[i][0], information[i][1], information[i][4]);
        }
        Thread.sleep(1000);
        openURL(URL_PREMIUM);
        for (int i = 0; i < information.length; i++) {
            AddPremium(information[i][0], information[i][1], information[i][4]);
        }
        Thread.sleep(1000);
        openURL(URL_INTRODUCTION);
        for (int i = 0; i < information.length; i++) {
            AddIntroduction(information[i][0], information[i][1], information[i][4]);
        }
        Thread.sleep(1000);
        openURL(URL_FOUNDATION);
        for (int i = 0; i < information.length; i++) {
            AddFoundation(information[i][0], information[i][1], information[i][4]);
        }
        Thread.sleep(1000);
        openURL(URL_INTENSIVE);
        for (int i = 0; i < information.length; i++) {
            AddIntensive(information[i][0], information[i][1], information[i][4]);
        }
        Thread.sleep(1000);
        openURL(URL_PREPARATION);
        for (int i = 0; i < information.length; i++) {
            AddPreparation(information[i][0], information[i][1], information[i][4]);
        }
    }

    public void AddBasic(String email, String name, String book) throws InterruptedException {
        if (book.contains("Foundation") || book.contains("Introduction")||book.contains("Combo 6 khóa")) {
            addMailToCohorts(email, name, "Basic");
        }
    }

    public void AddPremium(String email, String name, String book) throws InterruptedException {
        if (book.contains("Intensive") || book.contains("Preparation")||book.contains("Combo 6 khóa")) {
            addMailToCohorts(email, name, "Premium");
        }
    }

    public void AddFoundation(String email, String name, String book) throws InterruptedException {
        if (book.contains("Foundation")||book.contains("Combo 6 khóa")) {
            addMailToCohorts(email, name, "Foundation");
        }
    }

    public void AddIntroduction(String email, String name, String book) throws InterruptedException {
        if (book.contains("Introduction")||book.contains("Combo 6 khóa")) {
            addMailToCohorts(email, name, "Introduction");
        }
    }

    public void AddIntensive(String email, String name, String book) throws InterruptedException {
        if (book.contains("Intensive")||book.contains("Combo 6 khóa")) {
            addMailToCohorts(email, name, "Intensive");
        }
    }

    public void AddPreparation(String email, String name, String book) throws InterruptedException {
        if (book.contains("Preparation")||book.contains("Combo 6 khóa")) {
            addMailToCohorts(email, name, "Preparation");
        }
    }

    public void addMailToCohorts(String email, String name, String NameCohorts) throws InterruptedException {
        setText(addEmailInput, Keys.CONTROL + "a");
        setText(addEmailInput, email.replaceAll(" ", ""));
        Thread.sleep(2000);
        if (getTextElement(listAddSelectButton).contains("@")) {
            int listSelectEmail = listElements(listAddSelectButton);
            if (listSelectEmail >= 1) {
                for (int i = 1; i <= listSelectEmail; i++) {
                    By selectEmail = By.xpath("//*[@id=\"addselect\"]//option[" + i + "]");
                    String nameOnWeb = getTextElement(selectEmail);
                    if (removeAccent(nameOnWeb.substring(0, nameOnWeb.lastIndexOf('(')).toLowerCase()).contains(removeAccent(name.toLowerCase()))||removeAccent(name.toLowerCase()).contains(removeAccent(nameOnWeb.substring(0, nameOnWeb.lastIndexOf('(')).toLowerCase())) ) {
                        Thread.sleep(500);
                        clickElement(addSelectLastButton);
                        Thread.sleep(1000);
                        clickElement(ADD);
                        Thread.sleep(1000);
                        i = 1;
                        listSelectEmail = listElements(listAddSelectButton);
                    }
                }
            }
        } else {
            setText(removeEmailInput, Keys.CONTROL + "a");
            setText(removeEmailInput, email);
            Thread.sleep(2000);
            if (getTextElement(removeSelectButton).contains("@")) {
                int listRemoveEmail = listElements(removeSelectButton);
                if (listRemoveEmail > 0) {
                    for (int i = 1; i <= listRemoveEmail; i++) {
                        By selectEmail = By.xpath("//*[@id=\"removeselect\"]//option[" + i + "]");
                        String nameOnWeb = getTextElement(selectEmail);
                        if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(name.toLowerCase()))) {
                            break;
                        } else if (i == listRemoveEmail) {
                            System.out.println(email + " " + name + " không tìm thấy Mail");
                        }
                    }
                }
            }
            Thread.sleep(500);
        }
    }
}
