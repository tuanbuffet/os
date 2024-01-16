package org.example;

import java.sql.SQLOutput;

public class Main extends WebUI {
    public static void main(String[] args) throws InterruptedException {
        String[][] infomations = {
                {"thuhoai200494@gmail.com","Nguyễn Thùy Dung","","","Milo Milo"},
                {"phuongthaotran2782@gmail.com","Gia Huy","","","Milo Milo"},
                {"khanhchidang2024@gmail.com","Khánh Chi","","","Milo Milo"},
                {"hoaphuongpb2023@gmail.com","Phạm Thị Phương Thảo","","","Milo Milo"},


        };
        for (int i = 0; i < infomations.length; i++) {
            infomations[i][0] = infomations[i][0].replaceAll(" ", "");
        }
        Login login = new Login();
        ManageBooks manageBooks = new ManageBooks();
        ManageAcounts manageAcounts = new ManageAcounts();
        CheckSingUp checkSingUp = new CheckSingUp();
        /*login.ManageLogin(infomations);*/
        login.Login();
        manageAcounts.creatUser(infomations);
        manageBooks.addBook(infomations);
        /*checkSingUp.ManageRegister(infomations);*/
        /*manageBooks.DeleteBooks();*/
    }
}