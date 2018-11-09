import java.text.DecimalFormat;

/**
 * String TO BCD ，BCD TO String
 * 例：保留三位小数，String="2222.54" 编码为BCD，然后再解码为"2222.540"
 * BCD采用压缩编码，8421方式
 */

public class BCDCode_g {

    /**
     * str to BCD
     * @param data
     * @return
     */
    public static byte[] str2Bcd(String data){
        //处理String，带上3位小数，并消除.
        DecimalFormat df = new DecimalFormat("#.000");
        data = df.format(Double.valueOf(data));
        data = data.replace(".","");

        return str2bcd(data);
    }

    public static byte[] str2bcd(String bcd){
        //参数校验
        if( null == bcd || bcd.isEmpty()){
            return null;
        }else{
            // 获取字节数组长度，1个十进制数为4位，2个十进制数8位
            // 当字符串长度为偶数，字节数组长度为字符串长度/2
            // 当字符串长度为奇数，字节数组长度为字符串长度/2+1

            int size = bcd.length() / 2;
            int remainder = bcd.length() % 2;

            byte[] bcdByte = new byte[size+remainder];

            // 编码为BCD，两个两个转，可能还剩最后一个没有转
            for (int i = 0; i < size; i++) {
                int low = Integer.parseInt(bcd.substring(2*i,2*i+1));
                int high = Integer.parseInt(bcd.substring(2*i+1,2*i+2));
                bcdByte[i] = (byte) (high << 4 | low);
            }

            // 当长度为奇数时，前面4位补f
            if( remainder > 0 ){
                int low = Integer.parseInt(bcd.substring(bcd.length()-1));
                bcdByte[size+remainder-1] = (byte)(0xf << 4 | low);
            }

            return bcdByte;
        }
    }

    /**
     * bcd to str
     */
    public static String bcd2Str(byte[] bcdByte) {
        String data = bcd2str(bcdByte);
        System.out.println(data);
        //上一步生成的不带小数点，需要加上小数点
        StringBuilder sb = new StringBuilder();
        int i = Integer.parseInt(data);
        sb.append(i/1000);
        sb.append(".");
        sb.append(i%1000);
        return sb.toString();
    }

    public static String bcd2str(byte[] bcd){
        //参数校验
        if( null == bcd || bcd.length == 0 ){
            return "";
        }else{
            //创建用于存放字符串的StringBuilder
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bcd.length; i++) {
                int low = (bcd[i] & 0x0f);
                sb.append(low);

                //高字节需要判断，如果是填充的f，则丢弃
                int high = ((bcd[i] & 0xf0) >> 4);
                if( high != 0xf){
                    sb.append(high);
                }
            }
            return sb.toString();
        }
    }

    //测试用例
    public static void main(String[] args) {
        String s = "2222";
        System.out.println(bcd2Str(str2Bcd(s)));
    }
}
