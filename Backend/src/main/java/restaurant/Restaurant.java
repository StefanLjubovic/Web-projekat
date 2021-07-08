package restaurant;

import java.util.List;
import java.util.UUID;

public class  Restaurant {
    private String id;
    private String name;
    private String type;
    private boolean status;
    private Location location;
    private String logo;
    private List<Item> items;
    private Double grade;

    public Restaurant(String id, String name, String type, boolean status, Location location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isStatus() {
        return status;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double score) {
        this.grade = score;
    }

    public Restaurant addItem(Item item) {
        if(item.id == null){
            item.id = UUID.randomUUID().toString();
            this.items.add(item);
        }else{
            for(int i=0;i < this.items.size(); i++){
                if(this.items.get(i).id.equals(item.id)){
                    this.items.set(i, item);
                    return this;
                }
            }
        }
        return this;
    }
}
