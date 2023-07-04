package com.wgalvez;

import com.wgalvez.model.Contact;
import com.wgalvez.model.ContactService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactService service = new ContactService();
        System.out.println("Welcome to your personal Agenda");
        boolean status = true;
        String opt;
        while (status){
            System.out.println("Please choose an option: ");
            System.out.println("1) Add Contact");
            System.out.println("2) Find Contact By Name");
            System.out.println("3) Edit Contact");
            System.out.println("4) Find All Contacts");
            System.out.println("5) Delete Contact");
            System.out.println("6) Restart Agenda");
            System.out.println("7) Exit");
            opt = sc.next();
            switch (opt){
                case "1":
                    Contact contact = new Contact();
                    System.out.println("Enter the contact name: ");
                    String name = sc.next();
                    System.out.println("Enter the contact phone number: ");
                    String phone = sc.next();
                    contact.setName(name);
                    contact.setPhone(phone);
                    System.out.println(service.addContact(contact));
                    break;
                case "2":
                    System.out.println("Enter the contact name to find: ");
                    String nameToFind = sc.next();
                    for (Contact c : service.getContactsByName(nameToFind)) {
                        if (c != null){
                            System.out.println("-------------------");
                            System.out.println("Name: " + c.getName());
                            System.out.println("Phone Number: " + c.getPhone());
                        }
                    }
                    break;
                case "3":
                    for (Contact c : service.getAllContacts()) {
                        if (c != null){
                            System.out.println(c);
                        }
                    }
                    try{
                        System.out.println("Enter the contact ID to edit: ");
                        int id = sc.nextInt();
                        Contact contactToEdit = service.getContactById(id);
                        if (contactToEdit != null){
                            System.out.println("Enter the name to edit: ");
                            String nameToEdit = sc.next();
                            System.out.println("Enter the phone number to edit: ");
                            String phoneToEdit = sc.next();
                            contactToEdit.setName(nameToEdit);
                            contactToEdit.setPhone(phoneToEdit);
                            System.out.println(service.editContact(id, contactToEdit));
                        }
                    }catch (Exception e){
                        System.out.println("The ID is invalid");
                    }
                    break;
                case "4":
                    for (Contact c : service.getAllContacts()) {
                        if (c != null){
                            System.out.println("-------------------");
                            System.out.println("Name: " + c.getName());
                            System.out.println("Phone Number: " + c.getPhone());
                        }
                    }
                    System.out.println();
                    break;
                case "5":
                    for (Contact c : service.getAllContacts()) {
                        if (c != null){
                            System.out.println(c);
                        }
                    }
                    try{
                        System.out.println("Enter the contact ID to Delete: ");
                        int idToDelete = sc.nextInt();
                        System.out.println(service.deleteContact(idToDelete));
                    }catch (Exception e){
                        System.out.println("The ID is invalid");
                    }
                    break;
                case "6":
                    System.out.println("Are you sure you want to delete the Agenda? All data will be erased (y/n)");
                    String option = sc.next();
                    if (option.equalsIgnoreCase("y")){
                        System.out.println(service.resetAgenda());
                    }
                    break;
                case "7":
                    status = false;
                    break;
                default:
                    System.out.println("Option is not available");
                    break;
            }
        }
    }
}
