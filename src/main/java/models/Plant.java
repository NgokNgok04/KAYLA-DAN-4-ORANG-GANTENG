package models;

public class Plant extends LivingThing {
    private int ageToHarvest;
    private int age;
    private static final Plant BIJI_JAGUNG = new Plant("BIJI_JAGUNG", "IMAGE", 3, 0,new Product("BIJI_JAGUNG"));
    private static final Plant BIJI_LABU = new Plant("BIJI_LABU","IMAGE",5,0,new Product("BIJI_LABU"));
    private static final Plant BIJI_STROBERI = new Plant("BIJI_STROBERI","IMAGE",4,0,new Product("BIJI_STROBERI"));
    
    private Plant(String name, String image, int ageToHarvest, int age, Product product){
        this.setTypeObject("Plant");
        this.setName(name);
        this.setActive(true);
        this.setImage(image);

        this.setInstantHarvest(false);
        this.setProtection(false);
        this.setTrap(false);
        this.setProduct(product);
        // this.setItems(items);

        this.setAgeToHarvest(ageToHarvest);
        this.setAge(age);
    }
    
    private Plant(Plant plant){
        this.setTypeObject("Plant");
        this.setName(plant.getName());
        this.setActive(true);
        this.setImage(plant.getImage());

        this.setInstantHarvest(false);
        this.setProtection(false);
        this.setTrap(false);
        this.setProduct(plant.getProduct());
        // this.setItems(items);

        this.setAgeToHarvest(plant.getAgeToHarvest());
        this.setAge(plant.getAge());
    }

    public Plant(String name){
        if (name.equals("BIJI_JAGUNG")){
            new Plant(Plant.BIJI_JAGUNG);
        } else if (name.equals("BIJI_LABU")){
            new Plant(Plant.BIJI_LABU);
        } else if (name.equals("BIJI_STROBERI")){
            new Plant(Plant.BIJI_STROBERI);
        }
    }

    public int getAgeToHarvest(){
        return this.ageToHarvest;
    }

    public int getAge(){
        return this.age;
    }

    public void setAgeToHarvest(int ageToHarvest){
        this.ageToHarvest = ageToHarvest;
    }

    public void setAge(int age){
        this.age = age;
    }

    @Override
    public Product harvest(){
        if (this.getAgeToHarvest() < this.getAge()){
            return new Product("DUMMY");
        }
        
        Product resultHarvest = new Product(this.getName());
        this.setActive(false);
        return resultHarvest;
    }

    public void grow(){
        this.setAge(this.getAge() + 1);
    }

}
