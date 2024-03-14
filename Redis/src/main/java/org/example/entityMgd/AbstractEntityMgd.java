package org.example.entityMgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

public abstract class AbstractEntityMgd {

    @BsonProperty("_id")
    private int id;

    @BsonCreator
    public AbstractEntityMgd(@BsonProperty("_id") int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
