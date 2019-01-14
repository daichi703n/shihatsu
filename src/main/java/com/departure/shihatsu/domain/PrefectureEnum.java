package com.departure.shihatsu.domain;

// 都道府県Ref: http://javasampleokiba.blog.fc2.com/blog-entry-31.html
// 地域Ref: http://www.asahi-net.or.jp/~ax2s-kmtn/ref/jisx0401.html
/**
 * 都道府県列挙クラス
 */
public enum PrefectureEnum {
        HOKKAIDO    (1, "北海道", 1, "北海道"),
        AOMORI      (2, "青森", 2, "東北"),
        IWATE       (3, "岩手", 2, "東北"),
        MIYAGI      (4, "宮城", 2, "東北"),
        AKITA       (5, "秋田", 2, "東北"),
        YAMAGATA    (6, "山形", 2, "東北"),
        FUKUSHIMA   (7, "福島", 2, "東北"),
        IBARAKI     (8, "茨城", 3, "関東"),
        TOCHIGI     (9, "栃木", 3, "関東"),
        GUNMA       (10, "群馬", 3, "関東"),
        SAITAMA     (11, "埼玉", 3, "関東"),
        CHIBA       (12, "千葉", 3, "関東"),
        TOKYO       (13, "東京", 3, "関東"),
        KANAGAWA    (14, "神奈川", 3, "関東"),
        NIIGATA     (15, "新潟", 4, "中部"),
        TOYAMA      (16, "富山", 4, "中部"),
        ISHIKAWA    (17, "石川", 4, "中部"),
        FUKUI       (18, "福井", 4, "中部"),
        YAMANASHI   (19, "山梨", 4, "中部"),
        NAGANO      (20, "長野", 4, "中部"),
        GIFU        (21, "岐阜", 4, "中部"),
        SHIZUOKA    (22, "静岡", 4, "中部"),
        AICHI       (23, "愛知", 4, "中部"),
        MIE         (24, "三重", 5, "近畿"),
        SHIGA       (25, "滋賀", 5, "近畿"),
        KYOTO       (26, "京都", 5, "近畿"),
        OSAKA       (27, "大阪", 5, "近畿"),
        HYOGO       (28, "兵庫", 5, "近畿"),
        NARA        (29, "奈良", 5, "近畿"),
        WAKAYAMA    (30, "和歌山", 5, "近畿"),
        TOTTORI     (31, "鳥取", 6, "中国"),
        SHIMANE     (32, "島根", 6, "中国"),
        OKAYAMA     (33, "岡山", 6, "中国"),
        HIROSHIMA   (34, "広島", 6, "中国"),
        YAMAGUCHI   (35, "山口", 6, "中国"),
        TOKUSHIMA   (36, "徳島", 7, "四国"),
        KAGAWA      (37, "香川", 7, "四国"),
        EHIME       (38, "愛媛", 7, "四国"),
        KOCHI       (39, "高知", 7, "四国"),
        FUKUOKA     (40, "福岡", 8, "九州"),
        SAGA        (41, "佐賀", 8, "九州"),
        NAGASAKI    (42, "長崎", 8, "九州"),
        KUMAMOTO    (43, "熊本", 8, "九州"),
        OITA        (44, "大分", 8, "九州"),
        MIYAZAKI    (45, "宮崎", 8, "九州"),
        KAGOSHIMA   (46, "鹿児島", 8, "九州"),
        OKINAWA     (47, "沖縄", 8, "九州"),
        ;
    
        private final int code_;
        private final String text_;
        private final int areaCode_;
        private final String areaName_;
    
        private PrefectureEnum(int code, String text, int areaCode, String areaName) {
            code_ = code;
            text_ = text;
            areaCode_ = areaCode;
            areaName_ = areaName;
        }
    
        /**
         * 都道府県コードを取得します。
         * 
         * @return 都道府県コード
         */
        public int getCode() {
            return code_;
        }
    
        /**
         * 都道府県名を取得します。
         * "都"、"府"、"県"は含みません。
         * 
         * @return 都道府県名
         */
        public String getText() {
            return text_;
        }
    
        public int getAreaCode() { return areaCode_;}
        public String getAreaName() { return areaName_;}

        /**
         * 都道府県名を取得します。
         * "都"、"府"、"県"を含みます。
         * 
         * @return 都道府県名
         */
        public String getFullText() {
            switch (this) {
                case HOKKAIDO:
                    return getText();
                case TOKYO:
                    return getText() + "都";
                case KYOTO:
                case OSAKA:
                    return getText() + "府";
                default:
                    return getText() + "県";
            }
        }
    
        /**
         * 指定されたコードのPrefectureオブジェクトを取得します。
         * 存在しない場合はnullを返します。
         * 
         * @param code    都道府県コード
         * @return Prefectureオブジェクト
         */
        public static  PrefectureEnum getByCode(int code) {
            for ( PrefectureEnum p : PrefectureEnum.values()) {
                if (p.getCode() == code) return p;
            }
            return null;
        }
    
        /**
         * 指定された都道府県名（"都"、"府"、"県"は含まない）のPrefectureオブジェクトを取得します。
         * 存在しない場合はnullを返します。
         * 
         * @param text    都道府県名
         * @return Prefectureオブジェクト
         */
        public static  PrefectureEnum getByText(String text) {
            for ( PrefectureEnum p : PrefectureEnum.values()) {
                if (p.getText().equals(text)) return p;
            }
            return null;
        }
    
        /**
         * 指定された都道府県名（"都"、"府"、"県"は含む）のPrefectureオブジェクトを取得します。
         * 存在しない場合はnullを返します。
         * 
         * @param fullText    都道府県名
         * @return Prefectureオブジェクト
         */
        public static  PrefectureEnum getByFullText(String fullText) {
            for ( PrefectureEnum p : PrefectureEnum.values()) {
                if (p.getFullText().equals(fullText)) return p;
            }
            return null;
        }
    }