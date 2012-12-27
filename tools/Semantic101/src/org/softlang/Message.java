package org.softlang;

import java.util.List;
/*

{
  method: "getResourceTriples",
  params: [{resourceID:"Language-3AHaskell"}]
}
 */
public class Message {
    private String method;

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    private List<String> params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
