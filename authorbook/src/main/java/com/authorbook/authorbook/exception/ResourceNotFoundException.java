package com.authorbook.authorbook.exception;

public class ResourceNotFoundException  extends  RuntimeException
{
    public ResourceNotFoundException(String resourceName, String fieldname, long fieldValue)
    {
        // Post not found with id:1
        super(String.format("%s not found with %s:'%s' ",resourceName,fieldname, fieldValue));
        this.resourceName = resourceName;
        this.fieldname = fieldname;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldname() {
        return fieldname;
    }

    public long getFieldValue() {
        return fieldValue;
    }

    private String resourceName;
    private String fieldname;
    private long fieldValue;
}
