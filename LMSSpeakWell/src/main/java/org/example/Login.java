package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Login extends WebUI{
    String URL_Login = "https://moodle.speakup.vn/login/index.php";
    String URL_Very = "https://moodle.speakup.vn/my/";
    String account = "support@speakup.vn";
    String passWord = "Spw123@123";
    By userNameInput = By.xpath("//*[@id=\"username\"]");
    By passWordInput = By.xpath("//*[@id=\"password\"]");
    By loginButton = By.xpath("//*[@id=\"loginbtn\"]");
    public void Login(){
        openBrowser();
        openURL(URL_Login);
        setText(userNameInput,account);
        setText(passWordInput,passWord);
        clickElement(loginButton);
        waitForPageLoaded();
    }
    public void VeryLogin(String user, String password) throws InterruptedException {
        setText(userNameInput, Keys.CONTROL + "a");
        setText(userNameInput, user);
        setText(passWordInput, Keys.CONTROL + "a");
        setText(passWordInput, password);
        clickElement(loginButton);
        Thread.sleep(2000);
        if (getCurrentUrl().contains(URL_Very)){
            System.out.println(user + " " + password + " true");
        }
        else{
            System.out.println(user + " " + password + " false");
        }
        closeBrowser();
    }

    public void ManageLogin(String[][] infomations) throws InterruptedException {
        for (int i = 0; i < infomations.length; i++) {
            anchrome();
            openURL(URL_Login);
            VeryLogin(infomations[i][2], infomations[i][3]);
        }
    }
    public void ManageLogin2(String[][] infomations) throws InterruptedException {
        for (int i = 1; i < infomations.length; i=i+2) {
            anchrome();
            openURL(URL_Login);
            VeryLogin(infomations[i][2], infomations[i][3]);
        }
    }
}
