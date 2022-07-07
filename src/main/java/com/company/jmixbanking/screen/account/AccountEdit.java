package com.company.jmixbanking.screen.account;

import io.jmix.ui.screen.*;
import com.company.jmixbanking.entity.Account;

@UiController("Account.edit")
@UiDescriptor("account-edit.xml")
@EditedEntityContainer("accountDc")
public class AccountEdit extends StandardEditor<Account> {

}