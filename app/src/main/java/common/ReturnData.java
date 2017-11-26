package common;

import java.io.UnsupportedEncodingException;

/**
 * Created by FUSHUAI on 2017/9/28.
 */

public class ReturnData {
    private String htmlData;
    private HttpConnProp updatedProp;
    private String encoding;

    /**
     *
     * @param red
     *            返回的html信息
     * @param hcp
     *            更新后的HttpConnProp，特别是cookie
     * @param encoding
     *            返回的string的编码
     */
    public ReturnData(String red, HttpConnProp hcp, String encoding) {
        this.htmlData = red;
        this.updatedProp = hcp;
        this.encoding = encoding;
    }
    /**
     *  把结果的string 转byte[]
     * @return byte[]数组
     */
    public byte[] getReturnByteData() {

        try {
            return this.htmlData.getBytes(this.encoding);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public String getHtmlData() {
        return this.htmlData;
    }

    public void setHtmlData(String setb) {
        this.htmlData = setb;
    }

    public HttpConnProp getUpdateProp() {
        return this.updatedProp;
    }

    public void setUpdateProp(HttpConnProp uprop) {
        this.updatedProp = uprop;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String e) {
        this.encoding = e;
    }

};
