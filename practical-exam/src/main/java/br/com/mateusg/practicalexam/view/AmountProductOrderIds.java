package br.com.mateusg.practicalexam.view;

public class AmountProductOrderIds {

    private Long idProduct, idOrder;
    private int amount;

    public AmountProductOrderIds(Long idProduct, Long idOrder, int amount) {
        this.idProduct = idProduct;
        this.idOrder = idOrder;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public AmountProductOrderIds() {
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }
}
