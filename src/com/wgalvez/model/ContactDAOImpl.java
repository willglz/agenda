package com.wgalvez.model;

import java.util.*;

public class ContactDAOImpl implements ContactDAO {
    private static Contact[] contacts;
    private static final int CAPACITY = 10;

    static {
        contacts = new Contact[CAPACITY];
    }
    @Override
    public String saveContact(Contact contact) {
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] == null){
                contacts[i] = contact;
                System.out.println("Contact saved.");
                return contact.toString();
            }
        }
        return "Sorry, your agenda is currently full.";
    }

    @Override
    public Contact editContact(Integer id, Contact contact) {
        Contact contactToEdit = findContactById(id);
        if (contactToEdit != null){
            contactToEdit.setName(contact.getName() != null ? contact.getName() : contactToEdit.getName());
            contactToEdit.setPhone(contact.getPhone() != null ? contact.getPhone() : contactToEdit.getPhone());
        }
        return contactToEdit;
    }

    @Override
    public String deleteContact(Integer id) {
        Contact contactToDelete = findContactById(id);
        if (contactToDelete != null){
            for (int i = 0; i < contacts.length; i++) {
                if (contacts[i] != null && Objects.equals(contacts[i].getId(), id)){
                    contacts[i] = null;
                    return "Contact with id " + id + " has been successfully deleted";
                }
            }
        }
        return "Contact with id " + id + " not found";
    }

    @Override
    public String resetAgenda() {
        contacts = new Contact[CAPACITY];
        return "Agenda has been successfully reset";
    }

    @Override
    public Contact[] findContactByName(String name) {
        Contact[] contactByName = new Contact[CAPACITY];
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] != null && contacts[i].getName().equalsIgnoreCase(name)){
                contactByName[i] = contacts[i];
            }
        }
        return contactByName;
    }

    @Override
    public Contact[] findAllContacts() {
        bubbleSort();
        return contacts;
    }

    @Override
    public Contact findContactById(Integer id) {
        Contact contact;
        for (Contact c : contacts) {
            if (c != null && Objects.equals(c.getId(), id)){
                contact = c;
                return contact;
            }
        }
        return null;
    }
    private static void bubbleSort() {
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] != null){
                for (int j = 0; j < contacts.length; j++){
                    if (contacts[j] != null){
                        if (contacts[i].getName().compareTo(contacts[j].getName()) < 0){
                            Contact auxContact = contacts[j];
                            contacts[j] = contacts[i];
                            contacts[i] = auxContact;
                        }
                    }
                }
            }
        }
    }

    public boolean verifyName(String name){
        for (Contact c : contacts) {
            if (c != null && c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
