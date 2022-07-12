package com.company.jmixbanking.screen.account;

import com.company.jmixbanking.entity.Account;
import io.jmix.ui.screen.*;

@UiController("Account.browse")
@UiDescriptor("account-browse.xml")
@LookupComponent("accountsTable")
public class AccountBrowse extends StandardLookup<Account> {
    @Install(to = "accountsTable.create", subject = "screenConfigurer")
    private void accountsTableCreateScreenConfigurer(Screen screen) {
        ((AccountEdit)screen).setAccountCreation(true);
    }

}