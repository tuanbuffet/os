package org.example;

import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class ManageAccounts extends WebUI {

    String URL_REGISTER = "https://english.ican.vn/user/editadvanced.php?id=-1";
    String URL_SUCSESS = "https://english.ican.vn/admin/user.php";
    By usernameInput = By.id("id_username");
    By passWordInput = By.xpath("/html/body/div[3]/div[3]/div/div/section[1]/div/form/fieldset[1]/div/div[6]/div[2]/span/a[1]/span/span");
    By passWordInput2 = By.id("id_newpassword");
    By firstnameInput = By.id("id_firstname");
    By lastnameInput = By.id("id_lastname");
    By emailInput = By.id("id_email");
    By creatUserButton = By.xpath("//*[@id=\"id_submitbutton\"]");
    By messageUserError = By.xpath("//*[@id=\"id_error_username\"]");
    By messageEmailError = By.xpath("//*[@id=\"id_error_email\"]");
    EditProfiles editProfiles = new EditProfiles();
    CheckSingUp checkSingUp = new CheckSingUp();

    public void RUN(String[][] infomations) throws InterruptedException {
        for (int i = 0; i <= infomations.length - 1; i++) {
            try {
                CreatUser(infomations[i][0], infomations[i][1], infomations[i][2], infomations[i][3], infomations[i][4]);
            }
            catch (Exception e) {
                System.out.println(infomations[i][0] + " " + infomations[i][1] + " Loi KHONG XAC DINH");
            }
        }
    }
    public void CreatUser(String email, String name, String user, String password, String book) throws InterruptedException {
        openURL(URL_REGISTER);
        if(user.isEmpty()){
            user = SetUpUser(name, user, book);
            password = SetUpPassWord(user, password, book);
        }
        InputData(email,name,user,password);
        Submit();
        CheckDone(email, name, user, password, book);
    }

    public Boolean CheckCreatUser() {
        if (getCurrentUrl().contains(URL_SUCSESS)) return true;
        else return false;
    }
    public String SetUpUser( String name, String user, String book) {
        if (book.contains("Viettel Money")){
            user = "vtm." + Unsigned(convertUser(name).substring(0, 1)) + convertUser(name).substring(1) + "2023";
        }
        else {
            if (book.contains("Preparation")||book.contains("Intensive")||book.contains("Introduction")||book.contains("Foundation")) {
                user = "vdi." + Unsigned(convertUser(name).substring(0, 1)) + convertUser(name).substring(1) + "2023";
            }
            else if (book.contains("Public Speaking")) {
                user = "ps." + Unsigned(convertUser(name).substring(0, 1)) + convertUser(name).substring(1) + "2023";
            }
            else if (book.contains("Combo 6 khóa")){
                user = "cb6." + Unsigned(convertUser(name).substring(0, 1)) + convertUser(name).substring(1);
            }
            else if (book.contains("Master")){
                user = "m." + Unsigned(convertUser(name).substring(0, 1)) + convertUser(name).substring(1) + "2023";
            }
        }
        return user;
    }
    public String SetUpPassWord(String user, String password, String book) {
        if (book.contains("Viettel Money")){
            password = capitalize(user.substring(0, 4)) + capitalize(user.substring(4)) + "@123";
        }
        else {
            if (book.contains("Easy IELTS Video")) {
                password = capitalize(user.substring(0, 4)) + capitalize(user.substring(4)) + "@123";
            } else if (book.contains("Public Speaking")) {
                password = capitalize(user.substring(0, 3)) + capitalize(user.substring(3)) + "@123";
            }
            else if (book.contains("Combo 6 khóa")){
                password = capitalize(user.substring(0, 4)) + capitalize(user.substring(4)) + "@123";
            }
        }
        return password;
    }
    public void InputData(String email, String name, String user, String password){
        if (email.equals("")) email = "wellcomespeakwell@hot.com";
        String[] words = name.split("\\s");
        setText(usernameInput, user);
        clickElement(passWordInput);
        setText(passWordInput2, password);
        if (words.length == 1){
            setText(firstnameInput, words[0]);
        }
        else {
            for (int i = 0; i < words.length - 1; i++) {
                setText(firstnameInput, words[i] + " ");
            }
        }
        setText(lastnameInput, words[words.length - 1]);
        setText(emailInput, email);
    }
    public void Submit() throws InterruptedException {
        try {
            clickElement(creatUserButton);
        } catch (Exception E) {
            try {
                clickElement(creatUserButton);
            }
            catch (Exception G){
                clickElement(creatUserButton);
            }
        }
        Thread.sleep(2000);
    }
    public void CheckDone(String email, String name, String user, String password, String book) throws InterruptedException {
        if (CheckCreatUser()) {
            System.out.println(email + " " + name + " done>>>>>> "+ user);
        }
        else {
            //Nếu mail báo đang bị lỗi
            if (driver.findElement(messageEmailError).isDisplayed()) {
                //Mail báo lỗi định dạng
                if (getTextElement(messageEmailError).contains("Invalid email address")) {
                    System.out.println(email + "   " + name + " InvalidEmailAddress");
                }
                //Chỉ có mail lỗi
                else if (!driver.findElement(messageUserError).isDisplayed()) {

                    if (!checkSingUp.CheckMailRgister(email, name,book)) {

                        editProfiles.OnlyEditEmail(email, name, user, password);
                    }
                }
                //Cả mail và user lỗi
                else if (driver.findElement(messageUserError).isDisplayed()) {

                    if (!checkSingUp.CheckMailRgister(email, name,book)) {
                        System.out.println("Tn k trùng trên sheer");
                        editProfiles.EditEmailAndUser(email, name, user, password);
                    }
                }
            } else {
                editProfiles.OnlyEditErrorUser(email, name, user, password);
            }
        }
    }
}
