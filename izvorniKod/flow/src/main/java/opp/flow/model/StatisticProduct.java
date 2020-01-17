package opp.flow.model;



public class StatisticProduct {
    public static final String NL = System.getProperty("line.separator");
    private Long id;
    private String date;
    private String body;

    public StatisticProduct(Long id, ConsumedProduct consumedProduct,Product product) {
        this.id=id;
        this.date= consumedProduct.getDate().toString();
        this.body=initReviewPostBody(consumedProduct,product);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getBody() {
        return body;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String initReviewPostBody(ConsumedProduct consumedProduct, Product product) {

            return "Product name-->  "+ consumedProduct.getProductName() +"  |energy|= " + product.getEnergy()+
                "   |fat|= " + product.getFat()+
                    "   |saturatedFattyAcids|= " +product.getSaturatedFattyAcids()+
                    "   |carbohydrates|=  " + product.getCarbohydrates()+
                    "   |sugars|= " +product.getSugars()+
                    "   |protein|= " +product.getProtein()+
                    "   |salt|= "+product.getSalt();

    }

}
