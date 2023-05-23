package model;

public abstract class Menu {
    private String menuID;
    private String menuName;
    private double price;

    public Menu(String menuID, String menuName, double price) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.price = price;
    }

    public String getMenuID() {
        return menuID;
    }

    public double getPrice() {
        return price;
    }

    public String getMenuName() {
        return menuName;
    }

}
