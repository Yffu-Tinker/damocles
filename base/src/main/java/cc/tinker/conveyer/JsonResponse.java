package cc.tinker.conveyer;


/**
 * 服务器端响应json转换类。
 * <p/>
 */
public class JsonResponse implements java.io.Serializable, Cloneable {
    private static final long serialVersionUID = -5254768593025959969L;
    public static final String KEY = "jsonResponse";
    private long id = 0;
    private String sid = null;
    private Object data;
    private Integer stateCode;

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public static JsonResponse newOk() {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setStateCode(200);
        return jsonResponse;
    }

    public static JsonResponse newOk(Object data) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setData(data);
        jsonResponse.setStateCode(200);
        return jsonResponse;
    }

    public static JsonResponse newError(String msg) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setStateCode(500);
        return jsonResponse;
    }

    @Override
    public JsonResponse clone() {
        JsonResponse response = null;
        try {
            response = (JsonResponse) super.clone();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return response;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }
}
