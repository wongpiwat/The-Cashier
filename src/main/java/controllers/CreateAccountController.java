package controllers;

import databases.AccountsDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Accounts;

import java.io.IOException;

public class CreateAccountController {
    Accounts accounts;
    @FXML TextField department,firstName,lastName,userName,password;

    public void setEditAccounts(Accounts accounts){
        this.accounts = accounts;
        department.setText(this.accounts.getDepartment());
        firstName.setText(this.accounts.getFirstname());
        lastName.setText(this.accounts.getLastname());
        userName.setText(this.accounts.getUsername());
        password.setText(this.accounts.getPassword());
    }
    public void editAccount(ActionEvent event){
        if (accounts==null) {
            if (!department.getText().isEmpty() && !firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !userName.getText().isEmpty() && !password.getText().isEmpty()) {
                AccountsDB.saveAccountsDB(AccountsDB.getCreateAccountsID(), this.department.getText(), this.firstName.getText(), this.lastName.getText(), this.userName.getText(), this.password.getText());
                this.department.setText("");
                this.firstName.setText("");
                this.lastName.setText("");
                this.userName.setText("");
                this.password.setText("");
                this.backToAccounts(event);
            }
        } else {
            this.accounts.setDepartment(department.getText());
            this.accounts.setFirstname(firstName.getText());
            this.accounts.setLastname(lastName.getText());
            this.accounts.setUsername(userName.getText());
            this.accounts.setPassword(password.getText());
            AccountsController.accountsDB.editAccountsDB(this.accounts.getId(), this.accounts.getDepartment(), this.accounts.getFirstname(), this.accounts.getLastname(), this.accounts.getUsername(), this.accounts.getPassword());
            this.backToAccounts(event);
        }
    }

    public void cancelToAccounts(ActionEvent event){
        this.backToAccounts(event);
    }

    public void backToAccounts(ActionEvent event){
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/accounts.fxml"));
        try{
            stage.setScene(new Scene(loader.load()));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
