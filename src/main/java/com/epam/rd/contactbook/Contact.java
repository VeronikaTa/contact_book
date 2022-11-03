package com.epam.training.student_veronika_tarasova.contact_book.src.main.java.com.epam.rd.contactbook;

public class Contact {
    private String name;

    private NameContactInfo nameContactInfo;
    private Email[] email;
    private Social[] socialMedia;

    private ContactInfo phoneNumber;

    public Contact(String contactName) {
       if(contactName == null){
           throw new IllegalArgumentException();
       }
       name = contactName;
       email = new Email[3];
       socialMedia = new Social[5];
       nameContactInfo = new NameContactInfo();
    }

    private class NameContactInfo implements ContactInfo{

        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getValue() {
            return name;
        }

    }

    public static class Email implements ContactInfo{
        private String email;

        Email(String localPart, String domain){
            email = localPart+"@"+domain;
        }
        @Override
        public String getTitle() {
            return "Email";
        }

        @Override
        public String getValue() {
            return email;
        }
    }

    public static class Social implements ContactInfo{
        private String title;
        private String social;

        Social(String title, String social){
            this.title = title;
            this.social = social;
        }
        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getValue() {
            return social;

        }

    }
    public void rename(String newName) {
        if(newName != null && !newName.isBlank()){
            name = newName;
        }
    }

    public Email addEmail(String localPart, String domain) {
        if(localPart == null || domain == null || email[2] != null){
            return null;
        }

        int index= 0;
        for(int i = 0; i < 3; i ++){
            if(email[i] == null){
                index = i;
                email[i] = new Email(localPart, domain);
                break;
            }
        }
        return email[index];
    }


    public Email addEpamEmail(String firstname, String lastname) {
        if(firstname== null || lastname == null || email[2] != null){
            return null;
        }
        int index= 0;
        for(int i = 0; i < 3; i ++){
            if(email[i] == null){
                index = i;
                break;
            }
        }
        email[index] = new Email(firstname, lastname) {
            String email;
            {
                email = firstname+"_"+lastname+"@epam.com";
            }
            public String getTitle(){
                return "Epam Email";
            }

            public String getValue(){
                return email;
            }
        };
        return email[index];
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if(phoneNumber != null || code == 0 || number == null){
            return null;
        }
        phoneNumber = new ContactInfo(){

            @Override
            public String getTitle() {
                return "Tel";
            }

            @Override
            public String getValue() {
                return "+"+code+" "+number;
            }
        };
        return phoneNumber;
    }

    public Social addTwitter(String twitterId) {
        if(twitterId == null || socialMedia[4] != null){
            return null;
        }
        int index = 0;
        for(int i = 0; i< 5; i++){
            if(socialMedia[i] == null){
                index = i;
                break;
            }
        }
        socialMedia[index] = new Social("Twitter",twitterId);
        return socialMedia[index];
    }

    public Social addInstagram(String instagramId) {
        if(instagramId == null || socialMedia[4] != null){
            return null;
        }
        int index = 0;
        for(int i = 0; i< 5; i++){
            if(socialMedia[i] == null){
                index = i;
                break;
            }
        }
        socialMedia[index] = new Social("Instagram",instagramId);
        return socialMedia[index];
    }

    public Social addSocialMedia(String title, String id) {
        if(id == null || title == null || socialMedia[4] != null){
            return null;
        }
        int index = 0;
        for(int i = 0; i< 5; i++){
            if(socialMedia[i] == null){
                index = i;
                break;
            }
        }
        socialMedia[index] = new Social(title,id);
        return socialMedia[index];
    }

    public ContactInfo[] getInfo() {
        int length = 0;
        for(int i = 0; i < 3; i++){
            if(email[i] != null){
                length++;
            }
        }
        for(int i  = 0; i < 5; i++){
            if(socialMedia[i] != null){
                length++;
            }
        }
        length += phoneNumber != null ? 2 : 1;

        ContactInfo[] information = new ContactInfo[length];
        information[0] = nameContactInfo;
        if(phoneNumber != null){
            information[1] = phoneNumber;
        }
        for(int i = 0; i < email.length; i++){
            for(int j = 1; j < length; j++){
                if(information[j] == null && email[i] != null){
                    information[j] = email[i];
                    break;
                }
            }
        }

        for(int i = 0; i < socialMedia.length; i++){
            for(int j = 1; j < length; j++){
                if(information[j] == null && socialMedia[i] != null){
                    information[j] = socialMedia[i];
                    break;
                }
            }
        }
        return information;
    }

}
