package com.company.jmixbanking.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.NumberFormat;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Table(name = "OPERATION", indexes = {
        @Index(name = "IDX_OPERATION_ACCOUNT_ID", columnList = "ACCOUNT_ID")
})
@JmixEntity
@Entity
public class Operation {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Digits(integer = 100, fraction = 2)
    @PositiveOrZero(message = "{msg://com.company.jmixbanking.entity/Operation.amount.validation.PositiveOrZero}")
    @NumberFormat(pattern = "#,##0.##", decimalSeparator = ".", groupingSeparator = ",")
    @Column(name = "AMOUNT", nullable = false, precision = 19, scale = 2)
    @NotNull(message = "{msg://com.company.jmixbanking.entity/Operation.amount.validation.NotNull}")
    private BigDecimal amount;

    @Column(name = "TYPE_", nullable = false)
    @NotNull
    private Integer type;

    @Column(name = "DATE_", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date;

    @Column(name = "CATEGORY")
    private Integer category;

    @Column(name = "COMMENT_", length = 2000)
    private String comment;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @OnDelete(DeletePolicy.DENY)
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Account account;

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(OperationType type) {
        this.type = type == null ? null : type.getId();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OperationCategory getCategory() {
        return category == null ? null : OperationCategory.fromId(category);
    }

    public void setCategory(OperationCategory category) {
        this.category = category == null ? null : category.getId();
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OperationType getType() {
        return type == null ? null : OperationType.fromId(type);
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"type", "date"})
    public String getInstanceName() {
        return String.format("%s %s", type, date);
    }
}