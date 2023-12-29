package com.ecommerce.port.drivers;

import com.ecommerce.model.message.MessageResponse;
import com.ecommerce.model.user.Email;
import com.ecommerce.model.user.Info;
import com.ecommerce.model.user.Profile;

public interface UserDriverPort {

    MessageResponse updateUserInfo(Info info);

    MessageResponse updateUserEmail(Email email);

    Profile getUserProfile();

}
