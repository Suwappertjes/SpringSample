package nl.highway.iungomain.Datamodel;

import java.util.Date;

public abstract class TableResource {
    protected Date created = new Date();
    protected Date modified = created;
    // TODO: Change type to 'Gebruiker' ???
    // TODO: Make modifiedby set itself (currently null).
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

    //TODO: Use an ID instead of Gebruiker to avoid infinite recursion BUT you need to do cleanup then if one gets removed.

    protected String getModifiedBy() {
        return modifiedBy;
    }
}
