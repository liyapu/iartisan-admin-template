package org.iartisan.admin.template.authentication.support.service.entity;

import java.util.List;

/**
 * @author King
 * @since 2018/3/1
 */
public class ResourceEntity {

    private String title;

    private String value;

    private boolean checked = true;

    private List<ResourceEntity> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ResourceEntity> getData() {
        return data;
    }

    public void setData(List<ResourceEntity> data) {
        this.data = data;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
