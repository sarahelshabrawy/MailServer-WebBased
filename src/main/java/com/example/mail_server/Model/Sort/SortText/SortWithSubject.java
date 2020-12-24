package com.example.mail_server.Model.Sort.SortText;

import com.example.mail_server.Model.indexMail;

public class SortWithSubject extends SortText {

    @Override
    String getStringAttribute(indexMail mail) {
        return mail.getSubject();
    }
}
