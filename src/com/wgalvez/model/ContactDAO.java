package com.wgalvez.model;

public interface ContactDAO {
    String saveContact(Contact contact);
    Contact editContact(Integer id, Contact contact);
    String deleteContact(Integer id);
    String resetAgenda();
    Contact[] findContactByName(String name);
    Contact[] findAllContacts();
    Contact findContactById(Integer id);
}
