package com.ecommerce.model.user;


public class Profile {

        private Info info;

        private Email email;

        public Profile(Info info, Email email) {
                this.info = info;
                this.email = email;
        }
}
