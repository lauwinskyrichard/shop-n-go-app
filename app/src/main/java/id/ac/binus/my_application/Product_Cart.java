package id.ac.binus.my_application;

public class Product_Cart {

        private int cart_id;
        private int product_id;
        private  String product_name;
        private int product_price;
        private  double product_rating;
        private  String product_description;
        private double product_weight;
        private String product_brand;
        private String product_type;
        private int product_image;
        private int product_qty;

        public Product_Cart(int cart_id, int product_id, String product_name, int product_price, double product_rating, String product_description, double product_weight, String product_brand, String product_type, int product_image, int product_qty) {
            this.cart_id = cart_id;
            this.product_id = product_id;
            this.product_name = product_name;
            this.product_price = product_price;
            this.product_rating = product_rating;
            this.product_description = product_description;
            this.product_weight = product_weight;
            this.product_brand = product_brand;
            this.product_type = product_type;
            this.product_image = product_image;
            this.product_qty = product_qty;
        }

        public int getCart_id() {
            return cart_id;
        }

        public void setCart_id(int cart_id) {
            this.cart_id = cart_id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public int getProduct_price() {
            return product_price;
        }

        public void setProduct_price(int product_price) {
            this.product_price = product_price;
        }

        public double getProduct_rating() {
            return product_rating;
        }

        public void setProduct_rating(double product_rating) {
            this.product_rating = product_rating;
        }

        public String getProduct_description() {
            return product_description;
        }

        public void setProduct_description(String product_description) {
            this.product_description = product_description;
        }

        public int getProduct_qty() {
            return product_qty;
        }

        public void setProduct_qty(int product_qty) {
            this.product_qty = product_qty;
        }

        public double getProduct_weight() {
            return product_weight;
        }

        public void setProduct_weight(double product_weight) {
            this.product_weight = product_weight;
        }

        public String getProduct_brand() {
            return product_brand;
        }

        public void setProduct_brand(String product_brand) {
            this.product_brand = product_brand;
        }

        public String getProduct_type() {
            return product_type;
        }

        public void setProduct_type(String product_type) {
            this.product_type = product_type;
        }

        public int getProduct_image() {
            return product_image;
        }

        public void setProduct_image(int product_image) {
            this.product_image = product_image;
        }
}
