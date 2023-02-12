package br.com.mateusg.practicalexam.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.PersistenceConstructor;

@Entity
@Table(name = "client")
public class Client {
    @Id
    private Long idClient;

    @Column(name = "client_name", nullable = false, length = 80)
    private String clientName;

    @Column(name = "client_limit", nullable = false)
    private Double clientLimit;

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
        this.clientLimit = clientLimit;
    }

    public Byte getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public void setInvoiceDueDate(Byte invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }
}
