package in.hikev.commons.hibernate.model;

/**
 * Created by Administrator on 2015/6/25.
 */
public abstract class Entity {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
}
