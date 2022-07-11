package com.company.jmixbanking.screen.account;

import io.jmix.ui.screen.*;
import com.company.jmixbanking.entity.Account;

import java.math.BigDecimal;

@UiController("Account.edit")
@UiDescriptor("account-edit.xml")
@EditedEntityContainer("accountDc")
public class AccountEdit extends StandardEditor<Account> {
    @Subscribe
    public void onInitEntity(InitEntityEvent<Account> event) {

    }

}