package com.github.ican2056.doit;

/**
 * 正则工具类
 */
public class RegU {

    /**
     *共有12个特殊字符，使用String.split时需要转义
     */
    public static String escapeStr(String str) {
        switch (str) {
            case "$":
                return "\\$";
            case "(":
                return "\\(";
            case ")":
                return "\\)";
            case "*":
                return "\\*";
            case "+":
                return "\\+";
            case ".":
                return "\\.";
            case "[":
                return "\\[";
            case "?":
                return "\\?";
            case "\\":
                return "\\\\";
            case "^":
                return "\\^";
            case "{":
                return "\\{";
            case "|":
                return "\\|";
        }
        return str;
    }

}
