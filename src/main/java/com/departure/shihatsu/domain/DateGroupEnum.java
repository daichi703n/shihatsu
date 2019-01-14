package com.departure.shihatsu.domain;

/**
 * 曜日区分クラス
 */
public enum DateGroupEnum {
        WEEKDAY     ("weekday",  "平日"),
        SATURDAY    ("saturday", "土曜"),
        HOLIDAY     ("holiday",  "日・祝日"),
        ;
    
        private final String type_;
        private final String typeName_;
    
        private DateGroupEnum(String type, String typeName) {
            type_ = type;
            typeName_ = typeName;
        }
    
        /**
         * 曜日コードを取得します。
         * 
         * @return 曜日コード
         */
        public String getType() {
            return type_;
        }
    
        /**
         * 曜日名を取得します。
         * 
         * @return 曜日名
         */
        public String getTypeName() {
            return typeName_;
        }
    
        /**
         * 指定されたコードのTypeオブジェクトを取得します。
         * 存在しない場合はnullを返します。
         * 
         * @param type    都道府県コード
         * @return Typeオブジェクト
         */
        public static  DateGroupEnum getByType(String type) {
            for ( DateGroupEnum d : DateGroupEnum.values()) {
                if (d.getType() == type) return d;
            }
            return null;
        }
    
        /**
         * 指定された曜日名のTypeオブジェクトを取得します。
         * 存在しない場合はnullを返します。
         * 
         * @param typeName    都道府県名
         * @return Typeオブジェクト
         */
        public static  DateGroupEnum getByTypeName(String typeName) {
            for ( DateGroupEnum d : DateGroupEnum.values()) {
                if (d.getTypeName().equals(typeName)) return d;
            }
            return null;
        }
    }