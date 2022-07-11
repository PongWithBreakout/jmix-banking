package com.company.jmixbanking.screen.spendings;

import com.company.jmixbanking.app.SpendingService;
import com.company.jmixbanking.entity.Account;
import io.jmix.core.entity.KeyValueEntity;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.DateField;
import io.jmix.ui.component.EntityComboBox;
import io.jmix.ui.component.Table;
import io.jmix.ui.component.data.TableItems;
import io.jmix.ui.component.data.table.ContainerTableItems;
import io.jmix.ui.model.KeyValueCollectionContainer;
import io.jmix.ui.screen.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@UiController("SpendingsScreen")
@UiDescriptor("spendings-screen.xml")
public class SpendingsScreen extends Screen {
    @Autowired
    private Table spendingTable;
    @Autowired
    private SpendingService spendingService;
    @Autowired
    private DateField toDate;
    @Autowired
    private DateField fromDate;
    @Autowired
    private EntityComboBox<Account> entityComboBox;
    @Autowired
    private KeyValueCollectionContainer spendingsDc;

    @Subscribe
    public void onInit(InitEvent event) {
        spendingsDc.setItems(spendingService.getSpendingsByCategory());
    }



}