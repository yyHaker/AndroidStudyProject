package com.example.a57217.l04_app0809;

/**
 * @Description blackContacts对应的实体类
 * @Author yyHaker
 * @Time ${Date}
 */
public class BlackContact {
     private int id;
     private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BlackContact() {
        super();
    }

    public BlackContact(int id, String number) {
        this.id = id;
        this.number = number;
    }

    @Override
    public String toString() {
        return "BlackContact{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
