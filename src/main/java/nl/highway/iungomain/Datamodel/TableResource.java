package nl.highway.iungomain.Datamodel;

import java.util.Date;

public abstract class TableResource {
    protected Date created = new Date();
    protected Date modified = created;
    protected String createdBy;
    protected String modifiedBy;

    public void updateModified() {
        this.modified = new Date();
    }

    protected Date getCreated() {
        return created;
    }

    protected String getCreatedBy() {
        return createdBy;
    }

    protected Date getModified() {
        return modified;
    }

    protected String getModifiedBy() {
        return modifiedBy;
    }
}
