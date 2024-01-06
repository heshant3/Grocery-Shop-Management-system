package com.oop.cw.model;
;

public class User {
    private String userName;
    private String password;

    private WestminsterShoppingManager shoppingManager;
    public WestminsterShoppingManager getShoppingManager() {
        return shoppingManager;
    }

    public void setShoppingManager() {
       this.shoppingManager = WestminsterShoppingManager.getInstance();
    }



    private static User user;

    private User(){
        setUserName("");
        setPassword("");
    }

    public static User getInstance(){

        if(user == null){
            synchronized (WestminsterShoppingManager.class){
                if (user==null){
                    user=new User();
                }
            }
        }
        return  user;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
