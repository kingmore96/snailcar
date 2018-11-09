/**
 * 解析字节数组，解析器
 */
public class BufferParser_g {

    private byte[] buffer;

    private int startIndex = 0;

    private byte[] bufferOutPut = new byte[0];

    public BufferParser_g(byte[] buffer) {
        this.buffer = buffer;
    }

    public BufferParser_g(){};

    /**
     * 按照4个字节读buffer，并且转换为int类型返回
     * @return
     */
    public int readInt(){
        int number = bytes2Int(readByLength(startIndex, 4, buffer));
        startIndex += 4;
        return number;
    }

    /**
     * 按照一个字节读取buffer
     * @return
     */
    public byte readByte(){
        return buffer[startIndex++];
    }

    /**
     * 按照长度读取字节
     * @param length
     * @return
     */
    public byte[] readBytes(int length){
        byte[] bytes = readByLength(startIndex, length, buffer);
        startIndex += length;
        return bytes;
    }

    /**
     * 字节数组转换为int类型
     * @param bytes
     * @return
     */
    public static int bytes2Int(byte[] bytes){
        int number = bytes[0];
        number |= (( bytes[1] << 8 ) & 0xFF00);
        number |= (( bytes[2] << 16 ) & 0xFF0000);
        number |= (( bytes[3] << 24 ) & 0xFF000000);
        return number;
    }

    /**
     * int类型转换为字节数组
     * @param number
     * @return
     */
    public static byte[] int2Bytes(int number){
        byte[] b = new byte[4];
        b[0] = (byte)(number);
        b[1] = (byte)((number >> 8));
        b[2] = (byte) ((number >> 16));
        b[3] = (byte) ((number >> 24));
        return b;
    }

    /**
     * 根据长度读取buffer中的数据，使用System.arraycopy
     * @return
     */
    public static byte[] readByLength(int startIndex,int length,byte[] bytes){
        byte[] b = new byte[length];
        System.arraycopy(bytes,startIndex,b,0,length);
        return b;
    }


    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        BufferParser_g bf = new BufferParser_g();
        System.out.println(bf.bytes2Int(bf.int2Bytes(129999999)));
    }
}
