package com.wgalvez.model;

public class ContactService {
    private ContactDAOImpl contactDAO;

    public ContactService() {
        this.contactDAO = new ContactDAOImpl();
    }

    public String addContact(Contact contact){
        return contactDAO.saveContact(contact);
    }

    public Contact[] getAllContacts(){
        return contactDAO.findAllContacts();
    }

    public Contact getContactById(Integer id){
        return contactDAO.findContactById(id);
    }

    public Contact editContact(Integer id, Contact contact){
        return contactDAO.editContact(id, contact);
    }

    public Contact[] getContactsByName(String name){
        return contactDAO.findContactByName(name);
    }

    public String resetAgenda(){
        return contactDAO.resetAgenda();
    }
    public String deleteContact(Integer id){
        return contactDAO.deleteContact(id);
    }
}
