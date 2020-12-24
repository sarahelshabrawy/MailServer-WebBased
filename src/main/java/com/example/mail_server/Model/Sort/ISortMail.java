package com.example.mail_server.Model.Sort;

import com.example.mail_server.Model.indexMail;

import java.util.LinkedList;
import java.util.List;

public interface ISortMail {
    List<indexMail> Sort(LinkedList<indexMail> mails);
}
