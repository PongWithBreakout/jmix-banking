package com.company.jmixbanking.screen.spendings;

import com.company.jmixbanking.app.SpendingService;
import com.company.jmixbanking.entity.Account;
import com.company.jmixbanking.entity.OperationCategory;
import io.jmix.core.Messages;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.DateField;
import io.jmix.ui.component.EntityComboBox;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.KeyValueCollectionContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;

@UiController("SpendingsScreen")
@UiDescriptor("spendings-screen.xml")
public class SpendingsScreen extends Screen {

    @Autowired
    private Table spendingTable;
    @Autowired
    private Notifications notifications;
    @Autowired
    private Messages messages;
    @Autowired
    private SpendingService spendingService;
    @Autowired
    private DateField toDate;
    @Autowired
    private DateField fromDate;
    @Autowired
    private EntityComboBox<Account> accountChooser;
    @Autowired
    private KeyValueCollectionContainer spendingsDc;

    @Subscribe
    public void onInit(InitEvent event) {
        fromDate.setValue(Date.from(Instant.EPOCH));
        toDate.setValue(Date.from(Instant.now()));
    }

    @Install(to = "spendingTable.category", subject = "formatter")
    private String spendingTableCategoryFormatter(Object value) {
        if (value instanceof Integer) {
            OperationCategory type = OperationCategory.fromId((Integer)value);
            if (type != null)
                return messages.getMessage("com.company.jmixbanking.entity/OperationCategory." + type.name());
            else return null;
        }
        else if (value instanceof OperationCategory)
            return messages.getMessage("com.company.jmixbanking.entity/OperationCategory." + ((OperationCategory)value).name());
        else
            return null;
    }

    @Subscribe("countSpendings")
    public void onCountSpendingsClick(Button.ClickEvent event) {
        if (accountChooser.isEmpty() || fromDate.isEmpty() || toDate.isEmpty())
        {
            notifications.create()
                    .withCaption(messages.getMessage("notifyMissingFields")).show();
        }
        else
         spendingsDc.setItems(spendingService.getSpendingsByCategory(accountChooser.getValue(),
                 (Date) fromDate.getValue(), (Date)toDate.getValue()));
    }

}