package models;

public abstract class Animal extends LivingThing{
    private int weightToHarvest;
    private int weight;
    private String jenisHewan;

    public int getWeightToHarvest(){
        return this.weightToHarvest;
    }

    public int getWeight(){
        return this.weight;
    }

    public String getJenisHewan(){
        return this.jenisHewan;
    }

    public void setWeightToHarvest(int weightToHarvest){
        this.weightToHarvest = weightToHarvest;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public void setJenisHewan(String jenisHewan){
        this.jenisHewan = jenisHewan;
    }

    @Override
    public Product harvest(){
        if (this.getWeightToHarvest() < this.getWeight()){
            return new Product("DUMMY");
        }

        Product resultHarvest = new Product(this.getName());
        this.setActive(false);
        return resultHarvest;
    }

    @Override
    public void eat(Product product){
        if (this.getJenisHewan().equals("Carnivore") && product.getOrigin()){
            this.setWeight(this.getWeight() + 1);
        } else if (this.getJenisHewan().equals("Herbivore") && !product.getOrigin()){
            this.setWeight(this.getWeight() + 1);
        } else if (this.getJenisHewan().equals("Omnivore")){
            this.setWeight(this.getWeight() + 1);
        }
    }

}
