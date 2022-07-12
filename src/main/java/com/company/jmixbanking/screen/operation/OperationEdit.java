package com.company.jmixbanking.screen.operation;

import com.company.jmixbanking.entity.Account;
import com.company.jmixbanking.entity.OperationCategory;
import com.company.jmixbanking.entity.OperationType;
import io.jmix.ui.component.ComboBox;
import io.jmix.ui.component.DateField;
import io.jmix.ui.component.EntityComboBox;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import com.company.jmixbanking.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@UiController("Operation.edit")
@UiDescriptor("operation-edit.xml")
@EditedEntityContainer("operationDc")
public class OperationEdit extends StandardEditor<Operation> {
    @Autowired
    private OperationService operationService;
    @Autowired
    private EntityComboBox<Account> accountField;
    @Autowired
    private TextField<BigDecimal> amountField;
    @Autowired
    private ComboBox<OperationType> typeField;
    @Autowired
    private DateField<Date> dateField;
    @Autowired
    private ComboBox<OperationCategory> categoryField;

 /*   @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Account changingAccount = this.getEditedEntity().getAccount();
        BigDecimal newFunds = operationService.getAccountFunds(changingAccount);
        changingAccount.setFunds(newFunds);
    }
*/
    @Subscribe
    public void onAfterCommitChanges(AfterCommitChangesEvent event) {
        Account changingAccount = this.getEditedEntity().getAccount();
        BigDecimal newFunds = operationService.getAccountFunds(changingAccount);
        changingAccount.setFunds(newFunds);
    }

    @Subscribe
    public void onInitEntity(InitEntityEvent<Operation> event) {
        Date thisInstant = Date.from(Instant.now());
        dateField.setRangeEnd(thisInstant);
        event.getEntity().setDate(thisInstant);
    }

    public void disableFieldEditing(boolean categoryDisable)
    {
        accountField.setEditable(false);
        amountField.setEditable(false);
        typeField.setEditable(false);
        if (categoryDisable)
            categoryField.setEditable(false);
    }


}