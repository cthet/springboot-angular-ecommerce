package com.ecommerce.port.drivers;

import com.ecommerce.model.message.MessageResponse;
import com.ecommerce.model.user.Email;
import com.ecommerce.model.user.Info;
import com.ecommerce.model.user.Profile;
import com.ecommerce.model.user.User;

public interface UserDriverPort {

    User getUser();

    MessageResponse updateUserInfo(Info info);

    MessageResponse updateUserEmail(Email email);

    Profile getUserProfile();

}
