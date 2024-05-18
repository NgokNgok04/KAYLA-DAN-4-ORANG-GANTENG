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

}
