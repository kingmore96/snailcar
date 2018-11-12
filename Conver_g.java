import java.nio.ByteBuffer;

public class Conver_g {

    //类所属的字节数组缓冲区，用于字节数组和long相互转换
    private static ByteBuffer byteBuffer = ByteBuffer.allocate(8);

    /**
     * long转换为字节数组
     * @param x
     * @return
     */
    public static byte[] long2bytes(long x){
        byteBuffer.putLong(0,x);
        System.out.println("before array():"+byteBuffer);
        byte[] array = byteBuffer.array();
        System.out.println("after array():" + byteBuffer);
        return array;
    }

    /**
     * 字节数组转换为long
     * @return
     */
    public static long bytes2long(byte[] b){
        System.out.println("Before put:" + byteBuffer);
        byteBuffer.put(b,0,b.length);
        System.out.println("After put:" + byteBuffer);
        byteBuffer.flip();
        System.out.println("After flip()" + byteBuffer);
        System.out.println("Before getLong:" + byteBuffer);
        long aLong = byteBuffer.getLong();
        System.out.println("After getLong:" + byteBuffer);
        return aLong;
    }



    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {

        long l = Conver_g.bytes2long(Conver_g.long2bytes((long) 123));
        System.out.println(l);
//        System.out.println("----------Test allocate--------");
//        System.out.println("before alocate:"
//                + Runtime.getRuntime().freeMemory());
//
//        // 如果分配的内存过小，调用Runtime.getRuntime().freeMemory()大小不会变化？
//        // 要超过多少内存大小JVM才能感觉到？
//        ByteBuffer buffer = ByteBuffer.allocate(1024000);
//        System.out.println("buffer = " + buffer);
//
//        System.out.println("after alocate:"
//                + Runtime.getRuntime().freeMemory());
//
//        // 这部分直接用的系统内存，所以对JVM的内存没有影响
//        ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400);
//        System.out.println("directBuffer = " + directBuffer);
//        System.out.println("after direct alocate:"
//                + Runtime.getRuntime().freeMemory());
//
//        System.out.println("----------Test wrap--------");
//        byte[] bytes = new byte[32];
//        buffer = ByteBuffer.wrap(bytes);
//        System.out.println(buffer);
//
//        buffer = ByteBuffer.wrap(bytes, 10, 10);
//        System.out.println(buffer);
//
//
//        System.out.println("--------Test clear----------");
//        System.out.println("before clear" + buffer);
//        buffer.clear();
//        System.out.println("after clear:" + buffer);
//        buffer.position(5);
//        buffer.mark();
//        buffer.position(10);
//        System.out.println("--------Test reset----------");
//        System.out.println("before reset:" + buffer);
//        buffer.reset();
//        System.out.println("after reset:" + buffer);
//
//        System.out.println("--------Test rewind--------");
//        buffer.clear();
//        buffer.position(10);
//        buffer.limit(15);
//        System.out.println("before rewind:" + buffer);
//        buffer.rewind();
//        System.out.println("before rewind:" + buffer);
//
//        System.out.println("--------Test compact--------");
//        buffer.clear();
//        buffer.put("abcd".getBytes());
//        System.out.println("before compact:" + buffer);
//        System.out.println(new String(buffer.array()));
//        buffer.flip();
//        System.out.println("after flip:" + buffer);
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        System.out.println("after three gets:" + buffer);
//        System.out.println("\t" + new String(buffer.array()));
//        buffer.compact();
//        System.out.println("after compact:" + buffer);
//        System.out.println("\t" + new String(buffer.array()));
//
//        System.out.println("------Test get-------------");
//        buffer = ByteBuffer.allocate(32);
//        buffer.put((byte) 'a').put((byte) 'b').put((byte) 'c').put((byte) 'd')
//                .put((byte) 'e').put((byte) 'f');
//        System.out.println("before flip()" + buffer);
//        // 转换为读取模式
//        buffer.flip();
//        System.out.println("before get():" + buffer);
//        System.out.println((char) buffer.get());
//        System.out.println("after get():" + buffer);
//        // get(index)不影响position的值
//        System.out.println((char) buffer.get(2));
//        System.out.println("after get(index):" + buffer);
//        byte[] dst = new byte[10];
//        buffer.get(dst, 0, 2);
//        System.out.println("after get(dst, 0, 2):" + buffer);
//        System.out.println("\t dst:" + new String(dst));
//        System.out.println("buffer now is:" + buffer);
//        System.out.println("\t" + new String(buffer.array()));
//
//        System.out.println("--------Test put-------");
//        ByteBuffer bb = ByteBuffer.allocate(32);
//        System.out.println("before put(byte):" + bb);
//        System.out.println("after put(byte):" + bb.put((byte) 'z'));
//        System.out.println("\t" + bb.put(2, (byte) 'c'));
//        // put(2,(byte) 'c')不改变position的位置
//        System.out.println("after put(2,(byte) 'c'):" + bb);
//        System.out.println("\t" + new String(bb.array()));
//        // 这里的buffer是 abcdef[pos=3 lim=6 cap=32]
//        bb.put(buffer);
//        System.out.println("after put(buffer):" + bb);
//        System.out.println("\t" + new String(bb.array()));
    }

}
