package com.wgalvez;

import com.wgalvez.model.Contact;
import com.wgalvez.model.ContactService;
import com.wgalvez.utils.StringValidator;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        ContactService service = new ContactService();
        System.out.println("---------------------------------");
        System.out.println("Welcome to your personal Agenda");
        System.out.println("---------------------------------");
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
            opt = sc.nextLine();
            switch (opt){
                case "1":
                    Contact contact = new Contact();
                    boolean stat = true;
                    boolean check = true;
                    while (stat){
                        String name = "", phone = "";
                        while (check){
                            System.out.println("Enter the contact name: ");
                            name = sc.nextLine();
                            System.out.println("Enter the contact phone number: ");
                            phone = sc.nextLine();
                            if (!(StringValidator.isEmpty(name) || StringValidator.isEmpty(phone))){
                                check = false;
                            }
                            System.out.println("Name or Phone cannot be null");
                        }
                        if (service.checkName(name)){
                            System.out.println("'" + name + "' already exist in your agenda, do you want to continue? (y/n)");
                            String choice = sc.nextLine();
                            if (choice.equalsIgnoreCase("y")){
                                contact.setName(name);
                                contact.setPhone(phone);
                                System.out.println(service.addContact(contact));
                                stat = false;
                            }
                        }else {
                            contact.setName(name);
                            contact.setPhone(phone);
                            System.out.println(service.addContact(contact));
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println();
                            stat = false;
                        }
                    }
                    break;
                case "2":
                    System.out.println("Enter the contact name to find: ");
                    String nameToFind = sc.nextLine();
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
                        String id = sc.nextLine();
                        Contact contactToEdit = service.getContactById(Integer.parseInt(id));
                        if (contactToEdit != null){
                            boolean statToEdit = true;
                            boolean checkForEdit = true;
                            while (statToEdit){
                                String nameToEdit = "", phoneToEdit = "";
                                while (checkForEdit){
                                    System.out.println("Enter the contact name to edit: ");
                                    nameToEdit = sc.nextLine();
                                    System.out.println("Enter the contact phone number to edit: ");
                                    phoneToEdit = sc.nextLine();
                                    if (!(StringValidator.isEmpty(nameToEdit) || StringValidator.isEmpty(phoneToEdit))){
                                        checkForEdit = false;
                                    }
                                    System.out.println("Name or Phone cannot be null");
                                }
                                if (service.checkName(nameToEdit)){
                                    System.out.println("'" + nameToEdit + "' already exist in your agenda, do you want to continue? (y/n)");
                                    String choice = sc.nextLine();
                                    if (choice.equalsIgnoreCase("y")){
                                        contactToEdit.setName(nameToEdit);
                                        contactToEdit.setPhone(phoneToEdit);
                                        System.out.println(service.editContact(Integer.parseInt(id), contactToEdit));
                                        TimeUnit.SECONDS.sleep(1);
                                        System.out.println();
                                        statToEdit = false;
                                    }
                                }else {
                                    contactToEdit.setName(nameToEdit);
                                    contactToEdit.setPhone(phoneToEdit);
                                    System.out.println(service.editContact(Integer.parseInt(id), contactToEdit));
                                    TimeUnit.SECONDS.sleep(1);
                                    System.out.println();
                                    statToEdit = false;
                                }
                            }
                        }
                    }catch (Exception e){
                        System.out.println("The ID is invalid");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println();
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
                    TimeUnit.SECONDS.sleep(2);
                    break;
                case "5":
                    for (Contact c : service.getAllContacts()) {
                        if (c != null){
                            System.out.println(c);
                        }
                    }
                    try{
                        System.out.println("Enter the contact ID to Delete: ");
                        String idToDelete = sc.nextLine();
                        System.out.println(service.deleteContact(Integer.parseInt(idToDelete)));
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println();
                    }catch (Exception e){
                        System.out.println("The ID is invalid");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println();
                    }
                    break;
                case "6":
                    System.out.println("Are you sure you want to delete the Agenda? All data will be erased (y/n)");
                    String option = sc.nextLine();
                    if (option.equalsIgnoreCase("y")){
                        System.out.println(service.resetAgenda());
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println();
                    }else {System.out.println("Operation aborted.");}
                    break;
                case "7":
                    status = false;
                    break;
                default:
                    System.out.println("Option is not available");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println();
                    break;
            }
        }
    }
}
