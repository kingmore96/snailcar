package bsj.message;

/**
 * Message接口，所有的Message都可以拿到header end ip messagetype length contents
 */
public interface Message_g {
    byte[] getHeader();

    byte[] getEnd();

    byte[] getIp();

    byte[] getMsgType();

    byte[] getLength();

    byte[] getContents();

}
