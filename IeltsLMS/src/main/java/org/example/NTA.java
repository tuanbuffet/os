package org.example;

import org.openqa.selenium.By;

import java.util.Scanner;

public class NTA extends WebUI{
    private String URL = "https://2fa.live/";
    private By FAInput = By.xpath("//*[@id=\"listToken\"]");
    private  By SubmitButton = By.xpath("//*[@id=\"submit\"]");
    private By Output = By.xpath("//*[@id=\"output\"]");
    Scanner sc = new Scanner(System.in);
    public void NTA(){
        String Keys;
        anchrome();
        openURL(URL);
        setText(FAInput, "NTA");
        for (int i = 0 ; i<= 3 ; i++){
        clickElement(SubmitButton);
        System.out.println("Nhập mật khẩu:");
        Keys = sc.nextLine();
            if ((getAttributeElement(Output, "value").substring(5)).equals(Keys)){
                break;
            }
        }
        driver.quit();
    }
}
