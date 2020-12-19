package top.szymou.utils;

public enum ProcessStatus {
    WAITTING(1, "线程等待"),
    RUNNING(2, "线程执行"),
    TERMINATED(3, "线程结束"),
    ;

    private Integer code;
    private String message;

    private ProcessStatus(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
