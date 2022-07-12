package com.company.jmixbanking.screen.account;

import io.jmix.ui.screen.*;
import com.company.jmixbanking.entity.Account;

import java.math.BigDecimal;

@UiController("Account.edit")
@UiDescriptor("account-edit.xml")
@EditedEntityContainer("accountDc")
public class AccountEdit extends StandardEditor<Account> {
    private boolean accountCreation;
    {
        accountCreation = false;
    }
    public void setAccountCreation(boolean value) {
        accountCreation = value;
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        if (accountCreation)
            this.getEditedEntity().setFunds(BigDecimal.ZERO);
    }

}