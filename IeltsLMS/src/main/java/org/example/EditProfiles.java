package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Set;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class EditProfiles extends WebUI {
    String URL_REGISTER = "https://english.ican.vn/user/editadvanced.php?id=-1";
    String URL_SUCSESS = "https://english.ican.vn/admin/user.php";
    By usernameInput = By.id("id_username");
    By passWordButton = By.xpath("/html/body/div[3]/div[3]/div/div/section[1]/div/form/fieldset[1]/div/div[6]/div[2]/span/a[1]/span/span");
    By passWordInput = By.id("id_newpassword");
    By firstnameInput = By.id("id_firstname");
    By lastnameInput = By.id("id_lastname");
    By emailInput = By.id("id_email");
    By creatUserButton = By.xpath("//*[@id=\"id_submitbutton\"]");
    By messageUserError = By.xpath("//*[@id=\"id_error_username\"]");
    By messageEmailError = By.xpath("//*[@id=\"id_error_email\"]");

    public void EditUserName(String email, String name, String username) throws InterruptedException {

        //chèn kí tự x vào giữa chuỗi của user
        username = username.substring(0, username.length() - 4) + "x" + username.substring(username.length() - 4);
        for (int i = 1; i < 100; i++) {
            if (i <= 10) {
                // thay thế i vào x của chuỗi user
                username = username.substring(0, username.length() - 5) + i + username.substring(username.length() - 4);
            } else {
                // điền i có 2 kí tự vào chuỗi
                username = username.substring(0, username.length() - 6) + i + username.substring(username.length() - 4);
            }
            setText(usernameInput, Keys.CONTROL + "a");
            setText(usernameInput, username);
            clickElement(passWordButton);
            setText(passWordInput, Keys.CONTROL + "a");
            setText(passWordInput, capitalize(username) + "@123");
            try {
                clickElement(creatUserButton);
            } catch (Exception E) {
                clickElement(creatUserButton);
            }
            Thread.sleep(1000);
            if (getCurrentUrl().contains(URL_SUCSESS)) {
                System.out.println(email + " " + name + " =>>>>>>>>>>>>>>>> " + username);
                break;
            }
        }
    }

    public void OnlyEditErrorUser(String email, String name, String user, String password) throws InterruptedException {
        if (user.contains("vdi.")) {
            user = "vdi." + "1" + user.substring(4);
            password = "Vdi." + "1" + capitalize(user.substring(4)) + "@123";
        } else if (user.contains("ps.")) {
            user = "ps." + "1" + user.substring(3);
            password = "Ps." + "1" + capitalize(user.substring(3)) + "@123";
        }else if (user.contains("cb6.")) {
            user = "cb6." + "1" + user.substring(4);
            password = "Cb6." + "1" + capitalize(user.substring(4)) + "@123";
        } else if (user.contains("vtm.")) {
            user = "vtm." + "1" + user.substring(4);
            password = "Vtm." + "1" + capitalize(user.substring(4)) + "@123";
        }
        for (int i = 1; i <= 100; i++) {
            if (i == 100) {
                System.out.println(email + " " + name + " " + user + " chịu rồi, đổi user 100 lần mà vẫn báo trùng!!!!!!");
                break;
            }
            if (i >= 11) {
                if (user.contains("vdi.")) {
                    user = "vdi." + i + user.substring(6);
                    password = "Vdi." + i + capitalize(user.substring(6)) + "@123";
                }
                else if (user.contains("ps.")) {
                    user = "ps." + i + user.substring(5);
                    password = "Ps." + i + capitalize(user.substring(5)) + "@123";
                }else if (user.contains("cb6.")) {
                    user = "cb6." + i + user.substring(6);
                    password = "Cb6." + i + capitalize(user.substring(6)) + "@123";
                }else if (user.contains("vtm.")) {
                    user = "vtm." + i + user.substring(6);
                    password = "Vtm." + i + capitalize(user.substring(6)) + "@123";
                }else if (user.contains("m.")){
                    user = "m." + i + user.substring(4);
                    password = "M." + i + capitalize(user.substring(4)) + "@123";
                }
            }
            else {
                if (user.contains("vdi.")) {
                    user = "vdi." + i + user.substring(5);
                    password = "Vdi." + i + capitalize(user.substring(5)) + "@123";
                } else if (user.contains("ps.")) {
                    user = "ps." + i + user.substring(4);
                    password = "Ps." + i + capitalize(user.substring(4)) + "@123";
                }else if (user.contains("cb6.")) {
                    user = "cb6." + i + user.substring(5);
                    password = "Cb6." + i + capitalize(user.substring(5)) + "@123";
                }
                else if (user.contains("vtm.")) {
                    user = "vtm." + i + user.substring(5);
                    password = "Vtm." + i + capitalize(user.substring(5)) + "@123";
                }else if (user.contains("m.")){
                    user = "m." + i + user.substring(3);
                    password = "M." + i + capitalize(user.substring(3)) + "@123";
                }
            }

            setText(usernameInput, Keys.CONTROL + "a");
            setText(usernameInput, user);
            clickElement(passWordButton);
            Thread.sleep(500);
            setText(passWordInput, Keys.CONTROL + "a");
            setText(passWordInput, password);
            try {
                clickElement(creatUserButton);
            } catch (Exception E) {
                clickElement(creatUserButton);
            }
            if (getCurrentUrl().contains(URL_SUCSESS)) {
                System.out.println(email + " " + name + " =>>>>>>>>>>> " + user);
                break;
            }
        }
    }
    public void  UpdateUserPassword(String email, String name, String user, String password){
            user = "cb6." + "1" + user.substring(4);
            password = "Cb6." + "1" + capitalize(user.substring(4)) + "@123";
            for (int i = 0 ; i<= 100 ; i++){
                if (i == 100) {
                    System.out.println(email + " " + name + " " + user + " chịu rồi, đổi user 100 lần mà vẫn báo trùng!!!!!!");
                    break;
                }
                if (i >= 11) {
                        user = "cb6." + i + user.substring(5);
                        password = "Cb6." + i + capitalize(user.substring(5)) + "@123";
                }
                else{
                        user = "cb6." + i + user.substring(5);
                        password = "Cb6." + i + capitalize(user.substring(5)) + "@123";
                }
                setText(usernameInput, Keys.CONTROL +"a");
                setText(usernameInput, user);

                clickElement(passWordButton);
                setText(passWordInput, Keys.CONTROL +"a");
                setText(passWordInput, password);

                try {
                    clickElement(creatUserButton);
                }
                catch (Exception E){
                    clickElement(creatUserButton);
                }
                if (getCurrentUrl().contains(URL_SUCSESS)) {
                    System.out.println(email + " " + name + " =>>>>>>>>>>> " + user);
                    break;
                }
            }
    }

    public void EditEmailAndUser(String email, String name, String username, String password) throws InterruptedException {
        username = username.substring(0, 4) + "1" + username.substring(4);
        email =   email + "spw1";
        password = capitalize(username.substring(0, 4)) + "1" + capitalize(username.substring(4));
        //kiểm tra tên xem có mấy khoảng trống
        String[] words = name.split("\\s");
        for (int i = 2; i <= 100; i++) {
            openURL(URL_REGISTER);
            setText(usernameInput, Keys.CONTROL + "a");
            setText(usernameInput, username);
            clickElement(passWordButton);
            setText(passWordInput, Keys.CONTROL + "a");
            setText(passWordInput, password);
            if (i <= 10) {
                //vòng lặp nhập từng tên 1
                for (int j = 0; j < words.length - 1; j++) {
                    setText(firstnameInput, words[j] + " ");
                }
                setText(lastnameInput, words[words.length - 1]);
                setText(emailInput, email);
                try {
                    clickElement(creatUserButton);
                } catch (Exception E) {
                    clickElement(creatUserButton);
                }
                Thread.sleep(1000);

                if (getCurrentUrl().contains(URL_SUCSESS)) {
                    System.out.println(email + " " + name + " =>>>>>>>>>>>>>>>> " + username);
                    break;
                }
                //nếu chỉ có user trùng thì
                else if (driver.findElement(messageUserError).isDisplayed() && !driver.findElement(messageEmailError).isDisplayed()) {
                    username = username.substring(0, username.length() - 5) + i + username.substring(username.length() - 4);
                }
                //Nếu chỉ có mail trùng
                else if (!driver.findElement(messageUserError).isDisplayed() && driver.findElement(messageEmailError).isDisplayed()) {
                    email =  email.substring(0, email.length() - 4) + "spw" + i;
                } else
                {
                    username = username.substring(0, username.length() - 5) + i + username.substring(username.length() - 4);
                    email =  email.substring(0, email.length() - 4) + "spw" + i;
                }
            } else {
                //vòng lặp nhập từng tên 1
                for (int j = 0; j < words.length - 1; j++) {
                    setText(firstnameInput, words[j] + " ");
                }
                setText(lastnameInput, words[words.length - 1]);
                setText(emailInput, email);
                try {
                    clickElement(creatUserButton);
                } catch (Exception E) {
                    clickElement(creatUserButton);
                }
                Thread.sleep(1000);

                if (getCurrentUrl().contains(URL_SUCSESS)) {
                    System.out.println(email + " " + name + " =>>>>>>>>>>>>>>>> " + username);
                    break;
                }
                //nếu chỉ có user trùng thì
                else if (driver.findElement(messageUserError).isDisplayed() && !driver.findElement(messageEmailError).isDisplayed()) {
                    username = username.substring(0, username.length() - 6) + i + username.substring(username.length() - 4);
                } else if (!driver.findElement(messageUserError).isDisplayed() && driver.findElement(messageEmailError).isDisplayed()) {
                    email = "spw" + i + email.substring(5);
                } else {
                    username = username.substring(0, username.length() - 6) + i + username.substring(username.length() - 4);
                    email = "spw" + i + email.substring(5);
                }
            }

        }
    }

    public void OnlyEditEmail(String email, String name, String user, String password) throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            if (i == 100) {
                System.out.println(email + " " + name + " " + user + " Trùng Mail, đổi mail 100 lần vẫn k được!");
                break;
            }
            openURL(URL_REGISTER);
            String[] words = name.split("\\s");
            setText(usernameInput, Keys.CONTROL + "a");
            setText(usernameInput, user);
            clickElement(passWordButton);
            setText(passWordInput, Keys.CONTROL + "a");
            setText(passWordInput, password);
            for (int x = 0; x < words.length - 1; x++) {
                setText(firstnameInput, words[x] + " ");
            }
            setText(lastnameInput, Keys.CONTROL + "a");
            setText(lastnameInput, words[words.length - 1]);
            setText(emailInput, Keys.CONTROL + "a");
            setText(emailInput, email + "spw" + i);
            try {
                clickElement(creatUserButton);
            } catch (Exception E) {
                clickElement(creatUserButton);
            }
            Thread.sleep(2000);
            if (getCurrentUrl().contains(URL_SUCSESS)) {
                System.out.println(email +  name +" đổi email mới r nhé!!!=> " + user);
                break;
            } else {
                email = email.substring(4);
            }
        }

    }
}
