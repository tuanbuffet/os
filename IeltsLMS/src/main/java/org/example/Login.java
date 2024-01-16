package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Login extends WebUI {
    NTA s = new NTA();

    String URL_Login = "https://english.ican.vn/login/index.php";
    String accountGoogle = "ielts.advisor@ican.vn";
    String passWordGoogle = "Ielts@123456";
    By usernameInput = By.xpath("//*[@id=\"username\"]");
    By passwordInput = By.xpath("//*[@id=\"password\"]");
    By loginButton = By.xpath("//*[@id=\"loginbtn\"]");
    By myAccount = By.xpath("//*[@id=\"action-menu-toggle-1\"]/span/span[2]/span/img");
    By logOut = By.xpath("//*[@id=\"actionmenuaction-6\"]");
    String URL_Very = "https://english.ican.vn/my/";
    By LinkGoogleButton = By.xpath("//*[@id=\"region-main\"]/div/div[2]/div/div/div/div/div/div[2]/div[3]/div/a");
    By UserAnotherGoogleButton = By.xpath("//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div/div/ul/li[2]/div/div/div[2]");
    By emailAccountInput = By.xpath("//*[@id=\"identifierId\"]");
    By nextAccountButton = By.xpath("//*[@id=\"identifierNext\"]/div");
    By emailPasswordInput = By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input");
    By nextPassWordButton = By.xpath("//*[@id=\"passwordNext\"]/div");

    public void Login() throws InterruptedException {
        s.NTA();
        openBrowser();
        openURL(URL_Login);
        clickElement(LinkGoogleButton);
        try {
            setText(emailAccountInput, accountGoogle);
        } catch (Exception E) {
            clickElement(UserAnotherGoogleButton);
            setText(emailAccountInput, accountGoogle);
        }
        clickElement(nextAccountButton);
        setText(emailPasswordInput, passWordGoogle);
        clickElement(nextPassWordButton);
        waitForPageLoaded();
        Thread.sleep(5000);
    }
    public void VeryLogin(String user, String password) throws InterruptedException {
        setText(usernameInput, Keys.CONTROL + "a");
        setText(usernameInput, user);
        setText(passwordInput, Keys.CONTROL + "a");
        setText(passwordInput, password);
        clickElement(loginButton);
        Thread.sleep(1000);
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
}
