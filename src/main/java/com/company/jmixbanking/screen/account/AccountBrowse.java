package com.company.jmixbanking.screen.account;

import io.jmix.ui.Dialogs;
import io.jmix.ui.component.Button;
import io.jmix.ui.screen.*;
import com.company.jmixbanking.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Account.browse")
@UiDescriptor("account-browse.xml")
@LookupComponent("accountsTable")
public class AccountBrowse extends StandardLookup<Account> {
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private MessageBundle messageBundle;


}