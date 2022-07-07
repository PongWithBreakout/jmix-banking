package com.company.jmixbanking.screen.account;

import io.jmix.ui.screen.*;
import com.company.jmixbanking.entity.Account;

@UiController("Account.browse")
@UiDescriptor("account-browse.xml")
@LookupComponent("accountsTable")
public class AccountBrowse extends StandardLookup<Account> {
}