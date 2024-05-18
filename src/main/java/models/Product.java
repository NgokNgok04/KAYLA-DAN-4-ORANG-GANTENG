package models;

public class Product extends GameObject {
    private boolean origin;
    private int addedWeight;
    private int price;
    private static final Product PRODUCT_SIRIP_HIU = new Product("SIRIP_HIU","card_shark_fin.png",true, 12, 500);
    private static final Product PRODUCT_SUSU = new Product("SUSU","card_milk.png",true,4,100);
    private static final Product PRODUCT_DAGING_DOMBA = new Product("DAGING_DOMBA","card_sheep_meat.png",true,120,6);
    private static final Product PRODUCT_DAGING_KUDA = new Product("DAGING_KUDA","card_horse_meat.png",true,8,150);
    private static final Product PRODUCT_TELUR = new Product("TELUR","card_egg.png",true,2,50);
    private static final Product PRODUCT_DAGING_BERUANG = new Product("DAGING_BERUANG","card_bear_meat.png",true,12,500);
    private static final Product PRODUCT_JAGUNG = new Product("JAGUNG","card_corn.png",false,3,150);
    private static final Product PRODUCT_LABU = new Product("LABU","card_pumpkin.png",false,10,500);
    private static final Product PRODUCT_STROBERI = new Product("STROBERI","card_strawberry.png",false,5,350);


    private Product(String name,String image, boolean origin, int addedWeight, int price){
        this.setTypeObject("PRODUCT");
        this.setName(name);
        this.setActive(true);
        this.setImage(image);
        this.setOrigin(origin);
        this.setAddedWeight(addedWeight);
        this.setPrice(price);
    }

    private Product(Product product){
        this.setTypeObject("PRODUCT");
        this.setName(product.getName());
        this.setActive(true);
        this.setImage(product.getImage());
        this.setOrigin(product.getOrigin());
        this.setAddedWeight(product.getAddedWeight());
        this.setPrice(product.getPrice());
    }

    public Product(String name){
        if (name.equals("SIRIP_HIU")){
            new Product(Product.PRODUCT_SIRIP_HIU);
        } else if (name.equals("SUSU")){
            new Product(Product.PRODUCT_SUSU);
        } else if (name.equals("DAGING_DOMBA")){
            new Product(PRODUCT_DAGING_DOMBA);
        } else if (name.equals("DAGING_KUDA")){
            new Product(Product.PRODUCT_DAGING_KUDA);
        } else if (name.equals("TELUR")){
            new Product(Product.PRODUCT_TELUR);
        } else if (name.equals("DAGING_BERUANG")){
            new Product(Product.PRODUCT_DAGING_BERUANG);
        } else if (name.equals("JAGUNG")){
            new Product(Product.PRODUCT_JAGUNG);
        } else if (name.equals("LABU")){
            new Product(Product.PRODUCT_LABU);
        } else if (name.equals("STROBERI")){
            new Product(Product.PRODUCT_STROBERI);
        }
    }

    public boolean getOrigin(){
        return this.origin;
    }

    public int getAddedWeight(){
        return this.addedWeight;
    }

    public int getPrice(){
        return this.price;
    }

    public void setOrigin(boolean origin){
        this.origin = origin;
    }

    public void setAddedWeight(int addedWeight){
        this.addedWeight = addedWeight;
    }

    public void setPrice(int price){
        this.price = price;
    }
}
