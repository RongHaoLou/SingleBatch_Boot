package com.indusfo.spc.vo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;


public class CommonUtil {
	private static DecimalFormat dfTow = new DecimalFormat("#.##");

	/**
	 * @return 当前日期
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * @return 精确到分钟的当前日期
	 */
	public static Date getMinuteDate() {
		return DateUtils.addMinutes(DateUtils.setSeconds(DateUtils.setMilliseconds(getDate(), 0), 0), 1);
	}

	/**
	 * @param var
	 * @return 是否为非空
	 */
	public static boolean notEmpty(String var) {
		return StringUtils.isNotBlank(var);
	}

	/**
	 * @param var
	 * @return 是否为空
	 */
	public static boolean empty(String var) {
		return StringUtils.isBlank(var);
	}

	/**
	 * @param var
	 * @return 是否非空
	 */
	public static boolean notEmpty(Number var) {
		return null != var;
	}

	/**
	 * @param var
	 * @return 是否为空
	 */
	public static boolean empty(Number var) {
		return null == var;
	}

	/**
	 * @param var
	 * @return 是否非空
	 */
	public static boolean notEmpty(List<?> var) {
		return null != var && !var.isEmpty();
	}

	/**
	 * @param var
	 * @return 是否为空
	 */
	public static boolean empty(List<?> var) {
		return null == var || var.isEmpty();
	}

	/**
	 * @param var
	 * @return 是否非空
	 */
	public static boolean notEmpty(Map<?, ?> var) {
		return null != var && !var.isEmpty();
	}

	/**
	 * @param var
	 * @return 是否为空
	 */
	public static boolean empty(Map<?, ?> var) {
		return null == var || var.isEmpty();
	}

	/**
	 * @param file
	 * @return 是否非空
	 */
	public static boolean notEmpty(File file) {
		return null != file && file.exists();
	}

	/**
	 * @param file
	 * @return 是否为空
	 */
	public static boolean empty(File file) {
		return null == file || !file.exists();
	}

	/**
	 * @param var
	 * @return 是否非空
	 */
	public static boolean notEmpty(Object[] var) {
		return null != var && 0 < var.length;
	}

	/**
	 * @param var
	 * @return 是否为空
	 */
	public static boolean empty(Object[] var) {
		return null == var || 0 == var.length;
	}

	public static String join(Object[] var, String separator) {
		if (empty(var)) {
			return "";
		} else {
			return StringUtils.join(var, ",");
		}
	}

	public static String join(List<?> var, String separator) {
		if (empty(var)) {
			return "";
		} else {
			return StringUtils.join(var, ",");
		}
	}

	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	public static boolean checkFileSafe(String filePath) {
		return !filePath.contains("../");
	}

	public static String getFileSize(long size) {
		String ret = " byte";
		if (size > 1023) {
			ret = " KB";
			size = size / 1024;
			if (size > 1023) {
				ret = " MB";
				size = size / 1024;
				if (size > 1023) {
					size = size / 1024;
					ret = " GB";
				}
			}
		}
		return dfTow.format(size) + ret;
	}


	/**
	 * @param str
	 * @return
	 */
	public static String htmlSpecialChars(String str) {
		if (StringUtils.isNotBlank(str)) {
			str = str.replaceAll("&", "&amp;");
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("\"", "&quot;");
			return str;
		} else {
			return "";
		}
	}


	public static String getCodeNo(String beginCode, int i) {
		int len = beginCode.length();
		int v = Integer.valueOf(beginCode) + i;
		String code = "";
		for (int j = 0; j < len; j++) {
			code += "0";
		}
		code += v;
		return code.substring(code.length() - len);
	}


	public static String getCodeRandomDigits(Integer digits, Integer start) {
		return String.format("%0" + digits + "d", start);
	}

	public static List<ZTreeNode> getTree(List<ZTreeNode> nodes, Object id) {
		List<ZTreeNode> nNodes = new ArrayList<>();
		Iterator<ZTreeNode> it = nodes.iterator();
		while (it.hasNext()) {
			ZTreeNode _Node = it.next();
			if ((id == null && _Node.getPId() == null) || (_Node.getPId() != null && id != null && (_Node.getPId().equals(id)))) {
				_Node.setChildren(getTree(nodes, _Node.getId()));
				_Node.setParent(!_Node.getChildren().isEmpty());
				_Node.setOpen(true);
				nNodes.add(_Node);
			}
		}
		return nNodes;
	}

	public static List<SimpleTreeNode> getSimpleTree(List<SimpleTreeNode> nodes, Integer id, List<Integer> idList) {
		List<SimpleTreeNode> nNodes = new ArrayList<>();
		Iterator<SimpleTreeNode> it = nodes.iterator();
		while (it.hasNext()) {
			SimpleTreeNode _Node = it.next();
			if ((id == null && _Node.getpId() == null) || (_Node.getpId() != null && id != null && (_Node.getpId().equals(id)))) {
				_Node.setChildren(getSimpleTree(nodes, _Node.getId(), idList));
				if (null!=idList && idList.contains(_Node.getId())) {
					List<SimpleTreeNode> children = _Node.getChildren();
					// 只有最后一子级才设置为true，前端VUE框架
					if (null==children || children.size()==0) {
						_Node.setChecked(true);
					}
				}
				nNodes.add(_Node);
			}
		}
		return nNodes;
	}

	public static List<AssociTionTreeNode> getAssociTionTree(List<AssociTionTreeNode> nodes, Integer id, List<Integer> idList) {
		List<AssociTionTreeNode> nNodes = new ArrayList<>();
		Iterator<AssociTionTreeNode> it = nodes.iterator();
		while (it.hasNext()) {
			AssociTionTreeNode _Node = it.next();
			if ((id == null && _Node.getpId() == null) || (_Node.getpId() != null && id != null && (_Node.getpId().equals(id)))) {
				_Node.setChildren(getAssociTionTree(nodes, _Node.getId(), idList));
				if (null!=idList && idList.contains(_Node.getId())) {
					List<AssociTionTreeNode> children = _Node.getChildren();
					// 只有最后一子级才设置为true，前端VUE框架
					if (null==children || children.size()==0) {
						_Node.setChecked(true);
					}
				}
				nNodes.add(_Node);
			}
		}
		return nNodes;
	}
}
