package br.com.mateusg.practicalexam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "client")
public class Client {
    @Id
    private Long idClient;

    @Size(min = 1, max = 80, message = "Client name must have between 1 and 80 characters")
    @NotNull(message = "Client name is a required field and should not be empty")
    @Column(name = "client_name", nullable = false, length = 80)
    private String clientName;

    @Positive(message = "Client limit should be a positive number")
    @NotNull(message = "Client limit is a required field and should not be empty")
    @Column(name = "client_limit", nullable = false)
    private Double clientLimit;

    @Positive(message = "Invoice due date should be a positive number")
    @NotNull(message = "Invoice due date is a required field and should not be empty")
    @Column(name = "invoice_due_date", nullable = false)
    private Byte invoiceDueDate;

    public Client() {
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Double getClientLimit() {
        return clientLimit;
    }

    public void setClientLimit(Double clientLimit) {
        this.clientLimit = convertToTwoDecimalPlaces(clientLimit);
    }

    private Double convertToTwoDecimalPlaces(Double number){
        return Math.round(number * 100.0) / 100.0;
    }

    public Byte getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public void setInvoiceDueDate(Byte invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }
}
