package bsj.test;

import bsj.code.Conver_g;

/**
 * 工具类，用于构建客户端Message
 */
class MessageBuilder_g {

    /**
     * serverMsg:2929300006AA238822150D 点名指令
     * @param serverMsg
     * @param type
     * @return
     */
    static byte[] createMsg(byte[] serverMsg,int type){
        String ip = getIp(serverMsg);
        return createMsg(ip,type);
    }

    /**
     * 构建客户端message：80 81 85
     * @param ip
     * @param type
     * @return
     */
    static byte[] createMsg(String ip,int type){
        String hexstr;
        byte[] byteBuf = null;
        switch (type) {
            case 80:
                hexstr = "2929800038"
                        + ip
                        + "17092809182403900337121374210000021578007E91FFFC3B00000F000000FF00000006008800007E9100060089FFFFFFFDEE0D";
                byteBuf = Conver_g.hexString2Bytes(hexstr);
                break;
            case 81:
                hexstr = "2929810038"
                        + ip
                        + "17100208563103849505121236490000001378607EA8FFFC3200000F0000000000300006008800607EA800060089FFFFFFFDFE0D";
                byteBuf = Conver_g.hexString2Bytes(hexstr);
                break;
            case 85:
                hexstr = "2929850028" + ip + "171016082803038549151213753500000263F809487C7FFC3800000F0000000000686F0D";
                byteBuf = Conver_g.hexString2Bytes(hexstr);
                break;
            // 请求报文解析后:
            // 2929B300E0AA238822414343BFAA3A3135732C414343B9D83A37323030732C544350D0C4CCF83A313830732C49502855293A34372E39342E3230302E31393A383031332C495028542934372E39342E3230302E31393A383031332CB6CCD0C5D6D0D0C43A2CB3ACCBD9BCECB2E23A30732CCAA1B5E7C4A3CABD3ABFAA5BD2D1CAA1B5E75D2C475053B3D6D0F8B6A8CEBB
            // 2017-10-02 09:25:07.633 INFO 4484 --- [nio-8090-exec-1]
            // c.j.s.spring.controller.AskCarAction :
            default:
                break;
        }
        return byteBuf;
    }

    private static String getIp(byte[] serverMsg) {
        String msg = Conver_g.bytes2HexString(serverMsg);
        return msg.substring(10,18);
    }

}
