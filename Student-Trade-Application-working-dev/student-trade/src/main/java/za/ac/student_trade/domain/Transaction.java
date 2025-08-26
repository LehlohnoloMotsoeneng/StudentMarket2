package za.ac.student_trade.domain;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String transactionId;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "image_of_product")
    private String imageOfProduct;

    @Column(name = "product_label")
    private String productLabel;

    @Column(name= "description")
    private String productDescription;

    @Column(name = "product_condition")
    private String productCondition;

    @Column(name = "price")
    private double price;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Student buyer;

    public Transaction() {
    }

    protected Transaction (Builder builder) {
        this.transactionId = builder.transactionId;
        this.imageOfProduct = builder.imageOfProduct;
        this.productLabel = builder.productLabel;
        this.productDescription = builder.productDescription;
        this.productCondition = builder.productCondition;
        this.transactionDate = builder.transactionDate;
        this.price = builder.price;
        this.product = builder.product;
        this.buyer = builder.buyer;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getImageOfProduct() {
        return imageOfProduct;
    }

    public String getProductLabel() {
        return productLabel;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public double getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public Student getBuyer() {
        return buyer;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", price=" + price +
                ", product=" + product +
                ", buyer=" + buyer +
                '}';
    }

    public static class Builder {
        private String transactionId;
        private String imageOfProduct;
        private String productLabel;
        private String productDescription;
        private String productCondition;
        private LocalDateTime transactionDate;
        private double price;
        private Product product;
        private Student buyer;

        public Builder setTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder setImageOfProduct(String imageOfProduct) {
            this.imageOfProduct = imageOfProduct;
            return this;
        }

        public Builder setProductLabel(String productLabel) {
            this.productLabel = productLabel;
            return this;
        }

        public Builder setProductDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public Builder setProductCondition(String productCondition) {
            this.productCondition = productCondition;
            return this;
        }

        public Builder setTransactionDate(LocalDateTime transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder setBuyer(Student buyer) {
            this.buyer = buyer;
            return this;
        }



        public Builder copy(Transaction transaction) {
            this.transactionId = transaction.transactionId;
            this.imageOfProduct = transaction.imageOfProduct;
            this.productLabel = transaction.productLabel;
            this.productDescription = transaction.productDescription;
            this.productCondition = transaction.productCondition;
            this.transactionDate = transaction.transactionDate;
            this.price = transaction.price;
            this.product = transaction.product;
            this.buyer = transaction.buyer;
            return this;
        }

        public Builder builder(Transaction transaction) {
            this.transactionId = transaction.getTransactionId();
            this.imageOfProduct = transaction.getImageOfProduct();
            this.productLabel = transaction.getProductLabel();
            this.productDescription = transaction.getProductDescription();
            this.productCondition = transaction.getProductCondition();
            this.transactionDate = transaction.getTransactionDate();
            this.price = transaction.getPrice();
            this.product = transaction.getProduct();
            this.buyer = transaction.getBuyer();
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
