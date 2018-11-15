public class ConverTools_g {

    /**
     * 判断字符串是否是十六进制数
     * @param hexString
     * @return
     */
    public static boolean isHex(String hexString){
        int i = 0;
        //判断是否以0x或者0X开头
        if( hexString.length() > 2 ){
            if(hexString.charAt(0) == '0' && (hexString.charAt(1) == 'X' || hexString.charAt(1) == 'x')){
                i = 2;
            }
        }

        for (; i < hexString.length() ; i++){
            char ch = hexString.charAt(i);
            if((ch >= '0' && ch <= '9') ||(ch >= 'A' && ch <= 'F')||(ch >= 'a' && ch <= 'f')){
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * 求某个数的幂次方
     * @param value
     * @param count
     * @return
     */
    public static int getPower(int value,int count) throws Exception {

        if( count < 0 ){
            throw new Exception("count can not less than 0");
        }

        if( count == 1){
            return 1;
        }

        int sum = 1;
        for (int i = 0; i < count; i++) {
            sum = sum * value;
        }
        return sum;
    }

    /**
     * 计算十六进制字符对应的数值（十进制）
     * @param ch
     * @return
     */
    public static int getHex(char ch) throws Exception {
        if( ch >= '0' && ch <= '9'){
            return (int)ch - '0';
        }
        if( ch >= 'A' && ch <= 'F'){
            return (int) (ch - 'A'+10);
        }
        if( ch >= 'a' && ch <= 'f'){
            return (int) (ch - 'a'+10);
        }
        throw new Exception("error param");
    }

    /**
     * SIM卡号转为16进制伪ip地址
     * sim_no:13311000029
     * 伪ip:0B00809D
     * @param sim_no
     * @return
     */
    public static String num2HexIp(Long sim_no){
        return null;
    }
}
