package br.com.mateusg.practicalexam.view;

public class ProductOrderIds {

    private Long idProduct, idOrder;

    public ProductOrderIds(Long idProduct, Long idOrder) {
        this.idProduct = idProduct;
        this.idOrder = idOrder;
    }

    public ProductOrderIds() {
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
