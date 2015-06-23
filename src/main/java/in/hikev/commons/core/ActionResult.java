package in.hikev.commons.core;

/**
 * Created by Administrator on 2015/6/23.
 */
public class ActionResult {

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int statusCode;
    public Object data;

    public ActionResult(int statusCode,Object data){
        this.statusCode = statusCode;
        this.data = data;
    }

    public ActionResult(){}
}
