package com.wgalvez.model;

import java.util.Objects;

public class Contact {
    private Integer id;
    private static int lastId;
    private String name;
    private String phone;

    public Contact(){
        this.id = ++lastId;
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.id = ++lastId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "\nID: " + this.id +
                "\nName: " + this.name +
                "\nPhone number: " + this.phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }
}
