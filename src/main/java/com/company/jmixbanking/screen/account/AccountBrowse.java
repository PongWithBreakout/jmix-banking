package com.company.jmixbanking.screen.account;

import io.jmix.core.common.util.ParamsMap;
import io.jmix.ui.Dialogs;
import io.jmix.ui.component.Button;
import io.jmix.ui.screen.*;
import com.company.jmixbanking.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Account.browse")
@UiDescriptor("account-browse.xml")
@LookupComponent("accountsTable")
public class AccountBrowse extends StandardLookup<Account> {
    @Install(to = "accountsTable.create", subject = "screenConfigurer")
    private void accountsTableCreateScreenConfigurer(Screen screen) {
        ((AccountEdit)screen).setAccountCreation(true);
    }

}