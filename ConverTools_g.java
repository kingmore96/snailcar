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
        //转换为字符串
        String s = sim_no.toString();

        //去除首位
        if( s.length() == 11 ){
            s = s.substring(1);
        }

        int temp = 0x08;
        int hIp = 0;
        int[] cIp = new int[4];

        //每两位取出来存到int数组中
        int HIP = Integer.valueOf(s.substring(0,2));
        hIp = HIP - 30;

        cIp[0] = Integer.valueOf(s.substring(2,4));
        cIp[1] = Integer.valueOf(s.substring(4,6));
        cIp[2] = Integer.valueOf(s.substring(6,8));
        cIp[3] = Integer.valueOf(s.substring(8));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cIp.length; i++) {
            //数组中第三个第四个特殊处理
            if((temp & hIp) != 0){
                sb.append(Conver_g.byte2HexString((byte)(cIp[i] | 128)));
            }else{
                sb.append(Conver_g.byte2HexString((byte)(cIp[i])));
            }
            temp = temp >> 1;
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 16进制字符串转化为sim卡号
     * eg:0B00809D
     * sim_no:13311000029
     * @param hexString
     * @return
     */
    public static Long hexIp2Num(String hexString){
        int temp = 0x08;
        int hIp = 3;
        int[] cIp = new int[4];

        cIp[0] = Integer.valueOf(hexString.substring(0,2),16);
        cIp[1] = Integer.valueOf(hexString.substring(2,4),16);
        cIp[2] = Integer.valueOf(hexString.substring(4,6),16);
        cIp[3] = Integer.valueOf(hexString.substring(6,8),16);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int t = cIp[i];

            if((temp & hIp) != 0){
                t = t % 128;
            }

            if( t < 10 ){
                sb.append("0"+t);
            }
            else{
                sb.append(t);
            }

            temp = temp >> 1;
        }
        return Long.valueOf(new String("133")+sb.toString());
    }

    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(ConverTools_g.num2HexIp(new Long("13311000029")));
        System.out.println(ConverTools_g.hexIp2Num(ConverTools_g.num2HexIp(new Long("13311000029"))));
    }
}
