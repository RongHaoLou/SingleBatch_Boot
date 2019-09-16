package com.indusfo.spc.vo;


import java.util.List;

/**
 * 简易树
 *
 * @author xuz
 * @date 2019/7/15 1:26 PM
 */
public class SimpleTreeNode {
    /**
     * 菜单ID
     */
    private Integer id;
    /**
     * 菜单名称
     */
    private String title;
    /**
     * 菜单编码
     */
    private String code;
    /**
     * 上级菜单ID
     */
    private Integer pId;
    /**
     * 上级菜单编码
     */
    private String pCode;
    /**
     * 是否展开
     */
    private boolean expand =false;
    /**
     * 是否勾选
     * 父级为null，最后一子级设置是否勾选(适应前端VUE)
     */
    private Boolean checked;
    /**
     * 子菜单
     */
    private List<SimpleTreeNode> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<SimpleTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<SimpleTreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SimpleTreeNode{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", pId=" + pId +
                ", pCode='" + pCode + '\'' +
                ", expand=" + expand +
                ", checked=" + checked +
                ", children=" + children +
                '}';
    }
}
