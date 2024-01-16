package org.example;

import org.openqa.selenium.By;

import static org.example.WebUI.*;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        String[][] infomations = {
                {"Huongmayman1984gmail.com","Lưu Ngọc Hà My","","","Lì xì 26/12 -2/1 Easy IELTS Video_ Intensive"},
        };
        Login login = new Login();
        ManageAccounts manageAccounts = new ManageAccounts();
        ManageCohorst manageCohorst = new ManageCohorst();
        ManageBooks manageBooks = new ManageBooks();
        /*login.ManageLogin(infomations);*/
        login.Login();
        manageAccounts.RUN(infomations);
        manageCohorst.RUN(infomations);
        manageBooks.manageBooks(infomations);
    }
}