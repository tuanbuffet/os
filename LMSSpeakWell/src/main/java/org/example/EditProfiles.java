package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class EditProfiles extends WebUI {
    By usernameInput = By.id("id_username");
    By passWordInput = By.xpath("/html/body/div[3]/div[3]/div/div/section[1]/div/form/fieldset[1]/div/div[6]/div[2]/span/a[1]/span/span");
    By passWordInput2 = By.id("id_newpassword");
    By firstnameInput = By.id("id_firstname");
    By lastnameInput = By.id("id_lastname");
    By emailInput = By.id("id_email");
    By creatUserButton = By.xpath("//*[@id=\"id_submitbutton\"]");
    By messageUserError = By.xpath("//*[@id=\"id_error_username\"]");
    By messageEmailError = By.xpath("//*[@id=\"id_error_email\"]");

    public void EditUserName(String email, String name, String username ,String password) throws InterruptedException {
        if (username.contains("vtm.") || username.contains("cb6.")) {
            username = username.substring(0, 4) + "x" + username.substring(4);
        }
        else {
            username = username.substring(0, username.length() - 4) + "x" + username.substring(username.length() - 4);
        }
        for (int i = 1; i < 100; i++) {
            if (username.contains("vtm.") || username.contains("cb6.")) {
                if (i <= 10) {
                    username = username.substring(0, 4) + i + username.substring(5);
                    for (int j = 4; j < username.length(); j++) {
                        if (!isNumer(username.substring(j, j + 1))) {
                            password = capitalize(username.substring(0, j)) + capitalize(username.substring(j)) + "@123";
                            break;
                        }
                    }
                } else {
                    username = username.substring(0, 4) + i + username.substring(6);
                    for (int j = 4; j <= username.length(); j++) {
                        if (!isNumer(username.substring(j, j + 1))) {
                            password = capitalize(username.substring(0, j)) + capitalize(username.substring(j)) + "@123";
                            break;
                        }
                    }
                }
            } else {
                if (i <= 10) {
                    username = username.substring(0, username.length() - 5) + i + username.substring(username.length() - 4);
                } else {
                    // điền i có 2 kí tự vào chuỗi
                    username = username.substring(0, username.length() - 6) + i + username.substring(username.length() - 4);
                }
                password = capitalize(username) + "@123";
            }
            setText(usernameInput, Keys.CONTROL + "a");
            setText(usernameInput, username);
            clickElement(passWordInput);
            setText(passWordInput2, Keys.CONTROL + "a");
            setText(passWordInput2, password);
            try {
                clickElement(creatUserButton);
            }
            catch (Exception E) {
                try {
                    clickElement(creatUserButton);
                }
                catch (Exception e) {
                    clickElement(creatUserButton);
                }
            }
            Thread.sleep(1000);
            if (getCurrentUrl().contains("https://moodle.speakup.vn/admin/user.php")) {
                System.out.println(email + " " + name + " =>>>>>>>>>>>>>>>> " + username);
                break;
            }
        }
    }

    /*public void EditUserName(String email, String name, String username) throws InterruptedException {
        String password;
        //chèn kí tự x vào giữa chuỗi của user
        if (username.contains("vtm.") || username.contains("cb6.")) {
            username = username.substring(0, 4) + "x" + username.substring(4, username.length());
        } else {
            username = username.substring(0, username.length() - 4) + "x" + username.substring(username.length() - 4);
        }
        for (int i = 1; i < 100; i++) {
            if (i <= 10) {
                // thay thế i vào x của chuỗi user
                username = username.substring(0, 4) + i + username.substring(5, username.length());
                username = username.substring(0, username.length() - 5) + i + username.substring(username.length() - 4);
            } else {
                // điền i có 2 kí tự vào chuỗi
                username = username.substring(0, 4) + i + username.substring(6, username.length());
                username = username.substring(0, username.length() - 6) + i + username.substring(username.length() - 4);
            }
            setText(usernameInput, Keys.CONTROL + "a");
            setText(usernameInput, username);
            clickElement(passWordInput);
            *//*if ()*//*
            setText(passWordInput2, Keys.CONTROL + "a");
            setText(passWordInput2, capitalize(username) + "@123");
            try {
                clickElement(creatUserButton);
            } catch (Exception E) {
                try {
                    clickElement(creatUserButton);
                } catch (Exception e) {
                    clickElement(creatUserButton);
                }
            }
            Thread.sleep(1000);
            if (getCurrentUrl().contains("https://moodle.speakup.vn/admin/user.php")) {
                System.out.println(email + " " + name + " =>>>>>>>>>>>>>>>> " + username);
                break;
            }
        }
    }*/

    public void EditEmailAndUser(String email, String name, String username, String password) throws InterruptedException {
        username = username.substring(0, username.length() - 4) + "1" + username.substring(username.length() - 4);
        email = "spw1" + email;

        //kiểm tra tên xem có mấy khoảng trống
        String[] words = name.split("\\s");
        for (int i = 2; i <= 100; i++) {
            openURL("https://moodle.speakup.vn/user/editadvanced.php?course=1&id=-1");
            setText(usernameInput, Keys.CONTROL + "a");
            setText(usernameInput, username);
            clickElement(passWordInput);
            setText(passWordInput2, Keys.CONTROL + "a");
            setText(passWordInput2, capitalize(username) + "@123");
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

                if (getCurrentUrl().contains("https://moodle.speakup.vn/admin/user.php")) {
                    System.out.println(email + " " + name + " =>>>>>>>>>>>>>>>> " + username);
                    break;
                }
                //nếu chỉ có user trùng thì
                else if (driver.findElement(messageUserError).isDisplayed() && !driver.findElement(messageEmailError).isDisplayed()) {
                    username = username.substring(0, username.length() - 5) + i + username.substring(username.length() - 4);
                } else if (!driver.findElement(messageUserError).isDisplayed() && driver.findElement(messageEmailError).isDisplayed()) {
                    email = "spw" + i + email.substring(4);
                } else {
                    username = username.substring(0, username.length() - 5) + i + username.substring(username.length() - 4);
                    email = "spw" + i + email.substring(4);
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

                if (getCurrentUrl().contains("https://moodle.speakup.vn/admin/user.php")) {
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
        for (int i = 1; i <= 9; i++) {
            email = "spw" + i + email;
            if (i == 9) {
                System.out.println(email + " " + name + " " + user + " Trùng Mail, đổi mail 10 lần vẫn k được!");
                break;
            }
            openURL("https://moodle.speakup.vn/user/editadvanced.php?course=1&id=-1");
            String[] words = name.split("\\s");
            setText(usernameInput, Keys.CONTROL + "a");
            setText(usernameInput, user);
            clickElement(passWordInput);
            setText(passWordInput2, Keys.CONTROL + "a");
            setText(passWordInput2, password);
            for (int x = 0; x < words.length - 1; x++) {
                setText(firstnameInput, words[x] + " ");
            }
            setText(lastnameInput, Keys.CONTROL + "a");
            setText(lastnameInput, words[words.length - 1]);
            setText(emailInput, Keys.CONTROL + "a");
            setText(emailInput, email);
            try {
                clickElement(creatUserButton);
            } catch (Exception E) {
                clickElement(creatUserButton);
            }
            Thread.sleep(1000);
            if (getCurrentUrl().contains("https://moodle.speakup.vn/admin/user.php")) {
                System.out.println(email + "=> đổi email mới r nhé!!!" + " " + name + " " + user);
                break;
            } else {
                email = email.substring(4);
            }
        }

    }
}
