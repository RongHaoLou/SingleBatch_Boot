package com.indusfo.spc.vo;

import java.util.List;


/**
 * @Package: com.indusfo.spc.vo
 * @ClassName: XTreeNode
 * @Author: 熊冰
 * @Date: 2019/8/10 14:44
 * @Version: 1.0
 */
public class XTreeNode {

    private static final long serialVersionUID = 5L;
    /**
     * 当前编号
     */
    private Object id;
    /**
     * 父级编号
     */
    private Object pId;
    /**
     * 排序
     */
    private String sort;
    /**
     * 关键字
     */
    private String key;
    /**
     * 当前数据名称
     *
     */
    private String label;
    /**
     * 编号
     */
    private String  code;
    /**
     * 标记
     */
    private String flag;
    /**
     * 标题
     */
    private String title;

    /**
     * @Method
     * @Author 熊冰
     * @Version  1.0
     * @Description
     * @param null
     * @Return 是否被选中
     * @Exception
     * @Date 2019/9/6 14:23
     */
    private boolean selected;



    /**
     * 记录 treeNode 节点的 展开 / 折叠 状态。
     *
     * 1、初始化节点数据时，如果设定 treeNode.open = true，则会直接展开此节点
     *
     * 2、叶子节点 treeNode.open = false
     *
     * 3、为了解决部分朋友生成 json 数据出现的兼容问题, 支持 "false","true" 字符串格式的数据
     *
     * 4、非异步加载模式下，无子节点的父节点设置 open=true 后，可显示为展开状态，但异步加载模式下不会生效。（v3.5.15+）
     *
     * 默认值：false
     *
     * true 表示节点为 展开 状态
     *
     * false 表示节点为 折叠 状态
     */
    private boolean open = false;

    private boolean expand = false;


    /**
     * 1、设置节点是否隐藏 checkbox / radio [setting.check.enable = true 时有效]
     *
     * 2、为了解决部分朋友生成 json 数据出现的兼容问题, 支持 "false","true" 字符串格式的数据
     *
     * 默认值：false
     *
     * true 表示此节点不显示 checkbox / radio，不影响勾选的关联关系，不影响父节点的半选状态。
     *
     * false 表示节点具有正常的勾选功能
     */
    private boolean nocheck;

    /**
     * 节点名称。
     *
     * 1、如果不使用 name 属性保存节点名称，请修改 setting.data.key.name
     *
     * 默认值：无
     */
    private String name;

    /**
     * 记录 treeNode 节点是否为父节点。
     *
     * 1、初始化节点数据时，根据 treeNode.children 属性判断，有子节点则设置为 true，否则为 false
     *
     * 2、初始化节点数据时，如果设定 treeNode.isParent = true，即使无子节点数据，也会设置为父节点
     *
     * 3、为了解决部分朋友生成 json 数据出现的兼容问题, 支持 "false","true" 字符串格式的数据
     *
     * true 表示是父节点
     *
     * false 表示不是父节点
     */
    private boolean isParent;



    /**
     * 最简单的 click 事件操作。相当于 onclick="..." 的内容。 如果操作较复杂，请使用 onClick 事件回调函数。
     *
     * 由于 IE 对于 onclick 和
     * click事件共存时的处理与其他浏览器不同，所以请不要利用此参数控制是否允许跳转的操作（例如：treeNode.click = "return
     * false;"）。如有类似需求，请不要使用 url 属性设置网址，同时利用 onClick 回调函数控制跳转。
     *
     * 默认值：无
     *
     * 标准 javascript 语法， 例如：alert("test"); 等
     */
    private String click;

    /**
     * 1、设置节点的 checkbox / radio 是否禁用 [setting.check.enable = true 时有效]
     *
     * 2、为了解决部分朋友生成 json 数据出现的兼容问题, 支持 "false","true" 字符串格式的数据
     *
     * 3、请勿对已加载的节点修改此属性，禁止 或 取消禁止 请使用 setChkDisabled() 方法
     *
     * 4、初始化时，如果需要子孙节点继承父节点的 chkDisabled 属性，请设置 setting.check.chkDisabledInherit 属性
     *
     * 默认值：false
     *
     * true 表示此节点的 checkbox / radio 被禁用。
     *
     * false 表示此节点的 checkbox / radio 可以使用。
     */
    private boolean chkDisabled;

    /**
     * 节点的子节点数据集合。
     *
     * 1、如果不使用 children 属性保存子节点数据，请修改 setting.data.key.children
     *
     * 2、异步加载时，对于设置了 isParent = true 的节点，在展开时将进行异步加载
     *
     * 默认值：无
     */
    private List<XTreeNode> children;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getpId() {
        return pId;
    }

    public void setpId(Object pId) {
        this.pId = pId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public boolean isChkDisabled() {
        return chkDisabled;
    }

    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    public List<XTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<XTreeNode> children) {
        this.children = children;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    @Override
    public String toString() {
        return "XTreeNode{" +
                "id=" + id +
                ", pId=" + pId +
                ", sort='" + sort + '\'' +
                ", key='" + key + '\'' +
                ", label='" + label + '\'' +
                ", code='" + code + '\'' +
                ", flag='" + flag + '\'' +
                ", title='" + title + '\'' +
                ", selected=" + selected +
                ", open=" + open +
                ", expand=" + expand +
                ", nocheck=" + nocheck +
                ", name='" + name + '\'' +
                ", isParent=" + isParent +
                ", click='" + click + '\'' +
                ", chkDisabled=" + chkDisabled +
                ", children=" + children +
                '}';
    }
}
