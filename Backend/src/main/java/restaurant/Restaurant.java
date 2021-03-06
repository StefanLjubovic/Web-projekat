package restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class  Restaurant {
    private String id;
    private String name;
    private String type;
    private boolean opened;
    private Location location;
    private String logo;
    private List<Item> items;
    private Double grade;
    private String managerId;

    public Restaurant(String id, String name, String type, boolean status, Location location,String managerId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.opened = status;
        this.location = location;
        this.managerId = managerId;
    }

    public String getManagerId() {
        return managerId;
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

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
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

    public Restaurant availableItems() {
        List<Item> items = new ArrayList<>();
        for(Item item: this.items){
            if(!item.deleted)
                items.add(item);
        }
        this.items = items;
        return this;
    }
}
