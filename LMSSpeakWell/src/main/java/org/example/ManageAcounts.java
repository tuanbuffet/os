package org.example;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.apache.commons.lang3.text.WordUtils.capitalize;


public class ManageAcounts extends WebUI {
    CheckSingUp checkSingUp = new CheckSingUp();

    EditProfiles editProfiles = new EditProfiles();

    By usernameInput = By.id("id_username");
    By passWordInput = By.xpath("/html/body/div[3]/div[3]/div/div/section[1]/div/form/fieldset[1]/div/div[6]/div[2]/span/a[1]/span/span");
    By passWordInput2 = By.id("id_newpassword");
    By firstnameInput = By.id("id_firstname");
    By lastnameInput = By.id("id_lastname");
    By emailInput = By.id("id_email");
    By creatUserButton = By.xpath("//*[@id=\"id_submitbutton\"]");
    By messageUserError = By.xpath("//*[@id=\"id_error_username\"]");
    By messageEmailError = By.xpath("//*[@id=\"id_error_email\"]");

    public String SetUpUser( String name, String user, String book) {
        if (book.contains("Viettel Money")){
            user = "vtm." + Unsigned(convertUser(name).substring(0, 1)) + convertUser(name).substring(1) + "2023";
        }
        else {
            user = Unsigned(convertUser(name).substring(0,1)) + convertUser(name).substring(1) + "2023";
        }
        return user;
    }
    public String SetUpPassWord(String user, String password, String book) {
        if (book.contains("Viettel Money")){
            password = capitalize(user.substring(0, 4)) + StringUtils.capitalize(user.substring(4)) + "@123";
        }
        else {
            password =capitalize(user)+"@123";
        }
        return password;
    }

    public void creatUser(String infomations[][]) throws InterruptedException {


        for (int i = 0; i < infomations.length; i++) {
            if (infomations[i][2] == ""){
                infomations[i][2] = SetUpUser(infomations[i][1],infomations[i][2],infomations[i][4]);
                infomations[i][3] =SetUpPassWord(infomations[i][2],infomations[i][3],infomations[i][4]);
            }
            infomations[i][0] = infomations[i][0].replaceAll(" ","");
            openURL("https://moodle.speakup.vn/user/editadvanced.php?course=1&id=-1");
            setText(usernameInput, infomations[i][2]);
            clickElement(passWordInput);
            setText(passWordInput2, capitalize(infomations[i][3]));
            String[] words = infomations[i][1].split("\\s");
            if (words.length == 1){
                infomations[i][1] = infomations[i][1] + " " + infomations[i][1];
                words =infomations[i][1].split("\\s");
            }
            for (int x = 0; x < words.length - 1; x++) {
                setText(firstnameInput, words[x] + " ");
            }
            setText(lastnameInput, words[words.length - 1]);
            setText(emailInput, infomations[i][0]);
            try {
                clickElement(creatUserButton);
            } catch (Exception E) {
                clickElement(creatUserButton);
            }
            Thread.sleep(2000);
            if (getCurrentUrl().contains("https://moodle.speakup.vn/admin/user.php")) {
                System.out.println(infomations[i][0] + "   " + infomations[i][1] + " done=>>>>>>>>>>>> "+ infomations[i][2] );
                /*Thread.sleep(1000);*/
            } else if (driver.findElement(messageEmailError).isDisplayed()) {
                if (getTextElement(messageEmailError).contains("Invalid email address")){
                    System.out.println(infomations[i][0] + "   " + infomations[i][1] + " InvalidEmailAddress");
                }
                //chỉ có mail trùng
                else if (!driver.findElement(messageUserError).isDisplayed()) {
                    if (!checkSingUp.CheckMailRgister(infomations[i][0], infomations[i][1])) {
                        editProfiles.OnlyEditEmail(infomations[i][0], infomations[i][1],infomations[i][2],capitalize(infomations[i][2])+"@123");
                    }
                    //cả mail và user trùng
                } else if (driver.findElement(messageUserError).isDisplayed()) {
                    if (!checkSingUp.CheckMailRgister(infomations[i][0], infomations[i][1])) {
                        editProfiles.EditEmailAndUser(infomations[i][0], infomations[i][1], infomations[i][2], capitalize(infomations[i][2])+"@123");
                    }
                }
            }
            //chỉ có user trùng
            else if (driver.findElement(messageUserError).isDisplayed() && !driver.findElement(messageEmailError).isDisplayed()) {
                editProfiles.EditUserName(infomations[i][0], infomations[i][1],infomations[i][2], infomations[i][3]);
            }
        }
        openURL("https://moodle.speakup.vn/user/editadvanced.php?course=1&id=-1");
    }
}