package com.example.mail_server.Model.Sort.SortText;

import com.example.mail_server.Model.indexMail;

public class SortWithBody extends SortText {

    @Override
    String getStringAttribute(indexMail mail) {
        return mail.getBody();
    }
}
