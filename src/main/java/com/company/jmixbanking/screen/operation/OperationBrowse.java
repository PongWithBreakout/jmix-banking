package com.company.jmixbanking.screen.operation;

import com.company.jmixbanking.entity.OperationType;
import io.jmix.core.Messages;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.list.CreateAction;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.screen.*;
import com.company.jmixbanking.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.text.DateFormat;
import java.util.Optional;
import java.util.UUID;

@UiController("Operation.browse")
@UiDescriptor("operation-browse.xml")
@LookupComponent("operationsTable")
public class OperationBrowse extends StandardLookup<Operation> {
    @Autowired
    private GroupTable<Operation> operationsTable;
    @Autowired
    private Messages messages;
    @Autowired
    private Notifications notifications;
    @Named("operationsTable.create")
    private CreateAction<Operation> operationsTableCreate;

    @Install(to = "operationsTable.edit", subject = "screenConfigurer")
    private void operationsTableEditScreenConfigurer(Screen screen) {
        ((OperationEdit)screen).disableFieldEditing(false);
    }

    @Install(to = "operationsTable.cancel", subject = "initializer")
    private void operationsTableCancelInitializer(Operation operation) {
        Optional<Operation> selectedOperations = operationsTable.getSelected().stream().findFirst();
        if (selectedOperations.isPresent()) {
            Operation selectedOperation = selectedOperations.get();
            operation.setId(UUID.randomUUID());
            operation.setAccount(selectedOperation.getAccount());
            operation.setAmount(selectedOperation.getAmount());

            operation.setComment(String.format("%s %s", messages.getMessage("cancelComment"),
                    DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(selectedOperation.getDate())));
            operation.setCategory(selectedOperation.getCategory());
            if (selectedOperation.getType() == OperationType.OPERATION_DEPOSIT)
                operation.setType(OperationType.OPERATION_WITHDRAW);
            else if (selectedOperation.getType() == OperationType.OPERATION_WITHDRAW)
                operation.setType(OperationType.OPERATION_DEPOSIT);
        }
    }

    @Install(to = "operationsTable.cancel", subject = "screenConfigurer")
    private void operationsTableCancelScreenConfigurer(Screen screen) {
        ((OperationEdit)screen).disableFieldEditing(true);
    }


}