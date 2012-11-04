package de.spring.webservices.service;

public class TestService {
    private String notification = "HOLA GUS";
    
    public void setNotification(final String notification) {
        this.notification = notification;
    }
    
    public String getNotification() {
        return this.notification;
    }
}
