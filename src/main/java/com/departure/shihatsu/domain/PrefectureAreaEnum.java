package com.departure.shihatsu.domain;

/**
 * 都道府県列挙クラス
 */
public enum PrefectureAreaEnum {
        HOKKAIDO    (1, "北海道"),
        TOHOKU      (2, "東北"),
        KANTO       (3, "関東"),
        CHUBU       (4, "中部"),
        KINKI       (5, "近畿"),
        CHUGOKU     (6, "中国"),
        SHIKOKU     (7, "四国"),
        KYUSHU      (8, "九州")
        ;
    
        private final int code_;
        private final String area_;
    
        private PrefectureAreaEnum(int code, String area) {
            code_ = code;
            area_ = area;
        }
    
        /**
         * 地域コードを取得します。
         * 
         * @return 地域コード
         */
        public int getCode() {
            return code_;
        }
    
        /**
         * 地域名を取得します。
         * 
         * @return 地域名
         */
        public String getArea() {
            return area_;
        }
    
        /**
         * 指定されたコードのPrefectureAreaオブジェクトを取得します。
         * 存在しない場合はnullを返します。
         * 
         * @param code    地域コード
         * @return PrefectureAreaオブジェクト
         */
        public static  PrefectureAreaEnum getByCode(int code) {
            for ( PrefectureAreaEnum p : PrefectureAreaEnum.values()) {
                if (p.getCode() == code) return p;
            }
            return null;
        }
    
        /**
         * 指定された地域名のPrefectureオブジェクトを取得します。
         * 存在しない場合はnullを返します。
         * 
         * @param area    地域名
         * @return PrefectureAreaオブジェクト
         */
        public static   PrefectureAreaEnum getByArea(String area) {
            for (  PrefectureAreaEnum p :  PrefectureAreaEnum.values()) {
                if (p.getArea().equals(area)) return p;
            }
            return null;
        }
    }

