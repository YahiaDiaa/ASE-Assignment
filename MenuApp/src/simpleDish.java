public class simpleDish extends MainDishFactory{

    @Override
    public MenuItem createDish(String type) {
        if(type == null) return null;
       switch (type.toLowerCase()) {
           case "beef":
               return new BeefSteak();
           case "chicken":
               return new FriedChicken();
           default:
               return null;
       }
    }
}
