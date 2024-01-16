package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.apache.commons.lang3.StringUtils.capitalize;
import org.example.EditProfiles;

public class CheckSingUp extends WebUI {

    String URL_REGISTER = "https://english.ican.vn/user/editadvanced.php?id=-1";
    String URL_CheckMail = "https://english.ican.vn/admin/user.php";
    By removeAllFIlterButton = By.xpath("//*[@id=\"id_removeall\"]");
    By showMoreButton = By.xpath("/html/body/div[2]/div[3]/div/div/section[1]/div/form[1]/fieldset/div/div[2]/div/a");
    By emailAddressInput = By.xpath("//*[@id=\"id_email\"]");
    By addFilterButton = By.xpath("//*[@id=\"id_addfilter\"]");
    By listuserElements = By.xpath("//*[@id=\"users\"]/tbody/tr");
    By getUserName = By.xpath("//*[@id=\"id_username\"]");
    By passWordButton = By.xpath("/html/body/div[3]/div[3]/div/div/section/div/form/fieldset[1]/div/div[5]/div[2]/span/a[1]/span/span/em");
    By passwordInput = By.xpath("//*[@id=\"id_newpassword\"]");
    By updateProfileButton = By.xpath("//*[@id=\"id_submitbutton\"]");
    EditProfiles editProfiles = new EditProfiles();

    public boolean CheckMailRgister(String email, String name, String book) throws InterruptedException {
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
        Thread.sleep(1000);
        if (verifyElementVisible(listuserElements, 1)) {
            int listUsers = listElements(listuserElements);
            for (int i = 1; i <= listUsers; i++) {
                By userNameFilterButton = By.xpath("//*[@id=\"users\"]/tbody/tr[" + i + "]/td[1]");
                By editUserButton = By.xpath("//*[@id=\"users\"]/tbody/tr[" + i + "]/td[6]/a[3]");
                String nameOnWeb = getTextElement(userNameFilterButton);
                if (removeAccent(nameOnWeb.toLowerCase()).contains(removeAccent(name.toLowerCase())) || removeAccent(name.toLowerCase()).contains(removeAccent(nameOnWeb.toLowerCase()))) {
                    clickElement(editUserButton);
                    String userOnWeb = getAttributeElement(getUserName, "value");
                    String password = capitalize(userOnWeb) + "@123";
                    if (book.contains("Combo 6 khóa")) {
                        // Trường hợp là cb6.
                        if (userOnWeb.contains("cb6.")) {
                            //kểm tra xem user có chứa số ở ngay sau cb6. không
                            for (int j = 4; j <= userOnWeb.length(); j++) {
                                if (!isNumer(userOnWeb.substring(j, j + 1))) {
                                    password = capitalize(userOnWeb.substring(0, j)) + capitalize(userOnWeb.substring(j)) + "@123";
                                    clickElement(passWordButton);
                                    setText(passwordInput, password);
                                    break;
                                }
                            }
                            System.out.println(email + " " + nameOnWeb + " Registed=>>>>>>> " + userOnWeb);
                        }
                        //Trường hợp user k phải là cb6.
                        else {
                            //tạo lại user mới cho nó
                            String user = "cb6." + Unsigned(convertUser(name).substring(0, 1)) + convertUser(name).substring(1);
                            password =  capitalize(user.substring(0,4)) +capitalize(user.substring(4)) + "@123";
                                    setText(getUserName, Keys.CONTROL + "a");
                                    setText(getUserName, user);
                                    clickElement(passWordButton);
                                    setText(passwordInput, Keys.CONTROL + "a");
                                    setText(passwordInput, password);
                                    try {
                                        clickElement(updateProfileButton);
                                    } catch (Exception E) {
                                        clickElement(updateProfileButton);
                                    }
                                    // nếu update thành công
                                    if (getCurrentUrl().contains(URL_CheckMail)) {
                                        System.out.println(email + " " + name + " combo6khoa=>>>>>>>> " + user);
                                        email = "registed" + email;
                                        break;
                                    }

                                    // nếu update thất bại, thì sẽ sửa lại user và password
                                    else {
                                        editProfiles.UpdateUserPassword(email, name, user, password);
                                    }
                        }
                    }
                    else {
                        System.out.println(email + " " + nameOnWeb + " Registed=>>>>>>> " + userOnWeb);
                    }
                    email = "registed" + email;
                    break;
                }
            }
        }
        return email.contains("registed");
    }
    public void SetUserAndpassWord (){

    }
}
