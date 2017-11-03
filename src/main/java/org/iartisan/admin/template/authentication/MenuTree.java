package org.iartisan.admin.template.authentication;

import java.util.List;

/**
 * <p>
 * 菜单列表
 *
 * @author King
 * @since 2017/11/3
 */
public class MenuTree {

    private String title;

    private String icon;

    private String href;

    private String spread;

    private List<MenuTree> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }
}
