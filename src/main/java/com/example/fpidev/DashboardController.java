package com.example.fpidev;

import com.example.fpidev.entities.Encryptor;
import com.example.fpidev.entities.User;
import com.example.fpidev.interfaces.ServiceUser;

public class DashboardController {
    ServiceUser serviceUser;
    Encryptor encryptor = new Encryptor();
    private int index = -1;
    private static User loggedInUser;
    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }
}
