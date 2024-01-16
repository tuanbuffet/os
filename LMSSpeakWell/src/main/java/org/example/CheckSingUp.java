package org.example;

import org.openqa.selenium.By;

import static org.apache.commons.lang3.StringUtils.*;


public class CheckSingUp extends WebUI {
    String URL_CheckMail = "https://moodle.speakup.vn/admin/user.php";
    By removeAllFIlterButton = By.xpath("//*[@id=\"id_removeall\"]");
    By showMoreButton = By.xpath("/html/body/div[2]/div[3]/div/div/section[1]/div/form[1]/fieldset/div/div[2]/div/a");
    By emailAddressInput = By.xpath("//*[@id=\"id_email\"]");
    By addFilterButton = By.xpath("//*[@id=\"id_addfilter\"]");
    By listuserElements = By.xpath("//*[@id=\"users\"]/tbody/tr");
    By getUserName = By.xpath("//*[@id=\"id_username\"]");
    By passWordButton = By.xpath("/html/body/div[3]/div[3]/div/div/section/div/form/fieldset[1]/div/div[5]/div[2]/span/a[1]/span/span/em");
    By editPassWordButton = By.xpath("//*[@id=\"id_newpassword\"]");
    By updateProfileButton = By.xpath("//*[@id=\"id_submitbutton\"]");

    public void ManageRegister(String[][] informations) throws InterruptedException {
        for (int i = 0; i < informations.length; i++) {
            CheckMailRgister(informations[i][0], informations[i][1]);
        }
    }

    public boolean CheckMailRgister(String email, String name) throws InterruptedException {
        openURL(URL_CheckMail);
        try {
            clickElement(removeAllFIlterButton);
            clickElement(showMoreButton);
        } catch (Exception E) {
            clickElement(showMoreButton);
        }
        setText(emailAddressInput, email);
        try {
            clickElement(addFilterButton);
        } catch (Exception E) {
            clickElement(addFilterButton);
        }
        Thread.sleep(500);
        if (verifyElementVisible(listuserElements, 1)) {
            int listUsers = listElements(listuserElements);
            for (int i = 1; i <= listUsers; i++) {
                By userNameFilterButton = By.xpath("//*[@id=\"users\"]/tbody/tr[" + i + "]/td[1]");
                By editUserButton = By.xpath("//*[@id=\"users\"]/tbody/tr[" + i + "]/td[6]/a[3]");
                String nameOnWeb = getTextElement(userNameFilterButton);
                /*String.valueOf(charArray1);*/
                if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(name.toLowerCase())) || removeAccent(name.toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                    clickElement(editUserButton);
                    String userOnWeb = getAttributeElement(getUserName, "value");
                    String password = capitalize(getAttributeElement(getUserName, "value")) + "@123";
                    clickElement(passWordButton);
                    if (userOnWeb.contains("cb6.")||userOnWeb.contains("vtm.")) {
                        for (int j = 4; j <= nameOnWeb.length(); j++) {
                            if (isNumer(userOnWeb.substring(j, j + 1))) {
                            } else {
                                password = capitalize(userOnWeb.substring(0, j)) + capitalize(userOnWeb.substring(j)) + "@123";
                                setText(editPassWordButton, password);
                                System.out.println(email + " " + nameOnWeb + " Registed=>>>>>>> " + userOnWeb);
                                break;
                            }
                        }
                    }
                    else {
                        for (int j = 0; j <= userOnWeb.length(); j++) {
                            if (isNumer(userOnWeb.substring(j, j + 1))) {
                            } else {
                                password = capitalize(userOnWeb.substring(0, j)) + capitalize(userOnWeb.substring(j)) + "@123";
                                setText(editPassWordButton, password);
                                System.out.println(email + " " + nameOnWeb + " Registed=>>>>>>> " + userOnWeb);
                                break;
                            }
                        }
                    }
                    try {
                        clickElement(updateProfileButton);
                        Thread.sleep(500);
                    } catch (Exception E) {
                        try {
                            clickElement(updateProfileButton);
                        }
                        catch (Exception e){
                            clickElement(updateProfileButton);
                        }

                    }
                    email = "registed" + email;
                    break;
                }
            }
        }
        return email.contains("registed");
    }
}
