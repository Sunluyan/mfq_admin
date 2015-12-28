package com.mfq.admin.web.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 菜单对象
 * 
 */
public class Menu implements Comparable<Menu> {

    long id;
    long pid;
    String name;
    String url; // 通过url映射acl是否是同一个，需要抽取uri main部分进行判断
    int index; // index在这里是一个比较尴尬的角色，需要从acl copy过来

    Menu parent = null; // 非数据库字段，用于对应parent提取对象使用
    List<Menu> children = new ArrayList<Menu>(1); // 非数据库字段，用于对应parent提取对象使用
    
    public List<Menu> getChildren() {
        return children;
    }

    public void addChildren(Collection<Menu> menus) {
        for (Menu menu : menus) {
            addChild(menu);
        }
    }

    public void sort() {
        Collections.sort(children);
    }

    public void addChild(Menu menu) {
        children.add(menu);
        menu.parent = this;
    }

    public Menu getParent() {
        return parent;
    }

    public void clearChildren() {
        children.clear();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return this.children.size();
    }

    @Override
    public int compareTo(Menu o) {
        int v = index - o.index;
        if (v == 0 && name != null) {
            v = name.compareTo(o.name);
        }
        return v;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}